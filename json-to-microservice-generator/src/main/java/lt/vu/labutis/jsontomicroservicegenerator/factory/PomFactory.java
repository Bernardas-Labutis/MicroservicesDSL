package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.Gateway;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import lt.vu.labutis.jsontomicroservicegenerator.json.Service;
import org.apache.maven.model.*;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class PomFactory {
    public static void createPomForService(Service service, Path projectPath) {
        Model model = new Model();

        Parent parent = new Parent();
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.5.5");
        parent.setRelativePath("");
        model.setParent(parent);

        model.setGroupId("lt.vu.microly");
        model.setArtifactId(service.getName());
        model.setModelVersion("4.0.0");

        Properties properties = new Properties();
        properties.put("java.version", "11");
        properties.put("spring-cloud.version", "2020.0.4");
        model.setProperties(properties);

        Dependency dependency = new Dependency();
        dependency.setGroupId("org.springframework.boot");
        dependency.setArtifactId("spring-boot-starter-data-jpa");
        model.addDependency(dependency);

        Dependency dependency1 = new Dependency();
        dependency1.setGroupId("org.springframework.boot");
        dependency1.setArtifactId("spring-boot-starter-web");
        model.addDependency(dependency1);

        Dependency dependency2 = new Dependency();
        dependency2.setGroupId("org.springframework.cloud");
        dependency2.setArtifactId("spring-cloud-starter-netflix-eureka-client");
        dependency2.setVersion("3.0.4");
        model.addDependency(dependency2);

        Dependency dependency3 = new Dependency();
        dependency3.setGroupId("org.springframework.cloud");
        dependency3.setArtifactId("spring-cloud-starter-sleuth");
        dependency3.setVersion("3.0.4");
        model.addDependency(dependency3);


        Dependency dependency4 = new Dependency();
        dependency4.setGroupId("org.springframework.cloud");
        dependency4.setArtifactId("spring-cloud-starter-zipkin");
        dependency4.setVersion("2.2.8.RELEASE");
        model.addDependency(dependency4);

        Dependency dependency5 = new Dependency();
        dependency5.setGroupId("com.h2database");
        dependency5.setArtifactId("h2");
        dependency5.setScope("runtime");
        model.addDependency(dependency5);

        Dependency dependency6 = new Dependency();
        dependency6.setGroupId("org.springframework.boot");
        dependency6.setArtifactId("spring-boot-starter-test");
        dependency6.setScope("test");
        model.addDependency(dependency6);

        Exclusion exclusion = new Exclusion();
        exclusion.setGroupId("org.junit.vintage");
        exclusion.setArtifactId("junit-vintage-engine");
        dependency6.addExclusion(exclusion);

        DependencyManagement dependencyManagement = new DependencyManagement();
        Dependency managedDependency1 = new Dependency();
        managedDependency1.setGroupId("org.springframework.cloud");
        managedDependency1.setArtifactId("spring-cloud-dependencies");
        managedDependency1.setVersion("${spring-cloud.version}");
        managedDependency1.setType("pom");
        managedDependency1.setScope("import");
        dependencyManagement.addDependency(managedDependency1);
        model.setDependencyManagement(dependencyManagement);

        Build build = new Build();
        Plugin plugin = new Plugin();
        plugin.setGroupId("org.springframework.boot");
        plugin.setArtifactId("spring-boot-maven-plugin");
        build.addPlugin(plugin);
        model.setBuild(build);

        createPom(projectPath, model);
    }

    public static void createPomForRegistry(Registry registry, Path projectPath) {
        Model model = new Model();

        Parent parent = new Parent();
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.5.5");
        parent.setRelativePath("");
        model.setParent(parent);

        model.setGroupId("lt.vu.microly");
        model.setArtifactId("service-registry");
        model.setModelVersion("4.0.0");

        Properties properties = new Properties();
        properties.put("java.version", "11");
        properties.put("spring-cloud.version", "2020.0.4");
        model.setProperties(properties);

        Dependency eurekaServer = new Dependency();
        eurekaServer.setGroupId("org.springframework.cloud");
        eurekaServer.setArtifactId("spring-cloud-starter-netflix-eureka-server");
        model.addDependency(eurekaServer);

        Dependency test = new Dependency();
        test.setGroupId("org.springframework.boot");
        test.setArtifactId("spring-boot-starter-test");
        test.setScope("test");
        model.addDependency(test);

        DependencyManagement dependencyManagement = new DependencyManagement();
        Dependency cloudManagement = new Dependency();
        cloudManagement.setGroupId("org.springframework.cloud");
        cloudManagement.setArtifactId("spring-cloud-dependencies");
        cloudManagement.setVersion("${spring-cloud.version}");
        cloudManagement.setType("pom");
        cloudManagement.setScope("import");
        dependencyManagement.addDependency(cloudManagement);
        model.setDependencyManagement(dependencyManagement);

        Build build = new Build();
        Plugin plugin = new Plugin();
        plugin.setGroupId("org.springframework.boot");
        plugin.setArtifactId("spring-boot-maven-plugin");
        build.addPlugin(plugin);
        model.setBuild(build);

        createPom(projectPath, model);
    }

    public static void createPomForGateway(Gateway gateway, Path projectPath) {
        Model model = new Model();

        Parent parent = new Parent();
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.5.5");
        parent.setRelativePath("");
        model.setParent(parent);

        model.setGroupId("lt.vu.microly");
        model.setArtifactId("cloud-gateway");
        model.setModelVersion("4.0.0");

        Properties properties = new Properties();
        properties.put("java.version", "11");
        properties.put("spring-cloud.version", "2020.0.4");
        model.setProperties(properties);

        Dependency starterActuator = new Dependency();
        starterActuator.setGroupId("org.springframework.boot");
        starterActuator.setArtifactId("spring-boot-starter-actuator");
        model.addDependency(starterActuator);

        Dependency starterGateway = new Dependency();
        starterGateway.setGroupId("org.springframework.cloud");
        starterGateway.setArtifactId("spring-cloud-starter-gateway");
        model.addDependency(starterGateway);

        Dependency eurekaClient = new Dependency();
        eurekaClient.setGroupId("org.springframework.cloud");
        eurekaClient.setArtifactId("spring-cloud-starter-netflix-eureka-client");
        model.addDependency(eurekaClient);

        Dependency test = new Dependency();
        test.setGroupId("org.springframework.boot");
        test.setArtifactId("spring-boot-starter-test");
        test.setScope("test");
        model.addDependency(test);

        DependencyManagement dependencyManagement = new DependencyManagement();
        Dependency cloudManagement = new Dependency();
        cloudManagement.setGroupId("org.springframework.cloud");
        cloudManagement.setArtifactId("spring-cloud-dependencies");
        cloudManagement.setVersion("${spring-cloud.version}");
        cloudManagement.setType("pom");
        cloudManagement.setScope("import");
        dependencyManagement.addDependency(cloudManagement);
        model.setDependencyManagement(dependencyManagement);

        Build build = new Build();
        Plugin plugin = new Plugin();
        plugin.setGroupId("org.springframework.boot");
        plugin.setArtifactId("spring-boot-maven-plugin");
        build.addPlugin(plugin);
        model.setBuild(build);

        createPom(projectPath, model);
    }

    public static void createPomForParent(Path projectPath, List<String> modules) throws IOException {
        Model model = new Model();

        model.setModelVersion("4.0.0");
        model.setGroupId("lt.vu.microly");
        model.setArtifactId("microservice-architecture");
        model.setVersion("1.0-SNAPSHOT");
        model.setPackaging("pom");
        model.setModules(modules);

        createPom(projectPath, model);
    }


    private static void createPom(Path projectPath, Model model) {
        try (FileWriter fileWriter = new FileWriter(projectPath + "/pom.xml")) {
            new MavenXpp3Writer().write(fileWriter, model);
        } catch (IOException e) {
            throw new RuntimeException("unable to create pom.xml file", e);
        }
    }

    public static void createPomForTest(Path projectPath) {
        Model model = new Model();

        Parent parent = new Parent();
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.5.5");
        parent.setRelativePath("");
        model.setParent(parent);

        model.setGroupId("lt.vu.microly");
        model.setArtifactId("test");
        model.setModelVersion("4.0.0");

        Properties properties = new Properties();
        properties.put("java.version", "11");
        properties.put("spring-cloud.version", "2020.0.4");
        model.setProperties(properties);

        Dependency dependency1 = new Dependency();
        dependency1.setGroupId("org.springframework.boot");
        dependency1.setArtifactId("spring-boot-starter-web");
        model.addDependency(dependency1);

        Dependency dependency2 = new Dependency();
        dependency2.setGroupId("org.springframework.cloud");
        dependency2.setArtifactId("spring-cloud-starter-netflix-eureka-client");
        dependency2.setVersion("3.0.4");
        model.addDependency(dependency2);

        Dependency dependency3 = new Dependency();
        dependency3.setGroupId("org.springframework.cloud");
        dependency3.setArtifactId("spring-cloud-starter-sleuth");
        dependency3.setVersion("3.0.4");
        model.addDependency(dependency3);


        Dependency dependency4 = new Dependency();
        dependency4.setGroupId("org.springframework.cloud");
        dependency4.setArtifactId("spring-cloud-starter-zipkin");
        dependency4.setVersion("2.2.8.RELEASE");
        model.addDependency(dependency4);

        Dependency dependency5 = new Dependency();
        dependency5.setGroupId("com.h2database");
        dependency5.setArtifactId("h2");
        dependency5.setScope("runtime");
        model.addDependency(dependency5);

        Dependency dependency6 = new Dependency();
        dependency6.setGroupId("org.springframework.boot");
        dependency6.setArtifactId("spring-boot-starter-test");
        dependency6.setScope("test");
        model.addDependency(dependency6);

        Dependency dependency7 = new Dependency();
        dependency7.setGroupId("org.assertj");
        dependency7.setArtifactId("assertj-core");
        dependency7.setVersion("3.22.0");
        dependency7.setScope("test");
        model.addDependency(dependency7);

        Exclusion exclusion = new Exclusion();
        exclusion.setGroupId("org.junit.vintage");
        exclusion.setArtifactId("junit-vintage-engine");
        dependency6.addExclusion(exclusion);

        DependencyManagement dependencyManagement = new DependencyManagement();
        Dependency managedDependency1 = new Dependency();
        managedDependency1.setGroupId("org.springframework.cloud");
        managedDependency1.setArtifactId("spring-cloud-dependencies");
        managedDependency1.setVersion("${spring-cloud.version}");
        managedDependency1.setType("pom");
        managedDependency1.setScope("import");
        dependencyManagement.addDependency(managedDependency1);
        model.setDependencyManagement(dependencyManagement);

        Build build = new Build();
        Plugin plugin = new Plugin();
        plugin.setGroupId("org.springframework.boot");
        plugin.setArtifactId("spring-boot-maven-plugin");
        build.addPlugin(plugin);
        model.setBuild(build);

        createPom(projectPath, model);
    }
}
