public class CountingSemaphore {
    private Semaphore binarySemaphore1;
    private Semaphore binarySemaphore2;
    private int slots;

    public CountingSemaphore(int slots){
        binarySemaphore1 = new Semaphore();
        binarySemaphore2 = new Semaphore();
        this.slots = slots;
    }

    public void P() throws InterruptedException {
        binarySemaphore2.P();
        binarySemaphore1.P();

        slots--;

        if(slots > 0){
            binarySemaphore2.V();
        }
        binarySemaphore1.V();
    }

    public void V() throws InterruptedException {
        binarySemaphore1.P();

        slots++;

        if(slots == 1){
            binarySemaphore2.V();
        }
        binarySemaphore1.V();
    }
}
