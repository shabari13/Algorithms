package neetcode250.arrays_hashing;

import java.util.HashSet;
import java.util.Set;
/*
 * Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

Example 1:

Input: nums = [1, 2, 3, 3]

Output: true

Example 2:

Input: nums = [1, 2, 3, 4]

Output: false
ComplexityWhyTimeO(n)We visit each element exactly once. HashSet contains() and add()
 are both O(1) average.SpaceO(n)In the worst case (no duplicates), we store all n elements in the HashSet.
 */
public class ContainsDuplicate {
	
	public static boolean hasDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for(int num : nums) {
			if(set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}
	 public static void main(String[] args) {

	        // Test Case 1: classic duplicate
	        int[] test1 = {1, 2, 3, 1};
	        System.out.println("Input: [1, 2, 3, 1]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test1));   // true
	        System.out.println();

	        // Test Case 2: all unique
	        int[] test2 = {1, 2, 3, 4};
	        System.out.println("Input: [1, 2, 3, 4]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test2));   // false
	        System.out.println();

	        // Test Case 3: single element
	        int[] test3 = {7};
	        System.out.println("Input: [7]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test3));   // false
	        System.out.println();

	        // Test Case 4: all same elements
	        int[] test4 = {5, 5, 5, 5};
	        System.out.println("Input: [5, 5, 5, 5]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test4));   // true
	        System.out.println();

	        // Test Case 5: duplicate at the very end
	        int[] test5 = {10, 20, 30, 40, 10};
	        System.out.println("Input: [10, 20, 30, 40, 10]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test5));   // true
	        System.out.println();

	        // Test Case 6: negative numbers with duplicate
	        int[] test6 = {-3, -1, 0, 2, -1};
	        System.out.println("Input: [-3, -1, 0, 2, -1]");
	        System.out.println("Has Duplicate? " + hasDuplicate(test6));   // true
	    }
}
