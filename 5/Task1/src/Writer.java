import BySemaphore.Library;

public class Writer extends Thread {
    private Library library;

    public Writer(Library library){
        this.library = library;
    }

    @Override
    public void start() {
        for(int i = 0; i < 100; i++){
            library.write();
        }
    }
}
