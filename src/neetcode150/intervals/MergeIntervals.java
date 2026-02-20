package neetcode150.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

You may return the answer in any order.

Note: Intervals are non-overlapping if they have no common point. For example, [1, 2] and [3, 4] are non-overlapping, but [1, 2] and [2, 3] are overlapping.

Example 1:

Input: intervals = [[1,3],[1,5],[6,7]]

Output: [[1,5],[6,7]]
Example 2:

Input: intervals = [[1,2],[2,3]]

Output: [[1,3]]
Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= start <= end <= 1000

Time Complexity: O(n log n)

Sorting takes O(n log n) where n is the number of intervals
The loop that merges intervals runs once through all intervals in O(n)
Total: O(n log n) + O(n) = O(n log n) because sorting dominates

Space Complexity: O(1) or O(n)

If we don't count the output array: O(1) space (excluding the input and output)
If we count the output array: O(n) space because in the worst case (no overlapping intervals), we return n intervals
The sorting algorithm might use O(log n) space for the recursion stack (depending on the implementation)

Basically sort the intervals first based on their start time. 
Then add the first element of the intervals array to a list
then iterate through all the elements , check if the next intervals start time is<= lastintervals end time.
If yes then make last intervals end time = math.max(last intervals end time and intervals end time.
otherwise just merge it to the list and return the list which is converted to an array

 */
public class MergeIntervals {
	public static int[][] merge(int[][] intervals){
		if(intervals.length <= 1)
			return intervals;
		List<int[]> merged = new ArrayList<>();
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		merged.add(intervals[0]);
		for(int i = 1; i < intervals.length; i++) {
			int[] lastInterval = merged.get(merged.size() - 1);
			if(intervals[i][0] <= lastInterval[1]) {
				lastInterval[1] = Math.max(intervals[i][1], lastInterval[1]);
			} else {
				merged.add(intervals[i]);
			}
			
		}
		return merged.toArray(new int[merged.size()][]);	
	}
	
	 public static void main(String[] args) {
	        // Test case 1
	        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
	        System.out.println("Test case 1:");
	        System.out.println("Input: " + Arrays.deepToString(intervals1));
	        int[][] result1 = merge(intervals1);
	        System.out.println("Output: " + Arrays.deepToString(result1));
	        System.out.println();
	        
	        // Test case 2
	        int[][] intervals2 = {{1, 4}, {4, 5}};
	        System.out.println("Test case 2:");
	        System.out.println("Input: " + Arrays.deepToString(intervals2));
	        int[][] result2 = merge(intervals2);
	        System.out.println("Output: " + Arrays.deepToString(result2));
	        System.out.println();
	        
	        // Test case 3
	        int[][] intervals3 = {{1, 5}};
	        System.out.println("Test case 3:");
	        System.out.println("Input: " + Arrays.deepToString(intervals3));
	        int[][] result3 = merge(intervals3);
	        System.out.println("Output: " + Arrays.deepToString(result3));
	        System.out.println();
	        
	        // Test case 4
	        int[][] intervals4 = {{2, 3}, {4, 6}, {1, 2}, {8, 9}};
	        System.out.println("Test case 4:");
	        System.out.println("Input: " + Arrays.deepToString(intervals4));
	        int[][] result4 = merge(intervals4);
	        System.out.println("Output: " + Arrays.deepToString(result4));
	        System.out.println();
	        
	        // Test case 5
	        int[][] intervals5 = {{0, 0}};
	        System.out.println("Test case 5:");
	        System.out.println("Input: " + Arrays.deepToString(intervals5));
	        int[][] result5 = merge(intervals5);
	        System.out.println("Output: " + Arrays.deepToString(result5));
	        System.out.println();
	        
	        // Test case 6
	        int[][] intervals6 = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
	        System.out.println("Test case 6:");
	        System.out.println("Input: " + Arrays.deepToString(intervals6));
	        int[][] result6 = merge(intervals6);
	        System.out.println("Output: " + Arrays.deepToString(result6));
	    }
	
}
