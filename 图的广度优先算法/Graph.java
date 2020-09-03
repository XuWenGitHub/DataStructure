package practice.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @PackgeName: practice.graph
 * @ClassName: Graph
 * @Author: XuWen
 * Date: 2020/9/2 16:03
 * Introduce:
 */
public class Graph {

    private ArrayList<String> vertexList;   //存储顶点的集合
    private int[][] edges;  //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    //定义一个数组boolean[],记录某个节点是否被访问过
    private boolean[] isVisited;

    //构造器
    public Graph(int n){    //n表示顶点
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0; //不写也是0
        isVisited = new boolean[5];
    }

    //将isVisit置空
    public void isVisitEmpty(){
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * //添加边
     * @param v1    表示点的下标，即是第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2    第二个顶点对应的下标
     * @param weight    表示他们之间的值，矩阵之间表示用什么表示他们是关联的
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;   //因为是无向图，所以两个
        edges[v2][v1]=weight;
        numOfEdges++;
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i（下标）对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges[i].length;j++){
                System.out.print(edges[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     *  //得到第一个邻接节点的下标 w
     * @param index 当前节点的下标
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //如果邻接节点被访问，获取其下一个邻接节点
    public int getNextNeighbor(int v1,int v2){ //v1代表邻接矩阵行，v2代表邻接矩阵列
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i:第一次就是0
    private void dfs(boolean[] isVisited,int i){
        //首先我们访问该节点
        System.out.print(getValueByIndex(i)+"->");
        //将该节点设置为已经访问过
        isVisited[i]=true;
        //查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w!=-1){  //说明有邻接节点
            if(!isVisited[w]){  //说明没有访问过
                dfs(isVisited,w);
            }
            //如果w节点已经被访问过，就去i里面找下一次路线同的下标
            w=getNextNeighbor(i,w);
        }
    }
    //对dfs进行重载，遍历我们所有的节点，斌进行dfs
    public void dfs(){
        //遍历所有的节点，进行dfs（回溯）
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){  //表示没有访问过,提高效率
                dfs(isVisited,i);
            }
        }
    }


    public static void main(String[] args) {
        //测试一把图是否创建OK
        int n=5;    //节点的个数
        String[] vertexValue = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(5);
        //循环的添加顶点
        for(String s:vertexValue){
            graph.insertVertex(s);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示一把邻接矩阵
        graph.showGraph();

        //测试一把，我们的dfs遍历是否OK
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println();
        graph.isVisitEmpty();
        //测试一把，我们的bfs广度优先遍历是否OK
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u;  //表示队列的头结点对应的下标
        int w;  //邻接节点的下标
        //队列,节点访问的瞬息
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，输出节点的信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i]=true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列的头结点下标
            u=queue.removeFirst();
            //得到第一个邻接点的下标w
            w=getFirstNeighbor(u);
            while(w!=-1){   //找到
                if(!isVisited[w]){  //没有访问过
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已经访问
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接节点
                w = getNextNeighbor(u,w);   //找以u这一行的，w的下一个邻接点,体现出我们的广度优先
            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    public void bfs(){
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
