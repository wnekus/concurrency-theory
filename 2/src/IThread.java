class IThread extends Thread {
    private Counter cnt;
    private Semaphore semaphore;

    public IThread(Counter cnt, Semaphore semaphore){
        super();
        this.semaphore = semaphore;
        this.cnt = cnt;
    }

    public void run(){
        for(int i=0;i<100000;i++){
            try {
                semaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt.inc();
            semaphore.V();
        }
    }
}