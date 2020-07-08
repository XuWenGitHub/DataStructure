package hashtable;
//管理多条链表，哈希表
public class HashTable {
    private EmployeeLinkedList[] empLinkedListArray;
    private int size;   //表示有多少条链表
    //构造器
    public HashTable(int size){
        this.size=size;
        empLinkedListArray = new EmployeeLinkedList[size];
        //一个坑，不要忘了初始化每个链表
        for(int i=0;i<size;i++){
            empLinkedListArray[i]=new EmployeeLinkedList();
        }
    }
    //添加雇员
    public void add(Employee emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历哈希表
    public void list(){
        for(int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Employee emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到 雇员id = %d\n",(empLinkedListNo),id);
        }else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //根据输入的id，删除雇员
    public void delete(int id){
        int empLinkedLsitNo = hashFun(id);
        empLinkedListArray[empLinkedLsitNo].delete(id);
    }

    //编写一个散列函数,使用一个简单的取模法
    public int hashFun(int id){
        return id%size;
    }

}
