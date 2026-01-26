package neetcode150.arrays_and_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 
 * Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.

A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [2,20,4,10,3,4,5]

Output: 4
Explanation: The longest consecutive sequence is [2, 3, 4, 5].

Example 2:

Input: nums = [0,3,2,5,4,6,1,1]

Output: 7
Constraints:

0 <= nums.length <= 1000
-10^9 <= nums[i] <= 10^9

⏱️ Time Complexity: O(n)

We go through all numbers to put them in the HashSet: O(n)
We check each number once to see if it's a starter: O(n)
Even though there's a while loop inside, each number is visited at most twice (once in the outer loop, once in the inner while loop)
Total: O(n) where n is the number of elements


💾 Space Complexity: O(n)

We store all numbers in a HashSet: O(n)
A few variables (longestStreak, currentNum, currentStreak): O(1)
Total: O(n)


 */
public class LongestConsecutiveSequence {
	
	public int longestConsecutive(int[] nums) {
		
		if(nums.length == 0) {
			return 0;
		}
		Set<Integer> numSet = new HashSet<>();
		for(int num : nums) {
			
			numSet.add(num);
		}
		int longestStreak = 0;
		for(int number : numSet) {
			if(!numSet.contains(number - 1)) {
				int currentStreak = 1;
				int currentNum = number;
				
				while(numSet.contains(currentNum + 1)) {
					currentNum++;
					currentStreak++;
				}
				longestStreak = Math.max(longestStreak, currentStreak);
			}
			
		}
		return longestStreak;
		
	}
	public static void main(String[] args) {
		LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
	    
	    // Test Case 1
	    int[] nums1 = {100, 4, 200, 1, 3, 2};
	    System.out.println("Input: " + Arrays.toString(nums1));
	    System.out.println("Output: " + solution.longestConsecutive(nums1));
	    System.out.println("Explanation: Longest consecutive sequence is [1, 2, 3, 4]\n");
	    
	    // Test Case 2
	    int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
	    System.out.println("Input: " + Arrays.toString(nums2));
	    System.out.println("Output: " + solution.longestConsecutive(nums2));
	    System.out.println("Explanation: Longest consecutive sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8]\n");
	    
	    // Test Case 3
	    int[] nums3 = {9, 1, 4, 7, 3, 2, 8, 5, 6};
	    System.out.println("Input: " + Arrays.toString(nums3));
	    System.out.println("Output: " + solution.longestConsecutive(nums3));
	    System.out.println("Explanation: Longest consecutive sequence is [1, 2, 3, 4, 5, 6, 7, 8, 9]\n");
	    
	    // Test Case 4
	    int[] nums4 = {};
	    System.out.println("Input: " + Arrays.toString(nums4));
	    System.out.println("Output: " + solution.longestConsecutive(nums4));
	    System.out.println("Explanation: Empty array, no sequence\n");
	    
	    // Test Case 5
	    int[] nums5 = {1, 2, 0, 1};
	    System.out.println("Input: " + Arrays.toString(nums5));
	    System.out.println("Output: " + solution.longestConsecutive(nums5));
	    System.out.println("Explanation: Longest consecutive sequence is [0, 1, 2]\n");
	}
			

}
