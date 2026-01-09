package blind75.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * Given meeting time intervals with start and end times [[s1,e1],[s2,e2],...], find the minimum number of conference rooms required.
Example: [[0, 30],[5, 10],[15, 20]] returns 2
 * 
 * 
 * The approach uses:

Sort meetings by start time
Use a min-heap to track end times
For each meeting, check if any room is available (earliest ending meeting)
If available, reuse that room; otherwise, allocate a new room

Sort by start time - Line up all meetings in order of when they start (earliest first)
Use a special box (min-heap) - This box always shows you which meeting will end first
For each meeting:

Check if any room is free (did a meeting already end?)
If YES: Reuse that room
If NO: Get a new room


The answer = The most rooms we're using at the same time

⏱️ Time Complexity: O(n log n)
Breaking it down:

Sorting: O(n log n) - where n = number of meetings
Processing each meeting: O(n)

Each meeting: check heap (O(log n)), remove from heap (O(log n)), add to heap (O(log n))
Total: n meetings × O(log n) = O(n log n)


Overall: O(n log n) + O(n log n) = O(n log n)

💾 Space Complexity: O(n)

Min-Heap: In worst case, all meetings overlap → heap stores n end times
Space: O(n)


 */

public class MeetingRoomsii {

	public int minMeetingRooms(int[][] meetings) {
		if(meetings == null || meetings.length < 1)
			return 0;
		if(meetings.length == 1) {
			return 1;
		}
		Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		for(int i = 0; i < meetings.length; i++) {
			int start = meetings[i][0];
			int end = meetings[i][1];
			
			if(!minHeap.isEmpty() && minHeap.peek() <= start) {
				minHeap.poll();
			}
			minHeap.add(end);
		
		}
		return minHeap.size();
	}
	
	 public static void main(String[] args) {
		 MeetingRoomsii solution = new MeetingRoomsii();
	        
	        // Test Case 1: Basic example - overlapping meetings
	        System.out.println("=== Test Case 1 ===");
	        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
	        System.out.print("Input: ");
	        printIntervals(intervals1);
	        int result1 = solution.minMeetingRooms(intervals1);
	        System.out.println("Output: " + result1 + " rooms needed");
	        System.out.println("Explanation: Meeting [0,30] needs room 1, [5,10] needs room 2 (overlaps with first), [15,20] can use room 2 after [5,10] ends\n");
	        
	        // Test Case 2: No overlapping meetings
	        System.out.println("=== Test Case 2 ===");
	        int[][] intervals2 = {{7, 10}, {2, 4}};
	        System.out.print("Input: ");
	        printIntervals(intervals2);
	        int result2 = solution.minMeetingRooms(intervals2);
	        System.out.println("Output: " + result2 + " room needed");
	        System.out.println("Explanation: [2,4] ends before [7,10] starts, so only 1 room needed\n");
	        
	        // Test Case 3: All meetings at same time
	        System.out.println("=== Test Case 3 ===");
	        int[][] intervals3 = {{1, 5}, {1, 5}, {1, 5}};
	        System.out.print("Input: ");
	        printIntervals(intervals3);
	        int result3 = solution.minMeetingRooms(intervals3);
	        System.out.println("Output: " + result3 + " rooms needed");
	        System.out.println("Explanation: All meetings start at same time, need 3 separate rooms\n");
	        
	        // Test Case 4: Sequential meetings (back-to-back)
	        System.out.println("=== Test Case 4 ===");
	        int[][] intervals4 = {{1, 5}, {5, 10}, {10, 15}};
	        System.out.print("Input: ");
	        printIntervals(intervals4);
	        int result4 = solution.minMeetingRooms(intervals4);
	        System.out.println("Output: " + result4 + " room needed");
	        System.out.println("Explanation: Each meeting starts exactly when previous ends, can reuse same room\n");
	        
	        // Test Case 5: Complex overlapping scenario
	        System.out.println("=== Test Case 5 ===");
	        int[][] intervals5 = {{1, 4}, {2, 5}, {3, 6}, {7, 9}};
	        System.out.print("Input: ");
	        printIntervals(intervals5);
	        int result5 = solution.minMeetingRooms(intervals5);
	        System.out.println("Output: " + result5 + " rooms needed");
	        System.out.println("Explanation: [1,4], [2,5], [3,6] all overlap, need 3 rooms. [7,9] can reuse any room\n");
	        
	        // Test Case 6: Single meeting
	        System.out.println("=== Test Case 6 ===");
	        int[][] intervals6 = {{10, 20}};
	        System.out.print("Input: ");
	        printIntervals(intervals6);
	        int result6 = solution.minMeetingRooms(intervals6);
	        System.out.println("Output: " + result6 + " room needed");
	        System.out.println("Explanation: Only one meeting, needs only 1 room\n");
	    }
	    
	    // Helper method to print intervals
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
}
