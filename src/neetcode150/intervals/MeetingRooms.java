package neetcode150.intervals;

import java.util.Arrays;
/*
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their schedule without any conflicts.

Note: (0,8),(8,10) is not considered a conflict at 8

Example 1:

Input: intervals = [(0,30),(5,10),(15,20)]

Output: false
Explanation:

(0,30) and (5,10) will conflict
(0,30) and (15,20) will conflict
Example 2:

Input: intervals = [(5,8),(9,15)]

Output: true
Constraints:

0 <= intervals.length <= 500
0 <= intervals[i].start < intervals[i].end <= 1,000,000
Time Complexity
Detailed Analysis:

Sorting: O(n log n)

We use Arrays.sort() which implements a variant of quicksort/mergesort
For n intervals, this takes O(n log n) time


Linear scan: O(n)

We iterate through the array once: for (int i = 0; i < intervals.length - 1; i++)
We perform a constant-time comparison in each iteration
Total: O(n)


Overall: O(n log n) + O(n) = O(n log n)

The sorting dominates the time complexity



Time Complexity: O(n log n) where n is the number of intervals

Space Complexity
Detailed Analysis:

Sorting space: O(log n) or O(n)

Arrays.sort() uses O(log n) stack space for recursion (if using quicksort)
Some implementations might use O(n) for merge sort
In Java, typically O(log n) for primitive arrays, O(n) for object arrays


Variables: O(1)

We only use a constant amount of extra space for variable i
No additional data structures


Overall: O(log n) to O(n) depending on sort implementation

Space Complexity: O(log n) (or O(n) in worst case) for sorting
 */
public class MeetingRooms {
	static class Interval {
        int start;
        int end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
	
	public boolean canAttendMeetings(Interval[] intervals) {
		if(intervals.length <= 1)
			return true;
		Arrays.sort(intervals, (a,b) -> a.start - b.start);
		Interval lastInterval = intervals[0];
		for(int i = 1; i < intervals.length; i++) {
			Interval currentInterval = intervals[i];
			if(lastInterval.end > currentInterval.start)
				return false;
			else
				lastInterval=currentInterval;
		}
		return true;
	}

	 public static void main(String[] args) {
	        MeetingRooms solution = new MeetingRooms();
	        
	        // Test Case 1: Overlapping meetings
	        System.out.println("=== Test Case 1: Overlapping Meetings ===");
	        Interval[] intervals1 = {
	            new Interval(0, 30),
	            new Interval(5, 10),
	            new Interval(15, 20)
	        };
	        System.out.println("Input: " + Arrays.toString(intervals1));
	        boolean result1 = solution.canAttendMeetings(intervals1);
	        System.out.println("Output: " + result1);
	        System.out.println("Explanation: Meetings overlap, cannot attend all");
	        System.out.println();
	        
	        // Test Case 2: Non-overlapping meetings
	        System.out.println("=== Test Case 2: Non-overlapping Meetings ===");
	        Interval[] intervals2 = {
	            new Interval(5, 8),
	            new Interval(9, 15)
	        };
	        System.out.println("Input: " + Arrays.toString(intervals2));
	        boolean result2 = solution.canAttendMeetings(intervals2);
	        System.out.println("Output: " + result2);
	        System.out.println("Explanation: No overlaps, can attend all meetings");
	        System.out.println();
	        
	        // Test Case 3: Adjacent meetings (touching but not overlapping)
	        System.out.println("=== Test Case 3: Adjacent Meetings ===");
	        Interval[] intervals3 = {
	            new Interval(0, 8),
	            new Interval(8, 10),
	            new Interval(10, 15)
	        };
	        System.out.println("Input: " + Arrays.toString(intervals3));
	        boolean result3 = solution.canAttendMeetings(intervals3);
	        System.out.println("Output: " + result3);
	        System.out.println("Explanation: Meetings touch at boundaries but don't overlap");
	        System.out.println();
	        
	        // Test Case 4: Single meeting
	        System.out.println("=== Test Case 4: Single Meeting ===");
	        Interval[] intervals4 = {
	            new Interval(10, 20)
	        };
	        System.out.println("Input: " + Arrays.toString(intervals4));
	        boolean result4 = solution.canAttendMeetings(intervals4);
	        System.out.println("Output: " + result4);
	        System.out.println("Explanation: Only one meeting, no conflicts possible");
	        System.out.println();
	        
	        // Test Case 5: Empty input
	        System.out.println("=== Test Case 5: Empty Input ===");
	        Interval[] intervals5 = {};
	        System.out.println("Input: " + Arrays.toString(intervals5));
	        boolean result5 = solution.canAttendMeetings(intervals5);
	        System.out.println("Output: " + result5);
	        System.out.println("Explanation: No meetings to attend");
	        System.out.println();
	        
	        // Test Case 6: Multiple overlapping meetings
	        System.out.println("=== Test Case 6: Multiple Overlaps ===");
	        Interval[] intervals6 = {
	            new Interval(7, 10),
	            new Interval(2, 4),
	            new Interval(3, 6)
	        };
	        System.out.println("Input (unsorted): " + Arrays.toString(intervals6));
	        boolean result6 = solution.canAttendMeetings(intervals6);
	        System.out.println("Output: " + result6);
	        System.out.println("Explanation: After sorting [2,4] and [3,6] overlap at time 3");
	        System.out.println();
	        
	        // Test Case 7: Complex non-overlapping
	        System.out.println("=== Test Case 7: Complex Non-overlapping ===");
	        Interval[] intervals7 = {
	            new Interval(1, 5),
	            new Interval(6, 10),
	            new Interval(11, 15),
	            new Interval(16, 20)
	        };
	        System.out.println("Input: " + Arrays.toString(intervals7));
	        boolean result7 = solution.canAttendMeetings(intervals7);
	        System.out.println("Output: " + result7);
	        System.out.println("Explanation: All meetings are separated with gaps");
	        System.out.println();
	        
	        // Test Case 8: Detailed overlap example
	        System.out.println("=== Test Case 8: Detailed Overlap Analysis ===");
	        Interval[] intervals8 = {
	            new Interval(13, 15),
	            new Interval(1, 13),
	            new Interval(6, 9)
	        };
	        System.out.println("Input (unsorted): " + Arrays.toString(intervals8));
	        System.out.println("After sorting by start time:");
	        Arrays.sort(intervals8, (a, b) -> a.start - b.start);
	        for (Interval interval : intervals8) {
	            System.out.println("  " + interval);
	        }
	        boolean result8 = solution.canAttendMeetings(intervals8);
	        System.out.println("Output: " + result8);
	        System.out.println("Explanation: [1,13] ends at 13, [6,9] starts at 6 (6 < 13), so they overlap");
	    }
}
