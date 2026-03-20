package neetcode250.binary_search;
/*
 * You are given an array of length n which was originally sorted in non-decreasing order (not necessarily with distinct values). It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Given the rotated sorted array nums and an integer target, return true if target is in nums, or false if it is not present.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [3,4,4,5,6,1,2,2], target = 1

Output: true
Example 2:

Input: nums = [3,5,6,0,0,1,2], target = 4

Output: false
Constraints:

1 <= nums.length <= 5000
-10,000 <= target, nums[i] <= 10,000
nums is guaranteed to be rotated at some pivot.

💡 The Idea (Simple Explanation)
Imagine you have a row of numbered cards sorted in order, but someone picked them up, cut the deck somewhere in the middle, and swapped the two halves — then possibly added a few duplicate cards. You're trying to find if a specific number is in there. Instead of checking every card one-by-one, we use Binary Search — we always look at the middle card and decide: is my target in the left half or the right half? The twist is that because the deck is rotated, one half is always in order. We figure out which half is sorted, check if our target fits in it, and dive into that half. If duplicates confuse us (middle card equals edge card), we just shrink our search window by one step.
Imagine a row of toy blocks with numbers on them. Someone sorted them in order, but then cut the row in half and swapped the two pieces (that's the "rotation"). Now some blocks even have the same number (duplicates). Your job: find if a specific number is hiding in there.
Instead of checking every block one by one, you use a clever shortcut:

Always look at the middle block first.
If it's your number — you win! 🎉
One side of the row is always in perfect order (low to high). Figure out which side.
Ask: could my number be hiding in that neat side? If yes, throw away the messy side. If no, throw away the neat side.
But if the first, middle, and last block all show the same number, you can't tell which side is neat. Just peel off one block from each end and try again.
Keep shrinking until you find it — or run out of blocks.

Time Complexity:

Average / Best case: O(log n) — Normal binary search halves the window each iteration.
Worst case: O(n) — When the array is all duplicates (e.g. [1,1,1,1,1,1]), the shrink-by-one path fires every iteration, degrading to a linear scan. This is the key difference from Search in Rotated Sorted Array I (which has no duplicates and is always O(log n)).
 */
public class SearchinRotatedSortedArrayII {
	
	public boolean search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(nums[mid] == target) {
				return true;
			} 
			
			   // Step 3: Handle the tricky duplicate case
            // If nums[left] == nums[mid] == nums[right], we can't determine which
            // half is sorted. Safe move: shrink both ends by 1.
			if(nums[left] == nums[mid] && nums[mid] == nums[right]) {
				left++;
				right--;
			}
			else if(nums[mid] <= nums[right]) {
				if(target > nums[mid] && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid  - 1;
				}
			} else {
				if(target >= nums[left] && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return false;
		
	}

}
