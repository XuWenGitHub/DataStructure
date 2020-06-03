package cn.itcast_03;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //先创建一个棋盘二维数组
        //1代表黑旗，2代表白棋
        int[][] chessArr = new int[11][11];     //动态初始化，系统给出初始化值，int为0
        chessArr[2][3]=1;
        chessArr[1][2]=2;
        chessArr[4][4]=1;
        //显示一下棋盘
        for(int i=0;i<chessArr.length;i++){
            for(int j=0;j<11;j++){
                System.out.printf("%d\t",chessArr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();


        //发现有很多都是相同的，那么我们可以用稀疏数组来表示
        //把chessArr转成稀疏数组
        //稀疏数组，有n行，3列，n就为(不同数据的个数+1)
        int row = 0;
        for(int[] arr:chessArr){
            for(int value:arr){
                if(0!=value){
                    row++;
                }
            }
        }
        //System.out.println(row);
        int[][] sparseArr = new int[row+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=row;
//        System.out.println(chessArr.length);  11
        int conut=0;
        for(int i=0;i<chessArr.length;i++){
            for(int j=0;j<11;j++){
                if(0!=chessArr[i][j]){
                    conut++;    //累加器，代表sparseArr行数
                    sparseArr[conut][0]=i;
                    sparseArr[conut][1]=j;
                    sparseArr[conut][2]=chessArr[i][j];
                }
            }
        }
        //打印一下稀疏数组
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<3;j++){
                System.out.printf("%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();


        //把稀疏数组转成二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //把稀疏数组中不同的数，添加到chessArr2
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //打印chessArr2
        for(int i=0;i<chessArr2.length;i++){
            for(int j=0;j<11;j++){
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();


        //把sparseArr稀疏数组写入sparse2.txt文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("sparse2.txt"));
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<3;j++){
                bw.write(sparseArr[i][j]+"\t");
            }
            bw.write("\r\n");
        }
        bw.close();

        //读取sparse2.txt文件内容，存入sparseArr2
        int[][] sparseArr2 = new int[row+1][3];
        BufferedReader br = new BufferedReader(new FileReader("sparse2.txt"));
        String line = null;
        int row2=0; //累加器，sparseArr2的行
        while((line=br.readLine())!=null){
            String[] value = line.split("\t");
            for(int i=0;i<value.length;i++){
                int ii = Integer.parseInt(value[i]);
                sparseArr2[row2][i]=ii;
            }
            row2++;
        }
        //打印sparseArr2
        for(int i=0;i<sparseArr2.length;i++){
            for(int j=0;j<3;j++){
                System.out.printf("%d\t",sparseArr2[i][j]);
            }
            System.out.println();
        }
    }
}
