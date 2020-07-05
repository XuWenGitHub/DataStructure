package sort.merge;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        shellSort(arr,arr.length);
        System.out.println("希尔排序");
        System.out.println(Arrays.toString(arr));

    }
    //希尔排序(移位法)
    public static void shellSort(int[] arr,int len){
        //key代表步长，也代表起始元素
        for(int key=len/2;key>0;key/=2){
            for(int i=key;i<len;i++){
                int temp=arr[i];    //带插入元素
                int j;
                for(j=i-key;j>=0;j-=key){
                    if(temp<arr[j]){
                        //后移arr[j]
                        arr[j+key]=arr[j];
                    }else{
                        break;
                    }
                }
                arr[j+key]=temp;
            }
        }
    }
}
