# 锁相关
## ReentrantLock 可重入锁
### 方法
* lock() 获得锁，如果锁被占用则等待
* lockInterruptibly(); 获得锁但优先响应中断
* tryLock() 尝试获取锁,如果成功则返回true，失败返回false.该方法不等待,立即返回
* tryLock(long time,TimeUnit time) 在给定时间内尝试获取锁
* unlock 释放锁
### 实现元素
* 原子状态。原子状态用CAS操作来存储当前锁的状态，判断是否已经被其他线程所拥有
* 等待队列。所有没有请求到的线程会进入到等待队列进行等待,待有线程释放锁后
,系统就能从等待队列里唤醒一个线程,继续工作
* 是阻塞原语park()和unpark(),用来挂起和恢复线程。没有得到锁的线程
将会被挂起

```
public class ReenterLock implements Runnable{
    public  static ReentrantLock lock= new ReentrantLock();
    public  static int i=0;
    @Override
    public void run() {
        for(int j=0;j<100000;j++){
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReenterLock trant1 = new ReenterLock();
        Thread t1 = new Thread(trant1);
        Thread t2 = new Thread(trant1);
        t1.start();;
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
```

## 重入锁的好搭档Condition
>想必大家都知道，Objet类有wait() ，notify()方法;分别是让线程等待和唤醒，那么condition效果是大同小异的，只不过需要和ReentrantLock搭配使用（而wait和notify是和synchronzied搭配使用的）。由此可见，利用condition对象，我们就可以让线程在合适的时间等待，或在特定的某个时间得到通知，继续执行。

<h5>Condition接口有以下几个方法</h5>
* void await() throws InterruptedException; 当前线程等待，同时释放当前锁,会在等待过程中响应中断
* void awaitUninterruptibly(); 当前线程等待,不会在等待过程中响应中断
* void signal();唤醒等待中的一个线程
* void signalAll();唤醒所有等待的线程

```
/**
 * condition接口
 * await 会使当前线程等待,并且释放当前锁,当其他线程使用signal()或者signaleAll()时
 * 线程会重新获得锁继续执行
 * 重入锁和condition在ArrayBlockingQueue被广泛使用
 */
public class ReenterLockCondition implements Runnable {
    public  static  ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();
    @Override
    public void run() {
        try {
            reentrantLock.tryLock();
            condition.await();
            System.out.println("Theard is going on");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);
        t1.start();
        Thread.sleep(1000);
        //通知继续执行
        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();
    }
```

## Semaphore 限制线程数量

## ReadWriteLock读写锁

## 倒计时器CountDownLatch

## CyclicBarrier循环栅栏

## LockSupport线程阻塞工具类
