package neetcode150.dynamic_programming;
/*
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.

For example, "cat" is a subsequence of "crabt".
Example 1:

Input: nums = [9,1,4,2,3,3,7]

Output: 4
Explanation: The longest increasing subsequence is [1,2,3,7], which has a length of 4.

Example 2:

Input: nums = [0,3,1,3,2,3]

Output: 4
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

Time Complexity: O(n²) - We compare each element with all previous elements
Space Complexity: O(n) - We need one array to store results

Basically we create a dp. We check what is the max length of the subsequenet at point of each index i. 
You compare the element at i with j which is lesser index then i and if value of i is less than j then you increment the ith index value.
Also keep track of max length; 
 */
public class LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		for(int i=0; i < nums.length; i++) {
			dp[i] = 1;
		}
		int maxLength = 1;
		for(int i=1; i < nums.length; i++) {
			for(int j=0; j < i; j++) {
				if(nums[i] > nums[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			maxLength = Math.max(maxLength, dp[i]);
		}
		return maxLength;
	}
	
	public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        
        // Test Case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("===== Test Case 1 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]);
            if (i < nums1.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums1));
        System.out.println("Explanation: The longest increasing subsequence is [2, 3, 7, 101] or [2, 5, 7, 101]");
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("===== Test Case 2 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i]);
            if (i < nums2.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums2));
        System.out.println("Explanation: The longest increasing subsequence is [0, 1, 2, 3]");
        System.out.println();
        
        // Test Case 3
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("===== Test Case 3 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i]);
            if (i < nums3.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums3));
        System.out.println("Explanation: All elements are same, so longest increasing subsequence is just 1 element");
        System.out.println();
        
        // Test Case 4
        int[] nums4 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println("===== Test Case 4 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums4.length; i++) {
            System.out.print(nums4[i]);
            if (i < nums4.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums4));
        System.out.println("Explanation: The longest increasing subsequence is [1, 3, 6, 7, 9, 10]");
        System.out.println();
        
        // Test Case 5 - Single element
        int[] nums5 = {5};
        System.out.println("===== Test Case 5 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums5.length; i++) {
            System.out.print(nums5[i]);
            if (i < nums5.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums5));
        System.out.println("Explanation: Single element is an increasing subsequence of length 1");
        System.out.println();
        
        // Test Case 6 - Strictly decreasing
        int[] nums6 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("===== Test Case 6 =====");
        System.out.print("Input: [");
        for (int i = 0; i < nums6.length; i++) {
            System.out.print(nums6[i]);
            if (i < nums6.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Output: " + solution.lengthOfLIS(nums6));
        System.out.println("Explanation: Array is decreasing, so longest increasing subsequence is just 1 element");
    }
}
