package neetcode250.two_pointers;

import java.util.Arrays;

/*
 * You are given two integer arrays nums1 and nums2, both sorted in non-decreasing order, along with two integers m and n, where:

m is the number of valid elements in nums1,
n is the number of elements in nums2.
The array nums1 has a total length of (m+n), with the first m elements containing the values to be merged, and the last n elements set to 0 as placeholders.

Your task is to merge the two arrays such that the final merged array is also sorted in non-decreasing order and stored entirely within nums1.
You must modify nums1 in-place and do not return anything from the function.

Example 1:

Input: nums1 = [10,20,20,40,0,0], m = 4, nums2 = [1,2], n = 2

Output: [1,2,10,20,20,40]
Example 2:

Input: nums1 = [0,0], m = 0, nums2 = [1,2], n = 2

Output: [1,2]
Constraints:

0 <= m, n <= 200
1 <= (m + n) <= 200
nums1.length == (m + n)
nums2.length == n

The key insight is to fill nums1 from right to left, comparing the largest unplaced elements from each array. This avoids overwriting valid elements in nums1.
Why not merge from the front?
Merging left-to-right would require shifting elements, risking overwriting valid data. Starting from the back, the empty slots (0s) are always available first.



TimeO(m + n)Each element is visited exactly once
SpaceO(1)In-place, no extra arrays used

 */
public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int p1 = m-1;
		int p2 = n-1;
		int fill = m + n - 1;
		while(p1 >= 0 && p2 >= 0) {
			if(nums1[p1] > nums2[p2]) {
				nums1[fill] = nums1[p1];
				p1--;
			} else {
				nums1[fill] = nums2[p2];
				p2--;
			}
			fill--;
		}
		while(p2 >=0 ) {
			nums1[fill] = nums2[p2];
			p2--;
			fill--;
		}
		
	}
	
	public static void main(String[] args) {
		MergeSortedArray solution = new MergeSortedArray();

	    // Example 1
	    int[] nums1 = {10, 20, 20, 40, 0, 0};
	    int[] nums2 = {1, 2};
	    solution.merge(nums1, 4, nums2, 2);
	    System.out.println("Example 1: " + Arrays.toString(nums1));

	    // Example 2
	    int[] nums3 = {0, 0};
	    int[] nums4 = {1, 2};
	    solution.merge(nums3, 0, nums4, 2);
	    System.out.println("Example 2: " + Arrays.toString(nums3));
	}
}
