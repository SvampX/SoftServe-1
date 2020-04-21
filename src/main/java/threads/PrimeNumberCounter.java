package threads;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberCounter extends Thread {
    List<Integer> task;
    List<Integer> primes;
    List<Integer> localPrimes = new ArrayList<>();
    private boolean isSynchronized;
    private int id;
    private int number;
    private boolean isPrime;

    public PrimeNumberCounter(int id, List<Integer> task, List<Integer> primes, boolean isSynchronized) {
        this.id = id;
        this.task = task;
        this.primes = primes;
        this.isSynchronized = isSynchronized;
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

    @Override
    public void run() {

        for (int i = 0; i < task.size(); i++) {
            calculate(task.get(i));
        }

        System.err.println("localPrimes of Thread-" + id + " = " + localPrimes);
        if (!isSynchronized)
            saveAllPrimes(localPrimes);
    }
}
