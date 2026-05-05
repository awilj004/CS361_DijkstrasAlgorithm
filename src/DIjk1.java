import java.util.*;
public class DIjk1 {
    private static void buildGraphs(){
        int inf = Integer.MAX_VALUE;
        int[][] sparseGraph1M = {
                {0,4,2,inf,inf,inf},
                {4,0,inf,5,inf,inf},
                {2,inf,0,1,inf,inf},
                {inf,5,1,0,3,inf},
                {inf,inf,inf,3,0,2},
                {inf,inf,inf,inf,2,0}
        };
        int[][] sparseGraph2M = {
                {0,3,6,inf,inf,inf,inf},
                {3,0,inf,2,5,inf,inf},
                {6,inf,0,inf,4,inf,inf},
                {inf,2,inf,0,inf,7,inf},
                {inf,5,4,inf,0,inf,1},
                {inf,inf,inf,7,inf,0,inf},
                {inf,inf,inf,inf,1,inf,0}
        };
        int[][] denseGraph1M = {
                {0,2,5,1,4},
                {2,0,3,2,6},
                {5,3,0,3,1},
                {1,2,3,0,2},
                {4,6,2,2,0}
        };
        int[][] denseGraph2M = {
                {0,3,2,6,5,4},
                {3,0,1,2,4,7},
                {2,1,0,3,6,5},
                {6,2,3,0,2,4},
                {5,4,6,2,0,1},
                {4,7,5,4,1,0}
        };

        List<List<Edge>> sparseGraph1L = matrixToList(sparseGraph1M);
        List<List<Edge>> sparseGraph2L = matrixToList(sparseGraph2M);
        List<List<Edge>> denseGraph1L = matrixToList(denseGraph1M);
        List<List<Edge>> denseGraph2L = matrixToList(denseGraph2M);
        runTests(sparseGraph1L,sparseGraph2L,denseGraph1L,denseGraph2L,
                sparseGraph1M,sparseGraph2M,denseGraph1M,denseGraph2M);
    }
    private static List<List<Edge>> matrixToList(int[][] m){
        int inf = Integer.MAX_VALUE;
       List<List<Edge>> returnList = new ArrayList<>();
       for(int i = 0; i<m.length; i++){
           List<Edge> innerList = new ArrayList<>();
           for(int j = 0; j<m.length; j++){
               if(m[i][j] != inf){
                   Edge e = new Edge(j,m[i][j]);
                   innerList.add(e);
               }
           }
           returnList.add(innerList);
       }
       return returnList;
    }
    private static int[] dijkstra (List<List<Edge>> g, int s){
        int n = g.size();
        int[] distList = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i<distList.length; i++){
            distList[i] = Integer.MAX_VALUE;
        }
        distList[s] = 0;
        int curr = s;
        for(int i = 0; i<n; i++){
            visited[curr] = true;
            int dist = distList[curr];
            for(int j = 0; j<g.get(curr).size(); j++){
                Edge e = g.get(curr).get(j);
                int newDist = dist + e.weight;
                if(newDist < distList[e.next]){
                    distList[e.next] = newDist;
                }
            }
            int shortestPath = -1;
            for(int j = 0; j<n; j++){
                if(!visited[j]){
                    if(shortestPath == -1 || distList[j] < distList[shortestPath]){
                        shortestPath = j;
                    }
                }
            }
            if(shortestPath == -1){
                break;
            }
            curr = shortestPath;
        }
        return distList;
    }

    private static int[] dijkstra (int[][] g, int s){
        int n = g.length;
        int[] distList = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i<distList.length; i++){
            distList[i] = Integer.MAX_VALUE;
        }
        distList[s] = 0;
        int curr = s;

        for(int i = 0; i<n-1; i++){
            visited[curr] = true;
            int dist = distList[curr];
            //reweight all nodes
            for(int j = 0; j<n; j++){
                //account for integer overflow errors
                if(g[curr][j] == Integer.MAX_VALUE){
                    continue;
                }
                int newDist = dist+g[curr][j];
                //reweight node
                if(newDist<distList[j]){
                    distList[j] = newDist;
                }
            }
            //find shortest path
            int shortestDistance = -1;
            for(int j = 0; j<n; j++){
                if(!visited[j]){
                    if(shortestDistance == -1 || distList[shortestDistance] > distList[j]){
                        shortestDistance = j;
                    }
                }
            }
            if(shortestDistance == -1){break;}
            curr = shortestDistance;
        }
        return distList;
    }

    private static void runTests(List<List<Edge>> sg1l,List<List<Edge>> sg2l, List<List<Edge>> dg1l, List<List<Edge>> dg2l,
                                 int[][] sg1m, int[][] sg2m, int[][] dg1m, int[][] dg2m)
    {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("*********SPARSE GRAPH BENCHMARKS****** \n");
        System.out.println("Bench One: Sparse Graph 1 - Adj List");
        long start = System.nanoTime();
        int[] result1 = dijkstra(sg1l,0);
        long duration = System.nanoTime() - start;
        long usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result1)+ "\n");

        System.out.println("Bench two: Sparse Graph 1 - Adj Matrix");
        start = System.nanoTime();
        int[] result2 = dijkstra(sg1m,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result2)+ "\n");

        System.out.println("Bench Three: Sparse Graph 2 - Adj List");
        start = System.nanoTime();
        int[] result3 = dijkstra(sg2l,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result3)+ "\n");

        System.out.println("Bench Four: Sparse Graph 2 - Adj Matrix");
        start = System.nanoTime();
        int[] result4 = dijkstra(sg2m,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result4)+ "\n");


        System.out.println("*********DENSE GRAPH BENCHMARKS****** \n");
        System.out.println("Bench Five: Dense Graph 1 - Adj List");
        start = System.nanoTime();
        int[] result5 = dijkstra(dg1l,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result5)+ "\n");

        System.out.println("Bench Six: Dense Graph 1 - Adj Matrix");
        start = System.nanoTime();
        int[] result6 = dijkstra(dg1m,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result6)+ "\n");

        System.out.println("Bench Seven: Dense Graph 2 - Adj List");
        start = System.nanoTime();
        int[] result7 = dijkstra(dg2l,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result7)+ "\n");

        System.out.println("Bench Eight: Dense Graph 2 - Adj Matrix");
        start = System.nanoTime();
        int[] result8 = dijkstra(dg2m,0);
        duration = System.nanoTime() - start;
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result8)+ "\n");


    }
    public static void main(String[] args) {

        buildGraphs();
    }

}
