package com.prokhorov.signallab;

import com.prokhorov.signallab.image.ImageHandler;
import com.prokhorov.signallab.sound.SoundHandler;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private final static int[] actions = new int[]{0, 1, 2, 3, 4, 5, 6};
    private final static Scanner scanner = new Scanner(System.in);

    private Console() {
    }

    public static void start() {
        System.out.println("--------------------\nВыберите лабораторную работу: ");
        System.out.println("1 - изменение звуковой дорожки;");
        System.out.println("2 - смешивание цветов;");
        System.out.println("3 - работа с картинкой;");
        System.out.println("4 - контуры картинки;");
        System.out.println("5 - скремблер;");
        System.out.println("6 - разложение Фурье;");
        System.out.println("0 - выход;");
        setAction(tryScan(actions));
    }

    private static int tryScan(int[] actions) {
        try {
            int action = scanner.nextInt();
            if (Arrays.stream(actions)
                    .filter(x -> x == action)
                    .findFirst()
                    .isEmpty()) {
                throw new InputMismatchException();
            }
            scanner.nextLine();
            return action;
        } catch (InputMismatchException e) {
            System.out.println("Некорректный выбор. Выберите лабораторную работу:");
            scanner.nextLine();
            return tryScan(actions);
        }
    }

    private static void setAction(int action) {
        switch (action) {
            case 0:
                System.exit(0);
            case 1:
                System.out.print("Выберите уровень зашумления: ");
                int noise = scanner.nextInt();
                System.out.println("Проигрывается обычная дорожка");
                SoundHandler.play(1);
                System.out.println("Проигрывается зашумленная дорожка");
                SoundHandler.play(noise);
                start();
            case 2:
                System.out.println("Введите числовой код компонента Red (0-255):");
                int red = scanner.nextInt();
                if (!ImageHandler.isCorrect(red)) {
                    setAction(2);
                    start();
                }

                System.out.println("Введите числовой код компонента Green (0-255):");
                int green = scanner.nextInt();
                if (!ImageHandler.isCorrect(green)) {
                    setAction(2);
                    start();
                }

                System.out.println("Введите числовой код компонента Blue (0-255):");
                int blue = scanner.nextInt();
                if (!ImageHandler.isCorrect(blue)) {
                    setAction(2);
                    start();
                }

                ImageHandler.createColor(red, green, blue);
                start();
            case 3:
                System.out.println("1 - сделать инверсию картинки");
                System.out.println("2 - изменить яркость");
                System.out.println("3 - изменить контрастность");
                int actionLab3 = scanner.nextInt();
                if (actionLab3 == 1) {
                    ImageHandler.inverseColor();
                } else if (actionLab3 == 2) {
                    ImageHandler.changeBrightness();
                } else {
                    ImageHandler.changeContrast();
                }
                start();
            case 4:
                ImageHandler.getContur();
                start();
            case 5:
                System.out.println("Проигрывается обычная дорожка");
                SoundHandler.play(1);
                System.out.println("Скремблер");
                SoundHandler.scramble();
                start();
            case 6:
                ImageHandler.getFurie();
                start();
        }
    }
}