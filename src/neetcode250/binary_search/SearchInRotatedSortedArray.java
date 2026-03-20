package neetcode250.binary_search;
/*
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Given the rotated sorted array nums and an integer target, return the index of target within nums, or -1 if it is not present.

You may assume all elements in the sorted rotated array nums are unique,

A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

Example 1:

Input: nums = [3,4,5,6,1,2], target = 1

Output: 4
Example 2:

Input: nums = [3,5,6,0,1,2], target = 4

Output: -1
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-1000 <= target <= 1000
All values of nums are unique.
nums is an ascending array that is possibly rotated.

The Idea
In a normally sorted array, binary search is simple — compare the middle element to the target and eliminate half the array. 
A rotated sorted array has a "pivot" somewhere in the middle where the sort order restarts (e.g. [4,5,6,7,0,1,2]). 
The key insight is: even after rotation, at least one half of any binary search window is always perfectly sorted.
 We figure out which half is sorted, check if our target could live in that sorted half, and if so search there. 
 Otherwise, we jump to the other half. This gives us O(log n) time just like classic binary search.

Like You're 5 Years Old 🧒
Imagine your toys are arranged by size on a shelf, but your little sibling picked them up from one end and put them at the other end. 
The shelf looks weird now! But here's the trick: if you look at the left or right section of the shelf, one of those sections is still in perfect order. 
So you say: "Is the toy I want in the neat section? If yes, look there. If no, look in the messy section." You keep doing this and find your toy super fast without
 checking every single toy!

In a normally sorted array, binary search is simple — compare the middle element to the target and eliminate half the array.
 A rotated sorted array has a "pivot" somewhere in the middle where the sort order restarts (e.g. [4,5,6,7,0,1,2]). The key insight is: even after rotation, at least one half of any binary search window is always perfectly sorted. We figure out which half is sorted, check if our target could live in that sorted half, and if so search there. Otherwise, we jump to the other half. This gives us O(log n) time just like classic binary search.
ComplexityReasoning
TimeO(log n)Every iteration eliminates exactly half the remaining search space, just like classic 
binary searchSpaceO(1)Only three integer pointers (lo, hi, mid) are used; no extra arrays or recursion stack
 */
public class SearchInRotatedSortedArray {
		public static int search(int[] nums, int target) {
			int left = 0;
			int right = nums.length - 1;
			while(left <= right) {
				int mid = left + (right - left) / 2;
				if(nums[mid] == target) {
					return mid;
				} else if(nums[mid] < nums[right]) {
					if(target > nums[mid] && target <= nums[right]) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				} else {
					if(target >= nums[left] && target < nums[mid]) {
						right  = mid - 1;
 					} else {
 						left = mid + 1;
 					}
				}
			}
			return -1;		
		}
		
		  public static void main(String[] args) {

		        // ── Test Case 1: Classic example from LeetCode ──
		        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
		        int target1 = 0;
		        System.out.println("Array : [4, 5, 6, 7, 0, 1, 2]");
		        System.out.println("Target: 0");
		        System.out.println("Result: " + search(arr1, target1));   // Expected: 4
		        System.out.println();

		        // ── Test Case 2: Target at the very beginning ──
		        int[] arr2 = {4, 5, 6, 7, 0, 1, 2};
		        int target2 = 4;
		        System.out.println("Array : [4, 5, 6, 7, 0, 1, 2]");
		        System.out.println("Target: 4");
		        System.out.println("Result: " + search(arr2, target2));   // Expected: 0
		        System.out.println();

		        // ── Test Case 3: Target not in array ──
		        int[] arr3 = {4, 5, 6, 7, 0, 1, 2};
		        int target3 = 3;
		        System.out.println("Array : [4, 5, 6, 7, 0, 1, 2]");
		        System.out.println("Target: 3");
		        System.out.println("Result: " + search(arr3, target3));   // Expected: -1
		        System.out.println();

		        // ── Test Case 4: Single element — found ──
		        int[] arr4 = {1};
		        int target4 = 1;
		        System.out.println("Array : [1]");
		        System.out.println("Target: 1");
		        System.out.println("Result: " + search(arr4, target4));   // Expected: 0
		        System.out.println();

		        // ── Test Case 5: Single element — not found ──
		        int[] arr5 = {1};
		        int target5 = 0;
		        System.out.println("Array : [1]");
		        System.out.println("Target: 0");
		        System.out.println("Result: " + search(arr5, target5));   // Expected: -1
		        System.out.println();

		        // ── Test Case 6: No rotation (normal sorted array) ──
		        int[] arr6 = {1, 2, 3, 4, 5, 6};
		        int target6 = 4;
		        System.out.println("Array : [1, 2, 3, 4, 5, 6]");
		        System.out.println("Target: 4");
		        System.out.println("Result: " + search(arr6, target6));   // Expected: 3
		        System.out.println();

		        // ── Test Case 7: Two elements, target is second ──
		        int[] arr7 = {3, 1};
		        int target7 = 1;
		        System.out.println("Array : [3, 1]");
		        System.out.println("Target: 1");
		        System.out.println("Result: " + search(arr7, target7));   // Expected: 1
		    }
}
