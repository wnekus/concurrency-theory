import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorLazySingleton {

    private static ExecutorLazySingleton executorLazySingleton;
    private AtomicInteger isProcessing = new AtomicInteger(0);

    private ExecutorLazySingleton() {
    }

    public static ExecutorLazySingleton getInstance() {
        if (executorLazySingleton == null) {
            executorLazySingleton = new ExecutorLazySingleton();
            return executorLazySingleton;
        } else
            return executorLazySingleton;
    }

    public boolean RequestOperation() {
        if (isProcessing.compareAndSet(0, 1)) {
            return true;
        } else return false;
    }

    public void setIsProcessing(){
        isProcessing.compareAndSet(1, 0);
    }
}