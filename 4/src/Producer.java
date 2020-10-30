public class Producer extends Thread {
    private Buffer buffer;
    private int id;
    private ChartDrawer chart;
    private long startTime;
    private long estimatedTime;

    public Producer(Buffer buffer, ChartDrawer chart, int id){
        this.buffer = buffer;
        this.chart = chart;
        this.id = id;
    }

    @Override
    public void run(){
        for(int i=0; i<1000; i++){
            int productions = Main.random();

            startTime = System.nanoTime();
            if(buffer.put(productions, id)) {
                estimatedTime = System.nanoTime() - startTime;
                chart.uploadData(new Time(Worker.PRODUCER, (double)estimatedTime/1000000, productions));
            }
        }
        buffer.decrementProducers();
    }
}
