package tree.test_01;
//二叉树
public class BinaryTree {
    private HeroNode root;  //根节点

    public void setRoot(HeroNode root){
        this.root=root;
    }

    //递归删除节点
    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else {
                root.delNode(no);
            }
        }else{
            System.out.println("空数，不能删除~~~");
        }
    }

    //前序遍历
    public void perOrder(){
        if(this.root!=null){
            this.root.perOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序查找
    public HeroNode perSearch(int no){
        if(root!=null){
            return root.perSearch(no);
        }else{
            return null;
        }
    }
    //中序查找
    public HeroNode infixSearch(int no){
        if(root!=null){
            return root.infixSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postSearch(int no){
        if(root!=null){
            return root.postSearch(no);
        }else{
            return null;
        }
    }
}
