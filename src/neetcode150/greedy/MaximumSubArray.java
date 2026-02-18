package neetcode150.greedy;
/*
 * Given an array of integers nums, find the subarray with the largest sum and return the sum.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,-3,4,-2,2,1,-1,4]

Output: 8
Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.

Example 2:

Input: nums = [-1]

Output: -1
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

Time Complexity: O(n)

We iterate through the array exactly once
Each operation inside the loop takes constant time O(1)

Space Complexity: O(1)

We only use two variables (maxSum and currentSum) regardless of input size
No additional data structures are needed

**How it works:**
- At each position, we decide whether to add the current element to the existing subarray or start a new subarray from the current element
- We keep track of the maximum sum seen so far

**Example:**
```
Input: [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
 */
public class MaximumSubArray {
	
	public int maxSubArray(int[] nums) {
		int currentSum = nums[0];
		int maxSum = nums[0];
		
		for(int i = 1; i < nums.length; i++) {
			currentSum = Math.max(nums[i], nums[i] + currentSum);
			maxSum = Math.max(maxSum, currentSum);
		}
		return maxSum;
	}

}
