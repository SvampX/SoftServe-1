package annotation.processor;

import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AnnotationProcessor {
    private static Map<String, Object> servicesMap;
    private static Set<Object> objects;
    private static Properties injectProps;

    static {
        objects = new HashSet<>();
        servicesMap = new HashMap<>();
        injectProps = new Properties();
    }

    public static void main(String[] args) {
        inspectServices();
        System.out.println("servicesMap = " + servicesMap);
    }

    private static void inspectServices() {
        loadProperties();
        Reflections reflections = new Reflections("annotation");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation.Service.class, true);
        classes.forEach(clazz -> {
            Constructor<?> constructor;
            try {
                constructor = clazz.getConstructor();
                Object obj = constructor.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    loadInjectionValues(field, clazz, obj);
                }
                servicesMap.put(clazz.getName(), obj);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadInjectionValues(Field field, Class<?> clazz, Object obj) {
        injectProps.computeIfPresent(clazz.getSimpleName() + "." + field.getName(), (key, value) ->
        {
            try {
                field.setAccessible(true);
                field.set(obj, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

    private static void loadProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "propTest.properties";
        try {
            injectProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getServicesMap() {
        assert servicesMap.size() != 0 : "Any services have been loaded";
        return servicesMap;
    }
}
