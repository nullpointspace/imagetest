package space.nullpoint.imagetest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("1.bmp"));
        ImageSplitter splitter = new ImageSplitter(image, PatternMode.DIAGONAL, PatternMode.VERTICAL, PatternMode.HORIZONTAL, PatternMode.DIAGONAL);

        File file1 = new File("out1.png");
        File file2 = new File("out2.png");
        ImageIO.write(splitter.getImage1(), "png", file1);
        ImageIO.write(splitter.getImage2(), "png", file2);
    }
}
