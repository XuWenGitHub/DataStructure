package hashtable;

/*
表示链表
 */
public class EmployeeLinkedList {
    //头指针,指向第一个雇员，因此我们这个链表的head是直接指向第一个Employee
    private Employee head;

    //添加雇员到链表
    public void add(Employee emp){
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员,先比较id
        Employee temp = head;
        while(true){
            if(temp.id>emp.id){
                break;  //从这里退出，说明要添加到temp的前面
            }
            if(temp.next==null){
                break;  //从这里退出说明要添加到最后一个,就是temp.next位置
            }
            temp=temp.next;
        }
        if(temp.id>emp.id){
            if(temp==head) {
                //说明temp是头节点，并且待插入员工要在该head前面，直接head=emp，emp.next=temp，即可
                head = emp;
                emp.next = temp;
            }else{
                //说明当前temp不是head,定义辅助节点，找到cur.next==temp,然后cur.next=emp;emp.next=temp;
                Employee cur = head;
                while(true){
                    if(cur.next==temp){
                        break;
                    }
                }
                cur.next=emp;
                emp.next=temp;
            }
        }else{
            temp.next=emp;
        }
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head==null){
            System.out.println("第"+(no)+" 链表为空");
            return;
        }
        System.out.print("第"+(no)+" 链表的信息为：");
        Employee temp = head;
        while(true){
            System.out.printf("=> id=%d name=%s\t",temp.id,temp.name);
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //找到返回Employee，没找到返回null
    public Employee findEmpById(int id){
        //判断链表是否为空
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Employee temp = head;
        while(true){
            if(temp.id==id){
                break;  //说明找到了
            }
            if(temp.next==null){
                temp=null;
                break;//说明遍历当前链表没有找到该雇员
            }
            temp=temp.next;
        }
        return temp;
    }

    //根据id删除雇员
    public void delete(int id){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        Employee temp = head;
        while(true){
            if(temp.id==id){
                break;  //找到了
            }
            if(temp.next==null){
                temp=null;  //说明找完了链表，但没找到
                break;
            }
            temp=temp.next;
        }

        if(temp==null){
            System.out.println("删除失败，当前链表没有找到id=" +
                    id+"雇员");
        }else{
            //已经找到该雇员，删除它即可
            //当前temp就是需要删除的雇员,判断他是不是第一个，如果是第一个，head直接置空，如果不是第一个，找到它的上一个
            Employee cur = head;
            if(cur.id==temp.id){
                head=head.next;
            }else {
                while(cur.next.id!=temp.id){
                    cur=cur.next;
                }
                cur.next=temp.next;
            }
            System.out.println("删除iD="+id+"雇员成功");
        }
    }
}
