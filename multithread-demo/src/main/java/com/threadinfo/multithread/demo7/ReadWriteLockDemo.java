package com.threadinfo.multithread.demo7;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写锁,读写锁允许多个线程同时读,在查询需求多的多线程情况下能大大提升效率
 *
 */
public class ReadWriteLockDemo{
    public static ReentrantLock lock = new ReentrantLock();//重入锁
    public static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();//读写锁
    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();
    private int value;
    public Object handleRead(Lock lock)throws InterruptedException{
        lock.lock();//读操作
        try{
            Thread.sleep(1000);

        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        return  value;
    }
    public void handleWrite(Lock lock,int index)throws InterruptedException{
        lock.lock();//写操作
        try{
            Thread.sleep(1000);
            value = index;
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final  ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleRead(readLock);
//                    readWriteLockDemo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleWrite(writeLock,new Random().nextInt());
//                    readWriteLockDemo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i=0;i<20;i++){
            new Thread(readRunnable).start();
        }

        for(int i=0;i<2;i++){
            new Thread(writeRunnable).start();
        }
    }
}
