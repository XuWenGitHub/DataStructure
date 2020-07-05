package sort.merge;

import java.util.Arrays;

/*
归并排序(分治算法)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort2(arr,0,arr.length-1,temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }
    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid= (left+right)/2;    //中间索引
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并方法
     * @param arr 排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引（也就是左边有序序列的最后一个元素的索引）
     * @param right 右边索引（右边有序序列的最后一个元素的索引）
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        System.out.println("(:");
        int i=left; //初始化i，左边有序序列的初始索引
        int j = mid+1;  //右边有序序列的初始化索引
        int t = 0;  //指向temp数组的当前索引

        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }else{
                temp[t]=arr[j];
                t+=1;
                j+=1;
            }
        }

        //把有剩余数据的一边的数据一次全部填充到temp
        while(i<=mid){
            temp[t]=arr[i];
            t+=1;
            i+=1;
        }
        while(j<=right){
            temp[t]=arr[j];
            t+=1;
            j+=1;
        }

        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t=0;
        int tempLeft = left;
        //第一次合并 templeft=0，right=1//templeft=2，right=3//templeft=1，right=3
        //最后一次 templeft=0, right=7
        while (tempLeft<=right){
            arr[tempLeft] = temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
    public static void mergeSort2(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left+right)/2;
            //左递归
            mergeSort2(arr,left,mid,temp);
            //右递归
            mergeSort2(arr,mid+1,right,temp);
            //合并
            merge2(arr,left,mid,right,temp);
        }
    }
    public static void merge2(int[] arr,int left,int mid,int right,int[] temp){
        int i=left; //表示左边有序序列
        int j=mid+1;    //表示右边有序序列
        int t = 0;  //temp的索引

        //先两个序列依次比较，从小到大放入temp
        while(i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                t+=1;
                i+=1;
            }else{
                temp[t]=arr[j];
                j+=1;
                t+=1;
            }
        }

        //循环退出后，一定有一个索引超过了自身序列
        while(i<=mid){
            temp[t]=arr[i]; //说明i这个序列没有添加完
            t+=1;
            i+=1;
        }
        while(j<=right){
            temp[t] = arr[j];
            t+=1;
            j+=1;
        }

        //最后把temp又放回arr，注意：不是每次放的元素个数一样
        t=0;
        int tempLeft = left;
        while(tempLeft<=right){
            arr[tempLeft]=temp[t];
            tempLeft+=1;
            t+=1;
        }
    }
}
