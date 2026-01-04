package blind75.array;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem Statement:
Given an integer array nums, return true LeetCode if any value appears at least twice in the array, and return false if every element is distinct.
Examples:
Example 1:

Input: nums = [1, 2, 3, 3]
Output: true

Example 2:

Input: nums = [1, 2, 3, 4]
Output: false


TimeO(n)SpaceO(n)
 */
public class ContainsDuplicate {
	
	public boolean containsDuplicate(int[] nums) {
		
		if(nums.length == 0 || nums.length == 1) {
			return false;
		
		}
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
		ContainsDuplicate cd = new ContainsDuplicate();
		int[] nums = {3};
		System.out.println(cd.containsDuplicate(nums));
	}

}
