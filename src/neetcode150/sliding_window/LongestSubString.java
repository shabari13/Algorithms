package neetcode150.sliding_window;

import java.util.HashMap;
import java.util.Map;
/*
 * 
 * Given a string s, find the length of the longest substring without duplicate characters.

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: s = "zxyzxyz"

Output: 3
Explanation: The string "xyz" is the longest without duplicate characters.

Example 2:

Input: s = "xxxx"

Output: 1
Constraints:

0 <= s.length <= 1000
s may consist of printable ASCII characters.

Time Complexity: O(n) ⏱️

We visit each character exactly once with the right pointer (goes from 0 to n-1)
The left pointer only moves forward and never goes back, so it also moves at most n times
HashMap operations (get, put, containsKey) all take O(1) on average
Total: O(n) where n is the length of the string

Space Complexity: O(min(n, m)) 💾

We store characters in a HashMap
In the worst case, we might store all unique characters
If the string has n characters and the character set size is m (like 26 for lowercase English letters, or 128 for ASCII)
We'll store at most min(n, m) entries
For example: if input is all unique letters → O(n)
If input has limited character set → O(m)

Simplified: Usually O(1) for fixed character sets (like English alphabet), or O(n) for variable character sets!
 */
public class LongestSubString {

	public static int longestSubStringWithoutRepeatingCharacters(String s) {
		
		Map<Character, Integer> map = new HashMap<>();
		int left = 0;
		int maxLength = 0;
		for(int right = 0; right < s.length(); right++) {
			char currentChar = s.charAt(right);
			if(map.containsKey(currentChar) && map.get(currentChar) >= left) {
				left  = map.get(currentChar) + 1;
			}
			map.put(currentChar, right);
			maxLength = Math.max(maxLength,  (right - left + 1) );
		}
		
		return maxLength;
				
	}
	  public static void main(String[] args) {
	        // Test cases
	        String[] testCases = {
	            "abcabcbb",
	            "bbbbb",
	            "pwwkew",
	            "",
	            "au",
	            "dvdf",
	            "abcdefg"
	        };
	        
	        System.out.println("=== Longest Substring Without Repeating Characters ===\n");
	        
	        for (String test : testCases) {
	            int result = longestSubStringWithoutRepeatingCharacters(test);
	            System.out.println("Input: \"" + test + "\"");
	            System.out.println("Output: " + result);
	            System.out.println("---");
	        }
	    }
}
