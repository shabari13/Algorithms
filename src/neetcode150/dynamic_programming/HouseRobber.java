package neetcode150.dynamic_programming;
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

 */
public class HouseRobber {
		public int rob(int[] nums) {
			if(nums == null || nums.length == 0) {
				return 0;
			}
			if(nums.length == 1) {
				return nums[0];
			} 
			if(nums.length == 2)
				return Math.max(nums[0], nums[1]);
			int prev2 = 0;
			int prev1 = 0;
			for(int i = 0; i < nums.length ; i++) {
				int current = Math.max(prev1, nums[i] + prev2);
				prev2 = prev1;
				prev1 = current;
			}
			return prev1;
		}
		
		
		  public static void main(String[] args) {
		        HouseRobber solution = new HouseRobber();
		        
		        // Test case 1
		        int[] houses1 = {1, 2, 3, 1};
		        System.out.println("Houses: [1, 2, 3, 1]");
		        System.out.println("Maximum money: " + solution.rob(houses1));
		        System.out.println();
		        
		        // Test case 2
		        int[] houses2 = {2, 7, 9, 3, 1};
		        System.out.println("Houses: [2, 7, 9, 3, 1]");
		        System.out.println("Maximum money: " + solution.rob(houses2));
		        System.out.println();
		        
		        // Test case 3
		        int[] houses3 = {5, 3, 4, 11, 2};
		        System.out.println("Houses: [5, 3, 4, 11, 2]");
		        System.out.println("Maximum money: " + solution.rob(houses3));
		        System.out.println();
		        
		        // Test case 4
		        int[] houses4 = {2, 1, 1, 2};
		        System.out.println("Houses: [2, 1, 1, 2]");
		        System.out.println("Maximum money: " + solution.rob(houses4));
		    }

}
