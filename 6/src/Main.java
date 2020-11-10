import Symmetrcial.Fork;
import Symmetrcial.Philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Fork[] forks = {new Fork(), new Fork(), new Fork(), new Fork(), new Fork()};

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            for(int i = 0; i<5; i++){
                executor.execute(new Philosopher(forks[i], forks[(i+1)%5]));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        executor.shutdown();

        long estimatedTime = System.nanoTime() - startTime;
    }
}
