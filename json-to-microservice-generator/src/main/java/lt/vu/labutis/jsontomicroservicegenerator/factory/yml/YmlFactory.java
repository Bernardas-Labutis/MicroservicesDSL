package lt.vu.labutis.jsontomicroservicegenerator.factory.yml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.*;
import lt.vu.labutis.jsontomicroservicegenerator.json.Gateway;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import lt.vu.labutis.jsontomicroservicegenerator.json.Route;
import lt.vu.labutis.jsontomicroservicegenerator.json.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YmlFactory {
    public static void createYmlForService(Service service, Integer registryPort, Path resourcesPath) {
        ServiceYml serviceYml = new ServiceYml();

        Server server = new Server();
        server.setPort(service.getPort());
        serviceYml.setServer(server);

        Spring spring = new Spring();
        Application application = new Application();
        application.setName(service.getName());
        spring.setApplication(application);
        serviceYml.setSpring(spring);

        Eureka eureka = new Eureka();

        Client client = new Client();
        ServiceUrl serviceUrl = new ServiceUrl();
        serviceUrl.setDefaultZone("http://localhost:" + registryPort + "/eureka/");
        client.setServiceUrl(serviceUrl);
        client.setFetchRegistry(true);
        client.setRegisterWithEureka(true);
        eureka.setClient(client);

        Instance instance = new Instance();
        instance.setHostname("localhost");
        eureka.setInstance(instance);

        serviceYml.setEureka(eureka);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        try {
            objectMapper.writeValue(new File(resourcesPath + "/application.yml"), serviceYml);
        } catch (IOException e) {
            throw new RuntimeException("could not create application.yml");
        }
    }

    public static void createYmlForRegistry(Registry registry, Path resourcesPath) {
        ServiceYml serviceYml = new ServiceYml();

        Server server = new Server();
        server.setPort(registry.getPort());
        serviceYml.setServer(server);

        Spring spring = new Spring();
        Application application = new Application();
        application.setName("SERVICE-REGISTRY");
        spring.setApplication(application);
        serviceYml.setSpring(spring);

        Eureka eureka = new Eureka();

        Client client = new Client();
        client.setFetchRegistry(false);
        client.setRegisterWithEureka(true);
        eureka.setClient(client);

        serviceYml.setEureka(eureka);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        try {
            objectMapper.writeValue(new File(resourcesPath + "/application.yml"), serviceYml);
        } catch (IOException e) {
            throw new RuntimeException("could not create application.yml");
        }
    }

    public static void createYmlForGateway(Gateway gateway, Integer registryPort, Path resourcesPath) {
        ServiceYml serviceYml = new ServiceYml();

        Server server = new Server();
        server.setPort(gateway.getPort());
        serviceYml.setServer(server);

        Spring spring = new Spring();
        Application application = new Application();
        application.setName("CLOUD-GATEWAY");
        spring.setApplication(application);
        serviceYml.setSpring(spring);

        Eureka eureka = new Eureka();

        Client client = new Client();
        ServiceUrl serviceUrl = new ServiceUrl();
        serviceUrl.setDefaultZone("http://localhost:" + registryPort + "/eureka/");
        client.setServiceUrl(serviceUrl);
        client.setFetchRegistry(true);
        client.setRegisterWithEureka(true);
        eureka.setClient(client);

        Instance instance = new Instance();
        instance.setHostname("localhost");
        eureka.setInstance(instance);

        serviceYml.setEureka(eureka);

        Cloud cloud = new Cloud();
        lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.Gateway gatewayYml = new lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.Gateway();
        List<lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.Route> routes = new ArrayList<>();
        for (Route route : gateway.getRoutes()) {
            lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.Route routeYml = new lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated.Route();
            routeYml.setId(route.getServiceName());
            routeYml.setUri("lb://" + route.getServiceName());
            routeYml.setPredicates(Arrays.asList("Path=" + route.getPath() + "/**"));
            routeYml.setFilters(Arrays.asList("StripPrefix=1"));
            routes.add(routeYml);
        }
        gatewayYml.setRoutes(routes);
        cloud.setGateway(gatewayYml);
        spring.setCloud(cloud);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        try {
            objectMapper.writeValue(new File(resourcesPath + "/application.yml"), serviceYml);
        } catch (IOException e) {
            throw new RuntimeException("could not create application.yml");
        }
    }

    public static void createYmlForTest(Registry registry, Path resourcesPath) {
        ServiceYml serviceYml = new ServiceYml();

        Server server = new Server();
        server.setPort(4000);
        serviceYml.setServer(server);

        Spring spring = new Spring();
        Application application = new Application();
        application.setName("test");
        spring.setApplication(application);
        serviceYml.setSpring(spring);

        Eureka eureka = new Eureka();

        Client client = new Client();
        ServiceUrl serviceUrl = new ServiceUrl();
        serviceUrl.setDefaultZone("http://localhost:" + registry.getPort() + "/eureka/");
        client.setServiceUrl(serviceUrl);
        client.setFetchRegistry(true);
        client.setRegisterWithEureka(true);
        eureka.setClient(client);

        Instance instance = new Instance();
        instance.setHostname("localhost");
        eureka.setInstance(instance);

        serviceYml.setEureka(eureka);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        try {
            objectMapper.writeValue(new File(resourcesPath + "/application.yml"), serviceYml);
        } catch (IOException e) {
            throw new RuntimeException("could not create application.yml");
        }
    }
}
