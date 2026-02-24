package neetcode150.graphs;
/*
 * You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).

An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the grid are surrounded by water.

The area of an island is defined as the number of cells within the island.

Return the maximum area of an island in grid. If no island exists, return 0.

Example 1:



Input: grid = [
  [0,1,1,0,1],
  [1,0,1,0,1],
  [0,1,1,0,1],
  [0,1,0,0,1]
]

Output: 6
Explanation: 1's cannot be connected diagonally, so the maximum area of the island is 6.
Time Complexity: O(R × C)
Every cell is visited at most once — when we find land we immediately mark it 0. So we do a constant amount of work per cell. R = rows, C = columns.
Space Complexity: O(R × C)
In the worst case (all land), the DFS call stack goes as deep as the total number of cells (imagine a giant spiral of land). The stack could hold up to R×C frames. No extra data structures are used beyond the implicit call stack.
basically check if a particular cell is 1. then call dfs and make that  cell and adjucent cell as 0.Then count the ares startitng with 1
by recursively calling dfs for row above, row down, and col above and col down.
 */
public class MaxAreaOfIsland {

	public static int maxAreaOfIsland(int[][] grid) {
		if(grid == null || grid.length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int maxArea = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 1) {
					int area = dfs(grid, i, j);
					maxArea = Math.max(area,  maxArea);
				}
			}
		}
		return maxArea;
	}
	
	public static int dfs(int[][] grid, int row, int col) {
		if(row < 0 || row >= grid.length || 
				col < 0 || col >= grid[0].length)
			return 0;
		if (grid[row][col] == 0) {
            return 0;
        }
		grid[row][col] = 0;
		int area = 1;
		area += dfs(grid, row + 1, col);
		area += dfs(grid, row - 1, col);
		area += dfs(grid, row, col + 1);
		area += dfs(grid, row, col - 1);
		return area;
		
	}
	
	  private static void printGrid(int[][] grid) {
	        System.out.println("  Grid:");
	        for (int[] row : grid) {
	            System.out.print("    ");
	            for (int cell : row) {
	                System.out.print(cell + " ");
	            }
	            System.out.println();
	        }
	    }

	    // -------------------------------------------------------------------------
	    // STEP 4: Main method with multiple test cases
	    // -------------------------------------------------------------------------
	    public static void main(String[] args) {

	        System.out.println("========================================");
	        System.out.println("       MAX AREA OF ISLAND - TESTS       ");
	        System.out.println("========================================\n");

	        // ------------------------------------------------------------------
	        // TEST CASE 1: Standard LeetCode example
	        // Expected Output: 6
	        // ------------------------------------------------------------------
	        System.out.println("TEST 1: Standard LeetCode Example");
	        int[][] grid1 = {
	            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
	            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
	            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
	        };
	        printGrid(grid1);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid1));
	        System.out.println("  Expected: 6\n");

	        // ------------------------------------------------------------------
	        // TEST CASE 2: All water — no island at all
	        // Expected Output: 0
	        // ------------------------------------------------------------------
	        System.out.println("TEST 2: All Water (no islands)");
	        int[][] grid2 = {
	            {0, 0, 0},
	            {0, 0, 0},
	            {0, 0, 0}
	        };
	        printGrid(grid2);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid2));
	        System.out.println("  Expected: 0\n");

	        // ------------------------------------------------------------------
	        // TEST 3: All land — entire grid is one island
	        // Expected Output: 9
	        // ------------------------------------------------------------------
	        System.out.println("TEST 3: All Land (entire grid is one island)");
	        int[][] grid3 = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };
	        printGrid(grid3);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid3));
	        System.out.println("  Expected: 9\n");

	        // ------------------------------------------------------------------
	        // TEST 4: Single cell island
	        // Expected Output: 1
	        // ------------------------------------------------------------------
	        System.out.println("TEST 4: Single Cell Island");
	        int[][] grid4 = {
	            {0, 0, 0},
	            {0, 1, 0},
	            {0, 0, 0}
	        };
	        printGrid(grid4);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid4));
	        System.out.println("  Expected: 1\n");

	        // ------------------------------------------------------------------
	        // TEST 5: Multiple separate islands, pick the largest
	        // Expected Output: 4
	        // ------------------------------------------------------------------
	        System.out.println("TEST 5: Multiple Separate Islands");
	        int[][] grid5 = {
	            {1, 1, 0, 1},
	            {1, 1, 0, 0},
	            {0, 0, 0, 1},
	            {1, 0, 0, 1}
	        };
	        printGrid(grid5);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid5));
	        System.out.println("  Expected: 4\n");

	        // ------------------------------------------------------------------
	        // TEST 6: Diagonal cells do NOT connect — they are separate islands
	        // Expected Output: 1  (each 1 is isolated)
	        // ------------------------------------------------------------------
	        System.out.println("TEST 6: Diagonal cells do NOT connect");
	        int[][] grid6 = {
	            {1, 0, 1},
	            {0, 1, 0},
	            {1, 0, 1}
	        };
	        printGrid(grid6);
	        System.out.println("  Max Area: " + maxAreaOfIsland(grid6));
	        System.out.println("  Expected: 1\n");

	        System.out.println("========================================");
	        System.out.println("             ALL TESTS DONE             ");
	        System.out.println("========================================");
	    }
}
