package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁,总是按照时间顺序，先到先得
 * 公平锁内部需要维护一个有序队列,故效率低下,实现成本高
 */
public class FairLock implements Runnable{
    public static ReentrantLock fairLock = new ReentrantLock(true);//公平锁
    @Override
    public void run() {
        while (true){
            try{
                fairLock.tryLock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }catch (Exception e){

            }finally {
                fairLock.unlock();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread thread1 = new Thread(fairLock,"lock1");

        Thread thread2 = new Thread(fairLock,"lock2");
        thread1.start();
        Thread.sleep(3000);
        thread2.start();

    }
}
