package neetcode150.mathandgeometry;

import java.util.Arrays;

/*
 * You are given an integer array digits, where each digits[i] is the ith digit of a large integer. It is ordered from most significant to least significant digit, and it will not contain any leading zero.

Return the digits of the given integer after incrementing it by one.

Example 1:

Input: digits = [1,2,3,4]

Output: [1,2,3,5]
Explanation 1234 + 1 = 1235.

Example 2:

Input: digits = [9,9,9]

Output: [1,0,0,0]
Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
ComplexityExplanation⏱ TimeO(n)In the worst case (all 9s), we visit every digit once. n = number of digits.🗂 SpaceO(1) amortized / O(n) 
worst caseWe modify in-place for most cases. Only when all digits are 9 do we allocate a new array of size n+1.

Basically check where all digits are less than 9. if yet add 1 to the digit and return. other wise add 0 to the digit. of it is greater than 9 them add 0 to that digit and carry over
otherwise make an array with n+1 mae results[0] as 1 and rest will be zero(999 example) */
public class PlusOne {
	
	public static int[] plusOne(int[] digits) {
		
		for(int i = digits.length - 1; i >= 0; i--) {
			if(digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] results = new int[digits.length + 1];
		results[0] = 1;
		return results;
	}
    // ✅ Main Method - Testing with multiple inputs
    public static void main(String[] args) {

        // Test Case 1: [1, 2, 3] → Expected: [1, 2, 4]
        int[] input1 = {1, 2, 3};
        System.out.println("Input:  " + Arrays.toString(input1));
        System.out.println("Output: " + Arrays.toString(plusOne(input1)));
        System.out.println();

        // Test Case 2: [4, 3, 2, 1] → Expected: [4, 3, 2, 2]
        int[] input2 = {4, 3, 2, 1};
        System.out.println("Input:  " + Arrays.toString(input2));
        System.out.println("Output: " + Arrays.toString(plusOne(input2)));
        System.out.println();

        // Test Case 3: [9] → Expected: [1, 0]
        int[] input3 = {9};
        System.out.println("Input:  " + Arrays.toString(input3));
        System.out.println("Output: " + Arrays.toString(plusOne(input3)));
        System.out.println();

        // Test Case 4: [9, 9, 9] → Expected: [1, 0, 0, 0]
        int[] input4 = {9, 9, 9};
        System.out.println("Input:  " + Arrays.toString(input4));
        System.out.println("Output: " + Arrays.toString(plusOne(input4)));
        System.out.println();

        // Test Case 5: [1, 9, 9] → Expected: [2, 0, 0]
        int[] input5 = {1, 9, 9};
        System.out.println("Input:  " + Arrays.toString(input5));
        System.out.println("Output: " + Arrays.toString(plusOne(input5)));
    }
}
