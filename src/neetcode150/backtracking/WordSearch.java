package neetcode150.backtracking;

/*
 * Given a 2-D grid of characters board and a string word, return true if the word is present in the grid, otherwise return false.

For the word to be present it must be possible to form it with a path in the board with horizontally or vertically neighboring cells. The same cell may not be used more than once in a word.

Example 1:



Input: 
board = [
  ["A","B","C","D"],
  ["S","A","A","T"],
  ["A","C","A","E"]
],
word = "CAT"

Output: true
Example 2:



Input: 
board = [
  ["A","B","C","D"],
  ["S","A","A","T"],
  ["A","C","A","E"]
],
word = "BAT"

Output: false

TC (O(m * n * 4^L)
TC : O(L)

AnalysisTimeO(M × N × 4^L) where M×N = grid cells, L = word length. Each DFS can branch 4 ways up to L levels deep.
 In practice it's much faster due to early pruning.SpaceO(L) 
for the recursion call stack, where L is the word length (max depth of DFS). 
No extra data structures used — we reuse the board itself for the visited marker.
Basically we use backtracking nad dfs for this.
we go through each character and check if it si there. then we check for row + 1, row -1 , col + 1 and col -1 and see
if there are characters present from the word in each index.
 */
public class WordSearch {
	
	public static boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(dfs(board, word, i, j, 0))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean dfs(char[][] board, String word, int row, int col, int index) {
		int m = board.length - 1;
		int n = board[0].length - 1;
		if(index == word.length())
			return true;
		if(row < 0 || col < 0 || row > m || col > n || board[row][col] != word.charAt(index))
			return false;
		char temp = board[row][col];
		board[row][col] = '#';
		boolean found = (dfs(board, word, row - 1, col, index+1)
						|| dfs(board, word,row + 1, col, index + 1)
						|| dfs(board, word,row, col - 1, index+1)
						|| dfs(board, word,row, col + 1, index + 1));
		board[row][col] = temp;
		return found;
	}
	  public static void main(String[] args) {

	        char[][] board1 = {
	                {'A','B','C','E'},
	                {'S','F','C','S'},
	                {'A','D','E','E'}
	        };

	        String word1 = "ABCCED";
	        String word2 = "SEE";
	        String word3 = "ABCB";

	        System.out.println("Word: " + word1 + " -> " + exist(board1, word1));
	        System.out.println("Word: " + word2 + " -> " + exist(board1, word2));
	        System.out.println("Word: " + word3 + " -> " + exist(board1, word3));

	        char[][] board2 = {
	                {'A','A','A','A'},
	                {'A','A','A','A'},
	                {'A','A','A','A'}
	        };

	        String word4 = "AAAA";
	        System.out.println("Word: " + word4 + " -> " + exist(board2, word4));
	    }
}
