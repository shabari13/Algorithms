package neetcode250.arrays_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
Example 2:

Input: nums = [7,7], k = 1

Output: [7]
Constraints:

1 <= nums.length <= 10^4.
-1000 <= nums[i] <= 1000
1 <= k <= number of distinct elements in nums.

ApproachTimeSpaceBest whenMin-HeapO(n log k) ✅O(n + k)k is smallMax-HeapO(n log n)O(n)k is close to n

you can even use minHeap where we just remove elements higher than k.
 */
public class TopKFrequentElements {
	
	public static int[] topKFrequent(int[] nums, int k) {
		
		if(nums.length == 0) {
			return new int[] {};
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));

		maxHeap.addAll(map.keySet());
		
		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i] = maxHeap.poll();
		}
		return result;
		
	}
	
	 public static void main(String[] args) {
	        // Test 1
	        int[] nums1 = {1, 1, 1, 2, 2, 3};
	        int k1 = 2;
	        System.out.println("Input : " + Arrays.toString(nums1) + ", k=" + k1);
	        System.out.println("Output: " + Arrays.toString(topKFrequent(nums1, k1)));
	        // → [1, 2]
	        System.out.println();
	        // Test 2
	        int[] nums2 = {4, 4, 4, 6, 6, 7, 7, 7, 7, 9};
	        int k2 = 3;
	        System.out.println("Input : " + Arrays.toString(nums2) + ", k=" + k2);
	        System.out.println("Output: " + Arrays.toString(topKFrequent(nums2, k2)));
	        // → [4, 6, 7]
	        System.out.println();
	        // Test 3 — single element
	        int[] nums3 = {5};
	        int k3 = 1;
	        System.out.println("Input : " + Arrays.toString(nums3) + ", k=" + k3);
	        System.out.println("Output: " + Arrays.toString(topKFrequent(nums3, k3)));
	        // → [5]
	        System.out.println();
	        // Test 4 — all same frequency
	        int[] nums4 = {1, 2, 3, 4};
	        int k4 = 2;
	        System.out.println("Input : " + Arrays.toString(nums4) + ", k=" + k4);
	        System.out.println("Output: " + Arrays.toString(topKFrequent(nums4, k4)));
	        // → any 2 elements (all tie at freq=1)
	    }

}
