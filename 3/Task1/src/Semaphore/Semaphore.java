package Semaphore;

public class Semaphore {
    private boolean state;
    private int waiting;

    public Semaphore() {
        state = true;
        waiting = 0;
    }

    public synchronized void P() throws InterruptedException {
        waiting++;
        while(!state){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        state = false;
        waiting--;
    }

    public synchronized void V() {
        state = true;
        if(waiting > 0){
            this.notify();
        }
    }
}
