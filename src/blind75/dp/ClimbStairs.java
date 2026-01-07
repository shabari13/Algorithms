package blind75.dp;

/*
 * 
 * You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Time Complexity: O(n)

We iterate through a loop from 3 to n (n-2 iterations)
Each iteration performs constant time operations (addition and assignment)
Therefore, the time complexity is linear: O(n)
Space Complexity: O(1)

We only use three variables (prev2, prev1, current) regardless of the input size
No additional data structures like arrays or recursion stack are used
Therefore, the space complexity is constant: O(1)
hint  : this is a fibonacci series;

 */
public class ClimbStairs {
	
	public int climbStairs(int n) {
		if(n <= 2) {
			return n;
		}
		int current = 0;
		int prev2 = 1;
		int prev1 = 2;
		for(int i = 3; i<= n; i++) {
			current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
			
		}
		return current;
	}

}
