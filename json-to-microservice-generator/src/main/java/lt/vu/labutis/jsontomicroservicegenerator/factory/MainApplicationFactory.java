package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.Gateway;
import lt.vu.labutis.jsontomicroservicegenerator.json.Registry;
import lt.vu.labutis.jsontomicroservicegenerator.json.Service;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ClassNameUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class MainApplicationFactory {
    public static JavaClass<?> createMainApplicationForService(Service service, String rootPackage) {
        JavaClassSource mainApplicationClass = Roaster.create(JavaClassSource.class);
        mainApplicationClass.setPackage(rootPackage);
        mainApplicationClass.addImport(SpringApplication.class);
        mainApplicationClass.addAnnotation(SpringBootApplication.class);
        mainApplicationClass.addAnnotation(EnableEurekaClient.class);

        mainApplicationClass
                .setPublic()
                .setName(ClassNameUtils.getFormattedClassName(service.getName()) + "Application");

        MethodSource<JavaClassSource> mainMethod = mainApplicationClass.addMethod();
        mainMethod
                .setPublic()
                .setStatic(true)
                .setReturnTypeVoid()
                .setName("main")
                .addParameter(String[].class, "args");

        mainMethod.setBody("SpringApplication.run(" + mainApplicationClass.getName() + ".class, args);");


        return mainApplicationClass;
    }

    public static JavaClass<?> createMainApplicationForRegistry(Registry registry, String rootPackage) {
        JavaClassSource mainApplicationClass = Roaster.create(JavaClassSource.class);
        mainApplicationClass.setPackage(rootPackage);
        mainApplicationClass.addImport(SpringApplication.class);
        mainApplicationClass.addAnnotation(SpringBootApplication.class);
        mainApplicationClass.addAnnotation(EnableEurekaServer.class);

        mainApplicationClass
                .setPublic()
                .setName("ServiceRegistryApplication");

        MethodSource<JavaClassSource> mainMethod = mainApplicationClass.addMethod();
        mainMethod
                .setPublic()
                .setStatic(true)
                .setReturnTypeVoid()
                .setName("main")
                .addParameter(String[].class, "args");

        mainMethod.setBody("SpringApplication.run(" + mainApplicationClass.getName() + ".class, args);");


        return mainApplicationClass;
    }

    public static JavaClass<?> createMainApplicationForGateway(Gateway gateway, String rootPackage) {
        JavaClassSource mainApplicationClass = Roaster.create(JavaClassSource.class);
        mainApplicationClass.setPackage(rootPackage);
        mainApplicationClass.addImport(SpringApplication.class);
        mainApplicationClass.addAnnotation(SpringBootApplication.class);
        mainApplicationClass.addAnnotation(EnableEurekaClient.class);

        mainApplicationClass
                .setPublic()
                .setName("CloudGatewayApplication");

        MethodSource<JavaClassSource> mainMethod = mainApplicationClass.addMethod();
        mainMethod
                .setPublic()
                .setStatic(true)
                .setReturnTypeVoid()
                .setName("main")
                .addParameter(String[].class, "args");

        mainMethod.setBody("SpringApplication.run(" + mainApplicationClass.getName() + ".class, args);");


        return mainApplicationClass;
    }

    public static JavaClass<?> createMainApplicationForTest(Registry registry, String rootPackage) {
        JavaClassSource mainApplicationClass = Roaster.create(JavaClassSource.class);
        mainApplicationClass.setPackage(rootPackage);
        mainApplicationClass.addImport(SpringApplication.class);
        mainApplicationClass.addAnnotation(SpringBootApplication.class);
        mainApplicationClass.addAnnotation(EnableEurekaClient.class);

        mainApplicationClass
                .setPublic()
                .setName("MainTestApplication");

        MethodSource<JavaClassSource> mainMethod = mainApplicationClass.addMethod();
        mainMethod
                .setPublic()
                .setStatic(true)
                .setReturnTypeVoid()
                .setName("main")
                .addParameter(String[].class, "args");

        mainMethod.setBody(SpringApplication.class.getSimpleName() + ".run(" + mainApplicationClass.getName() + ".class, args);");

        MethodSource<JavaClassSource> restTemplateLoadBalancedBean = mainApplicationClass.addMethod();
        restTemplateLoadBalancedBean.addAnnotation(LoadBalanced.class);
        restTemplateLoadBalancedBean.addAnnotation(Bean.class);
        restTemplateLoadBalancedBean
                .setPublic()
                .setReturnType(RestTemplate.class)
                .setName("restTemplateLoadBalanced")
                .addParameter(RestTemplateBuilder.class, "restTemplateBuilder");

        restTemplateLoadBalancedBean.setBody("return restTemplateBuilder.build();");

        MethodSource<JavaClassSource> restTemplateBeanMethod = mainApplicationClass.addMethod();
        restTemplateBeanMethod.addAnnotation(Bean.class);
        restTemplateBeanMethod
                .setPublic()
                .setReturnType(RestTemplate.class)
                .setName("restTemplate")
                .addParameter(RestTemplateBuilder.class, "restTemplateBuilder");

        restTemplateBeanMethod.setBody("return restTemplateBuilder.build();");

        return mainApplicationClass;
    }
}
