package neetcode150.arrays_and_hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

Example 1:

Input: nums = [1, 2, 3, 3]

Output: true

Time Complexity: O(n)

We look at each number in the array exactly once
For an array with n numbers, we do n checks
Checking if something is in a HashSet is super fast (O(1) on average)
So total time: n × O(1) = O(n)

Space Complexity: O(n)

In the worst case (no duplicates), we store all n numbers in our HashSet
So we need extra space proportional to the size of the input array
That's O(n) extra space
 */
public class ContainDuplicate {
	
	public boolean containsDuplicate(int[] nums) {
		
		Set<Integer> set  = new HashSet<>();
		
		for(int num : nums) {
			if(set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}
	
	 public static void main(String[] args) {
		 ContainDuplicate solution = new ContainDuplicate();
	        
	        // Test Case 1: Array with duplicates
	        int[] test1 = {1, 2, 3, 1};
	        System.out.println("Test 1: [1, 2, 3, 1]");
	        System.out.println("Contains Duplicate: " + solution.containsDuplicate(test1));
	        System.out.println();
	        
	        // Test Case 2: Array without duplicates
	        int[] test2 = {1, 2, 3, 4};
	        System.out.println("Test 2: [1, 2, 3, 4]");
	        System.out.println("Contains Duplicate: " + solution.containsDuplicate(test2));
	        System.out.println();
	        
	        // Test Case 3: Array with multiple duplicates
	        int[] test3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
	        System.out.println("Test 3: [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]");
	        System.out.println("Contains Duplicate: " + solution.containsDuplicate(test3));
	        System.out.println();
	        
	        // Test Case 4: Empty array
	        int[] test4 = {};
	        System.out.println("Test 4: []");
	        System.out.println("Contains Duplicate: " + solution.containsDuplicate(test4));
	        System.out.println();
	        
	        // Test Case 5: Single element
	        int[] test5 = {1};
	        System.out.println("Test 5: [1]");
	        System.out.println("Contains Duplicate: " + solution.containsDuplicate(test5));
	    }

}
