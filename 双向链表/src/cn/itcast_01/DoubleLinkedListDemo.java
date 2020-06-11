package cn.itcast_01;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双向链表的测试
        System.out.println("双向链表的测试");
        HeroNode hero2 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero1 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");


        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero4);
        doubleLinkedList.addOrder(hero1);
        doubleLinkedList.addOrder(hero3);
        doubleLinkedList.addOrder(hero2);
        doubleLinkedList.addOrder(hero4);
        doubleLinkedList.addOrder(hero3);


        doubleLinkedList.showList();

        //修改测试
        HeroNode newHeroNode = new HeroNode(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        doubleLinkedList.showList();

        //删除功能
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.showList();
    }
}
