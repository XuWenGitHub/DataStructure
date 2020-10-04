package practice.kruskal;

import java.util.Arrays;

/**
 * @PackgeName: practice.kruskal
 * @ClassName: KruskalCase
 * @Author: XuWen
 * Date: 2020/9/11 17:15
 * Introduce:
 */
public class KruskalCase {
    private int edgeNum;    //记录边的个数
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用INF这个常量表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[][]{
            {0,12,INF,INF,INF,16,14},
            {12,0,10,INF,INF,7,INF},
            {INF,10,0,3,5,6,INF},
            {INF,INF,3,0,4,INF,INF},
            {INF,INF,5,4,0,2,8},
            {16,7,6,INF,2,0,9},
            {14,INF,INF,INF,8,9,0},
        };
        //创建一个KruskalCase对象实例
        KruskalCase kruskalCase = new KruskalCase(vertex,matrix);
        kruskalCase.print();

        //测试边排序
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdge(edges);
        System.out.println(Arrays.toString(edges));

        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertex,int[][] matrix){
        //初始化顶点数和边的个数
        int vlen = vertex.length;   //顶点的个数

        //初始化顶点,深拷贝的方式
        this.vertex = new char[vlen];
        for(int i=0;i<vertex.length;i++){
            this.vertex[i] = vertex[i];
        }

        //初始化边,使用的是深拷贝
        this.matrix = new int[vlen][vlen];
        for(int i=0;i<vlen;i++){
            for(int j=0;j<vlen;j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for(int i=0;i<vlen;i++){
            for(int j=i+1;j<vlen;j++){
                if(this.matrix[i][j]!=INF){
                    this.edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index = 0;  //表示最后结果数组的索引
        int[] ends = new int[edgeNum];  //用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        //创建结果数组,保存最后的最小生成树
        EData[] results = new EData[vertex.length-1];

        //获取图中所有的边的集合,一共有12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合="+Arrays.toString(edges)+"共"+edges.length);

        //按照边的权值大小进行排序(从小到大)
        sortEdge(edges);

        //遍历edges数组,将边添加到最小生成树中，判断准备加入的边是否构成了回路,如何没有，就加入到result里面去，否则不能加入
        for(int i=0;i<edges.length;i++){
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到低i条边的第二个顶点（终点）
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树的终点
            int n = getEnd(ends,p2);
            //是否构成回路
            if(m!=n){   //没有构成回路
                ends[m] = n;    //设置m在"已有最小生成树"中的终点
                results[index++]=edges[i];  //有一条边加入到result数组
            }
        }

        //统计并打印"最小生成树",输出results
        System.out.println("最小生成树为="+Arrays.toString(results));
    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为：\n");
        for(int i=0;i<vertex.length;i++){
            for(int j=0;j<vertex.length;j++){
                System.out.printf("%12d",matrix[i][j]);   //%20d代表站位，如果不够，就空出来
            }
            System.out.println();
        }
    }

    //对边进行排序处理,冒泡
    private void sortEdge(EData[] edges){
        for(int i=0;i<edges.length-1;i++){
            for(int j=0;j<edges.length-1-i;j++){
                if(edges[j].compareTo(edges[j+1])>0){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值,例如'A'
     * @return  返回对应的下标,如果找不到返回-1
     */
    private int getPosition(char ch){
        for(int i=0;i<vertex.length;i++){
            if(vertex[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取图中的边,放到EData[]数组中，后面我们需要遍历该数组
     * 通过matrix 邻接矩阵来获取
     * EData[] 形式[['A','B',12],['B','F',7],......]
     * @return  边的数组
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for(int i=0;i<vertex.length;i++){
            for(int j=i+1;j<vertex.length;j++){
                if(matrix[i][j]!=INF&&matrix[i][j]!=0){
                    edges[index] = new EData(vertex[i],vertex[j],matrix[i][j]);
                    index++;
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点(),用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends数组是在遍历过程中,逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return  返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}

//创建一个类EData，它的对象实例就表示一条边
class EData implements Comparable<EData>{
    char start; //边的一个点
    char end;   //边的另外一个点
    int weight; //边的权值

    //构造器
    public EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString方法，便于输出这条边


    @Override
    public String toString() {
        return "" +
                "<" + start +
                "," + end +
                ">=" + weight;
    }

    @Override
    public int compareTo(EData eData) {
        return this.weight-eData.weight;
    }
}