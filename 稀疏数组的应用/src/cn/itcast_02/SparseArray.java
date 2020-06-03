package cn.itcast_02;

/*
二维数组转成稀疏数组
稀疏数组还原成二维数组
 */

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子，2表示白子
        int chessArr[][]= new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[3][4]=1;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row:chessArr){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



        //将二维数组转为稀疏数组的思路
        //1.先遍历二维数组，得到非0数据的个数
        int sum=0;
        for(int i=0;i<chessArr.length;i++){
            for(int j=0;j<chessArr.length;j++){
                if(0!=chessArr[i][j]){
                    sum++;
                }
            }
        }
//        System.out.println("sum="+sum);

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组第一行赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //遍历二维数组，将非0的值，存放到稀疏数组中
        int count=0;    //count用于记录是第几个非0数据
        for(int i=0;i<chessArr.length;i++){
            for(int j=0;j<chessArr.length;j++){
                if(0!=chessArr[i][j]){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for(int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        System.out.println();





        //将稀疏数组恢复成原始的二维数组
        //先读取稀疏数组的第一行的数据
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println("稀疏数组回复后的原始数组");
        for(int[] row:chessArr2){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //稀疏数组存到文件里
       BufferedWriter bw = new BufferedWriter(new FileWriter("sparse.txt"));    //文件写入流

        //将数组中的数据写入到文件中，每行个数据之间TAB间隔
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<3;j++){
                bw.write(sparseArr[i][j]+"\t");
            }
            bw.write("\r\n");
        }
        bw.close();

        //把文件里的数据，读到稀疏数组里
        int[][] sparseArr2 = new int[sum+1][3];

        BufferedReader br = new BufferedReader(new FileReader("sparse.txt"));
        String line=null;
        int row=0;
        while((line=br.readLine())!=null){

            String[] temp = line.split("\t");
            for(int j=0;j<temp.length;j++){
                sparseArr2[row][j]=Integer.parseInt(temp[j]);
            }
            row++;
        }
        br.close();

        //显示读取出的数组
        for(int i=0;i<sparseArr2.length;i++){
            for(int j=0;j<3;j++){
                System.out.printf("%d\t",sparseArr2[i][j]);
            }
            System.out.println();
        }

        //把sparseArr2稀疏数组，转化为对应的二维数组
        int[][] chessArr3 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for(int i=1;i<sparseArr2.length;i++){
            chessArr3[sparseArr2[i][0]][sparseArr2[i][1]]=sparseArr2[i][2];
        }

        //显示一下sparseArr2还原成对应的二维数组
        System.out.println();
        System.out.println("从文件里读取的数据，存储到稀疏数组，再从稀疏数组转成对应的二维数组");
        for(int i=0;i<sparseArr2[0][0];i++){
            for(int j=0;j<sparseArr2[0][1];j++){
                System.out.printf("%d\t",chessArr3[i][j]);
            }
            System.out.println();
        }
    }
}
