package neetcode250.sliding_window;

import java.util.HashSet;
import java.util.Set;

/*
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

Time Complexity

O(n)

Reason:

Each character is added to the set once

Each character is removed once

So total operations ≤ 2n

8. Space Complexity

O(min(n, charset))

In worst case the set stores all characters.

Example:

"abcdef" → set size = 6

Time: O(n) — The right pointer visits each character exactly once. The left pointer only moves forward, never backward, so across the entire run it also moves at most n times. Every HashMap operation (get, put, containsKey) is O(1) average. Total: O(n).
Space: O(min(n, m)) — The HashMap holds at most one entry per unique character in the current window. m is the size of the character set (e.g. 26 for lowercase letters, 128 for ASCII). In the worst case (all unique chars) the map grows to the length of the string, but it's bounded by the alphabet size. So the space is O(min(n, m)).
 */
public class LongestSubstringWORepeatingCharcters {
	
	public static int lengthOfLongestSubstring(String s) {
		
		Set<Character> set = new HashSet<>();
		int left = 0; int right = 0;
		int maxLength = 0;
		while(left <= right && right < s.length()) {
			if(!set.contains(s.charAt(right))) {
				set.add(s.charAt(right));
				maxLength = Math.max(maxLength, right - left+1);
				right++;
			} else {
				set.remove(s.charAt(left));
				left++;
			}
			
		}
        return maxLength;
        /*AI Code
         * 
        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char current = s.charAt(right);

            while (set.contains(current)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(current);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
         */
    }
	
	public static void main(String[] args) {
		System.out.print(lengthOfLongestSubstring("abcabcbb"));
	}

}
