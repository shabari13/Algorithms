package blind75.matrix;
/*
 * 
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

Time Complexity: O(n²)

The transpose step visits each element above/on the diagonal: approximately n²/2 operations
The reverse step visits each element once: n² operations
Total: O(n²), where n is the number of rows (or columns, since it's a square)

Space Complexity: O(1)

We only use a few extra variables (temp, left, right, i, j)
We modify the matrix in-place without creating any new data structures
No additional space proportional to input size is used
 */
public class RotateImage {
	
	public int[][] rotate(int[][] matrix) {
		
		int  rows = matrix.length;
		int cols = matrix[0].length;
		
		for(int i = 0; i < rows; i++) {
			for(int j = i; j < cols; j++) {
				int temp = matrix[i][j];
				
				matrix[i][j] = matrix[j][i];
				
				matrix[j][i] = temp;
				
			}
			
		}
		
		for(int  i = 0 ; i < rows; i++) {
			int left = 0;
			int right = cols-1;
			while(left <= right) {
				int temp = matrix[i][left];
				matrix[i][left] = matrix[i][right];
				matrix[i][right] = temp;
				left++;
				right--;
			}
			
			
		}
		
		return matrix;
		
	}
	
	public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        RotateImage solution = new RotateImage();
        
        // Test case 1: 3x3 matrix
        System.out.println("Test Case 1: 3x3 Matrix");
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Before rotation:");
        printMatrix(matrix1);
        solution.rotate(matrix1);
        System.out.println("After rotation:");
        printMatrix(matrix1);
        
        // Test case 2: 4x4 matrix
        System.out.println("Test Case 2: 4x4 Matrix");
        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        System.out.println("Before rotation:");
        printMatrix(matrix2);
        solution.rotate(matrix2);
        System.out.println("After rotation:");
        printMatrix(matrix2);
        
        // Test case 3: 2x2 matrix
        System.out.println("Test Case 3: 2x2 Matrix");
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Before rotation:");
        printMatrix(matrix3);
        solution.rotate(matrix3);
        System.out.println("After rotation:");
        printMatrix(matrix3);
        
        // Test case 4: 1x1 matrix
        System.out.println("Test Case 4: 1x1 Matrix");
        int[][] matrix4 = {{1}};
        System.out.println("Before rotation:");
        printMatrix(matrix4);
        solution.rotate(matrix4);
        System.out.println("After rotation:");
        printMatrix(matrix4);
    }
}


