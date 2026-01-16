package blind75.string;


/*
 * 
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.

 

Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false

 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Time Complexity: O(n)

We go through each letter once in the loop → n operations
We check 26 boxes at the end → constant time
Total: O(n) where n is the length of the string

Space Complexity: O(1)

We always use exactly 26 boxes, no matter how long the strings are
26 is a constant number
So it's O(1) - constant space!
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		
		int[] count = new int[26];
		
		if(s.length() != t.length()) {
			
			return false;
		}
		
		for(int i = 0 ; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
			count[t.charAt(i) - 'a']--;
			
		}
		for(int i = 0 ; i < 26; i++) {
			if(count[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	 public static void main(String[] args) {
	        ValidAnagram solution = new ValidAnagram();
	        
	        // Test Case 1
	        String s1 = "anagram";
	        String t1 = "nagaram";
	        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
	        System.out.println("Output: " + solution.isAnagram(s1, t1));
	        System.out.println();
	        
	        // Test Case 2
	        String s2 = "rat";
	        String t2 = "car";
	        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
	        System.out.println("Output: " + solution.isAnagram(s2, t2));
	        System.out.println();
	        
	        // Test Case 3
	        String s3 = "listen";
	        String t3 = "silent";
	        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\"");
	        System.out.println("Output: " + solution.isAnagram(s3, t3));
	        System.out.println();
	        
	        // Test Case 4
	        String s4 = "a";
	        String t4 = "ab";
	        System.out.println("Input: s = \"" + s4 + "\", t = \"" + t4 + "\"");
	        System.out.println("Output: " + solution.isAnagram(s4, t4));
	    }

}
