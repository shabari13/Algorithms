package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Medium
Topics
Company Tags
Hints
You are given an array of integers candidates, which may contain duplicates, and a target integer target. Your task is to return a list of all unique combinations of candidates where the chosen numbers sum to target.

Each element from candidates may be chosen at most once within a combination. The solution set must not contain duplicate combinations.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: candidates = [9,2,2,4,6,1,5], target = 8

Output: [
  [1,2,5],
  [2,2,4],
  [2,6]
]
Example 2:

Input: candidates = [1,2,3,4,5], target = 7

Output: [
  [1,2,4],
  [2,5],
  [3,4]
]
Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
Medium
Topics
Company Tags
Hints
You are given an array of integers candidates, which may contain duplicates, and a target integer target. Your task is to return a list of all unique combinations of candidates where the chosen numbers sum to target.

Each element from candidates may be chosen at most once within a combination. The solution set must not contain duplicate combinations.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: candidates = [9,2,2,4,6,1,5], target = 8

Output: [
  [1,2,5],
  [2,2,4],
  [2,6]
]
Example 2:

Input: candidates = [1,2,3,4,5], target = 7

Output: [
  [1,2,4],
  [2,5],
  [3,4]
]
Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
Imagine you have a bag of numbered candies, and you want to find all the ways to pick some candies so their numbers add up to a target number. BUT each candy can only be used once (unlike Combination Sum I), and some candies have the same number — if you pick the same-numbered candy twice from the same "position", you'd get duplicate answers, so we skip those. We sort the candies first so same-numbered ones are next to each other, making it easy to spot and skip duplicates!
Viewed a file, created a file, read a fileViewed a file, created a file, read a fileCheck skillCombination Sum II Java solutionCombinationSumII.javaPresented fileDone
Step-by-Step Explanation (Like You're 5 🧸)
Step 1 — Sort the candy bag: We sort the array [10,1,2,7,6,1,5] → [1,1,2,5,6,7,10]. Now same numbers are neighbors. This helps us find duplicates easily.
Step 2 — Try picking each candy: Starting from index 0, we pick a number and subtract it from the target. Then we try picking the next candy (not the same one again, so we pass i+1).
Step 3 — Found the target? Save it! When remaining == 0, we found a valid combo. We save a copy and go back.
Step 4 — Too big? Stop early! Since the array is sorted, if candidates[i] > remaining, every number after it is also too big. We break immediately — this is called pruning.
Step 5 — Same number at same level? Skip! This is the key trick. If candidates[i] == candidates[i-1] and i > start, we're at the same decision level trying the same value again. That would produce a duplicate answer. So we continue and skip it.
Step 6 — Backtrack: After exploring a path, we remove the last element and try the next candidate. This is the "undo" step of backtracking.

 */
public class CombinationSumII {
	
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(candidates, target, 0, new ArrayList<>(), result);		
		return result;
		
	}
	
	public static void backtrack(int[] candidates, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
		if(remaining == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		for(int i = start; i < candidates.length; i++) {
			if(candidates[i] > remaining) {
				break;
			} 
			
			if(i > start && candidates[i] == candidates[i-1]) {
				continue;
			}
			current.add(candidates[i]);
			backtrack(candidates, remaining-candidates[i], i+1, current, result);
			current.remove(current.size() - 1);
		}
		
	}
	
	 public static void main(String[] args) {

	        System.out.println("╔══════════════════════════════════════════╗");
	        System.out.println("║       COMBINATION SUM II - SOLUTIONS     ║");
	        System.out.println("╚══════════════════════════════════════════╝\n");

	        // ── TEST 1 ──────────────────────────────────────────────────────────────
	        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
	        int target1 = 8;
	        System.out.println("▶ Test 1:");
	        System.out.println("  Input   : candidates = " + Arrays.toString(candidates1) + ", target = " + target1);
	        List<List<Integer>> result1 = combinationSum2(candidates1, target1);
	        System.out.println("  Output  : " + result1);
	        System.out.println("  Expected: [[1,1,6],[1,2,5],[1,7],[2,6]]\n");

	        // ── TEST 2 ──────────────────────────────────────────────────────────────
	        int[] candidates2 = {2, 5, 2, 1, 2};
	        int target2 = 5;
	        System.out.println("▶ Test 2:");
	        System.out.println("  Input   : candidates = " + Arrays.toString(candidates2) + ", target = " + target2);
	        List<List<Integer>> result2 = combinationSum2(candidates2, target2);
	        System.out.println("  Output  : " + result2);
	        System.out.println("  Expected: [[1,2,2],[5]]\n");

	        // ── TEST 3 ──────────────────────────────────────────────────────────────
	        int[] candidates3 = {3, 1, 3, 5, 1, 1};
	        int target3 = 6;
	        System.out.println("▶ Test 3:");
	        System.out.println("  Input   : candidates = " + Arrays.toString(candidates3) + ", target = " + target3);
	        List<List<Integer>> result3 = combinationSum2(candidates3, target3);
	        System.out.println("  Output  : " + result3);
	        System.out.println("  Expected: [[1,1,1,3],[1,2,3] or similar depending on input]\n");

	        // ── TEST 4 (no solution) ────────────────────────────────────────────────
	        int[] candidates4 = {2, 4, 6};
	        int target4 = 7;
	        System.out.println("▶ Test 4 (No Solution):");
	        System.out.println("  Input   : candidates = " + Arrays.toString(candidates4) + ", target = " + target4);
	        List<List<Integer>> result4 = combinationSum2(candidates4, target4);
	        System.out.println("  Output  : " + result4);
	        System.out.println("  Expected: []\n");

	        // ── TEST 5 (single element) ─────────────────────────────────────────────
	        int[] candidates5 = {1, 1, 1, 1, 1};
	        int target5 = 3;
	        System.out.println("▶ Test 5 (All same elements):");
	        System.out.println("  Input   : candidates = " + Arrays.toString(candidates5) + ", target = " + target5);
	        List<List<Integer>> result5 = combinationSum2(candidates5, target5);
	        System.out.println("  Output  : " + result5);
	        System.out.println("  Expected: [[1,1,1]]\n");

	        // ── DETAILED TRACE for Test 2 ────────────────────────────────────────────
	        System.out.println("══════════════════════════════════════════════════");
	        System.out.println(" DETAILED ITERATION TRACE for Test 2");
	        System.out.println(" candidates = [2,5,2,1,2], target = 5");
	        System.out.println(" After sort: [1,2,2,2,5]");
	        System.out.println("══════════════════════════════════════════════════");
	        System.out.println();
	        System.out.println(" backtrack(remaining=5, start=0, current=[])");
	        System.out.println("   i=0 → pick 1 → current=[1]");
	        System.out.println("     backtrack(remaining=4, start=1, current=[1])");
	        System.out.println("       i=1 → pick 2 → current=[1,2]");
	        System.out.println("         backtrack(remaining=2, start=2, current=[1,2])");
	        System.out.println("           i=2 → pick 2 → current=[1,2,2]");
	        System.out.println("             backtrack(remaining=0) → ✅ ADD [1,2,2]");
	        System.out.println("           i=3 → candidates[3]=2 == candidates[2]=2, i>start → SKIP (duplicate)");
	        System.out.println("           i=4 → candidates[4]=5 > remaining=2 → BREAK");
	        System.out.println("         backtrack done, remove 2 → current=[1,2]");
	        System.out.println("       i=2 → candidates[2]=2 == candidates[1]=2, i>start → SKIP");
	        System.out.println("       i=3 → candidates[3]=2 == candidates[2]=2, i>start → SKIP");
	        System.out.println("       i=4 → pick 5 → remaining=4-5=-1 < 0? No, 5>4 → BREAK");
	        System.out.println("     backtrack done, remove 2 → current=[1]");
	        System.out.println("   ... (remaining combinations explored similarly)");
	        System.out.println("   i=4 → pick 5 → current=[5]");
	        System.out.println("     backtrack(remaining=0) → ✅ ADD [5]");
	        System.out.println();
	        System.out.println(" Final Result: [[1,2,2],[5]]");

	        // ── COMPLEXITY ───────────────────────────────────────────────────────────
	        System.out.println();
	        System.out.println("══════════════════════════════════════════════════");
	        System.out.println(" TIME & SPACE COMPLEXITY");
	        System.out.println("══════════════════════════════════════════════════");
	        System.out.println(" Time  : O(2^n) in the worst case — at each index we");
	        System.out.println("         decide to include or exclude it, forming a");
	        System.out.println("         binary decision tree of depth n. Sorting adds");
	        System.out.println("         O(n log n) but is dominated by O(2^n).");
	        System.out.println(" Space : O(n) for the recursion call stack depth");
	        System.out.println("         (at most n levels deep) + O(n) for the current");
	        System.out.println("         combination list. Result storage is extra.");
	        System.out.println("══════════════════════════════════════════════════");
	    }

}
