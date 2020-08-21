package test;

import java.util.Arrays;

/**
 * @PackgeName: test
 * @ClassName: Demo
 * @Author: XuWen
 * Date: 2020/8/16 12:33
 * Introduce:
 */
public class Demo {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] huffmanByte = huffmanCode.huffmanZip(str.getBytes());
        System.out.println(Arrays.toString(huffmanByte));
    }
}
