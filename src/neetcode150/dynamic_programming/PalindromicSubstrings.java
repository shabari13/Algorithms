package neetcode150.dynamic_programming;
/*
 * Given a string s, return the number of substrings within s that are palindromes.

A palindrome is a string that reads the same forward and backward.

Example 1:

Input: s = "abc"

Output: 3
Explanation: "a", "b", "c".

Example 2:

Input: s = "aaa"

Output: 6
Explanation: "a", "a", "a", "aa", "aa", "aaa". Note that different substrings are counted as different palindromes even if the string contents are the same.

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class PalindromicSubstrings {
	private int count;
	
	public int countSubstrings(String s) {
		//int count = 0;
		count = 0;
		
		for(int i = 0; i<s.length(); i++) {
			//count += expandFromCenter(s, i, i);
			 expandFromCenter(s, i, i);
			 expandFromCenter(s, i, i+1);
			//count += expandFromCenter(s, i, i+1);
		}
		return count;
	}
	
	public void expandFromCenter(String s, int left, int right) {
		//int count = 0;
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
		//return count;
	}
	
	public static void main(String[] args) {
        PalindromicSubstrings solution = new PalindromicSubstrings();
        
        // Test case 1
        String test1 = "abc";
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test1));
        System.out.println();
        
        // Test case 2
        String test2 = "aaa";
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test2));
        System.out.println();
        
        // Test case 3
        String test3 = "aba";
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test3));
        System.out.println();
        
        // Test case 4
        String test4 = "racecar";
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test4));
        System.out.println();
        
        // Test case 5
        String test5 = "a";
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test5));
    }

}
