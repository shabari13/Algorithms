package neetcode150.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given a 2-D matrix grid. Each cell can have one of three possible values:

0 representing an empty cell
1 representing a fresh fruit
2 representing a rotten fruit
Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.

Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is impossible within the grid, return -1.

Example 1:



Input: grid = [[1,1,0],[0,1,1],[0,1,2]]

Output: 4
Example 2:

Input: grid = [[1,0,1],[0,2,0],[1,0,1]]

Output: -1
Constraints:

1 <= grid.length, grid[i].length <= 10

TIME COMPLEXITY: O(M * N)
 * --------------------------
 * • Initial scan of the entire grid:        O(M * N)
 * • BFS: each cell is added to the queue
 *   at most once and processed at most once: O(M * N)
 * • For each cell we check 4 neighbors:     O(4) = O(1) per cell
 * • Total: O(M * N)
 *
 * SPACE COMPLEXITY: O(M * N)
 * ---------------------------
 * • Queue can hold at most all M*N cells in the worst case
 *   (e.g., entire grid is rotten from the start).
 * • We mutate the input grid in-place, so no extra grid is needed
 *   (excluding the copy made in main for testing).
 * • Total auxiliary space: O(M * N)
 *

basically keep count of the fresh oranges first then also keep a queue or list which keeps track of the 
cells where rotten oranges are kept.

then keep 4 directions +1, -1 row and +1 , - 1 col.
then iterate through each rotten cell, add each direction amd make them rotten and reduct fresh and also increase minutes
check in the end if fresh is zero, return minutes
you can even do minus 1 by minutes just to make sure you get the right minute if fresh count > 0 is not added in the conditionr
 */
public class RottenOranges {
	public static int orangesRotting(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		List<int[]> rotten = new ArrayList<>();
		int fresh = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0 ; j < n; j++) {
				if(grid[i][j] == 2) {
					rotten.add(new int[] {i, j});
				}
				else if (grid[i][j] == 1) {
					fresh++;
				}
			}
			
		}
		if(fresh == 0)
			return 0;
		int minutes  = 0;
		int[][] directions = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
		while(!(rotten.size() == 0) && fresh > 0 ) {
			 List<int[]> currentMinute = rotten; 
			 rotten = new ArrayList<>();
			minutes++;
			 for (int[] cell : currentMinute) {  

				int rottenRow = cell[0];
				int rottenCol = cell[1];
				
				
				for(int[] direction : directions) {
					int newRow  = rottenRow + direction[0];
					int newCol = rottenCol + direction[1];
					if(newRow >= 0 && newRow < m && newCol >=0 && newCol < n && grid[newRow][newCol] == 1) {
						grid[newRow][newCol] = 2;
						fresh--;
						rotten.add(new int[] {newRow, newCol});
					}
				}
				

			}
			
		}
		return (fresh == 0)? minutes  : -1;
		
	}
	   // ─── Deep copy grid so original is not mutated between test calls ─────────
    private static int[][] copy(int[][] grid) {
        int[][] result = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) result[i] = grid[i].clone();
        return result;
    }
    
 // ─── Helper to print grid ──────────────────────────────────────────────────
    private static void printGrid(int[][] grid) {
        System.out.println("Grid:");
        for (int[] row : grid) {
            System.out.println("  " + Arrays.toString(row));
        }
    }

    // ─── Main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        // ── Test 1: Standard case ─────────────────────────────────────────────
        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("=== Test 1 ===");
        printGrid(grid1);
        System.out.println("Output: " + orangesRotting(copy(grid1)));
        // Expected: 4

        // ── Test 2: Isolated fresh orange (impossible) ────────────────────────
        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("\n=== Test 2 ===");
        printGrid(grid2);
        System.out.println("Output: " + orangesRotting(copy(grid2)));
        // Expected: -1

        // ── Test 3: Already no fresh oranges ─────────────────────────────────
        int[][] grid3 = {
            {0, 2}
        };
        System.out.println("\n=== Test 3 ===");
        printGrid(grid3);
        System.out.println("Output: " + orangesRotting(copy(grid3)));
        // Expected: 0

        // ── Test 4: All fresh, no rotten ─────────────────────────────────────
        int[][] grid4 = {
            {1, 1},
            {1, 1}
        };
        System.out.println("\n=== Test 4 ===");
        printGrid(grid4);
        System.out.println("Output: " + orangesRotting(copy(grid4)));
        // Expected: -1

        // ── Test 5: Single rotten orange spreading ────────────────────────────
        int[][] grid5 = {
            {2, 1, 1, 1}
        };
        System.out.println("\n=== Test 5 ===");
        printGrid(grid5);
        System.out.println("Output: " + orangesRotting(copy(grid5)));
        // Expected: 3
    }

}
