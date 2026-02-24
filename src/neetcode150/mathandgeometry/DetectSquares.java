package neetcode150.mathandgeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * You are given a stream of points consisting of x-y coordinates on a 2-D plane. Points can be added and queried as follows:

Add - new points can be added to the stream into a data structure. Duplicate points are allowed and should be treated as separate points.
Query - Given a single query point, count the number of ways to choose three additional points from the data structure such that the three points and the query point form a square. The square must have all sides parallel to the x-axis and y-axis, i.e. no diagonal squares are allowed. Recall that a square must have four equal sides.
Implement the CountSquares class:

CountSquares() Initializes the object.
void add(int[] point) Adds a new point point = [x, y].
int count(int[] point) Counts the number of ways to form valid squares with point point = [x, y] as described above.
Example 1:



Input: 
["CountSquares", "add", [[1, 1]], "add", [[2, 2]], "add", [[1, 2]], "count", [[2, 1]], "count", [[3, 3]], "add", [[2, 2]], "count", [[2, 1]]]
       
Output:
[null, null, null, null, 1, 0, null, 2]

Explanation:
CountSquares countSquares = new CountSquares();
countSquares.add([1, 1]);
countSquares.add([2, 2]);
countSquares.add([1, 2]);

countSquares.count([2, 1]);   // return 1.
countSquares.count([3, 3]);   // return 0.
countSquares.add([2, 2]);     // Duplicate points are allowed.
countSquares.count([2, 1]);   // return 2. 
Constraints:

point.length == 2
0 <= x, y <= 1000

 */
public class DetectSquares {

    private final Map<String, Integer> pointCount;
    private final Map<Integer, List<Integer>> xToYs;

    // ── Constructor ─────────────────────────────────────────
    public DetectSquares() {
        pointCount = new HashMap<>();
        xToYs      = new HashMap<>();
    }

    // ── add ─────────────────────────────────────────────────
    /**
     * Adds point [x, y] to the data structure.
     *
     * Steps:
     *   1. Build key "x,y"
     *   2. Increment its frequency in pointCount
     *   3. Append y to xToYs[x]
     *
     * Time: O(1)
     */
    public void add(int[] point) {
        int x = point[0], y = point[1];
        String key = x + "," + y;

        pointCount.merge(key, 1, Integer::sum);
        xToYs.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
    }

    // ── count ────────────────────────────────────────────────
    /**
     * Given query point Q = (qx, qy), count all valid squares.
     *
     * For every y-value py stored at x = qx:
     *   P = (qx, py) is the partner on the same vertical edge as Q.
     *   skip if py == qy (zero-height, not a square)
     *
     *   side = |py - qy|
     *
     *   Extend RIGHT (+side):
     *     need (qx+side, qy) and (qx+side, py)
     *     ways = count(qx+side, qy) * count(qx+side, py)
     *
     *   Extend LEFT (-side):
     *     need (qx-side, qy) and (qx-side, py)
     *     ways = count(qx-side, qy) * count(qx-side, py)
     *
     * Time: O(n) where n = points added so far
     */
    public int count(int[] point) {
        int qx = point[0], qy = point[1];
        int total = 0;

        List<Integer> ysAtQx = xToYs.getOrDefault(qx, Collections.emptyList());

        for (int py : ysAtQx) {
            if (py == qy) continue; // same y → no height → skip

            int side = Math.abs(py - qy);

            // Extend RIGHT
            total += getCount(qx + side, qy) * getCount(qx + side, py);

            // Extend LEFT
            total += getCount(qx - side, qy) * getCount(qx - side, py);
        }

        return total;
    }

    // ── helper ───────────────────────────────────────────────
    private int getCount(int x, int y) {
        return pointCount.getOrDefault(x + "," + y, 0);
    }


    public static void main(String[] args) {

        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  LeetCode 2013 – Detect Squares : Full Test Suite");
        System.out.println("════════════════════════════════════════════════════════\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 1 : LeetCode Official Example
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 1 : LeetCode Official Example ══════════════════");
        DetectSquares cs1 = new DetectSquares();

        cs1.add(new int[]{3, 10});
        cs1.add(new int[]{11, 2});
        cs1.add(new int[]{3, 2});
        System.out.println("Added: (3,10), (11,2), (3,2)\n");

        System.out.println("Query count([11,10]):");
        System.out.println("  Q=(11,10). Points at x=11: y=[2].");
        System.out.println("  P=(11,2): side=|10-2|=8");
        System.out.println("    Extend RIGHT (x=11+8=19): (19,10)=0 × (19,2)=0  = 0");
        System.out.println("    Extend LEFT  (x=11-8= 3): (3,10)=1  × (3,2)=1   = 1");
        System.out.println("  Total = 1");
        System.out.println("  Result: " + cs1.count(new int[]{11, 10}) + "  (Expected: 1)\n");

        System.out.println("Query count([14,8]):");
        System.out.println("  Q=(14,8). No points at x=14. → 0");
        System.out.println("  Result: " + cs1.count(new int[]{14, 8}) + "  (Expected: 0)\n");

        cs1.add(new int[]{11, 2}); // duplicate
        System.out.println("Added duplicate: (11,2). Now (11,2) count=2\n");

        System.out.println("Query count([11,10]) after duplicate:");
        System.out.println("  Q=(11,10). Points at x=11: y=[2,2].");
        System.out.println("  Iteration 1 — P=(11,2): side=8, LEFT: (3,10)=1 × (3,2)=1 = 1");
        System.out.println("  Iteration 2 — P=(11,2): same P again: = 1");
        System.out.println("  Total = 2");
        System.out.println("  Result: " + cs1.count(new int[]{11, 10}) + "  (Expected: 2)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 2 : Simple 2×2 square
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 2 : Simple side-2 square ═══════════════════════");
        DetectSquares cs2 = new DetectSquares();
        cs2.add(new int[]{0, 0});
        cs2.add(new int[]{2, 0});
        cs2.add(new int[]{0, 2});
        System.out.println("Added: (0,0), (2,0), (0,2)");
        System.out.println("Query count([2,2]):");
        System.out.println("  Q=(2,2). Points at x=2: y=[0].");
        System.out.println("  P=(2,0): side=2. Extend LEFT (x=0): (0,2)=1 × (0,0)=1 = 1");
        System.out.println("  Result: " + cs2.count(new int[]{2, 2}) + "  (Expected: 1)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 3 : Duplicates multiply the count
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 3 : Duplicates multiply the count ══════════════");
        DetectSquares cs3 = new DetectSquares();
        cs3.add(new int[]{0, 0});
        cs3.add(new int[]{0, 0}); // duplicate → count=2
        cs3.add(new int[]{2, 0});
        cs3.add(new int[]{0, 2});
        System.out.println("Added: (0,0)×2, (2,0)×1, (0,2)×1");
        System.out.println("Query count([2,2]):");
        System.out.println("  Q=(2,2). P=(2,0): side=2. Extend LEFT:");
        System.out.println("  (0,2)=1 × (0,0)=2 = 2");
        System.out.println("  Result: " + cs3.count(new int[]{2, 2}) + "  (Expected: 2)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 4 : Two squares of different sizes
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 4 : Two different-sized squares ════════════════");
        DetectSquares cs4 = new DetectSquares();
        // 1×1 square with query (0,0): needs (1,0),(0,1),(1,1)
        cs4.add(new int[]{1, 0});
        cs4.add(new int[]{0, 1});
        cs4.add(new int[]{1, 1});
        // 2×2 square with query (0,0): needs (2,0),(0,2),(2,2)
        cs4.add(new int[]{2, 0});
        cs4.add(new int[]{0, 2});
        cs4.add(new int[]{2, 2});
        System.out.println("Added: (1,0),(0,1),(1,1),(2,0),(0,2),(2,2)");
        System.out.println("Query count([0,0]):");
        System.out.println("  Q=(0,0). Points at x=0: y=[1,2].");
        System.out.println("  P=(0,1): side=1. Extend RIGHT (x=1): (1,0)=1 × (1,1)=1 = 1");
        System.out.println("  P=(0,2): side=2. Extend RIGHT (x=2): (2,0)=1 × (2,2)=1 = 1");
        System.out.println("  Total = 2");
        System.out.println("  Result: " + cs4.count(new int[]{0, 0}) + "  (Expected: 2)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 5 : No squares possible
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 5 : No squares possible ════════════════════════");
        DetectSquares cs5 = new DetectSquares();
        cs5.add(new int[]{1, 2});
        cs5.add(new int[]{3, 5});
        cs5.add(new int[]{7, 1});
        System.out.println("Added: (1,2),(3,5),(7,1)  — scattered, no axis-aligned square");
        System.out.println("Result: " + cs5.count(new int[]{0, 0}) + "  (Expected: 0)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 6 : Query point itself is also stored
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 6 : Query point also stored in structure ═══════");
        DetectSquares cs6 = new DetectSquares();
        cs6.add(new int[]{0, 0});
        cs6.add(new int[]{3, 0});
        cs6.add(new int[]{0, 3});
        cs6.add(new int[]{3, 3});
        System.out.println("Added: (0,0),(3,0),(0,3),(3,3)");
        System.out.println("Query count([0,0]):");
        System.out.println("  Q=(0,0). Points at x=0: y=[0,3].");
        System.out.println("  P=(0,0): py==qy → SKIP");
        System.out.println("  P=(0,3): side=3. Extend RIGHT (x=3): (3,0)=1 × (3,3)=1 = 1");
        System.out.println("  Result: " + cs6.count(new int[]{0, 0}) + "  (Expected: 1)\n");

        // ─────────────────────────────────────────────────────────────
        //  TEST 7 : Large duplicate scenario
        // ─────────────────────────────────────────────────────────────
        System.out.println("══ TEST 7 : Large duplicate scenario ═══════════════════");
        DetectSquares cs7 = new DetectSquares();
        // Add (0,0) three times and (4,0) two times
        for (int i = 0; i < 3; i++) cs7.add(new int[]{0, 0});
        for (int i = 0; i < 2; i++) cs7.add(new int[]{4, 0});
        cs7.add(new int[]{0, 4});
        cs7.add(new int[]{4, 4});
        System.out.println("Added: (0,0)×3, (4,0)×2, (0,4)×1, (4,4)×1");
        System.out.println("Query count([0,0]):");
        System.out.println("  Q=(0,0). Points at x=0: y=[0,0,0,4].");
        System.out.println("  P=(0,0): py==qy → SKIP (3 times)");
        System.out.println("  P=(0,4): side=4. Extend RIGHT (x=4): (4,0)=2 × (4,4)=1 = 2");
        System.out.println("  Result: " + cs7.count(new int[]{0, 0}) + "  (Expected: 2)\n");

        // ─────────────────────────────────────────────────────────────
        //  COMPLEXITY SUMMARY
        // ─────────────────────────────────────────────────────────────
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  COMPLEXITY SUMMARY");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("  add()   → Time: O(1)    — simple map updates");
        System.out.println("  count() → Time: O(n)    — iterate y-values at x=qx,");
        System.out.println("                            at most n total (all adds)");
        System.out.println("  Space   → O(n)          — store each point in two maps");
        System.out.println("  n = total number of add() calls made so far.");
        System.out.println();
        System.out.println("  Note: With coordinate constraint 0 ≤ x,y ≤ 1000,");
        System.out.println("  count() is at most O(1001) per call in the worst case");
        System.out.println("  (at most 1001 distinct y-values at any one x-column).");
        System.out.println("════════════════════════════════════════════════════════");
    }
}

