package neetcode250.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an integer array nums of size n, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Note: [1,0,3,2] and [3,0,1,2] are considered as same quadruplets.

Example 1:

Input: nums = [3,2,3,-3,1,0], target = 3

Output: [[-3,0,3,3],[-3,1,2,3]]
Example 2:

Input: nums = [1,-1,1,-1,1,-1], target = 2

Output: [[-1,1,1,1]]
Constraints:

1 <= nums.length <= 200
-1,000,000,000 <= nums[i] <= 1,000,000,000
-1,000,000,000 <= target <= 1,000,000,000

 TimeO(n³)Two nested loops O(n²) × two-pointer scan O(n)🧠 SpaceO(1) auxiliaryOnly pointers used; output list not counted🗂️ SortingO(n log n)Dominated by O(n³) overall

 */
public class FourSum {
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int n = nums.length;
		if( n < 4) return result;
		for(int i = 0; i < n - 3; i++) {
			if( i > 0 && nums[i] == nums[i-1]) continue;
			for(int j = i + 1 ; j < n - 2; j++) {
				if( j > i + 1 && nums[j] == nums[j-1]) continue;
				int left = j + 1;
				int right = n - 1;
				while(left < right) {
					int sum = nums[i] + nums[j] + nums[left] + nums[right];
					if(sum == target) {
						result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
						while(left < right && nums[left] == nums[left+1]) left++;
						while(left < right && nums[right] == nums[right - 1]) right--;
						left++;right--;
					}
					else if(sum < target) {
						left++;
					} 
					else if(sum > target) {
						right--;
					}
				}
			}
		}
		return result;
		
	}
	
	 public static void main(String[] args) {
	        // Test Case 1
	        int[] nums1 = {1, 0, -1, 0, -2, 2};
	        int target1 = 0;
	        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " + target1);
	        System.out.println("Output: " + fourSum(nums1, target1));
	        System.out.println();

	        // Test Case 2
	        int[] nums2 = {2, 2, 2, 2, 2};
	        int target2 = 8;
	        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " + target2);
	        System.out.println("Output: " + fourSum(nums2, target2));
	        System.out.println();

	        // Test Case 3
	        int[] nums3 = {-3, -2, -1, 0, 0, 1, 2, 3};
	        int target3 = 0;
	        System.out.println("Input: " + Arrays.toString(nums3) + ", Target: " + target3);
	        System.out.println("Output: " + fourSum(nums3, target3));
	        System.out.println();

	        // Test Case 4
	        int[] nums4 = {1000000000, 1000000000, 1000000000, 1000000000};
	        int target4 = -294967296;
	        System.out.println("Input: " + Arrays.toString(nums4) + ", Target: " + target4);
	        System.out.println("Output: " + fourSum(nums4, target4));
	        System.out.println();

	        // Test Case 5 - No solution
	        int[] nums5 = {1, 2, 3, 4};
	        int target5 = 100;
	        System.out.println("Input: " + Arrays.toString(nums5) + ", Target: " + target5);
	        System.out.println("Output: " + fourSum(nums5, target5));
	    }

}
