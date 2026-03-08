package neetcode250.arrays_hashing;
/*
 * You are given an integer array nums and an integer val. Your task is to remove all occurrences of val from nums in-place.

After removing all occurrences of val, return the number of remaining elements, say k, such that the first k elements of nums do not contain val.

Note:

The order of the elements which are not equal to val does not matter.
It is not necessary to consider elements beyond the first k positions of the array.
To be accepted, the first k elements of nums must contain only elements not equal to val.
Return k as the final result.

Example 1:

Input: nums = [1,1,2,3,4], val = 1

Output: [2,3,4]
Explanation: You should return k = 3 as we have 3 elements which are not equal to val = 1.

Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2

Output: [0,1,3,0,4]
Explanation: You should return k = 5 as we have 5 elements which are not equal to val = 2.

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100

 TimeO(n)We visit each element exactly once with pointer i💾 SpaceO(1)Everything is done in-place — no extra array allocated
 */
public class RemoveElement {
	
	public static  int removeElement(int[] nums, int val) {
		 
		int k = 0;
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] != val) {
				k++;
			}
		}
		return k;
	}
	
	 static void printResult(int[] nums, int k, int val) {
	        System.out.println("\n===========================");
	        System.out.println("Input val to remove : " + val);
	        System.out.print("Valid elements      : [");
	        for (int i = 0; i < k; i++) {
	            System.out.print(nums[i]);
	            if (i < k - 1) System.out.print(", ");
	        }
	        System.out.println("]");
	        System.out.println("Count (k)           : " + k);
	        System.out.println("===========================");
	    }

	    // -------------------------------------------------------
	    // MAIN — multiple test cases
	    // -------------------------------------------------------
	    public static void main(String[] args) {

	        // ── Test 1 ──────────────────────────────────────────
	        int[] nums1 = {3, 2, 2, 3};
	        int val1 = 3;
	        System.out.println("Test 1 | nums = [3, 2, 2, 3] | val = 3");
	        int k1 = removeElement(nums1, val1);
	        printResult(nums1, k1, val1);

	        // ── Test 2 ──────────────────────────────────────────
	        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
	        int val2 = 2;
	        System.out.println("\nTest 2 | nums = [0,1,2,2,3,0,4,2] | val = 2");
	        int k2 = removeElement(nums2, val2);
	        printResult(nums2, k2, val2);

	        // ── Test 3: val not present ──────────────────────────
	        int[] nums3 = {1, 2, 3, 4};
	        int val3 = 9;
	        System.out.println("\nTest 3 | nums = [1, 2, 3, 4] | val = 9 (not present)");
	        int k3 = removeElement(nums3, val3);
	        printResult(nums3, k3, val3);

	        // ── Test 4: all elements are val ─────────────────────
	        int[] nums4 = {7, 7, 7};
	        int val4 = 7;
	        System.out.println("\nTest 4 | nums = [7, 7, 7] | val = 7 (all removed)");
	        int k4 = removeElement(nums4, val4);
	        printResult(nums4, k4, val4);

	        // ── Test 5: single element ───────────────────────────
	        int[] nums5 = {5};
	        int val5 = 5;
	        System.out.println("\nTest 5 | nums = [5] | val = 5 (single element removed)");
	        int k5 = removeElement(nums5, val5);
	        printResult(nums5, k5, val5);
	    }

}
