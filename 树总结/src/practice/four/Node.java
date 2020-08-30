package practice.four;

/**
 * @PackgeName: practice.four
 * @ClassName: Node
 * @Author: XuWen
 * Date: 2020/8/26 15:57
 * Introduce:
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    //构造器
    public Node(int val){
        this.val = val;
    }

    //添加元素
    public void add(Node node){
        if(node.val<this.val){
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
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.print(this+" ");
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

    //查找val的父节点
    public Node findParentNode(int val){
        if((this.left!=null&&this.left.val==val)||(this.right!=null&&this.right.val==val)){
            return this;
        }else{
            if(val<this.val&&this.left!=null){
                return this.left.findParentNode(val);
            }else if(val>this.val&&this.right!=null){
                return this.right.findParentNode(val);
            }else{
                return null;    //表示没有找到
            }
        }
    }


    //中序查找
    public Node findNode(int val){
        if(val<this.val){
            if(this.left==null){
                return null;
            }else{
                return this.left.findNode(val);
            }
        }else if(val>this.val){
            if(this.right==null){
                return null;
            }else{
                return this.right.findNode(val);
            }
        }else{
            return this;
        }
    }
}
