package neetcode250.arrays_hashing;
/*
 * 
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
 
 */
public class ProductOfArrayExceptSelf {
	
	public static int[] product(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];
		ans[0] = 1;
		for(int i = 1; i < n; i++) {
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
		
		int[] nums = {1,2,4,6};
		int[] ans = product(nums);
		for(int num : ans) {
			System.out.print(num + " ");
		}
		int[] nums2 = {-1,0,1,2,3};
		int[] ans2 = product(nums2);
		for(int num : ans2) {
			System.out.print(num + " ");
		}
	}

}
