
public class Graphs
{

    static class Node 
    {
        char vertex;
        int weight;

        AdjListNode(char v, int w)
        {
            this.vertex = v;
            this.weight = w; 
        }
    }

    int sparse = V;      
    int medium = (int)(V * Math.sqrt(V));
    int dense = V * (V - 1) / 2;


 
    Map<Character, ArrayList<AdjListNode>> sparseABCGraph = new HashMap<>();
    Map<Character, ArrayList<AdjListNode>> denseABCGraph = new HashMap<>();

    static void addABCEdge(Map<Character, ArrayList<Node>> graph, char u, char v, int w)
    {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v,new ArrayList<>());

        graph.get(u).add(new Node(v,w));
        //graph.get(v).add(new AdjListNode(u,w)); //undirected graph 
    }

    static voide addNumEdge

    static Map<Character,ArrayList<Node> ABCSparseGraph 
    {
        Map<Character, ArrayList<Node> graph = new HashMap<>();

        addABCEdge(graph, 'A', 'B',4);
        addABCEdge(graph, 'A', 'C',2);
        addABCEdge(graph, 'B', 'D',5);
        addABCEdge(graph, 'C', 'D',1);
        addABCEdge(graph, 'D', 'E',3);
        addABCEdge(graph, 'E', 'F',2);

        return graph; 

    }

    static Map<Character, ArrayList<Node> ABCDenseGraph 
    {
        Map<Character, ArrayList<Node> graph = new HashMap<>();

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

    static Map<Integer, ArrayList<AdjListNode>> genRandDenseGraph(int V, int E) {
    
    Map<Integer, ArrayList<AdjListNode>> graph = new HashMap<>();
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

        graph.get(u).add(new AdjListNode(v, weight));
        graph.get(v).add(new AdjListNode(u, weight)); // undirected

        edgesAdded++;
    }

    return graph;
}
}
