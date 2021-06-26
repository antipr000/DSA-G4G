
import java.util.*;

// Problem: https://www.hackerearth.com/practice/algorithms/searching/binary-search/practice-problems/algorithm/gaurav-and-subarray-3-787fb90a/

public class GauravAndSubarray {

    public static void main(String args[] ) throws Exception {

        // Write your code here
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();

        int [] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = scan.nextInt();

        Utils util = new Utils();

        util.populateCountBits(arr, n);

        while (q-- > 0){
            int k = scan.nextInt();
            System.out.println(util.binarySearch(n , k));
        }

        System.out.println(n);

        scan.close();


    }
}

class Utils {
    ArrayList<Integer> countBits;

    Utils(){
        this.countBits = new ArrayList<>();
    }

    private void debug(){
        System.out.println("Count bits array is " + Arrays.toString(new ArrayList[]{this.countBits}));
    }

    public void populateCountBits(int []arr, int n){
        for(int i=0;i<n;i++){
            int curr = arr[i];
            int num = 0;
            for(int j=0;j<31;j++){
                if((curr&(1<<j)) > 0) num+=1;
            }
            this.countBits.add(num);
        }

        debug();
    }

    public int binarySearch(int n, int k){
        int lo = 1, hi = n;
        if(P(lo, n, k)) return lo;
        if(!P(hi, n, k)) return -1;

        while(lo < hi-1){
            int mid = (lo + hi) / 2;
            if(P(mid, n, k)) hi = mid;
            else lo = mid;
        }

        return hi;
    }

    private boolean P(int x, int n, int k){
        int bits = 0;
        for(int i=0;i<x;i++) bits += this.countBits.get(i);

        if(bits >= k) return true;

        for(int i=x;i<n;i++){
            bits += this.countBits.get(i) - this.countBits.get(i-x);
            if(bits >= k) return true;
        }

        return false;
    }

}
