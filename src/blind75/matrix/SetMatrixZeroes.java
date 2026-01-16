package blind75.matrix;
/*
 * 
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

 

Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 

Follow up:

A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
Time Complexity: O(m × n)

m = number of rows, n = number of columns
We scan the entire matrix 3 times (once to mark, once to set zeros, once for first row/column)
3 × (m × n) = O(m × n)

Space Complexity: O(1)

We only use two boolean variables (firstRowZero, firstColZero)
We use the matrix itself for marking, so no extra space!
This is called "constant space" or O(1)



The Clever Trick We Use
Instead of using extra paper to remember which rows and columns need zeros, we use the FIRST ROW and FIRST COLUMN of our grid as special "sticky notes"!
Step-by-Step Walkthrough
Let's use Test Case 1 to see exactly what happens:
Starting Matrix:
1  1  1
1  0  1
1  1  1
Step 1: Check First Row and First Column
Before we use them as sticky notes, we need to remember: "Do they already have zeros?"

Looking at first row [1, 1, 1] → No zeros! So firstRowZero = false
Looking at first column [1, 1, 1] → No zeros! So firstColZero = false

Step 2: Find Zeros and Mark Them
Now we look at the rest of the grid (skipping the first row and column for now).

Position (0,0): Skip (it's in first row/column)
Position (0,1): Skip (first row)
Position (0,2): Skip (first row)
Position (1,0): Skip (first column)
Position (1,1): It's 0! Found one! 🎯

We mark row 1's beginning: matrix[1][0] = 0
We mark column 1's beginning: matrix[0][1] = 0


Position (1,2): It's 1, continue
Position (2,0): Skip (first column)
Position (2,1): It's 1, continue
Position (2,2): It's 1, continue

After marking, our matrix looks like:
1  0  1   ← We marked column 1
0  0  1   ← We marked row 1
1  1  1
Step 3: Set Zeros Based on Markers
Now we read our "sticky notes" (first row and column) and set zeros:

Position (1,1): Row 1 marker is 0 OR column 1 marker is 0? YES! → Set to 0 ✓
Position (1,2): Row 1 marker is 0 OR column 2 marker is 0? YES (row)! → Set to 0
Position (2,1): Row 2 marker is 0 OR column 1 marker is 0? YES (column)! → Set to 0
Position (2,2): Row 2 marker is 0 OR column 2 m
 */
public class SetMatrixZeroes {
	
	public int[][] setZeroes(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean isFirstRowZero = false;
		boolean isFirstColZero = false;
		
		for(int j = 0; j <cols ; j++) {
			if(matrix[0][j] == 0) {
				isFirstRowZero = true;
				break;
			}
		}
		
		for (int i=0; i<rows; i++) {
			if(matrix[i][0] == 0) {
				isFirstColZero = true;
				break;
			}
		}
		
		for(int i = 1; i< rows; i++) {
			for(int j = 1; j < cols; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for(int i = 1; i< rows; i++) {
			for(int j = 1; j < cols; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}
		if(isFirstRowZero) {
			for(int j = 0; j< cols; j++) {
				matrix[0][j] = 0;
			}
		}
		
		if (isFirstColZero) {
            for(int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
		
		return matrix;
		
	}
	
	  // Helper method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

	public static void main(String[] args) {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        
        // Test case 1
        System.out.println("Test Case 1:");
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        System.out.println("Input:");
        printMatrix(matrix1);
        solution.setZeroes(matrix1);
        System.out.println("Output:");
        printMatrix(matrix1);
        
        // Test case 2
        System.out.println("Test Case 2:");
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        System.out.println("Input:");
        printMatrix(matrix2);
        solution.setZeroes(matrix2);
        System.out.println("Output:");
        printMatrix(matrix2);
        
        // Test case 3
        System.out.println("Test Case 3:");
        int[][] matrix3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Input:");
        printMatrix(matrix3);
        solution.setZeroes(matrix3);
        System.out.println("Output:");
        printMatrix(matrix3);
        
        // Test case 4
        System.out.println("Test Case 4:");
        int[][] matrix4 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Input:");
        printMatrix(matrix4);
        solution.setZeroes(matrix4);
        System.out.println("Output:");
        printMatrix(matrix4);
    }
}
