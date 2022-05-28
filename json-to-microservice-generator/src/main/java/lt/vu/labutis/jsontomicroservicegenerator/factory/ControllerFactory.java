package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.Method;
import lt.vu.labutis.jsontomicroservicegenerator.json.RestApi;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ClassNameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.nio.file.Path;

public class ControllerFactory {
    public enum RequestType {
        POST(PostMapping.class),
        GET(GetMapping.class),
        PUT(PutMapping.class),
        DELETE(DeleteMapping.class);

        private final Class<? extends Annotation> typeAnnotationClass;

        RequestType(Class<? extends Annotation> typeAnnotationClass) {
            this.typeAnnotationClass = typeAnnotationClass;
        }

        public Class<? extends Annotation> getTypeAnnotation() {
            return this.typeAnnotationClass;
        }

    }

    public static JavaClass<?> createController(RestApi restApi, String serviceName, JavaClass<?> serviceClass, String rootPackage, Path mainJavaPath) {
        final JavaClassSource controllerClass = Roaster.create(JavaClassSource.class);
        String className = ClassNameUtils.getFormattedClassName(serviceName + "Controller");
        controllerClass
                .setPackage(rootPackage + ".controller")
                .setPublic()
                .setName(className);
        controllerClass
                .addAnnotation(RestController.class);
        controllerClass
                .addAnnotation(RequestMapping.class)
                .setStringValue(restApi.getPath());
        controllerClass.addImport(ResponseEntity.class);

        for (Method method : restApi.getMethods()) {

            JavaClass<?> request = DtoFactory.createRequestDto(method.getRequest().toString(), rootPackage, mainJavaPath, method.getName());
            JavaClass<?> response = DtoFactory.createResponseDto(method.getResponse().toString(), rootPackage, mainJavaPath, method.getName());
            controllerClass.addImport(request.getQualifiedName());
            controllerClass.addImport(response.getQualifiedName());

            MethodSource<JavaClassSource> controllerMethod = controllerClass
                    .addMethod()
                    .setPublic()
                    .setName(method.getName());

            controllerMethod.setBody("return " + ResponseEntity.class.getSimpleName() + ".ok(new " + response.getName() + "());");

            controllerMethod.addParameter(request.getName(), StringUtils.uncapitalize(request.getName()));
            controllerMethod.setReturnType(ResponseEntity.class.getSimpleName() + "<" + response.getName() + ">");

            RequestType requestType = RequestType.valueOf(method.getType());

            controllerMethod
                    .addAnnotation(requestType.getTypeAnnotation())
                    .setStringValue(method.getPath());
        }

        controllerClass.addField()
                .setPrivate()
                .setName("service")
                .setType(serviceClass)
                .addAnnotation(Autowired.class);

        MethodSource<?> get = controllerClass.addMethod()
                .setPublic()
                .setName("get")
                .setReturnType(ResponseEntity.class.getSimpleName() + "<" + Void.class.getSimpleName() + ">");

        get.setBody("return " + ResponseEntity.class.getSimpleName() + ".ok().build();");

        get.addAnnotation(GetMapping.class).setStringValue("/test");

        return controllerClass;
    }
}
