package neetcode250.stack;

import java.util.Stack;

/*
 * You are given an encoded string s, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. There will not be input like 3a, 2[4], a[a] or a[2].

The test cases are generated so that the length of the output will never exceed 100,000.

Example 1:

Input: s = "2[a3[b]]c"

Output: "abbbabbbc"
Example 2:

Input: s = "axb3[z]4[c]"

Output: "axbzzzcccc"
Example 3:

Input: s = "ab2[c]3[d]1[x]"

Output: "abccdddx"
Constraints:

1 <= s.length <= 30
s is made up of lowercase English letters, digits, and square brackets '[]'.
All the integers in s are in the range [1, 300].
s is guaranteed to be a valid input.

The Idea (for grown-ups)
We use two stacks — one for counts, one for strings built so far. As we scan each character:

Digit → build the number
[ → push current string & number onto stacks, reset them
] → pop from both stacks, repeat current string by the count, append to popped string
Letter → append to current string


👶 Explain Like I'm 5
Imagine you're reading a recipe that says "do this 3 times" inside "do that 2 times".
 You use two boxes (stacks). When you see a number followed by [, you put what you've already built into one box and the number into another box, 
 then start fresh. When you see ], you take things back out of both boxes, copy your fresh string that many times, and glue it to what was in the box.
 
 Time Complexity: O(n · k_max)

n = length of decoded output string
In the worst case (deeply nested repeats like k[k[k[...]]]), each character of the final string is written once. So time is O(n) where n is the length of the output, which can be exponential in the input size.
For input of length L with max repeat factor k, worst case is O(k^depth × L).

Space Complexity: O(m + d)

m = total length of strings stored across both stacks at peak nesting
d = maximum nesting depth (stack height)
In the worst case this is also O(n) proportional to the output size, because intermediate strings stored on the stack can collectively be as large as the final output.
 */
public class DecodeString {
	
	public static String decodeString(String str) {
		Stack<Integer> countStack = new Stack<>();
		Stack<StringBuilder> stringStack = new Stack<>();
		int k = 0;
		StringBuilder current = new StringBuilder();
		for(Character ch : str.toCharArray()) {
			if(Character.isDigit(ch)) {
				k = k * 10 + (ch - '0');
			} else if (ch.equals('[')) {
				countStack.push(k);
				stringStack.push(current);
				current = new StringBuilder();
				 k = 0;
			} else if(ch.equals(']')) {
				int repeatTimes = countStack.pop();
				StringBuilder prev = stringStack.pop();
				for(int i = 0 ; i < repeatTimes; i++) {
					prev.append(current);
				}
				current = prev;
			} else {
				current.append(ch);
			}
		}
		return current.toString();
	}
	
	  public static void main(String[] args) {

	        String[] inputs = {
	            "3[a]",          // → aaa
	            "2[bc]",         // → bcbc
	            "3[a2[c]]",      // → accaccacc
	            "2[abc]3[cd]ef", // → abcabccdcdcdef
	            "10[a]",         // → aaaaaaaaaa  (multi-digit number)
	            "2[3[b]c]"       // → bbbcbbbc
	        };

	        for (String input : inputs) {
	            System.out.println("Input : " + input);
	            System.out.println("Output: " + decodeString(input));
	            System.out.println("─".repeat(30));
	        }
	    }

}
