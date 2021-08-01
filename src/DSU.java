import java.util.Arrays;
import java.util.Scanner;

public class DSU {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        sc.nextLine();

        int [][]edges = new int[m][2];
        boolean []isBroken = new boolean[m];
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            sc.nextLine();
            edges[i] = new int[]{u, v};
        }

        DSUImpl dsu = new DSUImpl(n);

        int q = sc.nextInt();
        sc.nextLine();
        int []queries = new int[q];
        for(int i=0; i<q; i++){
            int index = sc.nextInt();
            isBroken[index] = true;
            queries[i] = index;
        }

        for(int i=0; i<m; i++){
            if(!isBroken[i]){
                dsu.combine(edges[i][0], edges[i][1]);
            }
        }

        int []ans = new int[q];
        ans[q-1] = dsu.maxVal;

        for(int i=q-1;i>0;i--){
            int index = queries[i];
            dsu.combine(edges[index][0], edges[index][1]);
            ans[i-1] = dsu.maxVal;
        }


        for(int v: ans){
            System.out.print(v + " ");
        }

        System.out.println("");

    }
}

class DSUImpl{
    int []par;
    int []cnt;
    int maxVal;

    DSUImpl(int n){
        par = new int[n+1];
        cnt = new int[n+1];
        for(int i=0;i<=n;i++) {
            par[i] = i;
            cnt[i] = 1;
        }

        maxVal = 1;
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
        maxVal = Math.max(maxVal, cnt[rx]);
    }
}
