package search.fibonacciSearch;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,123));
    }
    //编写斐波那契查找算法
    //公式mid=left+F(k-1)-1
    public static int fibSearch(int[] arr,int key){
        int left=0;
        int right = arr.length-1;
        int k=0;    //表示斐波那契分割数值的下标
        int mid = 0;    //存放mid值
        int[] f=fib();  //获取到斐波那契数列
        //获取到斐波那契分割数值的下标k
        while(right>f[k]-1){
            k++;
        }
        //因为f[k]这个值可能大于数组的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向arr
        //不足的部分会使用0填充
        int[] temp= Arrays.copyOf(arr,k);
        //实际上需求a数组最后的数填充temp
        //举例：
        //temp={1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234,1234}
        for(int i=right+1;i<temp.length;i++){
            temp[i]=arr[right];
        }

        //使用while来循环处理，找到我们的数
        while(left<=right){
            mid=left+f[k-1]-1;
            if(key<temp[mid]){
                right=mid-1;
                //全部元素=全面的元素+后面元素
                //f[k]=f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所有我们可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即：在f[k-1]的前面继续查找k--
                k--;
            }else if(key>temp[mid]){
                right=mid+1;
                k-=2;
            }else{
               
            }
        }
        if(left<=right){
            return mid;
        }else{
            return -1;
        }
    }
    //因为后面的公式mid=left+F(k-1)-1，会用到斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<f.length;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
}
