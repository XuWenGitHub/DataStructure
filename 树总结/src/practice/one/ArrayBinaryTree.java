package practice.one;

/**
 * @PackgeName: PACKAGE_NAME
 * @ClassName: practice.one.ArrayBinaryTree
 * @Author: XuWen
 * Date: 2020/8/25 16:35
 * Introduce:   顺序存储二叉树
 */
public class ArrayBinaryTree {
    private int[] arr;  //这就是树，其实底层是个数组，但是我们让其表现形成为树

    //构造器
    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void postOrder(){
        postOrder(0);
    }
    //后序遍历
    private void postOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("树为空");
            return;
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
        infixOrder(0);
    }
    //中序遍历
    private void infixOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("空树，无法遍历");
            return;
        }
        if((index*2+1)<arr.length){
            infixOrder(index*2+1);
        }
        System.out.println(arr[index]);
        if((index*2+2)<arr.length){
            infixOrder(index*2+2);
        }
    }

    public void prevOrder(){
        prevOrder(0);
    }
    //前序遍历
    private void prevOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("树为空，不能遍历");
            return;
        }
        System.out.println(arr[index]);
        if((index*2+1)<arr.length){
            prevOrder(index*2+1);
        }
        if((index*2+2)<arr.length){
            prevOrder(index*2+2);
        }
    }
}
