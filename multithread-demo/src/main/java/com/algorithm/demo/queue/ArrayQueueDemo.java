package com.algorithm.demo.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner =  new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 推出队列");
            System.out.println("a(add) 添加数据到队列");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 查看对头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value =  scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                   int queue =  arrayQueue.getQueue();
                    System.out.println("获得:"+queue);
                    break;
                case 'h':
                   int head =  arrayQueue.headQueue();
                    System.out.println("获得:"+head);
                    break;
            }

        }
    }

}
/**
 * ，模拟队列
 */
class ArrayQueue{
    private int rear;//指向队列尾部
    private int front;//指向队列头部
    private int[] array;
    private int maxSize;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        rear = -1;
        front = -1;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull(){
        return rear == array.length-1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能继续添加");
            return;
        }
        rear++;
        array[rear] = n;
    }

    public int getQueue(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        front++;
        return array[front];
    }
    public void showQueue(){
        if(isEmpty()){
            return;
        }
        for(int i:array){
            System.out.println(i);
        }
    }
    public int headQueue(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        return array[front+1];
    }
}
