package Main;

import Threads.Buffer;
import Threads.Consumer;
import Threads.Producer;
import Threads.Worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int bufferSize = 100;
        int workersNumber = 5;

        Semaphore[] semaphore = new Semaphore[workersNumber+1];
        for (int i=0; i<workersNumber+1; i++){
            semaphore[i] = new Semaphore(bufferSize);
            for(int j=0; j<bufferSize; j++){
                semaphore[i].acquire();
            }
        }

        Buffer buffer = new Buffer(bufferSize);
        Worker[] workers = new Worker[workersNumber];
        Producer producer = new Producer(buffer, bufferSize, semaphore[0]);
        Consumer consumer = new Consumer(buffer, bufferSize, semaphore[workersNumber]);

        for(int i=0; i<workersNumber; i++){
            workers[i] = new Worker(buffer, bufferSize, i+1, semaphore[i], semaphore[i+1]);
        }


        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            executor.execute(producer);
            for (int i=0; i < workersNumber; i++){
                executor.execute(workers[i]);
            }
            executor.execute(consumer);
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
    }
}