import java.util.*;
public class DIjk1 {
    List<List<Edge>> listGraph = new ArrayList<>();
    int[][] matrixGraph;

    private int[] dijkstra (List<List<Edge>> g, int s){
        int[] distList = new int[g.size()];
        boolean[] visited = new boolean[g.size()];
        for(int i = 0; i<distList.length-1; i++){
            distList[i] = Integer.MAX_VALUE;
        }
        distList[s] = 0;
        List<Edge> curr = g.get(s);
        return null;
    }

    private int[] dijkstra (int[][] g, int s){
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
    public static void main(String[] args) {

    }
}
