package lt.vu.labutis.jsontomicroservicegenerator.factory;

import lt.vu.labutis.jsontomicroservicegenerator.json.Service;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ClassNameUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class ServiceFactory {
    public static JavaClass<?> createServiceClass(Service service, String rootPackage) {
        JavaClassSource serviceClass = Roaster.create(JavaClassSource.class);
        serviceClass.setPackage(rootPackage + ".service");
        serviceClass.addAnnotation(org.springframework.stereotype.Service.class);
        serviceClass.setName(ClassNameUtils.getFormattedClassName(service.getName()));
        return serviceClass;
    }
}
