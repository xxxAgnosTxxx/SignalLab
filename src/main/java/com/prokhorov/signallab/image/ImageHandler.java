package com.prokhorov.signallab.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {
    private static final File imageFile = new File("src/main/resources/static/Image.jpg");

    public static boolean isCorrect(int code) {
        if (code < 0 || code > 255) {
            System.out.println("Число не попадает под диапазон 0-255:");
            return false;
        }
        return true;
    }

    public static void createColor(int red, int green, int blue) {
        BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < 800; x++){
            for(int y = 0; y < 800; y++){
                Color color = new Color(red, green, blue);
                bi.setRGB(x, y, color.getRGB());
            }
        }
        try {
            ImageIO.write(bi, "jpg", imageFile);
            Desktop.getDesktop().open(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
