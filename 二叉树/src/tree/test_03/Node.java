package tree.test_03;

public class Node {
    private int value;
    private Node left;
    private Node right;
    //说明：
    //1.如果leftType==0表示指向左子树，如果1则表示指向前驱节点
    //2.如果rightType==0表示指向右子树，如果1则表示指向后继节点
    private int leftType;
    private int rightType;

    public Node(int value){
        this.value=value;
    }
    //后序遍历
    public void postOrder(){
        if(this.getLeft()!=null&&this.getLeftType()==0){
            this.getLeft().postOrder();
        }
        if(this.getRight()!=null&&this.getRightType()==0){
            this.getRight().postOrder();
        }
        System.out.println(this);
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.getLeft()!=null&&this.getLeftType()==0){
            this.left.preOrder();
        }
        if(this.getRight()!=null&&this.getRightType()==0){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //左递归
        if(this.left!=null&&this.getLeftType()==0){
            this.left.infixOrder();
        }
        System.out.println(this);
        //右递归
        if(this.right!=null&&this.getRightType()==0){
            this.right.infixOrder();
        }
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
