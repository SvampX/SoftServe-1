package sequences.hierarchy;

import java.io.ByteArrayInputStream;

class DisplayerTest {

    @org.junit.jupiter.api.Test
    void runTest() {
        System.setIn(new ByteArrayInputStream(("C:\\Users\\SvampX\\IdeaProjects\\SoftServe-1\\resources\\test.txt\n" +
                "C:\\Users\\SvampX\\IdeaProjects\\SoftServe-1\\resources\\test.txt\n").getBytes()));
        Displayer displayer = new Displayer("test");
        displayer.start();
    }
}