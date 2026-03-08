package neetcode250.arrays_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
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

Time Complexity
O(n)

Why?

Insert into HashSet → O(n)

Each number visited at most twice

Total:

O(n)
Space Complexity
O(n)

 */
public class LongestConsecutiveSequence {
	
	public static int longestConsecutive(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for(int num : nums) {
			set.add(num);
		}
		int longestStreak = 0;
		for (int num : nums) {
			
			if(!set.contains(num - 1)) {
				int currentNum = num;

				 int count = 1;
				while(set.contains(currentNum+1)) {
					count++;
					currentNum++;
				}
				longestStreak = Math.max(longestStreak, count);
				
			}
			
		}
		return longestStreak;
	}
	
	   public static void main(String[] args) {

	        int[] nums1 = {100,4,200,1,3,2};
	        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
	        int[] nums3 = {9,1,4,7,3,-1,0,5,8,-1,6};

	        System.out.println("Input: " + Arrays.toString(nums1));
	        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums1));

	        System.out.println();

	        System.out.println("Input: " + Arrays.toString(nums2));
	        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums2));

	        System.out.println();

	        System.out.println("Input: " + Arrays.toString(nums3));
	        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums3));
	    }

}
