package neetcode250.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an integer array nums of size n, find all elements that appear more than ⌊ n/3 ⌋ times. You can return the result in any order.

Example 1:

Input: nums = [5,2,3,2,2,2,2,5,5,5]

Output: [2,5]
Example 2:

Input: nums = [4,4,4,4,4]

Output: [4]
Example 3:

Input: nums = [1,2,3]

Output: []
Constraints:

1 <= nums.length <= 50,000.
-1,000,000,000 <= nums[i] <= 1,000,000,000
Follow up: Could you solve the problem in linear time and in O(1) space?

The problem asks for all elements that appear more than ⌊n/3⌋ times. The key mathematical insight is that at most 2 such elements can exist —
 if 3+ elements each appeared more than n/3 times, their total count would exceed n, which is impossible. This lets us use the Extended Boyer-Moore Voting Algorithm: 
 instead of tracking 1 candidate (like Majority Element I), we track 2. We make two linear passes — one to nominate candidates, one to verify them.

TimeO(n)Two passes over the array (Phase 1 + Phase 2)SpaceO(1)Only 4 integer variables; output holds at most 2 elements
 */
public class MajorityElementII {
	
	public static List<Integer> majorityElement(int[] nums) {
		int candidate1 = 0;
		int candidate2 = 0;
		int count1 = 0;
		int count2 = 0;
		
		for(int num : nums) {
			if(num == candidate1) {
				count1++;
			} else if(num == candidate2) {
				count2++;
				
			} else if(count1 == 0) {
				candidate1 = num;
				count1 = 1;
				
			} else if(count2 == 0) {
				candidate2 = num;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		 count1 = 0;
		 count2 = 0;
		for(int num : nums) {
			if(num == candidate1) {
				count1++;
			} else if(num == candidate2) {
				count2++;
			}
		}
		
		int threshold = nums.length / 3;
		List<Integer> result = new ArrayList<>();
		if(count1 > threshold) {
			result.add(candidate1);
		}
		if(count2 > threshold) {
			result.add(candidate2);
		}
		
		return result;
		
	}
	
	 // ──────────────────────────────────────────────────────────
    //  DETAILED TRACE  (shows every iteration step-by-step)
    // ──────────────────────────────────────────────────────────
    public static void traceExecution(int[] nums) {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│              DETAILED ITERATION TRACE                       │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        System.out.println("  Input array: " + Arrays.toString(nums));
        System.out.println("  n = " + nums.length + "  →  threshold = n/3 = " + nums.length / 3);
        System.out.println();

        int candidate1 = 0, count1 = 0;
        int candidate2 = 0, count2 = 0;

        System.out.println("══ PHASE 1: Candidate Selection ══════════════════════════════");
        System.out.printf("  %-5s %-6s  %-14s %-14s  %s%n",
                "Step", "Num", "Candidate1", "Candidate2", "Action");
        System.out.println("  ─────────────────────────────────────────────────────────────");

        int step = 1;
        for (int num : nums) {
            String action;

            if (num == candidate1) {
                count1++;
                action = num + " matches cand1 → count1 becomes " + count1;
            } else if (num == candidate2) {
                count2++;
                action = num + " matches cand2 → count2 becomes " + count2;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
                action = "Seat1 empty → cand1 = " + num + ", count1 = 1";
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
                action = "Seat2 empty → cand2 = " + num + ", count2 = 1";
            } else {
                count1--;
                count2--;
                action = "No match → cancel both: count1=" + count1 + ", count2=" + count2;
            }

            System.out.printf("  %-5d %-6d  %-14s %-14s  %s%n",
                    step++,
                    num,
                    candidate1 + "(×" + count1 + ")",
                    candidate2 + "(×" + count2 + ")",
                    action);
        }

        System.out.println();
        System.out.println("  ► Nominated candidates after Phase 1:");
        System.out.println("    Candidate 1 = " + candidate1);
        System.out.println("    Candidate 2 = " + candidate2);
        System.out.println();

        // Phase 2 verification
        System.out.println("══ PHASE 2: Verification ════════════════════════════════════");
        count1 = 0; count2 = 0;
        for (int num : nums) {
            if      (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }
        int threshold = nums.length / 3;
        System.out.println("  Actual count of " + candidate1 + " = " + count1
                + "  (need > " + threshold + ") → " + (count1 > threshold ? "✔ QUALIFIES" : "✘ fails"));
        System.out.println("  Actual count of " + candidate2 + " = " + count2
                + "  (need > " + threshold + ") → " + (count2 > threshold ? "✔ QUALIFIES" : "✘ fails"));
        System.out.println();

        List<Integer> result = new ArrayList<>();
        if (count1 > threshold) result.add(candidate1);
        if (count2 > threshold) result.add(candidate2);
        System.out.println("  ► RESULT: " + result);
        System.out.println();
    }


    // ──────────────────────────────────────────────────────────
    //  MAIN — multiple test cases
    // ──────────────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║          MAJORITY ELEMENT II  —  Java Solution              ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Test Case 1 ───────────────────────────────────────
        // [3,2,3]  →  n=3, threshold=1
        // 3 appears 2 times (> 1) ✔,  2 appears 1 time (not > 1) ✘
        // Expected: [3]
        runTest(1, new int[]{3, 2, 3},
                "Classic case — one clear majority");

        // ── Test Case 2 ───────────────────────────────────────
        // [1,1,1,3,3,2,2,2]  →  n=8, threshold=2
        // 1 appears 3 times (> 2) ✔,  2 appears 3 times (> 2) ✔
        // Expected: [1, 2]
        runTest(2, new int[]{1, 1, 1, 3, 3, 2, 2, 2},
                "Two majority elements");

        // ── Test Case 3 ───────────────────────────────────────
        // [1,2,3,4,5,6]  →  n=6, threshold=2
        // No element appears > 2 times
        // Expected: []
        runTest(3, new int[]{1, 2, 3, 4, 5, 6},
                "No majority element");

        // ── Test Case 4 ───────────────────────────────────────
        // [7]  →  n=1, threshold=0
        // 7 appears 1 time (> 0) ✔
        // Expected: [7]
        runTest(4, new int[]{7},
                "Single element array");

        // ── Test Case 5 ───────────────────────────────────────
        // [2,2,9,3,9,3,9,3,9]  →  n=9, threshold=3
        // 9 appears 4 times (> 3) ✔,  3 appears 3 times (not > 3) ✘
        // Expected: [9]
        runTest(5, new int[]{2, 2, 9, 3, 9, 3, 9, 3, 9},
                "Close call — only one qualifies");

        // ── Detailed Trace for Test Case 2 ───────────────────
        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║  DETAILED TRACE  for  [1,1,1,3,3,2,2,2]                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");
        System.out.println();
        traceExecution(new int[]{1, 1, 1, 3, 3, 2, 2, 2});

        // ── Complexity Summary ────────────────────────────────
        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY ANALYSIS                                        ║");
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println("║  Time  Complexity:  O(n)                                    ║");
        System.out.println("║    • Phase 1: one pass over n elements       → O(n)         ║");
        System.out.println("║    • Phase 2: one pass over n elements       → O(n)         ║");
        System.out.println("║    • Total: 2 × O(n)                         = O(n)         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Complexity:  O(1)                                    ║");
        System.out.println("║    • Only 4 integer variables used           → O(1)         ║");
        System.out.println("║    • Output list holds at most 2 elements    → O(1)         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");
    }

    // Helper to print a labelled test result
    static void runTest(int id, int[] nums, String description) {
        List<Integer> result = majorityElement(nums);
        System.out.printf("  Test %d (%s)%n", id, description);
        System.out.printf("    Input  : %s%n", Arrays.toString(nums));
        System.out.printf("    Output : %s%n%n", result);
    }

}
