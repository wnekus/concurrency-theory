package nThreads;

import Threads.Buffer;

public class Consumer extends Thread {
    private Buffer buf;
    private int numberOfConsumptions;
    private int consumerNumber;

    public Consumer(Buffer buf, int numberOfConsumptions, int consumerNumber){
        this.buf = buf;
        this.numberOfConsumptions = numberOfConsumptions;
        this.consumerNumber = consumerNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfConsumptions; ++i) {
            System.out.println("Consumer " + consumerNumber + " get " + buf.get());
        }
    }
}