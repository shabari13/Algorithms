package blind75.binary;
/*
 * 
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

 

Example 1:

Input: nums = [3,0,1]

Output: 2

Explanation:

n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:

Input: nums = [0,1]

Output: 2

Explanation:

n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]

Output: 8

Explanation:

n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

 
 

 

 

Constraints:

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
 

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?

Time and Space Complexity
⏱️ Time Complexity: O(n)

We loop through the array once to add all numbers
n is the size of the array
If array has 10 numbers, we do 10 operations
If array has 100 numbers, we do 100 operations
Linear time!

💾 Space Complexity: O(1)

We only use a few variables: n, expectedSum, actualSum
We don't create any new arrays or data structures
Memory used doesn't grow with input size
Constant space!
 */
public class MissingNumber {
	
	public static int missingNumber(int[] nums) {
		
		int n = nums.length;
		int expectedSum = n * (n+1) / 2;
		int actualSum = 0;
		for(int num : nums) {
			actualSum += num;
		}
		return expectedSum - actualSum;
	}
	
	// Solution 2: Using XOR (Bitwise approach)
    public static int missingNumberXOR(int[] nums) {
        int n = nums.length;
        int result = n; // Start with n
        
        // XOR all indices and values
        for (int i = 0; i < n; i++) {
            result = result ^ i ^ nums[i];
        }
        
        return result;
    }
    public static void main(String[] args) {
        // Sample input
        int[] nums = {3, 0, 1};
        
        System.out.println("========== SOLUTION 1: SUM ==========");
        System.out.println("Input array: [3, 0, 1]");
        System.out.println("Missing number: " + missingNumber(nums));
        
        System.out.println("\n========== SOLUTION 2: XOR ==========");
        System.out.println("Input array: [3, 0, 1]");
        System.out.println("Missing number: " + missingNumberXOR(nums));
        
        // More test cases
        System.out.println("\n--- More Examples (XOR) ---");
        
        int[] nums2 = {0, 1};
        System.out.println("Input: [0, 1] -> Missing: " + missingNumberXOR(nums2));
        
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Input: [9, 6, 4, 2, 3, 5, 7, 0, 1] -> Missing: " + missingNumberXOR(nums3));
        
        int[] nums4 = {0};
        System.out.println("Input: [0] -> Missing: " + missingNumberXOR(nums4));
    }

}
