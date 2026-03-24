package neetcode250.dynamic_programming;



/*
 * You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the fewest number of coins that you need to make up the exact target amount. If it is impossible to make up the amount, return -1.

You may assume that you have an unlimited number of each coin.

Example 1:

Input: coins = [1,5,10], amount = 12

Output: 3
Explanation: 12 = 10 + 1 + 1. Note that we do not have to use every kind coin available.

Example 2:

Input: coins = [2], amount = 3

Output: -1
Explanation: The amount of 3 cannot be made up with coins of 2.

Example 3:

Input: coins = [1], amount = 0

Output: 0
Explanation: Choosing 0 coins is a valid way to make up 0.

Constraints:

1 <= coins.length <= 10
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10000

🪙 Coin Change — Dynamic Programming
💡 The Idea
The coin change problem asks: given a list of coin denominations and a target amount, what is the minimum number of coins needed to make that amount? We use bottom-up dynamic programming. We build a table dp[] where dp[i] stores the minimum coins needed to make amount i. For each amount from 1 to target, we try every coin and ask: "If I use this coin, does it reduce the count?" We build the answer incrementally — each sub-problem feeds into the next — until we reach our target.

👶 Like You're 5
Imagine you have a piggy bank with coins of different sizes — say 1¢, 5¢, and 7¢. You want to make exactly 11¢ using as few coins as possible. So you start small: "How do I make 1¢? Just one 1¢ coin." Then 2¢, 3¢... When you get to 7¢, you realize one 7¢ coin is better than seven 1¢ coins! You keep a notebook writing down the best answer for every amount, and when you finally reach 11¢, you look back at your notebook and pick the best combination. That notebook is the dp[] array!


Time Complexity: O(amount × coins)

We loop through amounts from 1 to 11 (amount times)
For each amount, we check all coin types (coins times)
So if amount = 11 and we have 3 coins: 11 × 3 = 33 operations


Space Complexity: O(amount)

 */
public class CoinChange {
	 public  int coinChange(int[] coins, int amount) {
		  // STEP 1: Create the DP array of size (amount + 1)
	        // dp[i] = minimum coins needed to make amount i
	        // We use (amount + 1) as a sentinel "infinity" value —
	        // it's larger than any valid answer (you can't need more than 'amount' coins of 1¢ each)
		 int[] dp = new int[amount+1];
		 for(int i = 0; i <= amount; i++) {
			 dp[i] = amount+1;
		 }
		 dp[0] = 0;
		 // STEP 3: Fill the DP table bottom-up
	        // For each amount from 1 to 'amount'...
		 for(int i  = 1; i <= amount; i++) {
			 
			 for(int coin : coins) {
				 if(coin <= i) {
					 // STEP 5: Key recurrence relation:
	                    // dp[i] = min(dp[i], 1 + dp[i - coin])
	                    //
	                    // "If I use one of THIS coin, I need 1 coin + however many
	                    //  coins it took to make (i - coin)"
					 dp[i] = Math.min(dp[i], dp[i-coin] + 1);
				 }
			 }
		 }
		 return dp[amount] > amount ? -1 : dp[amount];
	 }
	 public static void main(String[] args) {
	        CoinChange solution = new CoinChange();
	        
	        // Test case 1
	        int[] coins1 = {1, 2, 5};
	        int amount1 = 11;
	        System.out.println("Coins: [1, 2, 5], Amount: 11");
	        System.out.println("Output: " + solution.coinChange(coins1, amount1));
	        System.out.println();
	        
	        // Test case 2
	        int[] coins2 = {2};
	        int amount2 = 3;
	        System.out.println("Coins: [2], Amount: 3");
	        System.out.println("Output: " + solution.coinChange(coins2, amount2));
	        System.out.println();
	        
	        // Test case 3	
	        int[] coins3 = {1};
	        int amount3 = 0;
	        System.out.println("Coins: [1], Amount: 0");
	        System.out.println("Output: " + solution.coinChange(coins3, amount3));
	        System.out.println();
	        
	        // Test case 4
	        int[] coins4 = {1, 3, 4, 5};
	        int amount4 = 7;
	        System.out.println("Coins: [1, 3, 4, 5], Amount: 7");
	        System.out.println("Output: " + solution.coinChange(coins4, amount4));
	        System.out.println();
	        
	        // Test case 5
	        int[] coins5 = {5, 10, 25};
	        int amount5 = 30;
	        System.out.println("Coins: [5, 10, 25], Amount: 30");
	        System.out.println("Output: " + solution.coinChange(coins5, amount5));
	    }
}
