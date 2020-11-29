import java.util.concurrent.Callable;

public class Task implements Callable<Result> {
    private int startingPosition;
    private int width;
    private int height;
    private double zx, zy, cX, cY, tmp;

    public Task(int startingPosition, int width, int height){
        this.startingPosition = startingPosition;
        this.width = width;
        this.height = height;
    }

    @Override
    public Result call() {
        Result result = new Result(startingPosition, width, height);

        for (int y = startingPosition; y < startingPosition + height; y++) {
            for (int x = 0; x <  width; x++) {
                zx = zy = 0;
                cX = (x - 400) / Mandelbrot.ZOOM;
                cY = (y - 300) / Mandelbrot.ZOOM;

                int iter = Mandelbrot.MAX_ITER;

                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                result.setRGB(x, y - startingPosition, iter | (iter << 8));
            }
        }

        return result;
    }
}