package com.threadinfo.multithread.demo3;

import lombok.SneakyThrows;

public class GoodSuspend {
    public static Object b = new Object();
    public static class changeObjectThread extends  Thread{
        volatile  boolean suspendMe = false;

        //挂起
        public void suspendMe() {
            this.suspendMe = true;
        }

        //恢复
        public void resumeMe(){
            this.suspendMe = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendMe){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (b){
                    System.out.println("in changeObjectThread");
                }
                Thread.yield();
            }

        }
    }
    public static class staticReadObjectThread extends Thread{

        @Override
        public void run() {
            while (true){
                synchronized (b){
                    System.out.print(" in readObjectThread");
                }
                Thread.yield();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        changeObjectThread changeObjectThread = new changeObjectThread();
        staticReadObjectThread readObjectThread = new staticReadObjectThread();
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.suspendMe();
        System.out.print("挂机2秒");
        Thread.sleep(2000);
        System.out.print("唤醒");
        changeObjectThread.resumeMe();
    }
}
