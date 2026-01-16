package blind75.string;
/*
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 
Time Complexity: O(n)

n is the length of the input string
We go through the string once to clean it: O(n)
We go through at most half the cleaned string to check: O(n/2) = O(n)
Total: O(n) + O(n) = O(n)

Space Complexity: O(n)

We create a new StringBuilder to store the cleaned string
In the worst case (all characters are alphanumeric), it stores all n characters
So we use O(n) extra space

 */
public class ValidPalindrome {
	
	public boolean isPalindrome(String s) {
		StringBuilder cleaned = new StringBuilder();
		
		for(char c : s.toCharArray()) {
			if(Character.isLetterOrDigit(c)) {
				
				cleaned.append(Character.toLowerCase(c));
			}
		}
		int len  = cleaned.length();
		int left = 0;
		int right = len - 1;
		
		while(left < right) {
			
			if(cleaned.charAt(left) != cleaned.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
	}
	
	  public static void main(String[] args) {
	        ValidPalindrome solution = new ValidPalindrome();
	        
	        // Test case 1
	        String test1 = "A man, a plan, a canal: Panama";
	        System.out.println("Input: \"" + test1 + "\"");
	        System.out.println("Output: " + solution.isPalindrome(test1));
	        System.out.println();
	        
	        // Test case 2
	        String test2 = "race a car";
	        System.out.println("Input: \"" + test2 + "\"");
	        System.out.println("Output: " + solution.isPalindrome(test2));
	        System.out.println();
	        
	        // Test case 3
	        String test3 = " ";
	        System.out.println("Input: \"" + test3 + "\"");
	        System.out.println("Output: " + solution.isPalindrome(test3));
	        System.out.println();
	        
	        // Test case 4
	        String test4 = "Was it a car or a cat I saw?";
	        System.out.println("Input: \"" + test4 + "\"");
	        System.out.println("Output: " + solution.isPalindrome(test4));
	        System.out.println();
	        
	        // Test case 5
	        String test5 = "Madam";
	        System.out.println("Input: \"" + test5 + "\"");
	        System.out.println("Output: " + solution.isPalindrome(test5));
	    }

}
