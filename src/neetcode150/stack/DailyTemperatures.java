package neetcode150.stack;

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
⏱ Time Complexity
O(n)

Why?

Each day is:

Pushed once

Popped once

Total operations = 2n

📦 Space Complexity
O(n)

Why?

Stack can hold up to n days

Result array of size n
 */
public class DailyTemperatures {

	public static int[] dailyTemperatures(int[] temperatures) {
		int n =  temperatures.length;
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[n];
		for(int i = 0; i < n; i++) {
			while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
				int prevIndex =  stack.pop();
				result[prevIndex] = i - prevIndex;
			}
			stack.push(i);
		}
		return result;
	}
	 // Main method to test different inputs
    public static void main(String[] args) {

        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temps2 = {30, 40, 50, 60};
        int[] temps3 = {30, 60, 90};
        int[] temps4 = {90, 80, 70, 60};

        System.out.println(Arrays.toString(dailyTemperatures(temps1)));
        System.out.println(Arrays.toString(dailyTemperatures(temps2)));
        System.out.println(Arrays.toString(dailyTemperatures(temps3)));
        System.out.println(Arrays.toString(dailyTemperatures(temps4)));
    }
}
