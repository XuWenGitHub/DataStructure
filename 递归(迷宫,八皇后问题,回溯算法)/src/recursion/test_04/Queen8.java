package recursion.test_04;

public class Queen8 {
    int max=8;
    int[] array = new int[max]; //放八个皇后的位置
    static int count = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有"+count+"中八皇后的摆法");
    }
    //放皇后
    private void check(int n){  //n代表第几个皇后,n从0开始
        if(n==8){
            count++;
            show();
            return;
        }
        for(int i=0;i<max;i++){
            array[n]=i; //把第n个皇后放到n行i列
            if(judge(n)){
                check(n+1); //放好第n个放第n+1个，这里循环，达到了回溯的
            }
        }
    }
    private boolean judge(int n){
        for(int i=0;i<n;i++){
            if(array[n]==array[i]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    //显示一下八个皇后的位置
    private void show(){
        for(int i=0;i<array.length;i++){
            System.out.print((array[i]+1)+" "); //因为数组是从0开始，所以+1
        }
        System.out.println();
    }
}
