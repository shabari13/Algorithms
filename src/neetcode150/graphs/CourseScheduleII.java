package neetcode150.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. If it's not possible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 3, prerequisites = [[1,0]]

Output: [0,1,2]
Explanation: We must ensure that course 0 is taken before course 1.

Example 2:

Input: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]

Output: []
Explanation: It's impossible to finish all courses.

Constraints:

1 <= numCourses <= 1000
0 <= prerequisites.length <= 1000
All prerequisite pairs are unique.
Time Complexity

Let:

V = number of courses

E = number of prerequisites

Building graph:

O(V + E)

BFS processing:

Each node once → O(V)
Each edge once → O(E)

Total:
O(V + E)
📦 Space Complexity

Graph → O(V + E)

Indegree array → O(V)

Queue → O(V)

Result → O(V)
Count how many prerequisites each course has.


Start with courses that have no prerequisites (they are free to take).

Once we “finish” a course, we reduce the prerequisite count of the courses that depend on it.

If any of those courses now have 0 prerequisites, we can take them next.

If we are able to finish all courses → return the order.

If not → there is a cycle → return empty array.

This is called Topological Sorting using BFS (Kahn’s Algorithm).


 */
public class CourseScheduleII {
	
	public static int[] findOrder(int noOfCourses, int[][] preRequisites) {
		List<List<Integer>> graph = new ArrayList<>();
		int[] inDegree = new int[noOfCourses];
		for(int i = 0 ; i < noOfCourses; i++) {
			graph.add(new ArrayList<>());
		}
		//List<Integer> result = new ArrayList<>();
		for(int[] preRquisite : preRequisites) {
			int course = preRquisite[0];
			int preReq = preRquisite[1];
			graph.get(preReq).add(course);
			inDegree[course]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0 ; i < noOfCourses; i++) {
			if(inDegree[i] == 0) 
				queue.add(i);
				//result.add(i);
		}
		int coursesTaken = 0;
		int[] result = new int[noOfCourses];
        int index = 0;
		while(!queue.isEmpty()) {
			int currentCourse = queue.poll();
			//result.add(currentCourse);
			 result[index++] = currentCourse;
			coursesTaken++;
			for(int neighbor: graph.get(currentCourse)) {
				inDegree[neighbor]--;
				if(inDegree[neighbor] == 0) {
					queue.offer(neighbor);
					
					
				}
			}
			
		}
		return (coursesTaken == noOfCourses) ? /*result.stream()
                .mapToInt(Integer::intValue)
                .toArray()*/ result: new int[0];
	}
	
	 public static void main(String[] args) {

	        // Test Case 1
	        int numCourses1 = 2;
	        int[][] prereq1 = {{1, 0}};
	        System.out.println("Test Case 1:");
	        System.out.println(Arrays.toString(findOrder(numCourses1, prereq1)));

	        // Test Case 2
	        int numCourses2 = 4;
	        int[][] prereq2 = {{1,0},{2,0},{3,1},{3,2}};
	        System.out.println("Test Case 2:");
	        System.out.println(Arrays.toString(findOrder(numCourses2, prereq2)));

	        // Test Case 3 (Cycle case)
	        int numCourses3 = 2;
	        int[][] prereq3 = {{1,0},{0,1}};
	        System.out.println("Test Case 3 (Cycle):");
	        System.out.println(Arrays.toString(findOrder(numCourses3, prereq3)));
	    }
	 

}
