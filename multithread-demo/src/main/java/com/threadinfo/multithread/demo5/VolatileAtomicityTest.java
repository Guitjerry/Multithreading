package com.threadinfo.multithread.demo5;

import lombok.SneakyThrows;

/**
 * volatile原子性
 * volatile对于保证操作的原子性有很大的帮助,但是volatile不能代替锁,无法保证一些复合操作的原子性
 * 此处如果是原子性那i输出必然是100000，结果却总是小于100000
 */
public class VolatileAtomicityTest {
    static volatile int i =0;
    public static class plusTask extends Thread{

        @Override
        public void run() {
            for(int k=0;k<10000;k++){
                i++;
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(new plusTask());
            threads[i].start();
        }
        for(int i=0;i<10;i++){
            threads[i].join();
        }
        System.out.println(i);

    }
}
