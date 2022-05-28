package lt.vu.labutis.jsontomicroservicegenerator.service;

import lt.vu.labutis.jsontomicroservicegenerator.factory.*;
import lt.vu.labutis.jsontomicroservicegenerator.factory.yml.YmlFactory;
import lt.vu.labutis.jsontomicroservicegenerator.json.Entity;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import lt.vu.labutis.jsontomicroservicegenerator.json.Root;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateProjectsService {
    public void generateProjects(Root root) {
        try {
            String targetFolder = "./generated-sources/microservice-architecture";
            FileUtils.deleteDirectory(new File(targetFolder));
            Path targetPath = Paths.get(targetFolder);

            Registry registry = root.getRegistry();
            if (registry != null) {
                RegistryFactory.createServiceRegistryProject(registry, targetPath);
                for (lt.vu.labutis.jsontomicroservicegenerator.json.Service service : registry.getServices()) {
                    createServiceProject(service, registry.getPort(), targetPath);
                }
                if (registry.getGateway() != null) {
                    GatewayFactory.createGatewayProject(registry.getGateway(), registry.getPort(), targetPath);
                }

                TestProjectFactory.createTestProject(registry, targetPath);
            }


            List<String> modules = getModules(targetPath);
            PomFactory.createPomForParent(targetPath, modules);

            Resource wrapperResource = new ClassPathResource("maven-wrapper-files/.mvn");
            File mavenWrapperFiles = wrapperResource.getFile();
            FileUtils.copyDirectoryToDirectory(mavenWrapperFiles, targetPath.toFile());

            Resource mvnwResource = new ClassPathResource("maven-wrapper-files/mvnw");
            File mvnwFile = mvnwResource.getFile();
            FileUtils.copyFileToDirectory(mvnwFile, targetPath.toFile());

            Resource mvnwCmdResource = new ClassPathResource("maven-wrapper-files/mvnw.cmd");
            File mvnwCmdFile = mvnwCmdResource.getFile();
            FileUtils.copyFileToDirectory(mvnwCmdFile, targetPath.toFile());

            List<Path> childScriptPaths = new ArrayList<>();
            for (String moduleName : modules.stream()
                    .filter(moduleName -> !moduleName.equals("test"))
                    .collect(Collectors.toList())) {
                childScriptPaths.add(BatchScriptFactory.createScriptForModule(targetPath, moduleName));
            }

            BatchScriptFactory.createMainScript(targetPath, childScriptPaths);
            BatchScriptFactory.createScriptForTest(targetPath);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static List<String> getModules(Path path) throws IOException {
        List<String> modules = new ArrayList<>();
        Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(p -> p.endsWith("pom.xml"))
                .forEach(p -> {
                    try (FileInputStream fileInputStream = new FileInputStream(p.toFile())) {
                        MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
                        Model childModel = mavenXpp3Reader.read(fileInputStream);
                        modules.add(childModel.getArtifactId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return modules;
    }


    private static void createServiceProject(lt.vu.labutis.jsontomicroservicegenerator.json.Service service, Integer registryPort, Path generationFolderPath) throws IOException {
        Path projectPath = Paths.get(generationFolderPath + "/" + service.getName());
        FileUtils.deleteDirectory(new File(String.valueOf(projectPath)));

        Path srcPath = Paths.get(projectPath + "/src");
        Path mainPath = Paths.get(srcPath + "/main");
        Path mainJavaPath = Paths.get(mainPath + "/java");
        Path resourcesPath = Paths.get(mainPath + "/resources");

        Path javaPath = Paths.get(mainPath + "/java/lt/vu/microly/" + service.getName().replaceAll("[^a-zA-Z]", ""));
        Path controllerPath = Paths.get(javaPath + "/controller");
        Path dtoPath = Paths.get(javaPath + "/dto");
        Path entityPath = Paths.get(javaPath + "/entity");
        Path servicePath = Paths.get(javaPath + "/service");

        Path testPath = Paths.get(srcPath + "/test/java/lt/vu/microly/" + service.getName().replaceAll("[^a-zA-Z]", ""));
        Files.createDirectories(projectPath);
        Files.createDirectories(srcPath);
        Files.createDirectories(resourcesPath);

        Files.createDirectories(javaPath);
        Files.createDirectories(controllerPath);
        Files.createDirectories(dtoPath);
        Files.createDirectories(entityPath);
        Files.createDirectories(servicePath);


        Files.createFile(Path.of(resourcesPath + "/application.properties"));
        YmlFactory.createYmlForService(service, registryPort, resourcesPath);

        Files.createDirectories(testPath);

        PomFactory.createPomForService(service, projectPath);

        String rootPackage = "lt.vu.microly." + service.getName().replaceAll("[^a-zA-Z]", "");
        JavaClass<?> mainApplication = MainApplicationFactory.createMainApplicationForService(service, rootPackage);
        Path mainApplicationPath = Files.createFile(Path.of(javaPath + "/" + mainApplication.getName() + ".java"));
        FileUtils.writeStringToFile(mainApplicationPath.toFile(), mainApplication.toString(), Charset.forName(StandardCharsets.UTF_8.name()));

        JavaClass<?> serviceClass = ServiceFactory.createServiceClass(service, rootPackage);
        Path serviceClassFilePath = Files.createFile(Path.of(servicePath + "/" + serviceClass.getName() + ".java"));
        FileUtils.writeStringToFile(serviceClassFilePath.toFile(), serviceClass.toString(), Charset.forName(StandardCharsets.UTF_8.name()));

        JavaClass<?> controller = ControllerFactory.createController(service.getRestApi(), service.getName(), serviceClass, rootPackage, mainJavaPath);
        Path controllerFilePath = Files.createFile(Path.of(controllerPath + "/" + controller.getName() + ".java"));
        FileUtils.writeStringToFile(controllerFilePath.toFile(), controller.toString(), Charset.forName(StandardCharsets.UTF_8.name()));

        if (service.getDatabase() != null && service.getDatabase().getEntities() != null) {
            for (Entity entity : service.getDatabase().getEntities()) {
                JavaClass entityClass = EntityFactory.createEntity(entity, rootPackage);
                Path entityFilePath = Files.createFile(Path.of(entityPath + "/" + entityClass.getName() + ".java"));
                FileUtils.writeStringToFile(entityFilePath.toFile(), Roaster.format(entityClass.toString()), Charset.forName(StandardCharsets.UTF_8.name()));
            }
        }

    }
}
