package neetcode250.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * You are given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,-1,1,2], k = 2

Output: 4
Explanation: [2], [2,-1,1], [-1,1,2], [2] are the subarrays whose sum is equals to k.

Example 2:

Input: nums = [4,4,4,4,4,4], k = 4

Output: 6
Constraints:

1 <= nums.length <= 20,000
-1,000 <= nums[i] <= 1,000
-10,000,000 <= k <= 10,000,000
We use prefix sums + a HashMap. Instead of checking every possible subarray (O(n²)), we track a running sum as we walk the array. 
At each index i, we ask: "Has there been a point earlier where the sum was currentSum - k?"
 If yes, the elements between that earlier point and now form a subarray summing to exactly k. The HashMap lets us answer that question in O(1).

TimeO(n)One single pass through the array. HashMap get/put are O(1) averageSpaceO(n)The HashMap stores at most n+1 unique prefix sums
 */
public class SubarraySumEqualsK {
	 public static int subarraySum(int[] nums, int k) {
		 
			/*
			 * Map<Integer, Integer> prefixSum = new HashMap<>(); prefixSum.put(0,1); int
			 * count = 0; int currentSum = 0; for(int num : nums) { currentSum += num; int
			 * needed = currentSum - k; if(prefixSum.containsKey(needed)) { count +=
			 * prefixSum.get(needed); } prefixSum.put(currentSum,
			 * prefixSum.getOrDefault(currentSum, 0) + 1); } return count;
			 */
	     HashMap<Integer, Integer> prefixCount = new HashMap<>();
	        prefixCount.put(0, 1);

	        int currentSum = 0;   // running prefix sum up to index i
	        int count      = 0;   // total valid subarrays found

	        for (int i = 0; i < nums.length; i++) {

	            // Step 1 – extend the running sum
	            currentSum += nums[i];

	            // Step 2 – if (currentSum - k) was seen before,
	            //          those earlier prefix sums form valid subarrays with us.
	            int needed = currentSum - k;
	            if (prefixCount.containsKey(needed)) {
	                count += prefixCount.get(needed);
	            }

	            // Step 3 – record this prefix sum for future iterations
	            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
	        }

	        return count;
	 }
	 
	    public static int subarraySumVerbose(int[] nums, int k) {
	        System.out.println("  i | nums[i] | currentSum | needed=(sum-k) | map lookup | count | map after");
	        System.out.println("  --|---------|------------|----------------|------------|-------|----------");

	        HashMap<Integer, Integer> prefixCount = new HashMap<>();
	        prefixCount.put(0, 1);

	        int currentSum = 0;
	        int count      = 0;

	        for (int i = 0; i < nums.length; i++) {
	            currentSum += nums[i];
	            int needed  = currentSum - k;
	            int found   = prefixCount.getOrDefault(needed, 0);
	            count      += found;
	            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);

	            System.out.printf("  %d |   %4d  |     %5d  |      %5d     |  found %-4d|  %-4d | %s%n",
	                    i, nums[i], currentSum, needed, found, count, prefixCount);
	        }
	        return count;
	    }

	    // ─────────────────────────────────────────────────────────────────────────
	    // MAIN – multiple test cases
	    // ─────────────────────────────────────────────────────────────────────────
	    public static void main(String[] args) {

	        System.out.println("╔══════════════════════════════════════════════════════╗");
	        System.out.println("║         SUBARRAY SUM EQUALS K  –  Java Solution      ║");
	        System.out.println("╚══════════════════════════════════════════════════════╝\n");

	        // ── Test 1 ──────────────────────────────────────────────────────────
	        int[] nums1 = {1, 1, 1};
	        int k1 = 2;
	        System.out.println("━━━ Test 1 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [1, 1, 1]   k = 2");
	        System.out.println("Explanation: subarrays [1,1] at index(0-1) and [1,1] at index(1-2) sum to 2.");
	        System.out.println("\nDetailed iteration trace:");
	        int result1 = subarraySumVerbose(nums1, k1);
	        System.out.println("➤ Result = " + result1 + "  (expected 2)\n");

	        // ── Test 2 ──────────────────────────────────────────────────────────
	        int[] nums2 = {1, 2, 3};
	        int k2 = 3;
	        System.out.println("━━━ Test 2 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [1, 2, 3]   k = 3");
	        System.out.println("Explanation: subarrays [3] at index(2) and [1,2] at index(0-1) sum to 3.");
	        System.out.println("\nDetailed iteration trace:");
	        int result2 = subarraySumVerbose(nums2, k2);
	        System.out.println("➤ Result = " + result2 + "  (expected 2)\n");

	        // ── Test 3 – negative numbers ────────────────────────────────────────
	        int[] nums3 = {1, -1, 1, 1, -1};
	        int k3 = 1;
	        System.out.println("━━━ Test 3 (negative numbers) ━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [1, -1, 1, 1, -1]   k = 1");
	        System.out.println("\nDetailed iteration trace:");
	        int result3 = subarraySumVerbose(nums3, k3);
	        System.out.println("➤ Result = " + result3 + "  (expected 7)\n");

	        // ── Test 4 – single element equals k ────────────────────────────────
	        int[] nums4 = {5};
	        int k4 = 5;
	        System.out.println("━━━ Test 4 (single element) ━━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [5]   k = 5");
	        System.out.println("➤ Result = " + subarraySum(nums4, k4) + "  (expected 1)\n");

	        // ── Test 5 – no valid subarray ───────────────────────────────────────
	        int[] nums5 = {1, 2, 3};
	        int k5 = 10;
	        System.out.println("━━━ Test 5 (no valid subarray) ━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [1, 2, 3]   k = 10");
	        System.out.println("➤ Result = " + subarraySum(nums5, k5) + "  (expected 0)\n");

	        // ── Test 6 – all zeros ───────────────────────────────────────────────
	        int[] nums6 = {0, 0, 0};
	        int k6 = 0;
	        System.out.println("━━━ Test 6 (all zeros, k=0) ━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("Array : [0, 0, 0]   k = 0");
	        System.out.println("➤ Result = " + subarraySum(nums6, k6) + "  (expected 6)\n");

	        System.out.println("╔══════════════════════════════════════════════════════╗");
	        System.out.println("║  Complexity Summary                                   ║");
	        System.out.println("║  Time  : O(n)  – one pass, HashMap ops are O(1) avg  ║");
	        System.out.println("║  Space : O(n)  – HashMap holds up to n+1 entries     ║");
	        System.out.println("╚══════════════════════════════════════════════════════╝");
	    }
}
