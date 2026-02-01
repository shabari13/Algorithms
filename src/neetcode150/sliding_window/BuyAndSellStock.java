package neetcode150.sliding_window;
/*
 * 
 * You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.

You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.

Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.

Example 1:

Input: prices = [10,1,5,6,7,1]

Output: 6
Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.

Example 2:

Input: prices = [10,8,7,5,2]

Output: 0
Explanation: No profitable transactions can be made, thus the max profit is 0.

Constraints:

1 <= prices.length <= 100
0 <= prices[i] <= 100

Time Complexity: O(n)

We go through the array exactly once
n is the number of days (length of the prices array)
Even if there are 1 million prices, we only look at each one once!

Space Complexity: O(1)

We only use two extra variables: minPrice and maxProfit
No matter how big the array is, we always use the same tiny amount of extra memory
O(1) means "constant" - it doesn't grow with the input size
 */
public class BuyAndSellStock {
	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		int minPrice = prices[0];
		for(int i = 1; i < prices.length; i++) {
			int profit = prices[i] - minPrice;
			maxProfit = Math.max(maxProfit,  profit);
			minPrice = Math.min(minPrice, prices[i]);
		}
		return maxProfit;
		
	}
	public static void main(String[] args) {
		BuyAndSellStock solution = new BuyAndSellStock();
        
        // Test Case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1: prices = [7, 1, 5, 3, 6, 4]");
        System.out.println("Max Profit: " + solution.maxProfit(prices1));
        System.out.println();
        
        // Test Case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 2: prices = [7, 6, 4, 3, 1]");
        System.out.println("Max Profit: " + solution.maxProfit(prices2));
        System.out.println();
        
        // Test Case 3
        int[] prices3 = {2, 4, 1};
        System.out.println("Test Case 3: prices = [2, 4, 1]");
        System.out.println("Max Profit: " + solution.maxProfit(prices3));
        System.out.println();
        
        // Test Case 4
        int[] prices4 = {3, 2, 6, 5, 0, 3};
        System.out.println("Test Case 4: prices = [3, 2, 6, 5, 0, 3]");
        System.out.println("Max Profit: " + solution.maxProfit(prices4));
        System.out.println();
        
        // Test Case 5 - Single price
        int[] prices5 = {5};
        System.out.println("Test Case 5: prices = [5]");
        System.out.println("Max Profit: " + solution.maxProfit(prices5));
    }

}
