package practice.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @PackgeName: practice.graph
 * @ClassName: Test
 * @Author: XuWen
 * Date: 2020/9/3 12:43
 * Introduce:
 */
public class Test {
    private ArrayList<String> vertexList;   //存储的顶点
    private int[][] graph;  //存储顶点之间有没有连接
    private int edgesNum;   //边的个数
    private boolean[] isVisit;  //表示每个顶点是否被访问过


    //构造器   n代表顶点的个数
    public Test(int n){
        vertexList = new ArrayList<>(n);
        graph = new int[n][n];
        isVisit =new boolean[n];
    }



    //插入节点
    public void add(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加图的边
     * @param n1    表示顶点的下标
     * @param n2    表示顶点的下标
     */
    public void addEdges(int n1,int n2,int value){
        graph[n1][n2]=value;
        graph[n2][n1]=value;
        edgesNum++;
    }
    //返回节点的个数
    public int arraySize(){
        return vertexList.size();
    }
    //得到边的数目
    public int getEdgesNum(){
        return edgesNum;
    }
    //返回节点i（下标）对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return graph[v1][v2];
    }
    //显示对应的矩阵
    public void showGraph(){
        for(int[] arr:graph){
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void main(String[] args) {
        int n=5;
        Test test = new Test(n);
        String[] nodes = {"A","B","C","D","E"};
        for(String s:nodes){
            test.add(s);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        test.addEdges(0,1,1);
        test.addEdges(0,2,1);
        test.addEdges(1,2,1);
        test.addEdges(1,3,1);
        test.addEdges(1,4,1);
        test.showGraph();

        test.dfs();
    }
    //深度优先遍历
    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for(int i=0;i<arraySize();i++){
            if(graph[index][i]>0){
                return i;
            }
        }
        return -1;
    }
    //如果邻接节点被访问，获取其下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int i=v2+1;i<arraySize();i++){
            if(graph[v1][i]>0){
                return i;
            }
        }
        return -1;
    }
    //dfs
    public void dfs(boolean[] isVisit,int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisit[i]=true;
        int w = getFirstNeighbor(i);
        while(w!=-1){
            if(!isVisit[w]){
                dfs(isVisit,w);
            }
            w = getNextNeighbor(i,w);
        }
    }
    public void dfs(){
        for(int i=0;i<arraySize();i++){
            if(!isVisit[i]){
                dfs(isVisit,i);
            }
        }
    }
}
