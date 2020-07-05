package sort.quick;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1,2,-10,4,5,0,-1,8,9};
        quickSort3(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        int[] arr2 = new int[80000000];
        for(int i=0;i<80000000;i++){
            int value=(int)Math.random()*100+1;
            arr2[i]=value;
        }
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前："+sdf.format(d1));
        quickSort3(arr2,0,arr2.length-1);
        Date d2 = new Date();
        System.out.println("排序后："+sdf.format(d2));
    }
    public static void quickSort3(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int key = arr[(left+right)/2];  //中间值
        while(l<r){
            while(arr[l]<key){
                l++;
            }
            while(arr[r]>key){
                r--;
            }
            if(l>=r){
                break;  //有序了
            }
            arr[l]^=arr[r];
            arr[r]^=arr[l];
            arr[l]^=arr[r];
            if(arr[l]==key){
                r--;
            }
            if(arr[r]==key){
                l++;
            }
        }
        //当循环退出，我们先要判断，l和r是否相等，如果相等，要让l和r错位
        if(l==r){
            l++;
            r--;
        }
        //左递归
        if(left<r){
            quickSort3(arr,left,r);
        }
        if(right>l){
            quickSort3(arr,l,right);
        }
    }
    public static void quickSort2(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int key = arr[(left+right)/2];
        while(l<r){
            while(arr[l]<key){
                l++;
            }
            while(arr[r]>key){
                r--;
            }
            if(l>=r){
                break;
            }
            arr[l]^=arr[r];
            arr[r]^=arr[l];
            arr[l]^=arr[r];
            if(arr[l]==key){
                r--;
            }
            if(arr[r]==key){
                l++;
            }
        }
        if(l==r){
            l++;
            r--;
        }
        if(left<r){
            quickSort2(arr,left,r);
        }
        if(right>l){
            quickSort2(arr,l,right);
        }
    }
    public static void quickSort(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int key = arr[(left+right)/2];
        //当循环退出后，key左边的元素都比key小，key右边的元素都比key大。
        while(l<r){
            //从左边找到第一个比中间值大或者相等，或者就是中间值本身
            while(arr[l]<key){
                l++;
            }
            //从右往左找到第一个比中间值小或者相等，或者就是中间值本省
            while(arr[r]>key){
                r--;
            }
            //如果l和r相等或者l>r，那么说明中间值的左边都比中间值小，中间值的右边都比中间值大，便可以退出循环
            if(l>=r){
                break;
            }
            //如果没有退出，那么两个数交换
            arr[l]^=arr[r];
            arr[r]^=arr[l];
            arr[l]^=arr[r];
            //判断是否有和中间值key值相同的元素,如果有相同的，l或r不动，另外一个靠拢
            if(arr[l]==key){
                r--;
            }
            if(arr[r]==key){
                l++;
            }
        }

        //当上面循环退出后，我们需要判断两个指针是都指向同一个，如果指向了同一个，那么我们将其错开,防止栈溢出
        //如果两个指针指向同一个，为了下面的递归，我们把其错开
        if(l==r){
            l++;
            r--;
        }

        //左递归和右递归
        //左递归：递归结束条件：r>=left,那么递归就结束
        if(left<r){
            quickSort(arr,left,r);
        }
        //右递归：递归结束条件：l>=right,那么递归就结束
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
