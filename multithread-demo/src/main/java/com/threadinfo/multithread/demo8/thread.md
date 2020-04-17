# 线程池
## 核心线程池的处理流程
1. 如果此时线程池中的数量小于 corePoolSize（核心池的大小） ， 即使线程池中的线程都处于空闲状态， 也要创建新的线程来处理被添加的任务（也就是每来一个任务， 就要创建一个线程来执行任务） 。
2. 如果此时线程池中的数量大于等于 corePoolSize， 但是缓冲队列workQueue 未满， 那么任务被放入缓冲队列， 则该任务会等待空闲线程将其取出去执行
3. 如果此时线程池中的数量大于等于 corePoolSize ， 缓 冲 队 列workQueue 满， 并且线程池中的数量小于 maximumPoolSize（线程池最大线程数） ， 建新的线程来处理被添加的任务。
4. 如果 此时 线程 池中 的数量 大 于 等 于 corePoolSize， 缓 冲 队列workQueue 满， 并且线程池中的数量等于 maximumPoolSize， 那么通过RejectedExecutionHandler 所指定的策略(任务拒绝策略)来处理此任务。也就是处理任务的优先级为： 核心线程 corePoolSize、 任务队列workQueue、 最大线程 maximumPoolSize， 如果三者都满了， 使用handler 处理被拒绝的任务。
## 线程池的好处
* 通过重复利用已创建的线程， 减少在创建和销毁线程上所花的时间以及系统资源的开销
* 提高响应速度。 当任务到达时， 任务可以不需要等到线程创建就可以立即行。
* 提高线程的可管理性。 使用线程池可以对线程进行统一的分配和监控。
* 如果不使用线程池， 有可能造成系统创建大量线程而导致消耗完系统内存。
## 内部方法
>newFixedThreadPool

创建一个核心线程个数和最大线程个数都为nThreads的线程池，并且阻塞队列长度为Integer.MAX_VALUE，keeyAliveTime=0说明只要线程个数比核心线程个数多并且当前空闲则回收。
>newSingleThreadExecutor

创建一个核心线程个数和最大线程个数都为1的线程池，并且阻塞队列长度为Integer.MAX_VALUE，keeyAliveTime=0说明只要线程个数比核心线程个数多并且当前空闲则回收。

>newCachedThreadPool

创建一个按需创建线程的线程池，初始线程个数为0，最多线程个数为Integer.MAX_VALUE，并且阻塞队列为同步队列，keeyAliveTime=60说明只要当前线程60s内空闲则回收。这个特殊在于加入到同步队列的任务会被马上被执行，同步队列里面最多只有一个任务，并且存在后马上会拿出执行。

>newSingleThreadScheduledExecutor

创建一个最小线程个数corePoolSize为1，最大为Integer.MAX_VALUE，阻塞队列为DelayedWorkQueue的线程池。

>其中Worker继承AQS和Runnable是具体承载任务的对象，Worker继承了AQS自己实现了简单的不可重入独占锁，其中status=0标示锁未被获取状态也就是未被锁住的状态，state=1标示锁已经被获取的状态也就是锁住的状态。
 DefaultThreadFactory是线程工厂，newThread方法是对线程的一个分组包裹，其中poolNumber是个静态的原子变量，用来统计线程工厂的个数，threadNumber用来记录每个线程工厂创建了多少线程。

## fork/join 全面剖析
>    fork/join大体的执行过程，先把一个大任务分解(fork)成许多个独立的小任务，然后起多线程并行去处理这些小任务。处理完得到结果后再进行合并(join)就得到我们的最终结果。显而易见的这个框架是借助了现代计算机多核的优势并行去处理数据。这看起来好像没有什么特别之处，这个套路很多人都会，并且工作中也会经常运用~。其实fork/join的最特别之处在于它还运用了一种叫work-stealing(工作窃取)的算法，这种算法的设计思路在于把分解出来的小任务放在多个双端队列中，而线程在队列的头和尾部都可获取任务。当有线程把当前负责队列的任务处理完之后，它还可以从那些还没有处理完的队列的尾部窃取任务来处理，这连线程的空余时间也充分利用了！。work-stealing原理图如下：

![](https://images2018.cnblogs.com/blog/905730/201807/905730-20180711145448299-68610441.png)