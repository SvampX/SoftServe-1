package annotation.processor;

import annotation.entities.FirstAnnotatedClass;
import annotation.entities.SecondAnnotatedClass;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

import static org.junit.Assert.*;

public class AnnotationProcessorTest {

    @Test
    public void getServicesMap() {
        AnnotationProcessor.loadProperties("C:\\Users\\SvampX\\IdeaProjects\\SoftServe-2\\src\\main\\resources\\propTest.properties");
        AnnotationProcessor.main(null);
        Map<String, Object> servicesMap = AnnotationProcessor.getServicesMap();
        assertEquals(2, servicesMap.size());
        assertEquals("Alex", ((FirstAnnotatedClass) servicesMap.get("annotation.entities.FirstAnnotatedClass")).getName());
        assertNull(((SecondAnnotatedClass) servicesMap.get("annotation.entities.SecondAnnotatedClass")).getBirth());
    }
}