package blind75.intervals;

import java.util.Arrays;

/*
 * 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2 LeetCode],...] (si < ei), determine if a person could attend all meetings.
Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false

Example 2:

Input: [[7,10],[2,4]]
Output: true
 * 
 * Time Complexity: O(n log n)
Breaking it down:

Sorting: O(n log n) where n = number of meetings

Java's Arrays.sort() uses a modified merge sort/quicksort
This is the dominant operation


Checking for overlaps: O(n)

We loop through all meetings once
Each comparison is O(1)



Total: O(n log n) + O(n) = O(n log n)
Why? In Big O notation, we keep the largest term, and n log n grows faster than n.
Space Complexity: O(1) or O(log n)
It depends on the sorting algorithm:

O(1): If sorting is done in-place (which Arrays.sort() typically does for primitive arrays)
O(log n): If we count the recursive call stack used by the sorting algorithm


The Magic Trick:

First, organize your activities - Put them in order by when they START (like arranging toys from left to right)
Then check each pair - Look at each activity and the one after it
Ask: "Does the next activity start BEFORE the current one ends?"

If YES → You have a problem! You can't be in two places at once! 😢
If NO → Great! You can attend both! 😊
 */
public class MeetingRooms {
	
		public boolean canAttendMeetings(int[][] intervals) {
			if(intervals == null || intervals.length <= 1) {
				return true;
			}
			int n = intervals.length;
			Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
			for(int i =0; i < n - 1; i++) {
				if(intervals[i][1] > intervals[i+1][0]) {
					return false;
				}
				
			}
			return true;
		}
	
	 // Helper method to print the intervals
    private static void printIntervals(int[][] intervals) {
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
        MeetingRooms solution = new MeetingRooms();
        
        // Test Case 1: Overlapping meetings
        System.out.println("========== Test Case 1 ==========");
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.print("Input: ");
        printIntervals(intervals1);
        boolean result1 = solution.canAttendMeetings(intervals1);
        System.out.println("Output: " + result1);
        System.out.println("Explanation: Cannot attend all meetings - meeting [5,10] overlaps with [0,30]");
        System.out.println();
        
        // Test Case 2: Non-overlapping meetings
        System.out.println("========== Test Case 2 ==========");
        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.print("Input: ");
        printIntervals(intervals2);
        boolean result2 = solution.canAttendMeetings(intervals2);
        System.out.println("Output: " + result2);
        System.out.println("Explanation: Can attend all meetings - no overlaps exist");
        System.out.println();
        
        // Test Case 3: Back-to-back meetings (no overlap)
        System.out.println("========== Test Case 3 ==========");
        int[][] intervals3 = {{1, 5}, {5, 8}, {8, 10}};
        System.out.print("Input: ");
        printIntervals(intervals3);
        boolean result3 = solution.canAttendMeetings(intervals3);
        System.out.println("Output: " + result3);
        System.out.println("Explanation: Can attend all meetings - one ends exactly when next begins");
        System.out.println();
        
        // Test Case 4: Single meeting
        System.out.println("========== Test Case 4 ==========");
        int[][] intervals4 = {{1, 5}};
        System.out.print("Input: ");
        printIntervals(intervals4);
        boolean result4 = solution.canAttendMeetings(intervals4);
        System.out.println("Output: " + result4);
        System.out.println("Explanation: Only one meeting, no conflicts possible");
        System.out.println();
        
        // Test Case 5: Empty array
        System.out.println("========== Test Case 5 ==========");
        int[][] intervals5 = {};
        System.out.print("Input: ");
        printIntervals(intervals5);
        boolean result5 = solution.canAttendMeetings(intervals5);
        System.out.println("Output: " + result5);
        System.out.println("Explanation: No meetings to attend");
        System.out.println();
        
        // Test Case 6: Multiple overlapping meetings
        System.out.println("========== Test Case 6 ==========");
        int[][] intervals6 = {{1, 4}, {2, 5}, {3, 6}};
        System.out.print("Input: ");
        printIntervals(intervals6);
        boolean result6 = solution.canAttendMeetings(intervals6);
        System.out.println("Output: " + result6);
        System.out.println("Explanation: All meetings overlap with each other");
        System.out.println();
    }

}
