package cn.itcast_01;

/*
管理我们的英雄
 */
public class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    /*
    1.找到当前链表的最后节点
    2.将最后这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode){
        //因为head节点不能动，所有我们需要一个辅助节点来遍历temp
        HeroNode temp = head;   //把头节点赋值给了辅助节点temp
        //遍历链表
        while(true){
            //找到链表的最后
            if(temp.next==null){
                break;  //找到最后一个节点了
            }

            //如果没有找到最后，将temp后移,移向了下一个节点
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next=heroNode;
    }

    //显示链表[遍历]
    public void showLinkedList(){
        //先判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个赋值变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp==null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //temp后移一下
            temp=temp.next;
        }
    }
}
