package neetcode150.graphs;
/*
 * Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.

An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. You may assume water is surrounding the grid (i.e., all the edges are water).

Example 1:

Input: grid = [
    ["0","1","1","1","0"],
    ["0","1","0","1","0"],
    ["1","1","0","0","0"],
    ["0","0","0","0","0"]
  ]
Output: 1
Example 2:

Input: grid = [
    ["1","1","0","0","1"],
    ["1","1","0","0","1"],
    ["0","0","1","0","0"],
    ["0","0","0","1","1"]
  ]
Output: 4
Constraints:

1 <= grid.length, grid[i].length <= 100
grid[i][j] is '0' or '1'.
Time Complexity: O(M × N)
We visit every cell at most twice — once in the main loop, and once during DFS. Since DFS turns '1's to '0's, no cell is processed more than twice.
Space Complexity: O(M × N)
In the worst case (entire grid is land), the DFS recursion stack can go as deep as M×N (imagine a snake-shaped island winding through the entire grid). 
So the call stack uses O(M×N) space in the worst case. No extra data structures are used.
Basically you keep a noOfIsland countrer. 
You loop through every element and check if it is 1 and then we increment the islandcount.
And then we call dfs to make its adjacent cells which are 1 to 0 so that we dont visit them again
We call dfs recursively to call row+1, row-1, col+1, col-1;

 */
public class NumberOfIslands {
	public static int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0)
			return 0;
		int noOfIslands = 0;
		int m = grid.length;
		int n = grid[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == '1') {
					noOfIslands++;
					dfs(grid, i, j);
				}
			}
		}
		return noOfIslands;
	}
	
	public static void dfs(char[][] grid, int row, int col) {
		if(row < 0 || row >= grid.length
			|| col < 0 || col >= grid[0].length
			|| grid[row][col] == '0') {
				return;
		}
		grid[row][col] = '0';
		dfs(grid, row+1, col);
		dfs(grid, row-1, col);
		dfs(grid, row, col+1);
		dfs(grid, row, col-1);
		
		
	}
	  public static void main(String[] args) {

	        // --- Test Case 1: All connected → 1 island ---
	        char[][] grid1 = {
	            {'1','1','1','1','0'},
	            {'1','1','0','1','0'},
	            {'1','1','0','0','0'},
	            {'0','0','0','0','0'}
	        };
	        System.out.println("Test Case 1:");
	        System.out.println("Expected: 1, Got: " + numIslands(grid1));

	        // --- Test Case 2: Three separate islands ---
	        char[][] grid2 = {
	            {'1','1','0','0','0'},
	            {'1','1','0','0','0'},
	            {'0','0','1','0','0'},
	            {'0','0','0','1','1'}
	        };
	        System.out.println("\nTest Case 2:");
	        System.out.println("Expected: 3, Got: " + numIslands(grid2));

	        // --- Test Case 3: All water → 0 islands ---
	        char[][] grid3 = {
	            {'0','0','0'},
	            {'0','0','0'},
	            {'0','0','0'}
	        };
	        System.out.println("\nTest Case 3:");
	        System.out.println("Expected: 0, Got: " + numIslands(grid3));

	        // --- Test Case 4: All land but in a checkerboard (no connections) → 4 islands ---
	        char[][] grid4 = {
	            {'1','0','1'},
	            {'0','1','0'},
	            {'1','0','1'}
	        };
	        System.out.println("\nTest Case 4:");
	        System.out.println("Expected: 5, Got: " + numIslands(grid4));

	        // --- Test Case 5: Single cell land ---
	        char[][] grid5 = {
	            {'1'}
	        };
	        System.out.println("\nTest Case 5:");
	        System.out.println("Expected: 1, Got: " + numIslands(grid5));

	        // --- Test Case 6: Single row with alternating land/water ---
	        char[][] grid6 = {
	            {'1','0','1','0','1'}
	        };
	        System.out.println("\nTest Case 6:");
	        System.out.println("Expected: 3, Got: " + numIslands(grid6));
	    }

}
