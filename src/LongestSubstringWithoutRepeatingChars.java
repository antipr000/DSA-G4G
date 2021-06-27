import java.util.*;

//Problem: https://leetcode.com/problems/continuous-subarray-sum/

public class LongestSubstringWithoutRepeatingChars {
    public static void main(String[] args) {
        /*
             Pigeon Hole principle
        * Given s, find maximum length such that there exists a substring of that length with only unique characters
        *
        * No of distinct characters: 26, if any character possible: 256
        *
        * We know, maximum length won't exceed 26
        * So we can iterate for all possible maximum lengths and check if there is a substring
        * of that length having all distinct characters
        *
        * Given a length , find if there exists a substring of that length having all distinct character
        * abccdabcd, a->0, b->1, c->2, ..., z->25
        * freq[26],
        */
    }
}

class LongestSubstring{
    public int maxLength(String s){
        int N = s.length();

        if(N == 0) return 0;

        for(int k = Math.min(26, N); k>=1; k--){
            if(isSubstringAvailable(k, s)){
                return k;
            }
        }

        return 0;
    }

    private boolean isSubstringAvailable(int k, String s){
        int N = s.length();

        int []freq = new int[26];

        for(int i=0;i<k;i++){
            int idx = s.charAt('i') - 'a';
            freq[idx] += 1;
        }
        /*
        *  abbcde  [0, 1, 1, 1, 0] k=3
        * [0, k-1]
        * [1, k]
        * [2, k+1]
        * abcdeabcde
        * */

        if(checkIfAllDistinct(freq)) return true;

        for(int i=k;i<N;i++){
            int idx = s.charAt(i) - 'a';
            int idx2 = s.charAt(i-k) - 'a';
            freq[idx] ++;
            freq[idx2] --;

            if(checkIfAllDistinct(freq)) return true;
        }

        return false;
    }

    private boolean checkIfAllDistinct(int []freq){
        for(int i=0;i<26;i++) if(freq[i] > 1) return false;

        return true;
    }
}