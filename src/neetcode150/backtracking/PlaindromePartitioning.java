package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string s, split s into substrings where every substring is a palindrome. Return all possible lists of palindromic substrings.

You may return the solution in any order.

Example 1:

Input: s = "aab"

Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"

Output: [["a"]]
Constraints:

1 <= s.length <= 20
s contains only lowercase English letters.
Time Complexity

Worst case:
If string is all same characters like "aaaaa"

Every substring is palindrome.

We explore all possible partitions.

Total partitions = 2^(n-1)

Palindrome check takes O(n).

So total complexity:

O(n * 2^n)
💾 Space Complexity

Recursion stack → O(n)

Storing partitions → O(n * 2^n)

So overall:

O(n * 2^n)
Idea Behind the Solution (Normal Explanation)

We solve this using Backtracking (DFS).

Start from index 0.

Try every possible substring starting from that index.

If the substring is a palindrome:

Add it to the current list.

Recursively solve for the remaining string.

If we reach the end of the string:

Add the current partition to the final result.

Backtrack (remove last added substring) and try other possibilities.

It works like exploring a decision tree:

At each position, we decide where to cut the string.

Only valid palindrome cuts are allowed.
 */
public class PlaindromePartitioning {
	
	public static List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		backtrack(s, 0, new ArrayList<>(), result);
		return result;
	}
	
	public static void backtrack(String s, int start, List<String> current, List<List<String>> result) {
		if(start == s.length()) {
			result.add(new ArrayList<>(current));
		}
		for(int end = start; end < s.length(); end++) {
			if(isPalindrome(s, start, end)) {
				current.add(s.substring(start, end + 1));
				backtrack(s, end + 1, current, result);
				current.remove(current.size() - 1);
			}
			
		}
		
		
	}
	public static boolean isPalindrome(String s, int left, int right) {
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	 public static void main(String[] args) {

	        String[] testCases = {"aab", "a", "racecar", "abba"};

	        for (String s : testCases) {
	            System.out.println("Input: " + s);
	            List<List<String>> result = partition(s);
	            System.out.println("Output:");
	            for (List<String> partition : result) {
	                System.out.println(partition);
	            }
	            System.out.println("------------");
	        }
	    }

}
