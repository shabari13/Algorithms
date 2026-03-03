package neetcode150.dynamic_programming_2D;
/*
 * There is an m x n grid where you are allowed to move either down or to the right at any point in time.

Given the two integers m and n, return the number of possible unique paths that can be taken from the top-left corner of the grid (grid[0][0]) to the bottom-right corner (grid[m - 1][n - 1]).

You may assume the output will fit in a 32-bit integer.

Example 1:



Input: m = 3, n = 6

Output: 21
Example 2:

Input: m = 3, n = 3

Output: 6
Constraints:

1 <= m, n <= 100
 */
public class UniquePaths {
	
	public static int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for(int i = 0; i < n; i++) {
			dp[0][i] = 1;
		}
		for(int j=0; j < m; j++) {
			dp[j][0] = 1;
		}
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n ; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
		
	}
	   public static void main(String[] args) {

	        System.out.println("Test Case 1: m=3, n=3");
	        int result1 = uniquePaths(3, 3);
	        System.out.println("Output: " + result1);
	        System.out.println("========================");

	        System.out.println("Test Case 2: m=3, n=7");
	        int result2 = uniquePaths(3, 7);
	        System.out.println("Output: " + result2);
	        System.out.println("========================");

	        System.out.println("Test Case 3: m=1, n=5");
	        int result3 = uniquePaths(1, 5);
	        System.out.println("Output: " + result3);
	        System.out.println("========================");

	        System.out.println("Test Case 4: m=5, n=5");
	        int result4 = uniquePaths(5, 5);
	        System.out.println("Output: " + result4);
	    }

}
