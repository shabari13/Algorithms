package blind75.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
Example 3:

Input: intervals = [[4,7],[1,4]]
Output: [[1,7]]
Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 * 
 * O(n log n)

Sorting: O(n log n) - We sort all intervals
Merging: O(n) - We go through each interval once
Total: O(n log n) because sorting is the slowest part


💾 Space Complexity
O(n)

We create a merged list that could store all intervals in the worst case
If no intervals overlap, we store all n intervals
Step 1: Sort the intervals
First, we arrange all intervals by their start time (like lining up toys from smallest to biggest).
Step 2: Start merging

Take the first interval and put it in our "merged" list
Look at each next interval one by one
Ask: "Does this overlap with the last one we merged?"

YES: Extend the end time to include both
NO: Add it as a new separate interval





 */

public class MergeInterval {
	
	public int[][] merge(int[][] intervals) {
		
		List<int[]> result = new ArrayList<>();
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		int n = intervals.length;
		result.add(intervals[0]);
		
		for(int i = 1; i < intervals.length; i++) {
			int[] lastInterval = result.get(result.size() - 1);
			int[] currentInterval = intervals[i];
			if(currentInterval[0] <= lastInterval[1]) {
				lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
				
			} else {
				result.add(currentInterval);
			}
			
		}
		return result.toArray(new int[result.size()][]);
		
	}
	// Helper method to print intervals
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
    	MergeInterval solution = new MergeInterval();
        
        // DEMONSTRATION: Why merged.get(i-1) won't work
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("DEMONSTRATION: Why we CANNOT use merged.get(i-1)");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        int[][] demo = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.print("Input: ");
        printIntervals(demo);
        System.out.println();
        solution.merge(demo);
        
        System.out.println("\n═══════════════════════════════════════════════════════\n");
        
        // Test Case 1: Basic overlapping intervals
        System.out.println("Test Case 1:");
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.print("Input:  ");
        printIntervals(intervals1);
        int[][] result1 = solution.merge(intervals1);
        System.out.print("Output: ");
        printIntervals(result1);
        System.out.println();
        
        // SPECIAL: Test case showing why i & i+1 won't work
        System.out.println("Special Test - Multiple Consecutive Overlaps:");
        int[][] intervals_special = {{1, 4}, {2, 5}, {3, 6}};
        System.out.print("Input:  ");
        printIntervals(intervals_special);
        int[][] result_special = solution.merge(intervals_special);
        System.out.print("Output: ");
        printIntervals(result_special);
        System.out.println("(All three merge into ONE interval [1,6])\n");
        
        // Test Case 2: All overlapping intervals
        System.out.println("Test Case 2:");
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.print("Input:  ");
        printIntervals(intervals2);
        int[][] result2 = solution.merge(intervals2);
        System.out.print("Output: ");
        printIntervals(result2);
        System.out.println();
        
        // Test Case 3: No overlapping intervals
        System.out.println("Test Case 3:");
        int[][] intervals3 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.print("Input:  ");
        printIntervals(intervals3);
        int[][] result3 = solution.merge(intervals3);
        System.out.print("Output: ");
        printIntervals(result3);
        System.out.println();
        
        // Test Case 4: Completely overlapping intervals
        System.out.println("Test Case 4:");
        int[][] intervals4 = {{1, 10}, {2, 6}, {8, 9}};
        System.out.print("Input:  ");
        printIntervals(intervals4);
        int[][] result4 = solution.merge(intervals4);
        System.out.print("Output: ");
        printIntervals(result4);
        System.out.println();
        
        // Test Case 5: Single interval
        System.out.println("Test Case 5:");
        int[][] intervals5 = {{1, 5}};
        System.out.print("Input:  ");
        printIntervals(intervals5);
        int[][] result5 = solution.merge(intervals5);
        System.out.print("Output: ");
        printIntervals(result5);
    }

}
