package search.insertValueSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i]=i+1;
        }
        arr[1]=1;
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> arrayList = insertValueSearch(arr,0,arr.length-1,1);
        System.out.println("VALUE:"+1+" index:"+arrayList);
    }
    //插值查找算法，也要求数组是有序的
    public static ArrayList<Integer> insertValueSearch(int[] arr,int left,int right,int value){
        ArrayList<Integer> arrayList = new ArrayList<>();
        //注意：value<arr[0]和value>arr[arr.length-1]必须需要
        //否则我们得到的mid可能越界
        if(left>right||value<arr[0]||value>arr[arr.length-1]){
            return null;
        }
        int mid = left+(right-left)*(value-arr[left])/(arr[right]-arr[left]);   //公式
        int midValue=arr[mid];
        if(value>midValue){
            return insertValueSearch(arr,mid+1,right,value);
        }else if(value<midValue){
            return insertValueSearch(arr,left,mid-1,value);
        }else{
            arrayList.add(mid);
            //向左找
            for(int i=mid-1;i>=0;i--){
                if(arr[i]==midValue){
                    arrayList.add(i);
                }else{
                    break;
                }
            }
            //向右找
            for(int i=mid+1;i<arr.length;i++){
                if(arr[i]==midValue){
                    arrayList.add(i);
                }else{
                    break;
                }
            }
            return arrayList;
        }
    }
}
