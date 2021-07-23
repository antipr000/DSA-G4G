import java.util.ArrayList;

public class DistinctIntegers {
    public static void main(String[] args) {
        int n = 5;
        int [] arr1 = {1, 2, 3, 4, 5};
        int [] arr2 = {2, 1, 3, 1, 4};
        DistinctIntegerTree tree1 = new DistinctIntegerTree(n);

        tree1.build(arr1, n);

        DistinctIntegerTree tree2 = new DistinctIntegerTree(n);

        tree2.build(arr2, n);

        boolean []res1 = tree1.query(0, 2, n);
        boolean []res2 = tree2.query(3, 4, n);
        System.out.println(DistinctIntegerTree.countDistinct(DistinctIntegerTree.combine(res1, res2)));

        res1 = tree1.query(0, 2, n);
        res2 = tree2.query(2, 4, n);
        System.out.println(DistinctIntegerTree.countDistinct(DistinctIntegerTree.combine(res1, res2)));
    }
}


class DistinctIntegerTree{

    boolean [][]st;

    DistinctIntegerTree(int n){
        st = new boolean[4*n][5001];

    }

    public static boolean[] combine(boolean[] lChildVal, boolean[] rChildVal){
        boolean [] temp = new boolean[5001];
        for(int i=0;i<=5000;i++){
            temp[i] = (lChildVal[i] | rChildVal[i]);
        }
        return temp;
    }

    private void _build(int []arr, int beg, int end, int pos){
        if(beg == end){
            st[pos][arr[beg]] = true;
            //st[pos] = arr[beg];
            return;
        }
        int mid = (beg + end)/2;
        _build(arr, beg, mid, 2*pos+1);
        _build(arr, mid+1, end, 2*pos+2);
        st[pos] = combine(st[2*pos+1], st[2*pos+2]);
        //st[pos] = combine(st[2*pos+1], st[2*pos+2])
    }

    private boolean[] _query(int beg, int end, int l, int r, int pos){
        if(l<=beg && r>=end){
            return st[pos];
        } else if(beg>r || end<l){
            return new boolean[5001];
        }
        int mid = (beg + end)/2;
        return combine(_query(beg, mid, l, r, 2*pos+1), _query(mid+1, end, l, r, 2*pos+2));
    }

    private void _update(int beg, int end, int idx, int val, int oldValue, int pos){
        if(beg == end && beg == idx){
            st[pos][oldValue] = false;
            st[pos][val] = true;
            //st[pos] = arr[beg]
            return;
        }
        int mid = (beg + end)/2;
        if(idx<=mid) _update(beg, mid, idx, val, oldValue, 2*pos+1);
        else _update(mid+1, end, idx, val, oldValue, 2*pos+2);

        st[pos] =  combine(st[2*pos+1], st[2*pos+2]);
        //st[pos] = combine(st[2*pos+1], st[2*pos+2])
    }

    static int countDistinct(boolean []res){
        int count = 0;
        for(int i=0;i<res.length;i++){
            if(res[i]) count ++;
        }

        return count;
    }

    public void build(int []arr, int n){
        //O(n)
        _build(arr, 0, n-1, 0);
    }

    public boolean[] query(int l, int r, int n){
        //O(logN)
        return _query(0, n-1, l, r, 0);
    }

    public void update(int idx, int val, int oldVal, int n){
        //O(logN)
        _update(0, n-1, idx, val, oldVal, 0);
    }
}
