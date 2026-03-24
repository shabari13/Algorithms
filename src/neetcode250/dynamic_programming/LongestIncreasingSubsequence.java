package neetcode250.dynamic_programming;
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
We want the longest chain of numbers where each one is strictly bigger than the one before it — they don't have to be adjacent. The classic DP approach asks, for every position i: "what is the longest increasing chain that ends exactly here?" That answer is 1 (just itself) plus the best answer among all earlier positions j where nums[j] < nums[i]. After filling this table we return the maximum entry. The faster binary-search approach maintains a "patience-sorting" list called tails, where tails[k] stores the smallest possible tail of any increasing subsequence of length k+1 seen so far. Because this list stays sorted, we can binary-search to find where each new number slots in, giving us O(n log n) total time.

Explain it like I'm 5
Imagine you have a row of numbered blocks: 10, 9, 2, 5, 3, 7, 101, 18. You want to build the tallest tower possible, but you can only pick blocks and stack them if each block you add has a bigger number than the one below it. You're allowed to skip blocks — you don't have to use neighbors. You try every possible tower that ends at each block, write down its height, and finally report the tallest tower you found.

Time Complexity: O(n²) - We compare each element with all previous elements
Space Complexity: O(n) - We need one array to store results

Basically we create a dp. We check what is the max length of the subsequenet at point of each index i. 
You compare the element at i with j which is lesser index then i and if value of i is less than j then you increment the ith index value.
Also keep track of max length; 
 */
public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		for(int i = 0; i <= nums.length; i++) {
			dp[i] = 1;
		}
		int maxLength = 1;
		for(int i = 1; i <= nums.length; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLength = Math.max(maxLength, dp[i]);
		}
		return maxLength;
	}
}
