package neetcode250.heap_priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.

Return the k closest points to the origin (0, 0).

The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).

You may return the answer in any order.

Example 1:



Input: points = [[0,2],[2,2]], k = 1

Output: [[0,2]]
Explanation : The distance between (0, 2) and the origin (0, 0) is 2. The distance between (2, 2) and the origin is sqrt(2^2 + 2^2) = 2.82842. So the closest point to the origin is (0, 2).

Example 2:

Input: points = [[0,2],[2,0],[2,2]], k = 2

Output: [[0,2],[2,0]]
Explanation: The output [2,0],[0,2] would also be accepted.

Constraints:

1 <= k <= points.length <= 1000
-100 <= points[i][0], points[i][1] <= 100

The Big Idea
The problem asks: given a list of points on a 2D plane, find the k points closest to the origin (0, 0). The distance from a point (x, y) to the origin is √(x² + y²), but since we're only comparing distances, we can skip the square root and just compare x² + y² — this is called the squared Euclidean distance. The trick is using a Max-Heap of size k: we keep at most k points in the heap at all times, and the heap's "top" is always the farthest point seen so far. For every new point, if it's closer than the current farthest, we kick that farthest out and insert the new one. After processing all points, the heap contains exactly the k closest.

Explain it like you're 5
Imagine you have a jar that can only hold 3 balls, and you want to keep the 3 lightest ones. You start filling the jar. When it gets full and a new ball comes in, you compare it with the heaviest ball already in the jar. If the new ball is lighter, you throw out the heavy one and put the new one in. If the new ball is heavier, you just ignore it. At the end, your jar has the 3 lightest balls! That's exactly what our max-heap does — it's the jar, and "weight" is the distance from the origin.

 PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );
 
why do we have (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]) not just b * b - a*a?

12:29 PM
Great question! The short answer is: a and b are not numbers — they are int[] arrays (points with x and y coordinates).

What a and b actually are
When you write PriorityQueue<int[]>, each element in the heap is an int[] — specifically a 2-element array representing one point, like [x, y].

So when the comparator receives a and b, they look like this:

a = [1, 3]   → a[0] = 1 (x), a[1] = 3 (y)
b = [-2, 2]  → b[0] = -2 (x), b[1] = 2 (y)
You cannot write b * b because b is an array reference, not a number. Java would give you a compile error: "operator * cannot be applied to int[]".



   Complexity Summary
==============================================
Time  : O(n log k)  — n points, each heap op is O(log k)
Space : O(k)        — heap holds at most k points at once
==============================================
Time: O(n log k). We iterate over all n points once. For each, we do at most one offer (O(log k)) and one poll (O(log k)). Total: O(n log k). This is strictly better than O(n log n) sorting whenever k < n.
Space: O(k). The heap holds at most k+1 elements at any instant (k after each cleanup). The result array is also k. No other significant memory is used.
 */
public class KClosestPointToOrigin {
	public static int[][] kClosest(int[][] points, int k) {
		
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] + a[1] * a[1]);
		for(int[] point : points) {
			maxHeap.offer(point);
			if(maxHeap.size() > k) {
				maxHeap.poll();
			}
		}
		int[][] result = new int[k][2];
		
		for(int i=0; i < k; i++) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	 private static String formatPoints(int[][] pts) {
	        StringBuilder sb = new StringBuilder("[");
	        for (int i = 0; i < pts.length; i++) {
	            sb.append(Arrays.toString(pts[i]));
	            if (i < pts.length - 1) sb.append(", ");
	        }
	        sb.append("]");
	        return sb.toString();
	    }
	 
	    // ------------------------------------------------------------------
	    // Helper: compute squared distance (just for display purposes).
	    // ------------------------------------------------------------------
	    private static int squaredDist(int[] p) {
	        return p[0]*p[0] + p[1]*p[1];
	    }
	 
	    // ------------------------------------------------------------------
	    // Main — exercises several test cases and prints detailed output.
	    // ------------------------------------------------------------------
	    public static void main(String[] args) {
	 
	        System.out.println("==============================================");
	        System.out.println("   K-Closest Points to Origin — Demo");
	        System.out.println("==============================================\n");
	 
	        // ============================================================
	        // TEST CASE 1
	        // Points: [1,3], [-2,2]   k = 1
	        //
	        // Squared distances:
	        //   [1,3]  → 1 + 9  = 10
	        //   [-2,2] → 4 + 4  =  8   ← closer
	        //
	        // Iteration walk-through (k=1, max-heap):
	        //   Process [1,3]:
	        //     heap is empty  → push [1,3]
	        //     heap = {[1,3]}  size=1 == k, no removal
	        //
	        //   Process [-2,2]:
	        //     push [-2,2]
	        //     heap = {[1,3], [-2,2]}  size=2 > k=1
	        //     heap root (farthest) = [1,3] with d=10
	        //     poll [1,3]  → heap = {[-2,2]}
	        //
	        //   Result: [[-2,2]]
	        // ============================================================
	        System.out.println("--- Test Case 1 ---");
	        int[][] points1 = {{1, 3}, {-2, 2}};
	        int k1 = 1;
	        System.out.println("Points : " + formatPoints(points1));
	        System.out.println("k      : " + k1);
	        System.out.println("\nIteration details:");
	        System.out.println("  [1,3]  → squared dist = " + squaredDist(new int[]{1, 3})
	                           + "  | heap empty (<k), push → heap: {[1,3]}");
	        System.out.println("  [-2,2] → squared dist = " + squaredDist(new int[]{-2, 2})
	                           + "   | push → heap: {[1,3],[-2,2]} size>k → pop farthest [1,3] → heap: {[-2,2]}");
	        int[][] result1 = kClosest(points1, k1);
	        System.out.println("\nResult : " + formatPoints(result1));
	        System.out.println("Expected: [[-2, 2]]");
	        System.out.println();
	 
	        // ============================================================
	        // TEST CASE 2
	        // Points: [3,3], [5,-1], [-2,4]   k = 2
	        //
	        // Squared distances:
	        //   [3,3]   → 9  + 9 = 18
	        //   [5,-1]  → 25 + 1 = 26
	        //   [-2,4]  → 4  +16 = 20
	        //
	        // Iteration walk-through (k=2, max-heap):
	        //   Process [3,3]:
	        //     heap empty → push [3,3]
	        //     heap = {[3,3]}  size=1 < k=2, no removal
	        //
	        //   Process [5,-1]:
	        //     push [5,-1]
	        //     heap = {[5,-1],[3,3]}  size=2 == k, no removal
	        //     (heap root = [5,-1] with d=26, the farther one)
	        //
	        //   Process [-2,4]:
	        //     push [-2,4]
	        //     heap size=3 > k=2
	        //     heap root (farthest) = [5,-1] with d=26
	        //     poll [5,-1]  → heap = {[-2,4],[3,3]}
	        //
	        //   Result: [[3,3],[-2,4]]  (order may vary)
	        // ============================================================
	        System.out.println("--- Test Case 2 ---");
	        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
	        int k2 = 2;
	        System.out.println("Points : " + formatPoints(points2));
	        System.out.println("k      : " + k2);
	        System.out.println("\nIteration details:");
	        System.out.println("  [3,3]   → squared dist = " + squaredDist(new int[]{3, 3})
	                           + "  | heap empty (<k), push → heap: {[3,3]}");
	        System.out.println("  [5,-1]  → squared dist = " + squaredDist(new int[]{5, -1})
	                           + "  | push → size=2 == k, no pop → heap: {[5,-1],[3,3]}");
	        System.out.println("  [-2,4]  → squared dist = " + squaredDist(new int[]{-2, 4})
	                           + "  | push → size=3 > k → pop farthest [5,-1](d=26) → heap: {[-2,4],[3,3]}");
	        int[][] result2 = kClosest(points2, k2);
	        System.out.println("\nResult : " + formatPoints(result2));
	        System.out.println("Expected (any order): [[3,3], [-2,4]]");
	        System.out.println();
	 
	        // ============================================================
	        // TEST CASE 3
	        // Points: [0,1], [1,0], [0,-1], [-1,0], [2,2]   k = 3
	        //
	        // Squared distances:
	        //   [0,1]  → 1
	        //   [1,0]  → 1
	        //   [0,-1] → 1
	        //   [-1,0] → 1
	        //   [2,2]  → 8
	        //
	        // All four axis points are equidistant (d=1) and all closer than [2,2](d=8).
	        // Any 3 of the first four points are valid answers.
	        // ============================================================
	        System.out.println("--- Test Case 3 ---");
	        int[][] points3 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {2, 2}};
	        int k3 = 3;
	        System.out.println("Points : " + formatPoints(points3));
	        System.out.println("k      : " + k3);
	        System.out.println("\nIteration details:");
	        System.out.println("  [0,1]  d=1  | size<k, push");
	        System.out.println("  [1,0]  d=1  | size<k, push");
	        System.out.println("  [0,-1] d=1  | push → size=3 == k, no pop (all equal)");
	        System.out.println("  [-1,0] d=1  | push → size=4 > k=3");
	        System.out.println("               heap root d=1 >= new d=1 → pop one of the d=1 points");
	        System.out.println("  [2,2]  d=8  | push → size=4 > k=3");
	        System.out.println("               heap root d=1 < new d=8 → pop [2,2] (farther)");
	        int[][] result3 = kClosest(points3, k3);
	        System.out.println("\nResult : " + formatPoints(result3));
	        System.out.println("Expected: any 3 of [0,1],[1,0],[0,-1],[-1,0]");
	        System.out.println();
	 
	        // ============================================================
	        // TEST CASE 4  — single point
	        // ============================================================
	        System.out.println("--- Test Case 4 (edge: single point) ---");
	        int[][] points4 = {{5, 5}};
	        int k4 = 1;
	        int[][] result4 = kClosest(points4, k4);
	        System.out.println("Points : " + formatPoints(points4));
	        System.out.println("k      : " + k4);
	        System.out.println("Result : " + formatPoints(result4));
	        System.out.println("Expected: [[5, 5]]");
	        System.out.println();
	 
	        // ============================================================
	        // TEST CASE 5  — k equals total number of points
	        // ============================================================
	        System.out.println("--- Test Case 5 (edge: k == n) ---");
	        int[][] points5 = {{1, 1}, {2, 2}, {3, 3}};
	        int k5 = 3;
	        int[][] result5 = kClosest(points5, k5);
	        System.out.println("Points : " + formatPoints(points5));
	        System.out.println("k      : " + k5);
	        System.out.println("Result : " + formatPoints(result5));
	        System.out.println("Expected (any order): [[1,1],[2,2],[3,3]]");
	 
	        System.out.println("\n==============================================");
	        System.out.println("   Complexity Summary");
	        System.out.println("==============================================");
	        System.out.println("Time  : O(n log k)  — n points, each heap op is O(log k)");
	        System.out.println("Space : O(k)        — heap holds at most k points at once");
	        System.out.println("==============================================");
	    }
}
