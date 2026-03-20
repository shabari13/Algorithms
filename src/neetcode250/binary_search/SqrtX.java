package neetcode250.binary_search;
/*
 * You are given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
Example 1:

Input: x = 9

Output: 3
Example 2:

Input: x = 13

Output: 3
Constraints:

0 <= x <= ((2^31)-1)
The Idea Behind the Solution
We use Binary Search to find the integer square root of x. Instead of checking every number from 1 to x (which would be slow), we split the search range in half each time. We look at the middle number, square it, and ask: "Is this too big, too small, or just right?" If the middle number squared equals x, we found our answer. If it's too big, we search the left half. If it's too small, we search the right half — and we keep the last number that wasn't too big as our best answer.

Explain Like I'm 5 🧒
Imagine you have a magic number (say, 8) and you want to find which number, when multiplied by itself, gives you 8 (or something close but not over 8).
Instead of trying 1×1, 2×2, 3×3, 4×4... one by one (boring and slow!), you play a guessing game:

Pick the middle number between 1 and 8 → that's 4
4×4 = 16, which is too big! So the answer must be smaller than 4
Now pick the middle between 1 and 3 → that's 2
2×2 = 4, which is too small! So maybe 3?
3×3 = 9, which is too big! So the best answer is 2


⏱️ TimeO(log x)Binary search halves the range each iteration — for x=2 billion, that's only ~31 steps!🧠 SpaceO(1)Only a fixed number of variables (low, high, mid, result) — no arrays or recursion

 */
public class SqrtX {
	
	public static int mySqrt(int x) {
		if(x == 0 || x == 1)
			return x;
		
		int left = 1;
		int right = x / 2;
		int result = 1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			long square = mid * mid;
			if((square) == x) {
				return mid;
			} else if(square < x) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid -1;
			}
		}
		return result;
	}
	
	 public static void main(String[] args) {
	        int[] testInputs = {0, 1, 4, 8, 9, 15, 16, 26, 100, Integer.MAX_VALUE};

	        System.out.println("╔══════════════════════════════════╗");
	        System.out.println("║     Sqrt(x) — Binary Search      ║");
	        System.out.println("╠══════════╦═══════════════════════╣");
	        System.out.println("║  Input   ║  floor(sqrt(x))       ║");
	        System.out.println("╠══════════╬═══════════════════════╣");

	        for (int x : testInputs) {
	            int output = mySqrt(x);
	            System.out.printf("║  %-7d ║  %-21d║%n", x, output);
	        }

	        System.out.println("╚══════════╩═══════════════════════╝");
	    }

}
