package lt.vu.labutis.jsontomicroservicegenerator.factory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class BatchScriptFactory {

    public static Path createScriptForModule(Path rootPath, String moduleName) throws IOException {
        Path batchScriptPath = Path.of(rootPath + "/" + moduleName + "/" + "run_" + moduleName.toLowerCase() + ".bat");
        try (FileWriter fileWriter = new FileWriter(batchScriptPath.toFile(), true)) {
            fileWriter.write("..\\mvnw spring-boot:run");
        }
        return batchScriptPath;
    }

    public static Path createScriptForTest(Path rootPath) throws IOException {
        Path batchScriptPath = Path.of(rootPath + "/test/run_test.bat");
        try (FileWriter fileWriter = new FileWriter(batchScriptPath.toFile(), true)) {
            fileWriter.write("call ..\\mvnw test");
            fileWriter.write(System.lineSeparator());
            fileWriter.write("cmd /k");
        }
        return batchScriptPath;
    }

    public static Path createMainScript(Path rootPath, List<Path> childScriptsToStart) throws IOException {
        Path batchScriptPath = Path.of(rootPath + "/run_projects.bat");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batchScriptPath.toFile(), true))) {
            writer.write("@echo STARTING PROJECTS...");
            writer.newLine();
            writer.write("call mvnw clean install -Dmaven.test.skip=true");
            writer.newLine();

            writer.write("echo OPENING NEW TERMINAL FOR: service-registry");
            writer.newLine();
            writer.write("cd service-registry");
            writer.newLine();
            writer.write("start run_service-registry.bat");
            writer.newLine();
            writer.write("cd ..");
            writer.newLine();
            writer.write("echo WAITING FOR service-registry TO START");
            writer.newLine();
            writer.write("timeout 5");
            writer.newLine();


            for (Path childScriptPath : childScriptsToStart.stream()
                    .filter(path -> !path.getFileName().toString().equals("run_service-registry.bat"))
                    .collect(Collectors.toList())) {
                writer.write("echo OPENING NEW TERMINAL FOR: " + childScriptPath.getParent().getFileName());
                writer.newLine();
                writer.write("cd " + childScriptPath.getParent().getFileName());
                writer.newLine();
                writer.write("start " + childScriptPath.getFileName().toString());
                writer.newLine();
                writer.write("cd ..");
                writer.newLine();
            }

            writer.write("cmd /k");
            writer.newLine();
        }
        return batchScriptPath;
    }
}
