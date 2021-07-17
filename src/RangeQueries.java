import java.util.ArrayList;

public class RangeQueries {
    public static void main(String[] args) {
        int []arr = {2, 3, 1, 4, 3, 3, 2, 1, 4};
        SegmentTress st = new SegmentTress(arr.length);
        st.build(arr, arr.length);

        System.out.println("Value for a range is: " + st.query(2, 6, arr.length));
        st.update(3, 2, arr.length);
        System.out.println("Value for a range is: " + st.query(2, 6, arr.length));
    }
}

class SegmentTress{
    ArrayList<Integer> st;

    SegmentTress(int n){
        st = new ArrayList<>(4*n);
        for(int i=0;i<4*n;i++) st.add(0);
    }

    int combine(int lChildVal, int rChildVal){
        return Math.max(lChildVal, rChildVal);
    }

    private void _build(int []arr, int beg, int end, int pos){
        if(beg == end){
            st.set(pos, arr[beg]);
            //st[pos] = arr[beg];
            return;
        }
        int mid = (beg + end)/2;
        _build(arr, beg, mid, 2*pos+1);
        _build(arr, mid+1, end, 2*pos+2);
        st.set(pos, combine(st.get(2*pos+1), st.get(2*pos+2)));//
        //st[pos] = combine(st[2*pos+1], st[2*pos+2])
    }

    private int _query(int beg, int end, int l, int r, int pos){
        if(l<=beg && r>=end){
            return st.get(pos);
        } else if(beg>r || end<l){
            return 0;
        }
        int mid = (beg + end)/2;
        return combine(_query(beg, mid, l, r, 2*pos+1), _query(mid+1, end, l, r, 2*pos+2));
    }

    private void _update(int beg, int end, int idx, int val, int pos){
        if(beg == end && beg == idx){
            st.set(pos, val);
            //st[pos] = arr[beg]
            return;
        }
        int mid = (beg + end)/2;
        if(idx<=mid) _update(beg, mid, idx, val, 2*pos+1);
        else _update(mid+1, end, idx, val, 2*pos+2);

        st.set(pos, combine(st.get(2*pos+1), st.get(2*pos+2)));
        //st[pos] = combine(st[2*pos+1], st[2*pos+2])
    }

    public void build(int []arr, int n){
        //O(n)
        _build(arr, 0, n-1, 0);
    }

    public int query(int l, int r, int n){
        //O(logN)
        return _query(0, n-1, l, r, 0);
    }

    public void update(int idx, int val, int n){
        //O(logN)
        _update(0, n-1, idx, val, 0);
    }
}
