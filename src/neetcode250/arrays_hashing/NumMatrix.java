package neetcode250.arrays_hashing;
/*
 * You are given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.
Example 1:

Input: ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]

Output: [null, 8, 11, 12]
Explanation:
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

Constraints:


 * 
 * TimeSpaceConstructor (building prefix table)O(m × n)O(m × n) for the tablesumRegion (each query)O(1)O(1) extra
 */
public class NumMatrix {
	
	int[][] prefix;
	
	public NumMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		prefix = new int[rows+1][cols+1];
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= cols; j++) {
				prefix[i][j] = matrix[i - 1][j - 1]
								+ prefix[i][j-1]
								+ prefix[i-1][j]
								- prefix[i-1][j-1];
			}
		}
		
	}
	
	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = prefix[row2 + 1][col2 + 1]
				- prefix[row1][col2+1]
				- prefix[row2+1][col1]
				+ prefix[row1][col1];
		return sum;
	}
	
	 private static void printMatrix(int[][] m, String label) {
	        System.out.println("\n" + label);
	        for (int[] row : m) {
	            System.out.print("  [ ");
	            for (int v : row) System.out.printf("%4d ", v);
	            System.out.println("]");
	        }
	    }

	    // -------------------------------------------------------
	    // main – drives several distinct test scenarios
	    // -------------------------------------------------------
	    public static void main(String[] args) {

	        // ===================================================
	        // SCENARIO 1  –  the classic LeetCode example
	        // ===================================================
	        System.out.println("===========================================");
	        System.out.println(" SCENARIO 1 : LeetCode example (5x5)");
	        System.out.println("===========================================");

	        int[][] grid1 = {
	            {3,  0,  1,  4,  2},
	            {5,  6,  3,  2,  1},
	            {1,  2,  0,  1,  5},
	            {4,  1,  0,  1,  7},
	            {1,  0,  3,  0,  5}
	        };
	        printMatrix(grid1, "Matrix:");

	        NumMatrix nm1 = new NumMatrix(grid1);

	        // LeetCode test cases
	        query(nm1, 2, 1, 4, 3, "sumRegion(2,1,4,3) → expected 8");
	        query(nm1, 1, 1, 2, 2, "sumRegion(1,1,2,2) → expected 11");
	        query(nm1, 1, 2, 2, 4, "sumRegion(1,2,2,4) → expected 12");

	        // ===================================================
	        // SCENARIO 2  –  entire matrix as single region
	        // ===================================================
	        System.out.println("\n===========================================");
	        System.out.println(" SCENARIO 2 : Full matrix sum (3x3)");
	        System.out.println("===========================================");

	        int[][] grid2 = {
	            {1, 2, 3},
	            {4, 5, 6},
	            {7, 8, 9}
	        };
	        printMatrix(grid2, "Matrix:");
	        NumMatrix nm2 = new NumMatrix(grid2);

	        query(nm2, 0, 0, 2, 2, "sumRegion(0,0,2,2) → expected 45 (all)");
	        query(nm2, 0, 0, 0, 0, "sumRegion(0,0,0,0) → expected 1  (single cell top-left)");
	        query(nm2, 2, 2, 2, 2, "sumRegion(2,2,2,2) → expected 9  (single cell bottom-right)");
	        query(nm2, 0, 0, 1, 1, "sumRegion(0,0,1,1) → expected 12 (top-left 2x2)");
	        query(nm2, 1, 1, 2, 2, "sumRegion(1,1,2,2) → expected 28 (bottom-right 2x2)");

	        // ===================================================
	        // SCENARIO 3  –  matrix with negative numbers
	        // ===================================================
	        System.out.println("\n===========================================");
	        System.out.println(" SCENARIO 3 : Negative numbers (2x4)");
	        System.out.println("===========================================");

	        int[][] grid3 = {
	            { -3,  2, -1,  4},
	            {  5, -6,  3, -2}
	        };
	        printMatrix(grid3, "Matrix:");
	        NumMatrix nm3 = new NumMatrix(grid3);

	        query(nm3, 0, 0, 1, 3, "sumRegion(0,0,1,3) → expected 2  (entire)");
	        query(nm3, 0, 1, 0, 2, "sumRegion(0,1,0,2) → expected 1  (top row cols 1-2)");
	        query(nm3, 1, 1, 1, 2, "sumRegion(1,1,1,2) → expected -3 (bottom row cols 1-2)");

	        // ===================================================
	        // SCENARIO 4  –  single-row matrix
	        // ===================================================
	        System.out.println("\n===========================================");
	        System.out.println(" SCENARIO 4 : Single-row matrix (1x5)");
	        System.out.println("===========================================");

	        int[][] grid4 = {{10, 20, 30, 40, 50}};
	        printMatrix(grid4, "Matrix:");
	        NumMatrix nm4 = new NumMatrix(grid4);

	        query(nm4, 0, 0, 0, 4, "sumRegion(0,0,0,4) → expected 150 (all)");
	        query(nm4, 0, 1, 0, 3, "sumRegion(0,1,0,3) → expected 90  (middle three)");
	        query(nm4, 0, 4, 0, 4, "sumRegion(0,4,0,4) → expected 50  (last cell)");

	        // ===================================================
	        // SCENARIO 5  –  all zeros
	        // ===================================================
	        System.out.println("\n===========================================");
	        System.out.println(" SCENARIO 5 : All-zero matrix (2x2)");
	        System.out.println("===========================================");

	        int[][] grid5 = {{0, 0}, {0, 0}};
	        printMatrix(grid5, "Matrix:");
	        NumMatrix nm5 = new NumMatrix(grid5);
	        query(nm5, 0, 0, 1, 1, "sumRegion(0,0,1,1) → expected 0");
	    }

	    // -------------------------------------------------------
	    // Helper to run + print a single query neatly
	    // -------------------------------------------------------
	    private static void query(NumMatrix nm,
	                               int r1, int c1, int r2, int c2,
	                               String description) {
	        int result = nm.sumRegion(r1, c1, r2, c2);
	        System.out.printf("  %-45s  got: %d%n", description, result);
	    }

}
