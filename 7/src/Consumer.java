public class Consumer extends Thread{
    private IProxy proxy;

    public Consumer(IProxy proxy){
        this.proxy = proxy;
    }

    public void run(){
        while(true){
            Future future = proxy.get();
            if(future.isAvailable()) {
                System.out.println("Consumer " + Thread.currentThread() + " took" + future.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}