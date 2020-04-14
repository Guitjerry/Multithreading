package com.threadinfo.multithread.demo7;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 *作用是控制线程的并发数量。
 * 常用于限制可以访问某些资源的线程数量，例如通过 Semaphore 限流。
 */
public class SemaphoreDemo implements Runnable {
    //信号量,允许多少个线程同时访问资源
    final Semaphore  semaphore = new Semaphore(5);
    @SneakyThrows
    @Override
    public void run() {
        semaphore.acquire();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+":done");
        semaphore.release();
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor
                (20,20,10,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1),new ThreadPoolExecutor.DiscardOldestPolicy());
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for(int i=0;i<20;i++){
            threadPool.submit(semaphoreDemo);
        }
        threadPool.shutdown();
    }

}
