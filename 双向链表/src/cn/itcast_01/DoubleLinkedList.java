package cn.itcast_01;

public class DoubleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头结点
    public HeroNode getHead(){
        return head;
    }

    //遍历双向链表的方法
    public void showList(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }

    //按照编号顺序添加到双链表里,去掉heroNode.no重复的
    public void addOrder(HeroNode heroNode){
        HeroNode cur=head.next;  //帮助遍历heroNode，看是否有重复的
        while(cur!=null){
            if(cur.no==heroNode.no){
                System.out.println(heroNode.no+"以重复");
                return;
            }
            cur=cur.next;
        }

        HeroNode temp = head;   //辅助节点,找位置的节点
        //先找到位置
        while(temp.next!=null){
            if(temp.next.no>heroNode.no){
                break;
            }
            temp=temp.next;
        }
        //循环退出后，就找到新添加节点的位置，在temp下一个位置添加
        //如果添加节点的位置，不是最后一个的话，那么，需要连接新节点和新节点的下一个节点
        if(temp.next!=null){
            heroNode.next=temp.next;
            temp.pre=heroNode;
        }
        temp.next=heroNode;
        heroNode.pre=temp;

    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    //修改一个节点的内容
    public void update(HeroNode heroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        Boolean flag=false; //表示是否找到该节点
        while(true){
            if(temp==null){
                break;  //已经遍历完
            }
            if(temp.no==heroNode.no){
                flag=true;
                break;  //找到该节点
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=heroNode.name;
            temp.nickname=heroNode.nickname;
        }else {
            System.out.println("没有找到该节点");
        }
    }

    //删除一个节点
    public void delete(int no){
        if(head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;   //标志是否找到待删除节点
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next; //后移
        }
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next!=null) {
                //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针异常
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("没有找到no为"+no+"节点");
        }
    }


}
