package recursion.test_03;

public class Queue8 {
    //定义一个统计变量，统计一共有多少种八皇后摆放问题
    static int count=0;
    //定义一个max表示共有多少个皇后
    int max=8;
    //统计一下判断了多少次（judge）
    static int judgeCount=0;
    //定义数组array，保存皇后放置位置的结果，比如arr[8]={0,4,7,5,2,6,1,3}
    int[] array=new int[max];   //下标代表行，但是从0开始，下标所对应的值代表列，但是也是从0开始
    public static void main(String[] args) {
        //测试一把，8皇后是否证券
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有"+count+"种八皇后摆放方式");
        System.out.println("一共判断冲突"+judgeCount+"次");
    }


    //编写一个方法，放置第n个皇后
    //特别注意：check 是 每一次递归时，进入到check中都要for(int i=0;i<max;i++),因此会有回溯
    private void check(int n){
        if(n==max){ //n==8,说明现在放第9个皇后，前8个皇后放好了
            count++;
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++){
            //先把当前这个皇后n，放到该行的第1列
            array[n]=i;
            //判断当放置n个皇后去i列是，是否冲突
            if(judge(n)){   //不冲突
                check(n+1); //接着放下一个皇后，即开始递归
            }

            //如果冲突，就继续执行arr[n]=i，即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n+1个皇后,因为n从0开始
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        //判断放的n皇后的位置，和前面放的皇后位置，列是否相同，是否在同一斜线
        //不需要判断是否在同一行，没有必要，n和i就表示行，不可能相等
        for(int i=0;i<n;i++){
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置打印出来
    private void print(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
