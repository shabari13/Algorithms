package neetcode150.linked_list;

/*
 * You are given an array of integers nums containing n + 1 integers. Each integer in nums is in the range [1, n] inclusive.

Every integer appears exactly once, except for one integer which appears two or more times. Return the integer that appears more than once.

Example 1:

Input: nums = [1,2,3,2,2]

Output: 2
Example 2:

Input: nums = [1,2,3,4,4]

Output: 4
Follow-up: Can you solve the problem without modifying the array nums and using 
O
(
1
)
O(1) extra space?

Constraints:

1 <= n <= 10000
nums.length == n + 1
1 <= nums[i] <= n
Complexity Analysis
Time Complexity: O(n)

We visit each element at most twice (once in each phase)
n is the number of elements in the array

Space Complexity: O(1)

We only use two pointers (slow and fast)
No extra arrays or data structures needed!
Phase 1: Finding the Meeting Point
Starting: Both turtle and rabbit start at nums[0] = 1
Iteration 1:

Turtle moves 1 step: nums[1] = 3 (turtle is at position 3)
Rabbit moves 2 steps: nums[1] = 3, then nums[3] = 2 (rabbit is at position 2)
Are they at the same position? No (3 ≠ 2)

Iteration 2:

Turtle moves 1 step: nums[3] = 2 (turtle is at position 2)
Rabbit moves 2 steps: nums[2] = 4, then nums[4] = 2 (rabbit is at position 2)
Are they at the same position? YES! Both are at position 2

They met! But position 2 isn't necessarily our answer yet.

Phase 2: Finding the Duplicate
Now we reset the turtle to the start (position 0), but keep the rabbit where it met the turtle (position 2).
Both move 1 step at a time now:
Iteration 1:

Turtle: starts at nums[0] = 1 → moves to position 1
Rabbit: starts at position 2 → nums[2] = 4 → moves to position 4
Are they equal? No (1 ≠ 4)

Iteration 2:

Turtle: at position 1 → nums[1] = 3 → moves to position 3
Rabbit: at position 4 → nums[4] = 2 → moves to position 2
Are they equal? No (3 ≠ 2)

Iteration 3:

Turtle: at position 3 → nums[3] = 2 → moves to position 2
Rabbit: at position 2 → nums[2] = 4 → moves to position 4
Wait, let me recalculate...

Actually, when they both move one step:

Turtle: from position 1 → goes to position nums[1] = 3
Rabbit: from position 2 → goes to position nums[2] = 4

Then:

Turtle: from position 3 → goes to position nums[3] = 2
Rabbit: from position 4 → goes to position nums[4] = 2

They meet at position 2!
The answer is 2 (the value at that position).
 */
public class FindDuplicate {
	public int findDuplicate(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return -1;
		int slow = nums[0];
		int fast = nums[0];
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}while(slow != fast);
		 slow = nums[0];
		 while(slow != fast) {
			 slow = nums[slow];
			 fast = nums[fast];
		 }
		 return slow;
		
	}

}
