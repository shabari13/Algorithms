package blind75.array;
/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104

Time ComplexityO(n)

Space ComplexityO(1)

 * 
 * 
 */
public class BestTimeToBuySellStock {
	
	public static int maxProfit(int[] prices) {
		
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		
		for(int i=0; i < prices.length; i++) {
			minPrice = Math.min(minPrice, prices[i]);
			if(prices[i] - minPrice > 0) {
				maxProfit = Math.max(maxProfit, prices[i] - minPrice);
			}
		}
		
		return maxProfit;
	}
	
	
	public static void main(String[] args) {
        // Sample input
        int[] prices = {7, 1, 5, 3, 6, 4};
        
        // Call the method and print result
        int result = maxProfit(prices);
        
        System.out.println("Stock prices: ");
        for (int i = 0; i < prices.length; i++) {
            System.out.print(prices[i] + " ");
        }
        System.out.println("\n\nMaximum Profit: " + result);
        
        // Additional test cases
        System.out.println("\n--- Additional Test Cases ---");
        
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Prices: [7,6,4,3,1] -> Profit: " + maxProfit(prices2));
        
        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println("Prices: [1,2,3,4,5] -> Profit: " + maxProfit(prices3));
    }

}
