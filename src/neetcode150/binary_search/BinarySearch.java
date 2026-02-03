package neetcode150.binary_search;
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
⏱️ Time Complexity: O(log n)
Imagine you have 1000 boxes:

Regular search: Check all 1000 boxes (1000 steps)
Binary search:

Step 1: 1000 → 500 boxes left
Step 2: 500 → 250 boxes left
Step 3: 250 → 125 boxes left
Keep dividing by 2... only about 10 steps!



Each time we cut the search space in HALF. So for n items, we need at most log₂(n) steps.
💾 Space Complexity: O(1)
We only use a few variables (left, right, mid). No matter how big the array is, we use the same tiny amount of extra memory. That's called "constant space"!
 */
public class BinarySearch {
	
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while(left <= right) {
			int mid = left + (right - left)/2;
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
        BinarySearch bs = new BinarySearch();
        
        // Test Case 1
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Test Case 1:");
        System.out.println("Array: [-1, 0, 3, 5, 9, 12]");
        System.out.println("Target: " + target1);
        System.out.println("Result: " + bs.search(nums1, target1));
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Test Case 2:");
        System.out.println("Array: [-1, 0, 3, 5, 9, 12]");
        System.out.println("Target: " + target2);
        System.out.println("Result: " + bs.search(nums2, target2));
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {5};
        int target3 = 5;
        System.out.println("Test Case 3:");
        System.out.println("Array: [5]");
        System.out.println("Target: " + target3);
        System.out.println("Result: " + bs.search(nums3, target3));
        System.out.println();
        
        // Test Case 4
        int[] nums4 = {2, 5};
        int target4 = 0;
        System.out.println("Test Case 4:");
        System.out.println("Array: [2, 5]");
        System.out.println("Target: " + target4);
        System.out.println("Result: " + bs.search(nums4, target4));
        System.out.println();
        
        // Test Case 5
        int[] nums5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target5 = 7;
        System.out.println("Test Case 5:");
        System.out.println("Array: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        System.out.println("Target: " + target5);
        System.out.println("Result: " + bs.search(nums5, target5));
    }

}
