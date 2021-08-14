#include <bits/stdc++.h>
using namespace std;

//Problem Link: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/

int maxSumOfThreeSubarrays(vector<int>& nums, int k) {
    int n = nums.size();

    vector<int> prefix(n, 0);
    vector<int> max_l(n, 0);
    vector<int> max_r(n, 0);

    prefix[0] = nums[0];
    for(int i=1;i<n;i++){
        prefix[i] = prefix[i-1] + nums[i];
    }

    max_l[k-1] = prefix[k-1];

    for(int i=k;i<n;i++){
        max_l[i] = max(max_l[i-1], prefix[i] - prefix[i-k]);
    }

    max_r[n-k] = prefix[n-1];
    if((n-k-1) >= 0) max_r[n-k] -= prefix[n-k-1];

    for(int i=n-k-1; i>=0; i--){
        max_r[i] = max(max_r[i], prefix[i+k-1] - (i > 0 ? prefix[i-1] : 0));
    }

    int ans = 0;
    vector<int> best;

    for(int i=k; (i+k-1)<n-k; i++){
        ans = max(ans, (max_l[i-1] + max_r[i+k] + prefix[i+k-1] - prefix[i-1]));
    }

    return ans;
}

int main() {
	// your code goes here
	vector<int> nums = {1,2,1,2,1,2,1,2,1};
	cout<<maxSumOfThreeSubarrays(nums, 2);

	return 0;
}
