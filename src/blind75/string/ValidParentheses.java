package blind75.string;

import java.util.Stack;

/*
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false

 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 * 
 * 
 * Time and Space Complexity
Time Complexity: O(n)

n is the length of the string
We go through each character in the string exactly once
For each character, we do constant time operations: push, pop, or comparison
Therefore, the total time is proportional to the length of the string

Space Complexity: O(n)

In the worst case, the stack can hold all the characters
Example: if the input is "(((((", we push all 5 opening brackets onto the stack
The maximum size of the stack is n (when all characters are opening brackets)
Therefore, the space used is proportional to the length of the string
 */
public class ValidParentheses {
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(char c : s.toCharArray()) {
			
			if(c == '{' || c == '['  || c == '(') {
				stack.push(c);
				
			} else {
				if(stack.isEmpty())
					return false;
				char top =  stack.pop();
				if(c == '}' && top != '{')
					return false;
				if(c == ']' && top != '[') {
					return false;
				}
				if(c == ')' && top != '(') {
					return false;
				}
			}
		}
		return stack.empty();
		
	}
	
	 public static void main(String[] args) {
	        ValidParentheses solution = new ValidParentheses();
	        
	        // Test case 1: Simple valid parentheses
	        String test1 = "()";
	        System.out.println("Input: \"" + test1 + "\"");
	        System.out.println("Output: " + solution.isValid(test1));
	        System.out.println();
	        
	        // Test case 2: Multiple types of brackets
	        String test2 = "()[]{}";
	        System.out.println("Input: \"" + test2 + "\"");
	        System.out.println("Output: " + solution.isValid(test2));
	        System.out.println();
	        
	        // Test case 3: Nested brackets
	        String test3 = "{[]}";
	        System.out.println("Input: \"" + test3 + "\"");
	        System.out.println("Output: " + solution.isValid(test3));
	        System.out.println();
	        
	        // Test case 4: Invalid - wrong closing bracket
	        String test4 = "(]";
	        System.out.println("Input: \"" + test4 + "\"");
	        System.out.println("Output: " + solution.isValid(test4));
	        System.out.println();
	        
	        // Test case 5: Invalid - extra closing bracket
	        String test5 = "([)]";
	        System.out.println("Input: \"" + test5 + "\"");
	        System.out.println("Output: " + solution.isValid(test5));
	        System.out.println();
	        
	        // Test case 6: Invalid - only opening brackets
	        String test6 = "(((";
	        System.out.println("Input: \"" + test6 + "\"");
	        System.out.println("Output: " + solution.isValid(test6));
	        System.out.println();
	        
	        // Test case 7: Invalid - only closing brackets
	        String test7 = ")))";
	        System.out.println("Input: \"" + test7 + "\"");
	        System.out.println("Output: " + solution.isValid(test7));
	        System.out.println();
	        
	        // Test case 8: Empty string
	        String test8 = "";
	        System.out.println("Input: \"" + test8 + "\"");
	        System.out.println("Output: " + solution.isValid(test8));
	    }

}
