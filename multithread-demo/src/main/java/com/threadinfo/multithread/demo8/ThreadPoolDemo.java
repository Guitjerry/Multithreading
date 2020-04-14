package com.threadinfo.multithread.demo8;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小线程池(不推荐)
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+":ThreadId"+Thread.currentThread().getName());
            Thread.sleep(2000);
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0;i<10;i++){
            executorService.submit(task);
        }
    }
}
