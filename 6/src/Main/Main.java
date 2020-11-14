package Main;

import Waiter.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class Main {
    private final static int PHILOSOPHERS = 10;
    private final static int EATING_TIMES = 300;

    public static void main(String[] args) {

        Fork[] forks = Stream.generate(Fork::new)
                .limit(PHILOSOPHERS)
                .toArray(Fork[]::new);
        Lock lock = new ReentrantLock();
        Waiter waiter = new Waiter();

        ExecutorService executor = Executors.newFixedThreadPool(PHILOSOPHERS);
        try {
            for(int i = 0; i<PHILOSOPHERS; i++){
                executor.execute(new Philosopher(forks[i], forks[(i+1)%PHILOSOPHERS], EATING_TIMES, waiter));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        executor.shutdown();
    }
}
