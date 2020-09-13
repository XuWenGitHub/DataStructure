package practice.greedy;

import java.util.HashSet;

/**
 * @PackgeName: practice.greedy
 * @ClassName: Test
 * @Author: XuWen
 * Date: 2020/9/8 21:55
 * Introduce:
 */
public class Test {
    public static void main(String[] args) {
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet1.add("1");
        hashSet1.add("2");
        hashSet1.add("100");

        hashSet2.add("1");
        hashSet2.add("2");
        hashSet2.add("200");
        hashSet1.retainAll(hashSet2);   //把hashSet1变成两个集合的交集
        System.out.println(hashSet1);
        System.out.println(hashSet2);

    }
}
