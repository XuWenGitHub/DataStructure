package practice.huffmancode;

/**
 * @PackgeName: practice.huffmancode
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/15 15:44
 * Introduce:
 */
public class Node implements Comparable<Node>{
    public Byte data;   //存入数据本身，存的ascll
    public Integer weight;  //权值，表示数据出现的次数
    public Node left;
    public Node right;

    public Node(Byte data,Integer weight){
        this.data = data;
        this.weight = weight;
    }
    public Node(int weight){
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
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
