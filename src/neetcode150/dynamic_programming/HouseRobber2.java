package neetcode150.dynamic_programming;
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
0 <= nums[i] <= 100
Time and Space Complexity
Time Complexity: O(n)

We iterate through the array twice (once for each scenario), and each iteration goes through at most n-1 houses
O(n-1) + O(n-1) = O(n)

Space Complexity: O(1)

We only use a constant amount of extra space (prev1, prev2, current variables)
No arrays or data structures that grow with input size
 */
public class HouseRobber2 {

	public int rob(int[] nums) {
		if(nums.length == 1)
			return nums[0];
		int prev2 = 0;
		int prev1 = 0;
		int rob1 = robLinear(nums, 0, nums.length - 2);
		int rob2 = robLinear(nums, 1, nums.length - 1);
		return Math.max(rob1, rob2);
		
	}
	public int robLinear(int[] nums, int start, int end) {
		int prev2 = 0;
		int prev1 = 0;
		for(int i = start; i<= end; i++) {
			int current = Math.max(prev1, prev2 + nums[i]);
			prev2 = prev1;
			prev1 = current;
		}
		return prev1;
	}
	
	 public static void main(String[] args) {
		 HouseRobber2 solution = new HouseRobber2();
	        
	        // Test Case 1
	        int[] nums1 = {2, 3, 2};
	        System.out.println("Input: [2, 3, 2]");
	        System.out.println("Output: " + solution.rob(nums1));
	        System.out.println("Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses (circular).");
	        System.out.println();
	        
	        // Test Case 2
	        int[] nums2 = {1, 2, 3, 1};
	        System.out.println("Input: [1, 2, 3, 1]");
	        System.out.println("Output: " + solution.rob(nums2));
	        System.out.println("Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total = 1 + 3 = 4.");
	        System.out.println();
	        
	        // Test Case 3
	        int[] nums3 = {1, 2, 3};
	        System.out.println("Input: [1, 2, 3]");
	        System.out.println("Output: " + solution.rob(nums3));
	        System.out.println("Explanation: Rob house 3 (money = 3).");
	        System.out.println();
	        
	        // Test Case 4
	        int[] nums4 = {5};
	        System.out.println("Input: [5]");
	        System.out.println("Output: " + solution.rob(nums4));
	        System.out.println("Explanation: Only one house, rob it.");
	        System.out.println();
	        
	        // Test Case 5
	        int[] nums5 = {2, 7, 9, 3, 1};
	        System.out.println("Input: [2, 7, 9, 3, 1]");
	        System.out.println("Output: " + solution.rob(nums5));
	        System.out.println("Explanation: Rob house 2 (money = 7) and house 4 (money = 3). Total = 7 + 3 = 10.");
	        System.out.println();
	    }
}
