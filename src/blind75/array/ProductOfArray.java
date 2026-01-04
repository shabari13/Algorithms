package blind75.array;
/*
 * 
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.

Time Complexity: O(n) - two passes through the array
Space Complexity: O(1) - only uses the output array (not counted as extra space per problem constraints)

Build an array which contains values of products of numbers to its left
then build the answer array by having products of leftproduct and right product. 

Rihg tproduct will keep changing with values as rightproduct * nums[i] then you multiply this with already built left product
 */
public class ProductOfArray {
	
	public int[] productOfArray(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];
		ans[0] = 1;
		for(int i = 1; i < n ; i++) {
			ans[i] = nums[i-1] * ans[i-1];
		}
		int rightProduct = 1;
		for(int i = n-1; i>=0; i--) {
			ans[i] = ans[i] * rightProduct;
			rightProduct = rightProduct * nums[i];
		
		}
		return ans;
	}
	
	public static void main(String[] args) {
		ProductOfArray pa = new ProductOfArray();
		int[] nums = {3,4,5,6};
		int[] ans  = pa.productOfArray(nums);
		for(int num : ans) {
			System.out.println(num);
		}
	}

}
