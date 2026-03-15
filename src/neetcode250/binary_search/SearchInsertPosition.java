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

 */
public class SearchInsertPosition {
	public int searchInsertPosition(int[] nums, int target) {
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
}
