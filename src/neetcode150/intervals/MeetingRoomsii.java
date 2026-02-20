package neetcode150.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of rooms required to schedule all meetings without any conflicts.

Note: (0,8),(8,10) is NOT considered a conflict at 8.

Example 1:

Input: intervals = [(0,40),(5,10),(15,20)]

Output: 2
Explanation:
day1: (0,40)
day2: (5,10),(15,20)

Example 2:

Input: intervals = [(4,9)]

Output: 1
Constraints:

0 <= intervals.length <= 500
0 <= intervals[i].start < intervals[i].end <= 1,000,000
Sorting: O(N log N) where N is the number of meetings
Processing meetings: O(N log N)

We iterate through N meetings: O(N)
Each heap operation (peek, poll, offer) takes O(log N)
Total: O(N × log N)


Overall: O(N log N) + O(N log N) = O(N log N)

💾 Space Complexity: O(N)

Priority Queue: In the worst case, all meetings overlap, so the heap stores N end times: O(N)
Sorting: Java's sort uses O(log N) space for the call stack (in-place sorting)
Overall: O(N)

The key insight is to process meetings in chronological order (sorted by start time) and keep track of when each room becomes available. We use a Priority Queue (min-heap) to efficiently track the earliest ending meeting at any point. When a new meeting starts, we check if any room has become free (earliest ending meeting finished). If yes, we can reuse that room; otherwise, we need a new room. The maximum number of rooms we ever need simultaneously is our answer.
Basically you sort the start time and you can either sort/have a priority queue which will keep track of end time in a sorted order
then you check if the start time of an interval is >= PQ's smallest element . If it is remove that from the priority queue
ultimately return the size of the priority queue it shoudl resolved it.
 */
public class MeetingRoomsii {
	
	public int minMeetingRooms(int[][] intervals) {
		if(intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(intervals[0][1]);
		for(int i = 1; i < intervals.length; i++) {
			int start = intervals[i][0];
			int end = intervals[i][1];
			
			if(start >= pq.peek())
				pq.poll();
			pq.offer(end);
		}
		return pq.size();
	}
	
	 public static void main(String[] args) {
		 MeetingRoomsii solution = new MeetingRoomsii();
	        
	        // Test Case 1: Basic overlapping meetings
	        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
	        System.out.println("Test Case 1:");
	        System.out.println("Input: " + Arrays.deepToString(intervals1));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals1));
	        System.out.println("Explanation: We need 2 rooms. Meeting [0,30] and [5,10] overlap.\n");
	        
	        // Test Case 2: No overlapping meetings
	        int[][] intervals2 = {{7, 10}, {2, 4}};
	        System.out.println("Test Case 2:");
	        System.out.println("Input: " + Arrays.deepToString(intervals2));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals2));
	        System.out.println("Explanation: We need 1 room. Meetings don't overlap.\n");
	        
	        // Test Case 3: All meetings overlap
	        int[][] intervals3 = {{1, 5}, {2, 6}, {3, 7}, {4, 8}};
	        System.out.println("Test Case 3:");
	        System.out.println("Input: " + Arrays.deepToString(intervals3));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals3));
	        System.out.println("Explanation: We need 4 rooms. All meetings overlap.\n");
	        
	        // Test Case 4: Back-to-back meetings (can reuse room)
	        int[][] intervals4 = {{0, 5}, {5, 10}, {10, 15}};
	        System.out.println("Test Case 4:");
	        System.out.println("Input: " + Arrays.deepToString(intervals4));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals4));
	        System.out.println("Explanation: We need 1 room. Meetings are back-to-back.\n");
	        
	        // Test Case 5: Complex scenario
	        int[][] intervals5 = {{0, 30}, {5, 10}, {15, 20}, {25, 35}, {30, 40}};
	        System.out.println("Test Case 5:");
	        System.out.println("Input: " + Arrays.deepToString(intervals5));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals5));
	        System.out.println("Explanation: We need 2 rooms at peak time.\n");
	        
	        // Test Case 6: Single meeting
	        int[][] intervals6 = {{10, 20}};
	        System.out.println("Test Case 6:");
	        System.out.println("Input: " + Arrays.deepToString(intervals6));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals6));
	        System.out.println("Explanation: We need 1 room for a single meeting.\n");
	        
	        // Test Case 7: Empty input
	        int[][] intervals7 = {};
	        System.out.println("Test Case 7:");
	        System.out.println("Input: " + Arrays.deepToString(intervals7));
	        System.out.println("Output: " + solution.minMeetingRooms(intervals7));
	        System.out.println("Explanation: No meetings, so 0 rooms needed.\n");
	    }

}
