package com.threadinfo.multithread.demo3;

import lombok.SneakyThrows;

public class ThreadSusPendAndResume {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("thread1");
    static ChangeObjectThread t2 = new ChangeObjectThread("thread2");

    public static class ChangeObjectThread extends Thread{
        public  ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                //挂起
                Thread.currentThread().suspend();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();

    }
}
