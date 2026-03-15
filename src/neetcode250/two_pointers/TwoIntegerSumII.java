package neetcode250.two_pointers;

import java.util.Arrays;

/*
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
Approach: Two Pointers — since the array is sorted, use a left and right pointer converging inward.

If left + right == target → found it
If sum too small → move left right (need bigger number)
If sum too large → move right left (need smaller number)
TimeO(n)Each pointer moves at most n steps totalSpaceO(1)Only two pointer variables — no extra data structures
 */
public class TwoIntegerSumII {
	public static int[] twoSum(int[] numbers, int target) {
		  int left = 0, right = numbers.length - 1;

	        while (left < right) {
	            int currentSum = numbers[left] + numbers[right];

	            if (currentSum == target) {
	                return new int[]{left + 1, right + 1};  // Convert to 1-indexed
	            } else if (currentSum < target) {
	                left++;   // Need larger sum
	            } else {
	                right--;  // Need smaller sum
	            }
	        }

	        return new int[]{};  // Guaranteed a solution exists, so never reached
	}
	

    public static void main(String[] args) {
        int[][][] testNumbers = {
            {{1, 2, 3, 4}},
            {{2, 7, 11, 15}},
            {{2, 3, 4}},
            {{-3, -1, 0, 2, 4}},
            {{1, 3, 4, 5, 7, 11}},
            {{5, 25, 75}}
        };

        int[] targets  = {3, 9, 6, 1, 9, 100};
        int[][] expected = {{1,2}, {1,2}, {1,3}, {1,5}, {3,4}, {2,3}};

        System.out.printf("%-30s %-8s %-12s %-12s %s%n",
                          "Numbers", "Target", "Expected", "Got", "Pass?");
        System.out.println("-".repeat(72));

        for (int i = 0; i < targets.length; i++) {
            int[] numbers = testNumbers[i][0];
            int target    = targets[i];
            int[] result  = twoSum(numbers, target);
            boolean passed = Arrays.equals(result, expected[i]);

            System.out.printf("%-30s %-8d %-12s %-12s %s%n",
                Arrays.toString(numbers),
                target,
                Arrays.toString(expected[i]),
                Arrays.toString(result),
                passed ? "✅" : "❌"
            );
        }
    }

}
