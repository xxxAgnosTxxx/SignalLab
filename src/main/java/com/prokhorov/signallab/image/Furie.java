package com.prokhorov.signallab.image;

import java.awt.image.BufferedImage;

public class Furie {
    public static void getFurie(int M, int N, BufferedImage arr) {
        double[][] res = new double[M][N];
        for (int x = 0; x < M; x ++) {
            for (int y = 0; y < N; y++) {
                res[x][y] = arr.getRGB(x, y)*(Math.pow(Math.E, getStepen(M, N, x, y, x, y)));
            }
        }
        System.out.println();
    }

    private static double getStepen(int M, int N, int x, int y, int u, int v) {
        double stepen = 0;
        double one = u*x/M;
        double two = v*y/N;
        stepen = 2*Math.PI*(one + two);
        return stepen;
    }
}
