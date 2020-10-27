package Threads;

public class Buffer {
    Integer[] buffer;

    public Buffer(int bufferSize){
        this.buffer = new Integer[bufferSize];
    }

    public void put(int position) {
        buffer[position] = 0;
    }

    public void change(int position){
        buffer[position]+=1;
    }

    public synchronized int get(int position) {
       return buffer[position];
    }
}