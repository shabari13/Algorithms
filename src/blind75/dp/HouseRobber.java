package blind75.dp;
/*
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400


⏱️ Time Complexity: O(n)

We visit each house exactly once in a single loop
n = number of houses
Each operation inside the loop takes constant time O(1)

💾 Space Complexity:

Standard solution: O(n) - We use a dp array of size n
Optimized solution: O(1) - We only keep track of the last two values (prev1 and prev2)
📝 Step-by-Step Explanation
The Big Idea:
At each house, you make a choice:

Rob this house: Get money here + the best you could do 2 houses ago
Skip this house: Keep the best you could do at the previous house

 */
public class HouseRobber {
	
	public int rob(int[] nums) {
		
		if(nums.length == 0)
			return 0;
		if(nums.length == 1) {
			return nums[0];
		}
		if(nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		

		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for(int i = 2; i < nums.length; i++) {
			 dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
		}
		 return dp[nums.length - 1];
	}
	
	 // Space-optimized solution (O(1) space)
    public int robOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = nums[0];  // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]);  // dp[i-1]
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
	
	public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();
        
        // Test Case 1
        int[] houses1 = {1, 2, 3, 1};
        System.out.println("Test Case 1:");
        System.out.println("Houses: [1, 2, 3, 1]");
        System.out.println("Maximum money robbed: " + solution.rob(houses1));
        //System.out.println("Maximum money (optimized): " + solution.robOptimized(houses1));
        System.out.println();
        
        // Test Case 2
        int[] houses2 = {2, 7, 9, 3, 1};
        System.out.println("Test Case 2:");
        System.out.println("Houses: [2, 7, 9, 3, 1]");
        System.out.println("Maximum money robbed: " + solution.rob(houses2));
       // System.out.println("Maximum money (optimized): " + solution.robOptimized(houses2));
        System.out.println();
        
        // Test Case 3
        int[] houses3 = {5, 3, 4, 11, 2};
        System.out.println("Test Case 3:");
        System.out.println("Houses: [5, 3, 4, 11, 2]");
        System.out.println("Maximum money robbed: " + solution.rob(houses3));
       // System.out.println("Maximum money (optimized): " + solution.robOptimized(houses3));
        System.out.println();
        
        // Test Case 4 - Single house
        int[] houses4 = {10};
        System.out.println("Test Case 4:");
        System.out.println("Houses: [10]");
        System.out.println("Maximum money robbed: " + solution.rob(houses4));
        System.out.println();
        
        // Test Case 5 - Two houses
        int[] houses5 = {5, 1};
        System.out.println("Test Case 5:");
        System.out.println("Houses: [5, 1]");
        System.out.println("Maximum money robbed: " + solution.rob(houses5));
        System.out.println();
        
        // Test Case 6 - All same values
        int[] houses6 = {2, 1, 1, 2};
        System.out.println("Test Case 6:");
        System.out.println("Houses: [2, 2, 2, 2]");
        System.out.println("Maximum money robbed: " + solution.rob(houses6));
        System.out.println();
    }

}
