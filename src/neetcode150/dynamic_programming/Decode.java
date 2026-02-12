package neetcode150.dynamic_programming;
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

Time Complexity: O(n)

We loop through the string once (from index 2 to n)
At each step, we do constant work (check 1 digit and 2 digits)
Therefore: O(n) where n is the length of the string

Space Complexity: O(n)

We use a dp array of size n + 1
Therefore: O(n)

Note: This can be optimized to O(1) space by keeping only the last 2 values instead of the entire array, since we only need dp[i-1] and dp[i-2] at any time.

Basically create a DP and fill it in with i is no of ways it can be decode upto poistion i.

 */
public class Decode {

	public int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0') {
			return 0;
		}
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i <= n; i++) {
			int oneDigit = Integer.parseInt(s.substring(i-1, i));
			if(oneDigit > 0 && oneDigit <= 9) {
				dp[i] += dp[i-1];
			}
			int twoDigit = Integer.parseInt(s.substring(i-2, i));
			if(twoDigit >= 10 && twoDigit <= 26) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[n];
	}
	
	 public static void main(String[] args) {
		 Decode solution = new Decode();
	        
	        // Test Case 1
	        String input1 = "12";
	        System.out.println("Input: \"" + input1 + "\"");
	        System.out.println("Output: " + solution.numDecodings(input1));
	        System.out.println();
	        
	        // Test Case 2
	        String input2 = "226";
	        System.out.println("Input: \"" + input2 + "\"");
	        System.out.println("Output: " + solution.numDecodings(input2));
	        System.out.println();
	        
	        // Test Case 3
	        String input3 = "06";
	        System.out.println("Input: \"" + input3 + "\"");
	        System.out.println("Output: " + solution.numDecodings(input3));
	        System.out.println();
	        
	        // Test Case 4
	        String input4 = "11106";
	        System.out.println("Input: \"" + input4 + "\"");
	        System.out.println("Output: " + solution.numDecodings(input4));
	        System.out.println();
	        
	        // Test Case 5
	        String input5 = "10";
	        System.out.println("Input: \"" + input5 + "\"");
	        System.out.println("Output: " + solution.numDecodings(input5));
	    }
}
