package neetcode250.arrays_hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.

Example 1:



Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","8",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: true
Example 2:

Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","1",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: false
Explanation: There are two 1's in the top-left 3x3 sub-box.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.

The key insight is that a Sudoku board is valid if — and only if — no digit appears more than once in any row, any column, or any 3×3 box.
 Instead of checking each group separately in three passes, we do it all in a single pass over the 81 cells. We maintain 27 HashSets (9 for rows + 9 for columns + 9 for boxes).
  As we visit each filled cell, we try to add its digit to the relevant row-set, col-set, and box-set. A HashSet.add() returns false when the element already exists — 
  that's our duplicate detector.
 The moment any add fails, we return false. If we finish all 81 cells without a failure, the board is valid.

ComplexityExplanationTimeO(1)The board is always 9×9 = 81 cells. 
We visit each cell exactly once. 
The number of operations is fixed regardless of input — it never grows.SpaceO(1)We store at most 9 digits per set × 27 sets = 243 entries maximum,
 which is a fixed constant. No recursion stack is used.
 */

public class ValidSudoku {
	public static boolean isValidSudoku(char[][] matrix) {
		Set<Character>[] rows = new HashSet[9];
		Set<Character>[] cols = new HashSet[9];
		Set<Character>[] boxes = new HashSet[9];
		
		for(int i = 0; i < 9 ; i++) {
			rows[i] = new HashSet<>();
			cols[i] = new HashSet<>();
			boxes[i] = new HashSet<>();
		}
		
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = matrix[i][j];
				if(c == '.') {
					continue;
				}
				if(!rows[i].add(c)) {
					return false;
				}
				if(!cols[j].add(c)) {
					return false;
				}
				int boxIndex = i / 3 * 3 + j / 3;
				if(!boxes[boxIndex].add(c)) {
					return false;
				}
			}
		}
		return true;
	}
	
	   static void printBoard(char[][] board) {
	        System.out.println("  ┌───────┬───────┬───────┐");
	        for (int r = 0; r < 9; r++) {
	            if (r == 3 || r == 6)
	                System.out.println("  ├───────┼───────┼───────┤");
	            System.out.print("  │");
	            for (int c = 0; c < 9; c++) {
	                System.out.print(" " + board[r][c]);
	                if (c == 2 || c == 5) System.out.print(" │");
	            }
	            System.out.println(" │");
	        }
	        System.out.println("  └───────┴───────┴───────┘");
	    }

	    // ─────────────────────────────────────────────
	    //  MAIN  — three test cases
	    // ─────────────────────────────────────────────
	    public static void main(String[] args) {

	        // ═══════════════════════════════════════════
	        // TEST CASE 1 — Classic valid board
	        // (the standard LeetCode example)
	        // ═══════════════════════════════════════════
	        char[][] board1 = {
	            {'5','3','.','.','7','.','.','.','.'},
	            {'6','.','.','1','9','5','.','.','.'},
	            {'.','9','8','.','.','.','.','6','.'},
	            {'8','.','.','.','6','.','.','.','3'},
	            {'4','.','.','8','.','3','.','.','1'},
	            {'7','.','.','.','2','.','.','.','6'},
	            {'.','6','.','.','.','.','2','8','.'},
	            {'.','.','.','4','1','9','.','.','5'},
	            {'.','.','.','.','8','.','.','7','9'}
	        };

	        System.out.println("╔══════════════════════════════════╗");
	        System.out.println("║  TEST 1 — Expected: VALID        ║");
	        System.out.println("╚══════════════════════════════════╝");
	        printBoard(board1);
	        boolean result1 = isValidSudoku(board1);
	        System.out.println("  Result → " + (result1 ? "✓ VALID" : "✗ INVALID") + "\n");


	        // ═══════════════════════════════════════════
	        // TEST CASE 2 — Invalid: duplicate '8' in row 0
	        // Row 0 contains '8' at column 0 AND column 3
	        // ═══════════════════════════════════════════
	        char[][] board2 = {
	            {'8','3','.','.','7','.','.','.','8'}, // two 8s in the same row!
	            {'6','.','.','1','9','5','.','.','.'},
	            {'.','9','8','.','.','.','.','6','.'},
	            {'8','.','.','.','6','.','.','.','3'},
	            {'4','.','.','8','.','3','.','.','1'},
	            {'7','.','.','.','2','.','.','.','6'},
	            {'.','6','.','.','.','.','2','8','.'},
	            {'.','.','.','4','1','9','.','.','5'},
	            {'.','.','.','.','8','.','.','7','9'}
	        };

	        System.out.println("╔══════════════════════════════════╗");
	        System.out.println("║  TEST 2 — Expected: INVALID      ║");
	        System.out.println("║  Reason: two 8s in row 0         ║");
	        System.out.println("╚══════════════════════════════════╝");
	        printBoard(board2);
	        boolean result2 = isValidSudoku(board2);
	        System.out.println("  Result → " + (result2 ? "✓ VALID" : "✗ INVALID") + "\n");


	        // ═══════════════════════════════════════════
	        // TEST CASE 3 — Invalid: duplicate '1' in top-left box
	        // The 3x3 box (rows 0-2, cols 0-2) has '1' at (0,0) and (1,1)
	        // ═══════════════════════════════════════════
	        char[][] board3 = {
	            {'1','.','.','.','.','.','.','.','.'},
	            {'.','1','.','.','.','.','.','.','.'},   // second '1' in same box!
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.' },
	            {'.','.','.','.','.','.','.','.','.'}
	        };

	        System.out.println("╔══════════════════════════════════╗");
	        System.out.println("║  TEST 3 — Expected: INVALID      ║");
	        System.out.println("║  Reason: two 1s in top-left box  ║");
	        System.out.println("╚══════════════════════════════════╝");
	        printBoard(board3);
	        boolean result3 = isValidSudoku(board3);
	        System.out.println("  Result → " + (result3 ? "✓ VALID" : "✗ INVALID") + "\n");


	        // ═══════════════════════════════════════════
	        // SUMMARY
	        // ═══════════════════════════════════════════
	        System.out.println("╔══════════════════════════════════╗");
	        System.out.println("║  SUMMARY                         ║");
	        System.out.println("╠══════════════════════════════════╣");
	        System.out.printf( "║  Test 1: %-24s ║%n", result1 ? "✓ VALID"   : "✗ INVALID");
	        System.out.printf( "║  Test 2: %-24s ║%n", result2 ? "✓ VALID"   : "✗ INVALID");
	        System.out.printf( "║  Test 3: %-24s ║%n", result3 ? "✓ VALID"   : "✗ INVALID");
	        System.out.println("╚══════════════════════════════════╝");
	    }

}
