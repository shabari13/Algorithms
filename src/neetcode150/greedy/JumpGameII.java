package neetcode150.greedy;
/*
 * You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index i. For example, if you are at nums[i], you can jump to any index i + j where:

j <= nums[i]
i + j < nums.length
You are initially positioned at nums[0].

Return the minimum number of jumps to reach the last position in the array (index nums.length - 1). You may assume there is always a valid answer.

Example 1:

Input: nums = [2,4,1,1,1,1]

Output: 2
Explanation: Jump from index 0 to index 1, then jump from index 1 to the last index.

Example 2:

Input: nums = [2,1,2,1,0]

Output: 2
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 100
Time and Space Complexity
Time Complexity: O(n)

We iterate through the array exactly once
Each position is visited once
All operations inside the loop (max calculation, comparisons) are O(1)
Therefore, total time is linear: O(n) where n is the length of the array

Space Complexity: O(1)

We only use a constant amount of extra space
Variables: jumps, currentEnd, farthest, and loop counter i
No additional data structures (arrays, lists, etc.) are used
Space usage doesn't grow with input size
Therefore, space complexity is constant: O(1)
 */
public class JumpGameII {
	  public int jump(int[] nums) {
	        if (nums.length <= 1) {
	            return 0;
	        }
	        
	        int jumps = 0;
	        int currentEnd = 0;
	        int farthest = 0;
	        
	        // We don't need to check the last position
	        for (int i = 0; i < nums.length - 1; i++) {
	            // Update the farthest position we can reach
	            farthest = Math.max(farthest, i + nums[i]);
	            
	            // If we've reached the end of current jump range
	            if (i == currentEnd) {
	                jumps++;
	                currentEnd = farthest;
	                
	                // Early exit if we can reach the last position
	                if (currentEnd >= nums.length - 1) {
	                    break;
	                }
	            }
	        }
	        
	        return jumps;
	    }
	 public static void main(String[] args) {
	        JumpGameII solution = new JumpGameII();
	        
	        // Test Case 1
	        int[] nums1 = {2, 3, 1, 1, 4};
	        System.out.println("Input: [2, 3, 1, 1, 4]");
	        System.out.println("Output: " + solution.jump(nums1));
	        System.out.println("Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.\n");
	        
	        // Test Case 2
	        int[] nums2 = {2, 3, 0, 1, 4};
	        System.out.println("Input: [2, 3, 0, 1, 4]");
	        System.out.println("Output: " + solution.jump(nums2));
	        System.out.println("Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.\n");
	        
	        // Test Case 3
	        int[] nums3 = {1, 2, 3};
	        System.out.println("Input: [1, 2, 3]");
	        System.out.println("Output: " + solution.jump(nums3));
	        System.out.println("Explanation: Jump 1 step from index 0 to 1, then 1 step to the last index.\n");
	        
	        // Test Case 4
	        int[] nums4 = {1, 1, 1, 1};
	        System.out.println("Input: [1, 1, 1, 1]");
	        System.out.println("Output: " + solution.jump(nums4));
	        System.out.println("Explanation: Jump from each index one by one.\n");
	        
	        // Test Case 5
	        int[] nums5 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0};
	        System.out.println("Input: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0]");
	        System.out.println("Output: " + solution.jump(nums5));
	        System.out.println("Explanation: Jump from index 0 directly to the last index.\n");
	        
	        // Test Case 6
	        int[] nums6 = {5};
	        System.out.println("Input: [5]");
	        System.out.println("Output: " + solution.jump(nums6));
	        System.out.println("Explanation: Already at the last index, no jumps needed.\n");
	    }
}
