package neetcode150.stack;

import java.util.Arrays;
import java.util.Stack;

/*
 * There are n cars traveling to the same destination on a one-lane highway.

You are given two arrays of integers position and speed, both of length n.

position[i] is the position of the ith car (in miles)
speed[i] is the speed of the ith car (in miles per hour)
The destination is at position target miles.

A car can not pass another car ahead of it. It can only catch up to another car and then drive at the same speed as the car ahead of it.

A car fleet is a non-empty set of cars driving at the same position and same speed. A single car is also considered a car fleet.

If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered to be part of the fleet.

Return the number of different car fleets that will arrive at the destination.

Example 1:

Input: target = 10, position = [1,4], speed = [3,2]

Output: 1
Explanation: The cars starting at 1 (speed 3) and 4 (speed 2) become a fleet, meeting each other at 10, the destination.

Example 2:

Input: target = 10, position = [4,1,0,7], speed = [2,2,1,1]

Output: 3
Explanation: The cars starting at 4 and 7 become a fleet at position 10. The cars starting at 1 and 0 never catch up to the car ahead of them. Thus, there are 3 car fleets that will arrive at the destination.

Constraints:

n == position.length == speed.length.
1 <= n <= 1000
0 < target <= 1000
0 < speed[i] <= 100
0 <= position[i] < target
All the values of position are unique.
Time Complexity: O(n log n)

Calculating time for each car: O(n)
Sorting the cars array: O(n log n)
Iterating and stack operations: O(n) - each push/peek is O(1)
Total: O(n log n) (sorting dominates)

Space Complexity: O(n)

cars array: O(n)
Stack can have at most n elements (worst case: all cars form separate fleets): O(n)
Total: O(n)
 */
public class CarFleet {
	
	public int carFleet(int target, int[] positions, int[] speed) {
		
		double[][] car = new double[positions.length][2];
		for(int  i = 0 ; i < positions.length; i++) {
			car[i][0] = positions[i];
			car[i][1] = (double)(target - positions[i] / speed[i]);
		}
		Arrays.sort(car, (a, b) -> Double.compare(b[0], a[0]));
		Stack<Double> stack = new Stack<>();
		for(int i = 0; i < positions.length; i++) {
			double currentTime = car[i][1];
			if(stack.isEmpty() || currentTime > stack.peek()) {
				stack.push(currentTime);
			}
			
		}
		return stack.size();
	}
	public static void main(String[] args) {
		CarFleet solution = new CarFleet();
        
        // Test Case 1
        int target1 = 12;
        int[] position1 = {10, 8, 0, 5, 3};
        int[] speed1 = {2, 4, 1, 1, 3};
        System.out.println("Test Case 1:");
        System.out.println("Target: " + target1);
        System.out.println("Position: " + Arrays.toString(position1));
        System.out.println("Speed: " + Arrays.toString(speed1));
        System.out.println("Number of fleets: " + solution.carFleet(target1, position1, speed1));
        System.out.println();
        
        // Test Case 2
        int target2 = 10;
        int[] position2 = {3};
        int[] speed2 = {3};
        System.out.println("Test Case 2:");
        System.out.println("Target: " + target2);
        System.out.println("Position: " + Arrays.toString(position2));
        System.out.println("Speed: " + Arrays.toString(speed2));
        System.out.println("Number of fleets: " + solution.carFleet(target2, position2, speed2));
        System.out.println();
        
        // Test Case 3
        int target3 = 100;
        int[] position3 = {0, 2, 4};
        int[] speed3 = {4, 2, 1};
        System.out.println("Test Case 3:");
        System.out.println("Target: " + target3);
        System.out.println("Position: " + Arrays.toString(position3));
        System.out.println("Speed: " + Arrays.toString(speed3));
        System.out.println("Number of fleets: " + solution.carFleet(target3, position3, speed3));
        System.out.println();
        
        // Test Case 4
        int target4 = 10;
        int[] position4 = {6, 8};
        int[] speed4 = {3, 2};
        System.out.println("Test Case 4:");
        System.out.println("Target: " + target4);
        System.out.println("Position: " + Arrays.toString(position4));
        System.out.println("Speed: " + Arrays.toString(speed4));
        System.out.println("Number of fleets: " + solution.carFleet(target4, position4, speed4));
    }

}
