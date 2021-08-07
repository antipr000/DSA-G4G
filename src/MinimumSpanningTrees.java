import java.util.*;

public class MinimumSpanningTrees {
}

class Edge{
    int u;
    int v;
    int index;
    int wt;

    Edge(int u, int v, int w, int i){
        this.u = u;
        this.v = v;
        wt = w;
        index = i;
    }
}

class KruskalsAlgo{
    boolean []findMinimumSpanningTree(Edge[] edges, int n){
        int m = edges.length;
        boolean []isTaken = new boolean[m];
        DSUImpl dsu = new DSUImpl(n);
        Comparator comp = new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return (e1.wt - e2.wt);
            }
        };
        Arrays.sort(edges, comp);

        for(Edge e: edges){
            int ru = dsu.findRoot(e.u);
            int rv = dsu.findRoot(e.v);
            if(ru == rv){
                continue;
            }
            isTaken[e.index] = true;
            dsu.combine(ru, rv);
        }

        return isTaken;
    }
}


class PrimsAlgo{
    /*
    * edge_wt < curr_wt
    *
    * 2 -----3----5
    *
    * 2------1-----4
    * */
    ArrayList<ArrayList<WeightedNode>> graph;

    public void primsImpl(int n){
        int []d = new int[n+1];
        for(int i=0;i<=n;i++) d[i] = Integer.MAX_VALUE;

        PriorityQueue<WeightedNode> pq = new PriorityQueue<>(); //Todo comparator for sorting wrt edges
        pq.add(new WeightedNode(0, 0));
        boolean [] visited = new boolean[n+1];

        while(!pq.isEmpty()){
            WeightedNode top = pq.poll();
            if(visited[top.node]) continue;
            visited[top.node] = true;
            for(WeightedNode v: graph.get(top.node)){
                if(v.wt < d[v.node]){
                    pq.add(v);
                }
            }
        }
    }
}