package neetcode250.two_pointers;
/*
 * You are given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7,8], k = 4

Output: [5,6,7,8,1,2,3,4]
Explanation:
rotate 1 steps to the right: [8,1,2,3,4,5,6,7]
rotate 2 steps to the right: [7,8,1,2,3,4,5,6]
rotate 3 steps to the right: [6,7,8,1,2,3,4,5]
rotate 4 steps to the right: [5,6,7,8,1,2,3,4]

Example 2:

Input: nums = [1000,2,4,-3], k = 2

Output: [4,-3,1000,2]
Explanation:
rotate 1 steps to the right: [-3,1000,2,4]
rotate 2 steps to the right: [4,-3,1000,2]

Constraints:

1 <= nums.length <= 100,000
-(2^31) <= nums[i] <= ((2^31)-1)
0 <= k <= 100,000
Follow up: Could you do it in-place with O(1) extra space?


 */
public class RotateArray {

	public static void rotate(int[] nums, int k) {
		int n = nums.length;
		 k = k % n;
		 if(k == 0) return;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k-1);
		reverse(nums, k, n -1);
		
	}
	public static void reverse(int[] nums, int start, int end) {
		while(start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	
	 private static void printArray(int[] arr) {
	        System.out.print("[");
	        for (int i = 0; i < arr.length; i++) {
	            System.out.print(arr[i]);
	            if (i < arr.length - 1) System.out.print(", ");
	        }
	        System.out.println("]");
	    }

	    // ─────────────────────────────────────────────
	    //  Main – Multiple Sample Inputs
	    // ─────────────────────────────────────────────
	    public static void main(String[] args) {

	        // ── Test 1: Standard case ──────────────────
	        int[] arr1 = {1, 2, 3, 4, 5};
	        System.out.println("Test 1 | k = 2");
	        System.out.print("Before: "); printArray(arr1);
	        rotate(arr1, 2);
	        System.out.print("After : "); printArray(arr1);

	        System.out.println();

	        // ── Test 2: k equals array length (full rotation) ──
	        int[] arr2 = {1, 2, 3, 4, 5};
	        System.out.println("Test 2 | k = 5  (full rotation → same array)");
	        System.out.print("Before: "); printArray(arr2);
	        rotate(arr2, 5);
	        System.out.print("After : "); printArray(arr2);

	        System.out.println();

	        // ── Test 3: k larger than array length ────
	        int[] arr3 = {1, 2, 3, 4, 5};
	        System.out.println("Test 3 | k = 7  (7 % 5 = 2, same as Test 1)");
	        System.out.print("Before: "); printArray(arr3);
	        rotate(arr3, 7);
	        System.out.print("After : "); printArray(arr3);

	        System.out.println();

	        // ── Test 4: Single element ─────────────────
	        int[] arr4 = {42};
	        System.out.println("Test 4 | k = 3  (single element)");
	        System.out.print("Before: "); printArray(arr4);
	        rotate(arr4, 3);
	        System.out.print("After : "); printArray(arr4);

	        System.out.println();

	        // ── Test 5: Two elements ───────────────────
	        int[] arr5 = {-1, 100};
	        System.out.println("Test 5 | k = 1  (two elements)");
	        System.out.print("Before: "); printArray(arr5);
	        rotate(arr5, 1);
	        System.out.print("After : "); printArray(arr5);

	        System.out.println();

	        // ── Test 6: Negatives & k=3 ────────────────
	        int[] arr6 = {-1, -100, 3, 99};
	        System.out.println("Test 6 | k = 3  (negatives)");
	        System.out.print("Before: "); printArray(arr6);
	        rotate(arr6, 3);
	        System.out.print("After : "); printArray(arr6);
	    }
}
