public class Consumer extends Thread {
    private Buffer buffer;
    private int id;

    public Consumer(Buffer buffer, int id){
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i=0; i<100; i++){
            int consumptions = Main.random();
            buffer.get(consumptions, id);
            System.out.println("Consumer " + id + " get " + consumptions);
        }
        buffer.decrementConsumers();
    }
}
