package neetcode250.dynamic_programming;
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

💡 Core Idea (Simple Explanation)

We treat every character as the center and try to expand outward.

Because:

A palindrome mirrors around its center.

There are 2 types of centers:

Single character center → odd length palindrome (aba)
Between two characters → even length palindrome (aa)

So for each index:

Expand left and right
Count all palindromes
👶 Explain Like You're 5 Years Old

Imagine you have a word like "aba".

You stand on each letter and try to look left and right:

If both sides match → 🎉 palindrome!
Keep going outward until they don’t match

Like checking in a mirror:

If both sides look the same → it's special!

Time Complexity:
Outer loop: n
Expansion: worst case n
👉 O(n²)
📦 Space Complexity:
No extra space used
👉 O(1)

 */
public class PalindromicSubstrings {
	
	public static int countSubstrings(String s) {
		int count = 0;
		int n = s.length();
		for(int i = 0; i < n; i++) {
			count += expandFromCenter(s, i, i);
			count += expandFromCenter(s, i, i + 1);
		}
		return count;
	}

	public static int expandFromCenter(String s, int left, int right) {
		int count = 0;
		while(left >= 0 && right < s.length()  && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
		return count;
	}
	
	public static void main(String[] args) {

        String s1 = "abc";
        String s2 = "aaa";
        String s3 = "abba";
        String s4 = "racecar";

        System.out.println("Input: " + s1 + " -> Output: " + countSubstrings(s1));
        System.out.println("Input: " + s2 + " -> Output: " + countSubstrings(s2));
        System.out.println("Input: " + s3 + " -> Output: " + countSubstrings(s3));
        System.out.println("Input: " + s4 + " -> Output: " + countSubstrings(s4));
    }
}
