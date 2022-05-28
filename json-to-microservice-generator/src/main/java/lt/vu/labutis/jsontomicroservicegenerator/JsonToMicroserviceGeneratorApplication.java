package lt.vu.labutis.jsontomicroservicegenerator;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.*;
import org.apache.bcel.Constants;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;
import org.apache.commons.io.FileUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@SpringBootApplication
public class JsonToMicroserviceGeneratorApplication {
    public static void main(String[] args) throws IOException, JClassAlreadyExistsException {
        SpringApplication.run(JsonToMicroserviceGeneratorApplication.class, args);
    }

    public static void creatHelloWorldRoaster() throws IOException {
        String targetFolder = "./generated-sources/";
        JavaClassSource helloWorldClass = Roaster.create(JavaClassSource.class)
                .setPackage("lt.vu.roaster")
                .setName("HelloWorld");
        helloWorldClass.addMethod()
                .setPublic()
                .setStatic(true)
                .setReturnTypeVoid()
                .setName("main")
                .setBody("System.out.println(\"Hello World!\");")
                .addParameter(String[].class, "args");
        String packageFolderPath = helloWorldClass.getPackage().replace(".", "/");
        FileUtils.writeStringToFile(
                Path.of(targetFolder + "/" + packageFolderPath + "/" + helloWorldClass.getName() + ".java")
                        .toFile(),
                Roaster.format(helloWorldClass.toString()), Charset.forName(StandardCharsets.UTF_8.name()));
    }

    public static void createHelloWorldCodeModel() throws JClassAlreadyExistsException, IOException {
        String targetFolder = "./generated-sources/";
        JCodeModel codeModel = new JCodeModel();
        JPackage jp = codeModel._package("lt.vu.codemodel");
        JDefinedClass definedClass = jp._class("HelloWorld");
        JMethod method = definedClass.method(JMod.PUBLIC | JMod.STATIC, codeModel.VOID, "main");
        JBlock block = method.body();
        block.add(JExpr.invoke("System").invoke("out").invoke("println").arg("Hello World"));
        method.param(String[].class, "args");
        codeModel.build(Path.of(targetFolder).toFile());
    }

    public void createHelloWorldEclipseJdt() {

    }

    public static void createHelloWorldBcel() throws IOException {
        String targetFolder = "./generated-sources/";

        ClassGen classGen = new ClassGen("lt.vu.bcel.HelloWorldBcel",
                "java.lang.Object",
                "HelloWorldBcel.java",
                Constants.ACC_PUBLIC, null);

        InstructionList instructionList = new InstructionList();
        MethodGen ctorMethod = new MethodGen(Constants.ACC_PUBLIC, Type.VOID,
                new Type[]{Type.OBJECT}, new String[]{"arg0"}, "<init>",
                classGen.getClassName(), instructionList, classGen.getConstantPool());

        File targetFile = Path.of(
                targetFolder + "/" + classGen.getClassName().replaceAll(".", "/") + ".class")
                .toFile();

        classGen.getJavaClass().dump(targetFile);
    }
}
