package neetcode150.dynamic_programming;
/*
 * Maximum Product Subarray
Medium
Topics
Company Tags
Hints
Given an integer array nums, find a subarray that has the largest product within the array and return it.

A subarray is a contiguous non-empty sequence of elements within an array.

You can assume the output will fit into a 32-bit integer.

Example 1:

Input: nums = [1,2,-3,4]

Output: 4
Example 2:

Input: nums = [-2,-1]

Output: 2
Constraints:

1 <= nums.length <= 1000
-10 <= nums[i] <= 10

Time Complexity

We go through array once.

👉 O(n)

🧠 Space Complexity

We use only:

currentMax

currentMin

result

No extra array.

👉 O(1)

It is important that we keep track of both currentMax and currentMix because a negative number multipled by another negative could get bigger.
We also store currentMax in temp variable so that it could be used by currentMin later in the equation.
 */
public class MaximumProductSubarray {
	public static int maxProduct(int[] nums) {
		int currentMax = nums[0];
		int currentMin = nums[0];
		int result = nums[0];
		
		for(int i = 1; i < nums.length; i++) {
			int tempMax = currentMax;
			currentMax = Math.max(nums[i], Math.max(nums[i] * currentMax, nums[i] * currentMin));
			currentMin = Math.min(nums[i], Math.min(nums[i] * tempMax, nums[i] * currentMin));
			result = Math.max(result, currentMax);
		}
		return result;
		
	}
	
	  public static void main(String[] args) {

	        int[][] testCases = {
	                {2, 3, -2, 4},
	                {-2, 0, -1},
	                {-2, 3, -4},
	                {0, 2},
	                {-1, -3, -10, 0, 60},
	                {-2}
	        };

	        for (int i = 0; i < testCases.length; i++) {
	            System.out.print("Input: ");
	            printArray(testCases[i]);

	            int result = maxProduct(testCases[i]);
	            System.out.println("Maximum Product: " + result);
	            System.out.println("------------------------");
	        }
	    }

	    private static void printArray(int[] nums) {
	        System.out.print("[");
	        for (int i = 0; i < nums.length; i++) {
	            System.out.print(nums[i]);
	            if (i < nums.length - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println("]");
	    }
}
