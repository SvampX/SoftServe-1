package annotation.processor;

import annotation.Service;
import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class AnnotationProcessor {
    private static Map<String, Object> servicesMap = new HashMap<>();
    private static Set<Object> objects = new HashSet<>();
    private static Properties injectProps = new Properties();

    public static void main(String[] args) {
        inspectServices();
        System.out.println("servicesMap = " + servicesMap);
    }

    private static void inspectServices() {
        if(injectProps.size() == 0){
            loadProperties();
        }
        Reflections reflections = new Reflections("annotation");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation.Service.class, true);

        classes.forEach(clazz -> {
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getConstructor();
                Object obj = constructor.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
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
                servicesMap.put(clazz.getName(), obj);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadProperties() {
        //TODO find how to automatic copying properties
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "propTest.properties";
        try {
            injectProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProperties(String resource) {
        //TODO find how to automatic copying properties
        try {
            injectProps.load(new FileInputStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadService(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        if (clazz.isAnnotationPresent(Service.class)) {
            Constructor<?> constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();
            objects.add(obj);
        }
    }

    public static Map<String, Object> getServicesMap() {
        assert servicesMap.size() != 0 : "Any services have been loaded";
        return servicesMap;
    }
}
