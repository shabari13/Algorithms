package neetcode250.linked_list;

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

1 <= n <= 10,000
nums.length == n + 1
1 <= nums[i] <= n

The Problem
Given an array of integers where values are in the range [1, n] and the array has n+1 elements, find the one duplicate without modifying the array and using O(1) extra space. This is the classic "Find the Duplicate Number" problem (LeetCode 287).

The Idea: Floyd's Tortoise and Hare (Cycle Detection)
Think of each index in the array as a "node" and each value as a "pointer" to the next node. Because one value is duplicated, two different indices point to the same next node — this creates a cycle, just like in a linked list. Floyd's algorithm sends two pointers through this structure: a slow one (moves one step at a time) and a fast one (moves two steps). They'll eventually meet inside the cycle, and from there, a second phase pinpoints the exact entry of the cycle — which is the duplicate number.

Explain it like I'm 5 🐢🐇
Imagine a race track that has a loop in it. You send a turtle 🐢 who takes one step at a time, and a rabbit 🐇 who jumps two steps at a time. Since the track loops back (because of the duplicate number), they will eventually meet somewhere on the loop.
Once they meet, you put the turtle back at the very start of the track. Now both turtle and rabbit walk one step at a time. The place where they meet again is the start of the loop — that's your duplicate number! It's like they both converge at the "fork in the road" caused by the duplicate.
Time and Space Complexity
Time Complexity: O(n)
Both phases each traverse the array at most a linear number of steps. Phase 1 is bounded by how long before the two pointers meet (at most O(n) steps), and Phase 2 similarly takes O(n) steps in the worst case. There are no nested loops — only two single-pass pointer movements.
Space Complexity: O(1)
Only two integer variables (slow and fast) are used regardless of input size. The array is never copied, sorted, or augmented. This satisfies the problem's strict "constant extra space" constraint.
 */
public class FindTheDuplicateNumber {
	 public static int findDuplicate(int[] nums) {
	        int slow = nums[0];
	        int fast = nums[0];
	        do {
	        	slow = nums[slow];
	        	fast = nums[nums[fast]];
	        } while (slow != fast);
	        slow = nums[0];
	        while(slow != fast) {
	        	slow = nums[slow];
	        	fast = nums[fast];
	        }
	        return slow; 
	 }
	 
	 public static void main(String[] args) {

	        // Test 1 — standard example with duplicate 2
	        int[] test1 = {1, 3, 4, 2, 2};
	        System.out.println("Input:  [1, 3, 4, 2, 2]");
	        System.out.println("Output: " + findDuplicate(test1));  // → 2
	        System.out.println();

	        // Test 2 — duplicate is at the end of the value range
	        int[] test2 = {3, 1, 3, 4, 2};
	        System.out.println("Input:  [3, 1, 3, 4, 2]");
	        System.out.println("Output: " + findDuplicate(test2));  // → 3
	        System.out.println();

	        // Test 3 — duplicate is 1 (smallest possible value)
	        int[] test3 = {1, 1};
	        System.out.println("Input:  [1, 1]");
	        System.out.println("Output: " + findDuplicate(test3));  // → 1
	        System.out.println();

	        // Test 4 — duplicate appears more than twice
	        int[] test4 = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
	        System.out.println("Input:  [2, 5, 9, 6, 9, 3, 8, 9, 7, 1]");
	        System.out.println("Output: " + findDuplicate(test4));  // → 9
	        System.out.println();

	        // Test 5 — duplicate is the largest value
	        int[] test5 = {4, 3, 1, 4, 2};
	        System.out.println("Input:  [4, 3, 1, 4, 2]");
	        System.out.println("Output: " + findDuplicate(test5));  // → 4
	    }
}
