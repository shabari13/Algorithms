package neetcode150.stack;

import java.util.Stack;
/*
 * You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.

The input string s is valid if and only if:

Every open bracket is closed by the same type of close bracket.
Open brackets are closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
Return true if s is a valid string, and false otherwise.

Example 1:

Input: s = "[]"

Output: true
Example 2:

Input: s = "([{}])"

Output: true
Example 3:

Input: s = "[(])"

Output: false

Time Complexity:
O(n) where n = length of string
Why? We go through each character in the string exactly once. Each push and pop operation on the stack takes O(1) time.

Space Complexity:
O(n) in the worst case
Why? In the worst case, all characters are opening brackets (like "(((((("), so we'd store all of them in the stack. The stack can grow up to the size of the input string.
 */
public class ValidParenthesis {

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(char c : s.toCharArray()) {
			
			if(c == '{' || c == '[' || c == '(') {
				stack.push(c);
			} else {
				if(stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if( c == '}' && top != '{')
					return false;
				if( c == ']' && top != '[')
					return false;
				if( c == ')' && top != '(')
					return false;
			}
		}
		return stack.isEmpty();
	}
	
	  public static void main(String[] args) {
		  ValidParenthesis solution = new ValidParenthesis();
	        
	        // Test case 1
	        String test1 = "()";
	        System.out.println("Input: \"" + test1 + "\"");
	        System.out.println("Output: " + solution.isValid(test1));
	        System.out.println();
	        
	        // Test case 2
	        String test2 = "()[]{}";
	        System.out.println("Input: \"" + test2 + "\"");
	        System.out.println("Output: " + solution.isValid(test2));
	        System.out.println();
	        
	        // Test case 3
	        String test3 = "(]";
	        System.out.println("Input: \"" + test3 + "\"");
	        System.out.println("Output: " + solution.isValid(test3));
	        System.out.println();
	        
	        // Test case 4
	        String test4 = "([)]";
	        System.out.println("Input: \"" + test4 + "\"");
	        System.out.println("Output: " + solution.isValid(test4));
	        System.out.println();
	        
	        // Test case 5
	        String test5 = "{[]}";
	        System.out.println("Input: \"" + test5 + "\"");
	        System.out.println("Output: " + solution.isValid(test5));
	        System.out.println();
	        
	        // Test case 6 - empty string
	        String test6 = "";
	        System.out.println("Input: \"" + test6 + "\"");
	        System.out.println("Output: " + solution.isValid(test6));
	        System.out.println();
	        
	        // Test case 7 - only closing bracket
	        String test7 = "]";
	        System.out.println("Input: \"" + test7 + "\"");
	        System.out.println("Output: " + solution.isValid(test7));
	    }
}
