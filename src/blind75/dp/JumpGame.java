package blind75.dp;
/*
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 
 Time Complexity: O(n)

We visit each element in the array exactly once
n = length of the array
Even though we might return early, worst case is visiting all elements

💾 Space Complexity: O(1)

We only use one extra variable: maxReach
No extra arrays, no recursion stack
Constant space regardless of input size!

Instead of trying every possible jump combination (which would be super slow), we just keep track of "What's the farthest I can possibly reach?"
If at any point we're standing somewhere we can't reach, game over! Otherwise, if our "maximum reach" extends to or past the end, we win! 🏆
📝 How The Solution Works (Step-by-Step)
The Magic Variable: maxReach

This keeps track of the farthest lily pad we can possibly reach
We start at position 0, so initially maxReach = 0

The Simple Rule:

At each position, we ask: "Can I even GET here?" (Is current position ≤ maxReach?)
If YES: Update how far we can go from here
If NO: We're stuck! Return false
 */
public class JumpGame {

	public boolean canJump(int[] nums) {
		int maxReach = 0;
		
		for(int i = 0; i < nums.length; i++) {
			if( i > maxReach)
				return false;
			maxReach = Math.max(maxReach, i + nums[i]);
			if(maxReach >= nums.length - 1) {
				return true;
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        
        // Test Case 1: Can reach the end
        int[] test1 = {2, 3, 1, 1, 4};
        System.out.println("Test Case 1: " + java.util.Arrays.toString(test1));
        System.out.println("Can jump to end? " + solution.canJump(test1));
        System.out.println();
        
        // Test Case 2: Cannot reach the end
        int[] test2 = {3, 2, 1, 0, 4};
        System.out.println("Test Case 2: " + java.util.Arrays.toString(test2));
        System.out.println("Can jump to end? " + solution.canJump(test2));
        System.out.println();
        
        // Test Case 3: Single element (already at end)
        int[] test3 = {0};
        System.out.println("Test Case 3: " + java.util.Arrays.toString(test3));
        System.out.println("Can jump to end? " + solution.canJump(test3));
        System.out.println();
        
        // Test Case 4: Can reach with exact jumps
        int[] test4 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 4: " + java.util.Arrays.toString(test4));
        System.out.println("Can jump to end? " + solution.canJump(test4));
        System.out.println();
        
        // Test Case 5: Large jump at start
        int[] test5 = {5, 0, 0, 0, 0};
        System.out.println("Test Case 5: " + java.util.Arrays.toString(test5));
        System.out.println("Can jump to end? " + solution.canJump(test5));
        System.out.println();
        
        // Test Case 6: Zero in middle blocks path
        int[] test6 = {1, 0, 1, 0};
        System.out.println("Test Case 6: " + java.util.Arrays.toString(test6));
        System.out.println("Can jump to end? " + solution.canJump(test6));
        System.out.println();
        
        // Test Case 7: Multiple paths possible
        int[] test7 = {2, 0, 6, 9, 8, 4, 5, 0, 8, 9, 1, 2, 9, 6, 8, 8, 0, 6, 3, 1, 2, 2, 1, 2, 6, 5, 3, 1, 2, 2, 6, 4, 2, 4, 3, 0, 0, 0, 3, 8, 2, 4, 0, 1, 2, 0, 1, 4, 6, 5, 8, 0, 7, 9, 3, 4, 6, 6, 5, 8, 9, 3, 4, 3, 7, 0, 4, 9, 0, 9, 8, 4, 3, 0, 7, 7, 1, 9, 1, 9, 4, 9, 0, 1, 9, 5, 7, 7, 1, 5, 8, 2, 8, 2, 6, 8, 2, 2, 7, 5, 1, 7, 9, 6};
        System.out.println("Test Case 7: Large array");
        System.out.println("Can jump to end? " + solution.canJump(test7));
    }
}
