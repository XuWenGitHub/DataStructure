package cn.itcast_02;

/*
用数组先模拟一个栈
 */
public class ArrayStack {
    private int maxSize;    //栈的大小
    private int[] stack;    //数组模拟栈
    private int top=-1;     //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }
    //添加一个返回当前栈顶的值，但是不是出栈
    public int peek(){
        return stack[top];
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.printf("栈空，%d入栈失败",value);
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，出栈失败");
        }
        int value=stack[top];
        top--;
        return value;
    }
    //显示栈中所有的元素
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈空，没有数据~~");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.printf("%d\t",stack[i]);
        }
        System.out.println();
    }
    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;  //假定目前的表达式只有+，-，*，/
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='/'||val=='*';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int result=0;   //用于存放计算的结果
        switch (oper){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num2-num1;   //注意顺序
                break;
            case '*':
                result=num1*num2;
                break;
            case '/':
                result=num2/num1;   //注意顺序
                break;
        }
        return result;
    }
}
