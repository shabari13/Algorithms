package neetcode150.backtracking;

import java.util.*;
/*
 * You are given an array of distinct integers nums and a target integer target. Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.

The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency of each of the chosen numbers is the same, otherwise they are different.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input:
nums = [2,5,6,9]
target = 9

Output: [[2,2,5],[9]]
Explanation:
2 + 2 + 5 = 9. We use 2 twice, and 5 once.
9 = 9. We use 9 once.

Example 2:

Input:
nums = [3,4,5]
target = 16

Output: [[3,3,3,3,4],[3,3,5,5],[4,4,4,4],[3,4,4,5]]
Example 3:

Input:
nums = [3]
target = 5

Output: []
Constraints:

All elements of nums are distinct.
1 <= nums.length <= 20
2 <= nums[i] <= 30
2 <= target <= 30

 */
public class CombinationSum {
	
	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(nums, target, 0, new ArrayList<>(), result);
		return result;
		
	}
	
	public static void backtrack(int[] nums, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
		if( remaining == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		for(int i = start; i < nums.length; i++) {
			if(nums[i] > remaining)
				break;
			current.add(nums[i]);
			backtrack(nums, remaining - nums[i], i, current, result);
			current.remove(current.size() -1);
			
		}
			
		
	}
	
	public static void main(String[] args) {

        // ---- Test Case 1 ----
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        List<List<Integer>> output1 = combinationSum(candidates1, target1);
        System.out.println("=== Test Case 1 ===");
        System.out.println("Candidates : " + Arrays.toString(candidates1));
        System.out.println("Target     : " + target1);
        System.out.println("Result     : " + output1);
        // Expected: [[2,2,3],[7]]

        System.out.println();

        // ---- Test Case 2 ----
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        List<List<Integer>> output2 = combinationSum(candidates2, target2);
        System.out.println("=== Test Case 2 ===");
        System.out.println("Candidates : " + Arrays.toString(candidates2));
        System.out.println("Target     : " + target2);
        System.out.println("Result     : " + output2);
        // Expected: [[2,2,2,2],[2,3,3],[3,5]]

        System.out.println();

        // ---- Test Case 3 ----
        int[] candidates3 = {2};
        int target3 = 1;
        List<List<Integer>> output3 = combinationSum(candidates3, target3);
        System.out.println("=== Test Case 3 ===");
        System.out.println("Candidates : " + Arrays.toString(candidates3));
        System.out.println("Target     : " + target3);
        System.out.println("Result     : " + output3);
        // Expected: [] (no combination possible)

        System.out.println();

        // ---- Test Case 4 ----
        int[] candidates4 = {1, 2, 3};
        int target4 = 4;
        List<List<Integer>> output4 = combinationSum(candidates4, target4);
        System.out.println("=== Test Case 4 ===");
        System.out.println("Candidates : " + Arrays.toString(candidates4));
        System.out.println("Target     : " + target4);
        System.out.println("Result     : " + output4);
        // Expected: [[1,1,1,1],[1,1,2],[1,3],[2,2]]
    }

}
