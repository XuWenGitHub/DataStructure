package practice.hannuota;

/**
 * @PackgeName: practice.hannuota
 * @ClassName: HannuoTa
 * @Author: XuWen
 * Date: 2020/9/4 16:28
 * Introduce:
 */
public class HannuoTa {

    public static void main(String[] args) {
        hanoi('A','B','C',5);
    }
    public static int step=0;
    /**
     * 模拟鼠标移动,把第N个盘子从A塔移动到B塔
     * @param A 第n个盘子的初始地点
     * @param B 第n个盘子移动到的地点
     * @param n 表示第几个盘子
     */
    public static void remove(char A,char B,int n){
        System.out.println("第"+(++step)+"步,把第"+n+"个盘子,"+A+"塔---------->"+B+"塔");
    }

    /**
     * 汉诺塔递归实现
     * @param A 表示n盘子初始地点
     * @param B 表示n盘子从A塔往C塔移动时，中间过度的B塔
     * @param C 表示n盘子最终要去的地方
     * @param n 表示第几个盘子，第一次调用，也表示有多少个盘子
     */
    public static void hanoi(char A,char B,char C,int n){
        //如果只有1个盘子的话，直接从A移动到C，就OK了
        if(n==1){
            remove(A,C,n);
        }else{
            //如果我们有n>=2情况，我们总是可以看做是两个盘，1.最下边的一个盘 2.上面的所有盘子
            //先把n-1个盘子(最上面的所有盘)从A塔通过C塔移动到B塔
            hanoi(A,C,B,n-1);
            //再把最底下那个盘子从A塔移动到C塔
            remove(A,C,n);
            //最后再把n-1个盘子从B塔通过A塔移动到C塔
            hanoi(B,A,C,n-1);
        }
    }
}
