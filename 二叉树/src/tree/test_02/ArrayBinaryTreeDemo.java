package tree.test_02;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //arrayBinaryTree.preOrder();
        //arrayBinaryTree.infixOrder();
        arrayBinaryTree.postOrder();
    }
}
//ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrayBinaryTree{
    private int[] arr;  //存储数据节点的数组

    //构造器
    public ArrayBinaryTree(int[] arr){
        this.arr=arr;
    }

    public void postOrder(){
        this.postOrder(0);
    }
    public void postOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        if((index*2+1)<arr.length){
            postOrder(index*2+1);
        }
        if((index*2+2)<arr.length){
            postOrder(index*2+2);
        }
        System.out.println(arr[index]);
    }

    public void infixOrder(){
        this.infixOrder(0);
    }
    public void infixOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if((index*2+1)<arr.length){
            infixOrder(index*2+1);
        }
        System.out.println(arr[index]);
        if((index*2+2)<arr.length){
            infixOrder(index*2+2);
        }
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    //编写一个方法，完成顺序存储二叉树的前序遍历
    //index表示数组的下标
    public void preOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+2)<arr.length){
            preOrder(index*2+2);
        }
    }
}
