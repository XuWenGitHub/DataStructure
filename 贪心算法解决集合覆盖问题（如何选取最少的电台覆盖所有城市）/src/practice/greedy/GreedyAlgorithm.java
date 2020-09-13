package practice.greedy;

import java.util.*;

/**
 * @PackgeName: practice.greedy
 * @ClassName: GreedyAlgorithm
 * @Author: XuWen
 * Date: 2020/9/8 21:29
 * Introduce:
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到Map里面
        //key:字符串  value:hashSet
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("杭州");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //allAreas存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        //遍历一下hashmap，拿到所有的地区
        for(Map.Entry<String,HashSet<String>> entry:broadcasts.entrySet()){
            HashSet<String> hashSet = entry.getValue();
            allAreas.addAll(hashSet);
        }
        //测试一下所有的地区
        //System.out.println(allAreas);

        //创建ArrayList，存放选择的电台集合
        List<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey，它保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey，不会null，则会加入到selects里面
        String maxKey = null;
        while(allAreas.size()!=0){  //如果allAreas不为0，则表示还没有覆盖到所有的地区
            //每进行一个while，需要置空maxKey
            maxKey = null;

            //遍历broadcasts,取出对应的key
            //从所有电台中找到最大覆盖地区的key
            for(String key:broadcasts.keySet()){
                //每进行一个for，要把tempSet.claer();
                tempSet.clear();

                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);//把tempSet和allAreas取交集，然后给tempSet，知道当前key覆盖没有覆盖的地区
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合的地区还多
                //就需要重置maxKey
                //tempSet.size()>broadcasts.get(maxKey).size() 体现出贪心算法的特点，每次都选择最优的
                if(tempSet.size()>0&&(maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey!=null,就应该将maxKey，加入selects,加入当前选择的电台，然后再把需要覆盖的地区中去掉当前电台覆盖的地区
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从allAreas里面清除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果："+selects);
    }
}
