package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PrimeNumberCounterCallable implements Callable<Integer> {
    List<Integer> task;
    List<Integer> primes;
    List<Integer> localPrimes = new ArrayList<>();
    private boolean isSynchronized;
    private int id;
    private int number;
    private int startNumber;
    private int endNumber;
    private boolean isPrime;

    public PrimeNumberCounterCallable(int id, List<Integer> task, List<Integer> primes, boolean isSynchronized) {
        this.id = id;
        this.task = task;
        this.primes = primes;
        this.isSynchronized = isSynchronized;
    }

    public PrimeNumberCounterCallable(int id, int number, List<Integer> primes) {
        this.id = id;
        this.number = number;
        this.primes = primes;
    }

    public PrimeNumberCounterCallable(int id, int startNumber, int endNumber, List<Integer> primes) {
        this.id = id;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.primes = primes;
    }

    private void calculate(int number) {
        isPrime = true;
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            localPrimes.add(number);
            if (isSynchronized) {
                synchronized (primes) {
                    primes.add(number);
                }
            }
        }
    }

    private void saveAllPrimes(List<Integer> localPrimes) {
        synchronized (primes) {
            primes.addAll(localPrimes);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < task.size(); i++) {
            calculate(task.get(i));
        }

        System.err.println("localPrimes of Thread-" + id + " = " + localPrimes);
        if (!isSynchronized)
            saveAllPrimes(localPrimes);
        return 42;
    }
}
