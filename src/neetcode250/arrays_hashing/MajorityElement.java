package neetcode250.arrays_hashing;
/*
 * Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times in the array. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [5,5,1,1,1,5,5]

Output: 5
Example 2:

Input: nums = [2,2,2]

Output: 2
Constraints:

1 <= nums.length <= 50,000
-1,000,000,000 <= nums[i] <= 1,000,000,000
Follow-up: Could you solve the problem in linear time and in O(1) space?
TimeO(n)Single pass through the array — each element is visited exactly onceSpaceO(1)
Only two variables used (candidate and count) — no extra array/map needed
 */
public class MajorityElement {
	
	public static int majorityElement(int[] nums) {
		int candidate = nums[0];
		int count = 1;
		for(int i = 1; i < nums.length; i++) {
			int current = nums[i];
			if(count == 0) {
				candidate = current;
				count = 1;
			} else if(current == candidate) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
	}
	  public static void main(String[] args) {

	        // ---- Sample Input 1 ----
	        System.out.println("========================================");
	        System.out.println("Test Case 1: [3, 2, 3]");
	        System.out.println("Expected: 3");
	        int[] nums1 = {3, 2, 3};
	        int result1 = majorityElement(nums1);
	        System.out.println("Output: " + result1);

	        // ---- Sample Input 2 ----
	        System.out.println("\n========================================");
	        System.out.println("Test Case 2: [2, 2, 1, 1, 1, 2, 2]");
	        System.out.println("Expected: 2");
	        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
	        int result2 = majorityElement(nums2);
	        System.out.println("Output: " + result2);

	        // ---- Sample Input 3 ----
	        System.out.println("\n========================================");
	        System.out.println("Test Case 3: [6, 6, 6, 1, 2]");
	        System.out.println("Expected: 6");
	        int[] nums3 = {6, 6, 6, 1, 2};
	        int result3 = majorityElement(nums3);
	        System.out.println("Output: " + result3);

	        // ---- Sample Input 4: Single Element ----
	        System.out.println("\n========================================");
	        System.out.println("Test Case 4: [99]");
	        System.out.println("Expected: 99");
	        int[] nums4 = {99};
	        int result4 = majorityElement(nums4);
	        System.out.println("Output: " + result4);

	        // ---- Sample Input 5: All Same ----
	        System.out.println("\n========================================");
	        System.out.println("Test Case 5: [5, 5, 5, 5, 5]");
	        System.out.println("Expected: 5");
	        int[] nums5 = {5, 5, 5, 5, 5};
	        int result5 = majorityElement(nums5);
	        System.out.println("Output: " + result5);
	    }
}
