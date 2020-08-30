package practice.four;

/**
 * @PackgeName: practice.four
 * @ClassName: BinarySortTree
 * @Author: XuWen
 * Date: 2020/8/26 16:02
 * Introduce:
 */
public class BinarySortTree {
    private Node root;

    public Node getRoot(){
        return root;
    }

    public BinarySortTree(){}
    //判断二叉排序树是否为空
    public boolean isNull(){
        return root==null;
    }
    //二叉排序树添加元素
    public void add(Node node){
        if(isNull()){
            root = node;
        }else{
            root.add(node);
        }
    }

    //找某个节点，返回查找的节点，没查找到返回null
    public Node findNode(int val){
        if(isNull()){
            return null;
        }
        return root.findNode(val);
    }

    //查找某个节点的父节点，没有找到返回null
    public Node findParentNode(int val){
        if(isNull()||root.val==val){
            return null;
        }
        return this.root.findParentNode(val);
    }

    //以node为根节点，删除该右子树最小的节点，并返回该值
    public int delNodeLeftMin(Node node){
        Node cur = node;
        while(cur.left!=null){
            cur = cur.left;
        }
        delNode(cur.val);
        return cur.val;
    }

    //二叉排序树的删除元素
    /*
    有三种情况：
    （1）删除叶子结点   只需要找到待删除节点和待删除节点的父节点
    （2）删除只有一颗子树的节点  有四种情况
    （3）删除有两颗子树的节点   找到待删除节点，然后找其右子树中最小的，删除其最小的，然后返回值，然后带删除节点值一替换即可
     */
    public void delNode(int val){
        Node node = findNode(val);  //待删除节点
        if(node==null){
            System.out.println("没有找到待删除节点");
            return;
        }
        Node parentNode = findParentNode(val);  //待删除节点的父节点
        if(node.left==null&&node.right==null){  //删除叶子结点
            if(parentNode==null){   //删除的是根节点,并且只有这一个节点
                root=null;
            }else{
                if(parentNode.left==node){
                    parentNode.left=null;
                }else{
                    parentNode.right=null;
                }
            }
        }else if(node.left!=null&&node.right!=null){    //删除有两颗子树的节点
            node.val = delNodeLeftMin(node.right);  //把待删除节点的右子树权值中最小的删除，并返回其值
        }else{//删除有一颗子树的节点
            if(node.left!=null){    //删除的只有左子节点
                if(parentNode!=null){   //表示删除的不是root
                    if(parentNode.left==node){
                        parentNode.left = node.left;
                    }else{
                        parentNode.right= node.left;
                    }
                }else {
                    root = node.left;
                }
            }else{  //删除的只有右子节点
                if(parentNode!=null){   //表示删除的不是root
                    if(parentNode.left==node){
                        parentNode.left = node.right;
                    }else{
                        parentNode.right = node.right;
                    }
                }else{
                    root = root.right;
                }
            }
        }
    }

    //二叉排序树遍历
    public void infixOrder(){
        if(isNull()){
            System.out.println("链表为空~~");
        }else{
            root.infixOrder();
            System.out.println();
        }
    }
}
