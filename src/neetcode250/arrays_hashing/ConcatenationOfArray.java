package neetcode250.arrays_hashing;
/*
 * You are given an integer array nums of length n. Create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).

Specifically, ans is the concatenation of two nums arrays.

Return the array ans.

Example 1:

Input: nums = [1,4,1,2]

Output: [1,4,1,2,1,4,1,2]
Example 2:

Input: nums = [22,21,20,1]

Output: [22,21,20,1,22,21,20,1]
Constraints:

1 <= nums.length <= 1000.
1 <= nums[i] <= 1000
TimeO(n)We loop through the array exactly once, visiting each of the n elements one timeSpaceO(n)
We allocate a new array of size 2n to store the result — which is O(n) extra space
 */
public class ConcatenationOfArray {
	
	public static int[] getConcatenation(int[] nums) {
		int n = nums.length;
		int[] result = new int[2 * n];
		for(int i = 0; i < nums.length; i++) {
			result[i] = nums[i];
			result[i+n] = nums[i];
		}
		return result;
	}
	
	  public static void printArray(int[] arr) {
	        System.out.print("[");
	        for (int i = 0; i < arr.length; i++) {
	            System.out.print(arr[i]);
	            if (i < arr.length - 1) System.out.print(", ");
	        }
	        System.out.println("]");
	    }

	    public static void main(String[] args) {

	        // ── Test Case 1 ──────────────────────────────────────────
	        int[] input1 = {1, 2, 1};
	        System.out.println("Input 1:  ");
	        printArray(input1);
	        int[] output1 = getConcatenation(input1);
	        System.out.print("Output 1: ");
	        printArray(output1);

	        // ── Test Case 2 ──────────────────────────────────────────
	        int[] input2 = {1, 3, 2, 1};
	        System.out.println("\nInput 2:  ");
	        printArray(input2);
	        int[] output2 = getConcatenation(input2);
	        System.out.print("Output 2: ");
	        printArray(output2);

	        // ── Test Case 3 (single element) ─────────────────────────
	        int[] input3 = {7};
	        System.out.println("\nInput 3:  ");
	        printArray(input3);
	        int[] output3 = getConcatenation(input3);
	        System.out.print("Output 3: ");
	        printArray(output3);

	        // ── Test Case 4 (all same elements) ──────────────────────
	        int[] input4 = {5, 5, 5};
	        System.out.println("\nInput 4:  ");
	        printArray(input4);
	        int[] output4 = getConcatenation(input4);
	        System.out.print("Output 4: ");
	        printArray(output4);
	    }
}
