package neetcode250.dynamic_programming;

import blind75.dp.HouseRobberii;

/*
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are arranged in a circle, i.e. the first house and the last house are neighbors.

You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [3,4,3]

Output: 4
Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses. The maximum you can rob is nums[1] = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 15
Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses. The maximum you can rob is nums[1] + nums[4] = 15.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 200

Time Complexity — O(n): We iterate through the array exactly once, doing O(1) work at each step (one addition and one max comparison). If there are n houses, we do n − 2 iterations.
Space Complexity — O(1): Instead of storing the full dp[] array of size n, we only ever need the last two values (prev1 and prev2). No matter how many houses there are, we use a constant amount of extra memory — just two integer variables.

 */
public class HouseRobberII {
	public static int rob(int[] nums) {
		int n = nums.length;
		if(n == 0) return 0;
		if (n == 1) return nums[0];
		if(n == 2) return Math.max(nums[0], nums[1]);
		int theft1 = robLinear(nums, 0, n-2);
		int theft2 = robLinear(nums, 1, n-1);
		return Math.max(theft1, theft2);
	}
	
	public static int robLinear(int[] nums, int startIndex, int endIndex) {
		int prev2 = 0;int prev1 = 0;
		for(int i = startIndex; i <= endIndex; i++) {
			int current = Math.max(prev1,  nums[i] + prev2);
			prev2 = prev1;
			prev1 = current;
		}
		return prev1;
	}
	
	public static void main(String[] args) {
		HouseRobberii solution = new HouseRobberii();
        
        // Test Case 1
        int[] nums1 = {2, 3, 2};
        System.out.println("Test Case 1:");
        System.out.println("Houses: [2, 3, 2]");
        System.out.println("Maximum money robbed: " + solution.rob(nums1));
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Test Case 2:");
        System.out.println("Houses: [1, 2, 3, 1]");
        System.out.println("Maximum money robbed: " + solution.rob(nums2));
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {1, 2, 3};
        System.out.println("Test Case 3:");
        System.out.println("Houses: [1, 2, 3]");
        System.out.println("Maximum money robbed: " + solution.rob(nums3));
        System.out.println();
        
        // Test Case 4
        int[] nums4 = {5};
        System.out.println("Test Case 4:");
        System.out.println("Houses: [5]");
        System.out.println("Maximum money robbed: " + solution.rob(nums4));
        System.out.println();
        
        // Test Case 5
        int[] nums5 = {2, 7, 9, 3, 1};
        System.out.println("Test Case 5:");
        System.out.println("Houses: [2, 7, 9, 3, 1]");
        System.out.println("Maximum money robbed: " + solution.rob(nums5));
        System.out.println();
        
        // Test Case 6
        int[] nums6 = {10, 1, 1, 10};
        System.out.println("Test Case 6:");
        System.out.println("Houses: [10, 1, 1, 10]");
        System.out.println("Maximum money robbed: " + solution.rob(nums6));
    }
}
