package neetcode150.graphs;
/*
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

The pair [0, 1], indicates that must take course 1 before taking course 0.

There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return true if it is possible to finish all courses, otherwise return false.

Example 1:

Input: numCourses = 2, prerequisites = [[0,1]]

Output: true
Explanation: First take course 1 (no prerequisites) and then take course 0.

Example 2:

Input: numCourses = 2, prerequisites = [[0,1],[1,0]]

Output: false
Explanation: In order to take course 1 you must take course 0, and to take course 0 you must take course 1. So it is impossible.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
prerequisites[i].length == 2
0 <= a[i], b[i] < numCourses
All prerequisite pairs are unique.

Basically you create a grpah which has relation between a course and the courses which are dependenet on this course. For ex preReq with {{1,0},{2,0},{3,1},{3,2}};
This will have o - {1, 2}
1 - > {3}
2 -> {2}
3-> {}
Then you create inDegree array meaning how many course one has to complete a course. 
here [0, 1, 1, 2]. Ex: course 3 has 2 prerequisites. Couese 2 has 1 preReq.
Then you have a queue which keeps track of course which has 0 prereq. 
you navigate through queue, remove element and increment a count of  no of courses taken.
If it matched the no of course in the end , all courses have been completed


 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
	
	public static  boolean canFinish(int noOfCourses, int[][] prerequisites) {
		
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0 ; i < noOfCourses; i++) {
			graph.add(new ArrayList<>());
		}
		int[] inDegree = new int[noOfCourses];
		for(int[] prerequisite : prerequisites) {
			int course = prerequisite[0];
			int preReq = prerequisite[1];
			graph.get(preReq).add(course);
			inDegree[course]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < noOfCourses; i++) {
			if(inDegree[i] == 0)
				queue.add(i);
		}
		int coursesTaken = 0;
		while(!queue.isEmpty()) {
			int currentCourse = queue.poll();
			coursesTaken++;
			for(int neighbor : graph.get(currentCourse)) {
				inDegree[neighbor]--;
				if(inDegree[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
			
		}
		return coursesTaken ==  noOfCourses;
		
	}

	 public static void main(String[] args) {

	        // Test Case 1
	        int numCourses1 = 2;
	        int[][] prereq1 = {{1, 0}};
	        System.out.println("Test Case 1: " + canFinish(numCourses1, prereq1));

	        // Test Case 2 (Cycle exists)
	        int numCourses2 = 2;
	        int[][] prereq2 = {{1, 0}, {0, 1}};
	        System.out.println("Test Case 2: " + canFinish(numCourses2, prereq2));

	        // Test Case 3
	        int numCourses3 = 4;
	        int[][] prereq3 = {{1,0},{2,0},{3,1},{3,2}};
	        System.out.println("Test Case 3: " + canFinish(numCourses3, prereq3));

	        // Test Case 4 (No prerequisites)
	        int numCourses4 = 3;
	        int[][] prereq4 = {};
	        System.out.println("Test Case 4: " + canFinish(numCourses4, prereq4));
	    }
}
