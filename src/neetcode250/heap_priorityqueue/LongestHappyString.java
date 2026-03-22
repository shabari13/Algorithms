package neetcode250.heap_priorityqueue;

import java.util.PriorityQueue;

/*
 * A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
You are given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: a = 3, b = 4, c = 2

Output: "bababcabc"
Example 2:

Input: a = 0, b = 1, c = 5

Output: "ccbcc"
Constraints:

0 <= a, b, c <= 100
a + b + c > 0


💡 The Idea Behind the Solution
The strategy is greedy with a max-heap. At every step, we want to use whichever character has the most remaining supply — because using the most-frequent character first gives us the best chance of fitting all characters into a long valid string. There's only one hard rule we must obey: we can never place the same character three times in a row. So if the top character would form a triple, we temporarily skip it and use the second-most-frequent character instead, then put the top one back. We keep doing this until no valid move exists.

👶 Explain Like I'm 5
Imagine you have a big bag of 7 blue candies, 1 red candy, and 1 green candy. You're making a candy necklace, but there's a rule: you can never put 3 blue candies next to each other (it would be too sweet!). So what do you do? You mostly put blue candies, but every time you've used blue twice in a row, you quickly sneak in a red or green candy to "break the streak." Then you go back to blue again. You always use whatever candy you have the MOST of — unless the rule stops you, in which case you use the next most common one. You stop when you get stuck (only one colour left and it would make 3 in a row).

Time Complexity: O(N log 3) = O(N)
Where N = a + b + c (the total characters appended). Each iteration we do at most 2 heap operations (poll + offer). The heap has at most 3 elements at all times (one per character), so each poll/offer is O(log 3) — effectively O(1). Total: O(N).
Space Complexity: O(N)
The heap holds at most 3 elements → O(1) extra space. The StringBuilder holds the result string of length up to N → O(N) for the output. If we don't count the output itself, space is O(1).

 */
public class LongestHappyString {
	
	 // =========================================================
    // CORE SOLUTION METHOD
    // =========================================================
 
    /**
     * Returns the longest "happy" string using at most 'a' 'a's, 'b' 'b's, 'c' 'c's.
     * A happy string never contains "aaa", "bbb", or "ccc" as a substring.
     *
     * IDEA:
     * Use a max-heap (priority queue) ordered by remaining count of each character.
     * At every step, greedily pick the character with the highest remaining count.
     * If the last TWO characters of the result are already the same as the top
     * character, we MUST pick the second-most-frequent character instead
     * (to avoid a triple). If no valid character exists, we stop.
     *
     * @param a  count of 'a' available
     * @param b  count of 'b' available
     * @param c  count of 'c' available
     * @return   the longest valid happy string
     */
	public static String longestDiverseString(int a, int b, int c) {
		 // Step 1 ─ Build a max-heap of [count, character].
        // Each element is an int[] { count, charCode }
        // Sorted descending by count so the most-frequent char is always on top.
		 PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
		            (x, y) -> y[0] - x[0]   // higher count  →  higher priority
		        );
		// Only add characters that actually have a non-zero count.
        if(a > 0) maxHeap.offer(new int[]{a, 'a'});
        if(b > 0) maxHeap.offer(new int[] {b, 'b'});
        if(c > 0) maxHeap.offer(new int[] {c, 'c'});
        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()) {
        	int first[]  = maxHeap.poll();
        	 // Check whether appending 'first' would create "aaa" / "bbb" / "ccc".
        	boolean wouldTriple = (result.length() >= 2)
        				&& result.charAt(result.length()-1) == (char)first[1]
        				&& result.charAt(result.length()-2) == (char)first[1];
        	if(!wouldTriple) {
        		result.append((char)first[1]);
        		first[0]--;
        		if(first[0] > 0) {
        			maxHeap.offer(first);
        		}
        	} else {
        		  // Appending 'first' would cause a triple → try the second-best char.
        		if(!maxHeap.isEmpty()) {
        			int[] second = maxHeap.poll();
        			result.append((char)second[1]);
        			second[0]--;
            		if(second[0] > 0) {
            			maxHeap.offer(second);
            		}
            		   // Put 'first' back — we didn't use it this round.
            		maxHeap.offer(first);
        		}
        	}
        }
        return result.toString();
    }
	
	public static void main(String[] args) {
		 
        System.out.println("=========================================");
        System.out.println("   Longest Happy String — Test Cases");
        System.out.println("=========================================\n");
 
        // ── Test 1 ──────────────────────────────────────────
        int a1 = 1, b1 = 1, c1 = 7;
        String r1 = longestDiverseString(a1, b1, c1);
        System.out.printf("Input : a=%d, b=%d, c=%d%n", a1, b1, c1);
        System.out.printf("Output: \"%s\"  (length %d)%n%n", r1, r1.length());
 
        // ── Test 2 ──────────────────────────────────────────
        int a2 = 2, b2 = 2, c2 = 1;
        String r2 = longestDiverseString(a2, b2, c2);
        System.out.printf("Input : a=%d, b=%d, c=%d%n", a2, b2, c2);
        System.out.printf("Output: \"%s\"  (length %d)%n%n", r2, r2.length());
 
        // ── Test 3 ──────────────────────────────────────────
        int a3 = 7, b3 = 1, c3 = 0;
        String r3 = longestDiverseString(a3, b3, c3);
        System.out.printf("Input : a=%d, b=%d, c=%d%n", a3, b3, c3);
        System.out.printf("Output: \"%s\"  (length %d)%n%n", r3, r3.length());
 
        // ── Test 4 ──────────────────────────────────────────
        int a4 = 0, b4 = 0, c4 = 5;
        String r4 = longestDiverseString(a4, b4, c4);
        System.out.printf("Input : a=%d, b=%d, c=%d%n", a4, b4, c4);
        System.out.printf("Output: \"%s\"  (length %d)%n%n", r4, r4.length());
 
        // ── Test 5 ──────────────────────────────────────────
        int a5 = 3, b5 = 3, c5 = 3;
        String r5 = longestDiverseString(a5, b5, c5);
        System.out.printf("Input : a=%d, b=%d, c=%d%n", a5, b5, c5);
        System.out.printf("Output: \"%s\"  (length %d)%n%n", r5, r5.length());
    }
}
