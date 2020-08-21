package practice.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @PackgeName: practice.huffmancode
 * @ClassName: HuffmanCode
 * @Author: XuWen
 * Date: 2020/8/15 15:43
 * Introduce：赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
        String srcFile = "C:\\Users\\Lenovo\\Desktop\\1.jpg";
        String dstFIle = "2.zip";

        zifFile(srcFile,dstFIle);
        System.out.println("压缩文件ok~~~");

        //测试解压文件
        String zipFile = "2.zip";
        String destFile = "1.png";
        unZipFile(zipFile,destFile);
        System.out.println("解压成功");

//        String str = "i like like like java do you like a java";
        /*
        byte[] bytes = str.getBytes();
        List<Node> nodes = getNodes(bytes);
        //System.out.println(nodes);
        Node root = createHuffmanTree(nodes);
        root.preOrder();

        //测试是否生成了对应的哈夫曼编码
        //getCodes(root,"",sb);
        System.out.println(getCodes(root));

        byte[] huffmanCodeBytes = zip(str.getBytes(),getCodes(root));
        System.out.println(Arrays.toString(huffmanCodeBytes));
        */

//        byte[] huffmanCodeBytes = huffmanZip(str.getBytes());
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//
//        byte[] srcBytes = decode(huffmanCodes,huffmanCodeBytes);
//        System.out.println("原来的字符串"+new String(srcBytes));

    }

    /**
     * 编写一个方法，完成对压缩文件的解压
     * @param zipFile   准备解压的文件
     * @param destFile  将文件解压到哪个路劲
     */
    public static void unZipFile(String zipFile,String destFile){
        //定义文件输入流
        BufferedInputStream bis = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(new FileInputStream(zipFile));
            ois = new ObjectInputStream(bis);   //对象输入流
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> codes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(codes,huffmanBytes);
            //将bytes数组写入到目标文件
            bos.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert bos != null;
                bos.close();
                ois.close();;
                bis.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 编写方法，将一个文件进行压缩
     * @param srcFile   你传入的希望压缩的文件的全路径
     * @param dstFile   压缩后的放到哪个目录下
     */
    public static void zifFile(String srcFile,String dstFile){
        //创建一个文件的输入流
        BufferedInputStream bis = null;
        //创建输出流
        BufferedOutputStream bos = null;
        ObjectOutputStream oos=null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            //创建文件的输出流,存放压缩文件
            bos = new BufferedOutputStream(new FileOutputStream(dstFile));
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(bos);

            //创建一个和源文件大小一样的byte数组
            byte[] b = new byte[bis.available()];
            //读取文件
            bis.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes=huffmanZip(b);
            //这里我们以对象流的方式写入赫夫曼编码，为了以后恢复源文件时使用
            oos.writeObject(huffmanBytes);  //!!!!
            //注意一定要把赫夫曼编码写入到压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert bis != null;
                bis.close();
                assert bos != null;
                bos.close();
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //使用一个方法，将前面的方法封装起来，便于我们使用
    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return  返回压缩后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据赫夫曼树生成对应的赫夫曼编码
        Map<Byte,String> huffmanCode = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanBytes = zip(bytes,huffmanCode);

        return huffmanBytes;
    }
    //完成数据的解压
    //思路
    //1.将huffmanBytes转成赫夫曼对应的二进制的字符串"10101000..."
    //2.赫夫曼对应的二进制字符串，对照赫夫曼编码，转成字符串

    //编写一个方法，完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes  赫夫曼编码得到的字节数组
     * @return  返回原来的字符串对应的数组
     */
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //显得到huffmanBytes对应的二进制的字符串
        StringBuilder sb = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i=0;i<huffmanBytes.length;i++){
            if(i==huffmanBytes.length-1){
                sb.append(byteToBitString(false,huffmanBytes[i]));
            }else {
                sb.append(byteToBitString(true,huffmanBytes[i]));
            }
        }
        //System.out.println("赫夫曼字节数组对应的二进制字符串="+sb.toString());

        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，一弄完反向查询a->100 100->a
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for(int i=0;i<sb.length();){
            int count=1;    //小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag){
                //递增的取出key，取出一个"1"or"0"
                String key = sb.substring(i,i+count);   //i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if(b==null){    //说明没有匹配到
                    count++;
                }else {//匹配到
                    flag=false;
                }
            }
            list.add(b);
            i+=count;   //i直接移动到count位置
        }
        //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for(int i=0;i<b.length;i++){
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte 转成一个二进制的字符串
     * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false，表示不补,如果是最后一个字节，无需补高位
     * @param b byte
     * @return b对应的二进制的字符串。（是按补码返回的）
     */
    public static String byteToBitString(boolean flag,byte b){
        //使用一个变量保存b
        int temp = b;   //将b转成int
        //如果是正数，我们还存在一个补高位的问题
        //但是如果最后一个字节，不需要补高位
        if(flag) {
            temp |= 256;  //按位或256    1 0000 0000 | 0000 0001 => 1 0000 0001
        }

        String str=Integer.toBinaryString(temp);    //返回的是temp对应的二进制的补码
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }



    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表,返回一个赫夫曼编码压缩后的byte[]
    /**
     *
     * @param bytes 原始的字符串对应的byte[]数组
     * @param huffmanCodes  生成的赫夫曼编码map
     * @return  返回赫夫曼编码处理后的数组
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1.利用HuffmanCodes，将bytes转成赫夫曼编码的字符串
        StringBuilder sb = new StringBuilder();
        //遍历bytes数组
        for(byte b:bytes){
            sb.append(huffmanCodes.get(b));
        }

        //将"10101000..."转成byte[]数组

        //统计返回byte[]HuffmanCodeBytes长度
        int len;
        if(sb.length()%8==0){
            len = sb.length()/8;
        }else{
            len = sb.length()/8+1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;    //记录是第几个byte
        for(int i=0;i<sb.length();i+=8){    //因为是每8位对应一个byte，所有步长+8
            String str;
            if(i+8>sb.length()){    //最后一点点不够八位
                str = sb.substring(i);
            }else {
                str = sb.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeBytes;
    }



    //生成赫夫曼树对应的赫夫曼编码表
    //思路：
    //1.将赫夫曼编码存放在Map<Byte,String>形式
    //  32 -> 01
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //2.在生成赫夫曼编码表时，需要去拼接路劲，定义一个StringBuilder，存储某个叶子结点的路径
    static StringBuilder sb = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte,String> getCodes(Node root){
        if(root==null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",sb);
        //处理root的右子树
        getCodes(root.right,"1",sb);
        return huffmanCodes;
    }

    /**
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合中
     * @param node  传入结点
     * @param code  路径（左0右1）
     * @param sb 用于拼接路劲
     */
    public static void getCodes(Node node,String code,StringBuilder sb){
        StringBuilder sb2 = new StringBuilder(sb);
        //将传入的code加入到StringBuilder2
        sb2.append(code);
        if(node!=null){ //如果node==null不处理
            if(node.data==null){    //非叶子结点
                //递归处理
                getCodes(node.left,"0",sb2);    //左递归
                getCodes(node.right,"1",sb2);   //右递归
            }else{  //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data,sb2.toString());
            }
        }
    }




    //通过list创建对应的赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);    //从小到大
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node node = new Node(null,leftNode.weight+rightNode.weight);
            node.left = leftNode;
            node.right = rightNode;

            //移除处理的两颗二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(node);
        }
        return nodes.get(0);    //返回nodes最后的节点
    }

    /**
     *
     * @param bytes 接受字节数组
     * @return  返回的就是list
     */
    public static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数，用map
        Map<Byte,Integer> map = new HashMap<>();
        for(byte b:bytes){
            //map还没有这个字符数据
            map.merge(b, 1, Integer::sum);
        }
        //把每一个键值对转成一个Node对象，并加入到nodes集合
        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
}
