package com.prokhorov.signallab;

import com.prokhorov.signallab.sound.SoundHandler;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private final static int[] actions = new int[]{0, 1, 2};
    private final static Scanner scanner = new Scanner(System.in);

    private Console() {
    }

    public static void start() {
        System.out.println("--------------------\nВыберите лабораторную работу: ");
        System.out.println("1 - изменение звуковой дорожки;");
        System.out.println("2 - смешивание цветов;");
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
        switch (action){
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
        }
    }
}