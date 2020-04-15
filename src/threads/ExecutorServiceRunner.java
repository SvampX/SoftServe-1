package threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceRunner {
    int threadPool;
    int startNumber;
    int endNumber;
    ExecutorService service = Executors.newFixedThreadPool(2);
}