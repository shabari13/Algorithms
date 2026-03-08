package neetcode250.arrays_hashing;
/*
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. However, you can buy it then immediately sell it on the same day. Also, you are allowed to perform any number of transactions but can hold at most one share of the stock at any time.

Find and return the maximum profit you can achieve.

Example 1:

Input: prices = [7,1,5,3,6,4]

Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3. Total profit is 4 + 3 = 7.

Example 2:

Input: prices = [1,2,3,4,5]

Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4. Total profit is 4.

Constraints:

1 <= prices.length <= 30,000
0 <= prices[i] <= 10,000
The key insight is that any multi-day profit can be broken down into a sum of single-day gains. If the price goes 1 → 5 → 6,
 buying at 1 and selling at 6 (+5) is identical to collecting (5-1) + (6-5) = 4 + 1 = 5. So instead of trying to find the perfect buy/sell windows, 
 we simply walk through every consecutive pair of days and collect any uphill difference. Skip the downhills, sum the uphills — done.

 */
public class BestTimeToBuyAndSellStockII {
	
	public static int maxProfit(int[] nums) {
	
		int totalProfit = 0;
		for(int i = 1 ; i < nums.length; i++) {
			if(nums[i] > nums[i-1]) {
				totalProfit += nums[i] - nums[i-1];
			}
		}
		return totalProfit;	
	}
	   private static void runTest(String label, int[] prices) {
	        System.out.println("─────────────────────────────────────────");
	        System.out.println("Test : " + label);
	        System.out.print ("Prices: ");
	        for (int p : prices) System.out.print(p + " ");
	        System.out.println();

	        // ── walk-through trace ──────────────────────────────────────────────
	        System.out.println("\nDay-by-day trace:");
	        int running = 0;
	        for (int i = 1; i < prices.length; i++) {
	            int diff = prices[i] - prices[i - 1];
	            String action;
	            if (diff > 0) {
	                running += diff;
	                action = "▲ PROFIT +" + diff + "  → running total = " + running;
	            } else if (diff == 0) {
	                action = "= no change  (skip)";
	            } else {
	                action = "▼ price drops  (skip)";
	            }
	            System.out.printf("  Day %d→%d  price %d→%d   %s%n",
	                    i - 1, i, prices[i - 1], prices[i], action);
	        }

	        int result = maxProfit(prices);
	        System.out.println("\n★ Max Profit = " + result);
	        System.out.println();
	    }


	    // ─────────────────────────────────────────────────────────────────────────
	    //  MAIN – multiple sample inputs
	    // ─────────────────────────────────────────────────────────────────────────
	    public static void main(String[] args) {

	        // ── Test 1 : LeetCode example 1 ──────────────────────────────────────
	        // prices = [7, 1, 5, 3, 6, 4]
	        // Rising segments: 1→5 (+4),  3→6 (+3)   ⟹  total 7
	        runTest("LeetCode Example 1  [7,1,5,3,6,4]",
	                new int[]{7, 1, 5, 3, 6, 4});

	        // ── Test 2 : LeetCode example 2 ──────────────────────────────────────
	        // prices = [1, 2, 3, 4, 5]
	        // Every day rises:  +1+1+1+1 = 4
	        runTest("LeetCode Example 2  [1,2,3,4,5]",
	                new int[]{1, 2, 3, 4, 5});

	        // ── Test 3 : LeetCode example 3 ──────────────────────────────────────
	        // prices = [7, 6, 4, 3, 1]
	        // Always falling – never buy  ⟹  0
	        runTest("LeetCode Example 3  [7,6,4,3,1]",
	                new int[]{7, 6, 4, 3, 1});

	        // ── Test 4 : Single day ───────────────────────────────────────────────
	        runTest("Single day  [5]",
	                new int[]{5});

	        // ── Test 5 : Two days, profit ─────────────────────────────────────────
	        runTest("Two days, profit  [1,10]",
	                new int[]{1, 10});

	        // ── Test 6 : Mixed with plateau ──────────────────────────────────────
	        // prices = [3, 3, 5, 0, 0, 3, 1, 4]
	        runTest("Mixed with plateau  [3,3,5,0,0,3,1,4]",
	                new int[]{3, 3, 5, 0, 0, 3, 1, 4});
	    }
}
