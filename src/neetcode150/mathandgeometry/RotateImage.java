package neetcode150.mathandgeometry;
/*
 * Time Complexity: O(n²)

The transpose operation uses two nested loops, visiting each element above and on the diagonal: approximately n²/2 operations
The reverse operation processes each row with n/2 swaps per row for n rows: n × (n/2) = n²/2 operations
Total: O(n²/2 + n²/2) = O(n²)

Space Complexity: O(1)

We only use a constant amount of extra space (the temp variable for swapping)
The rotation is done in-place, modifying the original matrix without creating a copy
No additional data structures are used that scale with input size      
 */
public class RotateImage {
	
	public void rotate(int[][] matrix){
		int n = matrix.length;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
				
			}
		}
		
		for(int i = 0; i < n; i++) {
			int left = 0;
			int right = n - 1;
			while(left < right) {
				int temp = matrix[i][left];
				matrix[i][left] = matrix[i][right];
				matrix[i][right] = temp;
				left++;
				right--;
			}
		}
		
	}
	  // Helper method to print matrix
    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        RotateImage solution = new RotateImage();
        
        // Test Case 1: 3x3 matrix
        System.out.println("Test Case 1: 3x3 Matrix");
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Before rotation:");
        solution.printMatrix(matrix1);
        solution.rotate(matrix1);
        System.out.println("After rotation:");
        solution.printMatrix(matrix1);
        System.out.println();
        
        // Test Case 2: 4x4 matrix
        System.out.println("Test Case 2: 4x4 Matrix");
        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        System.out.println("Before rotation:");
        solution.printMatrix(matrix2);
        solution.rotate(matrix2);
        System.out.println("After rotation:");
        solution.printMatrix(matrix2);
        System.out.println();
        
        // Test Case 3: 2x2 matrix
        System.out.println("Test Case 3: 2x2 Matrix");
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Before rotation:");
        solution.printMatrix(matrix3);
        solution.rotate(matrix3);
        System.out.println("After rotation:");
        solution.printMatrix(matrix3);
        System.out.println();
        
        // Test Case 4: 1x1 matrix
        System.out.println("Test Case 4: 1x1 Matrix");
        int[][] matrix4 = {
            {1}
        };
        System.out.println("Before rotation:");
        solution.printMatrix(matrix4);
        solution.rotate(matrix4);
        System.out.println("After rotation:");
        solution.printMatrix(matrix4);
    }

}
