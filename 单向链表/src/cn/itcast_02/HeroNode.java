package cn.itcast_02;

public class HeroNode {
    public int no;  //英雄的序列
    public String name; //英雄的名字
    public String nikename; //英雄的别名

    public HeroNode next;   //next域

    //构造器
    public HeroNode(int no, String name, String nikename) {
        this.no = no;
        this.name = name;
        this.nikename = nikename;
    }

    //为了显示方便，我们重写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }
}
