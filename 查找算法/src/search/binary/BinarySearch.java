package search.binary;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7,8,9,9,9,9,9,9,10};
        ArrayList<Integer> list = binarySearch(arr,0,arr.length-1,3);
        System.out.println(list);
    }
    //二分查找，查找一个数（可以多个相同的数，会把所有的和查找值相同的下标全部存入list里面返回)
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int value){
        ArrayList<Integer> list = new ArrayList<>();
        if(left>right){
            return null;  //表示没有找到
        }
        int mid = (left+right)/2;
        int midValue = arr[mid];

        if(value>midValue){
            return binarySearch(arr,mid+1,right,value);
        }else if(value<midValue){
            return binarySearch(arr,left,mid-1,value);
        }else {
            //如果有多个相同的元素，那么存入list里面，找到第一个后，往左往右扫描(因为数组元素有序，所以相等的元素，就在他的左右)
            int temp=mid-1;
            while(true){
                if(temp<0||arr[temp]!=value){
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(mid);

            temp=mid+1;
            while(true){
                if(temp>=arr.length||arr[temp]!=value){
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
