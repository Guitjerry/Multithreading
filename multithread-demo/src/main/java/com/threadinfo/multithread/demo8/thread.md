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