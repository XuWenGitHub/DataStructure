package cn.itcast_02;
import java.util.Scanner;

public class CricleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列对象
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
        //这里size为3，其实只有2个空间可以用，因为最后一个空间我们设置为约定
        Scanner sc = new Scanner(System.in);
        char key;   //接受用户输入
        boolean choose = true;
        //输出一个菜单
        while(choose){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key=sc.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    //System.exit(1);
                    //sc.close();
                    choose=false;
                    break;
                case 'a':
                    if(sc.hasNextInt()){
                        int number=sc.nextInt();
                        arrayQueue.addQueue(number);
                    }else {
                        System.out.println("输入数据有误");
                    }
                    break;
                case'g':
                    try {
                        int number=arrayQueue.getQueue();
                        System.out.println("取出数据为"+number);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'h':
                    try {
                        System.out.println("该队列的头部为："+arrayQueue.headQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }
}
