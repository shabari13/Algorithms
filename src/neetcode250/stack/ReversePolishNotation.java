package neetcode250.stack;

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
ComplexityWhyTimeO(n)Each of the n tokens is visited exactly once; each stack push/pop is O(1)SpaceO(n)Worst case: all tokens are numbers with one final operator (e.g., 1 2 3 4 ... +), so all n−1 numbers sit on the stack simultaneously
 */
public class ReversePolishNotation {
	public static int evalRPN(String[] tokens) {
	        Stack<Integer> stack = new Stack<>();
	        for(String token : tokens) {
	        	if(token.equals("+")) {
	        		int operator1 = stack.pop();
	        		int operator2 = stack.pop();
	        		int sum = operator1+ operator2;
	        		stack.push(sum);
	        	}
	        	else if(token.equals("*")) {
	        		int operator1 = stack.pop();
	        		int operator2 = stack.pop();
	        		int mul = operator1 * operator2;
	        		stack.push(mul);
	        	}
	        	
	        	else if(token.equals("/")) {
	        		int operator1 = stack.pop();
	        		int operator2 = stack.pop();
	        		int div = operator2 / operator1;
	        		stack.push(div);
	        	}
	        	else if(token.equals("-")) {
	        		int operator1 = stack.pop();
	        		int operator2 = stack.pop();
	        		int sub = operator2 - operator1;
	        		stack.push(sub);
	        	} else {
	        		stack.push(Integer.parseInt(token));
	        	}
	        }
	        return stack.peek();
	}
	
	 static void test(String label, String[] tokens, int expected) {
	        int result = evalRPN(tokens);
	        String status = (result == expected) ? "PASS" : "FAIL";
	        System.out.printf("[%s] %-45s → result: %4d  (expected: %d)%n",
	                status, label, result, expected);
	    }
	 
	    // ─────────────────────────────────────────────────────────────────────────
	    // MAIN: Five test cases exercising different scenarios.
	    // ─────────────────────────────────────────────────────────────────────────
	    public static void main(String[] args) {
	 
	        System.out.println("=== Evaluate Reverse Polish Notation ===\n");
	 
	        // ── Test 1 ──────────────────────────────────────────────────────────
	        // Expression: (2 + 1) * 3
	        // Tokens:      2  1  +  3  *
	        //
	        // Iteration detail:
	        //   token="2"  → push 2        → stack: [2]
	        //   token="1"  → push 1        → stack: [2, 1]
	        //   token="+"  → pop b=1, a=2  → push 2+1=3   → stack: [3]
	        //   token="3"  → push 3        → stack: [3, 3]
	        //   token="*"  → pop b=3, a=3  → push 3*3=9   → stack: [9]
	        //   End        → pop → answer = 9
	        test("(2 + 1) * 3",
	                new String[]{"2", "1", "+", "3", "*"},
	                9);
	 
	        // ── Test 2 ──────────────────────────────────────────────────────────
	        // Expression: 4 + (13 / 5) = 4 + 2 = 6   (integer division: 13/5=2)
	        // Tokens:      4  13  5  /  +
	        //
	        // Iteration detail:
	        //   token="4"  → push 4          → stack: [4]
	        //   token="13" → push 13         → stack: [4, 13]
	        //   token="5"  → push 5          → stack: [4, 13, 5]
	        //   token="/"  → pop b=5, a=13   → push 13/5=2 → stack: [4, 2]
	        //   token="+"  → pop b=2, a=4    → push 4+2=6  → stack: [6]
	        //   End        → pop → answer = 6
	        test("4 + (13 / 5) [integer division]",
	                new String[]{"4", "13", "5", "/", "+"},
	                6);
	 
	        // ── Test 3 ──────────────────────────────────────────────────────────
	        // Expression: 10 - (3 * 2) = 10 - 6 = 4
	        // Tokens:      10  3  2  *  -
	        //
	        // Iteration detail:
	        //   token="10" → push 10         → stack: [10]
	        //   token="3"  → push 3          → stack: [10, 3]
	        //   token="2"  → push 2          → stack: [10, 3, 2]
	        //   token="*"  → pop b=2, a=3    → push 3*2=6  → stack: [10, 6]
	        //   token="-"  → pop b=6, a=10   → push 10-6=4 → stack: [4]
	        //   End        → pop → answer = 4
	        test("10 - (3 * 2)",
	                new String[]{"10", "3", "2", "*", "-"},
	                4);
	 
	        // ── Test 4: Negative numbers ─────────────────────────────────────────
	        // Expression: -2 * -3 = 6
	        // Tokens:      -2  -3  *
	        //
	        // Iteration detail:
	        //   token="-2" → push -2         → stack: [-2]
	        //   token="-3" → push -3         → stack: [-2, -3]
	        //   token="*"  → pop b=-3, a=-2  → push (-2)*(-3)=6 → stack: [6]
	        //   End        → pop → answer = 6
	        test("Negatives: -2 * -3",
	                new String[]{"-2", "-3", "*"},
	                6);
	 
	        // ── Test 5: Complex / deeply nested ──────────────────────────────────
	        // Expression: ((2 + 3) * (10 - 4)) / 6
	        //           = (5 * 6) / 6
	        //           = 30 / 6
	        //           = 5
	        // Tokens:   2  3  +  10  4  -  *  6  /
	        //
	        // Iteration detail:
	        //   token="2"  → push 2          → stack: [2]
	        //   token="3"  → push 3          → stack: [2, 3]
	        //   token="+"  → pop b=3, a=2    → push 2+3=5    → stack: [5]
	        //   token="10" → push 10         → stack: [5, 10]
	        //   token="4"  → push 4          → stack: [5, 10, 4]
	        //   token="-"  → pop b=4, a=10   → push 10-4=6   → stack: [5, 6]
	        //   token="*"  → pop b=6, a=5    → push 5*6=30   → stack: [30]
	        //   token="6"  → push 6          → stack: [30, 6]
	        //   token="/"  → pop b=6, a=30   → push 30/6=5   → stack: [5]
	        //   End        → pop → answer = 5
	        test("((2+3)*(10-4))/6",
	                new String[]{"2", "3", "+", "10", "4", "-", "*", "6", "/"},
	                5);
	 
	        System.out.println("\n=== All tests complete ===");
	    }
}
