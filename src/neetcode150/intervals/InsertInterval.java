package neetcode150.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [start_i, end_i] represents the start and the end time of the ith interval. intervals is initially sorted in ascending order by start_i.

You are given another interval newInterval = [start, end].

Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and also intervals still does not have any overlapping intervals. You may merge the overlapping intervals if needed.

Return intervals after adding newInterval.

Note: Intervals are non-overlapping if they have no common point. For example, [1,2] and [3,4] are non-overlapping, but [1,2] and [2,3] are overlapping.

Example 1:

Input: intervals = [[1,3],[4,6]], newInterval = [2,5]

Output: [[1,6]]
Example 2:

Input: intervals = [[1,2],[3,5],[9,10]], newInterval = [6,7]

Output: [[1,2],[3,5],[6,7],[9,10]]
Constraints:

0 <= intervals.length <= 1000
newInterval.length == intervals[i].length == 2
0 <= start <= end <= 1000
Time Complexity: O(n) — We visit each interval exactly once across the three phases. No sorting is needed since the input is already sorted.
Space Complexity: O(n) — The result list holds at most n + 1 intervals (all original intervals plus the new one in the worst case with no merges). 
The output array itself is the only extra space used.

Basically whenever you need to insert an interval divide them into 3
the one where endtime is less than new interval's start time, directly add it to the result
the one where start time is less than or equal to end time of new interval and add it to the result
the rest where new intervals end time < intervals start time

 */
public class InsertInterval {
	
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		
		int i = 0;
		int n = intervals.length;
		List<int[]> result = new ArrayList<>();
		while(i < n && intervals[i][1] < newInterval[0]) {
			result.add(intervals[i]);
			i++;
		}
		while(i < n && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
			newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			i++;
		}
		result.add(newInterval);
		while(i < n) {
			result.add(intervals[i]);
			i++;
		}
		return result.toArray(new int[result.size()][]);
			
	}
	
	  static String format(int[][] arr) {
	        StringBuilder sb = new StringBuilder("[");
	        for (int[] interval : arr) {
	            sb.append(Arrays.toString(interval)).append(", ");
	        }
	        if (arr.length > 0) sb.setLength(sb.length() - 2);
	        sb.append("]");
	        return sb.toString();
	    }

	    public static void main(String[] args) {

	        System.out.println("=== Insert New Interval ===\n");

	        // ── Test 1 ────────────────────────────────────────────────────────────
	        int[][] intervals1  = {{1,3},{6,9}};
	        int[]   newInterval1 = {2, 5};
	        System.out.println("Test 1:");
	        System.out.println("  Intervals   : " + format(intervals1));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval1));
	        System.out.println("  Output      : " + format(insert(intervals1, newInterval1)));

	        // ── Test 2 ────────────────────────────────────────────────────────────
	        int[][] intervals2   = {{1,2},{3,5},{6,7},{8,10},{12,16}};
	        int[]   newInterval2 = {4, 8};
	        System.out.println("\nTest 2:");
	        System.out.println("  Intervals   : " + format(intervals2));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval2));
	        System.out.println("  Output      : " + format(insert(intervals2, newInterval2)));

	        // ── Test 3: new interval goes BEFORE all existing ────────────────────
	        int[][] intervals3   = {{3,5},{7,9}};
	        int[]   newInterval3 = {1, 2};
	        System.out.println("\nTest 3 (insert at beginning):");
	        System.out.println("  Intervals   : " + format(intervals3));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval3));
	        System.out.println("  Output      : " + format(insert(intervals3, newInterval3)));

	        // ── Test 4: new interval goes AFTER all existing ─────────────────────
	        int[][] intervals4   = {{1,3},{4,6}};
	        int[]   newInterval4 = {8, 10};
	        System.out.println("\nTest 4 (insert at end):");
	        System.out.println("  Intervals   : " + format(intervals4));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval4));
	        System.out.println("  Output      : " + format(insert(intervals4, newInterval4)));

	        // ── Test 5: new interval swallows ALL existing ────────────────────────
	        int[][] intervals5   = {{1,2},{3,4},{5,6}};
	        int[]   newInterval5 = {0, 7};
	        System.out.println("\nTest 5 (new interval swallows everything):");
	        System.out.println("  Intervals   : " + format(intervals5));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval5));
	        System.out.println("  Output      : " + format(insert(intervals5, newInterval5)));

	        // ── Test 6: empty intervals array ────────────────────────────────────
	        int[][] intervals6   = {};
	        int[]   newInterval6 = {5, 7};
	        System.out.println("\nTest 6 (empty input):");
	        System.out.println("  Intervals   : " + format(intervals6));
	        System.out.println("  New Interval: " + Arrays.toString(newInterval6));
	        System.out.println("  Output      : " + format(insert(intervals6, newInterval6)));
	    }

}
