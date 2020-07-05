package sort.insert;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,2,1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        insertSort(arr,arr.length);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr,int len){
        //我们这是设定第一个数是有序的序列，从第二个数开始插入，
        // 升序：如果待插入的数小于前面某个数，那前面那数后移一下，
        //      如果待插入数大于前面某个数，说明待插入数，应该插到该数的后面那个位置
        //      然后又判断下一个数，依次循环
        for(int i=1;i<len;i++){
            int temp=arr[i];    //待插入元素，从第二个元素开始往前面插入
            int j;
            for(j=i-1;j>=0;j--){
                if(arr[j]>temp){
                    //如果temp前面的数大于temp，前面那数后移代表arr[j]要往后移
                    arr[j+1]=arr[j];
                }else {
                    //如果temp前面那数小于temp，说明找到位置了，要插在该数的后面
                    break;
                }
            }
            arr[j+1]=temp;  //将temp插入arr[j]的后面
        }
    }
}
