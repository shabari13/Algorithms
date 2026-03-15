package neetcode250.stack;

import java.util.Stack;

/*
 * You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

Given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:

An integer x: Record a new score of x.
'+': Record a new score that is the sum of the previous two scores.
'D': Record a new score that is the double of the previous score.
'C': Invalidate the previous score, removing it from the record.
Return the sum of all the scores on the record after applying all the operations.

Note: The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.

Example 1:

Input: ops = ["1","2","+","C","5","D"]

Output: 18
Explanation:

"1" - Add 1 to the record, record = [1].
"2" - Add 2 to the record, record = [1, 2].
"+" - Add 1 + 2 = 3 to the record, record = [1, 2, 3].
"C" - Invalidate and remove the previous score, record = [1, 2].
"5" - Add 5 to the record, record = [1, 2, 5].
"D" - Add 2 * 5 = 10 to the record, record = [1, 2, 5, 10].
The total sum is 1 + 2 + 5 + 10 = 18.
Example 2:

Input: ops = ["5","D","+","C"]

Output: 15
Explanation:

"5" - Add 5 to the record, record = [5].
"D" - Add 2 * 5 = 10 to the record, record = [5, 10].
"+" - Add 5 + 10 = 15 to the record, record = [5, 10, 15].
"C" - Invalidate and remove the previous score, record = [5, 10].
The total sum is 5 + 10 = 15.
Constraints:

1 <= operations.length <= 1000
operations[i] is "C", "D", +, or a string representing an integer in the range [(-30,000), (30,000)].
For operation "+", there will always be at least two previous scores on the record.
For operations "C" and "D", there will always be at least one previous score on the record.

Time complexity — O(n)
Each of the n operations is processed exactly once. Every stack operation (push, pop, peek) runs in O(1). The final summation loop is also O(n). Total: O(n).
Space complexity — O(n)
In the worst case (no "C" operations), every operation adds one element to the stack, so the stack can hold up to n elements. Total: O(n).
 */
public class BaseballGame {
	
	 public static int calPoints(String[] operations) {
		 Stack<Integer> stack = new Stack<>();
		 for(String op : operations) {
			 if(op.equals("C")) {
				 stack.pop();
			 } else if (op.equals("D")) {
				 stack.push(2 * stack.peek());
			 } else if((op.equals("+"))) {
				 int top = stack.pop();
				 int sum = top + stack.peek();
				 stack.push(top);
				 stack.push(sum);
			 } else {
				 stack.push(Integer.parseInt(op));
			 }
		 }
		 int total = 0 ;
		 for(int num : stack) {
			 total += num;
		 }
	        return total;
	    }
	   public static void main(String[] args) {

	        // --- Test Case 1 (LeetCode example 1) ---
	        String[] ops1 = {"5", "2", "C", "D", "+"};
	        System.out.println("Input:    [\"5\", \"2\", \"C\", \"D\", \"+\"]");
	        System.out.println("Output:   " + calPoints(ops1));
	        System.out.println("Expected: 30");
	        System.out.println();

	        // --- Test Case 2 (LeetCode example 2) ---
	        String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};
	        System.out.println("Input:    [\"5\", \"-2\", \"4\", \"C\", \"D\", \"9\", \"+\", \"+\"]");
	        System.out.println("Output:   " + calPoints(ops2));
	        System.out.println("Expected: 27");
	        System.out.println();

	        // --- Test Case 3 (single score) ---
	        String[] ops3 = {"1"};
	        System.out.println("Input:    [\"1\"]");
	        System.out.println("Output:   " + calPoints(ops3));
	        System.out.println("Expected: 1");
	        System.out.println();

	        // --- Test Case 4 (negative scores) ---
	        String[] ops4 = {"-3", "D", "+"};
	        System.out.println("Input:    [\"-3\", \"D\", \"+\"]");
	        System.out.println("Output:   " + calPoints(ops4));
	        System.out.println("Expected: -18");
	    }
}
