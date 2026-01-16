package blind75.string;

import java.util.HashMap;
import java.util.Map;
/*Given a string s, find the length of the longest substring without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letter

s, digits, symbols and spaces.

Time and Space Complexity
Time Complexity: O(n)

We visit each character exactly once with the right pointer
Even though left pointer moves, each character is visited at most twice (once by right, once by left)
So it's linear time relative to the string length

Space Complexity: O(min(n, m))

Where n is the length of the string and m is the character set size
We store at most all unique characters in the HashMap
For ASCII, this is O(128), for extended ASCII O(256)
In the worst case (all unique characters), it's O(n)

The Big Idea:
We use a "sliding window" - think of it like a magic magnifying glass that can stretch and shrink. We move it across the beads, and whenever we see a duplicate bead inside our magnifying glass, we shrink it from the left side!
Our Tools:

charMap - A notebook where we write down each bead color and where we last saw it
left - Where our magnifying glass starts (left edge)
right - Where our magnifying glass ends (right edge)
maxLength - The longest stretch of unique beads we've found so far

 * 
 * 
 * 
 * 
 */
public class LongestSubstringWithoutRepeatingCharacter {
	
	public static int longestSubstring(String s) {
		
		Map<Character, Integer> charMap = new HashMap<>();
		int maxLength = 0;
		int left = 0;
		for(int right = 0; right < s.length(); right++) {
			char currentChar = s.charAt(right);
			if(charMap.containsKey(currentChar) && charMap.get(currentChar) >= left) {
				
				left = charMap.get(currentChar) + 1;
			}
			
			charMap.put(currentChar, right);
			maxLength = Math.max(maxLength, right - left + 1);
			
		}
		
		return maxLength;
		
	}
	
	public static void main(String[] args) {
        // Test case 1
        String input1 = "abcabcbb";
        System.out.println("Input: \"" + input1 + "\"");
        System.out.println("Output: " + longestSubstring(input1));
        System.out.println();
        
        // Test case 2
        String input2 = "bbbbb";
        System.out.println("Input: \"" + input2 + "\"");
        System.out.println("Output: " + longestSubstring(input2));
        System.out.println();
        
        // Test case 3
        String input3 = "pwwkew";
        System.out.println("Input: \"" + input3 + "\"");
        System.out.println("Output: " + longestSubstring(input3));
        System.out.println();
        
        // Test case 4
        String input4 = "";
        System.out.println("Input: \"" + input4 + "\"");
        System.out.println("Output: " + longestSubstring(input4));
        System.out.println();
        
        // Test case 5
        String input5 = "abcdef";
        System.out.println("Input: \"" + input5 + "\"");
        System.out.println("Output: " + longestSubstring(input5));
    }

}
