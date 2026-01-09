package blind75.dp;

/*
 * 
 *  Time Complexity: O(n)

We iterate through the array twice (once for each scenario)
Each iteration goes through at most n elements
O(n) + O(n) = O(n)

💾 Space Complexity: O(1)

We only use a constant amount of extra space
Just three variables: prev2, prev1, and current
No arrays or data structures that grow with input size
ou are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000

⏱️ Time Complexity: O(n)

We iterate through the array twice (once for each scenario)
Each iteration goes through at most n elements
O(n) + O(n) = O(n)

💾 Space Complexity: O(1)

We only use a constant amount of extra space
Just three variables: prev2, prev1, and current
No arrays or data structures that grow with input size

Since the first and last houses are neighbors (because of the circle), you can NEVER rob both of them. So we have two choices:

Skip the last house → Rob from houses 0 to n-2
Skip the first house → Rob from houses 1 to n-1
 */

public class HouseRobberii {
	
	 public int rob(int[] nums) {
	        // Special cases
	        if (nums == null || nums.length == 0) return 0;
	        if (nums.length == 1) return nums[0];
	        if (nums.length == 2) return Math.max(nums[0], nums[1]);
	        
	        // Since houses are in a circle, first and last are adjacent
	        // We can't rob both first and last house
	        // So we consider two scenarios:
	        // 1. Rob houses from 0 to n-2 (skip last house)
	        // 2. Rob houses from 1 to n-1 (skip first house)
	        
	        int robWithoutLast = robLinear(nums, 0, nums.length - 2);
	        int robWithoutFirst = robLinear(nums, 1, nums.length - 1);
	        
	        return Math.max(robWithoutLast, robWithoutFirst);
	    }
	    
	    // Helper method to solve linear house robber problem
	    private int robLinear(int[] nums, int start, int end) {
	        int prev2 = 0;  // Money robbed up to i-2
	        int prev1 = 0;  // Money robbed up to i-1
	        
	        for (int i = start; i <= end; i++) {
	            int current = Math.max(prev1, prev2 + nums[i]);
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
