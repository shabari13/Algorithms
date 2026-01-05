package blind75.array;
/*
 * 
 * There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1

Time Complexity: O(log n)
Space Complexity: O(1)

// Determine which half is sorted 
///
///if(nums[mid] <= nums[right]) { it means left half is rotated
if (nums[left] <= nums[mid]) { left half is sorted. Then after that you check target is in which range
 */
public class SearchRotatedArray {
	
	public int search(int[] nums, int target) {
		int n = nums.length;
		int left = 0; int right = n-1;
		
		while(left <= right) {
			
			int mid = left + (right - left) / 2;
			if(nums[mid] == target) {
				return mid;
			}
			
			if(nums[mid] <= nums[right]) {
				if(target > nums[mid] && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if(target >= nums[left] && target < nums[mid]) {
					
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) {
        SearchRotatedArray solution = new SearchRotatedArray();
        
        // Test case 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Target: " + target1);
        System.out.println("Output: " + solution.search(nums1, target1));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Target: " + target2);
        System.out.println("Output: " + solution.search(nums2, target2));
        System.out.println();
        
        // Test case 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Array: [1]");
        System.out.println("Target: " + target3);
        System.out.println("Output: " + solution.search(nums3, target3));
        System.out.println();
        
        // Test case 4
        int[] nums4 = {5, 1, 3};
        int target4 = 3;
        System.out.println("Array: [5, 1, 3]");
        System.out.println("Target: " + target4);
        System.out.println("Output: " + solution.search(nums4, target4));
    }

}
