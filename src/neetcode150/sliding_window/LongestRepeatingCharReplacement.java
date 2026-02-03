package neetcode150.sliding_window;
/*
 * 
 * You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k characters of the string and replace them with any other uppercase English character.

After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

Example 1:

Input: s = "XYYX", k = 2

Output: 4
Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

Example 2:

Input: s = "AAABABB", k = 1

Output: 5
Constraints:

1 <= s.length <= 1000
0 <= k <= s.length
Time Complexity: O(n)

n = length of string
We visit each character once with the right pointer (O(n))
The left pointer also moves at most n times total across all iterations
Even though there's a while loop, left never goes backward, so total movement is O(n)
Each operation inside the loop is O(1) (array access, arithmetic)
Total: O(n)

Space Complexity: O(1)

We use a count array of size 26 (for A-Z), which is constant space
A few integer variables (left, right, maxCount, maxLength)
Total: O(1) - constant space, doesn't depend on input size
 */
public class LongestRepeatingCharReplacement {

	public int characterReplacement(String s, int k) {
		
		int[] count = new int[26];
		int maxCount = 0;
		int maxLength = 0;
		int left = 0;
		
		for(int right = 0; right < s.length(); right++) {
			char currentChar = s.charAt(right);
			count[currentChar - 'A']++;
			maxCount = Math.max(maxCount, count[currentChar - 'A']);
			while(right - left + 1 - maxCount > k) {
				count[s.charAt(left) - 'A']--;
				left++;
				
			}
			maxLength = Math.max(maxLength, right - left + 1);
		}
		return maxLength;
	}
	
	 public static void main(String[] args) {
		 LongestRepeatingCharReplacement solution = new LongestRepeatingCharReplacement();
	        
	        // Test Case 1
	        String s1 = "ABAB";
	        int k1 = 2;
	        int result1 = solution.characterReplacement(s1, k1);
	        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
	        System.out.println("Output: " + result1);
	        System.out.println("Explanation: Replace the two 'A's with 'B's to get \"BBBB\"\n");
	        
	        // Test Case 2
	        String s2 = "AABABBA";
	        int k2 = 1;
	        int result2 = solution.characterReplacement(s2, k2);
	        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
	        System.out.println("Output: " + result2);
	        System.out.println("Explanation: Replace one 'A' in the middle to get \"AABBBBA\"\n");
	        
	        // Test Case 3
	        String s3 = "AAAA";
	        int k3 = 2;
	        int result3 = solution.characterReplacement(s3, k3);
	        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
	        System.out.println("Output: " + result3);
	        System.out.println("Explanation: All characters are same, no replacement needed\n");
	        
	        // Test Case 4
	        String s4 = "ABCDE";
	        int k4 = 1;
	        int result4 = solution.characterReplacement(s4, k4);
	        System.out.println("Input: s = \"" + s4 + "\", k = " + k4);
	        System.out.println("Output: " + result4);
	        System.out.println("Explanation: Can replace 1 character to get 2 same characters\n");
	    }
}
