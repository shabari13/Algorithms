package neetcode250.sliding_window;
/*
 * You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k characters of the string and replace them with any other uppercase English character.

After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

Example 1:

Input: s = "XYYX", k = 2

Output: 4
Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

Example 2:

Input: s = "AAABABB", k = 1

Output: 5
Constraints:

1 <= s.length <= 1000
0 <= k <= s.length

The core insight is the sliding window pattern. We maintain a window [left, right] over the string and track how many times
 the most frequent character appears inside that window (maxFreq). To make the entire window a single character, we need to replace all other characters —
  that cost is windowSize - maxFreq. If this cost exceeds k, the window is invalid and we shrink it from the left by one. Crucially, we never shrink the window
   below the best size we've ever seen, which means the window size is monotonically non-decreasing — a classic trick that lets us avoid rechecking smaller windows
and keeps the algorithm at O(n).

Time complexity — O(n). The right pointer visits each character exactly once, and the left pointer only ever moves right (never backwards). So each character is entered and exited the window at most once — total work is O(n) regardless of alphabet size.
Space complexity — O(1). The freq array is always size 26 (fixed alphabet). No matter how long the input string is, the extra memory never grows — it's constant.

 */
public class LongestRepeatingCharacterReplacement {
	public static int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0;
        int result = 0;
        int maxFreq = 0;
        for(int right = 0; right < s.length(); right++) {
        	int rightChar = s.charAt(right) - 'A';
        	freq[rightChar]++;
        	maxFreq = Math.max(maxFreq, freq[rightChar]);
        	int windowSize = right - left + 1;
        	if(windowSize - maxFreq > k) {
        		freq[s.charAt(left) - 'A']--;
        		left++;
        	}
        	result = Math.max(result, right - left + 1);
        }
        return result;
    }
	
	   public static void main(String[] args) {
	        // Test case 1: classic example
	        String s1 = "AABABBA";
	        int k1 = 1;
	        System.out.println("Input: \"" + s1 + "\", k=" + k1);
	        System.out.println("Output: " + characterReplacement(s1, k1));  // 4
	        System.out.println();

	        // Test case 2: all same char — no replacements needed
	        String s2 = "AAAA";
	        int k2 = 2;
	        System.out.println("Input: \"" + s2 + "\", k=" + k2);
	        System.out.println("Output: " + characterReplacement(s2, k2));  // 4
	        System.out.println();

	        // Test case 3: standard LeetCode example
	        String s3 = "ABAB";
	        int k3 = 2;
	        System.out.println("Input: \"" + s3 + "\", k=" + k3);
	        System.out.println("Output: " + characterReplacement(s3, k3));  // 4
	        System.out.println();

	        // Test case 4: k=0 → longest run without any replacement
	        String s4 = "AABCCDE";
	        int k4 = 0;
	        System.out.println("Input: \"" + s4 + "\", k=" + k4);
	        System.out.println("Output: " + characterReplacement(s4, k4));  // 2
	        System.out.println();

	        // Test case 5: single character string
	        String s5 = "A";
	        int k5 = 5;
	        System.out.println("Input: \"" + s5 + "\", k=" + k5);
	        System.out.println("Output: " + characterReplacement(s5, k5));  // 1
	    }
}
