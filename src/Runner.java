import threads.ThreadKeeper;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        ThreadKeeper threadKeeper = new ThreadKeeper(6, 0, 655, primes);
        threadKeeper.start();

    }
}
