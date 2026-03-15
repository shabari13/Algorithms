package neetcode250.stack;

import java.util.Arrays;
import java.util.Stack;
/*
 * Asteroid Collision
Medium
Topics
Company Tags
You are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:

Input: asteroids = [2,4,-4,-1]

Output: [2]
Example 2:

Input: asteroids = [5,5]

Output: [5,5]
Example 3:

Input: asteroids = [7,-3,9]

Output: [7,9]
Constraints:

2 <= asteroids.length <= 10,000.
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

 * Time Complexity
O(n) amortized
Space Complexity
O(n) worst case
Stack can hold at most n asteroids
e.g. all moving right → none collide
→ all n pushed onto stack
 */
public class AsteroidCollision {
	public static int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();
		boolean destroyed = false;
		for(int asteroid : asteroids) {
				while(!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
					  int top = stack.peek();
					  if (top < Math.abs(asteroid)) {
		                    // Case 1: Stack top is SMALLER → it gets destroyed, loop continues
		                    // The incoming asteroid might still collide with more asteroids below
		                    stack.pop();
	
		                } else if (top == Math.abs(asteroid)) {
		                    // Case 2: Equal size → BOTH are destroyed
		                    stack.pop();
		                    destroyed = true;
		                    break; // incoming also destroyed, stop the while loop
	
		                } else {
		                    // Case 3: Stack top is LARGER → incoming asteroid destroyed
		                    destroyed = true;
		                    break;
		                }
				}
				if(!destroyed) {
					stack.push(asteroid);
				}
			
		}
        int[] result = new int[stack.size()];
        for(int i = result.length - 1; i >= 0 ; i--) {
        	result[i] = stack.pop();
        }
        return result;
    }
	
	public static void main(String[] args) {

        // Test 1: Classic example — -5 loses to 10
        int[] test1 = {5, 10, -5};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test1)));
        System.out.println("Expect: [5, 10]");
        System.out.println();

        // Test 2: Equal-size collision — both explode
        int[] test2 = {8, -8};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test2)));
        System.out.println("Expect: []");
        System.out.println();

        // Test 3: Chain reaction — -10 destroys 5, then 10 destroys -10
        int[] test3 = {10, 2, -5};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test3)));
        System.out.println("Expect: [10]");
        System.out.println();

        // Test 4: No collision — all moving right
        int[] test4 = {1, 2, 3, 4};
        System.out.println("Input:  " + Arrays.toString(test4));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test4)));
        System.out.println("Expect: [1, 2, 3, 4]");
        System.out.println();

        // Test 5: No collision — all moving left (they move away from each other)
        int[] test5 = {-1, -2, -3};
        System.out.println("Input:  " + Arrays.toString(test5));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test5)));
        System.out.println("Expect: [-1, -2, -3]");
        System.out.println();

        // Test 6: Large left-mover wipes out multiple right-movers
        int[] test6 = {1, 2, 3, -100};
        System.out.println("Input:  " + Arrays.toString(test6));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test6)));
        System.out.println("Expect: [-100]");
        System.out.println();

        // Test 7: Mixed — some survive, some don't
        int[] test7 = {-2, -1, 1, 2};
        System.out.println("Input:  " + Arrays.toString(test7));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(test7)));
        System.out.println("Expect: [-2, -1, 1, 2]");
    }
}
