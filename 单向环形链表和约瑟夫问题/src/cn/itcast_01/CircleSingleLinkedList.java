package cn.itcast_01;

public class CircleSingleLinkedList {
    //先创建一个first节点,当前没有编号
    private Kid first = new Kid(-1);

    //添加小孩节点，构建成一个环形的单向链表

    /**
     * 添加小孩
     * @param nums    小孩个数
     */
    public void addBoy(int nums){
        //nums数据校验
        if(nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        Kid cur = null; //辅助指针，帮我们构建环形链表
        //使用for循环来创建我们的环形链表
        for(int i=1;i<=nums;i++){
            //根据编号来创建小孩节点
            Kid kid = new Kid(i);
            //如果是第一个小孩
            if(1==i){
                first=kid;
                first.setNext(first);   //构成环状
                cur=first;
            }else {
                cur.setNext(kid);
                kid.setNext(first);
                cur=cur.getNext();  //cur后移，一直指向最后一个有效节点
            }
        }
    }

    //遍历当前的环形链表
    public void showList(){
        if(first==null){
            System.out.println("没有任何小孩");
            return;
        }
        //First不能动，弄一个辅助指针
        Kid cur = first;
        while (true){
            System.out.println("小孩的编号"+cur.getNo());
            if(cur.getNext()==first){
                break;
            }
            cur=cur.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    /**
     *
     * @param startNo   表示从第几个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示最初有多少个小孩在圈中
     */
    public void countKid(int startNo,int countNum,int nums){
        //数据校验
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误,请重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Kid helper = first;
        //让helper指向最后一个小孩节点
        while (true){
            if(helper.getNext()==first){
                //让helper指向最后一个小孩节点
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前，先让frist和helper移动startNo-1次,让frist指向指定第几个小孩开始数数，让helper指向指定小孩的上一个
        for(int i=0;i<startNo-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //直到圈中只有一个节点
        while(true){
            if(helper==first){
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时的移动countNum-1次，让first指向出圈小孩，helper指向出圈小孩的上一个
            for(int i=0;i<countNum-1;i++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时frist指向的节点，就是要出圈的小孩的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将frist指向出圈小孩节点的下一个节点
            first=first.getNext();
            helper.setNext(first);  //这样就是出圈小孩的上一个指向出圈小孩的下一个，就删除了出圈的那个小孩
        }
        System.out.println("最后留在圈中的小孩编号"+first.getNo());
    }
}
