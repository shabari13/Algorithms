package neetcode150.graphs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * You are given a 
m
×
n
m×n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.

Assume the grid can only be traversed up, down, left, or right.

Modify the grid in-place.

Example 1:

Input: [
  [2147483647,-1,0,2147483647],
  [2147483647,2147483647,2147483647,-1],
  [2147483647,-1,2147483647,-1],
  [0,-1,2147483647,2147483647]
]

Output: [
  [3,-1,0,1],
  [2,2,1,-1],
  [1,-1,2,-1],
  [0,-1,3,4]
]
Example 2:

Input: [
  [0,-1],
  [2147483647,2147483647]
]

Output: [
  [0,-1],
  [1,2]
]
Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is one of {-1, 0, 2147483647}
⏱ Time Complexity

Let:

m = number of rows

n = number of columns

Each cell is:

Visited at most once

So:

Time Complexity = O(m × n)
🧠 Space Complexity

Queue can hold at most all cells.

Space Complexity = O(m × n)

We use Multi-Source Breadth-First Search (BFS).

Instead of starting BFS from every empty land (which would be slow), we:

First put all gates (0s) into a queue.

Start BFS from all gates at the same time.

Spread outward level by level.

For every empty cell (INF), update its value with distance from nearest gate.

Because BFS expands in layers, the first time we reach a cell is the shortest distance to a gate.

This is efficient because:

Each cell is visited only once.

 */
public class IslandsAndTreasure {
	static final int INF = 2147483647;
	public static void islandsAndTreasure(int[][] rooms) {
		  
		if(rooms == null || rooms.length == 0)
			return;
		int rows = rooms.length;
		int cols = rooms[0].length;
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(rooms[i][j] == 0) {
					queue.offer(new int[] { i, j});
				}
			}
		}
		int[][] directions = { {1,0}, {-1, 0}, {0,1},{0, -1} };
		while(!queue.isEmpty()) {
			int[] cell = queue.poll();
			int row = cell[0];
			int col = cell[1];
			for(int[] dir : directions) {
				int newRow = row + dir[0];
				int newCol = col + dir[1];
				if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && rooms[newRow][newCol] == INF) {
					rooms[newRow][newCol] = rooms[row][col] + 1;
					queue.offer(new int[] {newRow, newCol});
				}
				
			}
			
		}
		
		
	}
	
	  public static void printGrid(int[][] grid) {
	        for (int[] row : grid) {
	            for (int cell : row) {
	                if (cell == INF)
	                    System.out.print("INF ");
	                else
	                    System.out.print(cell + " ");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }

	    public static void main(String[] args) {

	        // Test Case 1
	        int[][] grid1 = {
	            {INF, -1, 0, INF},
	            {INF, INF, INF, -1},
	            {INF, -1, INF, -1},
	            {0, -1, INF, INF}
	        };

	        System.out.println("Before:");
	        printGrid(grid1);

	        islandsAndTreasure(grid1);

	        System.out.println("After:");
	        printGrid(grid1);

	        // Test Case 2
	        int[][] grid2 = {
	            {INF, 0},
	            {INF, INF}
	        };

	        System.out.println("Before:");
	        printGrid(grid2);

	        islandsAndTreasure(grid2);

	        System.out.println("After:");
	        printGrid(grid2);

	        // Test Case 3
	        int[][] grid3 = {
	            {0}
	        };

	        System.out.println("Before:");
	        printGrid(grid3);

	        islandsAndTreasure(grid3);

	        System.out.println("After:");
	        printGrid(grid3);
	    }

}
