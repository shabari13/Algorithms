package neetcode150.dynamic_programming;
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
Time Complexity: O(n)

We iterate through the array once (from index 2 to n-1)
Each iteration does constant work: just comparing two values
Total operations: proportional to the length of the array

💾 Space Complexity:

Standard Solution: O(n) - we create a DP array of size n
Optimized Solution: O(1) - we only keep track of the last 2 values (prev1 and prev2)
 */
public class MinCostClimbingPairs {
	
	public int minCostClimbingStairs(int[] costs) {
		int prev2 = costs[0];
		int prev1 = costs[1];
		for(int i = 2 ; i < costs.length; i++) {
			int current = costs[i] + Math.min(prev1, prev2);
			prev2 = prev1;
			prev1 = current;
		}
		return Math.min(prev1, prev2);
		
	}

	 public static void main(String[] args) {
		 MinCostClimbingPairs solution = new MinCostClimbingPairs();
	        
	        System.out.println("=== Min Cost Climbing Stairs Solutions ===\n");
	        
	        // Test Case 1
	        int[] cost1 = {10, 15, 20};
	        System.out.println("Test Case 1:");
	        System.out.println("Input: cost = [10, 15, 20]");
	        int result1 = solution.minCostClimbingStairs(cost1);
	        System.out.println("Output: " + result1);
	        System.out.println("Explanation: Start at index 1, pay 15, climb 2 steps to reach top.");
	        System.out.println();
	        
	        // Test Case 2
	        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
	        System.out.println("Test Case 2:");
	        System.out.println("Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]");
	        int result2 = solution.minCostClimbingStairs(cost2);
	        System.out.println("Output: " + result2);
	        System.out.println("Explanation: Skip the high-cost steps (100s) by taking optimal path.");
	        System.out.println();
	        
	        // Test Case 3
	        int[] cost3 = {0, 0, 0, 1};
	        System.out.println("Test Case 3:");
	        System.out.println("Input: cost = [0, 0, 0, 1]");
	        int result3 = solution.minCostClimbingStairs(cost3);
	        System.out.println("Output: " + result3);
	        System.out.println();
	        
	        // Test Case 4
	        int[] cost4 = {5, 10};
	        System.out.println("Test Case 4:");
	        System.out.println("Input: cost = [5, 10]");
	        int result4 = solution.minCostClimbingStairs(cost4);
	        System.out.println("Output: " + result4);
	        System.out.println();
	        
	     
	        
	        
	 }
}
