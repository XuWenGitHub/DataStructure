package recursion.test_04;
/*
八皇后问题
用一维数组来装八皇后的位置，下标代表行，但是从0开始，下标所对应的值代表列，但是也是从0开始
 */
public class Queue8 {
    static int count = 0;   //来统计一共有多少种方法
    static int judgeCount=0;    //统计一共判断了多少次（judge）
    int max=8;  //一共有八个皇后
    int[] array = new int[max]; //统计八皇后的位置，下标代表行，下标所对应的值代表列
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);    //从第一个皇后开始放
        System.out.println("放八皇后的方法一共有"+count+"种");
        System.out.println(count+"种方法，一共判断了"+judgeCount+"次");
    }

    //放每一个皇后的算法
    private void check(int n){  //n代表第几个皇后
        if(n==max){
            count++;
            show();
            return;
        }
        for(int i=0;i<max;i++){
            array[n]=i; //把第n皇后放到i位置
            if(judge(n)){
                //如果不冲突，那么开始放n+1个皇后，因为在循环里面，这里递归，就会产生回溯问题
                check(n+1);
            }
        }
    }
    //判断皇后的位置是否满足条件
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            //判断第n个皇后跟前面的皇后冲突吗
            //每个皇后当然不能放在同一行，所有就不用判断了，只需要判断是否在同一列和是否在同一对角线
            if(array[n]==array[i]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    //输出八皇后位置数组的方法
    private void show(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
