package cn.itcast_03;
/*
用数组模拟栈
 */
public class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top=-1; //表示栈顶

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }
    //看一下栈顶元素
    public int seek(){
        return stack[top];
    }
    //判断是否为空
    public boolean isEmpty(){
        return top==-1;
    }
    //判断是否为满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //push入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满~~~");
            return;
        }
        top++;
        stack[top]=value;
    }
    //pop出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空~~~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈中元素
    public void showStack(){
        for(int i=top;i>=0;i--){
            System.out.printf("%d\t",stack[i]);
        }
        System.out.println();
    }
    //返回运算符的优先级
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是运算符
    public boolean isOper(char val){
        return val=='*'||val=='/'||val=='-'||val=='+';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int result=0;
        switch(oper){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num2-num1;
                break;
            case '*':
                result=num1*num2;
                break;
            case'/':
                result=num2/num1;
                break;
        }
        return result;
    }
}
