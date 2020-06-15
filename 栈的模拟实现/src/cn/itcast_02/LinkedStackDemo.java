package cn.itcast_02;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        String key="";
        boolean loop=true;  //控制是否退出菜单
        Scanner sc = new Scanner(System.in);

        while (loop){
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据到栈（入栈）");
            System.out.println("pop：从栈取出数据（出栈）");
            key=sc.next();
            switch (key){
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result=stack.pop();
                        System.out.println("出栈的数据："+result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
