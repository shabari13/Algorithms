package neetcode250.heap_priorityqueue;

import java.util.PriorityQueue;
/*
 * Design a class to find the kth largest integer in a stream of values, including duplicates. E.g. the 2nd largest from [1, 2, 3, 3] is 3. The stream is not necessarily sorted.

Implement the following methods:

constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.
Example 1:

Input:
["KthLargest", [3, [1, 2, 3, 3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]

Output:
[null, 3, 3, 3, 5, 6]

Explanation:
KthLargest kthLargest = new KthLargest(3, [1, 2, 3, 3]);
kthLargest.add(3);   // return 3
kthLargest.add(5);   // return 3
kthLargest.add(6);   // return 3
kthLargest.add(7);   // return 5
kthLargest.add(8);   // return 6
Constraints:

1 <= k <= 1000
0 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-1000 <= val <= 1000
We use a Min-Heap (Priority Queue) of size k. The heap always stores the k largest elements seen so far. The smallest element in this heap (the root/top) is always the kth largest element. When a new number arrives, if it's larger than the heap's top (the current kth largest), 
we remove the top and insert the new number — keeping the heap size at exactly k. This way, the answer is always sitting right at the top of the heap in O(log k) time per query.
There will always be at least k integers in the stream when you search for the kth integer.
ValueReasonTime — ConstructorO(n log k)Each of the n initial elements calls add(), which does a heap insert/remove in O(log k)Time — add()O(log k)One offer + at most one poll on a heap of max size kSpaceO(k)The heap never holds more than k elements

Complexity Analysis
Time — ConstructorO(n log k)Each of the n initial elements calls add(), which does a heap insert/remove in O(log k)
Time — add()    O(log k)One offer + at most one poll on a heap of max size k
Space O(k) The heap never holds more than k elements
 *
 *
 *
 */
public class KthLargestInStream {
	int k;
	PriorityQueue<Integer> minHeap ;
	public KthLargestInStream(int k, int[] nums) {
		this.k = k;
		this.minHeap = new PriorityQueue<>();
		for(int num : nums) {
			add(num);
		}
	}
	
	public int add(int num) {
		minHeap.offer(num);
		if(minHeap.size() > k) {
			minHeap.poll();
		}
		return minHeap.peek();
	}
	
	   static void runTest(String label, int k, int[] initial, int[] stream) {
	        System.out.println("══════════════════════════════════════════");
	        System.out.println("Test : " + label);
	        System.out.println("k    : " + k);
	        System.out.print  ("Init : [");
	        for (int i = 0; i < initial.length; i++)
	            System.out.print(initial[i] + (i < initial.length - 1 ? ", " : ""));
	        System.out.println("]");
	        System.out.println("──────────────────────────────────────────");

	        KthLargestInStream obj = new KthLargestInStream(k, initial);

	        System.out.printf("%-10s %-20s %-15s%n",
	                          "add(val)", "Heap after add()", "kth Largest");
	        System.out.println("──────────────────────────────────────────");

	        for (int val : stream) {
	            int result = obj.add(val);
	            System.out.printf("add(%-4d) heap=%-20s → %d%n",
	                              val,
	                              obj.minHeap.toString(),
	                              result);
	        }
	        System.out.println();
	    }

	    // ─────────────────────────────────────────────
	    //  main — multiple test cases
	    // ─────────────────────────────────────────────
	    public static void main(String[] args) {

	        // ── Test 1: classic LeetCode example ─────
	        runTest("Classic (k=3)",
	                3,
	                new int[]{4, 5, 8, 2},
	                new int[]{3, 5, 10, 9, 4});

	        // ── Test 2: k = 1 (always track maximum) ─
	        runTest("k=1 (running max)",
	                1,
	                new int[]{},
	                new int[]{5, 3, 9, 2, 7});

	        // ── Test 3: all same values ───────────────
	        runTest("All same (k=2)",
	                2,
	                new int[]{5, 5, 5},
	                new int[]{5, 5});

	        // ── Test 4: descending stream ─────────────
	        runTest("Descending stream (k=3)",
	                3,
	                new int[]{10, 9, 8},
	                new int[]{7, 6, 5, 4});

	        // ── Test 5: large k = 4 ───────────────────
	        runTest("k=4",
	                4,
	                new int[]{1, 2},
	                new int[]{3, 4, 5, 6, 7});
	    }
	
}
