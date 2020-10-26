package Semaphore;

public class Consumer extends Thread {
    private BufferWithSemaphore buf;
    private Semaphore semaphore;
    private int numberOfConsumptions;
    private int consumerNumber;

    public Consumer(BufferWithSemaphore buf, Semaphore semaphore, int numberOfConsumptions, int consumerNumber){
        this.buf = buf;
        this.semaphore = semaphore;
        this.numberOfConsumptions = numberOfConsumptions;
        this.consumerNumber = consumerNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfConsumptions; ++i) {
            try{
                semaphore.P();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Consumer " + consumerNumber + " get " + buf.get());
            semaphore.V();
        }
    }
}
