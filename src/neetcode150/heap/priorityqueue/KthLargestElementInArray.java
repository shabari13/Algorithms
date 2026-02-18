package neetcode150.heap.priorityqueue;

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
The solution uses a **Min Heap** (priority queue) of size K to efficiently find the Kth largest element. The key insight is that if we maintain a heap containing the K largest elements we've seen so far, the smallest element in that heap will be exactly the Kth largest element overall. We iterate through the array, and whenever we find an element larger than the smallest in our heap, we remove the smallest and add the new element. This ensures our heap always contains the K largest elements, and the root (minimum) of this heap is our answer.

 */
public class KthLargestElementInArray {
	
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap  = new PriorityQueue<>();
		for(int num:nums) {
			minHeap.offer(num);
			if(minHeap.size() > k)
				minHeap.poll();
			
		}
		return minHeap.peek();
	}
	public static void main(String[] args) {
	KthLargestElementInArray solution = new KthLargestElementInArray();
     
     // Test Case 1
     int[] nums1 = {3, 2, 1, 5, 6, 4};
     int k1 = 2;
     System.out.println("Array: [3, 2, 1, 5, 6, 4]");
     System.out.println("K = " + k1);
     System.out.println("The " + k1 + "nd largest element is: " + solution.findKthLargest(nums1, k1));
     System.out.println();
     
     // Test Case 2
     int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
     int k2 = 4;
     System.out.println("Array: [3, 2, 3, 1, 2, 4, 5, 5, 6]");
     System.out.println("K = " + k2);
     System.out.println("The " + k2 + "th largest element is: " + solution.findKthLargest(nums2, k2));
     System.out.println();
     
     // Test Case 3
     int[] nums3 = {7, 10, 4, 3, 20, 15};
     int k3 = 3;
     System.out.println("Array: [7, 10, 4, 3, 20, 15]");
     System.out.println("K = " + k3);
     System.out.println("The " + k3 + "rd largest element is: " + solution.findKthLargest(nums3, k3));
     System.out.println();
     
     // Test Case 4
     int[] nums4 = {1};
     int k4 = 1;
     System.out.println("Array: [1]");
     System.out.println("K = " + k4);
     System.out.println("The " + k4 + "st largest element is: " + solution.findKthLargest(nums4, k4));
 }

}
