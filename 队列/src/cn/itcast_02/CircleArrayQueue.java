package cn.itcast_02;

/*
数组模拟环形队列
 */

public class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //初始化环形数组队列
    public CircleArrayQueue(int size){
        maxSize=size;
        arr=new int[size];
        front=0;    //指向数组第一个
        rear=0;     //指向数组第一个
    }

    //判断队列是否为满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front==rear;
    }

    //入队列
    public void addQueue(int n){
        //先判断队列是否为满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;  //因为是环形队列，所有要取模
    }

    //出队列
    public int getQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            //System.out.println("队列空，不能取出数据");
            throw new RuntimeException("队列空，不能取出数据");
        }
        int value=arr[front];   //先把数据保存起来
        front=(front+1)%maxSize;    //因为是环形队列，所有要取模
        return value;
    }

    //显示所有数据
    public void showQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for(int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //返回队列元素个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据
    public int headQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
