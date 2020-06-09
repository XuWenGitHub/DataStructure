package cn.itcast_01;
/*
水浒传英雄节点
 */
public class HeroNode {
    //这三个就是数据域data
    public int no;  //英雄序列
    public String name; //英雄名字
    public String nickname; //英雄昵称

    public HeroNode next;   //指向下一个节点   相当于next域,默认为null

    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name=name;
        this.nickname=nickname;
    }

    //为了显示方便，我们重写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
