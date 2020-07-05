package sort.bubble;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};
        System.out.print("排序前：");
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间："+sdf.format(date1));

        bubbleSort(arr,arr.length);
        System.out.print("排序后：");
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
        Date date2 = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间："+sdf.format(date2));
    }
    public static void bubbleSort(int[] arr,int len){
        for(int i=0;i<len-1;i++){
            boolean flag=true; //优化冒泡排序，如果什么时候，一次都没有发生交换的话，那么说明，数组元素已经有序
            for(int j=0;j<len-1-i;j++){
                if(arr[j]>arr[j+1]){
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                    flag=false; //如果发生交换，把flag变成false
                }
            }
            if(flag){
                break;  //如果哪一趟，没有发生交换，数组元素有序，直接退出循环
            }
        }
    }
}
