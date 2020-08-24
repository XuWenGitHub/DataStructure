package tree.test_01;

public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //构造器
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //递归删除节点
    //如果删除的是叶子结点，则删除该节点
    //如果删除的是非叶子结点，则删除该子树
    public void delNode(int no){
        if(this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        //向左递归
        if(this.left!=null){
            this.left.delNode(no);
        }
        //向右递归
        if(this.right!=null){
            this.right.delNode(no);
        }

    }

    //前序查找的方法
    public HeroNode perSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if(this.no==no){
            return this;
        }
        //1.则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到节点，则返回
        HeroNode resultNode=null;
        if(this.left!=null){
            resultNode=this.left.perSearch(no);
        }
        if(resultNode!=null){
            return resultNode;  //说明找到了
        }
        if(this.right!=null){
            resultNode=this.right.perSearch(no);
        }
        return resultNode;    //表示没有找到当前节点
    }
    public HeroNode infixSearch(int no){
        HeroNode resultNode = null;
        //向左递归
        if(this.left!=null){
            resultNode = this.left.infixSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        System.out.println("进入中序遍历");
        if(this.no==no){
            return this;
        }
        //向右递归
        if(this.right!=null){
            resultNode = this.right.infixSearch(no);
        }
        return resultNode;
    }
    //后序遍历的查找
    public HeroNode postSearch(int no){
        //右递归
        HeroNode resultNode = null;
        if(this.left!=null){
            resultNode = this.left.postSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        //左递归
        if(this.right!=null){
            resultNode = this.right.postSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        //中
        System.out.println("后序遍历");
        if(this.no==no){
            return this;
        }
        return null;
    }
    //编写前序遍历的方法
    public void perOrder(){
        System.out.println(this);   //先输出父节点
        //递归向左子树
        if(this.left!=null){
            this.left.perOrder();
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.perOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
}

