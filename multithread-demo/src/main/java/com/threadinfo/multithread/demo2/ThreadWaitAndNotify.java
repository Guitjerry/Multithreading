package com.threadinfo.multithread.demo2;

import lombok.SneakyThrows;

/**
 * wait与notify使用
 * 在notify线程通知wait线程继续执行后,wait线程并不能马上执行,必须要等待notify线程释放锁后才能继续执行
 * sleep不会释放任何资源,wait会释放目标对象的锁
 */
public class ThreadWaitAndNotify {
    final static Object objects = new Object();
    public static class waitThread extends Thread{

        @Override
        public void run() {
            synchronized (objects){
                System.out.println("wait线程开始启动"+System.currentTimeMillis());
                try {
                    System.out.println("wait线程开始等待"+System.currentTimeMillis());
                    objects.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait等待完毕"+System.currentTimeMillis());
            }

        }
    }

    public static class notifyThread extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            synchronized (objects){
                System.out.println("notify线程开始启动"+System.currentTimeMillis());
                objects.notify();
                System.out.println("notify唤醒完毕"+System.currentTimeMillis());
                Thread.sleep(2000);
            }

        }
    }

    public static void main(String[] args) {
        new waitThread().start();
        new notifyThread().start();

    }
}
