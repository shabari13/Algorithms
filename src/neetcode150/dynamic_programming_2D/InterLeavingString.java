package neetcode150.dynamic_programming_2D;
/*
 * You are given three strings s1, s2, and s3. Return true if s3 is formed by interleaving s1 and s2 together or false otherwise.

Interleaving two strings s and t is done by dividing s and t into n and m substrings respectively, where the following conditions are met

|n - m| <= 1, i.e. the difference between the number of substrings of s and t is at most 1.
s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
Interleaving s and t is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...
You may assume that s1, s2 and s3 consist of lowercase English letters.

Example 1:



Input: s1 = "aaaa", s2 = "bbbb", s3 = "aabbbbaa"

Output: true
Explanation: We can split s1 into ["aa", "aa"], s2 can remain as "bbbb" and s3 is formed by interleaving ["aa", "aa"] and "bbbb".

Example 2:

Input: s1 = "", s2 = "", s3 = ""

Output: true
Example 3:

Input: s1 = "abc", s2 = "xyz", s3 = "abxzcy"

Output: false
Explanation: We can't split s3 into ["ab", "xz", "cy"] as the order of characters is not maintained.

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200

💡 The Idea (for Adults)
We use 2D Dynamic Programming. dp[i][j] = true if s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1]. At each cell, we check if we can reach it from the left (using a char from s2) or from above (using a char from s1).

🐣 Explain Like I'm 5
Imagine you have two LEGO towers (s1 and s2), and you want to build a new tower (s3) by taking bricks from EITHER tower — 
but always in order. We make a grid where each box says "Can I build this much of s3 using this much of s1 and s2?" 
We fill each box by looking at the box above and the box to the left. If either neighbor says YES, 
and the next brick matches, our box also says YES!

ComplexityTimeO(m × n) — visit every cell of the DP table exactly onceSpaceO(m × n) — storing the full DP tableOptimized
 SpaceO(n) — possible by keeping only the previous row
 */
public class InterLeavingString {

	public static boolean isInterleave(String s1, String s2, String s3) {
		int m = s1.length();
		int n = s2.length();
		if(s3.length() != m+n) {
			return false;
		}
		
		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;
		
		for(int i = 1; i <= m; i++) {
			dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
		}
		
		for(int j = 1; j <= n; j++) {
			dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j - 1);
		}
		
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				char s3Char = s3.charAt(i + j -1);
				boolean fromAbove = dp[i - 1][j] && s1.charAt(i - 1) == s3Char;
				boolean fromLeft = dp[i][j-1] && s2.charAt(j - 1) == s3Char;
				dp[i][j] = fromAbove || fromLeft;
			}
		}
		return dp[m][n];
	}
	
	  // ─── Utility to pretty-print ───────────────────────────────────────────────
    static void test(String s1, String s2, String s3) {
        boolean result = isInterleave(s1, s2, s3);
        System.out.println("s1 = \"" + s1 + "\"");
        System.out.println("s2 = \"" + s2 + "\"");
        System.out.println("s3 = \"" + s3 + "\"");
        System.out.println("Result: " + result);
        System.out.println("─────────────────────────────");
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║  INTERLEAVING STRING TESTS   ║");
        System.out.println("╚══════════════════════════════╝\n");

        // Test 1: Classic LeetCode example — TRUE
        // s3 = "aadbbcbcac" can be formed by weaving "aabcc" and "dbbca"
        test("aabcc", "dbbca", "aadbbcbcac");

        // Test 2: LeetCode example — FALSE
        test("aabcc", "dbbca", "aadbbbaccc");

        // Test 3: Both empty strings, s3 also empty — TRUE
        test("", "", "");

        // Test 4: One string is empty — TRUE (s3 must just equal s1)
        test("abc", "", "abc");

        // Test 5: One string is empty — FALSE (s3 != s1)
        test("abc", "", "abx");

        // Test 6: Length mismatch — FALSE
        test("a", "b", "abc");

        // Test 7: Simple interleave — TRUE
        // "abcd" interleaved with "1234" = "a1b2c3d4"
        test("abcd", "1234", "a1b2c3d4");
    }
}
