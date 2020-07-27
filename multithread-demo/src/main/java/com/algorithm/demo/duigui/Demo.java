package com.algorithm.demo.duigui;

public class Demo {
    public static void main(String[] args) {
        test(10);
        int result = factorial(4);
        System.out.println(result);
    }

    public static void test(int n) {
        if(n >2) {
            test(n-1);
        }
        System.out.println("n=" +n);
    }

    public  static int factorial(int n) {
        if(n==1) {
            return n;
        }else {
            return factorial(n-1) * n;
        }

    }
}
