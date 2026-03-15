package neetcode250.sliding_window;
/*
 * You are given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: target = 10, nums = [2,1,5,1,5,3]

Output: 3
Explanation: The subarray [5,1,5] has the minimal length under the problem constraint.

Example 2:

Input: target = 5, nums = [1,2,1]

Output: 0
Constraints:

1 <= nums.length <= 100,000
1 <= nums[i] <= 10,000
1 <= target <= 1,000,000,000
Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).

TimeO(n) — right visits each element once; left also visits each element at most once. 
Total operations ≤ 2n.SpaceO(1) — only four integer variables (left, sum, minLen, right). No arrays, no maps.
 */
public class MinimumSizeSubArray {
	 public static int minSubArrayLen(int target, int[] nums) {
		  int n = nums.length;
		  
	        // 'left' is the start of our sliding window
	        int left = 0;
	 
	        // 'sum' keeps track of the sum of elements inside our current window
	        int sum = 0;
	 
	        // 'minLen' stores the answer — start with max possible value
	        // We use Integer.MAX_VALUE as a sentinel meaning "no valid window found yet"
	        int minLen = Integer.MAX_VALUE;
	 
	        // 'right' is the end pointer — we expand the window by moving right forward
	        for (int right = 0; right < n; right++) {
	 
	            // STEP 1: Expand window — include nums[right] into our running sum
	            sum += nums[right];
	 
	            // STEP 2: Once sum >= target, we have a valid window.
	            //         Now try to shrink it from the left to find the minimum length.
	            while (sum >= target) {
	 
	                // How long is the current window? (right - left + 1)
	                int currentLen = right - left + 1;
	 
	                // Update minimum length if this window is smaller
	                minLen = Math.min(minLen, currentLen);
	 
	                // STEP 3: Shrink window from the left — remove nums[left] from sum
	                sum -= nums[left];
	 
	                // Move left pointer forward
	                left++;
	            }
	        }
	 
	        // If minLen was never updated, no valid subarray exists — return 0
	        return minLen == Integer.MAX_VALUE ? 0 : minLen;
	        
	 }	
	 public static void main(String[] args) {
		 
	        System.out.println("===== Minimum Size Subarray Sum =====\n");
	 
	        // ---------------------------------------------------------------
	        // TEST 1: Classic example from LeetCode
	        // Array : [2, 3, 1, 2, 4, 3], target = 7
	        // Expected: 2  (subarray [4, 3])
	        // ---------------------------------------------------------------
	        int[] nums1 = {2, 3, 1, 2, 4, 3};
	        int target1 = 7;
	        int result1 = minSubArrayLen(target1, nums1);
	        System.out.println("Test 1:");
	        System.out.println("  Input : nums = [2, 3, 1, 2, 4, 3], target = 7");
	        System.out.println("  Output: " + result1);
	        System.out.println("  Expect: 2  (subarray [4, 3])");
	        System.out.println();
	 
	        // ---------------------------------------------------------------
	        // TEST 2: Entire array needed
	        // Array : [1, 1, 1, 1, 1], target = 11
	        // Expected: 0  (no subarray sums to >= 11)
	        // ---------------------------------------------------------------
	        int[] nums2 = {1, 1, 1, 1, 1};
	        int target2 = 11;
	        int result2 = minSubArrayLen(target2, nums2);
	        System.out.println("Test 2:");
	        System.out.println("  Input : nums = [1, 1, 1, 1, 1], target = 11");
	        System.out.println("  Output: " + result2);
	        System.out.println("  Expect: 0  (sum of whole array = 5, never reaches 11)");
	        System.out.println();
	 
	        // ---------------------------------------------------------------
	        // TEST 3: Single element satisfies target
	        // Array : [1, 4, 4], target = 4
	        // Expected: 1  (subarray [4])
	        // ---------------------------------------------------------------
	        int[] nums3 = {1, 4, 4};
	        int target3 = 4;
	        int result3 = minSubArrayLen(target3, nums3);
	        System.out.println("Test 3:");
	        System.out.println("  Input : nums = [1, 4, 4], target = 4");
	        System.out.println("  Output: " + result3);
	        System.out.println("  Expect: 1  (subarray [4])");
	        System.out.println();
	 
	        // ---------------------------------------------------------------
	        // TEST 4: Entire array is the minimum subarray
	        // Array : [1, 2, 3], target = 6
	        // Expected: 3  (only [1, 2, 3] sums to 6)
	        // ---------------------------------------------------------------
	        int[] nums4 = {1, 2, 3};
	        int target4 = 6;
	        int result4 = minSubArrayLen(target4, nums4);
	        System.out.println("Test 4:");
	        System.out.println("  Input : nums = [1, 2, 3], target = 6");
	        System.out.println("  Output: " + result4);
	        System.out.println("  Expect: 3  (subarray [1, 2, 3])");
	        System.out.println();
	 
	        // ---------------------------------------------------------------
	        // TEST 5: Large single value at end
	        // Array : [1, 1, 1, 1, 100], target = 100
	        // Expected: 1  (subarray [100])
	        // ---------------------------------------------------------------
	        int[] nums5 = {1, 1, 1, 1, 100};
	        int target5 = 100;
	        int result5 = minSubArrayLen(target5, nums5);
	        System.out.println("Test 5:");
	        System.out.println("  Input : nums = [1, 1, 1, 1, 100], target = 100");
	        System.out.println("  Output: " + result5);
	        System.out.println("  Expect: 1  (subarray [100])");
	        System.out.println();
	 
	    
	    }
}
