package practice.kmp;

import java.util.Arrays;

/**
 * @PackgeName: practice.kmp
 * @ClassName: KMPAlgorithm
 * @Author: XuWen
 * Date: 2020/9/8 18:25
 * Introduce:KMP算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str = "ABCDABD";
        int[] next = kmpNext(str);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1,str,next));
    }


    /**
     * //写出我们的kmp搜索算法
     * @param str1 源字符串
     * @param str2  子串
     * @param next  子串的部门匹配表
     * @return 匹配成功后，返回第一个匹配的位置,没有匹配成功，返回-1
     */
    //"BBC ABCDAB ABCDABCDABDE"
    //"ABCDABD"
    //[0, 0, 0, 0, 1, 2, 0]
    public static int kmpSearch(String str1,String str2,int[] next){

        //遍历大串 i指向str1  j指向str2
        for(int i=0,j=0;i<str1.length();i++){

            //需要处理当str1.charAt(i)!=str2.charAt(j),去调整j的大小
            //kmp算法核心点
            while (j>0&&str1.charAt(i)!=str2.charAt(j)){    //表示没有匹配上，那么就需要j根据匹配表往后移
                j=next[j-1];
            }

            if(str1.charAt(i)==str2.charAt(j)){ //表示目前匹配成功
                j++;
            }
            if(j==str2.length()){   //找到了
                return i-j+1;
            }

        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0]=0;  //如果字符串长度为1的话，那么部分匹配值是0
        //注意j表示字符串的头，i表示字符串的尾部，[j,i]表示一个字符串，然后找到其最大的前缀后缀一样的长度
        for(int i=1,j=0;i<dest.length();i++){
            //当dest.charAt(i)!=dest.charAt(j)，我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i)==dest.charAt(j)成立我们才退出
            //这是kmp算法的核心点
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }
            //当这个条件满足时，部分匹配值就是要+1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
