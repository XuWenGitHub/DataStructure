package practice.five;

/**
 * @PackgeName: practice.five
 * @ClassName: AVLTree
 * @Author: XuWen
 * Date: 2020/8/30 13:54
 * Introduce:AVL树
 */
public class AVLTree {
    private Node root;
    public AVLTree(){
    }
    //返回root根节点
    public Node getRoot(){
        return root;
    }

    //添加元素
    public void add(Node node){
        if(root==null){
            root=node;
        }else {
            root.add(node);
        }
    }
    //查找某个节点
    public Node findNode(int val){
        if(root==null){
            return null;
        }
        return root.findNode(val);
    }
    //查找某个节点的父节点
    public Node findParentNode(int val){
        if(root==null){
            return null;
        }
        return root.findParentNode(val);
    }
    //查某该节点右子树中最小的，删除该节点，并返回值
    public int delRightNodeMinVal(Node node){
        Node cur = node;
        while(cur.left!=null){
            cur = cur.left;
        }
        delNode(cur.val);
        return cur.val;
    }

    //删除某个节点
    //1.删除叶子结点 2.删除只有一颗子树的节点 3.删除有两颗子树的节点
    public void delNode(int val){
        if(root==null){
            System.out.println("AVL树为空");
            return;
        }
        Node target = findNode(val);    //待删除节点
        if(target==null){
            System.out.println("没有找到待删除节点");
            return;
        }
        Node parent = findParentNode(val);  //待删除节点的父节点
        if(target.left==null&&target.right==null){  //删除叶子结点
            //先要判断待删除的叶子结点，是否就是root节点
            if(root==target){
                root=null;
            }else{
                if(parent.left==target){
                    parent.left=null;
                }else{
                    parent.right=null;
                }
            }
        }else if(target.left!=null&&target.right!=null){    //删除有两颗子树的节点
            int min = delRightNodeMinVal(target.right);
            target.val = min;
        }else{  //删除只有一颗子树的节点
            //先判断待删除节点，是有左子树还是右子树
            if(target.left!=null){  //待删除的节点是有左子树
                if(parent!=null){   //待删除的节点不是根节点
                    if(parent.left==target) {
                        parent.left = target.left;
                    }else{
                        parent.right = target.left;
                    }
                }else{  //待删除的节点是根节点
                    root = root.left;
                }
            }else{  //待删除的节点是有右子树
                if(parent!=null){
                    if(parent.left==target){
                        parent.left = target.right;
                    }else{
                        parent.right = target.right;
                    }
                }else{
                    root = root.right;
                }
            }
        }
    }

    //中序输出
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
            System.out.println();
        }
    }
}
