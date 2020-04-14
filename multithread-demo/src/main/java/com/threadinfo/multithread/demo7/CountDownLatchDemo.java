package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch用来控制线程等待,多少个线程完毕后才往下执行
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(20);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getName()+"线程准备完毕");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor
                (20,20,10,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1),new ThreadPoolExecutor.DiscardOldestPolicy());
        for(int i=0;i<20;i++){
            threadPool.submit(demo);
        }
        end.await();
        System.out.println("发射火箭");
        threadPool.shutdown();
    }
}
