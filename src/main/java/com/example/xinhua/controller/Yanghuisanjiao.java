package com.xinhua.user.controller;

// 杨辉三角
public class Yanghuisanjiao {


    public static int sanjiao(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return sanjiao(i - 1, j - 1) + sanjiao(i - 1, j);
    }


//    public static void main(String[] args) {
//        System.out.println(sanjiao(4,2));
//    }
}
