package com.algorithm.demo.linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HearNode2 hearNode1 = new HearNode2(1, "宋江", "及时雨");
        HearNode2 hearNode2 = new HearNode2(2, "卢俊义", "玉麒麟");
        HearNode2 hearNode3 = new HearNode2(3, "吴用", "智多星");
        HearNode2 hearNode4 = new HearNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hearNode1);
        doubleLinkedList.add(hearNode2);
        doubleLinkedList.add(hearNode3);
        doubleLinkedList.add(hearNode4);
        doubleLinkedList.list();
        HearNode2 newHearNode = new HearNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHearNode);
        doubleLinkedList.list();
    }


}
class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动
    private HearNode2 head2 = new HearNode2(0, "", "");
    //添加一个节点到双向链表的最后
    public void add(HearNode2 hearNode) {
        HearNode2 temp = head2;
        //找到最后一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hearNode;
        hearNode.prev = temp;

    }
    public void delete(HearNode2 delHeaderNode){
        if(head2.next == null){
            System.out.println("链表为空");
            return;
        }
        HearNode2 temp = head2;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.No == delHeaderNode.No){
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if(flag){
            temp.prev.next = temp.next;
            if(temp.next!=null){
                temp.next.prev = temp.prev;
            }
        }else{
            System.out.println("要删除的节点不存在");
        }
    }
    public void update(HearNode2 newHeaderNode){
        if(head2.next == null){
            System.out.println("链表为空");
            return;
        }
        HearNode2 temp = head2.next;
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
        if (head2.next == null) {
            System.out.println("链表为空");
            return;
        }
        HearNode2 temp = head2.next;
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
class HearNode2 {
    int No;
    String name;
    String nickName;
    HearNode2 next;//指向下一个节点
    HearNode2 prev;//指向上一个节点

    public HearNode2(int no, String name, String nickName) {
        No = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeadNode:" +"[no:" + No + "]" + "[name:" + name + "]" + "[nickName:" + nickName + "]";
    }
}
