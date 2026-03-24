package neetcode250.dynamic_programming;
/*
 * You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase. After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.

You may choose to start at the index 0 or the index 1 floor.

Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.

Example 1:

Input: cost = [1,2,3]

Output: 2
Explanation: We can start at index = 1 and pay the cost of cost[1] = 2 and take two steps to reach the top. The total cost is 2.

Example 2:

Input: cost = [1,2,1,2,1,1,1]

Output: 4
Explanation: Start at index = 0.

Pay the cost of cost[0] = 1 and take two steps to reach index = 2.
Pay the cost of cost[2] = 1 and take two steps to reach index = 4.
Pay the cost of cost[4] = 1 and take two steps to reach index = 6.
Pay the cost of cost[6] = 1 and take one step to reach the top.
The total cost is 4.
Constraints:

2 <= cost.length <= 100
0 <= cost[i] <= 100
 /**
     * PROBLEM: LeetCode 746 - Min Cost Climbing Stairs
     *
     * Given an integer array `cost` where cost[i] is the cost of the i-th step,
     * once you pay the cost at step i, you can climb 1 or 2 steps.
     * You can start from index 0 or index 1.
     * Return the minimum cost to reach the top of the floor (past the last index).
     *
     * APPROACH: Bottom-Up Dynamic Programming
     *
     * Define dp[i] = minimum cost to REACH step i (not including cost[i] itself).
     * Base cases:
     *   dp[0] = 0  (you can start here for free)
     *   dp[1] = 0  (you can start here for free)
     *
     * Recurrence relation:
     *   dp[i] = min(dp[i-1] + cost[i-1],   <- came from step i-1, paid cost[i-1]
     *               dp[i-2] + cost[i-2])    <- came from step i-2, paid cost[i-2]
     *
     * The "top" is index n (one past the last step), so we compute dp[n].
     *
     * TIME COMPLEXITY : O(n) - single pass through the array
     * SPACE COMPLEXITY: O(n) - dp array of size n+1
     *                   (can be optimized to O(1) using two variables)
     
     Time & Space Complexity
Standard version (with full dp array):

Time: O(n) — one loop over all n+1 steps, each doing O(1) work
Space: O(n) — the dp array of size n+1

Space-optimized version (two variables):

Time: O(n) — same single pass
Space: O(1) — we only ever need the previous two dp values, so two variables (prev1, prev2) replace the entire array
 */
public class MinCostClimbingStairs {
	public static int minCostClimbingStairs(int[] cost) {
		int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= n; i++) {
        	int fromOneBelow = dp[i-1] + cost[i -1];
        	int from2Below = dp[i-2] + cost[i-2];
        	dp[i] = Math.min(fromOneBelow, from2Below);
        }
        return dp[n];
    }
	
	 // -----------------------------------------------------------------------
    public static int minCostClimbingStairsOptimized(int[] cost) {
 
        int n = cost.length;
 
        // prev2 = dp[i-2], prev1 = dp[i-1]
        int prev2 = 0; // dp[0]
        int prev1 = 0; // dp[1]
 
        for (int i = 2; i <= n; i++) {
            int current = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = current;
        }
 
        return prev1; // This is dp[n]
    }
	
	   // -----------------------------------------------------------------------
    private static void printIterations(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
 
        System.out.println("    cost array : " + java.util.Arrays.toString(cost));
        System.out.println("    Step  |  dp[i-1]+cost[i-1]  |  dp[i-2]+cost[i-2]  |  dp[i] chosen");
        System.out.println("    ------|---------------------|---------------------|----------------");
        System.out.printf("    dp[0] = 0  (free start)%n");
        System.out.printf("    dp[1] = 0  (free start)%n");
 
        for (int i = 2; i <= n; i++) {
            int fromOne = dp[i - 1] + cost[i - 1];
            int fromTwo = dp[i - 2] + cost[i - 2];
            dp[i] = Math.min(fromOne, fromTwo);
            String chosen = (fromOne <= fromTwo) ? "came from step " + (i-1) : "came from step " + (i-2);
            System.out.printf("    dp[%d]  |  %d + %d = %-11d|  %d + %d = %-11d|  %d  (%s)%n",
                    i,
                    dp[i - 1], cost[i - 1], fromOne,
                    dp[i - 2], cost[i - 2], fromTwo,
                    dp[i], chosen);
        }
        System.out.println("    Answer = dp[" + n + "] = " + dp[n]);
    }
 
    // -----------------------------------------------------------------------
    // MAIN METHOD – multiple test cases
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
 
        System.out.println("==========================================================");
        System.out.println("       Min Cost Climbing Stairs - DP Solution");
        System.out.println("==========================================================\n");
 
        // ------------------------------------------------------------------
        // TEST CASE 1: LeetCode Example 1
        // cost = [10, 15, 20]
        // Expected output: 15
        // Explanation: Start at index 1, pay 15, jump 2 steps to the top.
        // ------------------------------------------------------------------
        System.out.println("TEST CASE 1: cost = [10, 15, 20]");
        System.out.println("Expected : 15");
        int[] cost1 = {10, 15, 20};
        System.out.println("  Iterations:");
        printIterations(cost1);
        System.out.println("  Result (standard)   : " + minCostClimbingStairs(cost1));
        System.out.println("  Result (O(1) space) : " + minCostClimbingStairsOptimized(cost1));
        System.out.println();
 
        // ------------------------------------------------------------------
        // TEST CASE 2: LeetCode Example 2
        // cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
        // Expected output: 6
        // Explanation: Skip the 100-cost steps by always jumping over them.
        // ------------------------------------------------------------------
        System.out.println("TEST CASE 2: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]");
        System.out.println("Expected : 6");
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("  Iterations:");
        printIterations(cost2);
        System.out.println("  Result (standard)   : " + minCostClimbingStairs(cost2));
        System.out.println("  Result (O(1) space) : " + minCostClimbingStairsOptimized(cost2));
        System.out.println();
 
        // ------------------------------------------------------------------
        // TEST CASE 3: All equal costs
        // cost = [5, 5, 5, 5]
        // Expected output: 10
        // Explanation: Jump every 2 steps: 0->2->top, paying 5+5=10
        // ------------------------------------------------------------------
        System.out.println("TEST CASE 3: cost = [5, 5, 5, 5]  (all equal)");
        System.out.println("Expected : 10");
        int[] cost3 = {5, 5, 5, 5};
        System.out.println("  Iterations:");
        printIterations(cost3);
        System.out.println("  Result (standard)   : " + minCostClimbingStairs(cost3));
        System.out.println("  Result (O(1) space) : " + minCostClimbingStairsOptimized(cost3));
        System.out.println();
 
        // ------------------------------------------------------------------
        // TEST CASE 4: Two steps only (minimum input size)
        // cost = [0, 0]
        // Expected output: 0
        // ------------------------------------------------------------------
        System.out.println("TEST CASE 4: cost = [0, 0]  (minimum size, zero cost)");
        System.out.println("Expected : 0");
        int[] cost4 = {0, 0};
        System.out.println("  Iterations:");
        printIterations(cost4);
        System.out.println("  Result (standard)   : " + minCostClimbingStairs(cost4));
        System.out.println("  Result (O(1) space) : " + minCostClimbingStairsOptimized(cost4));
        System.out.println();
 
        // ------------------------------------------------------------------
        // TEST CASE 5: Heavily skewed
        // cost = [1, 1, 1, 999, 1, 1]
        // Expected output: 3  (avoid the 999 step completely)
        // ------------------------------------------------------------------
        System.out.println("TEST CASE 5: cost = [1, 1, 1, 999, 1, 1]  (one huge spike)");
        System.out.println("Expected : 3");
        int[] cost5 = {1, 1, 1, 999, 1, 1};
        System.out.println("  Iterations:");
        printIterations(cost5);
        System.out.println("  Result (standard)   : " + minCostClimbingStairs(cost5));
        System.out.println("  Result (O(1) space) : " + minCostClimbingStairsOptimized(cost5));
        System.out.println();
 
        System.out.println("==========================================================");
        System.out.println("  Complexity Summary");
        System.out.println("  Standard version  : Time O(n), Space O(n)");
        System.out.println("  Optimized version : Time O(n), Space O(1)");
        System.out.println("==========================================================");
    }
}
