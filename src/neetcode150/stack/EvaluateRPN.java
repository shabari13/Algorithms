package neetcode150.stack;

import java.util.Stack;

/*
 * You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.

Return the integer that represents the evaluation of the expression.

The operands may be integers or the results of other operations.
The operators include '+', '-', '*', and '/'.
Assume that division between integers always truncates toward zero.
Example 1:

Input: tokens = ["1","2","+","3","*","4","-"]

Output: 5

Explanation: ((1 + 2) * 3) - 4 = 5
Constraints:

1 <= tokens.length <= 1000.
tokens[i] is "+", "-", "*", or "/", or a string representing an integer in the range [-100, 100].

Time Complexity: O(n)

n is the number of tokens
We go through each token exactly once
Each operation (push, pop, arithmetic) takes O(1) constant time
Total: O(n)

Space Complexity: O(n)

In the worst case, we might push all tokens onto the stack
Example: ["1", "2", "3", "4", "5"] (no operators) would have 5 items in stack
So we need O(n) space for the stack
 */
public class EvaluateRPN {
	public int evalRPN(String[] tokens) {
		
		Stack<Integer> stack =  new Stack<>();
		for(String token : tokens) {
			if(token.equals("+")) {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a + b);
			}  else if(token.equals("-")) {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a - b);
			} else if(token.equals("*")) {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a * b);
			} else if(token.equals("/")) {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a / b);
			} else {
				stack.push(Integer.parseInt(token));
			}
			
		}
		return stack.peek();
	}
	
	public static void main(String[] args) {
		EvaluateRPN solution = new EvaluateRPN();
        
        // Test Case 1
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Input: [\"2\", \"1\", \"+\", \"3\", \"*\"]");
        System.out.println("Output: " + solution.evalRPN(tokens1));
        System.out.println();
        
        // Test Case 2
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Input: [\"4\", \"13\", \"5\", \"/\", \"+\"]");
        System.out.println("Output: " + solution.evalRPN(tokens2));
        System.out.println();
        
        // Test Case 3
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Input: [\"10\", \"6\", \"9\", \"3\", \"+\", \"-11\", \"*\", \"/\", \"*\", \"17\", \"+\", \"5\", \"+\"]");
        System.out.println("Output: " + solution.evalRPN(tokens3));
        System.out.println();
        
        // Test Case 4
        String[] tokens4 = {"3", "4", "+", "2", "*", "7", "/"};
        System.out.println("Input: [\"3\", \"4\", \"+\", \"2\", \"*\", \"7\", \"/\"]");
        System.out.println("Output: " + solution.evalRPN(tokens4));
    }
}
