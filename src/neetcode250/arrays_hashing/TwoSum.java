package neetcode250.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.

You may assume that every input has exactly one pair of indices i and j that satisfy the condition.

Return the answer with the smaller index first.

Example 1:

Input: 
nums = [3,4,5,6], target = 7

Output: [0,1]
Explanation: nums[0] + nums[1] == 7, so we return [0, 1].

Example 2:

Input: nums = [4,5,6], target = 10

Output: [0,2]
Example 3:

Input: nums = [5,5], target = 10

Output: [0,1]
Constraints:

2 <= nums.length <= 1000
-10,000,000 <= nums[i] <= 10,000,000
-10,000,000 <= target <= 10,000,000
Only one valid answer exists.

Time  Complexity : O(n)
  → We iterate through the array once.
  → HashMap lookup/insert = O(1) average.
  → Total: n * O(1) = O(n).

Space Complexity : O(n)
  → In the worst case (no pair found until
    the very last element), we store n-1
    entries in the HashMap.
  → This is O(n) extra space.
 */
public class TwoSum {
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i<nums.length; i++) {
			int compliment = target - nums[i];
			if(map.containsKey(compliment)) {
				return new int[] {map.get(compliment), i};
			}
			map.put(nums[i], i);
			
		}
		return new int[] {};
		
	}
	  private static void printResult(int[] nums, int target) {
	        int[] result = twoSum(nums, target);
	        System.out.println("──────────────────────────────────────────");
	        System.out.print("Array  : [");
	        for (int i = 0; i < nums.length; i++) {
	            System.out.print(nums[i] + (i < nums.length - 1 ? ", " : ""));
	        }
	        System.out.println("]");
	        System.out.println("Target : " + target);
	        System.out.println("Output : [" + result[0] + ", " + result[1] + "]"
	            + "  →  nums[" + result[0] + "] + nums[" + result[1] + "] = "
	            + nums[result[0]] + " + " + nums[result[1]] + " = " + target);
	    }

	    // ----------------------------------------------------------
	    //  MAIN — sample calls with detailed iteration trace
	    // ----------------------------------------------------------
	    public static void main(String[] args) {

	        System.out.println("==========================================");
	        System.out.println("       TWO SUM — Java Solution            ");
	        System.out.println("==========================================");

	        // ── SAMPLE 1 ──────────────────────────────────────────
	        // nums = [2, 7, 11, 15], target = 9
	        //
	        // Iteration 1 (i=0): nums[0]=2, complement=9-2=7
	        //   → 7 NOT in map  →  map: {2:0}
	        //
	        // Iteration 2 (i=1): nums[1]=7, complement=9-7=2
	        //   → 2 IS in map at index 0  →  return [0, 1]  ✓
	        //
	        // No more iterations needed — answer found early!
	        System.out.println("\n🔍 Test 1: Classic example");
	        printResult(new int[]{2, 7, 11, 15}, 9);

	        // ── SAMPLE 2 ──────────────────────────────────────────
	        // nums = [3, 2, 4], target = 6
	        //
	        // Iteration 1 (i=0): nums[0]=3, complement=6-3=3
	        //   → 3 NOT in map  →  map: {3:0}
	        //
	        // Iteration 2 (i=1): nums[1]=2, complement=6-2=4
	        //   → 4 NOT in map  →  map: {3:0, 2:1}
	        //
	        // Iteration 3 (i=2): nums[2]=4, complement=6-4=2
	        //   → 2 IS in map at index 1  →  return [1, 2]  ✓
	        System.out.println("\n🔍 Test 2: Answer not at start");
	        printResult(new int[]{3, 2, 4}, 6);

	        // ── SAMPLE 3 ──────────────────────────────────────────
	        // nums = [3, 3], target = 6
	        //
	        // Iteration 1 (i=0): nums[0]=3, complement=6-3=3
	        //   → 3 NOT in map yet  →  map: {3:0}
	        //
	        // Iteration 2 (i=1): nums[1]=3, complement=6-3=3
	        //   → 3 IS in map at index 0  →  return [0, 1]  ✓
	        //   (Same value, different indices — valid!)
	        System.out.println("\n🔍 Test 3: Duplicate values");
	        printResult(new int[]{3, 3}, 6);

	        // ── SAMPLE 4 ──────────────────────────────────────────
	        // nums = [1, 5, 3, 7, 2], target = 10
	        //
	        // Iteration 1 (i=0): nums[0]=1, complement=10-1=9
	        //   → 9 NOT in map  →  map: {1:0}
	        //
	        // Iteration 2 (i=1): nums[1]=5, complement=10-5=5
	        //   → 5 NOT in map  →  map: {1:0, 5:1}
	        //
	        // Iteration 3 (i=2): nums[2]=3, complement=10-3=7
	        //   → 7 NOT in map  →  map: {1:0, 5:1, 3:2}
	        //
	        // Iteration 4 (i=3): nums[3]=7, complement=10-7=3
	        //   → 3 IS in map at index 2  →  return [2, 3]  ✓
	        System.out.println("\n🔍 Test 4: Longer array");
	        printResult(new int[]{1, 5, 3, 7, 2}, 10);

	        // ── SAMPLE 5 — Negative numbers ───────────────────────
	        // nums = [-3, 4, 3, 90], target = 0
	        //
	        // Iteration 1 (i=0): nums[0]=-3, complement=0-(-3)=3
	        //   → 3 NOT in map  →  map: {-3:0}
	        //
	        // Iteration 2 (i=1): nums[1]=4, complement=0-4=-4
	        //   → -4 NOT in map  →  map: {-3:0, 4:1}
	        //
	        // Iteration 3 (i=2): nums[2]=3, complement=0-3=-3
	        //   → -3 IS in map at index 0  →  return [0, 2]  ✓
	        System.out.println("\n🔍 Test 5: Negative numbers");
	        printResult(new int[]{-3, 4, 3, 90}, 0);

	        System.out.println("──────────────────────────────────────────");
	        System.out.println();
	        System.out.println("==========================================");
	        System.out.println("  COMPLEXITY ANALYSIS");
	        System.out.println("==========================================");
	        System.out.println("Time  Complexity : O(n)");
	        System.out.println("  → We iterate through the array once.");
	        System.out.println("  → HashMap lookup/insert = O(1) average.");
	        System.out.println("  → Total: n * O(1) = O(n).");
	        System.out.println();
	        System.out.println("Space Complexity : O(n)");
	        System.out.println("  → In the worst case (no pair found until");
	        System.out.println("    the very last element), we store n-1");
	        System.out.println("    entries in the HashMap.");
	        System.out.println("  → This is O(n) extra space.");
	        System.out.println("==========================================");
	    }
}
