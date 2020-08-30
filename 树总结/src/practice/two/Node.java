package practice.two;

/**
 * @PackgeName: practice.two
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/26 11:05
 * Introduce:   线索化二叉树中节点
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    //线索化二叉树
    public int leftType;   //0表示正常，1表示指向前驱节点
    public int rightType;

    //构造器
    public Node(int val){
        this.val =val;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(val);
        if(left!=null&&leftType==0){
            this.left.preOrder();
        }
        if(right!=null&&rightType==0){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
