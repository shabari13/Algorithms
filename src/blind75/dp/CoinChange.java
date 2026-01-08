package blind75.dp;
/*
 * 
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Time Complexity: O(amount × coins)

We loop through every amount from 1 to 11 (that's 11 times)
For each amount, we check every coin (that's 3 coins)
Total: 11 × 3 = 33 operations
Generally: amount × number of coins

Space Complexity: O(amount)

We use one array dp of size (amount + 1)
For amount = 11, we use 12 slots
We don't use any other significant memory

The Big Idea
We're going to figure out the fewest coins needed for every amount from $0 to $11, one step at a time. It's like climbing stairs, where each stair tells us "how many coins do I need?"


dp[i] = Math.min(dp[i], dp[i - coin] + 1); how do we conclude this?

11:47 AM
Great question! Let me break down why this formula works.

The Logic Behind dp[i] = Math.min(dp[i], dp[i - coin] + 1)
The Thinking Process
When we're at amount i and we want to use a coin, we're asking:

"If I use this coin, how many total coins will I need?"

Let's think step-by-step:

If I use this coin (let's say it's worth coin dollars)
Then I still need to make (i - coin) dollars
We already know the minimum coins needed for (i - coin) — it's stored in dp[i - coin]!
So total coins = coins needed for (i - coin) + 1 (the coin we just used)
That gives us: dp[i - coin] + 1



 */
public class CoinChange {
	
	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			dp[i] = amount + 1;
		}
		for(int i = 1; i <= amount; i++) {
			for(int coin : coins) {
				if(coin <= i) {
					dp[i] = Math.min(dp[i], (dp[i - coin] + 1));
				}
			}
		}
		
	 return dp[amount] > amount ? -1:dp[amount];
	}
	
	 public static void main(String[] args) {
	        // Sample input
	        int[] coins = {1, 2, 5};
	        int amount = 11;
	        
	        // Call the method
	        int result = coinChange(coins, amount);
	        
	        // Print the output
	        System.out.println("Coins available: [1, 2, 5]");
	        System.out.println("Target amount: " + amount);
	        System.out.println("Minimum coins needed: " + result);
	  }
	

}
