package neetcode250.two_pointers;
/*
 * You are given an integer array nums sorted in non-decreasing order. Your task is to remove duplicates from nums in-place so that each element appears only once.

After removing the duplicates, return the number of unique elements, denoted as k, such that the first k elements of nums contain the unique elements.

Note:

The order of the unique elements should remain the same as in the original array.
It is not necessary to consider elements beyond the first k positions of the array.
To be accepted, the first k elements of nums must contain all the unique elements.
Return k as the final result.

Example 1:

Input: nums = [1,1,2,3,4]

Output: [1,2,3,4]
Explanation: You should return k = 4 as we have four unique elements.

Example 2:

Input: nums = [2,10,10,30,30,30]

Output: [2,10,30]
Explanation: You should return k = 3 as we have three unique elements.

Constraints:

1 <= nums.length <= 30,000
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
Time: O(n) — single pass through the array
Space: O(1) — modified in-place, no extra memory used

 */
public class RemoveDuplicatesFromSortedArray {
	  public static int removeDuplicates(int[] nums) {
	        int k = 1;
	        for(int i = 1; i < nums.length; i++) {
	        	if(nums[i] != nums[i-1]) {
	        		 nums[k] = nums[i];
	        		k++;
	        	}
	        	
	        }
	        return k;
	    }
	  
	  public static void main(String[] args) {
	        // Test Case 1
	        int[] nums1 = {1, 1, 2, 3, 4};
	        int k1 = removeDuplicates(nums1);
	        System.out.print("Output 1: [");
	        for (int i = 0; i < k1; i++) {
	            System.out.print(i < k1 - 1 ? nums1[i] + ", " : nums1[i]);
	        }
	        System.out.println("] k = " + k1);

	        // Test Case 2
	        int[] nums2 = {2, 10, 10, 30, 30, 30};
	        int k2 = removeDuplicates(nums2);
	        System.out.print("Output 2: [");
	        for (int i = 0; i < k2; i++) {
	            System.out.print(i < k2 - 1 ? nums2[i] + ", " : nums2[i]);
	        }
	        System.out.println("] k = " + k2);
	    }
}
