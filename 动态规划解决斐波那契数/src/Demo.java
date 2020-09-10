import java.util.Arrays;
import java.util.Scanner;

/**
 * @PackgeName: PACKAGE_NAME
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/9/9 20:19
 * Introduce:   动态规划解决斐波那契数
 */
public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入求第几个斐波那契数：");
        int n = sc.nextInt();
        int[] fib = new int[n+1];
        System.out.println(fib(fib,n));
        System.out.println(fib(n));
        System.out.println(Arrays.toString(fib));
    }
    //递归+动态规划解决斐波那契数
    /*
    分析：
    A：在形参中，加入fib[]数组，它的长度是n+1，因为我们fib[0]=0,n表示第几个斐波那契数
    B：然后遍历时，递归出口变成，n==0和n==1，因为我们需要得到fib[]数组，然后如果n!=0&&n!=1，那么就可以判断fib[n]>0
    C：如果fib[n]>0，说明已经求出来了第n个斐波那契数，就不用往下递归，直接拿出来用
    D：注意！！！
    最重要的是，如果递归没有出去，fib[n] = fib(fib,n-1)+fib(fib,n-2);  这就是给斐波那契数组添加斐波那契数
    最后在return fib(fib,n-1)+fib(fib,n-2);即可
     */
    public static int fib(int[] fib,int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(fib[n]>0){
            return fib[n];
        }
        fib[n] = fib(fib,n-1)+fib(fib,n-2);
        return fib(fib,n-1)+fib(fib,n-2);
    }

    //递归解决求第n个斐波那契数
    //0 1 1 2 3 5 8 13 21
    public static int fib(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }

}
