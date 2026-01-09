package blind75.dp;

/*
 * 
 * here is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Imagine you're playing a video game where you have a grid (like a checkerboard). You start at the top-left corner and want to reach the bottom-right corner to get a treasure!
But there are rules:

You can only move RIGHT →
You can only move DOWN ↓
You can NEVER go back up or left!

The question is: How many different ways can you reach the treasure?

🧩 How the Solution Works
Think of it like building a house with LEGO blocks:
Step 1: Set up your game board

We create a table (2D array) where each box remembers "how many ways can I reach THIS box?"

Step 2: Fill in the easy boxes

First row: If you're on the top edge, there's only ONE way to get there - keep going right! So every box = 1
First column: If you're on the left edge, there's only ONE way - keep going down! So every box = 1

Step 3: Fill in the rest using magic math!

For any other box: "How many ways can I get here?"
Answer: Add the ways from the box ABOVE me + the box to my LEFT
Why? Because you can only come from those two directions!

Step 4: The answer is in the final box!

The bottom-right corner has the total number of unique paths!

⏱️ Time Complexity: O(m × n)
Why?

We visit every single cell in the grid exactly once
If the grid is m rows × n columns, we do work for m × n cells
Example: 3×7 grid = 21 cells to visit = O(21) = O(m×n)


💾 Space Complexity: O(m × n)
Why?

We create a 2D array of size m × n to store our answers
Example: 3×7 grid needs an array with 21 spaces

Space Optimization Note: This can be optimized to O(n) by only keeping track of the current row, but the basic solution uses O(m × n).

 */
public class UniquePaths {
	
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for(int  i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for(int j = 0; j < n ; j++) {
			dp[0][j] = 1;
		}
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		return dp[m-1][n-1];
	}
	
	// Main method with test cases
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        
        // Test Case 1: 3x7 grid
        int m1 = 3, n1 = 7;
        int result1 = solution.uniquePaths(m1, n1);
        System.out.println("Grid Size: " + m1 + " x " + n1);
        System.out.println("Number of Unique Paths: " + result1);
        System.out.println("-----------------------------------");
        
        // Test Case 2: 3x2 grid
        int m2 = 3, n2 = 2;
        int result2 = solution.uniquePaths(m2, n2);
        System.out.println("Grid Size: " + m2 + " x " + n2);
        System.out.println("Number of Unique Paths: " + result2);
        System.out.println("-----------------------------------");
        
        // Test Case 3: 1x1 grid (edge case)
        int m3 = 1, n3 = 1;
        int result3 = solution.uniquePaths(m3, n3);
        System.out.println("Grid Size: " + m3 + " x " + n3);
        System.out.println("Number of Unique Paths: " + result3);
        System.out.println("-----------------------------------");
        
        // Test Case 4: 5x5 grid
        int m4 = 5, n4 = 5;
        int result4 = solution.uniquePaths(m4, n4);
        System.out.println("Grid Size: " + m4 + " x " + n4);
        System.out.println("Number of Unique Paths: " + result4);
        System.out.println("-----------------------------------");
        
        // Test Case 5: 2x10 grid
        int m5 = 2, n5 = 10;
        int result5 = solution.uniquePaths(m5, n5);
        System.out.println("Grid Size: " + m5 + " x " + n5);
        System.out.println("Number of Unique Paths: " + result5);
        System.out.println("-----------------------------------");
    }

}
