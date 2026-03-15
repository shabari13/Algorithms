package neetcode250.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] where nums[i] + nums[j] + nums[k] == 0, and the indices i, j and k are all distinct.

The output should not contain any duplicate triplets. You may return the output and the triplets in any order.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]

Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].

Example 2:

Input: nums = [0,1,1]

Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:

Input: nums = [0,0,0]

Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:

3 <= nums.length <= 1000
-10^5 <= nums[i] <= 10^5

Time Complexity:  O(n^2)  — one outer loop O(n) × two-pointer scan O(n)
     * Space Complexity: O(1)    — ignoring output list; sorting is in-place

 */

public class ThreeSum {
	
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		int n = nums.length;
		Arrays.sort(nums);
		for(int i = 0; i < n; i++) {
			if(nums[i] > 0) break;
			if( i > 0 && nums[i] == nums[i-1]) continue;
			int left = i+1;
			int right = n - 1;
			while(left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if(sum == 0) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while(left < right && nums[left] == nums[left+1]) left++;
					while(left < right && nums[right] == nums[right - 1]) right--;
					left++;
					right--;
				} else if(sum < 0) left++;
				else {
					right--;
				}
				
			}
			
		}
		return result;
	}
	
	 public static void main(String[] args) {

	        System.out.println("══════════════════════════════════════════════");
	        System.out.println("          3SUM PROBLEM - JAVA SOLUTION        ");
	        System.out.println("══════════════════════════════════════════════\n");

	        // ── Test Case 1: Standard case with multiple triplets ────────────────
	        int[] input1 = {-1, 0, 1, 2, -1, -4};
	        System.out.println("Test 1 → Input:  " + Arrays.toString(input1));
	        System.out.println("         Output: " + threeSum(input1));
	        System.out.println("         Expect: [[-1,-1,2],[-1,0,1]]\n");

	        // ── Test Case 2: All zeros ───────────────────────────────────────────
	        int[] input2 = {0, 0, 0, 0};
	        System.out.println("Test 2 → Input:  " + Arrays.toString(input2));
	        System.out.println("         Output: " + threeSum(input2));
	        System.out.println("         Expect: [[0,0,0]]\n");

	        // ── Test Case 3: No valid triplet ────────────────────────────────────
	        int[] input3 = {1, 2, 3, 4, 5};
	        System.out.println("Test 3 → Input:  " + Arrays.toString(input3));
	        System.out.println("         Output: " + threeSum(input3));
	        System.out.println("         Expect: []\n");

	        // ── Test Case 4: All negatives ───────────────────────────────────────
	        int[] input4 = {-5, -3, -1, -2, -4};
	        System.out.println("Test 4 → Input:  " + Arrays.toString(input4));
	        System.out.println("         Output: " + threeSum(input4));
	        System.out.println("         Expect: []\n");

	        // ── Test Case 5: Mix with many duplicates ────────────────────────────
	        int[] input5 = {-2, 0, 0, 2, 2};
	        System.out.println("Test 5 → Input:  " + Arrays.toString(input5));
	        System.out.println("         Output: " + threeSum(input5));
	        System.out.println("         Expect: [[-2,0,2]]\n");

	        // ── Test Case 6: Detailed trace for [-1, 0, 1, 2, -1, -4] ───────────

	    }


}
