package Threads;

import java.util.LinkedList;

public class Buffer {
    LinkedList<Integer> buffer;
    int maxSize;

    public Buffer(int maxSize){
        buffer = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public synchronized void put(int i) {
        while(buffer.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.add(i);
        notify();
    }

    public synchronized int get() {
        while(buffer.size() == 0){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        int retVal = buffer.removeFirst();
        notify();
        return retVal;
    }
}