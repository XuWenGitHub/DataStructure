package test;

/**
 * @PackgeName: test
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/16 12:35
 * Introduce:   这个是二叉树
 */
public class Node implements Comparable<Node>{
    public Byte data;   //表示数据
    public Integer num; //表示这个数据多少个，最后要通过这个排序
    public Node left;
    public Node right;

    //构造器
    public Node(Byte data,Integer num){
        this.data = data;
        this.num = num;
    }

    @Override
    public int compareTo(Node node) {
        return this.num-node.num;   //从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", num=" + num +
                '}';
    }

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
