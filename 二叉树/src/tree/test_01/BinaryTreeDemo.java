package tree.test_01;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //需要先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        System.out.println("前序遍历");//1,2,3,5,4
//        binaryTree.perOrder();
//
//        System.out.println("中序遍历");//2,1,5,3,4
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");//2,5,4,3,1
//        binaryTree.postOrder();
//
//        //前序查找
//        System.out.println("前序查找方式~~~");
//        HeroNode heroNode = binaryTree.perSearch(5);
//        System.out.println(heroNode);
//
//        //中序查找
//        System.out.println("中序查找方式~~~");
//        HeroNode heroNode1 = binaryTree.infixSearch(5);
//        System.out.println(heroNode1);
//
//        //后序查找
//        System.out.println("后序查找方式~~~");
//        HeroNode heroNode2 = binaryTree.postSearch(5);
//        System.out.println(heroNode2);

        //测试删除节点
        System.out.println("删除前，前序遍历");
        binaryTree.perOrder();
        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.perOrder();
    }
}
