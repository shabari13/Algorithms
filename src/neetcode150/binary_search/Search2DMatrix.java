package neetcode150.binary_search;
/*
 * 
 * You are given an m x n 2-D integer array matrix and an integer target.

Each row in matrix is sorted in non-decreasing order.
The first integer of every row is greater than the last integer of the previous row.
Return true if target exists within matrix or false otherwise.

Can you write a solution that runs in O(log(m * n)) time?

Example 1:



Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 10

Output: true
Example 2:



Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 15

Output: false
Time Complexity: O(log(m × n))

We have m rows and n columns, so total elements = m × n
Binary search divides the search space in half each time
Number of iterations = log₂(m × n)

For our example: 12 elements → log₂(12) ≈ 3.58, so maximum 4 iterations!
Space Complexity: O(1)

We only use a few variables: left, right, mid, midRow, midCol, midValue
No extra arrays or data structures
Constant space!
 */
public class Search2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int left = 0;
		int right = rows * cols - 1;
		while(left <= right) {
			int mid = left + (right - left)/2;
			int midRow = mid / cols;
			int midCol = mid % cols;
			int midVal = matrix[midRow][midCol];
			if(midVal ==  target)
				return true;
			else if(midVal < target) {
				left = mid + 1;
			} else {
				right  = mid - 1;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
        Search2DMatrix solution = new Search2DMatrix();
        
        // Test Case 1
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("Matrix: ");
        printMatrix(matrix1);
        System.out.println("Target: " + target1);
        System.out.println("Found: " + solution.searchMatrix(matrix1, target1));
        System.out.println();
        
        // Test Case 2
        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target2 = 13;
        System.out.println("Test Case 2:");
        System.out.println("Matrix: ");
        printMatrix(matrix2);
        System.out.println("Target: " + target2);
        System.out.println("Found: " + solution.searchMatrix(matrix2, target2));
        System.out.println();
        
        // Test Case 3
        int[][] matrix3 = {{1}};
        int target3 = 1;
        System.out.println("Test Case 3:");
        System.out.println("Matrix: ");
        printMatrix(matrix3);
        System.out.println("Target: " + target3);
        System.out.println("Found: " + solution.searchMatrix(matrix3, target3));
        System.out.println();
        
        // Test Case 4
        int[][] matrix4 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target4 = 11;
        System.out.println("Test Case 4:");
        System.out.println("Matrix: ");
        printMatrix(matrix4);
        System.out.println("Target: " + target4);
        System.out.println("Found: " + solution.searchMatrix(matrix4, target4));
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

}
