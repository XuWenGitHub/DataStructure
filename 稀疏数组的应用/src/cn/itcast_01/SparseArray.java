package cn.itcast_01;

/*
二维数组转成稀疏数组
稀疏数组还原成二维数组
再加上IO流存储，和读取
 */

public class SparseArray {
    public static void main(String[] args) {
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
        System.out.println("sum="+sum);

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
    }
}
