package praceice.one;

/**
 * @PackgeName: praceice.one
 * @ClassName: BinarySearch
 * @Author: XuWen
 * Date: 2020/9/4 15:49
 * Introduce:
 */
public class BinarySearch {
    public static void main(String[] args) {
        //测试
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,1);
        System.out.println(index);
    }
    //二分查找的非递归实现
    /**
     *
     * @param arr   待查找的数组,arr是升序排列
     * @param target    需要查找的数
     * @return  返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }
}
