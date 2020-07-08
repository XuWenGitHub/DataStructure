package hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTable hashTab = new HashTable(7);
        //写一个简单的菜单
        String key = "";
        Scanner sc = new Scanner(System.in);
        Scanner scName = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("del：删除雇员");
            System.out.println("exit：退出系统");
            key = sc.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入姓名");
                    String name = scName.nextLine();
                    Employee emp = new Employee(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入查找的id:");
                    int idf = sc.nextInt();
                    hashTab.findEmpById(idf);
                    break;
                case "del":
                    System.out.println("请输入删除雇员的id：");
                    int idD = sc.nextInt();
                    hashTab.delete(idD);
                    break;
                case "exit":
                    flag=false;
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }

    }
}
