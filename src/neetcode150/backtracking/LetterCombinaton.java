package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given a string digits made up of digits from 2 through 9 inclusive.

Each digit (not including 1) is mapped to a set of characters as shown below:

A digit could represent any one of the characters it maps to.

Return all possible letter combinations that digits could represent. You may return the answer in any order.



Example 1:

Input: digits = "34"

Output: ["dg","dh","di","eg","eh","ei","fg","fh","fi"]
Example 2:

Input: digits = ""

Output: []
Constraints:

0 <= digits.length <= 4
2 <= digits[i] <= 9

Time Complexity

Let:

n = number of digits

Each digit has up to 4 letters (like 7 and 9)

Worst case:

4^n combinations

So:

✅ Time Complexity = O(4^n)
💾 Space Complexity

Recursion stack depth = n

Result stores up to 4^n strings

✅ Space Complexity = O(n + 4^n)

(or simply O(4^n))

Idea Behind the Solution (Normal Explanation)

Each digit maps to some letters (like on old Nokia phones). For example:

2 → abc

3 → def

4 → ghi

5 → jkl

6 → mno

7 → pqrs

8 → tuv

9 → wxyz

If the input is "23":

2 gives us a, b, c

3 gives us d, e, f

We combine each letter from digit 2 with each letter from digit 3.

So:

a + d, e, f

b + d, e, f

c + d, e, f

Result:

ad, ae, af, bd, be, bf, cd, ce, cf

This is a Backtracking (Recursion) problem:

We build combinations step by step.

When the combination length equals input length, we store it.

👶 Explaining Like You’re 5 Years Old

Imagine you have number buttons.

When you press:

Button 2 → it gives you stickers: 🅰️ 🅱️ 🅲

Button 3 → it gives you stickers: 🅳 🅴 🅵

Now if you press 2 and 3, you must:

Pick one sticker from 2

Pick one sticker from 3

So you try:

a + d

a + e

a + f

b + d

b + e

b + f

c + d

c + e

c + f

You try ALL possibilities. That’s it 😊

Basiclaly have a mapping string array with letters mappend to each index. for ex: mapping[2] will be "abc".
then take each character from the string, get the mapped letters to that digit, traveers through letters, append each letter to a strign builder and bactrack it .
 */
public class LetterCombinaton {
	public static List<String> letterCombinations(String s) {
		List<String> result = new ArrayList<>();
		String[] mapping = { "",
							"",
							"abc",
							"def",
							"ghi",
							"jkl",
							"mno",
							"pqrs",
							"tuv",
							"wxyz"};
		backtrack(result, mapping, s, 0, new StringBuilder());
		return result;
	
	}
	public static void backtrack(List<String> result, String[] mapping, String s, int index, StringBuilder current) {
		
		if(index == s.length()) {
			result.add(current.toString());
			return;
		}
		char digit = s.charAt(index);
		String letters = mapping[digit - '0'];
		for(int i = 0; i < letters.length(); i++) {
			current.append(letters.charAt(i));
			backtrack(result, mapping, s, index+1, current);
			current.deleteCharAt(current.length() - 1);
		}
		
	}
	
	 public static void main(String[] args) {

	        String[] testInputs = {"23", "7", "234", ""};

	        for (String input : testInputs) {
	            System.out.println("Input: " + input);
	            List<String> output = letterCombinations(input);
	            System.out.println("Output: " + output);
	            System.out.println("---------------------------");
	        }
	    }

}
