package practice.binarysorttree;

/**
 * @PackgeName: practice.binarysorttree
 * @ClassName: BinarySortTree
 * @Author: XuWen
 * Date: 2020/8/22 13:25
 * Introduce:
 */
public class BinarySortTree {
    private Node root;

    //查找待删除的节点
    public Node search(int value){
        if(root==null){
            return null;
        }else{
            return root.search(value);
        }
    }
    //查找父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /**
     * 返回以node为根节点的二叉排序树的最大节点的值，并且删除该最大节点的值的节点
     * @param node  传入的节点（当做二叉排序树的根节点)
     * @return  返回以node为根节点的二叉排序树的最大节点的值
     */
    public int delLeftTreeMax(Node node){
        Node target = node;
        while(target.right!=null){
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }
    /**
     * 返回以node为根节点的二叉排序树的最小节点的值，删除以node为根节点的二叉排序树的最小节点的值节点
     * @param node  传入的节点(当做二叉排序树的根节点)
     * @return  返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while(target.left!=null){
            target = target.left;
        }
        //这是target就指向了最小节点
        delNode(target.value);  //删掉了最小节点
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if(root==null){
            return;
        }
        //找到待删除的节点 targetNode
        Node targetNode = search(value);
        //如果没有找到要删除的节点
        if(targetNode==null){
            return;
        }
        //如果我们发现当前这颗二叉排序树只有一个节点
        if(root.left==null&&root.right==null){
            root=null;
            return;
        }
        //去找到targetNode的父节点
        Node parent = searchParent(value);


        if(targetNode.left==null&&targetNode.right==null){//如果要删除的节点是叶子结点
            //判断targetNode是父节点左子节点还是右子节点
            if(parent.left==targetNode){
                parent.left=null;
            }else if(parent.right==targetNode){
                parent.right = null;
            }
        }else if(targetNode.left!=null&&targetNode.right!=null){//删除有两颗子树的节点
//
//            int minVal = delRightTreeMin(targetNode.right);
//            targetNode.value = minVal;
            //用左子树的最大值，替换当前待删除的节点
            int maxVal = delLeftTreeMax(targetNode.left);
            targetNode.value = maxVal;
        }else{//删除只有一颗子树的节点
            //如果要删除的节点有左子节点
            if(targetNode.left!=null){
                if(parent!=null) {  //因为如果删除的根节点，这里parent就为null，就会出空指针异常，所以要判断
                    if (parent.left == targetNode) {    //判断待删除节点是其父节点的左子节点还是右子节点
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                }else{
                    root = targetNode.left;
                }
            }else{  //删除的节点只有右子节点
                if(parent!=null) {
                    if (parent.left == targetNode) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
        }

    }

    //添加节点的方法
    public void add(Node node){
        if(root==null){
            root = node;
        }else{
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
            System.out.println();
        }else{
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;
    //构造器
    public Node(int value){
        this.value = value;
    }


    /**
     * 查找要删除的节点
     * @param value 希望删除的节点的值
     * @return  如果找到返回该节点，否则返回null
     */
    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){    //说明找不到了
                return null;
            }
            return this.left.search(value);
        }else{
            if(this.right==null){   //说明找不到了
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要查找的节点的值
     * @return  返回的是要删除节点的父节点,如果没有，就返回Null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，就返回
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else{
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为null，就左递归
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);  //向右子树递归查找
            }else {
                return null;    //没有找到父节点
            }
        }
    }

    //添加节点的方法
    //递归的形式添加节点，需要满足二叉排序树的要求
    public void add(Node node){
        if(node==null){
            return;
        }
        //判断传入的节点的值和当前子树的根节点的值关系
        if(node.value<this.value){
            if(this.left==null){
                this.left = node;
            }else{
                this.left.add(node);    //递归的向左子树添加
            }
        }else{  //添加的节点的值大于等于当前的值
            if(this.right==null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    //前序遍历
    public void preOrder(){
        System.out.print(this.value+" ");
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
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
    //后序遍历
}
