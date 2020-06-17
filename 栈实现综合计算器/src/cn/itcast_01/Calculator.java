package cn.itcast_01;

/*
用栈模拟计算器
 */
/*
思路分析：
    1.通过一个index值（索引），来遍历我们的表达式
    2.如果我们发现是一个数字，就直接入数栈
    3.如果发现扫描到是一个符号，就分如下情况
    3.1 如果发现当前的符号栈为空，就直接入符号栈
    3.2 如果符号栈有操作符，就进行比较
        如果当前的操作符的优先级小于或者等于栈中的操作符，
        就需要从数栈中pop出两个数，再从符号栈pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
        如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
    4.当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相对应的数和符号，并运行
    5.最后在数栈只有一个数字，就是表达式的结果
 */
public class Calculator {
    public static void main(String[] args) {
        //目前是有一个问题的：数字不能是多位数
        String expreesion="1+2*6-4";//表达式
        ArrayStack numberStack=new ArrayStack(10);
        ArrayStack operStack=new ArrayStack(10);
        int index=0;    //用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int result=0;
        char ch=' ';//将每次扫描得到的char保存到ch中
        while(true){
            //依次得到expreesion的每一个字符
            ch=expreesion.substring(index,index+1).charAt(0);

            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){
                //如果是运算符,判断当前符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较如果当前的操作符的优先级小于或者等于栈中的操作符，
                    //就需要从数栈中pop出两个数，再从符号栈pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numberStack.pop();
                        num2=numberStack.pop();
                        oper=operStack.pop();
                        result=numberStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numberStack.push(result);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else {
                //如果是数，则直接入数栈
                numberStack.push(ch-48);    //'1' => 1
            }

            //让index+1，并判断是否扫描到expreesion最后
            index++;
            if(index==expreesion.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相对应的数和符号，并运行
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }
            num1=numberStack.pop();
            num2=numberStack.pop();
            oper=operStack.pop();
            result=numberStack.cal(num1,num2,oper);
            numberStack.push(result);
        }
        //将数栈的最后数，pop出，就是结果
        System.out.printf("表达式%s=%d",expreesion,numberStack.pop());
    }
}
