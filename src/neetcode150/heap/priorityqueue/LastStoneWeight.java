package neetcode150.heap.priorityqueue;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * You are given an array of integers stones where stones[i] represents the weight of the ith stone.

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

Time Complexity: O(n log n)

Adding n stones to the heap: O(n log n) - each insertion takes O(log n)
Smashing stones: In the worst case, we perform n-1 smash operations
Each smash involves 2 polls and potentially 1 offer: O(log n) each
Total for smashing: O(n log n)
Overall: O(n log n)

Space Complexity: O(n)

The max heap stores all n stones initially
In the worst case, the heap maintains O(n) elements
No additional data structures used that scale with input size
Overall: O(n)
 */
public class LastStoneWeight {
	
	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int stone : stones) {
			maxHeap.offer(stone);
		}
		while(maxHeap.size() > 1) {
			int heaviestStone = maxHeap.poll();
			int secondHeaviest = maxHeap.poll();
			
			if(heaviestStone > secondHeaviest) {
			   int diff = heaviestStone - secondHeaviest;
			   maxHeap.add(diff);
			}
		}
		return maxHeap.isEmpty() ? 0 : maxHeap.peek();
		
		
	}
	
	public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        
        // Test Case 1
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println("Input: [2, 7, 4, 1, 8, 1]");
        System.out.println("Output: " + solution.lastStoneWeight(stones1));
        System.out.println();
        
        // Test Case 2
        int[] stones2 = {1};
        System.out.println("Input: [1]");
        System.out.println("Output: " + solution.lastStoneWeight(stones2));
        System.out.println();
        
        // Test Case 3
        int[] stones3 = {2, 2};
        System.out.println("Input: [2, 2]");
        System.out.println("Output: " + solution.lastStoneWeight(stones3));
        System.out.println();
        
        // Test Case 4
        int[] stones4 = {3, 7, 8};
        System.out.println("Input: [3, 7, 8]");
        System.out.println("Output: " + solution.lastStoneWeight(stones4));
        System.out.println();
        
        // Test Case 5
        int[] stones5 = {1, 3, 5, 7, 9};
        System.out.println("Input: [1, 3, 5, 7, 9]");
        System.out.println("Output: " + solution.lastStoneWeight(stones5));
    }

}
