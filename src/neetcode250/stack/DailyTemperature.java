package neetcode250.stack;

import java.util.Arrays;
import java.util.Stack;

/*
 * You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.

Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.

Example 1:

Input: temperatures = [30,38,30,36,35,40,28]

Output: [1,4,1,2,1,0,0]
Example 2:

Input: temperatures = [22,21,20]

Output: [0,0,0]
Constraints:

1 <= temperatures.length <= 1000.
1 <= temperatures[i] <= 100

Imagine you're standing in a line of days, looking forward. For each day, you want to find the next day that's hotter. The brute-force way is to 
look at every following day — but that's slow. The clever way uses a stack (like a pile of plates) to keep track of days we haven't yet found a warmer future for. As we scan forward, whenever we find a warmer day, we "resolve" all the colder
 days sitting on the stack by calculating the gap. This way, each day is pushed and popped from the stack at most once — making it blazing fast.

Time Complexity: O(n)
Each index is pushed onto the stack exactly once and popped at most once. So across all n days, we do at most 2n stack operations — linear time regardless of input shape.
Space Complexity: O(n)
In the worst case (a strictly decreasing temperature array like [90, 80, 70, 60]), 
every index piles onto the stack before any are resolved, using O(n) extra space. 
The result array is also O(n) but that's required output, not auxiliary space.
 */
public class DailyTemperature {	
	public static int[] dailyTemperatures(int[] temperatures) {
		int[] result = new int[temperatures.length];
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < temperatures.length; i++) {
			while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()] ) {
				int prevIndex = stack.pop();
				result[prevIndex] = i - prevIndex;
			}
			stack.push(i);
		}
		return result;
	}
	public static void main(String[] args) {

        // Test 1 — classic example
        int[] t1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Input:    " + Arrays.toString(t1));
        System.out.println("Output:   " + Arrays.toString(dailyTemperatures(t1)));
        System.out.println("Expected: [1, 1, 4, 2, 1, 1, 0, 0]");
        System.out.println();

        // Test 2 — strictly decreasing (all zeros except none)
        int[] t2 = {90, 80, 70, 60};
        System.out.println("Input:    " + Arrays.toString(t2));
        System.out.println("Output:   " + Arrays.toString(dailyTemperatures(t2)));
        System.out.println("Expected: [0, 0, 0, 0]");
        System.out.println();

        // Test 3 — strictly increasing (each day waits 1)
        int[] t3 = {60, 65, 70, 75, 80};
        System.out.println("Input:    " + Arrays.toString(t3));
        System.out.println("Output:   " + Arrays.toString(dailyTemperatures(t3)));
        System.out.println("Expected: [1, 1, 1, 1, 0]");
        System.out.println();

        // Test 4 — single element
        int[] t4 = {55};
        System.out.println("Input:    " + Arrays.toString(t4));
        System.out.println("Output:   " + Arrays.toString(dailyTemperatures(t4)));
        System.out.println("Expected: [0]");
        System.out.println();

        // Test 5 — all same temperature
        int[] t5 = {70, 70, 70, 70};
        System.out.println("Input:    " + Arrays.toString(t5));
        System.out.println("Output:   " + Arrays.toString(dailyTemperatures(t5)));
        System.out.println("Expected: [0, 0, 0, 0]");
    }
}
