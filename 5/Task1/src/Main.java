import BySemaphore.Library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int READERS = 100;
    public static int WRITERS = 1;

    public static void main(String[] args) throws InterruptedException {
        Library library = new Library();


        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(READERS + WRITERS);
        try {
            for (int i = 0; i < WRITERS; i++) {
                executor.execute(new Writer(library));
            }
            for (int i = 0; i < READERS; i++) {
                executor.execute(new Reader(library));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        executor.shutdown();

        long estimatedTime = System.nanoTime() - startTime;
        Thread.sleep(1000);

        System.out.println("Writers = " + WRITERS + ", readers = " + READERS + ", program time = "+ (double)estimatedTime/1000000  + " ms");
    }
}
