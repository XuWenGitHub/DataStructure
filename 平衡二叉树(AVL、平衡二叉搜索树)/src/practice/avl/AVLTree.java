package practice.avl;

/**
 * @PackgeName: practice.avl
 * @ClassName: AVLTree
 * @Author: XuWen
 * Date: 2020/8/23 13:59
 * Introduce:
 */
public class AVLTree {
    private Node root;

    public Node getRoot(){
        return this.root;
    }

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

    //返回以node为根节点的排序树的最小节点的值
    //并删除该节点
    public int delRightTreeMin(Node node){
        Node target = node;
        while(target.left!=null){
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除节点
    //1.删除叶子结点 2.删除只有一颗子树的节点
    //3.删除有两颗子树的节点
    public void delNode(int value){
        if(root==null){
            return;
        }
        Node targetNode = search(value);    //待删除节点
        if(targetNode==null){
            return; //表示没有找到待删除的节点
        }
        if(root.left==null&&root.right==null){
            root=null;
            return;
        }
        //找到待删除节点的父节点
        Node parent = searchParent(value);
        if(targetNode.left==null&&targetNode.right==null){
            //删除叶子结点
            if(parent.left!=null&&parent.left==targetNode){
                parent.left=null;
            }else if(parent.right!=null&&parent.right==targetNode){
                parent.right=null;
            }
        }else if(targetNode.left!=null&&targetNode.right!=null){
            //删除有两颗子树的节点
            int minVal = delRightTreeMin(targetNode.right); //找到待删除节点的右子树的最小节点，并删除最小节点
            targetNode.value = minVal;  //然后把最小节点的值赋值给待删除节点
        }else{
            //删除只有一颗子树的节点
            if(targetNode.left!=null){//删除的节点只要左子节点
                if(parent!=null){
                    if(parent.left==targetNode){
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.left;
                    }
                }else{
                    root = targetNode.left;
                }
            }else{  //删除的节点只有右子节点
                if(parent!=null){
                    if(parent.left==targetNode){
                        parent.left = targetNode.right;
                    }else {
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
            root=node;
        }else{
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root==null){
            System.out.println("空树~~~");
        }else{
            root.infixOrder();
            System.out.println();
        }
    }
}
