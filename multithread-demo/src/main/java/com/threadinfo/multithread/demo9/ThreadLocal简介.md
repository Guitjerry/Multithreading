# ThreadLocal简介

## 1. ThreadLocal介绍

### 1.1官方介绍功能点

* 线程并发：在多线程并发的场景下
* 传递数据: 我们可以通过ThreadLocal同一线程,不同组件中传递公共变量
* 线程隔离: 每个线程的变量都是独立的,不会相互影响

### 1.2 基本使用

#### 1.2.1 常用方法

| 方法声明                 | 描述                       |
| ------------------------ | -------------------------- |
| ThreadLocal()            | 创建ThreadLocal对象        |
| public void set(T value) | 设置当前线程绑定的局部变量 |
| public T get()           | 获取当前线程绑定的局部变量 |
| public void remove()     | 移除当前线程绑定的局部变量 |

#### 1.2.2 使用案例

```
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
```

可以看到输出时每个线程的结果都不会乱

Thread0----------Thread0 的数据
Thread1----------Thread1 的数据
Thread3----------Thread3 的数据
Thread2----------Thread2 的数据
Thread4----------Thread4 的数据

### 1.3 ThreadLocal类与synchronized

|        |                                                              |                                                              |
| ------ | ------------------------------------------------------------ | :----------------------------------------------------------- |
| 原理   | 同步机制采用以时间换空间的方式,只提供了一份变量,让不同的线程排队访问 | ThreadLocak采用空间换时间的方式,为每一个线程都提供了一份变量的副本,实现访问的互不干扰 |
| 侧重点 | 多个线程之间访问资源的同步                                   | 多线程中让每个线程之间的数据相互隔离                         |

## 2. 内部结构

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\1.7内部结构.png)

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\1.8内部结构.png)

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\1.8设计的好处.png)

* 因为threadLocal数量远比thread少
* 销毁thread时,内部的ThreadLocal也会随之销毁

### 2.1 threadLocalMap内部结构

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\threadLocalMap内部结构.png)

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\threadLocalMap属性.png)

### 2.2 Entry结构

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\entry结构.png)

## 3.弱引用和内存泄漏

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\弱引用和内存泄漏.png)

### 3.1假设Entry的key使用强引用

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\强引用导致内存泄漏.png)

### 3.2假设Entry的key使用强引用

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\弱引用同样会导致内存泄漏.png)

### 3.3内存泄漏真正原因

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\内存泄漏根本原因.png)

### 3.4为什么要使用弱引用

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\threadLocal\为什么要使弱引用.png)

## 4. hash冲突的解决

