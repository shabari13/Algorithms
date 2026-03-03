package neetcode150.dynamic_programming_2D;
/*
 * Given two strings text1 and text2, return the length of the longest common subsequence between the two strings if one exists, otherwise return 0.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.

For example, "cat" is a subsequence of "crabt".
A common subsequence of two strings is a subsequence that exists in both strings.

Example 1:

Input: text1 = "cat", text2 = "crabt" 

Output: 3 
Explanation: The longest common subsequence is "cat" which has a length of 3.

Example 2:

Input: text1 = "abcd", text2 = "abcd"

Output: 4
Example 3:

Input: text1 = "abcd", text2 = "efgh"

Output: 0
Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.

⏱ Time and Space Complexity
Time Complexity
O(m × n)

Because:

We iterate through all characters of text1

For each, we iterate through all characters of text2

Space Complexity
O(m × n)

Because:

We use a 2D DP array.

Basically we look into creating a dynamic array with 1 extra row and column. First row and column is 0 values.
Also we start iterating rom 1 for both i and j and thn we look for letters matching at i-1 and j-1
then we fill dp with values . If characters match then we increment dp[i[j]with 1+dp[i-1][j-1]. Otherwise we drop character  from both the strings and
we chech the max length with dp[i][j] = dp[i-1][j],dp[i][j-1]
 */
public class LongestCommonSubsequence {
	public static int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		
		int[][] dp = new int[m+1][n+1];
		for(int i = 1; i<=m ; i++) {
			for(int j = 1; j <= n; j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = 1 + dp[i - 1][j-1];
					
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
			
		}
		return dp[m][n];
		
	}
	public static void main(String[] args) {

        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Input: " + text1 + ", " + text2);
        System.out.println("LCS Length: " + longestCommonSubsequence(text1, text2));

        String text3 = "abc";
        String text4 = "abc";
        System.out.println("\nInput: " + text3 + ", " + text4);
        System.out.println("LCS Length: " + longestCommonSubsequence(text3, text4));

        String text5 = "abc";
        String text6 = "def";
        System.out.println("\nInput: " + text5 + ", " + text6);
        System.out.println("LCS Length: " + longestCommonSubsequence(text5, text6));
    }
}
