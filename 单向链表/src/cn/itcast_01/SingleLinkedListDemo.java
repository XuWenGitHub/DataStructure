package cn.itcast_01;

/*
进行一个测试
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建一个链表,加入
        SingleLinkedList sll = new SingleLinkedList();
        sll.add(hero1);
        sll.add(hero2);
        sll.add(hero3);
        sll.add(hero4);

        //显示一下链表
        sll.showLinkedList();
    }
}
