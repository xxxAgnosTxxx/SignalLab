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
    private static final File imageFile3 = new File("src/main/resources/static/img3.jpg");
    private static final File imageFile6 = new File("src/main/resources/static/image6.jpg");
    private static final File inverse = new File("src/main/resources/static/inverse.jpg");
    private static final File bright = new File("src/main/resources/static/bright.jpg");
    private static final File contrast = new File("src/main/resources/static/contrast.jpg");
    private static final File contur = new File("src/main/resources/static/contur.jpg");
    private static final File furie = new File("src/main/resources/static/furie.jpg");

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

            ImageIO.write(bi, "jpg", inverse);
            Desktop.getDesktop().open(inverse);
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
            ImageIO.write(bi, "jpg", bright);
            Desktop.getDesktop().open(bright);
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
            ImageIO.write(bi, "jpg", contrast);
            Desktop.getDesktop().open(contrast);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getContur() {
        try {
            BufferedImage bi = ImageIO.read(imageFile3);
            int width = bi.getWidth();
            int height = bi.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y + 1 < height; y++) {
                    int rgbL = bi.getRGB(x, y);
                    int rgbR = bi.getRGB(x, y + 1);
                    if (rgbL != rgbR){
                        bi.setRGB(x, y, new Color(255, 255, 255).getRGB());
                        //y+=1;
                    }
                }
            }

            for (int x = 0; x < width; x++) {
                for (int y = height - 1; y - 1 >= 0; y--) {
                    int rgbL = bi.getRGB(x, y);
                    //if(rgbL == new Color(255, 255, 255).getRGB())   continue;
                    int rgbR = bi.getRGB(x, y - 1);
                    if (rgbL != rgbR) bi.setRGB(x, y, new Color(255, 255, 255).getRGB());
                }
            }

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgbL = bi.getRGB(x, y);
                    if (rgbL != new Color(255, 255, 255).getRGB()) bi.setRGB(x, y, new Color(0, 0, 0).getRGB());
                }
            }

            ImageIO.write(bi, "jpg", contur);
            Desktop.getDesktop().open(contur);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getFurie() {
        try {
            BufferedImage bi = ImageIO.read(imageFile6);
            int width = bi.getWidth();
            int height = bi.getHeight();

            Furie.getFurie(width, height, bi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
