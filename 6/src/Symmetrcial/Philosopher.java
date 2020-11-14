package Symmetrcial;

import Main.AbstractPhilosopher;
import Main.Fork;

public class Philosopher extends AbstractPhilosopher implements Runnable {
    private int counter = 0;

    public Philosopher(Fork leftFork, Fork rightFork){
        super(leftFork, rightFork);
    }

    @Override
    public void run() {
        while (true) {
            think();
            takeForks();
            eat();
            putDownForks();
            counter++;
            if (counter % 100 == 0) {
                System.out.println("Philosopher " + Thread.currentThread() + "ate " + counter + " times");
            }
        }
    }
}
