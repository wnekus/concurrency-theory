package Waiter;

import Main.Fork;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private Lock lock =  new ReentrantLock();

    public synchronized boolean takeForks(Fork leftFork, Fork rightFork) {
        lock.lock();
        if(leftFork.getLock().tryLock()) {
            if(rightFork.getLock().tryLock()) {
                lock.unlock();
                return true;
            }
            leftFork.putDown();
        }
        lock.unlock();
        return false;
    }

    public void returnForks(Fork leftFork, Fork rightFork){
        leftFork.putDown();
        rightFork.putDown();
    }
}
