package neetcode150.heap.priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * You are given an array of CPU tasks tasks, where tasks[i] is an uppercase english character from A to Z. You are also given an integer n.

Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.

The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.

Return the minimum number of CPU cycles required to complete all tasks.

Example 1:

Input: tasks = ["X","X","Y","Y"], n = 2

Output: 5
Explanation: A possible sequence is: X -> Y -> idle -> X -> Y.

Example 2:

Input: tasks = ["A","A","A","B","C"], n = 3

Output: 9
Explanation: A possible sequence is: A -> B -> C -> Idle -> A -> Idle -> Idle -> Idle -> A.

Constraints:

1 <= tasks.length <= 1000
0 <= n <= 100
Time and Space Complexity
Time Complexity: O(n log k)

n = total number of tasks
k = number of unique task types (k ≤ 26)

Breakdown:

Counting frequencies: O(n) - iterate through all tasks
Building heap: O(k) - add k elements to heap
Processing cycles:

Total tasks processed: n
Each task: one poll + one offer operation = O(log k)
Total: O(n log k)


Overall: O(n log k)
In practice: O(n) since k ≤ 26 (constant)

Space Complexity: O(k)

HashMap: O(k) where k ≤ 26
PriorityQueue: O(k) where k ≤ 26
Temporary list: O(k) where k ≤ 26
Overall: O(k) = O(1) since k is bounded by 26

The PriorityQueue approach simulates the actual scheduling process cycle by cycle. We use a max heap to always pick the most frequent remaining task first (greedy strategy). In each cycle of length (n+1), we schedule up to (n+1) different tasks. After scheduling a task, we decrease its count and put it back in the heap if it still has remaining instances. This continues until all tasks are scheduled. The key insight is that by always choosing the most frequent task first, we minimize idle time because we're dealing with the most constrained tasks early. If the heap becomes empty after a cycle, we know it's the last cycle and only count actual tasks scheduled (not idle slots).

## Explain Like I'm 5

Imagine you're playing with letter blocks and you have a rule: **after you use an 'A' block, you must use 2 other different blocks before you can use 'A' again**.

Think of it like a game:

1. **Sort your blocks in piles**: Count how many A's, B's, C's you have
2. **Use a magic bag**: This bag always gives you the block you have MOST of (it's magical!)
3. **Play in rounds**: Each round, you try to pick 3 blocks (because cooldown is 2, so n+1=3)
   - First pick: the one you have most of
   - Second pick: the next one you have most of  
   - Third pick: the next one
   - If you run out of blocks to pick, you wait (that's "idle time")
4. **Put leftovers back**: If a block still has more copies, put it back in the magic bag
5. **Keep playing rounds** until the magic bag is empty

Count all your picks and waits - that's your answer!

 */
public class TaskScheduler {
	
	public int minTaskSchedule(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
		for(char c:tasks) {
			map.put(c, map.getOrDefault(c, 0)+1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
		pq.addAll(map.values());
		int intervals = 0;
		while(!pq.isEmpty()) {
			List<Integer> temp = new ArrayList<>();
			int cycleTaskCount = 0;
			for(int i = 0; i <=n; i++) {
				if(!pq.isEmpty()) {
					int freq = pq.poll();
					freq--;
					if(freq > 0)
						temp.add(freq);
				}
			}
			 for (int freq : temp) {
	                pq.offer(freq);
	         }
			 if (pq.isEmpty()) {
	                intervals += cycleTaskCount;
	            } else {
	                intervals += (n + 1);
	            }
	        }
	        
	        return intervals;
		}
		

	
	

}
