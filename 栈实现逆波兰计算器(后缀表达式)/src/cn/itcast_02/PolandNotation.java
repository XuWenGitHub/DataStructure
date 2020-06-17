package cn.itcast_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先把一个中缀表达式转成一个后缀表达式
        String expression = "14+((2+3)*4)-100";
        ArrayList<String> infixList = toInfixExpressionList(expression);
        System.out.println("中缀表达式："+infixList);
        ArrayList<String> suffixList = parseSuffixExpreesionList(infixList);
        System.out.println("后缀表达式："+suffixList);
        System.out.println(expression+"的结果为："+calculate(suffixList));

    }
    //将一个逆波兰表达式（后缀表达式）传入，算出结果，然后传出
    public static int calculate(ArrayList<String> suffixList){
        Stack<String> stack = new Stack<>();
        for(String s:suffixList){
            if(s.matches("\\d+")){
                //如果是数字，那么直接入栈
                stack.push(s);
            }else {
                //如果是操作符，那么直接从栈中弹出两个数字，然后运算，然后将结果再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result=0;
                if(s.equals("+")){
                    result=num2+num1;
                }else if(s.equals("-")){
                    result=num2-num1;
                }else if(s.equals("*")){
                    result = num2*num1;
                }else if(s.equals("/")){
                    result = num2/num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(Integer.toString(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //这是将中缀表达式list转换成一个后缀表达式list
    public static ArrayList<String> parseSuffixExpreesionList(ArrayList<String> infixList){
        Stack<String> s1 = new Stack<>();
        //本来需要两个栈，一个s1，一个s2，s1用来存字符，s2用来存最后转换成后缀表达式的结果的逆序，
        // 但是s2没有出栈的操作，所有我们用ArrayList来代替，最后ArrayList存放的就是后缀表达式的结果
        ArrayList<String> list = new ArrayList<>();
        for(String item:infixList){
            if(item.matches("\\d+")){
                //如果是数字，就直接加入list里面
                list.add(item);
            }else if(item.equals("(")){
                //如果是左括号，那么直接加入s1中
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，那么从s1中pop出数据加入到list，直到s1中遇到左括号，最后左括号和又括号都要清除
                while(!s1.peek().equals("(")){
                    list.add(s1.pop());
                }
                //最后再把s1中左括号pop出来，消除掉
                s1.pop();
            }else {
                //如果是操作符，就要进行比较操作符的优先级了
                //如果item的优先级，小于或等于s1栈顶操作符的优先级，那么将s1栈顶操作符pop出添加到list里面
                //然后又判断item与下一个s1栈顶操作符的优先级进行比较，直到item的优先级大于栈顶元素优先级，就把item存入s1里面
                while(s1.size()!=0&&Operation.getValue(item)<=Operation.getValue(s1.peek())){
                    list.add(s1.pop());
                }
                s1.add(item);
            }
        }
        //循环结束后，再把s1里面的元素全部添加进list里面
        while(s1.size()!=0){
            list.add(s1.pop());
        }
        return list;
    }

    //这是一个将中缀表达式中每一个数字和字符存进ArrayList集合，方便遍历
    public static ArrayList<String> toInfixExpressionList(String expression){
        ArrayList<String> list = new ArrayList<>();
        int i=0;    //字符串索引
        char ch;    //存字符串中每一个数字和字符
        String keepStr = "";    //用来多位数字拼接
        while(i<expression.length()){
            if((ch=expression.charAt(i))<'0'||(ch=expression.charAt(i))>'9'){
                //如果该字符不是数字是字符的话，那么将其存入list中
                list.add(""+ch);
                i++;
            }else {
                //如果该是数字字符的话,那么要考虑多位数
                while (i<expression.length()&&(ch=expression.charAt(i))>='0'&&(ch=expression.charAt(i))<='9'){
                    keepStr+=ch;
                    i++;
                }
                list.add(keepStr);
                keepStr=""; //重置keepStr
            }
        }
        return list;
    }
}
//这是一个类，里面放的字符的优先级，用数字代替，数字也大，表示优先级越高
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    public static int getValue(String operation){
        int result=0;
        switch(operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
        }
        return result;
    }
}
