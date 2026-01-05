package blind75.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

 Time Complexity: O(n²)

Sorting takes O(n log n)
We have one loop (n times) and inside it, two pointers move (n times in total)
n × n = n² (this dominates)
 
  Space Complexity: O(1) or O(n)

O(1): We only use a few pointers (not counting output)
O(n): If we count the space for sorting (Java's sort might use extra space)
The output list doesn't count toward space complexity

 The Strategy (Step-by-Step)
Step 1: Organize Your Cards
First, we line up all the cards from smallest to biggest:
Before: -1, 0, 1, 2, -1, -4
After:  -4, -1, -1, 0, 1, 2
Step 2: Pick One Card and Find Two Friends
We pick one card and ask: "Which two other cards can be friends with this one to make 0?"
We use three fingers:

👆 First finger (i): The card we picked
☝️ Second finger (left): Starts right after the first card
👉 Third finger (right): Starts at the very end


 */
public class ThreeSum {
	

	public List<List<Integer>> threeSum(int[] nums) {
		 List<List<Integer>> result = new ArrayList<>();

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right - 1]) {
						right--;

					}
					left++;
                    right--;
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}

			}

		}
		return result;

	}
	
	public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        
        // Sample input 1
        System.out.println("===== Example 1 =====");
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: " + Arrays.toString(nums1));
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println("Output: " + result1);
        System.out.println();
        
        // Sample input 2 - Shows why we need duplicate skipping
        System.out.println("===== Example 2 (Shows duplicate skipping) =====");
        int[] nums2 = {-2, 0, 0, 2, 2};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("After sorting: " + Arrays.toString(nums2));
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("Output: " + result2);
        System.out.println("Without duplicate skipping, we'd get [-2,0,2] multiple times!");
        System.out.println();
        
        // Sample input 3
        System.out.println("===== Example 3 =====");
        int[] nums3 = {0, 0, 0};
        System.out.println("Input: " + Arrays.toString(nums3));
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println("Output: " + result3);
        System.out.println();
        
        // Sample input 4 - Shows BOTH types of duplicates
        System.out.println("===== Example 4 (Shows why we need BOTH checks) =====");
        int[] nums4 = {-2, -1, -1, -1, 3, 3, 3};
        System.out.println("Input: " + Arrays.toString(nums4));
        List<List<Integer>> result4 = solution.threeSum(nums4);
        System.out.println("Output: " + result4);
        System.out.println("Only ONE triplet [-2, -1, 3] despite many duplicates!");
    }

}
