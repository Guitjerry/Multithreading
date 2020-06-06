package com.threadinfo.multithread.demo5;

import lombok.SneakyThrows;

/**
 * volatile可见性测试
 * java虚拟机模式client与server的区别可查看下面的java虚拟机client与server模式.md文档
 * 在java虚拟机是server模式的情况下,由于系统优化的原因,readThread线程将无法看到主线程的线程,导致线程一直无法退出
 * 使用volatile可以告诉虚拟机这个变量可能需要在不同的线程中修改
 *
 */
public class VolatileVisibilityTest {
    private static boolean ready;
//    private static volatile  boolean readyVolatile;
    private static int number;
    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready);
//            while (!readyVolatile);
            System.out.println(number);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReadThread readThread = new ReadThread();
        readThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number=42;

        ready=true;
//        readyVolatile=true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
