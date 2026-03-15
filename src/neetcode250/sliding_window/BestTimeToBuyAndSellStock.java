package neetcode250.sliding_window;
/*
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

The trick is to always remember the cheapest price we have seen so far.

Then for every new price:

Check if selling today gives better profit.

Update the cheapest price if we find a cheaper one.

So we keep track of two things:

minimum price seen so far
maximum profit possible so far

Time Complexity
O(n)

Why?

We scan the array only once.

8. Space Complexity
O(1)

Why?

We only store:

minPrice
maxProfit
profit

No extra arrays or data structures.

 */
public class BestTimeToBuyAndSellStock {
	 public static int maxProfit(int[] prices) {
	       int maxProfit = 0;
	       int minPrice = prices[0];
	       for(int i = 1; i < prices.length; i++) {
	    	   minPrice = Math.min(minPrice, prices[i]);
	    	   if(prices[i] - minPrice > 0) {
	    		   maxProfit = Math.max(maxProfit, prices[i] - minPrice);
	    	   }
	    	   
	       }
	       return maxProfit;
	 }
	 
	  public static void main(String[] args) {

	        int[] prices1 = {7,1,5,3,6,4};
	        int[] prices2 = {7,6,4,3,1};
	        int[] prices3 = {2,4,1};
	        int[] prices4 = {3,2,6,5,0,3};

	        System.out.println("Profit for prices1: " + maxProfit(prices1));
	        System.out.println("Profit for prices2: " + maxProfit(prices2));
	        System.out.println("Profit for prices3: " + maxProfit(prices3));
	        System.out.println("Profit for prices4: " + maxProfit(prices4));
	    }
}
