20/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
	}
}

class LCA{
    final int N = 100005;
    final int LGN = 20;
    int [][]dp;
    int []height;
    ArrayList<Integer>[]g;
    
    LCA(){
        dp = new int[LGN][N];
        height = new int[N];
        g = new ArrayList<Integer>[N];
    }
    
    void dfs(int s, int p){
        height[s] = height[p] + 1;
        dp[0][s] = p;
        for(int i=1; i<LGN; i++){
            dp[i][s] = dp[i-1][dp[i-1][s]];
        }
        
        for(int v: g[s]){
            if(v!=p){
                dfs(v, s);
            }
        }
    }
    
    
    int lift(int u, int h){
        for(int i=0; i<LGN; i++){
            if(h&(1<<i)){
                u = dp[i][u];
            }
        }
        
        return u;
    }
    
    
    int lca(int u, int v){
        if(height[v] > height[u]){
            //swap u and v
            u = u^v; //u^v
            v = u^v; // u 
            u = u^v; // v
        }
        
        u = lift(u, height[u] - height[v]);
        
        if(u == v) return v;
        
        for(int i=LGN-1; i>=0; i--){
            if(dp[i][u] != dp[i][v]){
                u = dp[i][u];
                v = dp[i][v];
            }
        }
        
        return dp[0][u];
    }
    
}
