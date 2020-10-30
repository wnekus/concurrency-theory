public class Time {
    private Worker worker;
    private double time;
    private int numberOfGoods;

    public Time(Worker worker, Double time, int numberOfGoods){
        this.worker = worker;
        this.time = time;
        this.numberOfGoods = numberOfGoods;
    }

    public Worker getWorker(){
        return this.worker;
    }

    public double getTime(){
        return this.time;
    }

    public int getNumberOfGoods(){
        return this.numberOfGoods;
    }
}
