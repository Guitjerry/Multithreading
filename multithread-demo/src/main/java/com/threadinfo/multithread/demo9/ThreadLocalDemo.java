package com.threadinfo.multithread.demo9;

public class ThreadLocalDemo {
    ThreadLocal<String> t1 = new ThreadLocal<>();
    private String content;

    public String getContent() {
        return t1.get();
    }

    public void setContent(String content) {
        t1.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i <5 ; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName()+" 的数据");
                    System.out.println(Thread.currentThread().getName()+"----------"+demo.getContent());
                }
            });
           thread.setName("Thread"+i);
            thread.start();
        }
    }
}
