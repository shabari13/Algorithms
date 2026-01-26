package blind75.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2

Output: [1,2]

Example 2:

Input: nums = [1], k = 1

Output: [1]

Example 3:

Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2

Output: [1,2]

 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * Max Heap Approach - The Prize Ceremony 🏆
Imagine all kids line up by height (tallest first), and you pick the first k kids.
For [1,1,1,2,2,3], k=2:
After counting: {1→3, 2→2, 3→1}
Max Heap (biggest on top):

Add all: Heap = [1(3), 2(2), 3(1)] - arranged with biggest frequency at top
Pop k=2 times:

Pop #1: get candy #1 (freq=3)
Pop #2: get candy #2 (freq=2)



Final result: [1, 2]

Time Complexity: O(n log m) where m = unique elements

Count frequencies: O(n)
Add all m elements to heap: O(m log m)
Extract k elements: O(k log m)
Total: O(n log m) ← Worse than min heap when m is large!


Space Complexity: O(n + m)

HashMap: O(n)
Max Heap: O(m) ← Stores ALL unique elements
Total: O(n)
 */

public class TopKFrequent {
	
	public int[] topKFrequentElements(int[] nums, int k) {
		
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		
		for(int num: nums) {		
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);		
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> 
		frequencyMap.get(b) - frequencyMap.get(a));
		
		maxHeap.addAll(frequencyMap.keySet());
		
		int[] result = new int[k];
		
		for(int i = 0; i < k ; i++) {
			result[i] = maxHeap.poll();
			
		}
		return result;
		
		
	}

}
