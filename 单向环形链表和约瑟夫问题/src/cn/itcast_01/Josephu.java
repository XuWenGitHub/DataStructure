package cn.itcast_01;

public class Josephu {
    public static void main(String[] args) {
        //测试构建环形链表和显示
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        //list.showList();

        //测试一把小孩出圈是否正确
        list.countKid(1,2,5);
    }
}
