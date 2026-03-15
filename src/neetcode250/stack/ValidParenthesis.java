package neetcode250.stack;

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
Explanation: The brackets are not closed in the correct order.

Constraints:

1 <= s.length <= 1000
The idea behind the solution: The key insight is that parentheses must be closed in the reverse order they were opened — the last opened bracket must be the first closed. This "last in, first out" behavior is exactly what a stack is built for. As we scan the string left-to-right, every opening bracket gets pushed onto the stack. When we hit a closing bracket, we peek at the top of the stack — it must be the matching opener. If it doesn't match, or the stack is empty when we see a closer, the string is invalid. At the end, the stack must be completely empty, meaning every opener was properly closed.
Time complexity — O(n): We visit each character exactly once. Each push and pop on the stack is O(1), so the total work scales linearly with the length of the string.
Space complexity — O(n): In the worst case (e.g. "(((((((" with all openers and no closers), every character gets pushed onto the stack, consuming O(n) space.
 In the best case (alternating pairs like "()()()") the stack never holds more than one element at a time — O(1) — but we measure by the worst case.
 */
public class ValidParenthesis {
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
				stack.push(s.charAt(i) );
			} else if(s.charAt(i) == '}' || s.charAt(i) == ')' || s.charAt(i) == ']'){
				if(stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if(s.charAt(i) == '}' && top != '{') {
					return false;
				}
				if(s.charAt(i) == ']' && top != '[') {
					return false;
				}
				if(s.charAt(i) == ')' && top != '(') {
					return false;
				}
			}
		}
		return stack.isEmpty();
        
    }
	
	public static void main(String[] args) {
        String[] inputs = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "",
            "(((",
            "{[()]}",
            "]"
        };

        System.out.println("===== Validate Parentheses =====");
        for (String input : inputs) {
            boolean result = isValid(input);
            System.out.printf("Input: %-12s -> %s%n",
                "\"" + input + "\"",
                result ? "VALID ✓" : "INVALID ✗");
        }
    }
}
