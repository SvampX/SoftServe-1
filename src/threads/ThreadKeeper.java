package threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadKeeper {
    private int threadPoolSize;
    private int startNumber;
    private int endNumber;
    private int threadLoad;
    private List<Integer> primes = new ArrayList<>();

    public ThreadKeeper(int threadPoolSize, int startNumber, int endNumber, List<Integer> primes) {
        this.threadPoolSize = threadPoolSize;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.primes = primes;
    }

    public void start(){
        System.out.println("Thread pool size is " + threadPoolSize);
        if(startNumber > endNumber || threadPoolSize < 1) throw new IllegalArgumentException();
        if(endNumber - startNumber < threadPoolSize){
            System.out.println("Wow, isn't it too much threads for so little amount of numbers?");
            for (int i = startNumber; i <= endNumber; i++) {
                new PrimeNumberCounter(i, primes).start();
            }
        } else {
            threadLoad = (endNumber - startNumber) / threadPoolSize;
            System.out.println("Threads will search prime numbers from  " + startNumber + "  till  " + endNumber);
            for (int i = 0; i < threadPoolSize; i++) {
                if(i == 0){
                    new PrimeNumberCounter(startNumber, startNumber + threadLoad, primes).start();
                } else {
                    if(i == threadPoolSize - 1){
                        new PrimeNumberCounter(startNumber + threadLoad * i, endNumber, primes).start();
                    } else {
                        new PrimeNumberCounter(startNumber + threadLoad * i, startNumber + threadLoad * (i + 1), primes).start();
                    }
                }
            }
        }
    }
}
