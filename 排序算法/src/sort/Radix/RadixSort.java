package sort.Radix;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
有负数的数组，不支持基数排序,因为效率也低了，全是正数，就推荐基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214,0,100,10000,0};
        radixSort2(arr);
        System.out.println("基数排序后");
        System.out.println(Arrays.toString(arr));

//        int[] arr2 = new int[8000000];
//        for(int i=0;i<arr2.length;i++){
//            arr2[i]=(int)Math.random()*100+1;
//        }
//        Date d1 = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(d1));
//        radixSort(arr2);
//        Date d2 = new Date();
//        System.out.println(sdf.format(d2));
    }
    //基数排序,不支持负数，负数，不推荐基数排序
    /*
    分析：1.先找到所有数中，最大数的位数有多少位
        2.然后取出每一位数字，然后放入对应的桶中，
        3.再将桶里有数据的依次往arr里面放
        4,上面三步，循环，循环最大数的位数次，每一次，取出的数字为个位，十位，百位...
     */
    public static void radixSort(int[] arr){
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据后，桶内元素的个数
        int[] bucketElementCounts = new int[10];

        //先得到数组中最大的数的有多少位
        int digit=0;
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        while(max!=0){
            max/=10;
            digit++;
        }

        //再根据最大数有多少位，就进行多少次桶排序
        for(int i=0;i<digit;i++){
            //先把数除i再对10取模，得到数的每个位，如果不够，就为0
            for(int j=0;j<arr.length;j++){
                int digitNumber=0;  //表示取出每个位的数
                digitNumber=(arr[j]/((int)Math.pow(10,i)))%10;
                //放入桶中
                bucket[digitNumber][bucketElementCounts[digitNumber]]=arr[j];
                bucketElementCounts[digitNumber]++; //该桶里元素数量加一
            }

            int index=0;    //放入桶时，arr的下标
            //再把桶里有数据的，依次放入arr中
            for(int k=0;k<bucket.length;k++){
                if(bucketElementCounts[k]!=0){
                    //桶里有数据，放入arr里面
                    for(int l=0;l<bucketElementCounts[k];l++){
                        arr[index]=bucket[k][l];
                        index++;
                    }
                }
                //桶里数据放完，要清空桶的个数
                bucketElementCounts[k]=0;
            }
        }
    }
    public static void radixSort2(int[] arr){
        //先造一个二维数组，表示十个桶
        int[][] bucket = new int[10][arr.length];
        //在造一个一维数组，记录十个桶中，每个桶存储了多少个元素
        int[] bucketElementCounts=new int[10];
        //先找到最大数，有多少位
        int max = arr[0];
        int digit=0;    //记录最大数有多少位
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        while(max!=0){
            max/=10;
            digit++;
        }
        for(int i=0;i<digit;i++){   //把数据装进每个桶，和把每个桶中数据放入arr一共进行digit次
            for(int j=0;j<arr.length;j++){
                int num=0;  //表示每个数的个位十位百位...
                if(i==0){
                    num=arr[j]%10;
                }else{
                    num=(arr[j]/(int)(Math.pow(10,i)))%10;
                }
                bucket[num][bucketElementCounts[num]]=arr[j];   //装进桶
                bucketElementCounts[num]++;
            }
            int index=0;
            //再从桶里取出然后放入arr里面
            for(int k=0;k<bucketElementCounts.length;k++){  //表示10个桶
                if(bucketElementCounts[k]!=0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index]=bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k]=0;   //放入arr中后，清空该桶
            }
        }
    }
}
