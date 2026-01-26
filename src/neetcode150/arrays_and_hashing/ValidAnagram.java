package neetcode150.arrays_and_hashing;
/*
 * 
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: s = "racecar", t = "carrace"

Output: true
Example 2:

Input: s = "jar", t = "jam"

Output: false

Time Complexity: O(n) where n is the length of the strings

We go through each letter once in the counting loop
We check 26 positions in the final loop (constant)
So it's like counting n candies one by one

Space Complexity: O(1)

We only use an array of size 26, which doesn't change no matter how long the strings are
26 is a constant, so we say O(1) (constant space)
It's like always using the same 26 jars, whether you're counting 5 letters or 5000 letters!
 */
public class ValidAnagram {
	
	public static boolean isAnagram(String s, String t) {
		
		if(s.length() != t.length()) {
			return false;
		}
		int[] charCount = new int[26];
		for(int i = 0 ; i < s.length(); i++) {
			charCount[s.charAt(i) - 'a']++;
			charCount[t.charAt(i) - 'a']--;
			
		}
		for(int count : charCount) {
			if(count != 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
        // Test Case 1
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: " + isAnagram(s1, t1));
        System.out.println();
        
        // Test Case 2
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: " + isAnagram(s2, t2));
        System.out.println();
        
        // Test Case 3
        String s3 = "listen";
        String t3 = "silent";
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Output: " + isAnagram(s3, t3));
        System.out.println();
        
        // Test Case 4
        String s4 = "hello";
        String t4 = "world";
        System.out.println("Input: s = \"" + s4 + "\", t = \"" + t4 + "\"");
        System.out.println("Output: " + isAnagram(s4, t4));
        System.out.println();
        
        // Test Case 5 - Different lengths
        String s5 = "a";
        String t5 = "ab";
        System.out.println("Input: s = \"" + s5 + "\", t = \"" + t5 + "\"");
        System.out.println("Output: " + isAnagram(s5, t5));
    }

}
