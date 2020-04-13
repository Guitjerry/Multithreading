package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;
/**
 * 可重入锁,需要手动显示指定加锁，可重入锁表示这种锁是可以反复进入获取锁的，获取锁多少次对应释放锁也得多少次
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable{
    public  static ReentrantLock lock= new ReentrantLock();
    public  static int i=0;
    @Override
    public void run() {
        for(int j=0;j<100000;j++){
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReenterLock trant1 = new ReenterLock();
        Thread t1 = new Thread(trant1);
        Thread t2 = new Thread(trant1);
        t1.start();;
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
