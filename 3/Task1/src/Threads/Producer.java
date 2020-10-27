package Threads;

public class Producer extends Thread {
    private Buffer buf;

    public Producer(Buffer buf){
        this.buf = buf;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; ++i) {
            buf.put(i);
            System.out.println("Producer put " + i);
        }
    }
}