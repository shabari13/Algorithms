package neetcode150.mathandgeometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an m x n matrix of integers matrix, return a list of all elements within the matrix in spiral order.

Example 1:

Input: matrix = [[1,2],[3,4]]

Output: [1,2,4,3]
Example 2:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]

Output: [1,2,3,6,9,8,7,4,5]
Example 3:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]

Output: [1,2,3,4,8,12,11,10,9,5,6,7]
Constraints:

1 <= matrix.length, matrix[i].length <= 10
-100 <= matrix[i][j] <= 100

Time Complexity: O(m × n)

Where m is the number of rows and n is the number of columns
We visit each element in the matrix exactly once
Each element is added to the result list once

Space Complexity: O(1)

We only use a constant amount of extra space for our boundary variables (top, bottom, left, right)
The output list doesn't count toward space complexity as it's required for the answer
If we count the output, it would be O(m × n)

just have 4 counters which keep track of top row, bottom row, left and right
Traverse through first row, increment top
traverse through last column decrement right
then chech if top >= bottom and then travers from right to left in the bottom row an dthen bottom--
then check if right >= left and traverse through first column and left++
 */
public class SpiralMatrix {
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		
		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;
		while(top <= bottom && left <= right) {
			for(int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			top++;
			
			for(int j = top; j <= bottom; j++) {
				result.add(matrix[j][right]);
			}
			right--;
			if(top <= bottom)
				for(int m = right; m >= left; m--) {
					result.add(matrix[bottom][m]);
				}
			bottom--;
			if(right >= left) {
				for(int n = bottom; n >= top; n--) {
					result.add(matrix[n][left]);
				}
			}
			left++;
		}
		return result;
		
	}

	 public static void main(String[] args) {
	        SpiralMatrix solution = new SpiralMatrix();
	        
	        // Test Case 1: 3x3 matrix
	        int[][] matrix1 = {
	            {1, 2, 3},
	            {4, 5, 6},
	            {7, 8, 9}
	        };
	        System.out.println("Test Case 1 - 3x3 matrix:");
	        System.out.println("Input: " + Arrays.deepToString(matrix1));
	        System.out.println("Output: " + solution.spiralOrder(matrix1));
	        System.out.println();
	        
	        // Test Case 2: 3x4 matrix
	        int[][] matrix2 = {
	            {1, 2, 3, 4},
	            {5, 6, 7, 8},
	            {9, 10, 11, 12}
	        };
	        System.out.println("Test Case 2 - 3x4 matrix:");
	        System.out.println("Input: " + Arrays.deepToString(matrix2));
	        System.out.println("Output: " + solution.spiralOrder(matrix2));
	        System.out.println();
	        
	        // Test Case 3: Single row
	        int[][] matrix3 = {
	            {1, 2, 3, 4}
	        };
	        System.out.println("Test Case 3 - Single row:");
	        System.out.println("Input: " + Arrays.deepToString(matrix3));
	        System.out.println("Output: " + solution.spiralOrder(matrix3));
	        System.out.println();
	        
	        // Test Case 4: Single column
	        int[][] matrix4 = {
	            {1},
	            {2},
	            {3}
	        };
	        System.out.println("Test Case 4 - Single column:");
	        System.out.println("Input: " + Arrays.deepToString(matrix4));
	        System.out.println("Output: " + solution.spiralOrder(matrix4));
	        System.out.println();
	        
	        // Test Case 5: 1x1 matrix
	        int[][] matrix5 = {
	            {5}
	        };
	        System.out.println("Test Case 5 - 1x1 matrix:");
	        System.out.println("Input: " + Arrays.deepToString(matrix5));
	        System.out.println("Output: " + solution.spiralOrder(matrix5));
	    }
}
