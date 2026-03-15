package neetcode250.binary_search;

import java.util.Arrays;

/*
 * You are given an array of distinct integers nums, sorted in ascending order, and an integer target.

Implement a function to search for target within nums. If it exists, then return its index, otherwise, return -1.

Your solution must run in 
O
(
l
o
g
n
)
O(logn) time.

Example 1:

Input: nums = [-1,0,2,4,6,8], target = 4

Output: 3
Example 2:

Input: nums = [-1,0,2,4,6,8], target = 3

Output: -1
Constraints:

1 <= nums.length <= 10000.
-10000 < nums[i], target < 10000
All the integers in nums are unique.

Best CaseO(1)Target is at the very first midAverage CaseO(log n)Halving the space each timeWorst CaseO(log n)Target not found or found lastSpaceO(1)Only 3 integer variables used, no extra array
 */
public class Binary_Search {
	public static int binarySearch(int[] nums, int target) {
		int left = 0 ;
		int right = nums.length - 1;
		while(left <= right) {
			int mid = left + (right - left) /2;
			if(nums[mid] == target) {
				return mid;
			} else if(nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
	
	 public static void main(String[] args) {

	        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
	        System.out.println("Array: " + Arrays.toString(arr));
	        System.out.println("─────────────────────────────────────────────");

	        // Test 1: target exists in the array
	        int target1 = 23;
	        int result1 = binarySearch(arr, target1);
	        System.out.println("Search for " + target1 + " → index " + result1);  // 5

	        // Test 2: target is the first element
	        int target2 = 2;
	        int result2 = binarySearch(arr, target2);
	        System.out.println("Search for " + target2 + "  → index " + result2); // 0

	        // Test 3: target is the last element
	        int target3 = 91;
	        int result3 = binarySearch(arr, target3);
	        System.out.println("Search for " + target3 + " → index " + result3); // 10

	        // Test 4: target is NOT in the array
	        int target4 = 50;
	        int result4 = binarySearch(arr, target4);
	        System.out.println("Search for " + target4 + " → index " + result4); // -1

	        // Test 5: single-element array — found
	        int[] single = {7};
	        System.out.println("Search for 7 in [7] → index " + binarySearch(single, 7)); // 0

	        // Test 6: single-element array — not found
	        System.out.println("Search for 3 in [7] → index " + binarySearch(single, 3)); // -1
	    }

}
