package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an array nums of integers, which may contain duplicates. Return all possible subsets.

The solution must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,1]

Output: [[],[1],[1,2],[1,1],[1,2,1],[2]]
Example 2:

Input: nums = [7,7]

Output: [[],[7], [7,7]]
Constraints:

1 <= nums.length <= 11
-20 <= nums[i] <= 20

Time Complexity

Total subsets = 2ⁿ

We explore each subset once

Time Complexity:
O(2^n)

Sorting:

O(n log n)

Final:

O(n * 2^n)

(Because copying subsets takes O(n))

📦 Space Complexity

Recursive stack:

O(n)

Result storage:

O(n * 2^n)

Total:

O(n * 2^n)
 */
public class SubsetsII {
	
	public static List<List<Integer>> subsetsWithDup(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(nums, 0, new ArrayList<>(), result);
		return result;
		
	}
	
	public static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
		result.add(new ArrayList<>(current));
		
		for(int i = start; i < nums.length; i++) {
			if(i > start && nums[i] == nums[i-1]) continue;
			current.add(nums[i]);
			backtrack(nums, i+1, current, result);
			current.remove(current.size() - 1);
			
		}
		
	}
	
	 // ✅ Helper to print results nicely
    private static void printResult(int[] nums, List<List<Integer>> result) {
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output (" + result.size() + " subsets): " + result);
        System.out.println();
    }

    // ✅ Main method with multiple test cases
    public static void main(String[] args) {

        // Test 1: Standard case with one duplicate
        int[] nums1 = {1, 2, 2};
        printResult(nums1, subsetsWithDup(nums1));

        // Test 2: All duplicates
        int[] nums2 = {1, 1, 1};
        printResult(nums2, subsetsWithDup(nums2));

        // Test 3: No duplicates
        int[] nums3 = {1, 2, 3};
        printResult(nums3, subsetsWithDup(nums3));

        // Test 4: Two pairs of duplicates
        int[] nums4 = {1, 2, 2, 3, 3};
        printResult(nums4, subsetsWithDup(nums4));

        // Test 5: Single element
        int[] nums5 = {0};
        printResult(nums5, subsetsWithDup(nums5));
    }

}
