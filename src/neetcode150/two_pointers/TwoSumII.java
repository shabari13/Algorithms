package neetcode150.two_pointers;
/*
 * 
 * Given an array of integers numbers that is sorted in non-decreasing order.

Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.

There will always be exactly one valid solution.

Your solution must use 
O
(
1
)
O(1) additional space.

Example 1:

Input: numbers = [1,2,3,4], target = 3

Output: [1,2]
Explanation:
The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2. We return [1, 2].

Constraints:

2 <= numbers.length <= 1000
-1000 <= numbers[i] <= 1000
-1000 <= target <= 1000

Time Complexity: O(n)

We visit each element at most once
The two pointers start at opposite ends and move toward each other
In the worst case, they meet in the middle, touching all n elements
Each iteration does constant work: one addition, one comparison
Think of it like: if you have 100 boxes, you'll check at most 100 boxes total

Space Complexity: O(1)

We only use a fixed amount of extra space:

Two integer variables (left and right)
One variable for sum
One array for the result (always size 2)


No matter how big the input array is, we use the same tiny amount of extra memory
Think of it like: whether you have 10 boxes or 1 million boxes, you still only use 2 fingers!
 */
public class TwoSumII {

	public int[] twoSum(int[] nums, int target) {
		
		int left = 0;
		int right = nums.length - 1;
		while(left < right) {
			int sum = nums[left] + nums[right];
			if(sum == target) {
				return new int[]{left + 1, right + 1};
			}
			else if(sum < target) {
				left++;
			} else {
				right --;
			}
		}
		return new int[] {-1, -1};
		
	}
	public static void main(String[] args) {
		TwoSumII solution = new TwoSumII();
        
        // Test Case 1
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(numbers1, target1);
        System.out.println("Test Case 1:");
        System.out.println("Array: [2, 7, 11, 15], Target: 9");
        System.out.println("Output: [" + result1[0] + ", " + result1[1] + "]");
        System.out.println();
        
        // Test Case 2
        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(numbers2, target2);
        System.out.println("Test Case 2:");
        System.out.println("Array: [2, 3, 4], Target: 6");
        System.out.println("Output: [" + result2[0] + ", " + result2[1] + "]");
        System.out.println();
        
        // Test Case 3
        int[] numbers3 = {-1, 0};
        int target3 = -1;
        int[] result3 = solution.twoSum(numbers3, target3);
        System.out.println("Test Case 3:");
        System.out.println("Array: [-1, 0], Target: -1");
        System.out.println("Output: [" + result3[0] + ", " + result3[1] + "]");
        System.out.println();
        
        // Test Case 4
        int[] numbers4 = {1, 2, 3, 4, 5, 6, 7};
        int target4 = 13;
        int[] result4 = solution.twoSum(numbers4, target4);
        System.out.println("Test Case 4:");
        System.out.println("Array: [1, 2, 3, 4, 5, 6, 7], Target: 13");
        System.out.println("Output: [" + result4[0] + ", " + result4[1] + "]");
    }
}
