package neetcode150.mathandgeometry;
/*
 * 
 * Pow(x, n) is a mathematical function to calculate the value of x raised to the power of n (i.e., x^n).

Given a floating-point value x and an integer value n, implement the myPow(x, n) function, which calculates x raised to the power n.

You may not use any built-in library functions.

Example 1:

Input: x = 2.00000, n = 5

Output: 32.00000
Example 2:

Input: x = 1.10000, n = 10

Output: 2.59374
Example 3:

Input: x = 2.00000, n = -3

Output: 0.12500
Constraints:

-100.0 < x < 100.0
-1000 <= n <= 1000
n is an integer.
If x = 0, then n will be positive.

Time & Space Complexity for myPow(2.0, 16)
Time Complexity: O(log n)
The key operation is N is halved every single iteration:
N = 16  →  8  →  4  →  2  →  1  →  0 (stop)
How many times can you halve 16 before reaching 0?
16 / 2 = 8    (step 1)
 8 / 2 = 4    (step 2)
 4 / 2 = 2    (step 3)
 2 / 2 = 1    (step 4)
 1 / 2 = 0    (step 5)

Total steps = 5 = log₂(16) = 4... +1 for the final odd step = log₂(n)
Compare Brute Force vs Fast Power
nBrute Force (multiply n times)Our Algorithm (log₂n steps)1616 steps5 steps10001000 steps~10 steps1,000,0001,000,000 steps~20 steps1,000,000,0001 billion steps 😱~30 steps 😎
As n doubles, brute force doubles too. But our algorithm only adds 1 more step. That is the power of O(log n).

Space Complexity: O(1)
We only ever created 3 variables regardless of how big n is:
javalong N = n;         // 1 variable
double result = 1.0 // 1 variable
// x was passed in  // 1 variable
```
```
n = 16        →  3 variables
n = 1000      →  3 variables
n = 1 billion →  3 variables  (still just 3!)
No arrays, no recursion stack, no extra data structures. Memory usage is constant no matter what n is. That is O(1).


 */
public class PowerXN {
	 public static double myPow(double x, int n) {
	        long N = n; // Use long to safely negate Integer.MIN_VALUE
	        if (N < 0) {
	            x = 1 / x;
	            N = -N;
	        }

	        double result = 1.0;

	        while (N > 0) {
	            if (N % 2 == 1) {       // If odd, grab one x into result
	                result *= x;
	            }
	            x *= x;                 // Square the base
	            N /= 2;                 // Halve the exponent
	        }

	        return result;
	    }
	 
	    public static void main(String[] args) {
	        System.out.println("2^10   = " + myPow(2, 10));    // 1024.0
	        System.out.println("2^-2   = " + myPow(2, -2));    // 0.25
	        System.out.println("3^4    = " + myPow(3, 4));     // 81.0
	        System.out.println("2^0    = " + myPow(2, 0));     // 1.0
	        System.out.println("1.5^3  = " + myPow(1.5, 3));   // 3.375
	        System.out.println("2^-3   = " + myPow(2, -3));    // 0.125
	    }

	    
}
