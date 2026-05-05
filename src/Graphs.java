import java.lang.classfile.components.ClassPrinter;
import java.util.*;

public class Graphs
{

    static class ABCNode
    {
        char vertex;
        int weight;

        ABCNode(char v, int w)
        {
            this.vertex = v;
            this.weight = w; 
        }
    }

    static class numNode
    {
        int vertex;
        int weight;

        numNode (int v, int w)
        {
            this.vertex = v;
            this.weight = w;
        }
        int getVertex() {return vertex;}
        int getWeight() { return weight; }
    }

    //int sparse = V;
    //int medium = (int)(V * Math.sqrt(V));
    //int dense = V * (V - 1) / 2;


 
    Map<Character, ArrayList<ABCNode>> sparseABCGraph = new HashMap<>();
    Map<Character, ArrayList<ABCNode>> denseABCGraph = new HashMap<>();

    static void addABCEdge(Map<Character, ArrayList<ABCNode>> graph, char u, char v, int w)
    {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v,new ArrayList<>());

        graph.get(u).add(new ABCNode(v,w));
        graph.get(v).add(new ABCNode(u,w)); //undirected graph
    }

    static void addNumEdge(){}

    static Map<Character,ArrayList<ABCNode>> ABCSparseGraph()
    {
        Map<Character, ArrayList<ABCNode>> graph = new HashMap<>();

        addABCEdge(graph, 'A', 'B',4);
        addABCEdge(graph, 'A', 'C',2);
        addABCEdge(graph, 'B', 'D',5);
        addABCEdge(graph, 'C', 'D',1);
        addABCEdge(graph, 'D', 'E',3);
        addABCEdge(graph, 'E', 'F',2);

        return graph; 

    }

    static Map<Character, ArrayList<ABCNode>> ABCDenseGraph()
    {
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

    static Map<Integer, ArrayList<numNode>> genRandDenseGraph(int V, int E) {
    
    Map<Integer, ArrayList<numNode>> graph = new HashMap<>();
    Random rand = new Random();

    for (int i = 0; i < V; i++) {
        graph.put(i, new ArrayList<>());
    }

    int edgesAdded = 0;
    while (edgesAdded < E) {
        int u = rand.nextInt(V);
        int v = rand.nextInt(V);

        if (u == v) continue; // no self loops

        int weight = rand.nextInt(10) + 1;

        graph.get(u).add(new numNode(v, weight));
        graph.get(v).add(new numNode(u, weight)); // undirected

        edgesAdded++;
    }

    return graph;
}

static int[] PriorityQueueDijkstra(int v, ArrayList<ArrayList<numNode>> graph, int source)
{
   int dist[] = new int[v];
    Arrays.fill(dist, Integer.MAX_VALUE);
   dist[source] = 0;
   boolean[] vistited = new boolean[v];

    PriorityQueue<numNode> priorityQueue = new PriorityQueue<>(
            (v1,v2) -> v1.getWeight() - v2.getWeight());
    priorityQueue.add(new numNode(source,0));

   while(!priorityQueue.isEmpty())
   {
       numNode curr = priorityQueue.poll();
       int u = curr.getVertex();

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
}
