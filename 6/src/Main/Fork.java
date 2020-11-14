package Main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    Lock lock = new ReentrantLock();

    public void take() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }

    public Lock getLock() {
        return lock;
    }
}