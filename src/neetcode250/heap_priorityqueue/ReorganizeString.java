package neetcode250.heap_priorityqueue;

import java.util.PriorityQueue;

/*
 * You are given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

You can return any possible rearrangement of s or return "" if not posssible.

Example 1:

Input: s = "axyy"

Output: "xyay"
Example 2:

Input: s = "abbccdd"

Output: "abcdbcd"
Example 3:

Input: s = "ccccd"

Output: ""
Constraints:

1 <= s.length <= 500.
s is made up of lowercase English characters.

aaab"" show me the iteration for this with this solution

Here's the complete Java solution for the Reorganize String problem, where the goal is to rearrange characters so no two adjacent characters are the same (return "" if impossible).
The Idea: Think of it like seating people at a table where no two people with the same name can sit next to each other. We always pick the most common character available and place it next, 
but we skip it if the last placed character was the same. We use a Max Heap (Priority Queue) that always gives us the character with the highest remaining count. By always choosing the most 
frequent unused character, we greedily ensure the arrangement is possible whenever it's mathematically feasible (no single character appears more than (n+1)/2 times).
Explain it like I'm 5: Imagine you have a bag of colored Lego bricks — some colors have lots of bricks, some have just one. Your rule is: you can never place two of the same color brick next 
to each other. So you always reach in and grab the color you have the MOST of, place it down, then grab the next most-common color, place it, and so on. If you ever get stuck (the only color left 
is the same as the last one you placed, and there's nothing else), you give up and say "impossible!"

7:06 PM
Here's the full step-by-step iteration for "aaab".

First, let's set the scene. Frequency count: a=3, b=1. The heap starts with (a,3) at the top and (b,1) below it. The critical math: a appears 3 times in a 4-character string —
 that's more than (4+1)/2 = 2.5, so the maximum allowed is 2. With 3 as, it's mathematically impossible to avoid placing two as next to each other. Let's watch exactly how the algorithm discovers this:



The key insight of the impossible case is this: the algorithm never explicitly checks "is this possible?" — it just tries its best and falls short. After Step 3, prev is holding a×1 (still needs to be placed),
 but the heap is empty, meaning there's no other character to place first so a can safely follow. The while loop exits with only 3 characters built out of 4, and the final length check result.length() == s.length() 
 	catches this and returns "".

The mathematical root cause: a appears 3 times in a string of length 4. The rule is that no character can appear more than ⌈n/2⌉ = ⌈4/2⌉ = 2 times. Since 3 > 2, 
there's no valid arrangement — and the algorithm's "prev resting" mechanism naturally gets stuck trying to enforce the separation.

Time & Space Complexity:
ComplexityReason
TimeO(n log k)We do n insertions/polls from the heap, each costing O(log k) where k = number of unique chars (≤26, so effectively O(n log 26) = O(n))
SpaceO(k)The heap holds at most 26 entries (one per letter), plus O(n) for the output string

 */
public class ReorganizeString {
	public static String reorganizeString(String s) {
		int freq[] = new int[26];
		for(Character c : s.toCharArray()) {
			freq[c - 'a']++;
		}
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		for(int i = 0 ; i < freq.length; i++) {
			if(freq[i] > 0) {
				maxHeap.offer(new int[] {freq[i], i + 'a'});
			}
		}
		int[] prev = null;
		StringBuilder sb = new StringBuilder();
		while(!maxHeap.isEmpty()) {
			// Pick the most frequent character available
			int[] curr = maxHeap.poll();
			sb.append((char)curr[1]);
			curr[0]--;
			if(prev != null && prev[0] > 0) {
				maxHeap.offer(prev);
			}
			prev = curr;
		}
		return (sb.toString().length() == s.length()) ? sb.toString() : "";
	}
	
	 public static void main(String[] args) {

	        String[] inputs = {"aab", "aaab", "vvvlo", "a", "aabb", "aaabc"};

	        System.out.println("=== Reorganize String Results ===\n");

	        for (String input : inputs) {
	            String output = reorganizeString(input);
	            String status = output.isEmpty() ? "IMPOSSIBLE" : "\"" + output + "\"";
	            System.out.printf("Input: %-10s → Output: %s%n", "\"" + input + "\"", status);
	        }
	    }
}
