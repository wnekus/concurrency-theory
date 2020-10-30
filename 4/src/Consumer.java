public class Consumer extends Thread {
    private Buffer buffer;
    private int id;
    private ChartDrawer chart;
    private long startTime;
    private long estimatedTime;

    public Consumer(Buffer buffer, ChartDrawer chart, int id){
        this.buffer = buffer;
        this.chart = chart;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i=0; i<1000; i++){
            int consumptions = Main.random();

            startTime = System.nanoTime();
            if(buffer.get(consumptions, id)) {
                estimatedTime = System.nanoTime() - startTime;
                chart.uploadData(new Time(Worker.CONSUMER, (double)estimatedTime/1000000, consumptions));
            }
        }
        buffer.decrementConsumers();
    }
}
