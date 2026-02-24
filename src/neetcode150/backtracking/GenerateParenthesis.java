package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of parentheses.

Example 1:

Input: n = 1

Output: ["()"]
Example 2:

Input: n = 3

Output: ["((()))","(()())","(())()","()(())","()()()"]
You may return the answer in any order.

Constraints:

1 <= n <= 7

🌟 Idea Behind the Solution (Normal Explanation)

This problem is about generating all valid combinations of n pairs of parentheses.

A valid parentheses string means:

Every ( has a matching )

At no point should ) be more than (

We solve this using Backtracking.

At each step:

We can add ( if we still have some left.

We can add ) only if it won’t break the rule (i.e., number of ) used < number of ( used).

We build the string step by step and stop when its length becomes 2 * n.

👶 Explain Like You're 5 Years Old

Imagine you have:

Some opening doors (

Some closing doors )

Rule:

You cannot close a door unless you opened one before.

You must use all your doors.

So:

First, open doors.

Close them carefully.

Never close more doors than you opened.

We try all safe ways of opening and closing doors until we use them all.

That’s it! 🎉

Time Complexity:
O(4^n / √n)

Because we generate all valid combinations.

💾 Space Complexity

Recursion stack depth = O(n)

Result storage = O(C(n))

So overall:

O(4^n / √n)
ComplexityExplanationTimeO(4ⁿ / √n)
This is the n-th Catalan number × n (cost to copy each string). 
The Catalan number Cₙ = C(2n,n)/(n+1) grows as 4ⁿ/(n^(3/2) * √π)SpaceO(n)
The recursion depth is at most 2n (the length of the string). 
The current StringBuilder is also max size 2n. Result storage is extra O(4ⁿ/√n * n) but that's output space, not auxiliary space.
 */
public class GenerateParenthesis {
	
	public static List<String> generateParenthesis(int n) {
		List<String> result  = new ArrayList<>();
		backtrack(n,"", 0, 0, result);
		return result;
		
	}
	
	public static void backtrack(int n, String current, int open, int close, List<String> result) {
		if(current.length() == 2 * n)
			result.add(current);
		if(open < n) {
			backtrack(n, current + "(", open+1, close, result);
		}
		
		if(close < open) {	
			backtrack(n, current + ")", open, close+1, result);
		}
		
	}
	public static void main(String[] args) {
        int[] testInputs = {1, 2, 3};

        for (int n : testInputs) {
            System.out.println("Input: n = " + n);
            List<String> output = generateParenthesis(n);
            System.out.println("Output: " + output);
            System.out.println("-------------------------");
        }
    }

}
