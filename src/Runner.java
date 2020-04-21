import threads.ThreadKeeper;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        int threadPool;
        int startNumber;
        int endNumber;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter thread pool size:");
        threadPool = scanner.nextInt();
        System.out.println("Enter start number:");
        startNumber = scanner.nextInt();
        System.out.println("Enter end number:");
        endNumber = scanner.nextInt();


            ThreadKeeper threadKeeper = new ThreadKeeper(threadPool, startNumber, endNumber, args!=null);
            threadKeeper.start();

    }
}
