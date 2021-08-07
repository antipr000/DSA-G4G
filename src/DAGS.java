import java.util.ArrayList;
import java.util.Stack;

public class DAGS {
}

class TopologicalSort{
    ArrayList<ArrayList<Integer>> graph;
    Stack<Integer> st;
    boolean []visited;

    TopologicalSort(int n){
        graph = new ArrayList<>(n+1);
        visited = new boolean[n+1];
    }

    void dfs(int s){
        visited[s] = true;
        for(int v: graph.get(s)){
            if(!visited[v]) dfs(v);
        }

        st.push(s);
    }

    void topoSort(int n){

        for(int i=1;i<=n;i++){
            if(!visited[i]) dfs(i);
        }
    }


}