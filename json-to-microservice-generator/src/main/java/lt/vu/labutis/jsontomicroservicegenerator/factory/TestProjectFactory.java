package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.factory.yml.YmlFactory;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import org.apache.commons.io.FileUtils;
import org.jboss.forge.roaster.model.JavaClass;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestProjectFactory {
    public static void createTestProject(Registry registry, Path targetPath) throws IOException {
        String javaProjectName = "test";
        Path projectPath = Paths.get(targetPath + "/test");

        Path srcPath = Paths.get(projectPath + "/src");
        Path mainPath = Paths.get(srcPath + "/main");
        Path mainJavaPath = Paths.get(mainPath + "/java");
        Path resourcesPath = Paths.get(mainPath + "/resources");

        Path javaPath = Paths.get(mainPath + "/java/lt/vu/microly/" + javaProjectName);
        Path dtoPath = Paths.get(javaPath + "/dto");
        Path testPath = Paths.get(srcPath + "/test/java/lt/vu/microly/" + javaProjectName);

        Files.createDirectories(projectPath);
        Files.createDirectories(srcPath);
        Files.createDirectories(resourcesPath);

        Files.createDirectories(mainJavaPath);
        Files.createDirectories(javaPath);
        Files.createDirectories(dtoPath);

        Files.createFile(Path.of(resourcesPath + "/application.properties"));
        YmlFactory.createYmlForTest(registry, resourcesPath);

        Files.createDirectories(testPath);

        PomFactory.createPomForTest(projectPath);

        String rootPackage = "lt.vu.microly." + javaProjectName;
        JavaClass<?> mainApplication = MainApplicationFactory.createMainApplicationForTest(registry, rootPackage);
        Path mainApplicationPath = Files.createFile(Path.of(javaPath + "/" + mainApplication.getName() + ".java"));
        FileUtils.writeStringToFile(mainApplicationPath.toFile(), mainApplication.toString(), Charset.forName(StandardCharsets.UTF_8.name()));

        String rootTestPackage = "lt.vu.microly." + javaProjectName;
        JavaClass<?> testApplication = TestApplicationFactory.createTestApplication(registry, rootTestPackage, mainJavaPath);
        Path testApplicationPath = Files.createFile(Path.of(testPath + "/" + testApplication.getName() + ".java"));
        FileUtils.writeStringToFile(testApplicationPath.toFile(), testApplication.toString(), Charset.forName(StandardCharsets.UTF_8.name()));
    }
}
