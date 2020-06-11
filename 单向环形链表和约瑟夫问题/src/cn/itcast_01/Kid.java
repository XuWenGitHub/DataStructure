package cn.itcast_01;

public class Kid {
    private int no; //编号
    private Kid next;   //指向下一个节点，默认null

    public Kid(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Kid getNext() {
        return next;
    }

    public void setNext(Kid next) {
        this.next = next;
    }
}
