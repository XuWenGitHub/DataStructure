package cn.itcast_03;

public class Calculator {
    public static void main(String[] args) {
        String expreesion ="7*2+10+10*6-4";
        ArrayStack numStack = new ArrayStack(20);   //存放数字
        ArrayStack operStack = new ArrayStack(20);  //存放字符
        int index=0;    //扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int result=0;
        String keepNum="";
        char ch=' ';    //来保存扫描得到的字符
        while (true){
            ch=expreesion.substring(index,index+1).charAt(0);   //取出第一个字符
            if(operStack.isOper(ch)){
                //如果是字符,先判断是否为空
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else{
                    //如果不为空，比较当前字符和栈顶字符的优先级
                    if(operStack.priority(ch)<=operStack.priority(operStack.seek())){
                        //如果当前运算符优先级小于或等于栈顶运算符优先级，那么数栈出栈2个，运算符栈顶的运算符出来运算
                        //运算后的结果如何又入栈到数栈，最后再将当前运算符入栈
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        result=numStack.cal(num1,num2,oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }else{
                        //如果当前运算符优先级大于栈顶运算符优先级，那么直接入栈
                        operStack.push(ch);
                    }
                }
            }else {
                keepNum+=ch;//先保存到keepNum字符串中，然后看一下下一位ch还是不是一个数字？
                //先确保ch不是最后一个，不然看下一位ch就越界了
                if(index==expreesion.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if(operStack.isOper(expreesion.substring(index+1,index+2).charAt(0))){
                        //如果下一位是字符，则当前keepNum入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum=""; //刷新keepNum
                    }
                }

            }
            index++;
            if(index==expreesion.length()){
                break;
            }
        }

        while(true) {
            //表达式完毕后,将栈里面的按照规律运算，当运算符栈空break
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            result=numStack.cal(num1,num2,oper);
            numStack.push(result);
        }
        System.out.printf("表达式：%s=%d", expreesion, numStack.pop());
    }
}
