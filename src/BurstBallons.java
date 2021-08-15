class Solution {
    
    private int compute(int []A, int [][]dp, int i, int j, int k){
        int leftVal = (i>0) ? A[i-1] : 1;
        int rightVal = (j<A.length-1) ? A[j+1] : 1;
        
        int leftDp = (k>0) ? dp[i][k-1] : 0;
        int rightDp = (k<A.length-1) ? dp[k+1][j] : 0;
        return leftDp + rightDp + A[k] * leftVal * rightVal;
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        int [][]dp = new int[n][n];
        
        for(int i=0; i<n; i++){
            dp[i][i] = nums[i];
        }
        
        /*
        
            dp[i][j] = max(dp[i][k-1] + dp[k+1][j] + A[i-1]*A[j+1]*A[k])
        */
        
        for(int i=0;i<n;i++){
            for(int j=i+1; j<n; j++){
                dp[i][j] = 0;
                
                for(int k = i; k <= j; k++){
                    dp[i][j] = Math.max(dp[i][j], compute(A, dp, i, j, k));
                }
            }
        }
        
        return dp[0][A.length-1];
    }
}
