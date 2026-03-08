package neetcode250.arrays_hashing;

import java.util.Arrays;

/*
 * You are given an array nums consisting of n elements where each element is an integer representing a color:

0 represents red
1 represents white
2 represents blue
Your task is to sort the array in-place such that elements of the same color are grouped together and arranged in the order: red (0), white (1), and then blue (2).

You must not use any built-in sorting functions to solve this problem.

Example 1:

Input: nums = [1,0,1,2]

Output: [0,1,1,2]

TIME COMPLEXITY:  O(n)
 *    - We traverse the array exactly ONCE with the 'mid' pointer.
 *    - Each element is visited at most once (mid only moves forward,
 *      and high only moves backward — they never reset).
 *    - Total operations = proportional to n. Hence O(n).
 *
 *  SPACE COMPLEXITY: O(1)
 *    - We sort IN-PLACE using only 3 integer pointer variables.
 *    - No extra array or data structure is created.
 *    - Memory usage does NOT grow with input size. Hence O(1).
 *
 */
public class SortColors {

	public static void sortColors(int[] nums) {
		int low=0; int  mid = 0;
		int high = nums.length - 1;
		while(mid <= high) {
			if(nums[mid] == 0) {
				swap(nums, low, mid);
				low++;
				mid++;
			} else if(nums[mid] == 1) {
				mid++;
			} else {
				swap(nums, mid, high);
				high--;
			}
			
		}
	} 
	public static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
		
	}
	public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      SORT COLORS — Test Cases            ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── Test 1: Classic LeetCode example ──────────────────
        runTest(1, new int[]{2, 0, 2, 1, 1, 0});

        // ── Test 2: Already sorted ────────────────────────────
        runTest(2, new int[]{0, 0, 1, 1, 2, 2});

        // ── Test 3: Reverse sorted ────────────────────────────
        runTest(3, new int[]{2, 2, 1, 1, 0, 0});

        // ── Test 4: All same color ────────────────────────────
        runTest(4, new int[]{1, 1, 1, 1});

        // ── Test 5: Single element ────────────────────────────
        runTest(5, new int[]{0});

        // ── Test 6: Mixed with many 2s ────────────────────────
        runTest(6, new int[]{2, 1, 0, 2, 0, 1, 2});

        // ── DETAILED TRACE for Test 1 ─────────────────────────
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   DETAILED STEP-BY-STEP TRACE             ║");
        System.out.println("║   Input: [2, 0, 2, 1, 1, 0]              ║");
        System.out.println("╚══════════════════════════════════════════╝\n");
        traceSort(new int[]{2, 0, 2, 1, 1, 0});
    }

    // ─────────────────────────────────────────────────────────
    //  Helper: run a test and print before/after
    // ─────────────────────────────────────────────────────────
    private static void runTest(int testNum, int[] nums) {
        System.out.println("Test " + testNum + ":");
        System.out.println("  Before: " + Arrays.toString(nums));
        sortColors(nums);
        System.out.println("  After : " + Arrays.toString(nums));
        System.out.println();
    }

    // ─────────────────────────────────────────────────────────
    //  Helper: detailed trace showing every step
    // ─────────────────────────────────────────────────────────
    private static void traceSort(int[] nums) {
        int low  = 0;
        int mid  = 0;
        int high = nums.length - 1;
        int step = 1;

        System.out.printf("  Initial state : %s%n", Arrays.toString(nums));
        System.out.printf("  Pointers      : low=%d  mid=%d  high=%d%n%n", low, mid, high);

        while (mid <= high) {
            System.out.printf("  ── Step %d ──────────────────────────────%n", step++);
            System.out.printf("  Array         : %s%n", Arrays.toString(nums));
            System.out.printf("  Pointers      : low=%d  mid=%d  high=%d%n", low, mid, high);
            System.out.printf("  nums[mid]     : %d%n", nums[mid]);

            if (nums[mid] == 0) {
                System.out.printf("  Action        : 0 found → swap(low=%d, mid=%d), low++, mid++%n", low, mid);
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                System.out.printf("  Action        : 1 found → already in place, mid++%n");
                mid++;
            } else {
                System.out.printf("  Action        : 2 found → swap(mid=%d, high=%d), high--%n", mid, high);
                swap(nums, mid, high);
                high--;
            }

            System.out.printf("  After step    : %s%n%n", Arrays.toString(nums));
        }

        System.out.println("  ✓ Sorting complete!");
        System.out.printf("  Final result  : %s%n", Arrays.toString(nums));
    }
}
