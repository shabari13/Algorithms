package neetcode250.dynamic_programming;
/*
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are arranged in a straight line, i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.

You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [1,1,3,3]

Output: 4
Explanation: nums[0] + nums[2] = 1 + 3 = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 16
Explanation: nums[0] + nums[2] + nums[4] = 2 + 8 + 6 = 16.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

⏱ Time Complexity:
O(n)

We loop through the array once.

💾 Space Complexity:
O(1)
 */
public class HouseRobber {
	public static int rob(int[] nums) {
		int n  = nums.length;
		if (n == 0) return 0;
	    if (n == 1) return nums[0];
	    
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i =2 ; i < n; i++) {
        	dp[i] = Math.max(dp[i-1], nums[i] + dp[i - 2]);
        }
        return dp[n-1];
    }
	public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {2, 1, 1, 2};
        int[] nums4 = {5, 3, 4, 11, 2};

        System.out.println("Output 1: " + rob(nums1));
        System.out.println("Output 2: " + rob(nums2));
        System.out.println("Output 3: " + rob(nums3));
        System.out.println("Output 4: " + rob(nums4));
    }
}
