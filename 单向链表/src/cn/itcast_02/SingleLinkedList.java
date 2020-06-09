package cn.itcast_02;

import java.util.Stack;

public class SingleLinkedList {
    public SingleLinkedList() {
    }

    public HeroNode getHead() {
        //返回头结点
        return head;
    }

    //先初始化一个头结点
    private HeroNode head = new HeroNode(0,"","");

    //将节点添加到链表里
    public void add(HeroNode heroNode){
        //创建一个辅助节点
        HeroNode temp = head;

        //找到链表尾部
        while(temp.next!=null){
            temp=temp.next; //后移辅助节点
        }

        temp.next=heroNode;
    }

    //第二种方式在添加英雄时，根据排名奖英雄插入到指定位置
    // 如果有这排名则添加失败，给出提示
    public void addByOrder(HeroNode heroNode){
        //头结点不能动，造一个辅助节点
        HeroNode temp =head;
        boolean flag = false;   //标识添加的编号是否存在，默认为false
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){   //位置找到了，就在temp的后面插入
                break;
            }else if(temp.next.no==heroNode.no){    //说明希望添加的heroNode的编号已经存在
                flag=true;  //说明编号存在
                break;
            }
            temp = temp.next;   //后移,遍历当前链表
        }
        if(flag){
            System.out.println("准备插入的英雄的编号"+heroNode.no+"已经存在");
        }else {
            //插入到链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断链表不为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助节点
        HeroNode temp = head.next;
        boolean flag = false;   //表示是否找到该节点
        while (true){
            if(temp==null){
                break;  //链表遍历完毕了
            }
            if(temp.no==newHeroNode.no){
                flag=true;  //说明找到了
                break;
            }
            temp=temp.next; //后移
        }
        if(flag){
            temp.name=newHeroNode.name;
            temp.nikename=newHeroNode.nikename;
        }else {
            System.out.println("没有找到编号"+newHeroNode.no+"的节点,不能修改");
        }
    }

    //删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;   //标识是否找到待删除节点的
        while(true){
            if(temp.next==null){
                System.out.println("链表为空");
                break;  //链表空
            }
            if(temp.next.no==no){
                flag=true;
                break;  //找到了待删除节点的前一个节点temp
            }
            temp = temp.next;   //后移
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("要删除的"+no+"节点不存在");
        }
    }

    //遍历链表
    public void showLinkedList(){
        //先判断链表不为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }

        //创建一个辅助节点
        HeroNode temp = head.next;
        while(true){
            System.out.println(temp);
            if(temp.next==null){
                break;
            }
            temp=temp.next; //辅助节点后移
        }
    }

    /*
    合并两个有序的单链表，合并之后的链表依然有序
    定义一个cur1，指向第一个有序的单链表的head.next，用来遍历第一个单链表
    定义一个cur2，指向第二个有序的单链表的head.next，用来遍历第二个单链表
    定义一个next，用来保存cur的next域，以防止链表断了
    创建一个新的头结点newHead，相当于新的一条链表，然后cur1和cur2的cn比较，
    定义一个last，指向newHead，让其一直指向newHead的最后一个有效元素
        cur1小：先保存cur1的next域，让last指向cur1，再让cur1指向null，最后让cur1和last都要后移一下
        cur2小：先保存cur2的next域，让last指向cur2，再让cur2指向null，最后让cur2和last都要后移一下
        一样大：和上面一样，随便弄一个cur，但是后移是cur1和cur2和last都要后移
     */
    /**
     * 两个有序的单链表的合并，最后合并在了head1里面
     * @param head1 第一个单链表的头节点
     * @param head2 第二个单链表的头结点
     */
    public static void consolidateList(HeroNode head1,HeroNode head2){
        HeroNode cur1 = head1.next; //遍历head1链表
        HeroNode cur2 = head2.next; //遍历head2链表
        HeroNode newHead = new HeroNode(0,"","");
        HeroNode next = null;   //用来装cur的next域，避免链表断掉
        HeroNode last = newHead;    //定以一个辅助变量，一直指向newHead链表的最后一个有效元素
        while(cur1!=null||cur2!=null){
            //如果其中一个链表空了，那么直接把另一个不空的部分，加到newHead最后,这里很重要，不然就会出现空指针异常
            if(cur1==null||cur2==null){
                next=cur1!=null?cur1:cur2;
                last.next=next; //newHead链表最后一个有效元素指向剩余部分
                break;
            }

            if(cur1.no<cur2.no){
                //添加cur1，并cur1后移
                next = cur1.next;   //保存cur1的指针域，防止丢失下一个数据
                last.next = cur1;   //newHead链表最后一个有效元素指向cur1
                cur1.next = null;   //cur1的next置空
                last=last.next; //后移一下，last就又是最后一个有效元素了
                cur1 = next;         //最后后移cur1
            }else if(cur2.no<cur1.no){
                //添加cur2，并cur2后移
                next = cur2.next;   //保存cur2的指针域，防止丢失下一个数据
                last.next = cur2;   //newHead链表最后一个有效元素指向cur2
                cur2.next = null;   //cur2的next置空
                last = last.next;   //后移一下，last就又是最后一个有效元素了
                cur2 = next;    //cur2后移
            }else{
                //这里是cur1和cur2的no相等，那么随便添加一个即可，然后cur1和cur2都要后移
                next = cur1.next;   //保存cur1的next域，防止链表断了
                last.next = cur1;
                cur1.next=null;
                last = last.next;
                cur1 = next;
                cur2 = cur2.next;
            }
        }
        //将第一个链表的头节点指向newHead的第一个有效数据
        head1.next=newHead.next;
    }


    /**
     * 方式1：直接逆序单链表打印，改变了单链表的结构
     * 先将一个带头结点的单链表反转
     *     再将单链表打印
     *     再将单链表反转
     * @param head  需要逆序打印的单链表的头节点
     */
    public static void reversePrint1(HeroNode head){
        if(head.next==null){
            return; //链表为空
        }
        //实现反转
        HeroNode cur = head.next;   //用来遍历原先的链表
        HeroNode next = null;   //取出原先链表中某一个节点，用next保存它的next域，然后cur就可以后移
        HeroNode reverseHead = new HeroNode(0,"","");   //定义一个新的头结点，然后实现反转
        while(cur!=null){
            next = cur.next;    //先保存cur的next域，后面好后移
            cur.next = reverseHead.next;    //用cur指向reversehead链表的下一个
            reverseHead.next = cur;     //再用reversehead指向cur
            cur = next; //cur后移
        }
        //最后再把head.next=reverseHead.next
        head.next = reverseHead.next;

        //打印链表
        HeroNode temp = head.next;
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next; //后移
        }
    }

    /**
     * 方式2：利用栈，不改变单链表的数据结构
     * 利用栈这个数据结构，将各个节点压入到栈中，
     * 然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * @param head  需要逆序打印的单链表的头结点
     */
    public static void reversePrint2(HeroNode head){
        if(head.next==null){
            return; //空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;   //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印
        while(stack.size()>0){
            System.out.println(stack.pop());    //stack的特点就是先进后出
        }
    }

    /**
     * 将单链表进行反转【百度面试题】
     * @param head 头结点
     */
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return; //如果链表为空，或者只有一个节点，那么就无需反转
        }

        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;   //保留cur的下一个节点的位置，不然cur插入到reverseHead的最前端，那么原来的链表cur的下一个节点位置找不到了
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表reverseHead的最前端
        while(cur!=null){
            next=cur.next;  //先暂时保留当前节点的下一个节点，因为后面需要使用

            //下面这两句代码，实现了，取出每一个节点，放在新链表的reverseHead的最前端
            cur.next=reverseHead.next;  //将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur;   //再讲新的链表的头结点指向cur

            cur = next; //后移一下
        }
        //将head.next指向reverseHead.next,实现了单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    /**
     *
     * @param head  链表的头结点
     * @param index 倒数第index个
     * @return  返回倒数第k个节点，找不到返回null
     */
    public static HeroNode findLastNode(HeroNode head,int index){
        if(head.next==null){
            return null;    //链表为空
        }
        //第一次遍历得到链表的有效节点的个数
        int size = getLenth(head);
        //第二次遍历，遍历size-index位置，就是我们倒数第k个节点
        //先做一个index的校验
        if(index<=0||index>size){
            return null;
        }
        //定义一个辅助节点
        HeroNode cur = head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;   //后移
        }
        return cur;
    }

    //获取到单链表的节点的个数（如果是带头结点的链表，需要不统计头结点）
    /**
     *
     * @param head 链表的头结点
     * @return  返回的就是有效节点的个数
     */
    public static int getLenth(HeroNode head){
        if(head.next==null){
            return 0;   //空链表
        }
        int length=0;
        //定义一个辅助节点
        HeroNode cur = head.next;   //没有统计头结点
        while (cur!=null){
            length++;
            cur=cur.next;   //后移
        }
        return length;
    }
}
