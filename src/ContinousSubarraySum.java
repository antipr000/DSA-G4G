import java.lang.reflect.Array;
import java.util.*;

//Problem: https://leetcode.com/problems/continuous-subarray-sum/

public class ContinousSubarraySum {
    public static void main(String[] args) {
        /*
        * Find if there exists a subarray of length at least 2 such that the sum of that subarray is divisible by k
        *
        * sum of a range [a, b] : prefix[b] - prefix[a-1]
        * sum % k : (prefix[b] - prefix[a-1]) % k;
        * m and n are any integers (m-n) is divisible by k
        * m-n = k*q, m = k*q1 + r1 , n = k*q2 + r2
        * m-n = k*(q1-q2) + (r1-r2) = k*q
        * or r1-r2 = 0 or r1 = r2
        * m-n is divisible by k iff m%k = n%k
        *
        * sum of range [a, b] is divisible by k iff prefix[b]%k = prefix[a-1]%k
        *
        * Currently we are at index i, we fix i to be the RHS of the range
        * [j, i] : Here i is fixed and j is variable
        * We need to find if there is a subarray that ends at i and is divisible by k
        * Or in other words is there a j that exists such that j<i-1 and prefix[i] % k = prefix[j] % k : [j+1, i]
        * [j+1, i] is a subarray of length >= 2 and is divisible by k
        * */
    }
}

class FindIfSubarrayExists{
    ArrayList<Integer> prefix;

    FindIfSubarrayExists(){
        this.prefix = new ArrayList<>();
    }

    public boolean doesSubarrayExist(int []arr, int n, int k){
        int sum = 0;
        for(int i: arr){
            sum += i;
            this.prefix.add(sum);
        }

        int []freq = new int[k];
        for(int i=0;i<k;i++) freq[i] = 0;
        /*
        * When I reach any i, my freq array should only hold records upto i-2
        * i+1 freq array should hold records upto i-1
        * [23,2,6,4,7] ,  k = 6
        * prefix: [23, 25, 31, 35, 42]  freq: [0, 1, 0, 0, 0, 1]
        * i: 1 -> remainder = 1 , i=0:  remainder = 5
        * i: 2 -> remainder = 1, i=1: 1, prefix[2]%k = prefix[1]%k or the range [2,2] is divisible by k
        * i: 3 -> remainder = 5 , prefix[3] - prefix[0] is divisible by 6. [1,3]
        * */
        for(int i=1; i<n; i++){
            int remainder = this.prefix.get(i) % k;
            if(freq[remainder] > 0){
                return true;
            }

            freq[this.prefix.get(i-1)%k] += 1;
        }

        return false;
    }


}

/*
* Improved: Find the length of the longest subarray that is divisible by k if exists else return -1;
* */