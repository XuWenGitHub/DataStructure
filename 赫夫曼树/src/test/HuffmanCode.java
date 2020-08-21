package test;

import java.util.*;

/**
 * @PackgeName: test
 * @ClassName: HuffmanCode
 * @Author: XuWen
 * Date: 2020/8/16 12:32
 * Introduce:   将字节数组压缩
 */

public class HuffmanCode {
    /*
    分析：
    A：我们先要把字符串转成字节数组，然后通过字节数组构建哈夫曼树
    B：再通过哈夫曼树形成哈夫曼编码表
    C：再通过哈夫曼编码表来将最初的字节数组变成压缩后的解压字节数组返回
     */

    /**
     * 最后把下面的函数封装起来，根据原始数组，即可返回解压后的数组
     * @param bytes 原始的byte数组
     * @return  压缩后的数组
     */
    public byte[] huffmanZip(byte[] bytes){
        //先根据byte[]得到nodes
        List<Node> nodes = creatNodes(bytes);
        //再通过nodes构建哈夫曼树
        Node root = creatHuffmanTree(nodes);
        //在通过哈夫曼树构建哈夫曼编码
        Map<Byte,String> huffmanCode = creatHuffmanCode(root);
        //最后通过哈夫曼编码解压数据
        byte[] huffmanBytes = zip(huffmanCode,bytes);
        return huffmanBytes;
    }

    /**
     * 通过哈夫曼编码表把原字节数组压缩成新的字节数组，最后返回
     * @param huffmanCode   哈夫曼编码表
     * @param bytes 原字节数组
     * @return  压缩后的新的字节数组
     */
    private byte[] zip(Map<Byte,String> huffmanCode,byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b:bytes){
            sb.append(huffmanCode.get(b));
        }
        int len=(sb.length()+7)/8;  //算出新的字节数组的长度
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for(int i=0;i<sb.length();i+=8){
            String str;
            if(i+8>sb.length()){
                str = sb.substring(i);
            }else{
                str = sb.substring(i,i+8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * creatHuffmanCode方法重载一下
     * @param root  表示哈夫曼树的根节点
     * @return  哈夫曼树形成的哈夫曼表
     */
    private Map<Byte,String> creatHuffmanCode(Node root){
        if(root==null){
            return null;
        }
        creatHuffmanCode(root,"",sb);
        return huffmanCode;
    }

   static Map<Byte,String> huffmanCode = new HashMap<>();
   static StringBuilder sb = new StringBuilder();
    /**
     * 通过哈夫曼树来构建哈夫曼编码
     * @param root 哈夫曼树的根节点
     * @param code  定义一个规则，左子树为0，右子树为1
     * @param sb    存放编码的sb
     */
    private void creatHuffmanCode(Node root,String code,StringBuilder sb){
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if(root!=null){
            if(root.data==null){    //表示非叶子节点
                if(root.left!=null){
                    creatHuffmanCode(root.left,"0",sb2);
                }
                if(root.right!=null){
                    creatHuffmanCode(root.right,"1",sb2);
                }
            }else{  //表示叶子结点
                huffmanCode.put(root.data,sb2.toString());
            }
        }
    }



    /**
     * 通过节点集合来构建哈夫曼树
     * @return  哈夫曼树的根节点
     */
    private Node creatHuffmanTree(List<Node> nodes){
        //先对集合排序
        //然后取出前两个然后造出一颗二叉树
        //然后再删除前两个，再添加上一步构建的二叉树
        //然后一直循环，知道集合size为1，那就是root，然后直接返回
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node node = new Node(null,leftNode.num+rightNode.num);
            node.left = leftNode;
            node.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(node);
        }
        return nodes.get(0);
    }

    /**
     * 将字节数组每个元素先变成二叉树节点，存入List返回
     * @param bytes 字节数组
     * @return  List集合
     */
    private List<Node> creatNodes(byte[] bytes){
        //用map集合来判断每个数据的num
        Map<Byte,Integer> map = new HashMap<>();
        for(byte b:bytes){
            if(map.containsKey(b)){ //如果包含b
                map.put(b,map.get(b)+1);
            }else{  //如果不包含，那么就添加进去
                map.put(b,1);
            }
        }
        //现在遍历map，创建List集合，存放Node
        List<Node> nodes = new ArrayList<>();
        for(Map.Entry<Byte,Integer> set:map.entrySet()){
            byte data = set.getKey();
            int num = set.getValue();
            nodes.add(new Node(data,num));
        }
        return nodes;
    }

}
