package neetcode150.mathandgeometry;
/*
 * Given an m x n matrix of integers matrix, if an element is 0, set its entire row and column to 0's.

You must update the matrix in-place.

Follow up: Could you solve it using O(1) space?

Example 1:



Input: matrix = [
  [0,1],
  [1,0]
]

Output: [
  [0,0],
  [0,0]
]
Example 2:



Input: matrix = [
  [1,2,3],
  [4,0,5],
  [6,7,8]
]

Output: [
  [1,0,3],
  [0,0,0],
  [6,0,8]
]
Constraints:

1 <= matrix.length, matrix[0].length <= 100
-2^31 <= matrix[i][j] <= (2^31) - 1

Time and Space Complexity
Time Complexity: O(m × n)

We scan the matrix 3 times:

Once to check first row and column: O(m + n)
Once to mark zeros: O(m × n)
Once to set zeros based on marks: O(m × n)


Overall: O(m + n) + O(m × n) + O(m × n) = O(m × n)

Space Complexity: O(1)

We only use two boolean variables (firstRowHasZero and firstColHasZero)
We use the matrix itself as storage for markers
No additional arrays or data structures are needed
This is a constant space solution

Keep tow boolean flags. One for checking if frst column or first row has zero in it.
Ater that keep a flag if any elements is zero, make that rows and column first element as 0. Then check and make other elements 0

 */
public class SetMatrixZeroes {
	
	public void setZeroes(int[][] matrix){
		boolean firstRowHasZero = false;
		boolean firstColHasZero = false;
		int rows = matrix.length;
		int cols = matrix[0].length;
		for(int i=0; i<cols; i++) {
			if(matrix[0][i] == 0)
				firstRowHasZero	= true;
		}
		for(int j = 0; j < rows; j++)
			if(matrix[j][0] == 0)
				firstColHasZero = true;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for(int i = 1 ; i < rows; i++) {
			for(int j = 1; j < cols; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
					
			}
		}
		if(firstRowHasZero) {
			for(int i = 0; i < cols; i++) {
				matrix[0][i] = 0;
			}
		}
		if(firstColHasZero) {
			for(int j = 0; j < rows; j++) {
				matrix[j][0] = 0;
			}
		}
		
	}
	
	  // Helper method to print matrix
    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        
        // Test Case 1
        System.out.println("Test Case 1:");
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        System.out.println("Before:");
        solution.printMatrix(matrix1);
        solution.setZeroes(matrix1);
        System.out.println("After:");
        solution.printMatrix(matrix1);
        
        System.out.println("\n" + "=".repeat(30) + "\n");
        
        // Test Case 2
        System.out.println("Test Case 2:");
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        System.out.println("Before:");
        solution.printMatrix(matrix2);
        solution.setZeroes(matrix2);
        System.out.println("After:");
        solution.printMatrix(matrix2);
        
        System.out.println("\n" + "=".repeat(30) + "\n");
        
        // Test Case 3
        System.out.println("Test Case 3:");
        int[][] matrix3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Before:");
        solution.printMatrix(matrix3);
        solution.setZeroes(matrix3);
        System.out.println("After:");
        solution.printMatrix(matrix3);
        
        System.out.println("\n" + "=".repeat(30) + "\n");
        
        // Test Case 4 - First row and column have zeros
        System.out.println("Test Case 4:");
        int[][] matrix4 = {
            {0, 1, 2},
            {3, 0, 5},
            {6, 7, 8}
        };
        System.out.println("Before:");
        solution.printMatrix(matrix4);
        solution.setZeroes(matrix4);
        System.out.println("After:");
        solution.printMatrix(matrix4);
    }

}
