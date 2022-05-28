package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.Entity;
import lt.vu.labutis.jsontomicroservicegenerator.json.Field;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ClassNameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class EntityFactory {
    public static JavaClass<?> createEntity(Entity entityJson, String rootPackage) {
        final JavaClassSource entityClass = Roaster.create(JavaClassSource.class);
        String className = ClassNameUtils.getFormattedClassName(entityJson.getName());

        entityClass
                .setPackage(rootPackage + ".entity")
                .setPublic()
                .setName(className)
                .addAnnotation(javax.persistence.Entity.class);

        FieldSource<JavaClassSource> idField = entityClass
                .addField()
                .setPrivate()
                .setType("Long")
                .setName("id");
        idField.addAnnotation(Id.class);
        idField.addAnnotation(GeneratedValue.class);

        for (Field field : entityJson.getFields()) {
            FieldSource<JavaClassSource> classField = entityClass
                    .addField()
                    .setPrivate()
                    .setType(field.getType())
                    .setName(field.getName());

        }

        for (FieldSource<JavaClassSource> classField : entityClass.getFields()) {
            MethodSource<JavaClassSource> getMethod = entityClass
                    .addMethod()
                    .setPublic()
                    .setReturnType(classField.getType())
                    .setName("get" + StringUtils.capitalize(classField.getName()))
                    .setBody("return this." + classField.getName() + ";");

            MethodSource<JavaClassSource> setMethod = entityClass
                    .addMethod()
                    .setPublic()
                    .setReturnTypeVoid()
                    .setName("set" + StringUtils.capitalize(classField.getName()))
                    .setParameters(classField.getType().getName() + " " + classField.getName())
                    .setBody("this." + classField.getName() + "=" + classField.getName() + ";");
        }

        return entityClass;
    }
}
