package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array nums of unique integers, return all possible subsets of nums.

The solution set must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [7]

Output: [[],[7]]
Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10

ComplexityExplanationTimeO(n × 2ⁿ)There are 2ⁿ subsets.
For each subset, we spend O(n) time copying it into the result list.SpaceO(n × 2ⁿ)We store all 2ⁿ subsets, 
and each subset can have up to n elements. The recursion stack itself goes O(n) deep,
 but that's dominated by the output storage.

 */
public class Subsets {
	
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(nums,0,new ArrayList<>(), result);
		return result;
	}
	
	public static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
		result.add(new ArrayList<>(current));
		for(int i =  start; i < nums.length; i++) {
			current.add(nums[i]);
			backtrack(nums, i+1, current, result);
			current.remove(current.size() - 1);
		}
		
	}
	
	  public static void main(String[] args) {

	        // ── Sample Input 1 ──────────────────────────────────────────
	        int[] nums1 = {1, 2, 3};
	        System.out.println("Input: " + Arrays.toString(nums1));
	        System.out.println("Subsets: " + subsets(nums1));
	        System.out.println();

	        // ── Sample Input 2 ──────────────────────────────────────────
	        int[] nums2 = {0};
	        System.out.println("Input: " + Arrays.toString(nums2));
	        System.out.println("Subsets: " + subsets(nums2));
	        System.out.println();

	        // ── Sample Input 3 ──────────────────────────────────────────
	        int[] nums3 = {1, 2};
	        System.out.println("Input: " + Arrays.toString(nums3));
	        System.out.println("Subsets: " + subsets(nums3));
	        System.out.println();

	        // ── Sample Input 4 ──────────────────────────────────────────
	        int[] nums4 = {4, 5, 6, 7};
	        System.out.println("Input: " + Arrays.toString(nums4));
	        System.out.println("Subsets: " + subsets(nums4));
	    }

}
