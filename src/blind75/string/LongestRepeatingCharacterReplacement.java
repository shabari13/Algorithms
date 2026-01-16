package blind75.string;
/*
 * 
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
 

Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 * 
 * 
 * The Big Idea:
We use a sliding window (like a magnifying glass) that moves across the string. We try to find the longest section where we can make all characters the same by changing at most k characters.
Step-by-Step Breakdown
Variables We Use:

count[26]: An array that counts how many times each letter (A-Z) appears in our current window
left: The start of our window
right: The end of our window
maxCount: The count of the most frequent character in our current window
maxLength: The answer - the longest valid window we've found
The Key Insight
Think of it this way: You have a window (a substring) and you want to make ALL characters in that window the same by changing some of them.
The Question: How many characters do we need to change?
The Answer: All the characters EXCEPT the most frequent one!

 * Time and Space Complexity
Time Complexity: O(n)

We visit each character at most twice (once with right pointer, once with left pointer)
n is the length of the string
The while loop doesn't create nested iterations because left only moves forward

Space Complexity: O(1)

We use a fixed-size array of 26 elements (for A-Z)
The space doesn't grow with input size
Therefore, it's constant space
 */
public class LongestRepeatingCharacterReplacement {
	
	public int characterReplacement(String s, int k) {
		
		int maxCount = 0;
		int maxLength = 0;
		int[] count = new int[26];
		int left = 0;
		for(int right = 0; right < s.length(); right++) {
			count[s.charAt(right) - 'A']++;
			maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);
			
			while(right - left + 1 - maxCount > k) {
				count[s.charAt(left) - 'A']--;
				left++;
			}
			maxLength = Math.max(maxLength, right - left +1);
			
		}
		return maxLength;
		
	}
	public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
        
        // Test case 1
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + solution.characterReplacement(s1, k1));
        System.out.println();
        
        // Test case 2
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + solution.characterReplacement(s2, k2));
        System.out.println();
        
        // Test case 3
        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + solution.characterReplacement(s3, k3));
        System.out.println();
        
        // Test case 4
        String s4 = "ABBB";
        int k4 = 2;
        System.out.println("Input: s = \"" + s4 + "\", k = " + k4);
        System.out.println("Output: " + solution.characterReplacement(s4, k4));
        System.out.println();
        
        // Test case 5
        String s5 = "AABCABBB";
        int k5 = 2;
        System.out.println("Input: s = \"" + s5 + "\", k = " + k5);
        System.out.println("Output: " + solution.characterReplacement(s5, k5));
    }

}
