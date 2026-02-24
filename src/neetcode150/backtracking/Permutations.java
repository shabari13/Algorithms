package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Given an array nums of unique integers, return all the possible permutations. You may return the answer in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [7]

Output: [[7]]
Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10

ComplexityReasonTimeO(n × n!)There are n! permutations; copying each takes O(n)SpaceO(n) (excl. output)Recursion depth = n; current & used are size nOutput spaceO(n × n!)Storing all permutations
 Idea Behind the Solution (Normal Explanation)

We use backtracking.

Backtracking means:

Try one number.

Then try another number that hasn’t been used.

Keep going until the permutation is complete.

Then go back (undo last choice) and try a different number.

Think of it like building permutations step by step.
At each step:

Choose a number not already used.

Add it to the current permutation.

Continue.

When the permutation length equals input length → save it.

Then remove the last number and try another option.

This way, we explore all possible arrangements.

🧸 Explain Like You’re 5 Years Old

Imagine you have 3 toys:

1, 2, 3

You want to arrange them in every possible way.

First:

Pick 1.

Then pick 2.

Then pick 3 → 🎉 We made: 1 2 3

Go back.

Now pick 3.

Then pick 2 → 🎉 We made: 1 3 2

Now go back to start.

Pick 2:

Then 1 → then 3 → 🎉 2 1 3

Then 3 → then 1 → 🎉 2 3 1

Pick 3:

Then 1 → then 2 → 🎉 3 1 2

Then 2 → then 1 → 🎉 3 2 1

We tried every toy in every position.
That’s permutation!
 */
public class Permutations {
	
	public  List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		boolean[] used = new boolean[nums.length];
		backtrack(nums, used, new ArrayList<>(), result);
		return result;
		
	}
	
	public  void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
		if(current.size() == nums.length) {
			result.add(new ArrayList<>(current));
			return;
		}
		for(int i = 0; i < nums.length; i++) {
			if(used[i]) continue;
			current.add(nums[i]);
			used[i] = true;
			backtrack(nums, used, current, result);
			current.remove(current.size() - 1);
			used[i] = false;
		}
	}
	
	 public static void main(String[] args) {

	        Permutations solver = new Permutations();

	        // Sample 1: [1, 2, 3]  → 3! = 6 permutations
	        int[] nums1 = {1, 2, 3};
	        System.out.println("Input: [1, 2, 3]");
	        System.out.println("Permutations: " + solver.permute(nums1));
	        System.out.println("Count: " + solver.permute(nums1).size());
	        System.out.println();

	        // Sample 2: [0, 1]  → 2! = 2 permutations
	        int[] nums2 = {0, 1};
	        System.out.println("Input: [0, 1]");
	        System.out.println("Permutations: " + solver.permute(nums2));
	        System.out.println("Count: " + solver.permute(nums2).size());
	        System.out.println();

	        // Sample 3: [1]  → 1! = 1 permutation
	        int[] nums3 = {1};
	        System.out.println("Input: [1]");
	        System.out.println("Permutations: " + solver.permute(nums3));
	        System.out.println("Count: " + solver.permute(nums3).size());
	        System.out.println();

	        // Sample 4: [1, 2, 3, 4]  → 4! = 24 permutations
	        int[] nums4 = {1, 2, 3, 4};
	        System.out.println("Input: [1, 2, 3, 4]");
	        System.out.println("Permutations: " + solver.permute(nums4));
	        System.out.println("Count: " + solver.permute(nums4).size());
	    }

}
