package threads;

import java.util.List;

public class PrimeNumberCounter extends Thread {
    private int number;
    private int startNumber;
    private int endNumber;
    private boolean isPrime = true;
    List<Integer> primes;

    public PrimeNumberCounter(int number, List<Integer> primes) {
        this.number = number;
        this.primes = primes;
    }

    public PrimeNumberCounter(int startNumber, int endNumber, List<Integer> primes) {
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
            synchronized (primes) {
                primes.add(number);
            }
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
        System.out.println("primes = " + primes);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
