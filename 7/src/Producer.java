public class Producer extends Thread{
    private IProxy proxy;

    public Producer(IProxy proxy){
        this.proxy = proxy;
    }

    public void run(){
        while(true){
            proxy.add(Thread.currentThread());
            System.out.println("Producer " + Thread.currentThread() + " added");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}