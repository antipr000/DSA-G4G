import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphs {
    ArrayList<Integer> []graph;
    boolean [] visited;

    Graphs(int n){
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
    }

    public void addAEdge(int u, int v){
        graph[u].add(v);
        graph[v].add(u);
    }


    void dfs(int s){
        visited[s] = true;
        for(int v: graph[s]){
            if(!visited[v]){
                dfs(v);
            }
        }
    }


    void bfs(int s){
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()){
            int u = q.poll();
            for(int v: graph[u]){
                if(!visited[v]){
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }
}
