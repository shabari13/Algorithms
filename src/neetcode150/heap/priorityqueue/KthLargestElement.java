package neetcode150.heap.priorityqueue;

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
There will always be at least k integers in the stream when you search for the kth integer.

Time Complexity:

Constructor: O(n log k) where n is the length of the initial array

We add n elements, each insertion takes O(log k) time


add() method: O(log k)

Inserting into a heap of size k: O(log k)
Removing from a heap of size k: O(log k)
Total: O(log k)



Space Complexity:

O(k)

We maintain a heap of at most k elements
No additional data structures are used
k is typically much smaller than n (total elements processed)
add elements to minHeap. eep removing smallest elemtn as o as size is higher than k;
 */
public class KthLargestElement {
	PriorityQueue<Integer> pq ;
	int k;
	
	public KthLargestElement(int k, int[] nums) {
		this.k = k;
		pq = new PriorityQueue<>();
		for(int num : nums) {
			add(num);
		}
	}
	public int add(int val) {
		pq.offer(val);
		if(pq.size() > k) {
			pq.poll();
		}
		return pq.peek();
	}
	 public static void main(String[] args) {
	        System.out.println("=== Kth Largest Element in Stream ===\n");
	        
	        // Example 1: k = 3, initial array = [4, 5, 8, 2]
	        int k = 3;
	        int[] initialArray = {4, 5, 8, 2};
	        
	        System.out.println("Initializing with k = " + k);
	        System.out.print("Initial array: [");
	        for (int i = 0; i < initialArray.length; i++) {
	            System.out.print(initialArray[i]);
	            if (i < initialArray.length - 1) System.out.print(", ");
	        }
	        System.out.println("]\n");
	        
	        KthLargestElement kthLargest = new KthLargestElement(k, initialArray);
	        
	        // Test adding new elements
	        int[] newElements = {3, 5, 10, 9, 4};
	        
	        for (int element : newElements) {
	            int result = kthLargest.add(element);
	            System.out.println("Added " + element + " -> 3rd largest is: " + result);
	        }
	        
	        System.out.println("\n=== Example 2 ===\n");
	        
	        // Example 2: k = 1, initial array = [0]
	        int k2 = 1;
	        int[] initialArray2 = {0};
	        
	        System.out.println("Initializing with k = " + k2);
	        System.out.println("Initial array: [0]\n");
	        
	        KthLargestElement kthLargest2 = new KthLargestElement(k2, initialArray2);
	        
	        int[] newElements2 = {-1, 1, -2, -4, 3};
	        
	        for (int element : newElements2) {
	            int result = kthLargest2.add(element);
	            System.out.println("Added " + element + " -> 1st largest is: " + result);
	        }
	    }
	}


