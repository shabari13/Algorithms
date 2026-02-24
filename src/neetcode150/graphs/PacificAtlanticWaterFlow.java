package neetcode150.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. Water can also flow into the ocean from cells adjacent to the ocean.

Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.

Example 1:



Input: heights = [
  [4,2,7,3,4],
  [7,4,6,4,7],
  [6,3,5,3,6]
]

Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]
Example 2:

Input: heights = [[1],[1]]

Output: [[0,0],[1,0]]
Constraints:

1 <= heights.length, heights[r].length <= 100
0 <= heights[r][c] <= 1000
*/
public class PacificAtlanticWaterFlow {
	
	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> result = new ArrayList<>();
		int rows = heights.length;
		int cols = heights[0].length;
		boolean[][] atlantic = new boolean[rows][cols];
		boolean[][] pacific = new boolean[rows][cols];
	
		for(int i = 0; i < rows; i++) {
			dfs(heights, pacific, i, 0);
			dfs(heights, atlantic, i, cols-1);
		}
		for(int j =0; j < cols; j++) {
			dfs(heights, pacific, 0, j);
			dfs(heights, atlantic, rows-1, j);
		}
		for(int i = 0 ; i< rows; i++) {
			for(int  j= 0 ; j < cols; j++) {
				if(pacific[i][j] && atlantic[i][j]) {
					result.add(Arrays.asList(i, j));
				}
					
			}
		}
		return result;
	}
	
	public void dfs(int[][] heights, boolean[][] visited, int row, int col) {
		int rows = heights.length;
		int cols = heights[0].length;
		visited[row][col] = true;
		int[][] directions = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
		for(int[] direction: directions) {
			int newRow = row + direction[0];
			int newCol = col + direction[1];
			
			if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol] 
					&& heights[newRow][newCol] > heights[row][col]) {
				dfs(heights, visited, newRow, newCol);
			}
			
		}
		
	}
	
	 public static void main(String[] args) {
	        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

	        int[][] input1 = {
	            {1, 2, 2, 3, 5},
	            {3, 2, 3, 4, 4},
	            {2, 4, 5, 3, 1},
	            {6, 7, 1, 4, 5},
	            {5, 1, 1, 2, 4}
	        };

	        int[][] input2 = {
	            {1}
	        };

	        System.out.println("Test Case 1:");
	        List<List<Integer>> result1 = solution.pacificAtlantic(input1);
	        for (List<Integer> cell : result1) {
	            System.out.println(cell);
	        }

	        System.out.println("\nTest Case 2:");
	        List<List<Integer>> result2 = solution.pacificAtlantic(input2);
	        for (List<Integer> cell : result2) {
	            System.out.println(cell);
	        }
	    }

}
