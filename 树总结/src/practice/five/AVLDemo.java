package practice.five;

/**
 * @PackgeName: practice.five
 * @ClassName: AVLDemo
 * @Author: XuWen
 * Date: 2020/8/30 14:18
 * Introduce:
 */
public class AVLDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] arr = {10,3,-1,213,4,57,2,5,2};
        for(int i:arr){
            avlTree.add(new Node(i));
        }
//        avlTree.infixOrder();
//        avlTree.delNode(-1);
//        avlTree.infixOrder();
//        avlTree.delNode(213);
//        avlTree.infixOrder();
//        avlTree.delNode(5);
//        avlTree.infixOrder();
//        avlTree.delNode(2);
//        avlTree.infixOrder();

        avlTree.infixOrder();
        //System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}
