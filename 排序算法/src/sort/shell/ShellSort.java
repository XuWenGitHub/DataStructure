package sort.shell;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        shellSort1(arr,arr.length);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));

        //测试速度
        int[] arr2 = new int[80000000];
        for(int i=0;i<80000000;i++){
            int value =(int)Math.random()*100+1;    //1-100的随机值
            arr2[i]=value;
        }

        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d1));
        shellSort1(arr2,arr2.length);
        Date d2 = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf2.format(d2));
    }

    //希尔排序【移位发】，效率快，推荐 8千万个数据只需要1秒就排序OK了
    /*
    思路：先把数组分成，长度/2个组，然后每个组进行插入排序
         再把数组分成，长度/2/2个组，然后每个组再进行插入排序
         ....直到数组长度/2/2/2/2...最终变成0，那么就结束循环了，就排序成功了
     */
    public static void shellSort2(int[] arr,int len){
        //gap步长，也是分成多少个组,然后对每个组进行插入排序
        for(int gap=len/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int temp=arr[i];
                int j;
                for(j=i-gap;j>=0;j-=gap){
                    if(arr[j]>temp){
                        //arr【j】后移
                        arr[j+gap]=arr[j];
                    }else {
                        break;  //找到位置了,在j+gap的位置
                    }
                }
                arr[j+gap]=temp;    //插入
            }
        }
    }


    //希尔排序【移位发】,效率快，推荐
    public static void shellSort1(int[] arr,int len){
        for(int gap=len/2;gap>0;gap/=2){
            for(int i=gap;i<len;i++){
                int temp=arr[i];    //待插入数据
                int j;
                for(j=i-gap;j>=0;j-=gap){
                    if(arr[j]>temp){
                        //后移gap步长
                        arr[j+gap]=arr[j];
                    }else{
                        break;
                    }
                }
                arr[j+gap]=temp;
            }
        }
    }

    //希尔排序【交换法】 效率慢，不推荐
    public static void shellSort(int[] arr,int len){
       for(int gap=len/2;gap>0;gap/=2){
           for(int i=gap;i<len;i++){
               for(int j=i-gap;j>=0;j-=gap){
                   if(arr[j]>arr[j+gap]){
                       arr[j]^=arr[j+gap];
                       arr[j+gap]^=arr[j];
                       arr[j]^=arr[j+gap];
                   }
               }
           }
       }
    }


}
