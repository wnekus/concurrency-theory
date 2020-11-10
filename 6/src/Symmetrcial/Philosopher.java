package Symmetrcial;

public class Philosopher extends Thread {
    private int counter = 0;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

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

    public void think() {
        System.out.println("Philosopher " + Thread.currentThread() + " is thinking");
    }

    public void takeForks(){
        leftFork.take();
        rightFork.take();
        System.out.println("Philosopher " + Thread.currentThread() + " took forks");
    }

    public void putDownForks(){
        leftFork.putDown();
        rightFork.putDown();
        System.out.println("Philosopher " + Thread.currentThread() + " put down forks");
    }

    public void eat() {
        System.out.println("Philosopher " + Thread.currentThread() + " is eating");
    }
}
