package tree.test_03;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);
        ThreadedBInaryTree threadedBInaryTree = new ThreadedBInaryTree();
        threadedBInaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //threadedBInaryTree.threadNodes();//中序线索化
        //threadedBInaryTree.threadList();
        //threadedBInaryTree.infixOrder();

        //threadedBInaryTree.threadNodes2();//前序线索化
        //threadedBInaryTree.preOrder();

        threadedBInaryTree.threadNodes3();//后序线索化
        threadedBInaryTree.postOrder();
        System.out.println("10号节点的前驱结点是="+node5.getLeft());
        System.out.println("10号节点的后继节点是="+node5.getRight());
        System.out.println("8号节点的前驱节点是="+node4.getLeft());
        System.out.println("8号节点的后继节点是="+node4.getRight());
    }
}

