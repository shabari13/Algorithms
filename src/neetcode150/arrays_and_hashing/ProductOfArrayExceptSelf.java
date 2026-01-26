package neetcode150.arrays_and_hashing;
/*
 * Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].

Each product is guaranteed to fit in a 32-bit integer.

Follow-up: Could you solve it in 
O
(
n
)
O(n) time without using the division operation?

Example 1:

Input: nums = [1,2,4,6]

Output: [48,24,12,8]
Example 2:

Input: nums = [-1,0,1,2,3]

Output: [0,-6,0,0,0]
Constraints:

2 <= nums.length <= 1000
-20 <= nums[i] <= 20


 * Time Complexity: O(n)

First loop: O(n) - we visit each element once
Second loop: O(n) - we visit each element once again
Total: O(n) + O(n) = O(n)

Space Complexity: O(1) (excluding output array)

We only use one extra variable rightProduct
The result array doesn't count as extra space because it's the required output
So constant space O(1)!
 */
public class ProductOfArrayExceptSelf {
	
	public static int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		result[0] = 1;
		
		for(int i = 1; i < nums.length; i++) {
			
			result[i] = result[i-1] * nums[i-1];
		}
		
		int rightProduct = 1;
		
		for(int i = nums.length - 1 ; i >= 0; i--) {
			
			result[i]  = result[i] * rightProduct;
			rightProduct = nums[i] * rightProduct;
		}
		
		return result;
		
	}
	
	 public static void main(String[] args) {
	        // Test Case 1
	        int[] nums1 = {1, 2, 3, 4};
	        int[] result1 = productExceptSelf(nums1);
	        System.out.println("Input: [1, 2, 3, 4]");
	        System.out.print("Output: [");
	        for (int i = 0; i < result1.length; i++) {
	            System.out.print(result1[i]);
	            if (i < result1.length - 1) System.out.print(", ");
	        }
	        System.out.println("]\n");
	        
	        // Test Case 2
	        int[] nums2 = {-1, 1, 0, -3, 3};
	        int[] result2 = productExceptSelf(nums2);
	        System.out.println("Input: [-1, 1, 0, -3, 3]");
	        System.out.print("Output: [");
	        for (int i = 0; i < result2.length; i++) {
	            System.out.print(result2[i]);
	            if (i < result2.length - 1) System.out.print(", ");
	        }
	        System.out.println("]\n");
	        
	        // Test Case 3
	        int[] nums3 = {5, 2};
	        int[] result3 = productExceptSelf(nums3);
	        System.out.println("Input: [5, 2]");
	        System.out.print("Output: [");
	        for (int i = 0; i < result3.length; i++) {
	            System.out.print(result3[i]);
	            if (i < result3.length - 1) System.out.print(", ");
	        }
	        System.out.println("]\n");
	    }

}
