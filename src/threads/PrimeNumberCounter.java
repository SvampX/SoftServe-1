package threads;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberCounter extends Thread {
    private int id;
    private int number;
    private int startNumber;
    private int endNumber;
    private boolean isPrime;
    List<Integer> primes;
    List<Integer> localPrimes = new ArrayList<>();

    public PrimeNumberCounter(int id, int number, List<Integer> primes) {
        this.id = id;
        this.number = number;
        this.primes = primes;
    }

    public PrimeNumberCounter(int id, int startNumber, int endNumber, List<Integer> primes) {
        this.id = id;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.primes = primes;
    }

    private void calculate(int number){
        isPrime = true;
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
                localPrimes.add(number);
        }
    }

    @Override
    public void run() {
        if(startNumber == 0 && endNumber == 0){
            calculate(number);
        } else {
            for (int i = startNumber; i < endNumber; i++) {
                calculate(i);
            }
        }
        System.out.println("localPrimes of Thread-" + id + " = " + localPrimes);
        synchronized (primes){
            primes.addAll(localPrimes);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
