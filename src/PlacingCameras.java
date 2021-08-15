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

class PlacingCameras{
    final int N = 100005;
    int [][][]dp;
    ArrayList<Integer>[]graph;
    
    PlacingCameras(){
        dp = new int[N][2][2];
        graph = new ArrayList<Integer>[N];
    }
    
    void dfs(int s, int p, int placeCamera, int hasCamera){
        /*
            placeCamera -> 0/1 depending on whether my current node has has has camera
            hasCamera -> 0/1 depending on whether my parent has camera
        */
        
        if(dp[s][placeCamera][hasCamera] != -1) return;
        
        /*
            Case: 0 1 / 1 0 / 1 1 -> Child nodes might or might not have camera -> min(dp[v][0][placeCamera], dp[v][1][placeCamera])
            Case: 0 0 -> Atleast one child should have camera -> min(dp[v][1][0] - dp[v][0][0]) + 
        */
        
        int sum = 0;
        int minVal = Integer.MAX_VAL;
        boolean isLeaf = true;
        for(int v: graph[s]){
            if(v!=p){
                isLeaf = false;
                dfs(v, s, 0, placeCamera);
                dfs(v, s, 1, placeCamera);
                sum += Math.min(dp[v][0][placeCamera], dp[v][1][placeCamera]);
                int val = dp[v][1][placeCamera] - dp[v][0][placeCamera];
                if(val < minVal) minVal = val;
                i++;
            }
        }
        
        if(isLeaf){
            if(hasCamera == 0 && placeCamera == 0){
                dp[s][placeCamera][hasCamera] = Integer.MAX_VAL;
                return;
            }
        }
        
        if(hasCamera > 0 || placeCamera > 0){
            dp[s][placeCamera][hasCamera] = sum + placeCamera;
            return;
        }
        
        //0 0 Case
        boolean taken = false;
        for(int v: graph[s]){
            if(v!=p){
                int val = dp[v][1][placeCamera] - dp[v][0][placeCamera];
                if((val == minVal) && !taken){
                    taken = true;
                    dp[s][placeCamera][hasCamera] += dp[v][1][placeCamera];
                } else{
                    dp[s][placeCamera][hasCamera] += Math.min(dp[v][0][placeCamera], dp[v][1][placeCamera]);
                }
            }
        }
        
        
    }
    
    
    int getMinCamera(int root){
        dfs(root, 1, 0);
        dfs(root, 0, 0);
        return Math.min(dp[root][1][0], dp[root][0][0]);
    }
    
    
    
    
}
