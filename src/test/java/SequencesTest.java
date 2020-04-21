import org.junit.jupiter.api.Test;
import sequences.hierarchy.Displayer;
import sequences.hierarchy.Searcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SequencesTest {

    @Test
    void TwoFileTest() throws InterruptedException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        rootPath = rootPath.replace("/", "\\").substring(1,rootPath.length());
        System.setIn(new ByteArrayInputStream((rootPath + "test.txt\n" +
                rootPath + "test.txt" +
                "\n42").getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        PrintStream out = System.out;
        System.setOut(printStream);
        new Displayer("Test").run();
        System.setOut(out);
        System.out.println(byteArrayOutputStream.toString());
        assertTrue(byteArrayOutputStream.toString().contains("The longest sequences of 24 symbols"));
    }

    @Test
    void searcherTest() {
        String text = "hehe karp-rabin algo is cool, cause it is karp-rabin algo!\n" +
                "karp-rabin algo is cool,ho hehe cause it is karp-rabin algo!\n" +
                "hehe";
        Searcher searcher = new Searcher(new Displayer("Test"), 3, text, "TestSearcher");
        searcher.start();
        searcher.setText(text);
    }
}