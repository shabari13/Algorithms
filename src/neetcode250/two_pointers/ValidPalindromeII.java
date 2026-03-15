package neetcode250.two_pointers;
/*
 * You are given a string s, return true if the s can be a palindrome after deleting at most one character from it.

A palindrome is a string that reads the same forward and backward.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "aca"

Output: true
Explanation: "aca" is already a palindrome.

Example 2:

Input: s = "abbadc"

Output: false
Explanation: "abbadc" is not a palindrome and can't be made a palindrome after deleting at most one character.

Example 3:

Input: s = "abbda"

Output: true
Explanation: "We can delete the character 'd'.

We use a two-pointer approach. Start with pointers at both ends of the string and move inward. If characters match, continue. If they don't match, 
we get one chance to skip either the left character or the right character — then check if the remaining substring is a palindrome. If either option works, return true.

Constraints:

1 <= s.length <= 100,000
s is made up of only lowercase English letters.

⏱ TimeO(n)We scan the string at most twice — once in the main loop, once in isPalindrome helper
🧠 SpaceO(1)Only integer pointers used; no extra data structures or string copies

 */
public class ValidPalindromeII {
	
	public static boolean isValidPalindrome(String s, int left, int right) {
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	public static boolean validPalindrome(String s) {
		
		int left = 0 ; int right = s.length() - 1;
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return (isValidPalindrome(s, left + 1, right) || isValidPalindrome(s, left, right-1));
			}
			left++;
			right--;
		}
		return true;
	}
	
	  // ─── Main Method with Multiple Test Cases ────────────────────────────────
    public static void main(String[] args) {
        String[] testCases = {
            "aba",       // already a palindrome
            "abca",      // remove 'c' → "aba" ✓
            "raceacar",  // remove 'a' at index 4 → "racecar" ✓
            "deeee",     // remove one 'e' → "deee"? No. remove 'd' → "eeee" ✓
            "abcdef",    // no way to fix with 1 deletion ✗
            "a",         // single character → always true
            "aguokepatgbnlihuegoqig" // longer tricky case
        };

        for (String test : testCases) {
            System.out.println("Input    : \"" + test + "\"");
            System.out.println("Output   : " + validPalindrome(test));
            System.out.println("─".repeat(45));
        }
    }

}
