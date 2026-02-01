package neetcode150.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * ⏱️ Time Complexity: O(n²)

Sorting takes O(n log n)
Main loop: We have one loop (i goes through array) = O(n)

Inside, we have two pointers (left and right) that together scan the array = O(n)
So nested: O(n) × O(n) = O(n²)



Overall: O(n²) because n² grows faster than n log n
In simple terms: If you have 10 numbers, you might do about 100 operations. If you have 100 numbers, you might do about 10,000 operations.

💾 Space Complexity: O(1) or O(n)

We only use a few variables (i, left, right) = O(1) extra space
The result list can hold at most O(n²) triplets in worst case
Sorting might use O(log n) or O(n) depending on implementation

Ignoring output: O(1)
Including output: O(n) for sorting space
 */
public class ThreeSum {
	
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for(int i = 0; i < nums.length; i++) {
			if( i > 0 && nums[i] == nums[i - 1])
				continue;
			
			int left = i + 1;
			int right = nums.length - 1;
			while(left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if(sum == 0) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while(left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while(left < right && nums[right] == nums[right - 1]) {
						right -- ;
					}
					left++;
					right--;
				} else if(sum < 0) {
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
        
        // Test Case 1
        System.out.println("=== Test Case 1 ===");
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: " + Arrays.toString(nums1));
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println("Output: " + result1);
        System.out.println();
        
        // Test Case 2
        System.out.println("=== Test Case 2 ===");
        int[] nums2 = {0, 1, 1};
        System.out.println("Input: " + Arrays.toString(nums2));
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("Output: " + result2);
        System.out.println();
        
        // Test Case 3
        System.out.println("=== Test Case 3 ===");
        int[] nums3 = {0, 0, 0};
        System.out.println("Input: " + Arrays.toString(nums3));
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println("Output: " + result3);
        System.out.println();
        
        // Test Case 4
        System.out.println("=== Test Case 4 ===");
        int[] nums4 = {-2, 0, 1, 1, 2};
        System.out.println("Input: " + Arrays.toString(nums4));
        List<List<Integer>> result4 = solution.threeSum(nums4);
        System.out.println("Output: " + result4);
        System.out.println();
        
        // Test Case 5
        System.out.println("=== Test Case 5 ===");
        int[] nums5 = {-4, -1, -1, 0, 1, 2};
        System.out.println("Input: " + Arrays.toString(nums5));
        List<List<Integer>> result5 = solution.threeSum(nums5);
        System.out.println("Output: " + result5);
        System.out.println();
    }

}
