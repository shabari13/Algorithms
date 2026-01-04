package blind75.array;

/*
 * 
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 Time Complexity: O(n)

Space Complexity: O(1)
 
 The Magic Rule:

As you walk past each box, you ask: "Should I keep collecting from where I am, or should I start fresh from this box?"
You always remember the biggest total you've seen so far!

Always start with 2 variable maxSum and currentSum pointing 
to first number of the array. Then as you go check if you want to start with that numbe\
r or add that number to the prev current sum
 
 */

public class MaxSubArray {
	
	public int maxSubArray(int[] nums) {
		
		int maxSum = nums[0];
		int currentSum = nums[0];
		for(int i=1; i < nums.length; i++) {
			currentSum = Math.max(nums[i], currentSum + nums[i]);
			maxSum = Math.max(maxSum,  currentSum);
			
		}
		
		return maxSum;
		
	}
	
	public static void main(String[] args) {
		MaxSubArray solution = new MaxSubArray();
        
        // Test Case 1
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result1 = solution.maxSubArray(nums1);
        System.out.println("Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Output: " + result1);
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {1};
        int result2 = solution.maxSubArray(nums2);
        System.out.println("Input: [1]");
        System.out.println("Output: " + result2);
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {5, 4, -1, 7, 8};
        int result3 = solution.maxSubArray(nums3);
        System.out.println("Input: [5, 4, -1, 7, 8]");
        System.out.println("Output: " + result3);
	}

}
