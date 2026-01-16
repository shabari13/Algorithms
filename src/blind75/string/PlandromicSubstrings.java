package blind75.string;

/*
 * Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

The Big Idea 💡
Think of palindromes like a sandwich - they have a center, and both sides mirror each other!

"aba" → center is 'b', and we have 'a' on both sides
"aa" → center is between the two 'a's

Our trick: For every position in the string, we stand in the middle and walk outwards on both sides, checking if the letters match.
 * 
 * Time Complexity: O(n²)

We visit each position once: n positions
For each position, we might expand up to n times in the worst case
Example: "aaaa" - from the center, we expand many times
So: n positions × n expansions = O(n²)

Space Complexity: O(1)

We only use a few variables (count, left, right)
No extra arrays or data structures
The space we use doesn't grow with input size
 */
public class PlandromicSubstrings {
	
	public int countSubstrings(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			count += expandFromCenter(s, i, i);
			count += expandFromCenter(s, i, i+1);
			
		}
		
		return count;
		
	}
	
	public int expandFromCenter(String s, int left, int right) {
		
		int count =0 ;
		
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		PlandromicSubstrings solution = new PlandromicSubstrings();
        
        // Test Case 1
        String test1 = "abc";
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test1));
        System.out.println();
        
        // Test Case 2
        String test2 = "aaa";
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test2));
        System.out.println();
        
        // Test Case 3
        String test3 = "aba";
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test3));
        System.out.println();
        
        // Test Case 4
        String test4 = "racecar";
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test4));
        System.out.println();
        
        // Test Case 5
        String test5 = "a";
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Output: " + solution.countSubstrings(test5));
    }

}
