package cn.itcast_01;

/*
用数组模拟栈
 */
public class ArrayStack {
    private int maxSize;    //栈大小
    private int[] stack;    //数组，数组模拟栈，数据放在该数组中
    private int top=-1; //栈顶

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return (top+1)==maxSize;
    }

    //判断栈空
    public boolean isEmpty(){
        return top==-1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //显示栈的数据,便利时，需要从栈顶开始显示数据
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.println(stack[i]);
        }
    }
}
