package cn.itcast_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //测试中缀表达式转成后缀表达式
        //完成将一个中缀表达式转成后缀表达式的功能
        //A:"1+((2+3)*4)-5" => 1 2 3 + 4 * + 5 -
        //B:因为直接对一个字符串进行操作，不方便，先将字符串转成中缀表达式对应的List
        //  即："1+((2+3)*4)-5" => [1,+,(,(,2,+,3,),*,4,),-,5]
        //C:将得到的中缀表达式对应的List => 后缀表达式对应的List
        //  即："1+((2+3)*4)-5" => 1 2 3 + 4 * + 5 -
        String expression = "1+((2+3)*4)-5";
        List<String> list1 = toInfixExpressionList(expression); //中缀表达式对应的List
        System.out.println("中缀表达式对应的List："+list1);
        List<String> list2=parseSuffixExpreesionList(list1);    //后缀表达式对应的List
        System.out.println("后缀表达式对应的List："+list2);

        //测试中缀表达式转成后缀表达式再计算出的结果
        System.out.printf("expreesion=%d\n",calculate(list2));

        //测试后缀表达式算出的结果
//        //先定义一个逆波兰表达式
//        //数字和符号用空格隔开
//        String suffixExpression="3 4 + 5 * 6 -";    //中缀表达式：(3+4)*5-6
//        //思路
//        //1.先将"3 4 + 5 * 6 -"放到ArrayList中
//        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println(list);
//
//        System.out.println("计算的结果是="+calculate(list));
    }

    //"1+((2+3)*4)-5" => 1 2 3 + 4 * + 5 -
    public static List<String> parseSuffixExpreesionList(List<String> list){
        //定义两个栈
        Stack<String> s1 = new Stack<>();   //符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们不用Stack<String>直接使用List<String> s2
        //Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for(String item:list){
            //如果是一个数，加入到s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，一直弹出s1栈顶的运算符，并并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();   //将"("弹出s1栈，消除小括号
            }else {
                //当item的优先级小于或者等于栈顶运算符的优先级，将s1栈顶的运算符弹出并压入到s2中，再去去和栈顶元素比较
                while(s1.size()!=0&&Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还如要将item压入到栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出加入到s2中
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;  //因为存放到List，因此按顺序输出就是对应的逆波兰表达式
    }

    //将中缀表达式转成对应的List
    //"1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int i=0; //相当于一个指针，用于遍历字符串中每一个字符
        String str; //做对多位数的拼接工作
        char c; //每遍历到一个字符，就放到c中
        do{
            //如果c是一个非数字，就直接加入到list
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                list.add(""+c);
                i++;
            }else {
                //如果是一个数，需要考虑多位数
                str=""; //str置空
                while(i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c; //拼接
                    i++;
                }
                list.add(str);
            }
        }while (i<s.length());
        return list;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
    "3 4 + 5 * 6 -"
    1：从左向右扫描，将3和4压入栈
    2：遇到+运算符，因此弹出4和3（栈顶元素和次顶元素），计算出3+4的值，得7，再将7入栈
    3：将5入栈
    4：接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈
    5：将6入栈
    6：最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list){
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for(String item:list){
            if(item.matches("\\d+")){
                //匹配的是多位数,或一位数
                stack.push(item);
            }else {
                //如果是字符,pop出两个数，并运算，结果再入栈
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                int result=0;
                if(item.equals("+")){
                    result=num2+num1;
                }else if(item.equals("-")){
                    result=num2-num1;
                }else if(item.equals("*")){
                    result=num2*num1;
                }else if(item.equals("/")){
                    result=num2/num1;   //注意顺序
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(Integer.toString(result));
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
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
