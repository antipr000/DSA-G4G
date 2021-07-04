import java.util.ArrayList;

public class PatternMatching {
    public static void main(String[] args) {
        String text = "ABCABCABD";
        String pattern = "ABCABD";

        KMPAlgo kmp = new KMPAlgo();
        System.out.println("Does text match to pattern? " + kmp.matchPatternToText(text, pattern));
    }
}

class KMPAlgo{
    public boolean matchPatternToText(String text, String pattern){
        ArrayList<Integer> lps = computeLpsArray(pattern);
        int i=0 , j=0;

        while(i<text.length()){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
                if(j == pattern.length()){
                    return true;
                }
            } else{
                if(j>0){
                    j = lps.get(j-1);
                } else{
                    i++;
                }
            }
        }

        return false;
    }

    private ArrayList<Integer> computeLpsArray(String s){
        int n = s.length();
        ArrayList<Integer> lps = new ArrayList<>();
        for(int i=0;i<n;i++) lps.add(0);
        int i = 1, len = 0;
        while(i<s.length()){
            if(s.charAt(i) == s.charAt(len)){
                len++;
                lps.set(i, len);
                i++;
            } else{
                if(len>0){
                    len = lps.get(len-1);
                } else{
                    lps.set(i, 0);
                    i++;
                }
            }
        }
        return lps;
    }
}