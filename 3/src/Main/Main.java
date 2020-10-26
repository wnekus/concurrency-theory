package Main;

import Semaphore.BufferWithSemaphore;
import Semaphore.Consumer;
import Semaphore.Producer;
import Semaphore.Semaphore;
import Threads.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BufferWithSemaphore buffer = new BufferWithSemaphore();
        Semaphore semaphore = new Semaphore();
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for (int i=0; i < 1; i++){
                executor.execute(new Producer(buffer, semaphore, 100, i));
            }
            for (int i=0; i < 1; i++){
                executor.execute(new Consumer(buffer, semaphore, 100, i));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
    }
}