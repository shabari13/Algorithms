package neetcode250.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
 * You are given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k, otherwise return false.

Example 1:

Input: nums = [1,2,3,1], k = 3

Output: true
Example 2:

Input: nums = [2,1,2], k = 1

Output: false
Constraints:

1 <= nums.length <= 100,000
-1,000,000,000 <= nums[i] <= 1,000,000,000
0 <= k <= 100,000

ComplexityReasonTimeO(n)One pass through the array; HashMap get/put are O(1) averageSpaceO(min(n, k))Map holds at most k+1 entries at any time (the active window)

 */
public class ContainsDuplicateII {

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])) {
				if(Math.abs(i - map.get(nums[i])) <= k) {
					return true;
				}
			} 
				map.put(nums[i], i);
			
		}
        return false;
    }
	
	public static void main(String[] args) {

        // Test 1: Expected true (nums[0]=1 and nums[3]=1, |0-3|=3 <= k=3)
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println("Test 1: nums=[1,2,3,1], k=3 → " + containsNearbyDuplicate(nums1, k1));

        // Test 2: Expected true (nums[0]=1 and nums[1]=1, |0-1|=1 <= k=1)
        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println("Test 2: nums=[1,0,1,1], k=1 → " + containsNearbyDuplicate(nums2, k2));

        // Test 3: Expected false (|0-2|=2 > k=1, |1-4|=3 > k=1)
        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println("Test 3: nums=[1,2,3,1,2,3], k=2 → " + containsNearbyDuplicate(nums3, k3));

        // Test 4: Expected false (all unique)
        int[] nums4 = {4, 5, 6, 7};
        int k4 = 3;
        System.out.println("Test 4: nums=[4,5,6,7], k=3 → " + containsNearbyDuplicate(nums4, k4));

        // Test 5: Expected true (single duplicate right next to each other)
        int[] nums5 = {99, 99};
        int k5 = 2;
        System.out.println("Test 5: nums=[99,99], k=2 → " + containsNearbyDuplicate(nums5, k5));
    }
}
