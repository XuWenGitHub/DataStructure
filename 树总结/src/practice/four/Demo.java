package practice.four;

/**
 * @PackgeName: practice.four
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/8/26 15:58
 * Introduce:
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree= new BinarySortTree();
        for(int i:arr){
            binarySortTree.add(new Node(i));
        }
        binarySortTree.infixOrder();
        System.out.println(binarySortTree.getRoot());
//        //测试查找val的父节点和查找val元素
//        System.out.println(binarySortTree.findNode(12));
//        System.out.println(binarySortTree.findParentNode(1));

       // binarySortTree.delNode(12);
//        binarySortTree.infixOrder();
       // binarySortTree.delNode(3);
        //binarySortTree.infixOrder();
//        binarySortTree.delNode(5);
//        binarySortTree.infixOrder();

//        binarySortTree.delNode(9);
//        binarySortTree.infixOrder();
//        binarySortTree.delNode(10);
//        binarySortTree.infixOrder();
        //binarySortTree.delNode(1);

       // binarySortTree.infixOrder();
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
        binarySortTree.delNode(9);
        binarySortTree.delNode(10);
        binarySortTree.infixOrder();
        binarySortTree.delNode(12);
        binarySortTree.infixOrder();
        System.out.println(binarySortTree.getRoot());
    }
}
