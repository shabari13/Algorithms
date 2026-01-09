package blind75.intervals;

import java.util.ArrayList;
import java.util.List;


/*
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Time Complexity: O(n)

We go through each interval exactly ONCE
n = number of intervals
Each operation (comparison, adding to result) takes O(1)
Total: O(n)

Why? Think of it like walking through a line of kids - you visit each kid once, no backtracking!
Space Complexity: O(n)

We create a result list that can hold at most n+1 intervals
In worst case (no merging), we have all original intervals plus the new one
O(n) space for the output

 // Step 1: Add all intervals that come BEFORE the new interval (no overlap)

 // Step 2: Merge all overlapping intervals with newInterval
 // Keep merging as long as current interval OVERLAPS with newInterval
     
 // Step 3: Add all remaining intervals that come AFTER (no overlap)
 * 
 * 
 */
public class InsertInterval {
	
	public int[][] insert(int[][] intervals, int[] newInterval){
		
		List<int[]> result = new ArrayList<>();
		
		int i = 0;
		
		int n = intervals.length;
		
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
	
	 // Helper method to print 2D array
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();
        
        // Test Case 1: Insert in the middle with merge
        System.out.println("Test Case 1:");
        int[][] intervals1 = {{1,3}, {6,9}};
        int[] newInterval1 = {2,5};
        System.out.print("Input: intervals = ");
        printIntervals(intervals1);
        System.out.println("       newInterval = [" + newInterval1[0] + "," + newInterval1[1] + "]");
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.print("Output: ");
        printIntervals(result1);
        System.out.println();
        
        // Test Case 2: Insert with multiple merges
        System.out.println("Test Case 2:");
        int[][] intervals2 = {{1,2}, {3,5}, {6,7}, {8,10}, {12,16}};
        int[] newInterval2 = {4,8};
        System.out.print("Input: intervals = ");
        printIntervals(intervals2);
        System.out.println("       newInterval = [" + newInterval2[0] + "," + newInterval2[1] + "]");
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.print("Output: ");
        printIntervals(result2);
        System.out.println();
        
        // Test Case 3: Insert at the beginning
        System.out.println("Test Case 3:");
        int[][] intervals3 = {{3,5}, {6,9}};
        int[] newInterval3 = {1,2};
        System.out.print("Input: intervals = ");
        printIntervals(intervals3);
        System.out.println("       newInterval = [" + newInterval3[0] + "," + newInterval3[1] + "]");
        int[][] result3 = solution.insert(intervals3, newInterval3);
        System.out.print("Output: ");
        printIntervals(result3);
        System.out.println();
        
        // Test Case 4: Insert at the end
        System.out.println("Test Case 4:");
        int[][] intervals4 = {{1,3}, {6,9}};
        int[] newInterval4 = {10,12};
        System.out.print("Input: intervals = ");
        printIntervals(intervals4);
        System.out.println("       newInterval = [" + newInterval4[0] + "," + newInterval4[1] + "]");
        int[][] result4 = solution.insert(intervals4, newInterval4);
        System.out.print("Output: ");
        printIntervals(result4);
        System.out.println();
        
        // Test Case 5: Empty intervals
        System.out.println("Test Case 5:");
        int[][] intervals5 = {};
        int[] newInterval5 = {5,7};
        System.out.print("Input: intervals = ");
        printIntervals(intervals5);
        System.out.println("       newInterval = [" + newInterval5[0] + "," + newInterval5[1] + "]");
        int[][] result5 = solution.insert(intervals5, newInterval5);
        System.out.print("Output: ");
        printIntervals(result5);
    }
			

}
