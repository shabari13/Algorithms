package neetcode150.greedy;

/*
 * You are given an integer array nums where each element nums[i] indicates your maximum jump length at that position.

Return true if you can reach the last index starting from index 0, or false otherwise.

Example 1:

Input: nums = [1,2,0,1,0]

Output: true
Explanation: First jump from index 0 to 1, then from index 1 to 3, and lastly from index 3 to 4.

Example 2:

Input: nums = [1,2,1,0,1]

Output: false
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
Time Complexity: O(n)

We iterate through the array exactly once, where n is the length of the array
Each iteration performs constant time operations: comparison, addition, and max calculation
Even though we might return early, in the worst case we visit all n elements

Space Complexity: O(1)

We only use a single integer variable maxReach to track our state
No additional data structures are created
The space used doesn't grow with input size - it's constant]
You stand on a stone and look at the number on it
That number tells you "I can jump 1 stone forward, 2 stones forward, or even 3 stones forward!"
You keep track of "What's the farthest stone I could possibly reach from where I am?"
You move to the next stone and update your "farthest reach"
If you ever find a stone that's too far away (you can't reach it), the game is over - you lose!
If your "farthest reach" touches the last stone, you win!
 */
public class JumpGame {

	public boolean canJump(int[] nums) {
		int farthest = 0;
		int maxJump = 0;
		for(int i = 0; i < nums.length; i++) {
			if(i > farthest)
				return false;
			farthest = Math.max(farthest, i + nums[i]);
			if(farthest >= nums.length -1)
				return true;
			
			
		}
		return false;
	}
	
	  public static void main(String[] args) {
	        JumpGame solution = new JumpGame();
	        
	        // Test Case 1: Can reach the end
	        int[] nums1 = {2, 3, 1, 1, 4};
	        System.out.println("Test Case 1: nums = [2, 3, 1, 1, 4]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums1));
	        System.out.println();
	        
	        // Test Case 2: Cannot reach the end
	        int[] nums2 = {3, 2, 1, 0, 4};
	        System.out.println("Test Case 2: nums = [3, 2, 1, 0, 4]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums2));
	        System.out.println();
	        
	        // Test Case 3: Single element
	        int[] nums3 = {0};
	        System.out.println("Test Case 3: nums = [0]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums3));
	        System.out.println();
	        
	        // Test Case 4: All elements are positive
	        int[] nums4 = {2, 0, 0};
	        System.out.println("Test Case 4: nums = [2, 0, 0]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums4));
	        System.out.println();
	        
	        // Test Case 5: Large jumps possible
	        int[] nums5 = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
	        System.out.println("Test Case 5: nums = [5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums5));
	        System.out.println();
	        
	        // Test Case 6: Cannot reach
	        int[] nums6 = {1, 1, 0, 1};
	        System.out.println("Test Case 6: nums = [1, 1, 0, 1]");
	        System.out.println("Can jump to last index? " + solution.canJump(nums6));
	    }
}
