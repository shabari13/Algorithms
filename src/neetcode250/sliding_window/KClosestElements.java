package neetcode250.sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * You are given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
Example 1:

Input: arr = [2,4,5,8], k = 2, x = 6

Output: [4,5]
Example 2:

Input: arr = [2,3,4], k = 3, x = 1

Output: [2,3,4]
Constraints:

1 <= k <= arr.length <= 10,000.
-10,000 <= arr[i], x <= 10,000
arr is sorted in ascending order.

 */
public class KClosestElements {
	 public static List<Integer> findClosestElements(int[] arr, int k, int x) {

	        // --- Step 1: Set binary search boundaries ---
	        // lo = smallest possible starting index of the window
	        // hi = largest possible starting index of the window
	        //      (must leave room for k elements, so arr.length - k)
	        int lo = 0;
	        int hi = arr.length - k;

	        // --- Step 2: Binary search for the best window start ---
	        while (lo < hi) {

	            // mid is the candidate starting index of our window
	            int mid = lo + (hi - lo) / 2;  // avoids integer overflow

	            // --- Step 3: Compare the two "edge" elements ---
	            // arr[mid]     = leftmost element of the candidate window
	            // arr[mid + k] = element just OUTSIDE the right of the window
	            //
	            // Distance of left  edge from x: x - arr[mid]
	            //   (arr is sorted and mid is left of x in most cases)
	            // Distance of right edge from x: arr[mid + k] - x
	            //
	            // If left edge is FARTHER from x than right edge:
	            //   → The window should shift RIGHT
	            //   → lo = mid + 1
	            // Otherwise (left edge is closer or equal):
	            //   → The window is fine here or should shift LEFT
	            //   → hi = mid

	            if (x - arr[mid] > arr[mid + k] - x) {
	                lo = mid + 1; // shift window right
	            } else {
	                hi = mid;     // shift window left or stay
	            }
	        }
	        // --- Step 4: lo is now the best starting index ---
	        // Extract k elements starting from lo
	        List<Integer> result = new ArrayList<>();
	        for (int i = lo; i < lo + k; i++) {
	            result.add(arr[i]);
	        }
	        return result;
	    }

	    // ─────────────────────────────────────────────
	    //  Helper to pretty-print results
	    // ─────────────────────────────────────────────
	    static void runTest(int[] arr, int k, int x) {
	        List<Integer> result = findClosestElements(arr, k, x);
	        System.out.println("arr=" + Arrays.toString(arr)
	                + ", k=" + k + ", x=" + x);
	        System.out.println("Output: " + result);
	        System.out.println();
	    }

	    public static void main(String[] args) {

	        // Example 1 from problem statement
	        runTest(new int[]{2, 4, 5, 8}, 2, 6);

	        // Example 2 from problem statement
	        runTest(new int[]{2, 3, 4}, 3, 1);

	        // x is in the middle of the array
	        runTest(new int[]{1, 2, 3, 4, 5}, 3, 3);

	        // x is larger than all elements
	        runTest(new int[]{1, 3, 5, 7, 9}, 3, 12);

	        // x is smaller than all elements
	        runTest(new int[]{1, 3, 5, 7, 9}, 3, -5);

	        // k equals array length (must return whole array)
	        runTest(new int[]{1, 2, 3, 4, 5}, 5, 100);

	        // Tie-breaking: equal distance, pick smaller number
	        runTest(new int[]{1, 2, 3, 4, 5}, 2, 3);
	    }
}
