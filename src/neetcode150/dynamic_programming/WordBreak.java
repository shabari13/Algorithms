package neetcode150.dynamic_programming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of dictionary words.

You are allowed to reuse words in the dictionary an unlimited number of times. You may assume all dictionary words are unique.

Example 1:

Input: s = "neetcode", wordDict = ["neet","code"]

Output: true
Explanation: Return true because "neetcode" can be split into "neet" and "code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen","ape"]

Output: true
Explanation: Return true because "applepenapple" can be split into "apple", "pen" and "apple". Notice that we can reuse words and also not use all the words.

Example 3:

Input: s = "catsincars", wordDict = ["cats","cat","sin","in","car"]

Output: false
Constraints:

1 <= s.length <= 200
1 <= wordDict.length <= 100
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
Time Complexity: O(n² × m) where n = length of string, m = average word length

Outer loop: n iterations
Inner loop: up to n iterations
substring() operation: O(m)
Set lookup: O(m) for string comparison
Overall: O(n² × m)

Optimized version would be O(n²) if we use more efficient substring checking
Space Complexity: O(n + k) where k = total characters in dictionary

DP array: O(n)
HashSet: O(k)
Overall: O(n + k)
 */
public class WordBreak{
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> set = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for(int i = 1; i<= s.length(); i++) {
			for(int j =0; j<i; j++) {
				if(dp[j] && set.contains(s.substring(j, i))) {
					dp[i] = true;
				}
			}
		}
		return dp[s.length()];
	}
	 public static void main(String[] args) {
	        WordBreak solution = new WordBreak();
	        
	        // Test case 1
	        String s1 = "leetcode";
	        List<String> wordDict1 = Arrays.asList("leet", "code");
	        System.out.println("Input: s = \"" + s1 + "\", wordDict = " + wordDict1);
	        System.out.println("Output: " + solution.wordBreak(s1, wordDict1));
	        System.out.println();
	        
	        // Test case 2
	        String s2 = "applepenapple";
	        List<String> wordDict2 = Arrays.asList("apple", "pen");
	        System.out.println("Input: s = \"" + s2 + "\", wordDict = " + wordDict2);
	        System.out.println("Output: " + solution.wordBreak(s2, wordDict2));
	        System.out.println();
	        
	        // Test case 3
	        String s3 = "catsandog";
	        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
	        System.out.println("Input: s = \"" + s3 + "\", wordDict = " + wordDict3);
	        System.out.println("Output: " + solution.wordBreak(s3, wordDict3));
	        System.out.println();
	        
	        // Test case 4
	        String s4 = "cars";
	        List<String> wordDict4 = Arrays.asList("car", "ca", "rs");
	        System.out.println("Input: s = \"" + s4 + "\", wordDict = " + wordDict4);
	        System.out.println("Output: " + solution.wordBreak(s4, wordDict4));
	    }
}
