import java.util.*;

//Problem: https://leetcode.com/problems/contains-duplicate-iii/

public class ContainsDuplicate {
    public static void main(String[] args) {

    }
}

class DuplicateSolution{
    public boolean containsNearByAlmostDuplicate(int []nums, int k, int t){
        /*
        *  We fix j, we only consider i<j without loss of generality
        * We fix j
        * We make sure that our set only contains entries from [j-k, j-1]
        * For current j we need to find closest element to it in our set
        * Why closest? Because if there is any i such that abs(nums[j] - nums[i]) <= t then with closest element
        * as well it will satisfy the property
        * */

        TreeMap<Integer, Integer> elementsInRange = new TreeMap<>();

        elementsInRange.put(nums[0], 1);
        for(int i=1; i<nums.length; i++){
            if(elementsInRange.containsKey(nums[i])) return true;
            int lowKey = elementsInRange.lowerKey(nums[i]);
            int highKey = elementsInRange.higherKey(nums[i]);

            if(elementsInRange.lowerKey(nums[i]) != null && Math.abs(lowKey - nums[i]) <= t) return true;
            if(elementsInRange.higherKey(nums[i]) != null && Math.abs(highKey - nums[i]) <= t) return true;

            if(i>=k){
                //i+1 -> [i-k+1, i] <-- [i-k, i-1]
               elementsInRange.replace(nums[i-k], elementsInRange.get(nums[i-k]) - 1);
               if(elementsInRange.get(nums[i-k]) == 0) elementsInRange.remove(nums[i-k]);
            }

            if(elementsInRange.containsKey(nums[i])){
                elementsInRange.replace(nums[i], elementsInRange.get(nums[i]) + 1);
            } else{
                elementsInRange.put(nums[i], 1);
            }

        }
        return false;
    }
}
