package neetcode150.dynamic_programming_2D;
/*
 * You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the number of distinct combinations that total up to amount. If it's impossible to make up the amount, return 0.

You may assume that you have an unlimited number of each coin and that each value in coins is unique.

Example 1:

Input: amount = 4, coins = [1,2,3]

Output: 4
Explanation:

1+1+1+1 = 4
1+1+2 = 4
2+2 = 4
1+3 = 4
Example 2:

Input: amount = 7, coins = [2,4]

Output: 0
Constraints:

1 <= coins.length <= 100
1 <= coins[i] <= 5000
0 <= amount <= 5000
Time Complexity:
O(N × amount)

Where:

N = number of coins

amount = target value

Because:

Outer loop runs for each coin

Inner loop runs from coin to amount

Space Complexity:
O(amount)

We use only one array of size amount + 1.

Idea Behind the Solution (Normal Explanation)

This is a Dynamic Programming (DP) problem.

We create a 1D array dp[] where:

dp[i] = number of ways to make amount i

We initialize dp[0] = 1
(There is exactly 1 way to make amount 0 → choose nothing)

Then:

For each coin:

For each amount from coin to amount

Add ways of making (current amount - coin)

Formula:

dp[i] += dp[i - coin]

Why?

If we know how many ways we can make (i - coin),
then by adding this coin, we can make amount i.

👶 Explanation Like You're 5 Years Old

Imagine you want to build number 5 using blocks.

You have blocks:
1 block, 2 block, 5 block.

You start with 0.
There is 1 way to make 0 (do nothing).

Now:

When you see coin 1:
You can make:
1 → using one 1
2 → using two 1s
3 → using three 1s
4 → using four 1s
5 → using five 1s

Now when you see coin 2:
You ask:
"Oh! If I know how to make 3, then I can add 2 and make 5!"

So you keep adding possibilities.

We build answers step by step, like stacking blocks.

 */
public class CoinChangeII {
	
	public static int change( int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0]  = 1;
		for(int coin : coins) {
			for(int i=coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}
	
	  public static void main(String[] args) {

	        // Test Case 1
	        int amount1 = 5;
	        int[] coins1 = {1, 2, 5};
	        System.out.println("Amount: " + amount1);
	        System.out.println("Ways: " + change(amount1, coins1));
	        System.out.println("-------------------------");

	        // Test Case 2
	        int amount2 = 3;
	        int[] coins2 = {2};
	        System.out.println("Amount: " + amount2);
	        System.out.println("Ways: " + change(amount2, coins2));
	        System.out.println("-------------------------");

	        // Test Case 3
	        int amount3 = 10;
	        int[] coins3 = {2, 5, 3, 6};
	        System.out.println("Amount: " + amount3);
	        System.out.println("Ways: " + change(amount3, coins3));
	        System.out.println("-------------------------");

	        // Test Case 4
	        int amount4 = 0;
	        int[] coins4 = {1, 2, 3};
	        System.out.println("Amount: " + amount4);
	        System.out.println("Ways: " + change(amount4, coins4));
	    }

}
