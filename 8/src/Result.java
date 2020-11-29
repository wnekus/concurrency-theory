import java.awt.image.BufferedImage;

public class Result extends BufferedImage {
    private final int start;

    public Result(final int start, int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_RGB);
        this.start = start;
    }

    public int getStart() {
        return start;
    }
}