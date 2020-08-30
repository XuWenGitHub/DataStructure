package practice.three;

import java.io.*;
import java.util.*;

/**
 * @PackgeName: practice.three
 * @ClassName: HuffmanCode
 * @Author: XuWen
 * Date: 2020/8/26 12:55
 * Introduce:  文件的压缩和解压，赫夫曼编码来压缩和解压
 */
public class HuffmanCode {
    /**
     * 编写一个方法，完成对压缩文件的解压
     * @param zipFile   准备解压的文件
     * @param destFile  将文件解压到哪个路径
     */
    public void unZipFile(String zipFile,String destFile){
        //定义文件输入流
        BufferedInputStream bis = null;
        //定义对象的输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(new FileInputStream(zipFile));
            ois = new ObjectInputStream(bis);
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> codes = (Map<Byte, String>)ois.readObject();

            //解码
            byte[] bytes = decode(codes,huffmanBytes);
            //将解码后的bytes数组写入到目标文件中
            bos.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert ois != null;
                ois.close();
                bis.close();
                assert bos != null;
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件进行压缩
     * @param src   你传入的希望压缩的文件全路径
     * @param det   压缩后的放到哪个目录下
     */
    public void zipFile(String src,String det){
        //创建一个文件的输入流
        BufferedInputStream bis = null;
        //创建输出流,这里我们用对象输出流
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(det));
            oos = new ObjectOutputStream(bos);
            //创建一个和源文件大小一样的字节数组
            byte[] b = new byte[bis.available()];
            //读取文件
            bis.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //这里我们要以对象流的方式写入赫夫曼编码和压缩后的数组，恢复文件时使用
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);  //!!!!
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert oos != null;
                oos.close();
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 完成对数据的解码
     * @param huffmanCodes  哈夫曼编码表 map
     * @param huffmanBytes  赫夫曼编码得到的字节数组
     * @return  返回原来的字符串对应的数组
     */
    public byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder sb = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i=0;i<huffmanBytes.length;i++){
            if(i==huffmanBytes.length-1){
                sb.append(byteToBitString(false,huffmanBytes[i]));
            }else{
                sb.append(byteToBitString(true,huffmanBytes[i]));
            }
        }

        //将赫夫曼编码进行键值互换
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for(int i=0;i<sb.length();){
            int count=0;    //小的计数器
            boolean flag=true;
            Byte b=null;
            while (flag){
                //递增的取出key，取出一个"1"or"0"
                String key = sb.substring(i,i+count);
                b = map.get(key);
                if(b==null){    //说明没有匹配到
                    count++;
                }else{  //匹配到
                    flag=false;
                }
            }
            list.add(b);
            i+=count;   //i直接移动到count位置
        }
        byte[] b = new byte[list.size()];
        for(int i=0;i<b.length;i++){
            b[i]=list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @param flag  标志是否需要补高位，如果需要true，不需要false，如果是最后一个字节，无需补高位
     * @param b byte
     * @return  b对应的二进制的字符串（按补码)
     */
    private String byteToBitString(boolean flag,byte b){
        int temp=b;
        //如果是正数，我们还存咋一个补高位的问题
        //但是如果最后一个字节，不需要补高位
        if(flag){
            temp|=256;  //按位或256   1 0000 0000 | 0000 0011 => 1 0000 0011
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }



    /**
     * 封装一下，字符串压缩
     * @param bytes 待压缩的字符串转成的字节数组
     * @return  返回压缩后的字节数组
     */
    public byte[] huffmanZip(byte[] bytes){
        //先根据bytes构建nodes
        List<Node> nodes = getNodes(bytes);
        //再根据nodes构建赫夫曼树
        Node root = createHuffmanTree(nodes);
        //再根据赫夫曼树，来创建赫夫曼编码表
        Map<Byte,String> huffmanCodes = getCodes(root);
        //再根据赫夫曼编码表，来压缩，得到新的byte[]
        byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
        return huffmanCodeBytes;
    }


    /**
     * 编写一个方法，将字符串对应的byte[]，通过赫夫曼编码，形成新的压缩后的byte[]，返回返回哈夫曼编码处理后的数组
     * @param bytes 原始的字符串对应的byte数组
     * @param huffmanCodes  生成的哈夫曼编码
     * @return  返回哈夫曼编码处理后的数组
     */
    private byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder sb = new StringBuilder();
        for(byte b:bytes){
            sb.append(huffmanCodes.get(b));
        }
        //将"1010100..."转成byte[]数组
        int len=(sb.length()%8==0)?(sb.length()/8):(sb.length()/8+1);
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;    //新的byte数组的下标
        for(int i=0;i<sb.length();i+=8){
            String str;
            if(i+8>sb.length()){
                str=sb.substring(i);
            }else {
                str=sb.substring(i,i+8);
            }
            huffmanCodeBytes[index]=(byte)Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    //为了方便调用，重载getCodes方法,返回的就是赫夫曼编码
    private Map<Byte,String> getCodes(Node root){
        if(root==null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",sb);
        //处理右子树
        getCodes(root.right,"1",sb);
        return huffmanCodes;
    }
    /**
     * 将传入的node节点的所有叶子结点的赫夫曼编码得到，并存入huffmanCodes集合中
     * @param node  传入结点
     * @param code  路径（左0右1）
     * @param sb    用于拼接的路劲
     */
    private void getCodes(Node node,String code,StringBuilder sb){
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if(node!=null){
            if(node.data==null){    //非叶子节点
                getCodes(node.left,"0",sb2);    //递归处理
                getCodes(node.right,"1",sb2);
            }else{  //叶子结点
                //就表示找到某个叶子结点的编码了
                huffmanCodes.put(node.data,sb2.toString());
            }
        }
    }


    /**
     * 构建赫夫曼树
     * @param nodes 节点的集合
     * @return  赫夫曼树的根节点
     */
    private Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node newNode = new Node(null,leftNode.weight+rightNode.weight);
            newNode.left = leftNode;
            newNode.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    /**
     * 将字节数组，统计每个字节出现的个数，然后构建节点，然后存入List返回
     * @param bytes 接受字节数组
     * @return  返回的就是list
     */
    private List<Node> getNodes(byte[] bytes){
        List<Node> list = new ArrayList<>();
        Map<Byte,Integer> map = new HashMap<>();
        for(byte b:bytes){
            if(map.containsKey(b)){
                map.put(b,map.get(b)+1);
            }else {
                map.put(b,1);
            }
        }
        //把每个键值对转成一个Node对象，存入到List里面
        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            list.add(new Node(entry.getKey(),entry.getValue()));
        }
        return list;
    }
}
