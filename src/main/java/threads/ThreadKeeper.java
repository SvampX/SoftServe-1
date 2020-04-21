package threads;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ThreadKeeper {
    ThreadGroup group = new ThreadGroup("Group");
    private long startTime;
    private long endTime;
    private int threadPoolSize;
    private int startNumber;
    private int endNumber;
    private boolean isSynchronized;
    private int threadLoad;
    private List<List<Integer>> tasks;
    private List<Integer> primes = new ArrayList<>();

    public ThreadKeeper(int threadPoolSize, int startNumber, int endNumber, boolean isSynchronized) {
        this.threadPoolSize = threadPoolSize;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.isSynchronized = isSynchronized;
    }

    private List<List<Integer>> setTasks(int threadPoolSize, int startNumber, int endNumber) {
        List<List<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < threadPoolSize; i++) {
            tasks.add(new ArrayList<Integer>());
        }
        for (int i = startNumber; i <= endNumber; i++) {
            tasks.get(i % threadPoolSize).add(i);
        }
        return tasks;
    }

    public void start() {
        System.out.println("Thread pool size is " + threadPoolSize);
        if (startNumber > endNumber || threadPoolSize < 1) throw new IllegalArgumentException();
        tasks = setTasks(threadPoolSize, startNumber, endNumber);
        for (int i = 0; i < threadPoolSize; i++) {
            new Thread(group, new PrimeNumberCounter(i, tasks.get(i), primes, isSynchronized)).start();
        }
        while (group.activeCount() > 0) {

        }
        endTime = System.currentTimeMillis();
        Collections.sort(primes);
        System.out.println(primes.size());
        System.out.println("\nprimes = " + primes);
        System.out.println();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(endTime - startTime);
        System.out.println("Time on calculations = " + cal.get(Calendar.MILLISECOND));
    }
}
