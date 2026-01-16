package blind75.matrix;
/*
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?
Time Complexity
O(M × N × 4^L) where:

M = number of rows
N = number of columns
L = length of the word
4^L because at each step we try 4 directions, and we do this L times

In simple terms: If the board is bigger or the word is longer, it takes more time. For each letter in the word, we might try 4 different paths.
Space Complexity
O(L) where L = length of the word
This is the maximum depth of our recursion (the function calling itself). We need space to remember where we are in our search path. It's like leaving breadcrumbs - we need one breadcrumb for each letter we're searching for.
The board modification (marking with '#') happens in-place, so it doesn't count as extra space.
 
	
 * 
 * 
 * 
 */
public class WordSearch {
	
	public boolean exist(char[][] board, String word) {
		
		for(int i = 0 ; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(dfs(board, word, i, j, 0)) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	public boolean dfs(char[][] board, String word, int row, int col, int index) {
		if(index == word.length())
			return true;
		
		if(row < 0 || row >= board.length ||
			col < 0 || col >= board[0].length || 
			board[row][col] != word.charAt(index)) {
			return false;
		}
		
		char temp = board[row][col];
		board[row][col] = '#';
		
		boolean found = dfs(board, word, row+1, col, index + 1) ||
				dfs(board, word, row-1, col, index + 1) ||
				dfs(board, word, row, col+1, index + 1) ||
				dfs(board, word, row, col-1, index + 1);
		
		board[row][col] = temp;
		return found;
	}

	public static void main(String[] args) {
        WordSearch solution = new WordSearch();
        
        // Test Case 1
        char[][] board1 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        System.out.println("Test Case 1:");
        System.out.println("Board:");
        printBoard(board1);
        System.out.println("Word: " + word1);
        System.out.println("Result: " + solution.exist(board1, word1));
        System.out.println();
        
        // Test Case 2
        char[][] board2 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word2 = "SEE";
        System.out.println("Test Case 2:");
        System.out.println("Board:");
        printBoard(board2);
        System.out.println("Word: " + word2);
        System.out.println("Result: " + solution.exist(board2, word2));
        System.out.println();
        
        // Test Case 3
        char[][] board3 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word3 = "ABCB";
        System.out.println("Test Case 3:");
        System.out.println("Board:");
        printBoard(board3);
        System.out.println("Word: " + word3);
        System.out.println("Result: " + solution.exist(board3, word3));
        System.out.println();
        
        // Test Case 4
        char[][] board4 = {
            {'C', 'A', 'A'},
            {'A', 'A', 'A'},
            {'B', 'C', 'D'}
        };
        String word4 = "AAB";
        System.out.println("Test Case 4:");
        System.out.println("Board:");
        printBoard(board4);
        System.out.println("Word: " + word4);
        System.out.println("Result: " + solution.exist(board4, word4));
        System.out.println();
        
        // Test Case 5 - Word not found
        char[][] board5 = {
            {'A', 'B'},
            {'C', 'D'}
        };
        String word5 = "ABCD";
        System.out.println("Test Case 5:");
        System.out.println("Board:");
        printBoard(board5);
        System.out.println("Word: " + word5);
        System.out.println("Result: " + solution.exist(board5, word5));
    }
    
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
