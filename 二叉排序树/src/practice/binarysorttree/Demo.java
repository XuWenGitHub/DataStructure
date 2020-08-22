package practice.binarysorttree;

/**
 * @PackgeName: practice.binarysorttree
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/8/22 13:36
 * Introduce:
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for(int i:arr){
            binarySortTree.add(new Node(i));
        }
        //中序遍历二叉排序树
        binarySortTree.infixOrder();
        //binarySortTree.delNode(2);  //测试删除叶子结点
        //binarySortTree.delNode(1);  //测试删除只有一颗子树的节点
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}
