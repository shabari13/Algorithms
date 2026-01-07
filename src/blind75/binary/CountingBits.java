package blind75.binary;


/*
 * 
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

 

Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
 

Constraints:

0 <= n <= 105

Imagine you have a number written in computer language (binary - only 0s and 1s):

If you cut off the last digit (divide by 2), you get a smaller number
We already know how many 1's that smaller number has!
Just add 1 more if the digit we cut off was a 1

 */
public class CountingBits {
	public static int[] countBits(int n) {
		int[] ans = new int[n+1];
		/*
		 * ans[0] = 0; for(int i = 1; i<=n; i++) { ans[i] = hammeingWeight(i); }
		 */
		
		 // The number of 1's in 'i' equals:
        // - Number of 1's in i/2 (which we already calculated)
        // - Plus 1 if i is odd (last bit is 1)
		for(int i = 0; i<=n; i++) {
			ans[i] = ans[ i >> 1] + (i&1);
		}
		return ans;
	}
	public static int hammeingWeight (int n) {
		int count = 0;
		while(n != 0) {
			count += n & 1;
			n = n >>> 1;
		}
		return count;
	}
	public static void main(String[] args) {
        // Test with sample input
        int n = 5;
        
        System.out.println("Input: n = " + n);
        System.out.println("Output: ");
        
        int[] result = countBits(n);
        
        // Print the result array
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        // Print detailed breakdown
        System.out.println("\nDetailed breakdown:");
        for (int i = 0; i <= n; i++) {
            System.out.println(i + " in binary: " + Integer.toBinaryString(i) + 
                             " → has " + result[i] + " one(s)");
        }
    }

}
