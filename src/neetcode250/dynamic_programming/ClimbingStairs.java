package neetcode250.dynamic_programming;
/*
 * You are given an integer n representing the number of steps to reach the top of a staircase. You can climb with either 1 or 2 steps at a time.

Return the number of distinct ways to climb to the top of the staircase.

Example 1:

Input: n = 2

Output: 2
Explanation:

1 + 1 = 2
2 = 2
Example 2:

Input: n = 3

Output: 3
Explanation:

1 + 1 + 1 = 3
1 + 2 = 3
2 + 1 = 3
Constraints:

1 <= n <= 45

Time Complexity — O(n): The loop runs from i = 3 to i = n, which is exactly n - 2 iterations. Each iteration does constant work (one addition, two assignments). So total work grows linearly with n.
Space Complexity — O(1): We use only two integer variables (prev1 and prev2) no matter how large n is. Compare this to a naive DP array solution which would use O(n) space — our sliding-window trick eliminates that entirely.

 */
public class ClimbingStairs {
	public static int climbStairs(int n) {
		if(n == 1 || n == 2) {
			return n;
		}
		int prev2 = 1;
		int prev1 = 2;
		int current = 0;
		for (int i = 3; i <= n; i++) {
            // Ways to reach step i = ways to reach step (i-1) + ways to reach step (i-2)
            current = prev1 + prev2;
 
            // Slide the window forward:
            // what was prev1 (step i-1) now becomes prev2 (step i-1 for the next iteration)
            prev2 = prev1;
 
            // what was current (step i) now becomes prev1 (step i for the next iteration)
            prev1 = current;
        }
 
        return current;
	}
	
	public static void main(String[] args) {
		System.out.println(climbStairs(6));
	}
}
