package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.factory.yml.YmlFactory;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import org.apache.commons.io.FileUtils;
import org.jboss.forge.roaster.model.JavaClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegistryFactory {
    public static void createServiceRegistryProject(Registry registry, Path targetPath) throws IOException {
        String javaProjectName = "serviceregistry";
        Path projectPath = Paths.get(targetPath + "/service-registry");
        FileUtils.deleteDirectory(new File(String.valueOf(projectPath)));

        Path srcPath = Paths.get(projectPath + "/src");
        Path mainPath = Paths.get(srcPath + "/main");
        Path resourcesPath = Paths.get(mainPath + "/resources");

        Path javaPath = Paths.get(mainPath + "/java/lt/vu/microly/" + javaProjectName);

        Path testPath = Paths.get(srcPath + "/test/java/lt/vu/microly/" + javaProjectName);
        Files.createDirectories(projectPath);
        Files.createDirectories(srcPath);
        Files.createDirectories(resourcesPath);

        Files.createDirectories(javaPath);

        Files.createFile(Path.of(resourcesPath + "/application.properties"));
        YmlFactory.createYmlForRegistry(registry, resourcesPath);

        Files.createDirectories(testPath);

        PomFactory.createPomForRegistry(registry, projectPath);

        String rootPackage = "lt.vu.microly." + javaProjectName;
        JavaClass<?> mainApplication = MainApplicationFactory.createMainApplicationForRegistry(registry, rootPackage);
        Path mainApplicationPath = Files.createFile(Path.of(javaPath + "/" + mainApplication.getName() + ".java"));
        FileUtils.writeStringToFile(mainApplicationPath.toFile(), mainApplication.toString(), Charset.forName(StandardCharsets.UTF_8.name()));
    }
}
