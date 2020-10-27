package Threads;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
    private Buffer buffer;
    private int bufferSize;
    private Semaphore nextWorkerSemaphore;

    public Producer(Buffer buffer, int bufferSize, Semaphore nextWorkerSemaphore){
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.nextWorkerSemaphore = nextWorkerSemaphore;
    }

    @Override
    public void run() {
        for(int i = 0; i < bufferSize; ++i) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.put(i);
            nextWorkerSemaphore.release();
            System.out.println("Producer put 0 in position " + i);
        }
    }
}