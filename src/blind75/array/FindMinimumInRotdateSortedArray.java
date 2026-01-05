package blind75.array;


/*
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

 

Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:

Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
Example 3:

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 

Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.

Time: O(log n)
Space: O(1)

// If mid element is greater than the rightmost element,
// minimum must be in the right half // Otherwise, minimum is in the left half (including mid)
 

 */
public class FindMinimumInRotdateSortedArray {
	
	public int minimumInRotatedSortedArray(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		if(nums[left] < nums[right]) {
			
			return nums[left];
		}
		while(left < right) {
			
			int mid = left + (right-left)/2;
			if(nums[mid] > nums[right]) {
				left = mid+1;
			} else 
				right = mid;
			
		}
		return nums[left];
		
	}
	
	public static void main(String[] args) {
		FindMinimumInRotdateSortedArray solution = new FindMinimumInRotdateSortedArray();
        
        // Test case 1: Rotated array
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Array: [3, 4, 5, 1, 2]");
        System.out.println("Minimum: " + solution.minimumInRotatedSortedArray(nums1));
        System.out.println();
        
        // Test case 2: More rotation
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Minimum: " + solution.minimumInRotatedSortedArray(nums2));
        System.out.println();
        
        // Test case 3: Array with 2 elements
        int[] nums3 = {2, 1};
        System.out.println("Array: [2, 1]");
        System.out.println("Minimum: " + solution.minimumInRotatedSortedArray(nums3));
        System.out.println();
        
        // Test case 4: No rotation (already sorted)
        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println("Array: [1, 2, 3, 4, 5]");
        System.out.println("Minimum: " + solution.minimumInRotatedSortedArray(nums4));
        System.out.println();
        
        // Test case 5: Single element
        int[] nums5 = {1};
        System.out.println("Array: [1]");
        System.out.println("Minimum: " + solution.minimumInRotatedSortedArray(nums5));
    }

}
