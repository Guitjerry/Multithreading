package com.threadinfo.multithread.demo8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 分而治之，先把一个大任务分解(fork)成许多个独立的小任务，
 * 然后起多线程并行去处理这些小任务。处理完得到结果后再进行合并(join)就得到我们的最终结果
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int fixNumber = 1000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum =0;
        //结束-开始超过10000则需要线程分治处理
        if(end-start<=fixNumber){
            for(long i=start;i<=end;i++){
                sum +=i;
            }
        }else{
            //分一百次处理
            long  step = (end-start)/100;
            long pos = start;
            List<CountTask> countTasks = new ArrayList<>();
            for(int i=0;i<100;i++){
                long lastOne = pos+step;
                if(lastOne>end){
                    lastOne=end;
                }

                CountTask countTask = new CountTask(pos,lastOne);
                pos+=step+1;
                countTasks.add(countTask);
                countTask.fork();
            }
            for(CountTask countTask:countTasks){
                sum+=countTask.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool joinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0,200000L);
        ForkJoinTask<Long> result =joinPool.submit(countTask);
        try {
            long res = result.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
