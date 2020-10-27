package Semaphore;

import java.util.LinkedList;

public class BufferWithSemaphore {
    LinkedList<Integer> buffer;

    public BufferWithSemaphore(){
        buffer = new LinkedList<>();
    }

    public synchronized void put(int i) {
        buffer.add(i);
    }

    public synchronized int get() {
        int retVal = buffer.removeFirst();
        return retVal;
    }
}
