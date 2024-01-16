package com.prokhorov.signallab.image;

import java.awt.image.BufferedImage;

public class Furie {
    public static double[][] getFurie(int M, int N, BufferedImage arr) {
        double[][] res = new double[M][N];
        for (int x = 0; x < M; x ++) {
            for (int y = 0; y < N; y++) {
                res[x][y] = arr.getRGB(x, y)*(Math.pow(Math.E, getStepen(M, N, x, y, x, y)));
            }
        }
        return res;
    }

    public static double[][] getFurie2(int M, int N, BufferedImage arr) {
        double[][] res = new double[M][N];
        for (int x = 0; x < M; x ++) {
            for (int y = 0; y < N; y++) {
                res[x][y] = countFurie(M, N, arr, x, y);
            }
        }
        return res;
    }

    private static double countFurie(int M, int N, BufferedImage arr, int u, int v){
        double a = 0;
        for (int x = 0; x < M; x ++) {
            for (int y = 0; y < N; y++) {
                a += arr.getRGB(x, y)*(Math.pow(Math.E, getStepen(M, N, x, y, u, v)));
            }
        }
        return a;
    }

    private static double getStepen(int M, int N, int x, int y, int u, int v) {
        double stepen = 0;
        float one = (float) (u * x) /M;
        float two = (float) (v * y) / N;
        stepen = -2*Math.PI*(one + two);
        return stepen;
    }
}
