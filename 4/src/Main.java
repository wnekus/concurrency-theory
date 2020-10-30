import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static int MAX_BUFFER_SIZE = 16384;
    private static int NUMER_OF_PRODUCERS = 4;
    private static int NUMBER_OF_CONSUMERS = 4;

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(MAX_BUFFER_SIZE, NUMER_OF_PRODUCERS, NUMBER_OF_CONSUMERS);
        ExecutorService executorService = Executors.newFixedThreadPool(NUMER_OF_PRODUCERS + NUMBER_OF_CONSUMERS);

        for(int i=0; i < NUMER_OF_PRODUCERS; i++){
            executorService.submit(new Producer(buffer, i));
        }

        for(int i=0; i<NUMBER_OF_CONSUMERS; i++){
            executorService.submit(new Consumer(buffer, i));
        }

        executorService.shutdown();
    }

    public static int random(){
        return  (int)(Math.random() * ((MAX_BUFFER_SIZE/2 - 1) + 1));
    }
}
