import org.junit.jupiter.api.Test;
import threads.ExecutorServiceRunner;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ThreadKeeperTest {

    @Test
    void start() {
        System.setIn(new ByteArrayInputStream("3\n 10\n 100000".getBytes()));
        Runner.main(new String[1]);
        System.setIn(new ByteArrayInputStream("3\n 10\n 100000".getBytes()));
        Runner.main(null);
        System.setIn(new ByteArrayInputStream("3\n 10\n 100000".getBytes()));
        ExecutorServiceRunner.main(new String[1]);
        System.setIn(new ByteArrayInputStream("3\n 10\n 100000".getBytes()));
        ExecutorServiceRunner.main(null);
    }
}