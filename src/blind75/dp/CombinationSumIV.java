package blind75.dp;
/*
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

 

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0



Imagine you have a piggy bank and you want to put exactly 4 dollars in it. You have coins of $1, $2, and $3. How many different ways can you put the money in?
The Magic Box (Array) Approach
We create a special box (called dp array) with slots numbered 0 to 4. Each slot tells us "how many ways can we make this amount?"
Step by step walkthrough with nums = [1, 2, 3] and target = 4:

Time Complexity: O(target × n)

We loop from 1 to target: target iterations
For each iteration, we check all numbers in nums array: n iterations
Total: target × n operations

Space Complexity: O(target)

We only use one array of size (target + 1)
No recursion stack, no extra arrays


 * 
 */
public class CombinationSumIV {
	
	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for(int i = 1; i <= target; i++) {
			for(int num : nums) {
				if(i >= num) {
					dp[i] = dp[i] + dp[i - num];
				}
				
			}
		}
		return dp[target];
	}
	
	 public static void main(String[] args) {
	        CombinationSumIV solution = new CombinationSumIV();
	        
	        // Test Case 1
	        int[] nums1 = {1, 2, 3};
	        int target1 = 4;
	        int result1 = solution.combinationSum4(nums1, target1);
	        System.out.println("Test Case 1:");
	        System.out.println("Input: nums = [1, 2, 3], target = 4");
	        System.out.println("Output: " + result1);
	        System.out.println("Explanation: Possible combinations are:");
	        System.out.println("(1,1,1,1), (1,1,2), (1,2,1), (1,3), (2,1,1), (2,2), (3,1)");
	        System.out.println();
	        
	        // Test Case 2
	        int[] nums2 = {9};
	        int target2 = 3;
	        int result2 = solution.combinationSum4(nums2, target2);
	        System.out.println("Test Case 2:");
	        System.out.println("Input: nums = [9], target = 3");
	        System.out.println("Output: " + result2);
	        System.out.println("Explanation: Cannot make 3 using only 9");
	        System.out.println();
	        
	        // Test Case 3
	        int[] nums3 = {1, 2};
	        int target3 = 3;
	        int result3 = solution.combinationSum4(nums3, target3);
	        System.out.println("Test Case 3:");
	        System.out.println("Input: nums = [1, 2], target = 3");
	        System.out.println("Output: " + result3);
	        System.out.println("Explanation: Possible combinations are:");
	        System.out.println("(1,1,1), (1,2), (2,1)");
	        System.out.println();
	        
	        // Test Case 4
	        int[] nums4 = {2, 3, 5};
	        int target4 = 8;
	        int result4 = solution.combinationSum4(nums4, target4);
	        System.out.println("Test Case 4:");
	        System.out.println("Input: nums = [2, 3, 5], target = 8");
	        System.out.println("Output: " + result4);
	        System.out.println();
	        
	        // Test Case 5 - Edge case with target = 1
	        int[] nums5 = {1, 2, 3};
	        int target5 = 1;
	        int result5 = solution.combinationSum4(nums5, target5);
	        System.out.println("Test Case 5:");
	        System.out.println("Input: nums = [1, 2, 3], target = 1");
	        System.out.println("Output: " + result5);
	        System.out.println("Explanation: Only one way: (1)");
	        System.out.println();
	    }

}
