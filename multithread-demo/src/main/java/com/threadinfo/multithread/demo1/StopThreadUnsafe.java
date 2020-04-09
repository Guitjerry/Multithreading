package com.threadinfo.multithread.demo1;

/**
 * 不要用stop来停止一个线程
 */
public class StopThreadUnsafe {
    public static  User u =new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            id=0;
            name="0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" +id+ "name=" +name+ "]";
        }
    }


    public static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                //赋值
                synchronized (u){
                    int v = (int) System.currentTimeMillis()/1000;
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                    super.run();
                }
                Thread.yield();
            }
        }
    }

    public static class readObjectThred extends Thread{
        @Override
        public void run() {
            while (true){
                //赋值
                synchronized (u){
                    if(u.getId()!=Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new readObjectThred().start();
        while (true){
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
