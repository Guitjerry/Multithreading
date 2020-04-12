package com.threadinfo.multithread.demo6;

import lombok.SneakyThrows;

/**
 * 守护线程是系统的守护者,在后台默默完成一些系统性的服务,例如垃圾回收线程，JTR线程,如果当前用户线程全部结束,只有守护线程时
 * 这是它所守护的对象已经不存在了(所以程序员有句情话,我愿意一直当你这辈子的守护线程)java虚拟机会自然退出
 */
public class TheardDaemon {
    public static class DaemonT extends Thread{

        @Override
        public void run() {
            while (true){
                System.out.println("我是守护线程");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @SneakyThrows
    public static void main(String[] args) {
        Thread t =new DaemonT();
        t.setDaemon(true);
        t.start();
        Thread.sleep(1000);
    }
}
