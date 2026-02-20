package neetcode150.intervals;

import java.util.Arrays;
/*
 * Given an array of intervals intervals where intervals[i] = [start_i, end_i], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note: Intervals are non-overlapping even if they have a common point. For example, [1, 3] and [2, 4] are overlapping, but [1, 2] and [2, 3] are non-overlapping.

Example 1:

Input: intervals = [[1,2],[2,4],[1,4]]

Output: 1
Explanation: After [1,4] is removed, the rest of the intervals are non-overlapping.

Example 2:

Input: intervals = [[1,2],[2,4]]

Output: 0
Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
-50000 <= starti < endi <= 50000
Time Complexity: O(n log n)

Sorting: O(n log n) — the dominant operation
Loop through intervals: O(n)
Total: O(n log n)

Space Complexity: O(1) (or O(log n) if you count the sorting space)

We only use a few variables: removedCount, lastEndTime
We don't create any additional data structures
The sorting space depends on the sorting algorithm implementation (typically O(log n) for quicksort's recursion stack)
 */
public class NonOverlappingIntervals {
	
	public static int eraseOverlapIntervals(int[][] intervals) {
		if(intervals.length <= 1)
			return 0;
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		
		int lastEndTime = intervals[0][1];
		int count = 0;
		for(int i = 1; i <intervals.length; i++) {
			int currentStart = intervals[i][0];
			int currentEndTime = intervals[i][1];
			if(currentStart < lastEndTime) {
				count++;
			} else {
				lastEndTime = currentEndTime;
			}
		}
		return count;
		
	}
	 public static void main(String[] args) {
	        System.out.println("=== Non-Overlapping Intervals Problem ===\n");
	        
	        // Test Case 1: Basic example with overlapping intervals
	        int[][] test1 = {{1, 2}, {2, 3}, {1, 3}};
	        System.out.println("Test Case 1:");
	        System.out.println("Input: " + Arrays.deepToString(test1));
	        System.out.println("Output: " + eraseOverlapIntervals(test1));
	        System.out.println("Explanation: We need to remove 1 interval (either [1,3] or [2,3])");
	        System.out.println();
	        
	        // Test Case 2: No overlapping intervals
	        int[][] test2 = {{1, 2}, {2, 3}, {3, 4}};
	        System.out.println("Test Case 2:");
	        System.out.println("Input: " + Arrays.deepToString(test2));
	        System.out.println("Output: " + eraseOverlapIntervals(test2));
	        System.out.println("Explanation: All intervals are non-overlapping, remove 0");
	        System.out.println();
	        
	        // Test Case 3: All overlapping intervals
	        int[][] test3 = {{1, 5}, {2, 4}, {3, 5}, {4, 6}};
	        System.out.println("Test Case 3:");
	        System.out.println("Input: " + Arrays.deepToString(test3));
	        System.out.println("Output: " + eraseOverlapIntervals(test3));
	        System.out.println("Explanation: Multiple overlaps, we keep [1,5], remove others");
	        System.out.println();
	        
	        // Test Case 4: Single interval
	        int[][] test4 = {{1, 2}};
	        System.out.println("Test Case 4:");
	        System.out.println("Input: " + Arrays.deepToString(test4));
	        System.out.println("Output: " + eraseOverlapIntervals(test4));
	        System.out.println("Explanation: Only 1 interval, nothing to remove");
	        System.out.println();
	        
	        // Test Case 5: Complex overlapping pattern
	        int[][] test5 = {{0, 2}, {1, 3}, {1, 2}, {2, 3}, {3, 4}, {1, 4}};
	        System.out.println("Test Case 5:");
	        System.out.println("Input: " + Arrays.deepToString(test5));
	        System.out.println("Output: " + eraseOverlapIntervals(test5));
	        System.out.println("Explanation: Multiple overlaps, greedy keeps earliest ending intervals");
	        System.out.println();
	        
	        // Test Case 6: Intervals with negative numbers
	        int[][] test6 = {{-5, -2}, {-3, 0}, {0, 2}, {1, 3}};
	        System.out.println("Test Case 6:");
	        System.out.println("Input: " + Arrays.deepToString(test6));
	        System.out.println("Output: " + eraseOverlapIntervals(test6));
	        System.out.println("Explanation: Handles negative numbers, [1,3] overlaps with [0,2]");
	        System.out.println();
	        
	        // Test Case 7: Empty overlap (touching intervals)
	        int[][] test7 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
	        System.out.println("Test Case 7:");
	        System.out.println("Input: " + Arrays.deepToString(test7));
	        System.out.println("Output: " + eraseOverlapIntervals(test7));
	        System.out.println("Explanation: Touching at points is NOT overlap, all kept");
	    }

}
