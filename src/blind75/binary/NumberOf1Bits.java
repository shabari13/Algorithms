package blind75.binary;

/*
 * 
 * Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).

 

Example 1:

Input: n = 11

Output: 3

Explanation:

The input binary string 1011 has a total of three set bits.

Example 2:

Input: n = 128

Output: 1

Explanation:

The input binary string 10000000 has a total of one set bit.

Example 3:

Input: n = 2147483645

Output: 30

Explanation:

The input binary string 1111111111111111111111111111101 has a total of thirty set bits.

Time Complexity: O(log n) or O(32) = O(1) for 32-bit integers

We check each bit exactly once
An integer has at most 32 bits, so maximum 32 iterations
For a number n, it has log₂(n) bits

Space Complexity: O(1)

We only use two variables: count and n
No extra arrays or data structures needed
Memory usage doesn't grow with input size
 
Basically you check the last bit of the number, by doing N & 1

then you throw away the last bit .

Keep doing it until you hit number 0
 
 The trick: You check one block at a time from right to left, and after checking each block, you throw it away!
 
 */
public class NumberOf1Bits {
	
	public int countSetBits(int n) {
		int count = 0;
		
		while (n != 0) {
			count += n & 1;
			n = n >>> 1;
			
		}
		return count;
		
		
	}
	
	  public static void main(String[] args) {
		  NumberOf1Bits solution = new NumberOf1Bits();
	        
	        // Test case 1: n = 11 (binary: 1011)
	        int input1 = 11;
	        int result1 = solution.countSetBits(input1);
	        System.out.println("Input: " + input1);
	        System.out.println("Binary: " + Integer.toBinaryString(input1));
	        System.out.println("Number of 1 bits: " + result1);
	        System.out.println();
	        
	        // Test case 2: n = 128 (binary: 10000000)
	        int input2 = 128;
	        int result2 = solution.countSetBits(input2);
	        System.out.println("Input: " + input2);
	        System.out.println("Binary: " + Integer.toBinaryString(input2));
	        System.out.println("Number of 1 bits: " + result2);
	        System.out.println();
	        
	        // Test case 3: n = 2147483645 (binary: 1111111111111111111111111111101)
	        int input3 = 2147483645;
	        int result3 = solution.countSetBits(input3);
	        System.out.println("Input: " + input3);
	        System.out.println("Binary: " + Integer.toBinaryString(input3));
	        System.out.println("Number of 1 bits: " + result3);
	    }

}
