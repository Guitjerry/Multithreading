package com.algorithm.demo.linkedList;

public class SingleLinkedList {
    public static void main(String[] args) {
        HearNode hearNode1 = new HearNode(1, "宋江", "及时雨");
        HearNode hearNode2 = new HearNode(2, "卢俊义", "玉麒麟");
        HearNode hearNode3 = new HearNode(3, "吴用", "智多星");
        HearNode hearNode4 = new HearNode(4, "林冲", "豹子头");
        SingleList singleList = new SingleList();
//        singleList.add(hearNode1);
//        singleList.add(hearNode2);
//        singleList.add(hearNode3);
//        singleList.add(hearNode4);
        singleList.addByOrder(hearNode1);
        singleList.addByOrder(hearNode3);
        singleList.addByOrder(hearNode4);
        singleList.addByOrder(hearNode2);

//        HearNode newHearNode2 = new HearNode(2,"小卢","玉麒麟");
//        HearNode delHearNode2 = new HearNode(1,"宋江","及时雨");
//        singleList.update(newHearNode2);
//        singleList.delete(delHearNode2);
        //显示
       reverseList(singleList.getHead());
        singleList.list();
    }

    public static void reverseList(HearNode head){
        if(head.next == null||head.next.next == null){
            return;
        }
        HearNode cur = head.next;//辅助指针变量
        HearNode next = null;//指向当前节点的下一个节点
        HearNode reverNode = new HearNode(0,"","");
        while (cur!=null){
            next = cur.next;
            cur.next = reverNode.next;
            reverNode.next = cur;
            cur = next;

        }
        head.next = reverNode.next;
    }


}

//定义singleLinked管理我们的英雄
class SingleList {
    //先初始化一个头节点，头节点不要动
    private HearNode head = new HearNode(0, "", "");

    public HearNode getHead() {
        return head;
    }

    public void setHead(HearNode head) {
        this.head = head;
    }

    public void add(HearNode hearNode) {
        HearNode temp = head;
        //找到最后一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hearNode;

    }

    //第二种添加英雄的方式，需要按顺序
    public void addByOrder(HearNode hearNode) {
        //因为是单链表，找到添加位置的前一个节点
        HearNode tmp = head;
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.next.No > hearNode.No) {
                break;
            }else if (tmp.No == hearNode.No){

                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag){
            System.out.println("不能添加重复的节点");
            return;
        }
        hearNode.next = tmp.next;
        tmp.next = hearNode;

    }
    public void delete(HearNode delHeaderNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HearNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.No == delHeaderNode.No){
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的节点不存在");
        }
    }

    public void update(HearNode newHeaderNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HearNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.No == newHeaderNode.No){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeaderNode.name;
            temp.nickName = newHeaderNode.nickName;
        }else{
            System.out.println("没有找到数据");
        }

    }

    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HearNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }

            System.out.println(temp.toString());
            //将temp后移
            temp = temp.next;
        }
    }

}

//定义一个hearNode，每一个为节点对象
class HearNode {
    int No;
    String name;
    String nickName;
    HearNode next;//指向下一个节点

    public HearNode(int no, String name, String nickName) {
        No = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeadNode:" +"[no:" + No + "]" + "[name:" + name + "]" + "[nickName:" + nickName + "]";
    }
}
