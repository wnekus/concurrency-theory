package BySemaphore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Library {
    private List<Integer> books = new LinkedList<>();
    private Semaphore resource = new Semaphore(1);
    private Semaphore queue = new Semaphore(1);
    private Semaphore numberOfReadingsSemaphore = new Semaphore(1);
    private int numberOfReaders = 0;

    public void write(){
        try{
            queue.acquire();
            resource.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        queue.release();

        books.add((int)(Math.random()));

        resource.release();
    }

    public void read(){
        try{
            queue.acquire();
            numberOfReadingsSemaphore.acquire();
            if(numberOfReaders == 0){
                resource.acquire();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        numberOfReaders++;
        queue.release();
        numberOfReadingsSemaphore.release();

       System.out.println( books.get((int)(Math.random() * books.size() - 1)));


        try{
            numberOfReadingsSemaphore.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        numberOfReaders--;
        if(numberOfReaders == 0){
            resource.release();
        }
        numberOfReadingsSemaphore.release();
    }
}
