package neetcode150.arrays_and_hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.

You may assume that every input has exactly one pair of indices i and j that satisfy the condition.

Return the answer with the smaller index first.

Example 1:

Input: 
nums = [3,4,5,6], target = 7

Output: [0,1]
Explanation: nums[0] + nums[1] == 7, so we return [0, 1].

Example 2:

Input: nums = [4,5,6], target = 10

Output: [0,2]
Example 3:

Input: nums = [5,5], target = 10

Output: [0,1]
Constraints:

2 <= nums.length <= 1000
-10,000,000 <= nums[i] <= 10,000,000
-10,000,000 <= target <= 10,000,000
Only one valid answer exists.

Time Complexity: O(n)

We look at each number in the array exactly once
HashMap operations (put and containsKey) take O(1) on average
So if we have n numbers, we do n operations → O(n)
In simple words: If you have 10 toys, you check 10 toys. If you have 100 toys, you check 100 toys. It grows directly with the number of items!

Space Complexity: O(n)

In the worst case, we might store almost all numbers in the HashMap
If we have n numbers and find the answer at the very end, we'd have stored (n-1) numbers
In simple words: Our notebook might need to write down almost all the toys before finding the answer!
 */
public class TwoSum {
	
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			int compliment = target - nums[i];
			if(map.containsKey(compliment))
				return new int[]{map.get(compliment), i};
			map.put(nums[i], i);
		}
		return new int[] {};
	}
	
	public static void main(String[] args) {
		TwoSum solution = new TwoSum();
        
        // Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [2, 7, 11, 15], target = 9");
        System.out.println("Output: [" + result1[0] + ", " + result1[1] + "]");
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [3, 2, 4], target = 6");
        System.out.println("Output: [" + result2[0] + ", " + result2[1] + "]");
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = [3, 3], target = 6");
        System.out.println("Output: [" + result3[0] + ", " + result3[1] + "]");
        System.out.println();
        
        // Test Case 4
        int[] nums4 = {1, 5, 3, 7, 9};
        int target4 = 12;
        int[] result4 = solution.twoSum(nums4, target4);
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [1, 5, 3, 7, 9], target = 12");
        System.out.println("Output: [" + result4[0] + ", " + result4[1] + "]");
    }

}
