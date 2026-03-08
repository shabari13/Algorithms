package neetcode250.arrays_hashing;
/*
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: s = "racecar", t = "carrace"

Output: true
Example 2:

Input: s = "jar", t = "jam"

Output: false
Constraints:

s and t consist of lowercase English letters.

 */
public class ValidAnagram {
	
	public static boolean isAnagram(String s, String t) {
		if(s.length() != t.length()) {
			return false;
		}
		int[] freq = new int[256];
		for(int i = 0; i < s.length(); i++) {
			freq[s.charAt(i)]++;
			freq[t.charAt(i)]--;
		}
		for(int num : freq) {
			if(num != 0) {
				return false;
			}
		}
		return true;
	}
	  public static void main(String[] args) {

	        // Test Case 1: Classic anagram
	        String s1 = "anagra!m", t1 = "nagara!m";
	        System.out.println("isAnagram(\"" + s1 + "\", \"" + t1 + "\") → "
	            + isAnagram(s1, t1));  // true

	        // Test Case 2: Not an anagram
	        String s2 = "rat", t2 = "car";
	        System.out.println("isAnagram(\"" + s2 + "\", \"" + t2 + "\") → "
	            + isAnagram(s2, t2));  // false

	        // Test Case 3: Different lengths → early exit
	        String s3 = "hello", t3 = "hi";
	        System.out.println("isAnagram(\"" + s3 + "\", \"" + t3 + "\") → "
	            + isAnagram(s3, t3));  // false

	        // Test Case 4: Same character repeated
	        String s4 = "aab", t4 = "bba";
	        System.out.println("isAnagram(\"" + s4 + "\", \"" + t4 + "\") → "
	            + isAnagram(s4, t4));  // false

	        // Test Case 5: Single character match
	        String s5 = "a", t5 = "a";
	        System.out.println("isAnagram(\"" + s5 + "\", \"" + t5 + "\") → "
	            + isAnagram(s5, t5));  // true
	    }

}
