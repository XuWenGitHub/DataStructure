package recursion.test_02;

/*
小球的路径，跟程序员设置的路策略有关，找路的上下左右的顺序有关
小球最短路径：把每一种策略都列举出来，然后找到路线，算出路程，统计2的个数，最后装在一个数组里orMap集合里,最后遍历数组，找到最小值，即可
 */
public class MiGong {
    public static void main(String[] args) {
        //先用二维数组来表示地图,1代表不能走的地方，0代表可以走的地方
        int[][] map = new int[8][7];
        //设置上边和下边为1
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //设置左边和右边为1
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
//        map[4][2]=1;
//        map[5][2]=1;
//        map[5][3]=1;
//        map[5][4]=1;
//        map[4][4]=1;

        //显示一下地图
        System.out.println("地图的情况");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        setWay4(map,1,1);
        System.out.println("小球走完迷宫地图的情况");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

//        setWay2(map,1,1);
//        System.out.println("小球（上->右->下->左）走完迷宫地图的情况");
//        for(int i=0;i<8;i++){
//            for(int j=0;j<7;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
    }
    /*
    写一个小球走迷宫问题（回溯问题的方法）
    约定：
        1表示墙，不能走
        2表示没有走过，可以走
        3表示已经走过，但是这个点不通，也不用走
     */
    /**
     *
     * @param map   表示上面的那张地图，因为是二维数组，所有递归每次都是在同一张地图
     * @param i 表示小球的起始位置
     * @param j 表示小球的起始位置
     * @return  返回当前该点是否能走通
     */
    public static boolean setWay(int[][] map,int i,int j){
        //递归结束条件就是小球走到map[6,5]
        if(map[6][5]==2){
            //说明小球找到了
            return true;
        }else {
            if(map[i][j]==0) {  //表示这个点还没走过
                map[i][j] = 2;    //假设这个点可以走
                //定义一个小球路线策略：下->右->上->左
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //如果上面的都行不通，说明这个点，是死路，上中下左都走不了，那么我们设置成3
                    map[i][j]=3;
                    return false;
                }
            }else {
                //当前点可能为1,2,3 都不能走
                return false;
            }
        }
    }

    //修改找路的策略，改成上->右->下->左
    public static boolean setWay2(int[][] map,int i,int j){
        //递归结束条件就是小球走到map[6,5]
        if(map[6][5]==2){
            //说明小球找到了
            return true;
        }else {
            if(map[i][j]==0) {  //表示这个点还没走过
                map[i][j] = 2;    //假设这个点可以走
                //定义一个小球路线策略：上->右->下->左
                if (setWay2(map, i - 1, j)) {
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    //如果上面的都行不通，说明这个点，是死路，上中下左都走不了，那么我们设置成3
                    map[i][j]=3;
                    return false;
                }
            }else {
                //当前点可能为1,2,3 都不能走
                return false;
            }
        }
    }

    //修改找路的策略，改成右->下->左->上
    public static boolean setWay4(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay4(map,i,j+1)){
                    return true;
                }else if(setWay4(map,i+1,j)){
                    return true;
                }else if(setWay4(map,i,j-1)){
                    return true;
                }else if(setWay4(map,i-1,j)){
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                //当前点为1,2，3不满足条件
                return false;
            }
        }
    }

    //修改找路的策略，改成上->下->左->右
    public static boolean setWay3(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay3(map,i-1,j)){
                    return true;
                }else if (setWay3(map,i+1,j)){
                    return true;
                }else if(setWay3(map,i,j-1)){
                    return true;
                }else if(setWay3(map,i,j+1)){
                    return true;
                }else {
                    //如果上下左右都走不通，那么这个点是思路
                    map[i][j]=3;
                    return false;
                }
            }else {
                //如果不为0，可能为1,2,3, 都不能走
                return false;
            }
        }
    }
}
