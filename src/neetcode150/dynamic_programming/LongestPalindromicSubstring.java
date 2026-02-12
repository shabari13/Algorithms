package neetcode150.dynamic_programming;
/*
 * Given a string s, return the longest substring of s that is a palindrome.

A palindrome is a string that reads the same forward and backward.

If there are multiple palindromic substrings that have the same length, return any one of them.

Example 1:

Input: s = "ababd"

Output: "bab"
Explanation: Both "aba" and "bab" are valid answers.

Example 2:

Input: s = "abbc"

Output: "bb"
Constraints:

1 <= s.length <= 1000
s contains only digits and English letters.

Time Complexity: O(n²)

We loop through each character once: O(n)
For each character, we potentially expand to check all characters: O(n)
Total: O(n) × O(n) = O(n²)

Space Complexity: O(1)

We only use a few variables (start, end, left, right)
We don't create any additional data structures
The space used doesn't grow with input size

Basically you treat every character as a possible centre point for plaindrome. For even plaindrome it is middle 2 characters, for odd middle is just 1 character. hence
i and i+1. Then you expan from center to find the start and end point of the string. expandfrom center returns right - left - 1 because it will be out of bounds.
 */
public class LongestPalindromicSubstring {
	
	public String longestPalindrome(String s) {
		if(s == null || s.length() == 0)
			return "";
		int start = 0;
		int end = 0;
		for(int  i = 0; i < s.length(); i++) {
			int len1 = expandFromCenter(s, i, i);
			System.out.println("len1 "+len1);
			
			int len2 = expandFromCenter(s, i, i+1);
			System.out.println("len2 "+len2);
			
			int len  = Math.max(len1,  len2);
			if(len > end - start) {
				start = i - (len - 1)/2;
				System.out.println("start "+start);
				end = i + (len/2);
				System.out.println("end "+end);
			}
		}
		return s.substring(start, end + 1);
	}
	public int expandFromCenter(String s, int left, int right) {
		while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}
	
	  public static void main(String[] args) {
	        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
	        
	        // Test case 1
	        String test1 = "babad";
	        System.out.println("Input: " + test1);
	        System.out.println("Output: " + solution.longestPalindrome(test1));
	        System.out.println();
	        
	        // Test case 2
	        String test2 = "cbbd";
	        System.out.println("Input: " + test2);
	        System.out.println("Output: " + solution.longestPalindrome(test2));
	        System.out.println();
	        
	        // Test case 3
	        String test3 = "racecar";
	        System.out.println("Input: " + test3);
	        System.out.println("Output: " + solution.longestPalindrome(test3));
	        System.out.println();
	        
	        // Test case 4
	        String test4 = "a";
	        System.out.println("Input: " + test4);
	        System.out.println("Output: " + solution.longestPalindrome(test4));
	        System.out.println();
	        
	        // Test case 5
	        String test5 = "ac";
	        System.out.println("Input: " + test5);
	        System.out.println("Output: " + solution.longestPalindrome(test5));
	    }
}
