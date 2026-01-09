package blind75.intervals;

import java.util.Arrays;
/*
 * 
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

 Time Complexity
O(n log n) where n = number of intervals
Why?

Sorting the intervals takes O(n log n) time
Going through each interval once takes O(n) time
Total: O(n log n) + O(n) = O(n log n)


💾 Space Complexity
O(1) or O(log n) depending on the sorting algorithm
Why?

We only use a few variables: count, lastEnd, i → constant space O(1)
However, sorting might use O(log n) space for the recursion stack (in Java's sort)
We don't create any new data structures that grow with input size

Step 1: Sort by Finish Time 🏁
Think of it like lining up your toys by when you finish playing with them. We put all the meetings in order based on when they END (not when they start!).
Why? Because the meeting that ends earliest leaves you the most time for other meetings later!
Step 2: Be Greedy (Pick Smartly) 🍪
Start with the first meeting. Then look at each next meeting:

If it starts AFTER the previous one ends → KEEP IT! ✅
If it starts BEFORE the previous one ends → CANCEL IT! ❌ (it overlaps!)


 */
public class NonOverlappingIntervals {

	public int eraseOverlapIntervals(int[][] intervals) {
		
		if(intervals == null || intervals.length == 0) {
			return 0;
		}
		
		Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
		int n = intervals.length;
		int lastEnd = intervals[0][1];
		int count = 0;
		for(int i = 1; i< n; i++) {
			if(lastEnd > intervals[i][0])
				count++;
			else {
				lastEnd = intervals[i][1];
			}
		}
		return count;
		
	}
	
	 public static void main(String[] args) {
	        NonOverlappingIntervals solution = new NonOverlappingIntervals();
	        
	        // Test Case 1
	        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
	        System.out.println("Test Case 1:");
	        System.out.println("Input: " + Arrays.deepToString(intervals1));
	        System.out.println("Output: " + solution.eraseOverlapIntervals(intervals1));
	        System.out.println();
	        
	        // Test Case 2
	        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
	        System.out.println("Test Case 2:");
	        System.out.println("Input: " + Arrays.deepToString(intervals2));
	        System.out.println("Output: " + solution.eraseOverlapIntervals(intervals2));
	        System.out.println();
	        
	        // Test Case 3
	        int[][] intervals3 = {{1, 2}, {2, 3}};
	        System.out.println("Test Case 3:");
	        System.out.println("Input: " + Arrays.deepToString(intervals3));
	        System.out.println("Output: " + solution.eraseOverlapIntervals(intervals3));
	        System.out.println();
	        
	        // Test Case 4
	        int[][] intervals4 = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};
	        System.out.println("Test Case 4:");
	        System.out.println("Input: " + Arrays.deepToString(intervals4));
	        System.out.println("Output: " + solution.eraseOverlapIntervals(intervals4));
	        System.out.println();
	        
	        // Test Case 5 - No overlapping
	        int[][] intervals5 = {{1, 2}, {3, 4}, {5, 6}};
	        System.out.println("Test Case 5:");
	        System.out.println("Input: " + Arrays.deepToString(intervals5));
	        System.out.println("Output: " + solution.eraseOverlapIntervals(intervals5));
	    }
}
