package neetcode150.greedy;
/*
 * You are given a string s which contains only three types of characters: '(', ')' and '*'.

Return true if s is valid, otherwise return false.

A string is valid if it follows all of the following rules:

Every left parenthesis '(' must have a corresponding right parenthesis ')'.
Every right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
A '*' could be treated as a right parenthesis ')' character or a left parenthesis '(' character, or as an empty string "".
Example 1:

Input: s = "((**)"

Output: true
Explanation: One of the '*' could be a ')' and the other could be an empty string.

Example 2:

Input: s = "(((*)"

Output: false
Explanation: The string is not valid because there is an extra '(' at the beginning, regardless of the extra '*'.

Constraints:

1 <= s.length <= 100
The Key Question: What does * do to our range?
When we see a *, we don't know what it is yet. It could be three different things:

* = ( → adds an open parenthesis
* = ) → closes an open parenthesis
* = empty → disappears, does nothing

Since we're tracking a range of possibilities, we need to consider both extremes:

📊 Visual Explanation
Imagine we currently have minOpen = 2 and maxOpen = 4:
Current range of open parens: [2, 3, 4]
Now we encounter a *. What happens?
For minOpen (pessimistic case):
We assume the * works against us (makes it harder to balance):

If * = ) → closes one open paren → minOpen - 1
If * = empty → no change → minOpen + 0
If * = ( → adds one open paren → minOpen + 1

We choose the WORST case (the one that makes balancing harder) = treat it as ) or empty
→ So minOpen decreases by 1
minOpen: 2 → 1
For maxOpen (optimistic case):
We assume the * works with us (makes it easier to balance):

If * = ( → adds one open paren → maxOpen + 1
If * = empty → no change → maxOpen + 0
If * = ) → closes one open paren → maxOpen - 1

We choose the BEST case (the one that gives us most flexibility) = treat it as (
→ So maxOpen increases by 1
maxOpen: 4 → 5
After seeing *:
New range: [1, 2, 3, 4, 5]

🎯 Why This Makes Sense
Think of it this way:

minOpen = "In the worst case scenario, how many unmatched ( do I have?"

We want this to be as small as possible (ideally 0)
So we treat * as helpful (closing or disappearing) → decrease by 1


maxOpen = "In the best case scenario, how many unmatched ( could I have?"

We want this to be as large as possible (more flexibility)
So we treat * as opening → increase by 1
Time Complexity: O(n)
We make a single pass through the string, processing each character exactly once with O(1) operations per character.
Space Complexity: O(1)
We only use two integer variables (minOpen and maxOpen) regardless of input size. No additional data structures are needed.
keep track of low ad high .low is minimum no of doors open
high is maximum no of doors open
if '(' increase both low and high
if ')' decrease both low and high
if '*' then get the worse case of low (low--)
get the beast case for high (high++)
then check if low == 0;
 */
public class ValidParenthesisString {
	
	public boolean isValid(String s) {
		int low = 0;
		int high = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				low++;
				high++;
			} else if(s.charAt(i) == ')') {
				low--;
				high--;
			} else {
				low--;
				high++;
			}
			if(high < 0) {
				return false;
			}
			if (low < 0)
				low = 0;
		}
		return low == 0;
	}

}
