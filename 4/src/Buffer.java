import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    LinkedList<Integer> buffer;
    private int maxSize;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private AtomicInteger consumersNumber;
    private AtomicInteger producersNumber;

    public Buffer(int maxSize, int producersNumber, int consumersNumber){
        this.buffer = new LinkedList<>();
        this.maxSize = maxSize;
        this.producersNumber = new AtomicInteger(producersNumber);
        this.consumersNumber = new AtomicInteger(consumersNumber);
    }

    public void put(int numberOfProducts, int id){
        lock.lock();
        try{
            while(maxSize - buffer.size() < numberOfProducts){
                if(consumersNumber.get() == 0){
                    return;
                }
                notFull.await();
            }
            for(int i=0; i<numberOfProducts; i++){
                buffer.add(id);
            }
            notEmpty.signalAll();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get(int numberOfConsumptions, int id){
        lock.lock();
        try{
            while(buffer.size() < numberOfConsumptions){
                if(producersNumber.get() == 0){
                    return;
                }
                notEmpty.await();
            }
            for(int i=0; i<numberOfConsumptions; i++){
                buffer.removeFirst();
            }
            notFull.signalAll();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrementProducers() {
        while(true) {
            int existingProducersValue = producersNumber.get();
            int newProducersValue = existingProducersValue - 1;
            if(producersNumber.compareAndSet(existingProducersValue, newProducersValue)) {
                notEmpty.signalAll();
                return;
            }
        }
    }

    public void decrementConsumers() {
        while(true) {
            int existingConsumersValue = consumersNumber.get();
            int newValue = existingConsumersValue - 1;
            if(consumersNumber.compareAndSet(existingConsumersValue, newValue)) {
                notFull.signalAll();
                return;
            }
        }
    }
}
