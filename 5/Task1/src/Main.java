import BySemaphore.Library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int READERS = 1;
    public static int WRITERS = 1;

    public static void main(String[] args) {
        Library library = new Library();

        long startTime = System.nanoTime();

        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for (int i=0; i < WRITERS; i++){
                executor.execute(new Writer(library));
            }
            for (int i=0; i < READERS; i++){
                executor.execute(new Reader(library));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();

        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Writers = " + WRITERS + ", readers = " + READERS + ", program time = " + (double)estimatedTime/1000000 + " ms");
    }
}
