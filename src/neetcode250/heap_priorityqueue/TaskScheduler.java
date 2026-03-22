package neetcode250.heap_priorityqueue;

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

Overall: O(n log k)
In practice: O(n) since k ≤ 26 (constant)

Space Complexity: O(k)

HashMap: O(k) where k ≤ 26
PriorityQueue: O(k) where k ≤ 26
Temporary list: O(k) where k ≤ 26
Overall: O(k) = O(1) since k is bounded by 26

Counting frequencies — a single for loop over all n tasks. This is straightforwardly O(n).
Building the heap — you add at most k entries (one per unique task letter, k ≤ 26). Each offer is O(log k), so this whole step is O(k log k). Since k is capped at 26, this is a constant and doesn't grow with input.
Processing cycles — this is the main loop. Across ALL rounds combined, you poll each task exactly once and offer it back at most once. So the total number of heap operations equals the total number of tasks — which is n. Each poll/offer costs O(log k). Since k ≤ 26, log k ≤ log 26 ≈ 4.7, which is a fixed constant. So the whole loop is O(n × constant) = O(n).
The formally correct answer is O(n log k), but since k is bounded by 26 (the alphabet size), this collapses to O(n) in practice. Both answers are acceptable — O(n log k) is more precise, O(n) is the practical reality.


Space Complexity: O(k) = O(1)
Every data structure you allocate holds at most k entries:

HashMap — at most 26 letter→count pairs.
PriorityQueue — at most 26 frequency values.
temp list — at most 26 leftover frequencies per cycle (it's cleared and rebuilt each round).





## The 5-year-old explanation

Imagine you have a big pile of colorful blocks. Each color is a task:
```
3 red blocks (A),  1 blue block (B),  1 green block (C)
Cooldown n = 3  →  you must wait 3 turns before reusing the same color
```

You have a magic bag that always hands you whichever color you have the **most of**. You play the game in **rounds**, and each round has exactly `n+1 = 4` turns.

---

### Round 1 — you have 4 turns to fill
```
Turn 1: Magic bag gives red (most blocks). Use it. Red count: 3→2
Turn 2: Magic bag gives blue (next most). Use it. Blue count: 1→0 (gone!)
Turn 3: Magic bag gives green. Use it.    Green count: 1→0 (gone!)
Turn 4: Bag is empty — you sit and wait.  IDLE.
```

The round used `4` slots (n+1). After the round, put leftover blocks back: red (count=2) goes back in the bag.

---

### Round 2 — you have 4 turns again
```
Turn 1: Magic bag gives red. Use it. Red count: 2→1
Turn 2: Bag is empty — IDLE.
Turn 3: Bag is empty — IDLE.
Turn 4: Bag is empty — IDLE.
```

Wait — after this round the bag IS empty. So this is the **last round**. You only count the **real turns** used (just 1), not the idle ones at the end. That's what the `if (pq.isEmpty())` check does — it says "was that the final round? if yes, don't count the empty waiting slots at the end."

---

### Total count
```
Round 1:  4 slots  (bag still has blocks → count full n+1)
Round 2:  1 slot   (bag empty after → count only real tasks)
Total  :  5  ✓

Now reading the actual code line by line
java// Step 1: Count how many of each block you have
Map<Character, Integer> map = new HashMap<>();
for (char c : tasks) {
    map.put(c, map.getOrDefault(c, 0) + 1);
}
// e.g. {A=3, B=1, C=1}
java// Step 2: Put all the COUNTS (not letters) into the magic bag
// The bag always gives you the biggest number first (max-heap)
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
pq.addAll(map.values());
// pq contains: [3, 1, 1]
javaint intervals = 0;   // our running total of CPU cycles used

while (!pq.isEmpty()) {   // keep playing rounds until no blocks left
java    List<Integer> temp = new ArrayList<>();  // blocks to put back after the round
    int cycleTaskCount = 0;                  // how many REAL tasks we did this round

    // Play one round: exactly (n+1) turns
    for (int i = 0; i <= n; i++) {
        if (!pq.isEmpty()) {
            int freq = pq.poll();   // grab the most-frequent block from the bag
            cycleTaskCount++;       // we actually did a real task this turn
            freq--;                 // used one block of this color
            if (freq > 0)
                temp.add(freq);     // still have leftovers? save for later
        }
        // if bag is empty, this turn is IDLE — we do nothing, don't increment
    }
java    // Put the leftover blocks back into the magic bag for next round
    for (int freq : temp) {
        pq.offer(freq);
    }
java    // Was this the LAST round?
    if (pq.isEmpty()) {
        // Yes — only count the real tasks, skip trailing idles
        // e.g. round had [A, idle, idle, idle] → only count 1
        intervals += cycleTaskCount;
    } else {
        // No — more rounds coming, count the full slot width including idles
        // e.g. [A, B, C, idle] → count all 4 = (n+1)
        intervals += (n + 1);
    }
}

return intervals;
 */
public class TaskScheduler {
	
	public int minTaskScheduler(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
		for(Character c: tasks) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
		pq.addAll(map.values());
		int intervals = 0;
		while(!pq.isEmpty()) {
			int currentTask = 0;
			List<Integer> temp = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				if(!pq.isEmpty()) {
					int freq = pq.poll();
					currentTask++;
					freq--;
					if(freq > 0) {
						temp.add(freq);
					}
				}
			}
			for(int freq : temp) {
				pq.offer(freq);
			}
			if(pq.isEmpty()) {
				intervals += currentTask;
			} else {
				intervals += n+1;
			}
			
		}
		return intervals;
	}

}
