package com.algorithm.demo.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
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
 * ，模拟环形队列
 */
class CircleArrayQueue{
    private int rear;//指向队列最后一个元素的后一个位置
    private int front;//指向队列第一个元素
    private int[] array;
    private int maxSize;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull(){

        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能继续添加");
            return;
        }
        //直接将数据加入
        array[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保留的变量返回
        int value = array[front];
        front = (front+1)%maxSize;
        return value;
    }
    public void showQueue(){
        if(isEmpty()){
            return;
        }
        //从front开始遍历，遍历多少个元素
       for(int i=front;i<front+size();i++){
           System.out.println(array[i%maxSize]);
       }
    }
    //求出当前队列有效数据的个数
    public int size(){
        //rear = 2
        //front =1
        //maxSize = 3s
        return (rear + maxSize - front) % maxSize;
    }
    public int headQueue(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        return array[front];
    }
}
