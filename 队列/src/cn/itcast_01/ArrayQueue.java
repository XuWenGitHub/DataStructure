package cn.itcast_01;
/*
数组模拟队列，不是环形的
目前数组使用一次就不能用，没有达到复用的效果;
 */
public class ArrayQueue {
    private int maxSize;    //表示数组的最大容量
    private int front;  //队列头
    private int rear;   //队列尾
    private int[] arr;  //该数组用于存放数据，模拟队列

    //创建队列构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[arrMaxSize];
        front=-1;   //指向队列头部,分析出front是指向队列头部的前一个位置
        rear=-1;    //指向队列尾部,指向队列尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列，入队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++; //让rear后移
        arr[rear]=n;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;    //让front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历arr数组
        if(isEmpty()){
            System.out.println("队列空，无数据");
            return;
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        //判断不为空
        if(isEmpty()){
            //System.out.println("队列空的，没有数据");
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front+1];
    }
}
