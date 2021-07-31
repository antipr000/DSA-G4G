import java.util.*;
import java.util.LinkedList;

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

class Cell{
    int row;
    int col;

    Cell(int row, int col){
        this.row = row;
        this.col = col;
    }
}


class MatrixGraph{

    private int [][]mat;
    private int N, M;
    int [][]distance;

    MatrixGraph(int [][]mat){
        this.mat = new int[mat.length][mat[0].length];
        distance = new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                this.mat[i][j] = mat[i][j];
                distance[i][j] = -1;
            }

        }
        N = mat.length;
        M = mat[0].length;
    }

    private boolean isValidCell(int row, int col){
        if(row < 0 || row >= N || col < 0 || col >= M || mat[row][col] == 0){
            return false;
        }

        return true;
    }

    private void bfs(){
        Queue<Cell> q = new LinkedList<Cell>();
        q.add(new Cell(0, 0));
        List<List<Integer>> moves;
        moves = Arrays.asList(Arrays.asList(1, 0), Arrays.asList(0, 1), Arrays.asList(-1, 0), Arrays.asList(0, -1));
        distance[0][0] = 0;
        while(!q.isEmpty()){
            Cell curr = q.poll();
            for(List<Integer> move: moves){
                int nextRow = curr.row + move.get(0);
                int nextCol = curr.col + move.get(1);
                if(isValidCell(nextRow, nextCol) && distance[nextRow][nextCol] == -1){
                    distance[nextRow][nextCol] = distance[curr.row][curr.col] + 1;
                    q.add(new Cell(nextRow, nextCol));
                }
            }

        }
    }

    public int getLeastDistance(){
        bfs();
        return distance[N-1][M-1];
    }

}

/*
*     src = 231
*     destination = 0
*      +1, -1, /2

* /
 */