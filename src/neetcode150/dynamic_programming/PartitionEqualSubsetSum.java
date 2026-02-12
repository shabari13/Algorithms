package neetcode150.dynamic_programming;
/*
 * You are given an array of positive integers nums.

Return true if you can partition the array into two subsets, subset1 and subset2 where sum(subset1) == sum(subset2). Otherwise, return false.

Example 1:

Input: nums = [1,2,3,4]

Output: true
Explanation: The array can be partitioned as [1, 4] and [2, 3].

Example 2:

Input: nums = [1,2,3,4,5]

Output: false
Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 50
## **Time Complexity: O(n × target)**
- `n` = number of elements in the array
- `target` = totalSum / 2
- We have a loop through all `n` numbers
- For each number, we iterate through values from `target` down to `num`
- In the worst case: **O(n × sum/2)** which simplifies to **O(n × sum)**

**Example:** If nums = [1, 5, 11, 5], n = 4, sum = 22, target = 11
- Iterations ≈ 4 × 11 = 44 operations

## **Space Complexity: O(target)**
- We only use a single DP array of size `target + 1`
- No recursion stack, no 2D array
- **O(sum/2)** which simplifies to **O(sum)**

**Example:** For target = 11, we need array of size 12
*
*Basically you check if the total sum of no is even or odd. 
*If it is odd then we can not split. If it even then we can
*So create a dp array with target which is totalsum/2.We create a DP array with target and move backwards.
*and checkeach number can that be formed using the num or by subtracting wit with the index. if yes, then make that index true.
*in the end rtun dp[index of target]
*
 
 */
public class PartitionEqualSubsetSum {
	
	public boolean canPartition(int[] nums) {
		int totalSum = 0;
		
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			return false;
		}
		int target = totalSum / 2;
		boolean[] dp = new boolean[target + 1];
		dp[0] = true;
		for(int num: nums) {
			for(int i = target; i >= num; i--) {
				dp[i] = dp[i] || dp[i - num];
			}
		}
		return dp[target];
	}
	public static void main(String[] args) {
        PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();
        
        // Test Case 1
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Input: [1, 5, 11, 5]");
        System.out.println("Output: " + solution.canPartition(nums1));
        System.out.println("Explanation: Array can be partitioned as [1, 5, 5] and [11]\n");
        
        // Test Case 2
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Input: [1, 2, 3, 5]");
        System.out.println("Output: " + solution.canPartition(nums2));
        System.out.println("Explanation: Array cannot be partitioned into equal sum subsets\n");
        
        // Test Case 3
        int[] nums3 = {1, 2, 5};
        System.out.println("Input: [1, 2, 5]");
        System.out.println("Output: " + solution.canPartition(nums3));
        System.out.println("Explanation: Total sum is 8, target is 4. We can make [1, 3] - wait, no 3! Cannot partition\n");
        
        // Test Case 4
        int[] nums4 = {2, 2, 1, 1};
        System.out.println("Input: [2, 2, 1, 1]");
        System.out.println("Output: " + solution.canPartition(nums4));
        System.out.println("Explanation: Array can be partitioned as [2, 1] and [2, 1]\n");
        
        // Test Case 5
        int[] nums5 = {1, 1};
        System.out.println("Input: [1, 1]");
        System.out.println("Output: " + solution.canPartition(nums5));
        System.out.println("Explanation: Array can be partitioned as [1] and [1]\n");
    }

}
