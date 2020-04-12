package com.threadinfo.multithread.demo6;

/**
 * 线程优先级 优先级高的总是会先执行，当这不是绝对的
 */
public class TheardFirst {
    public static class highThread extends Thread{
        static  int count =0;
        @Override
        public void run() {
            while(true){
                synchronized (TheardFirst.class){
                    count++;
                }
                if(count>10000){
                    System.out.println("highThread is complete");
                }
            }
        }
    }
    public static class lowThread extends Thread{
        static  int count =0;
        @Override
        public void run() {
            while(true){
                synchronized (TheardFirst.class){
                    count++;
                }
                if(count>1000000){
                    System.out.println("lowThread is complete");
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread highThread = new TheardFirst.highThread();
        Thread lowThread = new TheardFirst.lowThread();
        highThread.setPriority(Thread.MAX_PRIORITY);
        lowThread.setPriority(Thread.MIN_PRIORITY);
        lowThread.start();
        highThread.start();

    }
}
