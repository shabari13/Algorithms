package neetcode250.binary_search;
/*
 * You are given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [-1,0,2,4,6,8], target = 5

Output: 4
Example 2:

Input: nums = [-1,0,2,4,6,8], target = 10

Output: 6
Constraints:

1 <= nums.length <= 10,000.
-10,000 < nums[i], target < 10,000
nums contains distinct values sorted in ascending order.

Basically left always gives you the insert position

Time Complexity — O(log n)
Each iteration cuts the search space exactly in half. For an array of size n, we need at most log₂(n) steps. For 1 million elements, that's only ~20 iterations!
Space Complexity — O(1)
We only use three integer variables (left, right, mid) regardless of the array size. No extra arrays, no recursion stack. Completely constant space.
 */
public class SearchInsertPosition {
	public static int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(nums[mid] == target) {
				return mid;
			} else if(nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
	  public static void main(String[] args) {

	        // Test Case 1: Target EXISTS in array → return its index
	        // Array:  [1, 3, 5, 6]
	        // Target: 5  →  index 2
	        int[] nums1 = {1, 3, 5, 6};
	        System.out.println("=== Test Case 1 ===");
	        System.out.println("Array:  [1, 3, 5, 6]  |  Target: 5");
	        System.out.println("Output: " + searchInsert(nums1, 5));
	        // Expected: 2

	        // Test Case 2: Target NOT in array → insert BETWEEN elements
	        // Array:  [1, 3, 5, 6]
	        // Target: 2  →  goes between index 0 and 1 → insert at index 1
	        int[] nums2 = {1, 3, 5, 6};
	        System.out.println("\n=== Test Case 2 ===");
	        System.out.println("Array:  [1, 3, 5, 6]  |  Target: 2");
	        System.out.println("Output: " + searchInsert(nums2, 2));
	        // Expected: 1

	        // Test Case 3: Target SMALLER than all elements → insert at beginning
	        // Array:  [1, 3, 5, 6]
	        // Target: 0  →  goes before index 0 → insert at index 0
	        int[] nums3 = {1, 3, 5, 6};
	        System.out.println("\n=== Test Case 3 ===");
	        System.out.println("Array:  [1, 3, 5, 6]  |  Target: 0");
	        System.out.println("Output: " + searchInsert(nums3, 0));
	        // Expected: 0

	        // Test Case 4: Target LARGER than all elements → insert at end
	        // Array:  [1, 3, 5, 6]
	        // Target: 7  →  goes after index 3 → insert at index 4
	        int[] nums4 = {1, 3, 5, 6};
	        System.out.println("\n=== Test Case 4 ===");
	        System.out.println("Array:  [1, 3, 5, 6]  |  Target: 7");
	        System.out.println("Output: " + searchInsert(nums4, 7));
	        // Expected: 4

	        // Test Case 5: Single element array, target equals that element
	        int[] nums5 = {5};
	        System.out.println("\n=== Test Case 5 ===");
	        System.out.println("Array:  [5]  |  Target: 5");
	        System.out.println("Output: " + searchInsert(nums5, 5));
	        // Expected: 0

	        // Test Case 6: Single element array, target smaller
	        int[] nums6 = {5};
	        System.out.println("\n=== Test Case 6 ===");
	        System.out.println("Array:  [5]  |  Target: 3");
	        System.out.println("Output: " + searchInsert(nums6, 3));
	        // Expected: 0

	        // Test Case 7: Single element array, target larger
	        int[] nums7 = {5};
	        System.out.println("\n=== Test Case 7 ===");
	        System.out.println("Array:  [5]  |  Target: 8");
	        System.out.println("Output: " + searchInsert(nums7, 8));
	        // Expected: 1
	    }
	
}
