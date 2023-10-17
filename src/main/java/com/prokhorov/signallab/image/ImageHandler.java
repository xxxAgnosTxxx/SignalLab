package com.prokhorov.signallab.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImageHandler {
    private static final File imageFile = new File("src/main/resources/static/Image.jpg");
    private static final File imageFile2 = new File("src/main/resources/static/img2.jpg");
    private static final File imageFileSave2 = new File("src/main/resources/static/inverse.jpg");
    private static final File imageFileSave22 = new File("src/main/resources/static/bright.jpg");
    private static final File imageFileSave23 = new File("src/main/resources/static/contrast.jpg");

    public static boolean isCorrect(int code) {
        if (code < 0 || code > 255) {
            System.out.println("Число не попадает под диапазон 0-255:");
            return false;
        }
        return true;
    }

    public static void createColor(int red, int green, int blue) {
        BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 800; x++) {
            for (int y = 0; y < 800; y++) {
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

    public static void inverseColor() {
        try {
            BufferedImage bi = ImageIO.read(imageFile2);
            int width = bi.getWidth();
            int height = bi.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int oldRgb = bi.getRGB(x, y);
                    Color oldColor = new Color(oldRgb);
                    Color newColor = new Color(255 - oldColor.getRed(), 255 - oldColor.getGreen(), 255 - oldColor.getBlue());
                    bi.setRGB(x, y, newColor.getRGB());
                }
            }

            ImageIO.write(bi, "jpg", imageFileSave2);
            Desktop.getDesktop().open(imageFileSave2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeBrightness() {
        try {
            BufferedImage bi = ImageIO.read(imageFile2);
            int width = bi.getWidth();
            int height = bi.getHeight();

            int brightness = 0;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int oldRgb = bi.getRGB(x, y);
                    Color oldColor = new Color(oldRgb);
                    int tmpb = (int) (0.2126 * oldColor.getRed() + 0.7152 * oldColor.getGreen() + 0.0722 * oldColor.getBlue());
                    brightness += tmpb;
                }
            }
            brightness = brightness / (width * height);
            System.out.println("Яркость оригинала: " + brightness);

            System.out.println("Введите желаемый уровень яркости:");
            int lvl = new Scanner(System.in).nextInt();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int oldRgb = bi.getRGB(x, y);
                    Color oldColor = new Color(oldRgb);
                    int tmpb = lvl - brightness;
                    int red = tmpb + oldColor.getRed();
                    red = Math.max(red, 0);
                    red = Math.min(red, 255);
                    int green = tmpb + oldColor.getGreen();
                    green = Math.max(green, 0);
                    green = Math.min(green, 255);
                    int blue = tmpb + oldColor.getBlue();
                    blue = Math.max(blue, 0);
                    blue = Math.min(blue, 255);
                    Color newColor = new Color(red, green, blue);
                    bi.setRGB(x, y, newColor.getRGB());
                }
            }
            ImageIO.write(bi, "jpg", imageFileSave22);
            Desktop.getDesktop().open(imageFileSave22);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeContrast() {
        try {
            BufferedImage bi = ImageIO.read(imageFile2);
            int width = bi.getWidth();
            int height = bi.getHeight();

            int brightness = 0;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int oldRgb = bi.getRGB(x, y);
                    Color oldColor = new Color(oldRgb);
                    int tmpb = (int) (0.2126 * oldColor.getRed() + 0.7152 * oldColor.getGreen() + 0.0722 * oldColor.getBlue());
                    brightness += tmpb;
                }
            }
            brightness = brightness / (width * height);

            System.out.println("Введите коэффициент контрастности:");
            int lvl = new Scanner(System.in).nextInt();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int oldRgb = bi.getRGB(x, y);
                    Color oldColor = new Color(oldRgb);
                    int tmpb = (int) (0.2126 * oldColor.getRed() + 0.7152 * oldColor.getGreen() + 0.0722 * oldColor.getBlue());
                    if (tmpb < brightness) {
                        int red = oldColor.getRed() - lvl;
                        red = Math.max(red, 0);
                        red = Math.min(red, 255);
                        int green = oldColor.getRed() - lvl;
                        green = Math.max(green, 0);
                        green = Math.min(green, 255);
                        int blue = oldColor.getRed() - lvl;
                        blue = Math.max(blue, 0);
                        blue = Math.min(blue, 255);
                        Color newColor = new Color(red, green, blue);
                        bi.setRGB(x, y, newColor.getRGB());
                    } else {
                        int red = lvl + oldColor.getRed();
                        red = Math.max(red, 0);
                        red = Math.min(red, 255);
                        int green = lvl + oldColor.getGreen();
                        green = Math.max(green, 0);
                        green = Math.min(green, 255);
                        int blue = lvl + oldColor.getBlue();
                        blue = Math.max(blue, 0);
                        blue = Math.min(blue, 255);
                        Color newColor = new Color(red, green, blue);
                        bi.setRGB(x, y, newColor.getRGB());
                    }
                }
            }
            ImageIO.write(bi, "jpg", imageFileSave23);
            Desktop.getDesktop().open(imageFileSave23);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
