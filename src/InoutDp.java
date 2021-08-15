/* package codechef; // don't place package name! */

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


class InoutDP{
    final int N = 100005;
    ArrayList<Integer> g;
    int []in;
    int []out;
    
    InoutDP(){
        g = new ArrayList<Integer> [N];
        in = new int[N];
        out = new int[N];
    }
    
    
    void dfs1(int s, int p){
        in[s] = 0;
        for(int v: g[s]){
            if(v!=p){
                dfs1(v, s);
                in[s] = Math.max(in[s], in[v]+1);
            }
        }
    }
    
    void dfs2(int s, int p){
        ArrayList<Integer> inValues;
        for(int v: g[s]){
            if(v!=p){
                inValues.add(in[v]);
            }
        }
        int n = inValues.size();
        ArrayList<Integer> prefixMax;
        ArrayList<Integer> suffixMax;
        int pre = 0;
        for(int i=0; i<n; i++){
            prefixMax.add(pre);
            pre = Math.max(pre, inValues[i]);
        }
        
        int suf = 0;
        for(int i=n-1;i>=0; i--){
            suffixMax.add(suf);
            suf = Math.max(suf, inValues[i]);
        }
        
        int i = 0;
        for(int v: g[s]){
            if(v!=p){
                out[v] = out[s] + 1;
                int maxInValue = Math.max(prefixMax.get(i), suffixMax.get(i));
                out[v] = Math.max(out[v], 2 + maxInValue);
                i++;
            }
        }
    }
    
    
    
    
}
