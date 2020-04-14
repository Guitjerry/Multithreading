package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

/**
 * 可以在线程任意位置让线程阻塞
 * 1.弥补了由于resume()在前发生,导致线程无法执行的情况
 * 2.与Object.wait(需要获得对象的锁,只能在同步块使用)相比,它不需要先获得某个对象的锁
 * 3.支持中断影响,不会抛出异常，它只会默默返回可以从interrupt等方法获得中断标记
 *
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName()+" 被中断了");
                }
            }
            System.out.println(getName()+" 执行结束");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        t1.start();
        Thread.sleep(100);
        t2.start();
//        LockSupport.unpark(t1);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
