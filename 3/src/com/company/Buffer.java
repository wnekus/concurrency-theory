package com.company;

import java.util.LinkedList;

class Buffer {
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
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        buffer.add(i);
        notify();
    }

    public synchronized int get() {
        try{
            while(buffer.size() == 0){
                wait();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        int retVal = buffer.removeFirst();
        notify();
        return retVal;
    }
}