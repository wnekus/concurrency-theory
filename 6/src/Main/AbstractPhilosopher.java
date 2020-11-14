package Main;

public abstract class AbstractPhilosopher {
    public static final long EATING_TIME = 10;
    public static final long THINKING_TIME = 10;
    public Fork leftFork;
    public Fork rightFork;

    public AbstractPhilosopher(Fork leftFork, Fork rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
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
