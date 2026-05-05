
import java.util.*;

public class Graphs {
    static class ABCNode {
        char vertex;
        int weight;

        ABCNode(char v, int w) {
            this.vertex = v;
            this.weight = w;
        }
    }

    static class numNode {
        int vertex;
        int weight;

        numNode(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }

        int getVertex() {
            return vertex;
        }

        int getWeight() {
            return weight;
        }
    }

    //int sparse = V;
    //int medium = (int)(V * Math.sqrt(V));
    //int dense = V * (V - 1) / 2;

    Map<Character, ArrayList<ABCNode>> sparseABCGraph = new HashMap<>();
    Map<Character, ArrayList<ABCNode>> denseABCGraph = new HashMap<>();

    static void addABCEdge(Map<Character, ArrayList<ABCNode>> graph, char u, char v, int w) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        graph.get(u).add(new ABCNode(v, w));
        graph.get(v).add(new ABCNode(u, w)); //undirected graph
    }
    static Map<Character, ArrayList<ABCNode>> ABCSparseGraph() {
        Map<Character, ArrayList<ABCNode>> graph = new HashMap<>();

        addABCEdge(graph, 'A', 'B', 4);
        addABCEdge(graph, 'A', 'C', 2);
        addABCEdge(graph, 'B', 'D', 5);
        addABCEdge(graph, 'C', 'D', 1);
        addABCEdge(graph, 'D', 'E', 3);
        addABCEdge(graph, 'E', 'F', 2);

        return graph;

    }
    static Map<Character, ArrayList<ABCNode>> ABCDenseGraph() {
        Map<Character, ArrayList<ABCNode>> graph = new HashMap<>();

        addABCEdge(graph, 'A', 'B', 2);
        addABCEdge(graph, 'A', 'C', 5);
        addABCEdge(graph, 'A', 'D', 1);
        addABCEdge(graph, 'A', 'E', 4);
        addABCEdge(graph, 'B', 'C', 3);
        addABCEdge(graph, 'B', 'D', 2);
        addABCEdge(graph, 'B', 'E', 6);
        addABCEdge(graph, 'C', 'D', 3);
        addABCEdge(graph, 'C', 'E', 1);
        addABCEdge(graph, 'D', 'E', 2);

        return graph;
    }

    public static ArrayList<ArrayList<numNode>> sparseNumGraph(int v) {

    ArrayList<ArrayList<numNode>> graph = new ArrayList<>();
    for(int i = 0 ; i < v ; i++)
    {
        graph.add(new ArrayList<>());
    }

    graph.get(0).add(new numNode(1,3));
    graph.get(1).add((new numNode(0,3)));

    graph.get(0).add(new numNode(2,6));
    graph.get(2).add(new numNode(0,6));

    graph.get(1).add(new numNode(3,2));
    graph.get(3).add(new numNode(1,2));

    graph.get(2).add(new numNode(4,4));
    graph.get(4).add(new numNode(2,4));

    graph.get(3).add(new numNode(5,7));
    graph.get(5).add(new numNode(3,7));

    graph.get(4).add(new numNode(6,1));
    graph.get(6).add(new numNode(4,1));

    graph.get(1).add(new numNode(4,5));
    graph.get(4).add(new numNode(1,5));

    return graph;
    }

    public static ArrayList<ArrayList<numNode>> denseNumGraph(int v)
    {
        ArrayList<ArrayList<numNode>> graph = new ArrayList<>();
        for(int i = 0; i < v; i++)
        {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new numNode(1, 3));
        graph.get(1).add(new numNode(0, 3));

        graph.get(0).add(new numNode(2, 2));
        graph.get(2).add(new numNode(0, 2));

        graph.get(0).add(new numNode(3, 6));
        graph.get(3).add(new numNode(0, 6));

        graph.get(0).add(new numNode(4, 5));
        graph.get(4).add(new numNode(0, 5));

        graph.get(0).add(new numNode(5, 4));
        graph.get(5).add(new numNode(0, 4));

        graph.get(1).add(new numNode(2, 1));
        graph.get(2).add(new numNode(1, 1));

        graph.get(1).add(new numNode(3, 2));
        graph.get(3).add(new numNode(1, 2));

        graph.get(1).add(new numNode(4, 4));
        graph.get(4).add(new numNode(1, 4));

        graph.get(1).add(new numNode(5, 7));
        graph.get(5).add(new numNode(1, 7));

        graph.get(2).add(new numNode(3, 3));
        graph.get(3).add(new numNode(2, 3));

        graph.get(2).add(new numNode(4, 6));
        graph.get(4).add(new numNode(2, 6));

        graph.get(2).add(new numNode(5, 5));
        graph.get(5).add(new numNode(2, 5));

        graph.get(3).add(new numNode(4, 2));
        graph.get(4).add(new numNode(3, 2));

        graph.get(3).add(new numNode(5, 4));
        graph.get(5).add(new numNode(3, 4));

        graph.get(4).add(new numNode(5, 1));
        graph.get(5).add(new numNode(4, 1));

        return graph;
    }



//    static Map<Integer, ArrayList<numNode>> genRandDenseGraph(int V, int E) {
//
//    Map<Integer, ArrayList<numNode>> graph = new HashMap<>();
//    Random rand = new Random();
//
//    for (int i = 0; i < V; i++) {
//        graph.put(i, new ArrayList<>());
//    }
//
//    int edgesAdded = 0;
//    while (edgesAdded < E) {
//        int u = rand.nextInt(V);
//        int v = rand.nextInt(V);
//
//        if (u == v) continue; // no self loops
//
//        int weight = rand.nextInt(10) + 1;
//
//        graph.get(u).add(new numNode(v, weight));
//        graph.get(v).add(new numNode(u, weight)); // undirected
//
//        edgesAdded++;
//    }
//
//    return graph;
//}
private static ArrayList<ArrayList<numNode>> matrixToList(int[][] m){
    int inf = Integer.MAX_VALUE;
    ArrayList<ArrayList<numNode>> returnList = new ArrayList<>();
    for(int i = 0; i<m.length; i++){
        ArrayList<numNode> innerList = new ArrayList<>();
        for(int j = 0; j<m.length; j++){
            if(m[i][j] != inf){
                numNode e = new numNode(j,m[i][j]);
                innerList.add(e);
            }
        }
        returnList.add(innerList);
    }
    return returnList;
}
    static int inf = Integer.MAX_VALUE;
    static int[][] sparseGraph1M = {
            {0,4,2,inf,inf,inf},
            {4,0,inf,5,inf,inf},
            {2,inf,0,1,inf,inf},
            {inf,5,1,0,3,inf},
            {inf,inf,inf,3,0,2},
            {inf,inf,inf,inf,2,0}
    };
    static int[][] sparseGraph2M = {
            {0,3,6,inf,inf,inf,inf},
            {3,0,inf,2,5,inf,inf},
            {6,inf,0,inf,4,inf,inf},
            {inf,2,inf,0,inf,7,inf},
            {inf,5,4,inf,0,inf,1},
            {inf,inf,inf,7,inf,0,inf},
            {inf,inf,inf,inf,1,inf,0}
    };
    static int[][] denseGraph1M = {
            {0,2,5,1,4},
            {2,0,3,2,6},
            {5,3,0,3,1},
            {1,2,3,0,2},
            {4,6,2,2,0}
    };
    static int[][] denseGraph2M = {
            {0,3,2,6,5,4},
            {3,0,1,2,4,7},
            {2,1,0,3,6,5},
            {6,2,3,0,2,4},
            {5,4,6,2,0,1},
            {4,7,5,4,1,0}
    };
static int[] PriorityQueueDijkstra(int v, ArrayList<ArrayList<numNode>> graph, int source)
{
   int dist[] = new int[v];
    Arrays.fill(dist, Integer.MAX_VALUE);
   dist[source] = 0;
   boolean[] vistited = new boolean[v];

    PriorityQueue<numNode> priorityQueue = new PriorityQueue<>(
            (v1,v2) -> Integer.compare(v1.getWeight(), v2.getWeight()));
    priorityQueue.add(new numNode(source,0));

   while(!priorityQueue.isEmpty())
   {
       numNode curr = priorityQueue.poll();
       int u = curr.getVertex();

       if(graph.get(u) == null) continue;

       if(vistited[u]) continue;
       vistited[u] = true;

       for(numNode n : graph.get(u))
       {
           int u2 = n.getVertex();
           if(dist[u] + n.getWeight() < dist[u2])
           {
               dist[u2] = n.getWeight() + dist[u];
               priorityQueue.add(new numNode(n.getVertex(),dist[u2]));
           }
       }
   }
   return dist;
}

    public static void main(String[] args) {
    Runtime runtime =Runtime.getRuntime();
        ArrayList<ArrayList<numNode>> sparseGraph1 = matrixToList(sparseGraph1M);
        int v1 = 18;
        ArrayList<ArrayList<numNode>> sparseGraph2 = matrixToList(sparseGraph2M);
        int v2 = 21;

        ArrayList<ArrayList<numNode>> denseGraph1 = matrixToList(denseGraph1M);
        int v3 = 25;
        ArrayList<ArrayList<numNode>> denseGraph2 = matrixToList(denseGraph2M);
        int v4 = 36;

        System.out.println("Benchmark One: Sparse Graph 1");
        long start = System.nanoTime();
        int[] result = PriorityQueueDijkstra(v1,sparseGraph1,0);
        long usedMem = runtime.totalMemory() - runtime.freeMemory();
        long duration = System.nanoTime() - start;
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Total Memory: " + usedMem+ " bytes");
        System.out.println("Result : " + Arrays.toString(result) + "\n");

        System.out.println("Benchmark Two: Sparse Graph 2");
        start = System.nanoTime();
        int[] result2 = PriorityQueueDijkstra(v2,sparseGraph2,0);
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        duration = System.nanoTime() - start;
        System.out.println("Total Time: " + duration + " ns");
        System.out.println("Total Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result2)+ "\n");

        System.out.println("BenchMark 3: Dense Graph 1");
        start = System.nanoTime();
        int [] result3 = PriorityQueueDijkstra(v3, denseGraph1, 0);
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        duration = System.nanoTime() -start;
        System.out.println("Total Time: " + duration+ " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result3) + "\n");

        System.out.println("BenchMark 4: Dense Graph 2");
        start = System.nanoTime();
        int [] result4 = PriorityQueueDijkstra(v4, denseGraph2, 0);
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        duration = System.nanoTime() -start;
        System.out.println("Total Time: " + duration+ " ns");
        System.out.println("Used Memory: " + usedMem+ " bytes");
        System.out.println("Result: " + Arrays.toString(result4) + "\n");
    }
}
