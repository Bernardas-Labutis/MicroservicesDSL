package lt.vu.labutis.jsontomicroservicegenerator.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ClassNameUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class DtoFactory {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JavaClass createRequestDto(String request, String rootPackage, Path mainJavaPath, String methodName) {
        JavaClass javaClass = null;
        try {
            javaClass = createDto(request, rootPackage, mainJavaPath, methodName + "Request");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return javaClass;
    }

    public static JavaClass createResponseDto(String response, String rootPackage, Path mainJavaPath, String methodName) {
        JavaClass javaClass = null;
        try {
            javaClass = createDto(response, rootPackage, mainJavaPath, methodName + "Response");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return javaClass;
    }


    public static JavaClass createDto(String json, String rootPackage, Path mainJavaPath, String dtoClassName) {
        JavaClass javaClass = null;
        JCodeModel codeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(
                new RuleFactory(
                        config,
                        new Jackson2Annotator(config),
                        new SchemaStore()),
                new SchemaGenerator());

        try {
            dtoClassName = ClassNameUtils.getFormattedClassName(dtoClassName);
            String dtoPackage = rootPackage + ".dto";
            JType generatedClass = mapper.generate(codeModel, dtoClassName, dtoPackage, json);

            String generationRootPath = /*"target/" +*/ mainJavaPath.toString();
            File generationRootDir = new File(generationRootPath);

            codeModel.build(generationRootDir);

            String generatedClassRelativePath = dtoPackage.replace('.', '\\');
            String generatedClassFullPath =
                    generationRootPath + "/" + generatedClassRelativePath + "/" + dtoClassName + ".java";

            javaClass = Roaster.parse(JavaClassSource.class, new File(generatedClassFullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaClass;
    }
}
