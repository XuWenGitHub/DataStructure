package practice.two;

/**
 * @PackgeName: practice.two
 * @ClassName: ThreadBinaryTree
 * @Author: XuWen
 * Date: 2020/8/26 11:10
 * Introduce:
 */
public class ThreadBinaryTree {
    private Node root;
    private Node pre=null;   //每次线索化二叉树的时候，保存前驱节点

    public ThreadBinaryTree(Node root){
        this.root = root;
    }


    public void threadNode(){
        threadNode(root);
    }
    //对二叉树进行前序线索化的方法
    private void threadNode(Node node){
        if(node==null){
            return;
        }
        if(node.left==null){    //线索化当前节点的前驱
            node.left=pre;
            node.leftType = 1;
        }
        if(pre!=null&&pre.right==null){ //线索化pre节点的后继
            pre.right=node;
            pre.rightType=1;
        }
        pre=node;
        //线索化左子树
        if(node.left!=null&&node.leftType==0){
            threadNode(node.left);
        }
        //线索化右子树
        if(node.right!=null&&node.rightType==0){
            threadNode(node.right);
        }
    }
    //对二叉树进行中序线索化
    public void threadInfixNode(Node node){
        //线索化左子树
        if(node.left!=null&&node.rightType==0){
            threadInfixNode(node.left);
        }
        //线索化node的前驱节点
        if(node.left==null){
            node.left=pre;
            node.leftType=1;
        }
        //线索化pre的后继节点
        if(pre!=null&&pre.right==null){
            pre.right = node;
            pre.rightType=1;
        }
        pre=node;   //前驱移动
        //线索化右子树
        if(node.right!=null&&node.rightType==0){
            threadInfixNode(node.right);
        }
    }
    //前序遍历
    public void preOrder(){
        if(root==null){
            System.out.println("空树");
        }else {
            root.preOrder();
        }
    }
}
