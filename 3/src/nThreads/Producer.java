package nThreads;

import Threads.Buffer;

public class Producer extends Thread {
    private Buffer buf;
    private int numberOfProductions;
    private int producerNumber;

    public Producer(Buffer buf, int numberOfProductions, int producerNumber){
        this.buf = buf;
        this.numberOfProductions = numberOfProductions;
        this.producerNumber = producerNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfProductions; ++i) {
            buf.put(i);
            System.out.println("Producer " + producerNumber + " put " + i);
        }
    }
}