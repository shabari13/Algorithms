package neetcode250.binary_search;
/*
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the array 6 times produces the original array.

Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

Example 1:

Input: nums = [3,4,5,6,1,2]

Output: 1
Example 2:

Input: nums = [4,5,0,1,2,3]

Output: 0
Example 3:

Input: nums = [4,5,6,7]

Output: 4
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

TimeO(log n) — we halve the search space every iteration. Even for n = 1,000,000 that's only ~20 iterations.SpaceO(1) — only three integer variables (left, right, mid) regardless of input size. No recursion stack, no auxiliary array.
 */
public class FindMinimumInRotatedSortedArray {
	
	public static int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while(left < right) {
			int mid = left + (right - left) / 2;
			if(nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left];
	}
	 // ─── Main: run several test cases ─────────────────────────────
    public static void main(String[] args) {

        int[][] testCases = {
            {4, 5, 6, 7, 0, 1, 2},   // classic rotation
            {3, 4, 5, 1, 2},          // shorter rotation
            {11, 13, 15, 17},         // not rotated at all
            {2, 1},                   // two-element, rotated
            {1},                      // single element
            {5, 1, 2, 3, 4},          // rotation near the start
            {2, 3, 4, 5, 1}           // rotation near the end
        };

        String[] labels = {
            "[4,5,6,7,0,1,2]",
            "[3,4,5,1,2]",
            "[11,13,15,17]",
            "[2,1]",
            "[1]",
            "[5,1,2,3,4]",
            "[2,3,4,5,1]"
        };

        System.out.println("=== Find Minimum in Rotated Sorted Array ===\n");
        for (int i = 0; i < testCases.length; i++) {
            int result = findMin(testCases[i]);
            System.out.printf("Input: %-20s  →  Minimum = %d%n",
                              labels[i], result);
        }
    }
	

}
