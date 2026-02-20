package neetcode150.mathandgeometry;

import java.util.HashSet;
import java.util.Set;

/*
 * Non-Cyclical Number
Easy
Topics
Company Tags
Hints
A non-cyclical number is an integer defined by the following algorithm:

Given a positive integer, replace it with the sum of the squares of its digits.
Repeat the above step until the number equals 1, or it loops infinitely in a cycle which does not include 1.
If it stops at 1, then the number is a non-cyclical number.
Given a positive integer n, return true if it is a non-cyclical number, otherwise return false.

Example 1:

Input: n = 100

Output: true
Explanation: 1² + 0² + 0² = 1

Example 2:

Input: n = 101

Output: false
Explanation:
1² + 0² + 1² = 2
2² = 4
4² = 16
1² + 6² = 37
3² + 7² = 58
5² + 8² = 89
8² + 9² = 145
1² + 4² + 5² = 42
4² + 2² = 20
2² + 0² = 4 (This number has already been seen)

Constraints:

1 <= n <= 1000

⏱ Time Complexity

Let:

D = number of digits

M = maximum possible sum

Each transformation reduces number to at most:

For a 32-bit int:
Max digits = 10
Max sum = 9² * 10 = 810

So number quickly shrinks below 1000.

Worst case:
We process at most ~810 unique values.

Time Complexity:
👉 O(log n) per iteration
👉 Overall behaves like O(1) because number range is bounded

💾 Space Complexity

We store seen numbers in a HashSet.

Worst case: up to ~810 numbers.

👉 O(1) (constant space)
 */
public class HappyNumber {
	
	public static boolean isNonCyclical(int n) {
		Set<Integer> set = new HashSet<>();
		while(n != 1) {
			if(set.contains(n)) {
				return false;
			}
			
			set.add(n);
			int sum = 0;
			int temp = n;
			while(temp > 0 ) {
				int digit = temp % 10;
				sum+= digit * digit;
				temp = temp /10;
			}
			n = sum;
		}
		return true;
	}

	public static void main(String[] args) {

        int[] testNumbers = {19, 2, 7, 20, 1};

        for (int num : testNumbers) {
            boolean result = isNonCyclical(num);
            System.out.println("Input: " + num + " → Non-Cyclical? " + result);
        }
    }
}
