package com.threadinfo.multithread.demo6;

/**
 * 线程组
 * 为了有效对这些线程进行阻止管理，通常情况下是创建一个线程组，然后再将部分线程归属到该组中，以此来对零散的线程对象进行有效的管理
 */
public class ThreadGroupName implements Runnable {
    public static void main(String[] args) {
       ThreadGroup threadGroup = new ThreadGroup("myThreadGroup");
       Thread thread1 = new Thread(threadGroup,new ThreadGroupName(),"thread1");
       Thread thread2 = new Thread(threadGroup,new ThreadGroupName(),"thread2");
       thread1.start();
       thread2.start();
       System.out.println(threadGroup.activeCount());
        threadGroup.list();

    }
    @Override
    public void run() {
        String theardGroupAndName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
        while (true){
            System.out.println("i am:"+theardGroupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
