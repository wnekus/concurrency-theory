package Threads;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private Buffer buffer;
    private int bufferSize;
    private Semaphore mySemaphore;

    public Consumer(Buffer buffer, int bufferSize, Semaphore mySemaphore){
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.mySemaphore = mySemaphore;
    }

    @Override
    public void run() {
        for(int i = 0; i < bufferSize; ++i) {
            try {
                mySemaphore.acquire();
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer get " + buffer.get(i) + " from position " + i);
        }
    }
}