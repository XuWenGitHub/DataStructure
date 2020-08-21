package practice.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//创建节点类
//为了让Node 对象支持排序Collections集合排序
//让Node实现Comparavle接口
class Node implements Comparable<Node>{
    public int value;   //节点的权值
    public Node left;   //左子节点
    public Node right;  //右子节点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value-node.value;
    }

    //写一个前序遍历
    public void prevPrint(){
        System.out.print(this.value+" ");
        if(this.left!=null){
            this.left.prevPrint();
        }
        if(this.right!=null){
            this.right.prevPrint();
        }
    }
}
/**
 * @PackgeName: practice.huffmantree
 * @ClassName: HuffmanTree
 * @Author: XuWen
 * Date: 2020/8/11 15:55
 * Introduce:
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        preOrder(creatHuffmanTree2(arr));
        System.out.println();
        preOrder(creatHuffmanTree(arr));
    }
    public static void preOrder(Node root){
        if(root==null){
            System.out.println("空树不能遍历");
            return;
        }
        root.prevPrint();
    }

    /**
     * 创建赫夫曼树的方法
     * @param arr   待转成赫夫曼树的数组
     * @return  返回赫夫曼树的根节点
     */
    public static Node creatHuffmanTree(int[] arr){
        //1.遍历arr数组
        //2.将arr的每个元素构建成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for(int value:arr){
            nodes.add(new Node(value));
        }

        //我们处理的过程是一个重复的过程,每次remove2个，add1个
        while(nodes.size()>1) {
            //排序,从小到大
            Collections.sort(nodes);

            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }

        //返回赫夫曼树的root节点
        return nodes.get(0);
    }

    /*
    分析：
    A：先建造一个ArrayList集合，把arr装进去，
    B：然后对ArrayList进行排序，排序好把前两个取出来，构成一个二叉树，然后把根节点添加进ArrayList里面，
    C：知道ArrayList里面只有一个节点，那就是root节点，最后返回
     */
    public static Node creatHuffmanTree2(int[] arr){
        List<Node> nodes = new ArrayList<>();
        for(int i:arr){
            nodes.add(new Node(i));
        }

        while(nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node node = new Node(leftNode.value+rightNode.value);
            node.left = leftNode;
            node.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(node);
        }
        return nodes.get(0);
    }
}
