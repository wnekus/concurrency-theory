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
        try{
            while(buffer.size() >= maxSize){
                wait();
            }
            buffer.add(i);
            notify();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized int get() {
        try{
            while(buffer.size() == 0){
                wait();
            }
            int retVal = buffer.removeFirst();
            notify();
            return retVal;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return -1;
    }
}