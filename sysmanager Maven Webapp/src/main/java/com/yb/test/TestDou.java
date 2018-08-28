package com.yb.test;

public class TestDou {
    public static void main(String[] args) {
        double a=0.1;
        for (int i=0;i<100000000;i++){
            a+=0.1;
        }
        System.out.println(a);
    }
}
