package practice.five;

/**
 * @PackgeName: practice.five
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/30 13:44
 * Introduce:AVL树（平衡二叉树，二叉搜索树）
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    //构造器
    public Node(int val){
        this.val = val;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(this.left==null){
            return 0;
        }else{
            return this.left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight(){
        if(this.right==null){
            return 0;
        }else{
            return this.right.height();
        }
    }

    //返回以当前节点为根节点的树高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //左旋转的方法（这是右子树的高度比左子树的高度高)
    private void leftRotate(){
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(this.val);
        //把新的节点的左子树设置为当前节点的总字数
        newNode.left  = this.left;
        //把新的新的节点的右子树设置成当前节点右子树的左子树
        newNode.left = this.right.left;
        //把当前节点值替换成右子节点的值
        this.val = this.right.val;
        //把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //把当前几点的左子树设置成新的节点
        this.left = newNode;
    }

    //右旋转（这是左边的树高度比右边树高度高）
    private void rightRotate(){
        Node newNode = new Node(this.val);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.val = this.left.val;
        this.left = this.left.left;
        this.right = newNode;
    }

    //添加元素
    //如果添加的元素的值有重复的，添加到右子树
    public void add(Node node){
        if(node.val<this.val){
            if(this.left!=null){
                this.left.add(node);
            }else{
                this.left = node;
            }
        }else{
            if(this.right!=null){
                this.right.add(node);
            }else{
                this.right = node;
            }
        }

        //当添加完一个节点，如果（右子树的高度-左子树的高度）>1：左旋转
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

        //当添加完一个节点后，如果：（左子树的高度-右子树的高度）>1:右旋转
        if(leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                //先对当前节点的左子树进行左旋转
                this.left.leftRotate();
                //再对当前节点进行右旋转
            }
            rightRotate();
        }
    }

    //查找某个节点的父节点
    public Node findParentNode(int val){
        if((this.left!=null&&this.left.val==val)||(this.right!=null&&this.right.val==val)){
            return this;
        }
        if(this.val>val&&this.left!=null){
            return this.left.findParentNode(val);
        }else if(this.val<val&&this.right!=null){
            return this.right.findParentNode(val);
        }else{
            return null;
        }
    }

    //查找一个节点
    public Node findNode(int val){
        if(this.val<val){
            if(this.right!=null){
                return this.right.findNode(val);
            }else {
                return null;
            }
        }else if(this.val>val){
            if(this.left!=null){
                return this.left.findNode(val);
            }else{
                return null;
            }
        }else {
            return this;
        }
    }

    //中序排序
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.print(this.val+" ");
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
