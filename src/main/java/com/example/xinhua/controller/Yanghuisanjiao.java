package com.example.xinhua.controller;
public class Yanghuisanjiao {
    //    杨辉三角
//    public static void main(String[] args) {
//        print(10);
//        System.out.println("-----------------------------------------------------");
//        printjiyi(10);
//    }

    /**
     * @param i 行坐标
     * @param j 列坐标
     * @return
     */
    public static int sanjiao(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return sanjiao(i - 1, j - 1) + sanjiao(i - 1, j);
    }

    // 普通
    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", sanjiao(i, j));
            }
            System.out.println();
        }
    }


    // 记忆法优化
    public static void printjiyi(int n) {

        int[][] ints = new int[n][];
        for (int i = 0; i < n; i++) {
            ints[i] = new int[i + 1];
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", sanjiaojiyi(ints, i, j));
            }
            System.out.println();
        }
    }

    public static int sanjiaojiyi(int[][] ints, int i, int j) {
        //计算过就不走了
        if (ints[i][j] > 0) {
            return ints[i][j];
        }
        if (j == 0 || i == j) {
            ints[i][j] = 1;
            return 1;
        }
        ints[i][j] = sanjiaojiyi(ints, i - 1, j - 1) + sanjiaojiyi(ints, i - 1, j);
        return ints[i][j];
    }

    public static void printSpace(int n, int m) {
        int num = (n - 1 - m) * 2;
        for (int i = 0; i < num; i++) {
            System.out.print(" ");
        }
    }
}
