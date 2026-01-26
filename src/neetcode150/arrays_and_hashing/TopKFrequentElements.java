package neetcode150.arrays_and_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*Given an integer array nums and an integer k, return the k most frequent elements within the array.

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
Detailed Time Complexity Analysis:
Step 1: Build Frequency Map

Loop through all n elements: O(n)

Step 2: Build Max Heap

We have m unique elements (where m ≤ n)
Adding 1 element to heap: O(log m)
Adding m elements: O(m log m)

Step 3: Extract k elements

Each poll operation: O(log m)
Poll k times: O(k log m)

Total Time Complexity:
O(n) + O(m log m) + O(k log m)
= O(n + m log m + k log m)
In different scenarios:

Best case: When m is small (few unique elements) → O(n)
Worst case: When m = n (all unique elements) → O(n log n)
Average case: → O(n log m)

Space Complexity:

Frequency Map: O(m)
Max Heap: O(m)
Result array: O(k)

 * 
 * 
 */

public class TopKFrequentElements {
	
	public int[] topKFrequentMaxHeap(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num,  0) + 1);
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
		
		maxHeap.addAll(map.keySet());
		int[] result  = new int[k];
		
		for(int i = 0; i < k; i++) {
			result[i] = maxHeap.poll();
		}
		
		return result;
		
	}
	
	 public static void main(String[] args) {
	        TopKFrequentElements solution = new TopKFrequentElements();
	        
	        System.out.println("=".repeat(70));
	        System.out.println("COMPARING THREE APPROACHES: Max Heap vs Min Heap vs Bucket Sort");
	        System.out.println("=".repeat(70));
	        
	        // Test Case 1
	        int[] nums1 = {1, 1, 1, 2, 2, 3};
	        int k1 = 2;
	        System.out.println("\nTest Case 1:");
	        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
	        System.out.println("Max Heap Output: " + Arrays.toString(solution.topKFrequentMaxHeap(nums1, k1)));
	        
	        
	        // Test Case 2
	        int[] nums2 = {1};
	        int k2 = 1;
	        System.out.println("\nTest Case 2:");
	        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
	        System.out.println("Max Heap Output: " + Arrays.toString(solution.topKFrequentMaxHeap(nums2, k2)));
	        
	        // Test Case 3
	        int[] nums3 = {4, 4, 4, 5, 5, 6, 6, 6, 6, 7};
	        int k3 = 2;
	        System.out.println("\nTest Case 3:");
	        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
	        System.out.println("Max Heap Output: " + Arrays.toString(solution.topKFrequentMaxHeap(nums3, k3)));
	        
	        // Test Case 4
	        int[] nums4 = {1, 2, 3, 4, 5};
	        int k4 = 3;
	        System.out.println("\nTest Case 4:");
	        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", k = " + k4);
	        System.out.println("Max Heap Output: " + Arrays.toString(solution.topKFrequentMaxHeap(nums4, k4)));
	        
	        System.out.println("\n" + "=".repeat(70));
	        System.out.println("COMPLEXITY COMPARISON");
	        System.out.println("=".repeat(70));
	        System.out.println("Approach          | Time Complexity  | Space Complexity");
	        System.out.println("-".repeat(70));
	        System.out.println("Max Heap (All)    | O(n log m)       | O(m)");
	        System.out.println("Min Heap (k only) | O(m log k)       | O(k)");
	        System.out.println("Bucket Sort       | O(n)             | O(n)");
	        System.out.println("=".repeat(70));
	        System.out.println("where n = array length, m = unique elements, k = top k");
	    }

}
