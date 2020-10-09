public class Race {
    public static void main(String[] args) throws InterruptedException {
        Integer[] values = new Integer[100];

        for(int i=0;i<100;i++) {
            Counter cnt = new Counter(0);

            IThread iThread = new IThread(cnt);
            DThread dThread = new DThread(cnt);

            dThread.start();
            iThread.start();

            dThread.join();
            iThread.join();

            System.out.println("stan=" + cnt.value());

            values[i] = cnt.value();
        }
        ChartDrawer chartDrawer = new ChartDrawer(values);
        chartDrawer.show();
    }
}