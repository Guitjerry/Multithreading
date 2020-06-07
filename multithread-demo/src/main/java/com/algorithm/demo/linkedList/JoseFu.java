package com.algorithm.demo.linkedList;

/**
 * 约瑟夫问题
 */
public class JoseFu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点
    private Boy first = null;

    //添加小孩节点
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = first;
        for(int i=1; i<=nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;//让curboy指向第一个小孩，以后只能操作curboy
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    //出圈

    /**
     *
     * @param startNo //表示从第几个位置开始
     * @param countNum // 表示数几下
     * @param nums //表示总共多少个
     */
    public void countBoy(int startNo, int countNum,int nums){
        if(first == null || startNo<1 || startNo>nums){
            System.out.println("参数不正确");
        }
        Boy helper = first;
        //helper指向终点节点
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for(int i = 0; i <startNo-1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时，让first和helper指针同时移动m-1次
        //这是一个循环操作，直到圈中只有一个数
        while (true){
            if(helper == first){
                break;
            }
            //让helper和first移动countNum-1次
            for(int i = 0; i <countNum-1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈 \n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中小孩为%d \n",first.getNo());
    }
    public void showBoy(){
        if(first == null){
            System.out.println("没有任何节点");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}