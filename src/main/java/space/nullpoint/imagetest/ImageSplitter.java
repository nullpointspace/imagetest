package space.nullpoint.imagetest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageSplitter {

    private final int width;
    private final int height;

    private BufferedImage image1;
    private BufferedImage image2;

    private PatternMode[] modes;
    private boolean[][] binaryMap;
    private int[][] patternMap;

    /* ----- Constructors ----- */

    public ImageSplitter(BufferedImage image) {
        this(image, PatternMode.DIAGONAL);
    }

    public ImageSplitter(BufferedImage image, PatternMode... modes) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.modes = modes;
        binaryMap = new boolean[width][height];
        patternMap = new int[width][height];
        createImageBinaryMap(image);
        createImagesPatternMap();
    }

    /* ----- Public methods ----- */

    public void updateFinalImages() {
        createImagesPatternMap();
        createImages();
    }

    public void updateFinalImages(PatternMode... modes) {
        this.modes = modes;
        updateFinalImages();
    }

    public BufferedImage getImage1() {
        if (image1 == null) createImages();
        return image1;
    }

    public BufferedImage getImage2() {
        if (image2 == null) createImages();
        return image2;
    }

    /* ----- Private methods ----- */

    private void createImageBinaryMap(BufferedImage originImage) {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                binaryMap[w][h] = originImage.getRGB(w, h) == Color.BLACK.getRGB();
            }
        }
    }

    private void createImagesPatternMap() {
        int countVariants = modes.length * 2;
        Random random = new Random();

        for (int i = 0; i < width; i++) {
            patternMap[i] = random.ints(height, 0, countVariants).toArray();
        }
    }

    private void createImages() {
        Image[] patterns = ImagePatterns.getPatterns(modes);
        image1 = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_BYTE_BINARY);
        image2 = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D im1Graphic = image1.createGraphics();
        Graphics2D im2Graphic = image2.createGraphics();


        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Image pattern = patterns[patternMap[w][h]];
                im1Graphic.drawImage(pattern, w * 2, h * 2, null);

                if (binaryMap[w][h]) {
                    int patternNumber = patternMap[w][h];
                    if ((patternNumber & 1) == 0)
                        patternNumber++;
                    else
                        patternNumber--;
                    pattern = patterns[patternNumber];
                }
                im2Graphic.drawImage(pattern, w * 2, h * 2, null);
            }
        }
    }
}
