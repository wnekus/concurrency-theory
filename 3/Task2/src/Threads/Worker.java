package Threads;

import java.util.concurrent.Semaphore;

public class Worker extends Thread {
    private Buffer buffer;
    private int bufferSize;
    int workerNumber;
    private Semaphore mySemaphore;
    private Semaphore nextWorkerSemaphore;

    public Worker(Buffer buffer, int bufferSize, int workerNumber, Semaphore mySemaphore, Semaphore nextWorkerSemaphore){
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.workerNumber = workerNumber;
        this.mySemaphore = mySemaphore;
        this.nextWorkerSemaphore = nextWorkerSemaphore;
    }

    @Override
    public void run(){
        for(int i = 0; i < bufferSize; ++i) {
            try {
                mySemaphore.acquire();
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.change(i);
            System.out.println("Worker " + workerNumber + " add 1 in position " + i);
            nextWorkerSemaphore.release();
        }
    }
}
