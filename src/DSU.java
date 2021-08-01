public class DSU {
    int []par;
    int []cnt;

    DSU(int n){
        par = new int[n+1];
        cnt = new int[n+1];
        for(int i=0;i<=n;i++) {
            par[i] = i;
            cnt[i] = 1;
        }
    }

    int findRoot(int x){
        while (x!=par[x]){
            par[x] = par[par[x]];
            x = par[x];
        }

        return x;
    }

    void combine(int x, int y){
        int rx = findRoot(x);
        int ry = findRoot(y);
        if(rx == ry) return;
        par[ry] = rx;
        cnt[rx] += cnt[ry];
    }
}
