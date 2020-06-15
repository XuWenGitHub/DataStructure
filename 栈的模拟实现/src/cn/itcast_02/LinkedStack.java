package cn.itcast_02;

import java.util.Stack;

/*
用带头结点单链表模拟栈
用链表模拟栈，栈可以无限大
定义一个top节点，让其一直指向栈顶，也让其一直指向链表的最后一个有效节点，最开始指向头结点
栈满条件：如果top节点==head节点，那么栈满
入栈：定义一个新的节点，把传过来的value包装成一个新节点
     然后让top.next=新节点
     再让top=top.next //保证top节点，指向栈顶，也指向链表的最后一个有效节点
出栈：先判断栈是否为空，如果为空，输出一句话，如果return
     定义一个辅助节点，让寻找top的上一个节点,if(temp.next==top){}
        找到后，先保留top的value，让temp.next指向null，再让top=temp，保证top还是链表最后一个有效节点，最后return value
        如果没有找到，temp后移
显示栈的中元素：
    在方法里定义一个Stack栈，然后把遍历链表，从第一个有效节点开始，push压入栈
    最后再用Stack的size()>0作为条件判定，满足，就pop出栈
    因为栈是先进后出，就实现了，链表的逆序遍历
 */
public class LinkedStack {
    private Node head = new Node(0);
    private Node top = head;    //指向栈顶,开始指向头结点

    //判断栈是否为空
    public boolean isEmpty(){
        return top==head;
    }

    //入栈
    public void push(int value){
        Node newNode = new Node(value);
        top.setNext(newNode);
        top=top.getNext();  //top后移
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        Node temp = head;   //辅助指针，遍历到栈顶的上一个节点
        while(true){
            if(temp.getNext()==top){
                int value=top.getValue();   //保存栈顶数据
                temp.setNext(null); //栈顶的上一个节点指向空
                top=temp;   //让top前移，保持top继续指向链表的最后一个有效节点
                return value;   //返回栈顶数据
            }
            temp=temp.getNext();    //后移
        }
    }

    //显示栈的数据(逆序输出链表),利用栈就可以实现
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head.getNext();  //指向第一个有效节点
        while(cur!=null){
            stack.push(cur);
            cur=cur.getNext();  //后移
        }
        while(stack.size()>0){
            System.out.println(stack.pop().getValue());
        }
    }
}
