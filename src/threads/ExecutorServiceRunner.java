package threads;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ExecutorServiceRunner {
    private static List<List<Integer>> setTasks(int threadPoolSize, int startNumber, int endNumber) {
        List<List<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < threadPoolSize; i++) {
            tasks.add(new ArrayList<Integer>());
        }
        for (int i = startNumber; i <= endNumber; i++) {
            tasks.get(i % threadPoolSize).add(i);
        }
        return tasks;
    }

    public static void main(String[] args) {

        long startTime;
        long endTime;
        int threadPool;
        int startNumber;
        int endNumber;
        List<Integer> primes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter thread pool size:");
        threadPool = scanner.nextInt();
        ExecutorService service = Executors.newFixedThreadPool(threadPool);
        System.out.println("Enter start number:");
        startNumber = scanner.nextInt();
        System.out.println("Enter end number:");
        endNumber = scanner.nextInt();
        List<List<Integer>> tasks = setTasks(threadPool, startNumber, endNumber);
        List<Future<Integer>>futures = new ArrayList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < tasks.size(); i++) {
            futures.add(service.submit(new PrimeNumberCounterCallable(i, tasks.get(i), primes, args != null)));
        }
        for (int i = 0; i <futures.size() ; i++) {
            try {
                futures.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();

        System.out.println(primes.size());
        System.out.println("primes = " + primes);
        System.out.println();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(endTime - startTime);
        System.out.println("Time on calculations = " + cal.get(Calendar.MILLISECOND));
    }
}