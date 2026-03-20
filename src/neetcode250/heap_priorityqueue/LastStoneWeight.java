package neetcode250.heap_priorityqueue;

import java.util.PriorityQueue;
/*
 * Last Stone Weight
Easy
Topics
Company Tags
Hints
You are given an array of integers stones where stones[i] represents the weight of the ith stone.

We want to run a simulation on the stones as follows:

At each step we choose the two heaviest stones, with weight x and y and smash them togethers
If x == y, both stones are destroyed
If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
Continue the simulation until there is no more than one stone remaining.

Return the weight of the last remaining stone or return 0 if none remain.

Example 1:

Input: stones = [2,3,6,2,4]

Output: 1
Explanation:
We smash 6 and 4 and are left with a 2, so the array becomes [2,3,2,2].
We smash 3 and 2 and are left with a 1, so the array becomes [1,2,2].
We smash 2 and 2, so the array becomes [1].

Example 2:

Input: stones = [1,2]

Output: 1
Constraints:

1 <= stones.length <= 20
1 <= stones[i] <= 100

The Problem
You have a bunch of stones with weights. Each turn, pick the two heaviest stones and smash them together. If they're equal, both are destroyed. If not, the difference survives. Return the last stone's weight (or 0 if none remain).

🧒 Explain Like I'm 5
Imagine you have a bag of rocks of different sizes. Every day, you pick the two biggest rocks and crash them together! If they're the same size, both go poof and disappear. If one is bigger, the bigger rock shrinks by the size of the smaller one, and goes back in the bag. You keep doing this until there's either one rock left or no rocks left. We want to know how heavy that last rock is!

💡 Idea Behind the Solution
We use a Max-Heap (Priority Queue) — a special data structure that always gives us the largest element first. Instead of sorting the array every single time we need the two heaviest stones (which would be slow), the max-heap maintains order automatically. On each step, we poll() the two heaviest stones, compute the difference, and if it's non-zero, push it back into the heap. We repeat until one or zero stones remain.
TimeO(n log n)Each of the n stones triggers at most one add + two poll operations on the heap, each costing O(log n)
 SpaceO(n)The heap stores at most n elements
 */
public class LastStoneWeight {
	 public static int lastStoneWeight(int[] stones) {
		 PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
		 for(int stone : stones) {
			 maxHeap.add(stone);
		 }
		 while(maxHeap.size() > 1) {
			 int stone1 = maxHeap.poll();
			 int stone2 = maxHeap.poll();
			 if(stone1 != stone2) {
				 int diff = Math.abs(stone1 - stone2);
				 maxHeap.offer(diff);
			 }
		 }
		 return maxHeap.isEmpty() ? 0 : maxHeap.poll();
	 }
	 
	  // ─── Helper to print a test case ───────────────────────────────────────────
	    private static void runTest(String label, int[] stones) {
	        System.out.println("=".repeat(50));
	        System.out.print(label + " | Stones: [");
	        for (int i = 0; i < stones.length; i++) {
	            System.out.print(stones[i] + (i < stones.length - 1 ? ", " : ""));
	        }
	        System.out.println("]");
	        System.out.println("Result: " + lastStoneWeight(stones));
	    }

	    public static void main(String[] args) {
	        runTest("Test 1 - LeetCode Example", new int[]{2, 7, 4, 1, 8, 1});
	        runTest("Test 2 - Single Stone",      new int[]{5});
	        runTest("Test 3 - All Equal",         new int[]{3, 3, 3, 3});
	        runTest("Test 4 - Two Stones",        new int[]{10, 4});
	        runTest("Test 5 - Already Sorted",    new int[]{1, 2, 3, 4, 5});
	        runTest("Test 6 - Large Values",      new int[]{100, 99, 50, 25});
	    }
}
