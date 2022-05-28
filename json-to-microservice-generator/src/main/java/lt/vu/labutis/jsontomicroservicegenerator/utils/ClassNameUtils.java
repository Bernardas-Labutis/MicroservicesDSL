package lt.vu.labutis.jsontomicroservicegenerator.utils;

import org.apache.commons.lang3.StringUtils;

import javax.lang.model.SourceVersion;

public class ClassNameUtils {
    private ClassNameUtils() {
    }

    public static String getFormattedClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("className is null");
        }

        className = capitalizeFirstLetter(className.replaceAll("[^a-zA-Z]", ""));
        while (!SourceVersion.isName(className)) {
            throw new IllegalArgumentException("could not convert: " + className + " to a valid class name");
        }
        return className;
    }

    private static String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
