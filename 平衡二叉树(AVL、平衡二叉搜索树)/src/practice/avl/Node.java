package practice.avl;

/**
 * @PackgeName: practice.avl
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/23 13:20
 * Introduce:
 */
public class Node {
    public int value;
    Node left;
    Node right;
    //构造器
    public Node(int value){
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }else{
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }else{
            return right.height();
        }
    }

    //返回以当前节点为根节点的树高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //左旋转的方法    （这是右边的树高度比左边的树高度高）
    private void leftRotate(){
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(this.value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //把新的节点的右子树设置成当前节点右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置为新的节点
        this.left = newNode;
    }

    //右旋转   （这是左边的树高度比右边数高度高）
    private void rightRotate(){
        Node newNode = new Node(this.value);    //先以当前节点创建一个新的节点
        newNode.right = this.right; //新的节点的右子节点指向当前节点的右子节点
        newNode.left = this.left.right; //新的节点的左子节点指向当前节点的左子节点的右子节点
        this.value = this.left.value;   //当前节点的值变成为左子节点的值
        this.left = this.left.left; //现在当前节点指向左子节点的左子节点
        this.right = newNode;   //当前右子节点指向新的节点
    }

    //寻找一个节点
    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;    //说明找不到
            }
            return this.left.search(value);
        }else{
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //寻找一个节点的父节点
    public Node searchParent(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else{
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else{
                return null;    //说明没有找到父节点
            }
        }
    }

    //添加节点
    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value<this.value){
            if(this.left==null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right==null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }

        //当添加完一个节点后，如果：(右子树的高度-左子树的高度)>1
        if(rightHeight()-leftHeight()>1){
            //如果它的右子树的左子树高度大于它的右子树的右子树的高度
            if(right!=null&&right.leftHeight()>right.rightHeight()){
                //先对右子树进行右旋转
                this.right.rightRotate();
                //再对当前节点进行左旋转
            }
            leftRotate();
            return; //必须要
        }

        //当添加完一个节点后，如果：（左子树的高度-右子树的告诉）>1，右旋转
        if(leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                //先对当前节点的左子树进行左旋钻
                this.left.leftRotate();
                //再对当前节点进行右旋转
            }
            rightRotate();
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.print(this.value+" ");
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
