class SegmentTree{
    final int N = 100005;
    int []st;
    
    SegmentTree(){
        st = new int[2*N];
    }
    
    void build(int []arr, int n=N){
        for(int i=0;i<n;i++){
            st[i+n] = arr[i];
        }
        
        for(int i=n-1;i>0;i--){
            st[i] = max(st[i<<1], st[(i<<1)|1]); //st[i] = st[2*i] + st[2*i+1]
        }
    }
    
    void update(int idx, int val, int n=N){
        idx += n;
        st[idx] = max(st[idx], val);
        for(; idx>1; idx>>=1){
            st[idx>>1] = max(st[idx], st[idx^1]);
        }
    }
    
    //In the range [l, r)
    int query(int l, int r, int n=N){
        int res = 0;
        for(l+=n, r+=n; l<r; l>>=1, r>>=1){
            if(l&1){
                res = max(res, st[l++]);
            }
            if(r&1){
                res = max(res, st[--r]);
            }
        }
        
        return res;
    }
}

class Solution {
    
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        
        int dp = new int[n];
        SegmentTree st = new SegmentTree();
        for(int i=0; i<n; i++){
            //dp[i] = max(dp[j]) + 1 where A[j] < A[i]
            
            dp[i] = st.query(0, A[i]) + 1;
            st.update(A[i], dp[i]);
        }
        
        int max_val = *max_element(dp, dp+n);
        return max_val;
    }
}