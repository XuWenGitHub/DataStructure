package practice.dynamic;

/**
 * @PackgeName: practice.dynamic
 * @ClassName: KnapsackProblem
 * @Author: XuWen
 * Date: 2020/9/7 20:21
 * Introduce:
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};    //物品的重量
        int[] val = {1500,3000,2000};   //物品的价值,这里的val[i]就是前面讲的v[i]
        int m = 4;  //表示背包的容量
        int n = val.length; //物品的个数

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];  //行代表物品的个数，m表示背包的容量

        //初始化第一行和第一列（也可以不去处理，因为初始化就是为0)
        for(int i=0;i<v.length;i++){
            v[i][0] = 0;    //将第一列设置为0
        }
        for(int i=0;i<v[0].length;i++){
            v[0][i]=0;  //将第一行设置为0
        }

        //根据前面得到的公式来动态规划处理
        for(int i=1;i<v.length;i++){    //不处理第一行 i是从1开始的
            for(int j=1;j<v[i].length;j++){ //不处理第一列 j是从1开始的
                //公式
                if(w[i-1]>j){ //因为我们程序是从1开始的,但是商品重量和商品价值都要从0开始，因此原来的公式中w[i]要修改成w[i-1]
                    v[i][j]=v[i-1][j];
                }else{  //表示新添加的商品重量小于等于当前容量
                    //因为我们程序是从1开始的,但是商品重量和商品价值都要从0开始，因此原来的公式中val[i]要修改成val[i-1] w[i]修改成w[i-1]
                    //v[i][j]=Math.max(v[i-1][j],(val[i-1]+v[i-1][j-w[i-1]]));

                    //为了记录商品存放到背包的情况，我们不能直接使用简单的公式，需要使用if-else来处理
                    if(v[i-1][j]<(val[i-1]+v[i-1][j-w[i-1]])){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j]=1;   //只有这种情况下，我们才会往path里面存，因为这里面是最优的
                    }else{
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        //输出一下v 看看目前的情况
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("==============================");
        //输出最后我们是放入的哪些商品
        //这样输出汇报所有的放入情况都得到，但其实我们只需要最后的放入情况
//        for(int i=0;i<path.length;i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入到背包\n", i);
//                }
//            }
//        }

        //动脑筋,找到最终加入背包的物品
        int i=path.length-1;    //行的最大下标
        int j=path[0].length-1; //列的最大下标
        while(i>=0&&j>=0){  //从path的最后开始找
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n", i);
                j-=w[i-1];  //w[i-1]表示当前存放的商品容量，然后现在就要把背包容量减去那么多
            }
            i--;    //因为每件物品只能存放一个，所有--
        }
    }
}
