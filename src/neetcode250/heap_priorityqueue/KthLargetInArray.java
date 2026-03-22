package neetcode250.heap_priorityqueue;

import java.util.PriorityQueue;

/*
 * Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.

By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.

Follow-up: Can you solve it without sorting?

Example 1:

Input: nums = [2,3,1,5,4], k = 2

Output: 4
Example 2:

Input: nums = [2,3,1,1,5,5,4], k = 3

Output: 4
Constraints:

1 <= k <= nums.length <= 10000
-1000 <= nums[i] <= 1000


    /**
     * Finds the kth largest element in an array using a Min-Heap.
     *
     * Approach: Keep a min-heap of size k.
     * - The heap always holds the k largest elements seen so far.
     * - The root (peek) of the heap is the SMALLEST among those k elements,
     *   which is exactly the kth largest overall.
     *
     * Why a MIN-heap and not a MAX-heap?
     * - A max-heap would give you the largest element at the top, but you'd
     *   need to pop k times to reach the kth largest — O(k log n).
     * - A min-heap of size k keeps the "weakest survivor" at the top,
     *   making replacement decisions in O(log k) per element — much faster!
     *
     *
 Time Complexity: O(n log k)

We iterate through all n elements — that's O(n).
For each element, we do at most one offer() and one poll() on a heap of size k. Each heap operation costs O(log k).
Total: O(n log k). Since k ≤ n, this is always at most O(n log n) and often much faster (e.g. finding 3rd largest in a million elements: O(n log 3) ≈ O(n)!).

Space Complexity: O(k)

The heap holds at most k+1 elements at any time (we add one, then immediately remove if over k).
So we use O(k) extra space, regardless of how large n is.



 */
public class KthLargetInArray {
	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap  = new PriorityQueue<>();
		for(int num : nums) {
			minHeap.add(num);
			if(minHeap.size() > k) {
				minHeap.poll();
			}
		}
		return minHeap.poll();
	}
	 public static void main(String[] args) {

	        // ─── Test Case 1 ──────────────────────────────────────────────────────────
	        // Array: [3, 2, 1, 5, 6, 4], k = 2
	        // Sorted descending: [6, 5, 4, 3, 2, 1]
	        // 2nd largest = 5
	        int[] nums1 = {3, 2, 1, 5, 6, 4};
	        int k1 = 2;
	        System.out.println("Test 1 → Array: [3,2,1,5,6,4], k=" + k1);
	        System.out.println("         Expected: 5");
	        System.out.println("         Got:      " + findKthLargest(nums1, k1));
	        System.out.println();

	        // ─── Test Case 2 ──────────────────────────────────────────────────────────
	        // Array: [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4
	        // Sorted descending: [6, 5, 5, 4, 3, 3, 2, 2, 1]
	        // 4th largest = 4
	        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
	        int k2 = 4;
	        System.out.println("Test 2 → Array: [3,2,3,1,2,4,5,5,6], k=" + k2);
	        System.out.println("         Expected: 4");
	        System.out.println("         Got:      " + findKthLargest(nums2, k2));
	        System.out.println();

	        // ─── Test Case 3 (k = 1, just the max) ───────────────────────────────────
	        int[] nums3 = {7, 10, 4, 3, 20, 15};
	        int k3 = 1;
	        System.out.println("Test 3 → Array: [7,10,4,3,20,15], k=" + k3);
	        System.out.println("         Expected: 20");
	        System.out.println("         Got:      " + findKthLargest(nums3, k3));
	        System.out.println();

	        // ─── Test Case 4 (k = array length, the minimum) ─────────────────────────
	        int[] nums4 = {1, 2, 3, 4, 5};
	        int k4 = 5;
	        System.out.println("Test 4 → Array: [1,2,3,4,5], k=" + k4);
	        System.out.println("         Expected: 1");
	        System.out.println("         Got:      " + findKthLargest(nums4, k4));
	        System.out.println();

	        // ─── Test Case 5 (negative numbers) ──────────────────────────────────────
	        int[] nums5 = {-1, -3, -5, -2, -4};
	        int k5 = 2;
	        System.out.println("Test 5 → Array: [-1,-3,-5,-2,-4], k=" + k5);
	        System.out.println("         Expected: -2");
	        System.out.println("         Got:      " + findKthLargest(nums5, k5));
	    }
}
