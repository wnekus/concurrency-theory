public class Semaphore {
    private boolean _state;
    private int _waiting;

    public Semaphore() {
        _state = true;
        _waiting = 0;
    }

    public synchronized void P() throws InterruptedException {
        _waiting++;
        while(!_state){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        _state = false;
        _waiting--;
    }

    public synchronized void V() {
        _state = true;
        if(_waiting > 0){
            this.notify();
        }
    }
}
