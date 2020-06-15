package cn.itcast_02;

public class Node {
    private int value;  //数据域
    private Node next;  //指针域

    //构造器
    public Node(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
