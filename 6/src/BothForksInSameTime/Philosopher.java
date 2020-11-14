package BothForksInSameTime;

import Main.AbstractPhilosopher;
import Main.Fork;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

public class Philosopher extends AbstractPhilosopher implements Runnable {
    private int eatingCounter;
    private Lock lockTakingForks;

    public Philosopher(Fork leftFork, Fork rightFork, int eatingCounter, Lock lockTakingForks){
        super(leftFork, rightFork);
        this.eatingCounter = eatingCounter;
        this.lockTakingForks = lockTakingForks;
    }

    @Override
    public void run() {
        double[] times = new double[eatingCounter];
        int eatingCounterToDivide = eatingCounter;

        while (eatingCounter != 0) {
            long startTime = System.nanoTime();
            think();
            lockTakingForks.lock();
            takeForks();
            lockTakingForks.unlock();
            eat();
            putDownForks();
            eatingCounter--;
            times[eatingCounter] = (double)(System.nanoTime() - startTime)/1000000;
        }

        System.out.println(Arrays.stream(times).sum()/eatingCounterToDivide);
    }

    @Override
    public void think() {
        try{
            Thread.sleep(THINKING_TIME);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Philosopher " + Thread.currentThread() + " is thinking");
    }

    @Override
    public void eat() {
        try{
            Thread.sleep(EATING_TIME);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Philosopher " + Thread.currentThread() + " is eating");
    }
}
