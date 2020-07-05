package sort.select;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,1,123,53,45,0,-1,123};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr,arr.length);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }
    public static void selectSort(int[] arr,int len){
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                if(arr[i]>arr[j]){
                    arr[i]^=arr[j];
                    arr[j]^=arr[i];
                    arr[i]^=arr[j];
                }
            }
        }

    }
}
