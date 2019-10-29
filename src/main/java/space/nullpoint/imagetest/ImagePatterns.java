package space.nullpoint.imagetest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImagePatterns {

    private static Map<PatternMode, Image[]> patternMap;

    static {
        patternMap = new HashMap<>();
        patternMap.put(PatternMode.DIAGONAL, getDiagonalPattern());
        patternMap.put(PatternMode.VERTICAL, getVerticalPattern());
        patternMap.put(PatternMode.HORIZONTAL, getHorizontalPattern());
    }

    /* ----- Public methods ----- */

    public static Image[] getPatterns(PatternMode... modes) {
        Image[] patterns = new Image[modes.length * 2];
        for (int i = 0; i < modes.length; i++) {
            Image[] pattern = patternMap.get(modes[i]);
            patterns[i * 2] = pattern[0];
            patterns[i * 2 + 1] = pattern[1];
        }
        return patterns;
    }

    /* ----- Private methods ----- */

    private static Image[] getDiagonalPattern() {
        BufferedImage[] images = getEmptyPatterns();
        images[0].createGraphics().drawLine(0, 0, 1, 1);
        images[1].createGraphics().drawLine(1, 0, 0, 1);
        return images;
    }

    private static Image[] getVerticalPattern() {
        BufferedImage[] images = getEmptyPatterns();
        images[0].createGraphics().drawLine(0, 0, 0, 1);
        images[1].createGraphics().drawLine(1, 0, 1, 1);
        return images;
    }

    private static Image[] getHorizontalPattern() {
        BufferedImage[] images = getEmptyPatterns();
        images[0].createGraphics().drawLine(0, 0, 1, 0);
        images[1].createGraphics().drawLine(0, 1, 1, 1);
        return images;
    }

    private static BufferedImage[] getEmptyPatterns() {
        BufferedImage image1 = new BufferedImage(2, 2, BufferedImage.TYPE_BYTE_BINARY);
        BufferedImage image2 = new BufferedImage(2, 2, BufferedImage.TYPE_BYTE_BINARY);
        return new BufferedImage[]{image1, image2};
    }
}
