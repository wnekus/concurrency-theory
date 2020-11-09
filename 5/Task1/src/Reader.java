import BySemaphore.Library;

public class Reader extends Thread {
    private Library library;

    public Reader(Library library){
        this.library = library;
    }

    @Override
    public void start(){
        for(int i = 0; i < 100; i++){
            library.read();
        }
    }
}
