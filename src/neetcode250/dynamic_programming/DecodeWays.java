package neetcode250.dynamic_programming;
/*
 * A string consisting of uppercase english characters can be encoded to a number using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode a message, digits must be grouped and then mapped back into letters using the reverse of the mapping above. There may be multiple ways to decode a message. For example, "1012" can be mapped into:

"JAB" with the grouping (10 1 2)
"JL" with the grouping (10 12)
The grouping (1 01 2) is invalid because 01 cannot be mapped into a letter since it contains a leading zero.

Given a string s containing only digits, return the number of ways to decode it. You can assume that the answer fits in a 32-bit integer.

Example 1:

Input: s = "12"

Output: 2

Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "01"

Output: 0
Explanation: "01" cannot be decoded because "01" cannot be mapped into a letter.

Constraints:

1 <= s.length <= 100
s consists of digits

The core idea: Given a string of digits, count how many ways it can be decoded using the mapping A=1, B=2, ..., Z=26. The key insight is that at each position, you have at most two choices: decode the current digit alone (single digit), or decode it together with the previous digit (two digits). This is a classic dynamic programming problem where each state depends on the previous two states — very similar to climbing stairs.

Time Complexity: O(n)

We loop through the string once (from index 2 to n)
At each step, we do constant work (check 1 digit and 2 digits)
Therefore: O(n) where n is the length of the string

Space Complexity: O(n)

We use a dp array of size n + 1
Therefore: O(n)
 */
public class DecodeWays {
	public static int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0')
			return 0;
		int n = s.length();
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '0' ? 0 : 1;
		for(int i = 2; i <= n; i++) {
			int oneDigit = Integer.parseInt(s.substring(i-1, i));
			if(oneDigit > 0 && oneDigit < 10) {
				dp[i] += dp[i-1];
			}
			int twoDigit = Integer.parseInt(s.substring(i-2, i));
			if(twoDigit >=10 && twoDigit <= 26) {
				dp[i] += dp[i-2];
			}
		}
		return dp[n];
	}
	
	   public static void main(String[] args) {
	        String[] inputs = {"12", "226", "06", "10", "11106", "2611055971756562"};

	        for (String input : inputs) {
	            System.out.println("Input: \"" + input + "\" → Ways: " + numDecodings(input));
	        }
	    }
}
