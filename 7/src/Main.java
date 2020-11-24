public class Main {

    public static void main(String[] args) {
        Scheduler sch = new Scheduler();
        IProxy pr = new Servant();
        Consumer cr = new Consumer(pr);
        Producer pro = new Producer(pr);
        pro.join();
        cr.join();
    }
}
