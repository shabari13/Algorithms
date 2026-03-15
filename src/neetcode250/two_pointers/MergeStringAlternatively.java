package neetcode250.two_pointers;
/*
 * You are given two strings, word1 and word2. Construct a new string by merging them in alternating order, starting with word1 — take one character from word1, then one from word2, and repeat this process.

If one string is longer than the other, append the remaining characters from the longer string to the end of the merged result.

Return the final merged string.

Example 1:

Input: word1 = "abc", word2 = "xyz"

Output: "axbycz"
Example 2:

Input: word1 = "ab", word2 = "abbxxc"

Output: "aabbbxxc"
Constraints:

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
⏱ TimeO(m + n)We visit every character of both strings exactly once, where m = len(word1) and n = len(word2)
🗂 SpaceO(m + n)
The StringBuilder grows to hold all m + n characters of the final merged string
 */
public class MergeStringAlternatively {
	public static String mergeAlternately(String word1, String word2) {
		/*
		 * int pointer1 = 0; int pointer2 = 0; StringBuilder sb = new StringBuilder();
		 * while(pointer1 < word1.length() && pointer2 < word2.length()) {
		 * sb.append(word1.charAt(pointer1)); sb.append(word2.charAt(pointer2));
		 * pointer1++; pointer2++; } while(pointer1 < word1.length()) {
		 * sb.append(word1.charAt(pointer1)); pointer1++; } while(pointer2 <
		 * word2.length()) { sb.append(word2.charAt(pointer2)); pointer2++; } return
		 * sb.toString();
		 */
		
		StringBuilder sb = new StringBuilder();
		int len1 = word1.length();
		int len2 = word2.length();
		int i = 0;
		while(i < len1 || i < len2) {
			if(i < len1) {
				sb.append(word1.charAt(i));
			}
			if(i < len2) {
				sb.append(word2.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
	  public static void main(String[] args) {

	        // Test Case 1: Equal length strings
	        String w1 = "abc", w2 = "xyz";
	        System.out.println("Input   : word1 = \"" + w1 + "\", word2 = \"" + w2 + "\"");
	        System.out.println("Output  : " + mergeAlternately(w1, w2));
	        System.out.println();

	        // Test Case 2: word1 is shorter
	        w1 = "ab"; w2 = "xyzw";
	        System.out.println("Input   : word1 = \"" + w1 + "\", word2 = \"" + w2 + "\"");
	        System.out.println("Output  : " + mergeAlternately(w1, w2));
	        System.out.println();

	        // Test Case 3: word2 is shorter
	        w1 = "abcd"; w2 = "pq";
	        System.out.println("Input   : word1 = \"" + w1 + "\", word2 = \"" + w2 + "\"");
	        System.out.println("Output  : " + mergeAlternately(w1, w2));
	        System.out.println();

	        // Test Case 4: One character each
	        w1 = "a"; w2 = "b";
	        System.out.println("Input   : word1 = \"" + w1 + "\", word2 = \"" + w2 + "\"");
	        System.out.println("Output  : " + mergeAlternately(w1, w2));
	        System.out.println();

	        // Test Case 5: word1 has only one character
	        w1 = "a"; w2 = "bcde";
	        System.out.println("Input   : word1 = \"" + w1 + "\", word2 = \"" + w2 + "\"");
	        System.out.println("Output  : " + mergeAlternately(w1, w2));
	    }
}
