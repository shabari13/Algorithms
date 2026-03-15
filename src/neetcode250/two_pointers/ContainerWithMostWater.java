package neetcode250.two_pointers;
/*
 * You are given an integer array heights where heights[i] represents the height of the 
i
t
h
i 
th
  bar.

You may choose any two bars to form a container. Return the maximum amount of water a container can store.

Example 1:

Input: height = [1,7,2,5,4,7,3,6]

Output: 36
Example 2:

Input: height = [2,2,2]

Output: 4
Constraints:

2 <= height.length <= 1000
0 <= height[i] <= 1000

⏱ TimeO(n)Each pointer moves inward exactly once. Total moves = n−1. We never revisit an index.🧠 SpaceO(1)Only a fixed number of variables used (left, right, maxWater, width, currentHeight). No extra arrays or recursion stack needed.
 */
public class ContainerWithMostWater {
	
	public static int maxAreaVerbose(int[] heights) {
		int left = 0;
		int right = heights.length - 1;
		int area = 0;
		while(left < right) {
			int currentArea = (right - left) * Math.min(heights[left], heights[right]);
			area = Math.max(area,  currentArea);
			if(heights[left] < heights[right]) {
				left++;
			} else {
				right--;
			}
			
		}
		return area;
	}


    //  Main – sample runs
    // ─────────────────────────────────────────────
    public static void main(String[] args) {

        // ── Test Case 1 (classic LeetCode example) ──────────────────────────
        int[] heights1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Test Case 1: [1, 8, 6, 2, 5, 4, 8, 3, 7]");
        System.out.println("Expected answer: 49  (between index 1 (h=8) and index 8 (h=7))");
        System.out.println("───────────────────────────────────────────────────────");
        int result1 = maxAreaVerbose(heights1);
        System.out.println("  ➜  Max Water = " + result1);

        // ── Test Case 2 (only 2 bars) ────────────────────────────────────────
        int[] heights2 = {1, 1};
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("Test Case 2: [1, 1]");
        System.out.println("Expected answer: 1");
        System.out.println("───────────────────────────────────────────────────────");
        int result2 = maxAreaVerbose(heights2);
        System.out.println("  ➜  Max Water = " + result2);

        // ── Test Case 3 (ascending heights) ─────────────────────────────────
        int[] heights3 = {1, 2, 3, 4, 5};
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("Test Case 3: [1, 2, 3, 4, 5]");
        System.out.println("Expected answer: 6  (index 0 h=1, index 4 h=5 → 4×1=4; OR index 1 h=2, index 4 h=5 → 3×2=6)");
        System.out.println("───────────────────────────────────────────────────────");
        int result3 = maxAreaVerbose(heights3);
        System.out.println("  ➜  Max Water = " + result3);

        // ── Test Case 4 (all same height) ───────────────────────────────────
        int[] heights4 = {4, 4, 4, 4};
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("Test Case 4: [4, 4, 4, 4]");
        System.out.println("Expected answer: 12  (outermost bars: width=3, height=4)");
        System.out.println("───────────────────────────────────────────────────────");
        int result4 = maxAreaVerbose(heights4);
        System.out.println("  ➜  Max Water = " + result4);

        // ── Test Case 5 (large spike in middle) ──────────────────────────────
        int[] heights5 = {2, 3, 10, 5, 7, 8, 9};
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("Test Case 5: [2, 3, 10, 5, 7, 8, 9]");
        System.out.println("Expected answer: 36  (index 2 h=10, index 6 h=9 → 4×9=36)");
        System.out.println("───────────────────────────────────────────────────────");
        int result5 = maxAreaVerbose(heights5);
        System.out.println("  ➜  Max Water = " + result5);
    }
}
