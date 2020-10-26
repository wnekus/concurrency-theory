package Semaphore;

public class Producer extends Thread {
    private BufferWithSemaphore buf;
    private Semaphore semaphore;
    private int numberOfProductions;
    private int producerNumber;

    public Producer(BufferWithSemaphore buf, Semaphore semaphore, int numberOfProductions, int producerNumber){
        this.buf = buf;
        this.semaphore = semaphore;
        this.numberOfProductions = numberOfProductions;
        this.producerNumber = producerNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfProductions; ++i) {
            try{
                semaphore.P();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            buf.put(i);
            System.out.println("Producer " + producerNumber + " put " + i);
            semaphore.V();
        }
    }
}
