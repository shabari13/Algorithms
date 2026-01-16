package blind75.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 
**The Strategy:**
Think of it like peeling an onion, layer by layer! We start from the outside and work our way to the center.

## Step-by-Step Walkthrough

Let's use this 3x3 matrix as an example:
```
1  2  3
4  5  6
7  8  9
```

**Step 1: Set up our boundaries**
We create 4 invisible lines that tell us where we can walk:
- `top = 0` (top boundary starts at row 0)
- `bottom = 2` (bottom boundary at row 2)
- `left = 0` (left boundary at column 0)
- `right = 2` (right boundary at column 2)

Think of these as ropes marking where you can walk!

---

### **Iteration 1: The First Layer (Outside)**

**Move 1: Go RIGHT along the top**
- We walk from left to right along the top row
- Start at position (0,0) and go to (0,2)
- We collect: **1, 2, 3**
- Now we're done with the top row, so move the top rope down: `top = 1`

**Move 2: Go DOWN along the right side**
- We walk down the right column
- Start at position (1,2) and go to (2,2)
- We collect: **6, 9**
- Now we're done with the right column, so move the right rope left: `right = 1`

**Move 3: Go LEFT along the bottom**
- We check: is there still a bottom row to walk? Yes! (top=1 <= bottom=2)
- We walk from right to left along the bottom row
- Start at position (2,1) and go to (2,0)
- We collect: **8, 7**
- Now we're done with the bottom row, so move the bottom rope up: `bottom = 1`

**Move 4: Go UP along the left side**
- We check: is there still a left column to walk? Yes! (left=0 <= right=1)
- We walk up the left column
- Start at position (1,0) and go to (1,0)
- We collect: **4**
- Now we're done with the left column, so move the left rope right: `left = 1`

After Iteration 1, we've collected: **[1, 2, 3, 6, 9, 8, 7, 4]**

---

### **Iteration 2: The Second Layer (Center)**

Now our boundaries are:
- `top = 1, bottom = 1, left = 1, right = 1`

**Move 1: Go RIGHT along the top**
- Walk from (1,1) to (1,1)
- We collect: **5**
- Move top rope down: `top = 2`

**Move 2: Go DOWN along the right side**
- Now `top = 2` and `bottom = 1`, so `top > bottom` - there's nothing to walk!
- Skip this

**Move 3 & 4:** Similar checks show there's nothing left

---

**Final result:** [1, 2, 3, 6, 9, 8, 7, 4, 5] ✨

## Another Example: 3x4 Matrix
```
1   2   3   4
5   6   7   8
9   10  11  12
Iteration 1:

RIGHT: 1, 2, 3, 4
DOWN: 8, 12
LEFT: 11, 10, 9
UP: 5

Iteration 2:

RIGHT: 6, 7

Result: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
Why the Extra Checks?
Notice we check if (top <= bottom) before moving left and if (left <= right) before moving up. This is important!
Imagine a single row: [1, 2, 3, 4]

After going RIGHT and DOWN (which does nothing), top > bottom
If we don't check, we'd try to go LEFT again and collect the same numbers twice!

Complexity Analysis
Time Complexity: O(m × n)

m = number of rows, n = number of columns
We visit each element exactly once
Like counting every sticker exactly one time

Space Complexity: O(1)

We only use a fixed number of variables (top, bottom, left, right)
The result list doesn't count as extra space (it's the output we need to return)
It's like using only your hands to collect stickers, no extra bags needed!
Claude is AI and can make mistakes. Please double-check responses. Sonnet 4.5
 * 
 */

public class SpiralMatrix {
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		
		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;
		
		while(left <= right && top <= bottom) {
			for(int i = left; i <=right; i++) {
				result.add(matrix[top][i]);
			}
			top++;
			for(int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			right--;
			if(top <= bottom) {
				for(int i = right; i >= left; i--) {
					result.add(matrix[bottom][i]);
				}
				bottom--;
			}
			
			if(right >= left) {
				for(int i = bottom; i>= top; i--) {
					result.add(matrix[i][left]);
				}
				left++;
			}
			
		}
		
		
		
		return result;
		
	}
	
	public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();
        
        // Test case 1: 3x3 matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Input Matrix 1:");
        printMatrix(matrix1);
        System.out.println("Spiral Order: " + solution.spiralOrder(matrix1));
        System.out.println();
        
        // Test case 2: 3x4 matrix
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println("Input Matrix 2:");
        printMatrix(matrix2);
        System.out.println("Spiral Order: " + solution.spiralOrder(matrix2));
        System.out.println();
        
        // Test case 3: 1x4 matrix (single row)
        int[][] matrix3 = {
            {1, 2, 3, 4}
        };
        System.out.println("Input Matrix 3:");
        printMatrix(matrix3);
        System.out.println("Spiral Order: " + solution.spiralOrder(matrix3));
        System.out.println();
        
        // Test case 4: 4x1 matrix (single column)
        int[][] matrix4 = {
            {1},
            {2},
            {3},
            {4}
        };
        System.out.println("Input Matrix 4:");
        printMatrix(matrix4);
        System.out.println("Spiral Order: " + solution.spiralOrder(matrix4));
        System.out.println();
        
        // Test case 5: 1x1 matrix
        int[][] matrix5 = {
            {5}
        };
        System.out.println("Input Matrix 5:");
        printMatrix(matrix5);
        System.out.println("Spiral Order: " + solution.spiralOrder(matrix5));
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
