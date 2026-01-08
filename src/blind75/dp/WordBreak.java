package blind75.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.


The core trick in this solution is dynamic programming with substring validation.
 Instead of trying every possible way to break the string (which would be exponential),
  we build up our answer step-by-step by remembering what we've already solved. For each position
   i in the string, we check all possible "split points" j before it, asking: "If I could successfully
    break everything up to position j (stored in dp[j]), AND the substring from j to i is a valid 
    dictionary word, then I can successfully break everything up to position i." This transforms
     an exponential problem into a polynomial one because we're reusing previous results instead of 
     recalculating them. The dp[i] essentially means "Can I build the first i characters using 
     dictionary words?" and we fill this array left-to-right, where each new answer depends only 
     on previously computed answers. It's like climbing stairs where you remember which steps
      you've already reached, so you don't have to climb from the ground every single time!


 */
public class WordBreak {

	public boolean wordBreak(String s, List<String> wordDict) {

		Set<String> wordSet = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {

				if (dp[j] && wordSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}

		}

		return dp[s.length()];
	}

	public static void main(String[] args) {
		WordBreak solution = new WordBreak();

		// Test Case 1
		String s1 = "leetcode";
		List<String> wordDict1 = Arrays.asList("leet", "code");
		System.out.println("Input: s = \"" + s1 + "\", wordDict = " + wordDict1);
		System.out.println("Output: " + solution.wordBreak(s1, wordDict1));
		System.out.println();

		// Test Case 2
		String s2 = "applepenapple";
		List<String> wordDict2 = Arrays.asList("apple", "pen");
		System.out.println("Input: s = \"" + s2 + "\", wordDict = " + wordDict2);
		System.out.println("Output: " + solution.wordBreak(s2, wordDict2));
		System.out.println();

		// Test Case 3
		String s3 = "catsandog";
		List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
		System.out.println("Input: s = \"" + s3 + "\", wordDict = " + wordDict3);
		System.out.println("Output: " + solution.wordBreak(s3, wordDict3));
		System.out.println();

		// Test Case 4 - Empty string
		String s4 = "";
		List<String> wordDict4 = Arrays.asList("a");
		System.out.println("Input: s = \"" + s4 + "\", wordDict = " + wordDict4);
		System.out.println("Output: " + solution.wordBreak(s4, wordDict4));
	}

}
