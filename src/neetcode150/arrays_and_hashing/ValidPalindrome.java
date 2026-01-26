package neetcode150.arrays_and_hashing;
/*
 * 
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

 Time Complexity: O(n)
What does this mean?

n is the length of the string
In the worst case, we visit each character once
We use two pointers moving from opposite ends
Even though we have nested while loops, each character is visited at most once
The inner while loops just skip characters, they don't restart from the beginning

Example:

String "racecar" (7 characters): We check at most 7 characters
String "A man, a plan" (14 characters): We check at most 14 characters

Why not O(n²)?

Even though we have while inside while, the inner loops don't reset
The left pointer only moves right (never goes back)
The right pointer only moves left (never goes back)
Total movements: left moves n times max, right moves n times max = O(n)
💾 Space Complexity: O(1)
What does this mean?

We only use two integer variables: left and right
We don't create any arrays, lists, or new strings
The space used doesn't depend on the input size
O(1) means "constant space" - always the same!

What we DON'T do:

❌ We don't create a cleaned version of the string
❌ We don't store characters in an array
❌ We don't use recursion (which uses stack space)
 */
public class ValidPalindrome {
	
	public static boolean isPalindrome(String s) {
		
		if (s == null || s.length() == 0) {
            return true;
        }
		
		int left = 0; 
		
		int right = s.length() - 1;
		
		while(left < right) {
			
			while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				
				left++;
			}
			
			while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			}
			
			if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
				
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
        // Test case 1: Simple palindrome
        String test1 = "A man, a plan, a canal: Panama";
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test1));
        System.out.println();
        
        // Test case 2: Not a palindrome
        String test2 = "race a car";
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test2));
        System.out.println();
        
        // Test case 3: Empty string
        String test3 = " ";
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test3));
        System.out.println();
        
        // Test case 4: Single character
        String test4 = "a";
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test4));
        System.out.println();
        
        // Test case 5: Mixed case palindrome
        String test5 = "RaceCar";
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test5));
        System.out.println();
        
        // Test case 6: With numbers
        String test6 = "A1b2B1a";
        System.out.println("Input: \"" + test6 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test6));
        System.out.println();
        
        // Test case 7: Only special characters
        String test7 = "!!!";
        System.out.println("Input: \"" + test7 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test7));
        System.out.println();
        
        // Test case 8: Simple non-palindrome
        String test8 = "hello";
        System.out.println("Input: \"" + test8 + "\"");
        System.out.println("Is palindrome? " + isPalindrome(test8));
    }
}
