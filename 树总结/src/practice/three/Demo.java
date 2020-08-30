package practice.three;

import java.util.Arrays;

/**
 * @PackgeName: practice.three
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/8/26 12:55
 * Introduce:
 */
public class Demo {
    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        HuffmanCode huffmanCode = new HuffmanCode();
//        System.out.println("原始字节数组");
//        System.out.println(Arrays.toString(str.getBytes()));
//        byte[] bytes = huffmanCode.huffmanZip(str.getBytes());
//        System.out.println("压缩后：");
//        System.out.println(Arrays.toString(bytes));
//        System.out.println("解码后");
//        byte[] bytes1 = huffmanCode.decode(HuffmanCode.huffmanCodes,bytes);
//        System.out.println(Arrays.toString(bytes1));


        HuffmanCode huffmanCode = new HuffmanCode();
        huffmanCode.zipFile("1.png","1.zip");
        huffmanCode.unZipFile("1.zip","2.png");

//        StringBuilder sb = new StringBuilder();
//        sb.append("123123");
//        System.out.println(sb.substring(0,0));
    }
}
