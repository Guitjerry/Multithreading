package com.threadinfo.multithread.demo4;

import lombok.SneakyThrows;

/**
 * join表示一个线程要加入另外一个线程，那么最好的方法就是当前线程等他一起走
 * 源码关键区域,就是wait等待线程
 * while (isAlive()) {
 *                 long delay = millis - now;
 *                 if (delay <= 0) {
 *                     break;
 *                 }
 *                 wait(delay);
 *                 now = System.currentTimeMillis() - base;
 *             }
 */
public class ThreadJoin {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<100000;i++);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        AddThread addThread = new AddThread();
        addThread.start();
        System.out.println(i);//0
        addThread.join();
        System.out.println(i);//100000
    }
}
