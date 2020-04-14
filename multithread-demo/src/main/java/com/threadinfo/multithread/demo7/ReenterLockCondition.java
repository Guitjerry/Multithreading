package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition接口
 * await 会使当前线程等待,并且释放当前锁,当其他线程使用signal()或者signaleAll()时
 * 线程会重新获得锁继续执行
 * 重入锁和condition在ArrayBlockingQueue被广泛使用
 */
public class ReenterLockCondition implements Runnable {
    public  static  ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();
    @Override
    public void run() {
        try {
            reentrantLock.tryLock();
            condition.await();
            System.out.println("Theard is going on");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);
        t1.start();
        Thread.sleep(1000);
        //通知继续执行
        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();
    }
}
