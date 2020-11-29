import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mandelbrot extends JFrame {
    protected static final int MAX_ITER = 5000;
    protected static final double ZOOM = 150;
    private final int THREADS = 8;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<Future<Result>> results = new LinkedList<>();

        for (int y = 0; y < getHeight(); y += getHeight() / THREADS) {
                Task task = new Task(y, getWidth(), getHeight() / THREADS);
                results.add(executorService.submit(task));
        }

        results.stream()
                .forEach(result -> {
                    try {
                        Result imageResult = result.get();
                        g.drawImage(imageResult, 0, imageResult.getStart(), this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        executorService.shutdown();
        long endTime = System.currentTimeMillis() - startTime;

        System.out.println(endTime);
    }

    public static void main(String[] args) {
        new Mandelbrot().setVisible(true);
    }
}