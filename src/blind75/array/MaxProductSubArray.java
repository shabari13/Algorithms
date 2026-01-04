package blind75.array;

/*
 * Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 
Time Complexity: O(n)
Space Complexity: O(1)

Let's see with another example: [-2, 3, -4]

Start: max=-2, min=-2
After 3: max=3, min=-6 (that's -2×3)
After -4: max=24 (from -6×-4), min=-12

The answer is 24! We got this HUGE positive number because we multiplied two negatives: 
-6 × -4 = 24. If we only tracked the max, we would have lost that -6 and never found 24!

We need to trck 3 things , maxProduct, currentMax, currentMin

Store currentMax in a temp variable so that we don't lose it when we reassign. 
We need to pick max between number, and maximum of product between number, currentMax and number, currentMin
We need to pick min between number, and maximum of product between number, tempMax and number, currentMin
then product max is max between maxProduct and currentMax
 * 
 * 
 */
public class MaxProductSubArray {
	
	public int maxProduct(int[] nums) {
		
		  if(nums == null || nums.length == 0)
	        return 0;
		  
		  int currentMax = nums[0];
		  int currentMin = nums[0];
		  int maxProduct = nums[0];
		  
		  for(int i = 1; i < nums.length; i++) {
			  int tempMax = currentMax;
			  currentMax = Math.max(nums[i], Math.max(nums[i] * currentMax, nums[i] * currentMin));
			  currentMin = Math.min(nums[i], Math.min(nums[i] * tempMax, nums[i] * currentMin));
			  maxProduct = Math.max(maxProduct, currentMax);
			 
			  
		  }
		  
		  return maxProduct;
	}
	
	public static void main(String[] args) {
		MaxProductSubArray solution = new MaxProductSubArray();
        
		
	 int[] nums1 = {2, 3, -2, 4};
		  System.out.println("Input: [2, 3, -2, 4]"); System.out.println("Output: " +
		  solution.maxProduct(nums1)); System.out.println();
		  
		   int[] nums2 = {-2, 0, -1};
		  System.out.println("Input: [-2, 0, -1]"); System.out.println("Output: " +
		  solution.maxProduct(nums2)); System.out.println();
		 
        
        // Test Case 3
        int[] nums3 = {-2, 3, -4};
        System.out.println("Input: [-2, 3, -4]");
        System.out.println("Output: " + solution.maxProduct(nums3));
    }

}
