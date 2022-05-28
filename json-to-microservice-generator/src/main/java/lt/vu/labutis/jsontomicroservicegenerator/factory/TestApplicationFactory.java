package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.*;
import org.assertj.core.api.Assertions;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.StringJoiner;

public class TestApplicationFactory {
    private enum Tests {
        TEST_HTTP_OK,
        TEST_METHODS
    }

    public static JavaClass<?> createTestApplication(Registry registry, String rootPackage, Path mainJavaPath) {
        JavaClassSource mainApplicationClass = Roaster.create(JavaClassSource.class);
        mainApplicationClass.setPackage(rootPackage);
        mainApplicationClass.addImport(ResponseEntity.class);
        mainApplicationClass.addImport(Void.class);
        mainApplicationClass.addImport(HttpStatus.class);
        mainApplicationClass.addImport(Assertions.class);
        mainApplicationClass.addImport(HttpEntity.class);
        mainApplicationClass.addImport(HttpMethod.class);

        mainApplicationClass.addAnnotation(SpringBootTest.class);

        mainApplicationClass
                .setPublic()
                .setName("TestApplication");

        FieldSource<JavaClassSource> restTemplateField = mainApplicationClass
                .addField()
                .setPrivate()
                .setType(RestTemplate.class)
                .setName("restTemplate");
        restTemplateField.addAnnotation(Autowired.class);
        restTemplateField.addAnnotation(Qualifier.class)
                .setStringValue("restTemplate");

        FieldSource<JavaClassSource> restTemplateLoadBalancedField = mainApplicationClass
                .addField()
                .setPrivate()
                .setType(RestTemplate.class)
                .setName("restTemplateLoadBalanced");
        restTemplateLoadBalancedField.addAnnotation(Autowired.class);
        restTemplateLoadBalancedField.addAnnotation(Qualifier.class)
                .setStringValue("restTemplateLoadBalanced");

        MethodSource<JavaClassSource> mainMethod = mainApplicationClass.addMethod();
        mainMethod
                .setPublic()
                .setReturnTypeVoid()
                .setName("contextLoads")
                .addAnnotation(Test.class);

        StringJoiner stringJoiner = new StringJoiner("\n\n");
        stringJoiner.add("System.out.println(\"****************************************TESTS********************************\");");
        for (Service service : registry.getServices()) {
            RestApi restApi = service.getRestApi();
            if (restApi != null && restApi.getTests() != null) {
                for (String test : new HashSet<>(restApi.getTests())) {
                    if (Tests.TEST_HTTP_OK.name().equalsIgnoreCase(test)) {
                        Route route = getRouteForService(service, registry.getGateway());
                        stringJoiner.add(getTestHttpOkStatements(service, route));
                    }
                    if (Tests.TEST_METHODS.name().equalsIgnoreCase(test)) {
                        Route route = getRouteForService(service, registry.getGateway());
                        stringJoiner.add(getTestMethodsStatements(service, route, rootPackage, mainJavaPath, mainApplicationClass));
                    }
                }
            }
        }

        mainMethod.setBody(stringJoiner.toString());

        return mainApplicationClass;
    }

    public static String getTestHttpOkStatements(Service service, Route route) {
        String restApiPath = service.getRestApi().getPath();
        String serviceName = service.getName().replaceAll("[^a-zA-Z]", "");
        String statements = "System.out.println(\"testing " + service.getName() + " controller is reachable\");\n" +
                "\t\tResponseEntity<Void> testDirect" + serviceName + " = restTemplate.getForEntity(\"http://localhost:" + service.getPort() + restApiPath + "/test\", Void.class);\n" +
                "\t\tAssertions.assertThat(testDirect" + serviceName + ".getStatusCode().equals(HttpStatus.OK)).isTrue();\n" +
                "\n" +
                "\t\tSystem.out.println(\"testing " + service.getName() + " controller is reachable through service-registry\");\n" +
                "\t\tResponseEntity<Void> testRegistry" + serviceName + " = restTemplateLoadBalanced.getForEntity(\"http://" + service.getName() + restApiPath + "/test\", Void.class);\n" +
                "\t\tAssertions.assertThat(testRegistry" + serviceName + ".getStatusCode().equals(HttpStatus.OK)).isTrue();\n" +
                "\n";
        if (route != null) {
            statements +=
                    "\t\tSystem.out.println(\"testing " + service.getName() + "controller is reachable by cloud-gateway\");\n" +
                            "\t\tResponseEntity<Void> testingCloudGateway" + serviceName + " = restTemplateLoadBalanced.getForEntity(\"http://cloud-gateway/" + route.getPath() + restApiPath + "/test\", Void.class);\n" +
                            "\t\tAssertions.assertThat(testingCloudGateway" + serviceName + ".getStatusCode().equals(HttpStatus.OK)).isTrue();";
        }

        return statements;
    }

    private static Route getRouteForService(Service service, Gateway gateway) {
        return gateway.getRoutes().stream()
                .filter(route -> route.getServiceName().equals(service.getName()))
                .findFirst()
                .orElse(null);
    }

    private static String getTestMethodsStatements(Service service, Route route, String rootPackage, Path mainJavaPath, JavaClassSource mainApplicationClass) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        String serviceName = service.getName().replaceAll("[^a-zA-Z]", "");
        stringJoiner.add("System.out.println(\"testing " + service.getName() + " REST API methods\");");


        RestApi restApi = service.getRestApi();
        for (Method method : service.getRestApi().getMethods()) {
            stringJoiner.add("System.out.println(\"testing " + service.getName() + " method ["
                    + method.getType() + " " + method.getName() + "] path " + restApi.getPath() + method.getPath() + "\");");

            JavaClass<?> requestDto = DtoFactory.createRequestDto(method.getRequest().toString(), rootPackage, mainJavaPath, method.getName());
            JavaClass<?> responseDto = DtoFactory.createResponseDto(method.getResponse().toString(), rootPackage, mainJavaPath, method.getName());

            mainApplicationClass.addImport(requestDto.getQualifiedName());
            mainApplicationClass.addImport(responseDto.getQualifiedName());

            String responseVariableName = "testingService" + serviceName + "method" + method.getName();
            stringJoiner.add("ResponseEntity<" + responseDto.getName() + "> " + responseVariableName + " = restTemplateLoadBalanced.exchange(" + "\"http://cloud-gateway/" + route.getPath() + restApi.getPath() + method.getPath()
                    + "\", HttpMethod.valueOf(\"" + method.getType() + "\"), new HttpEntity<>(new " + requestDto.getName() + "()), " + responseDto.getName() + ".class);");
            stringJoiner.add("Assertions.assertThat(" + responseVariableName + ".getStatusCode().equals(HttpStatus.OK)).isTrue();");
        }

        return stringJoiner.toString();
    }

}
