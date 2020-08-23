package practice.avl;

/**
 * @PackgeName: practice.avl
 * @ClassName: AVLTreeDemo
 * @Author: XuWen
 * Date: 2020/8/23 13:20
 * Introduce:
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = {10,11,7,6,8,9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        for(int i:arr){
            avlTree.add(new Node(i));
        }
        //遍历
        avlTree.infixOrder();
        System.out.println("在没有旋转处理前");
        System.out.println("树的高度="+avlTree.getRoot().height());
        System.out.println("树的左子树高度="+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度="+avlTree.getRoot().rightHeight());

        System.out.println(avlTree.getRoot().left);
        System.out.println(avlTree.getRoot().right);
    }
}
