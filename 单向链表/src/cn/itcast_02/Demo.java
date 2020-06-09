package cn.itcast_02;


public class Demo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"徐文","大牛");
        //创建一个链表,加入
        SingleLinkedList sll = new SingleLinkedList();
        //加入
//        sll.add(hero1);
//        sll.add(hero2);
//        sll.add(hero3);
//        sll.add(hero4);

        //按照编号的顺序添加
        sll.addByOrder(hero4);
        sll.addByOrder(hero4);
        sll.addByOrder(hero1);
        //sll.addByOrder(hero3);
        sll.addByOrder(hero3);
        sll.addByOrder(hero2);

        SingleLinkedList sll2 = new SingleLinkedList();
        sll2.addByOrder(hero5);
        //sll2.addByOrder(hero2);
        //sll2.addByOrder(hero1);
        //sll2.addByOrder(hero3);

        System.out.println("链表1：");
        sll.showLinkedList();
        System.out.println();
        System.out.println("链表2：");
        sll2.showLinkedList();
        SingleLinkedList.consolidateList(sll.getHead(),sll2.getHead());
        System.out.println("两个链表合并后链表1：");
        sll.showLinkedList();
        //System.out.println();
        //sll2.showLinkedList();
//        //sll2.showLinkedList();
//        SingleLinkedList.consolidateList(sll.getHead(),sll2.getHead());
//        sll.showLinkedList();

//        //测试逆序打印单链表
//        System.out.println("逆序打印单链表，没有改变链表的结构");
//        SingleLinkedList.reversePrint2(sll.getHead());
//
//        System.out.println("逆序打印单链表，改变了链表的结构");
//        SingleLinkedList.reversePrint1(sll.getHead());
//
//        System.out.println("看改变了链表的结构：");
//        sll.showLinkedList();
//        System.out.println("反转前链表：");
//        sll.showLinkedList();
//
//        SingleLinkedList.reverseList(sll.getHead());
//        System.out.println("反转后链表：");
//        sll.showLinkedList();

        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~~");
//        sll.update(newHeroNode);
//
//        //显示一下链表
//        System.out.println("修改后的代码");
//        sll.showLinkedList();
//
//        //测试删除
//        System.out.println("删除后的代码");
//        sll.delete(1);
//        sll.delete(4);
////        sll.delete(3);
////        sll.delete(2);
////        sll.delete(4);
//        sll.showLinkedList();
//
//        //测试，求有效节点的个数
//        System.out.println("有效节点个数："+SingleLinkedList.getLenth(sll.getHead()));
//
//        //测试一下看看是否等得到了倒数第k个节点
//        HeroNode res = SingleLinkedList.findLastNode(sll.getHead(),3);
//        System.out.println(res);
    }
}
