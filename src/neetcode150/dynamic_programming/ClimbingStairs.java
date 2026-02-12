package neetcode150.dynamic_programming;
/*
 * You are given an integer n representing the number of steps to reach the top of a staircase. You can climb with either 1 or 2 steps at a time.

Return the number of distinct ways to climb to the top of the staircase.

Example 1:

Input: n = 2

Output: 2
Explanation:

1 + 1 = 2
2 = 2
Example 2:

Input: n = 3

Output: 3
Explanation:

1 + 1 + 1 = 3
1 + 2 = 3
2 + 1 = 3
Constraints:

1 <= n <= 30
## ⏱️ Time Complexity: **O(n)**
- We loop from 3 to n
- Each iteration does constant work (addition and assignment)
- Total: n-2 iterations, which is O(n)

## 💾 Space Complexity: **O(1)**
- We only use 3 variables: `prev2`, `prev1`, `current`
- No matter how big `n` is, we always use the same amount of memory
- This is **constant space**!

 */
public class ClimbingStairs {
	public int climbStairs(int n) {
		int prev2 = 1;
		int prev1 = 2;
		for(int i = 2; i <n; i++) {
			int current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return prev1;
		
	}
	 public static void main(String[] args) {
	        ClimbingStairs solution = new ClimbingStairs();
	        
	        // Test case 1
	        int n1 = 2;
	        System.out.println("Number of stairs: " + n1);
	        System.out.println("Number of ways: " + solution.climbStairs(n1));
	        System.out.println("---");
	        
	        // Test case 2
	        int n2 = 3;
	        System.out.println("Number of stairs: " + n2);
	        System.out.println("Number of ways: " + solution.climbStairs(n2));
	        System.out.println("---");
	        
	        // Test case 3
	        int n3 = 4;
	        System.out.println("Number of stairs: " + n3);
	        System.out.println("Number of ways: " + solution.climbStairs(n3));
	        System.out.println("---");
	        
	        // Test case 4
	        int n4 = 5;
	        System.out.println("Number of stairs: " + n4);
	        System.out.println("Number of ways: " + solution.climbStairs(n4));
	        System.out.println("---");
	        
	        // Test case 5
	        int n5 = 10;
	        System.out.println("Number of stairs: " + n5);
	        System.out.println("Number of ways: " + solution.climbStairs(n5));
	    }

}
