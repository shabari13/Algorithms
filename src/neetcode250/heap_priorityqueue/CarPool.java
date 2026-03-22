package neetcode250.heap_priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and a integer array trips where trips[i] = [numPassengers[i], from[i], to[i]] indicates that the ith trip has numPassengers[i] passengers and the locations to pick them up and drop them off are from[i] and to[i] respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

Example 1:

Input: trips = [[4,1,2],[3,2,4]], capacity = 4

Output: true
Example 2:

Input: trips = [[2,1,3],[3,2,4]], capacity = 4

Output: false
Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengers[i] <= 100
0 <= from[i] < to[i] <= 1000
1 <= capacity <= 100,000


The heap idea: sort trips by pickup stop, then ask "can I fit these new passengers given who has already left?"

Sort trips by their from stop (the order we encounter them).
Use a min-heap ordered by to stop to track who is currently in the car.
When we process a new trip, we first evict everyone whose to ≤ current from — they already got out.
Then we add the new passengers. If the total now exceeds capacity, return false.

The heap always shows us who gets out soonest (min by to), so we can efficiently remove them.

Time O(N log N)
Space O(N)


 */
public class CarPool {
	/**
     * HEAP (PRIORITY QUEUE) APPROACH
     *
     * Key idea:
     *   1. Sort trips by their FROM (pickup) stop.
     *   2. Use a min-heap ordered by TO (exit) stop.
     *      The heap tells us: "who gets out of the car soonest?"
     *   3. For each new trip:
     *      a. Evict everyone from the heap whose exit stop <= current pickup stop.
     *         (They already got out before this stop.)
     *      b. Add current trip to heap.
     *      c. If total passengers > capacity → return false.
     *   4. If we process all trips without overflow → return true.
     *
     * The heap stores int[] = { exitStop, numPassengers }
     * and is ordered by exitStop (min-heap on index 0).
     */
	public static boolean carPooling(int[][] trips, int capacity) {
	    Arrays.sort(trips, (m, n) -> m[1] - n[1]);
	    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
	    int totalPassengers = 0;

	    for (int[] trip : trips) {
	        int noOfPassengers = trip[0];
	        int fromStop       = trip[1];
	        int toStop         = trip[2];

	        // WHILE — evict ALL groups that have exited by this stop
	        while (!minHeap.isEmpty() && minHeap.peek()[0] <= fromStop) {
	            int[] departed = minHeap.poll();
	            totalPassengers -= departed[1];
	        }

	        totalPassengers += noOfPassengers;
	        minHeap.offer(new int[]{toStop, noOfPassengers});

	        if (totalPassengers > capacity)
	            return false;
	    }
	    return true;
	}
	 public static boolean carPoolingVerbose(int[][] trips, int capacity) {
	        System.out.println("=== HEAP APPROACH — VERBOSE ===");
	        System.out.println("Input trips (before sort):");
	        for (int[] t : trips)
	            System.out.printf("  [pax=%d, from=%d, to=%d]%n", t[0], t[1], t[2]);

	        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

	        System.out.println("\nAfter sorting by FROM stop:");
	        for (int[] t : trips)
	            System.out.printf("  [pax=%d, from=%d, to=%d]%n", t[0], t[1], t[2]);
	        System.out.println();

	        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
	        int currentPassengers = 0;

	        for (int[] trip : trips) {
	            int numPassengers = trip[0];
	            int fromStop      = trip[1];
	            int toStop        = trip[2];

	            System.out.printf("─── Processing trip [pax=%d, from=%d, to=%d] ──────%n",
	                              numPassengers, fromStop, toStop);

	            // Evict passengers
	            boolean anyEvicted = false;
	            while (!heap.isEmpty() && heap.peek()[0] <= fromStop) {
	                int[] out = heap.poll();
	                System.out.printf("  EVICT: %d passengers who exit at stop %d (exit=%d <= from=%d)%n",
	                                  out[1], out[0], out[0], fromStop);
	                currentPassengers -= out[1];
	                anyEvicted = true;
	            }
	            if (!anyEvicted) System.out.println("  No one evicted.");

	            // Board new passengers
	            currentPassengers += numPassengers;
	            heap.offer(new int[]{toStop, numPassengers});
	            System.out.printf("  BOARD: %d passengers (exit at stop %d)%n", numPassengers, toStop);
	            System.out.printf("  Heap now: %s%n", heapToString(heap));
	            System.out.printf("  Passengers in car: %d / %d %s%n",
	                              currentPassengers, capacity,
	                              currentPassengers > capacity ? "<-- OVER CAPACITY!" : "(ok)");
	            System.out.println();

	            if (currentPassengers > capacity) return false;
	        }
	        return true;
	    }

	    // Helper: show heap contents without draining it
	    private static String heapToString(PriorityQueue<int[]> pq) {
	        if (pq.isEmpty()) return "(empty)";
	        StringBuilder sb = new StringBuilder("[");
	        Object[] arr = pq.toArray();
	        for (Object o : arr) {
	            int[] e = (int[]) o;
	            sb.append(e[1]).append("pax@exit").append(e[0]).append(", ");
	        }
	        sb.setLength(sb.length() - 2);
	        sb.append("]");
	        return sb.toString();
	    }

	    // ─────────────────────────────────────────────────────────────
	    //  MAIN METHOD — multiple test cases
	    // ─────────────────────────────────────────────────────────────
	    public static void main(String[] args) {

	        System.out.println("╔══════════════════════════════════════╗");
	        System.out.println("║         CAR POOLING — HEAP           ║");
	        System.out.println("╚══════════════════════════════════════╝\n");

	        // ── Test 1 ───────────────────────────────────────────────
	        System.out.println("TEST 1: trips=[[2,1,5],[3,3,7]], capacity=4");
	        boolean r1 = carPooling(new int[][]{{2,1,5},{3,3,7}}, 4);
	        System.out.println("Output: " + r1 + "  (expected: false)\n");

	        // ── Test 2 ───────────────────────────────────────────────
	        System.out.println("TEST 2: trips=[[2,1,5],[3,3,7]], capacity=5");
	        boolean r2 = carPooling(new int[][]{{2,1,5},{3,3,7}}, 5);
	        System.out.println("Output: " + r2 + "  (expected: true)\n");

	        // ── Test 3 — verbose walkthrough ─────────────────────────
	        System.out.println("TEST 3 (VERBOSE): trips=[[3,2,7],[3,7,9],[8,3,9]], capacity=11");
	        boolean r3 = carPoolingVerbose(new int[][]{{3,2,7},{3,7,9},{8,3,9}}, 11);
	        System.out.println("Final Output: " + r3 + "  (expected: true)\n");

	        // ── Test 4 — trips with same pickup stop ──────────────────
	        System.out.println("TEST 4: trips=[[4,5,9],[3,5,7]], capacity=6");
	        boolean r4 = carPooling(new int[][]{{4,5,9},{3,5,7}}, 6);
	        System.out.println("Output: " + r4 + "  (expected: false)\n");

	        // ── Test 5 — overlapping exits evict correctly ────────────
	        System.out.println("TEST 5: trips=[[2,1,4],[3,2,5],[4,4,7]], capacity=5");
	        boolean r5 = carPooling(new int[][]{{2,1,4},{3,2,5},{4,4,7}}, 5);
	        System.out.println("Output: " + r5 + "  (expected: false)\n");
	    }
}
