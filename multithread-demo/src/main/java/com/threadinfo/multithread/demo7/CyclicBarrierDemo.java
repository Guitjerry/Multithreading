package com.threadinfo.multithread.demo7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * 它的作用就是会让所有线程都等待完成后才会继续下一步行动。
 * 可以用于多线程计算数据，最后合并计算结果的场景。
 * https://www.jianshu.com/p/333fd8faa56e
 */
public class CyclicBarrierDemo {
    static class TaskThread extends Thread{
        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName()+" 到达栅栏A");
                barrier.await();
                System.out.println(getName()+" 冲破栅栏A");
                Thread.sleep(2000);
                barrier.await();
                System.out.println(getName()+" 到达栅栏B");
                barrier.await();
                System.out.println(getName()+" 冲破栅栏B");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"完成最后任务");
            }
        });
        for(int i=0;i<5;i++){
            new TaskThread(cyclicBarrier).start();
        }

    }
}
