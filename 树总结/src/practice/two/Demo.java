package practice.two;

/**
 * @PackgeName: practice.two
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/8/26 11:25
 * Introduce:
 */
public class Demo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree(node1);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;

        threadBinaryTree.threadNode();
        threadBinaryTree.preOrder();
        System.out.println("10的前驱节点："+node5.left+" leftType:"+node5.leftType);
        System.out.println("10的后继节点："+node5.right+" rightType:"+node5.rightType);
    }
}
