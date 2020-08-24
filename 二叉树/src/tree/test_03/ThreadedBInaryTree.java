package tree.test_03;

public class ThreadedBInaryTree {
    private Node root;  //根节点
    //为了实现线索化需要创建一个指向当前节点的前驱结点的一个变量
    //在递归进行线索化时，pre总是保留前一个节点
    private Node pre=null;

    public void setRoot(Node root){
        this.root=root;
    }

    public void threadNodes3(){
        threadNodes3(root);
    }
    //对二叉树进行后序线索化的方法
    public void threadNodes3(Node node){
        if(node==null){
            return;
        }
        //先对左子树线索化
        if(node.getLeft()!=null&&node.getLeftType()==0){
            threadNodes3(node.getLeft());
        }
        //右子树线索化
        if(node.getRight()!=null&&node.getRightType()==0){
            threadNodes3(node.getRight());
        }
        //当前线索化
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
    }


    public void threadNodes2(){
        this.threadNodes2(root);
    }
    //对二叉树进行前序线索化的方法
    public void threadNodes2(Node node){
        if(node==null){
            return;
        }
        //先线索化当前节点
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        //线索化左子树
        if(node.getLeft()!=null&&node.getLeftType()==0){
            threadNodes2(node.getLeft());
        }
        //线索化右子树
        if(node.getRight()!=null&&node.getRightType()==0){
            threadNodes2(node.getRight());
        }
    }


    public void threadNodes(){
        this.threadNodes(root);
    }
    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node  就是当前需要线索化的节点
     */
    public void threadNodes(Node node){
        if(node==null){
            return;
        }
        //先线索化左子树
        if(node.getLeft()!=null){
            threadNodes(node.getLeft());
        }

        //线索化当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft()==null){
            //让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if(pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针，指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //!!!很重要 每处理一个节点，让当前节点pre是下一个节点的前驱节点
        pre=node;

        //线索化右子树
        if(node.getRight()!=null){
            threadNodes(node.getRight());
        }
    }

    //遍历线索化二叉树的方法
    public void threadList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        Node node = root;
        while(node!=null){
            //循环找到leftType==1的节点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType()==1){
                //获取到当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node=node.getRight();
        }
    }
    //后序遍历，递归版
    public void postOrder(){
        if(root==null){
            System.out.println("树为空，不能遍历");
        }else{
            root.postOrder();
        }
    }
    //前序遍历，递归版
    public void preOrder(){
        if(root==null){
            System.out.println("树为空，不能遍历");
        }else{
            root.preOrder();
        }
    }
    //中序遍历,递归版
    public void infixOrder(){
        if(root==null){
            System.out.println("树为空，不能遍历");
        }else{
            root.infixOrder();
        }
    }
}
