package neetcode150.dynamic_programming;
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

Time Complexity: O(amount × coins)

We loop through amounts from 1 to 11 (amount times)
For each amount, we check all coin types (coins times)
So if amount = 11 and we have 3 coins: 11 × 3 = 33 operations

Space Complexity: O(amount)

We create an array of size (amount + 1)
For amount = 11, we need an array of size 12
We only store one number per slot
Basically come up with a dp array where for each coin we check and have an array upto 
amount. If the coin is less the amount then it is the min between i and dp[i-coin]
 */
public class CoinChange {
	
	public int coinChange(int[] coins, int amount) {
		
		int[] dp = new int[amount+1];
		dp[0] = 0;
		for(int i=1; i<=amount; i++) {
			dp[i] = amount+1;
		}
		for(int i = 1; i <= amount; i++) {
			for(int coin : coins) {
				if(coin <= i) {
					dp[i] = Math.min(dp[i], dp[i - coin]+1);
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
