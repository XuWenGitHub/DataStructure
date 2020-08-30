package practice.three;

/**
 * @PackgeName: practice.three
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/26 12:55
 * Introduce:
 */
public class Node implements Comparable<Node>{
    public Byte data;   //存入数据的本身
    public Integer weight;  //权值，表示数据的个数
    public Node left;
    public Node right;

    //构造器
    public Node(Byte data,Integer weight){
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        //因为我们需要wpl小，所以把出现次数小的放在树的最下面
        //从小到大排序
        return this.weight-node.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
