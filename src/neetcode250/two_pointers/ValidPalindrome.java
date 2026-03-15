package neetcode250.two_pointers;
/*
 * Given a string s, return true if it is a palindrome, otherwise return false.

A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "Was it a car or a cat I saw?"

Output: true
Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

Example 2:

Input: s = "tab a cat"

Output: false
Explanation: "tabacat" is not a palindrome.

Constraints:

1 <= s.length <= 1000
s is made up of only printable ASCII characters.
TimeO(n)Each character is visited at most once by either the left or right pointer🧠 SpaceO(1)No cleaned copy of the string is ever created — only two integer pointers are stored
 */
public class ValidPalindrome {
	
	public static boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
		while(left < right) {
			while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			}
			while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			}
			
			if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right) )) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	 public static void main(String[] args) {

	        String[] testCases = {
	            "Was it a car or a cat I saw?",  // true  – classic sentence palindrome
	            "tab a cat",                      // false – "tabacat" not a palindrome
	            "A man, a plan, a canal: Panama", // true  – famous palindrome
	            "race a car",                     // false
	            " ",                              // true  – no alphanumeric chars → trivially true
	            "No 'x' in Nixon",               // true
	            "hello",                          // false
	            "Madam, I'm Adam",               // true
	            "0P",                             // false
	            "12321"                           // true  – digits work too
	        };

	        System.out.println("╔════════════════════════════════════╦═══════════════════╗");
	        System.out.println("║             Input                  ║      Result       ║");
	        System.out.println("╠════════════════════════════════════╬═══════════════════╣");

	        for (String test : testCases) {
	            boolean result = isPalindrome(test);
	            System.out.printf("║ %-34s ║ %-17s ║%n",
	                    "\"" + test + "\"",
	                    result ? "Palindrome ✓" : "Not Palindrome ✗");
	        }

	        System.out.println("╚════════════════════════════════════╩═══════════════════╝");
	    }
}
