import java.util.ArrayList;

public class LowLinks {
}

class BridgesAndArticulationPoints{
    ArrayList<ArrayList<Integer>> graph;
    boolean []visited;
    int []in;
    boolean []isCut;
    int []par;
    int []lowlinks;
    int T;

    BridgesAndArticulationPoints(int n){
        graph = new ArrayList<>(n+1);
        visited = new boolean[n+1];
        in = new int[n+1];
        isCut = new boolean[n+1];
        par = new int[n+1];
        lowlinks = new int[n+1];
        T = 0;
    }

    void dfs(int s, int p){
        par[s] = p;
        in[s] = T++;
        lowlinks[s] = in[s];
        visited[s] = true;
        for(int v: graph.get(s)){
            if(!visited[v]){
                dfs(v, s);
                lowlinks[s] = Math.min(lowlinks[s], lowlinks[v]);
            } else if(v!=p){
                //This is a back edge
                lowlinks[s] = Math.min(lowlinks[s], lowlinks[v]);
            }
        }

        if(lowlinks[s] < in[s]){
            isCut[s] = true;
        }
    }
}

