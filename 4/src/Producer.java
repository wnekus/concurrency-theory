public class Producer extends Thread {
    private Buffer buffer;
    private int id;

    public Producer(Buffer buffer, int id){
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i=0; i<100; i++){
            int productions = Main.random();
            buffer.put(productions, id);
            System.out.println("Producer " + id + " put " + productions);
        }
        buffer.decrementProducers();
    }
}
