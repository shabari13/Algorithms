package neetcode150.two_pointers;
/*
 * 
 * You are given an integer array heights where heights[i] represents the height of the 
i
t
h
i 
th
  bar.

You may choose any two bars to form a container. Return the maximum amount of water a container can store.

Example 1:



Input: height = [1,7,2,5,4,7,3,6]

Output: 36
Example 2:

Input: height = [2,2,2]

Output: 4
Constraints:

2 <= height.length <= 1000
0 <= height[i] <= 1000

Time Complexity: O(n)

We visit each element at most once
Left pointer moves from 0 → right
Right pointer moves from n-1 → left
They meet in the middle, so total steps = n

Space Complexity: O(1)

We only use a few variables (left, right, maxWater, width, currentHeight, currentArea)
No extra arrays or data structures needed!
 */
public class ContainerWithMostWater {

	public int maxArea(int[] heights) {
		int maxArea = 0;
		int left = 0;
		
		int right = heights.length - 1;
		while(left < right) {
			int currentArea = Math.min(heights[left], heights[right]) * (right - left);
			maxArea = Math.max(maxArea, currentArea);
			if(heights[left] < heights[right])
				left++;
			else right--;
			
		}
		return maxArea;
	}
	 public static void main(String[] args) {
		 ContainerWithMostWater solution = new ContainerWithMostWater();
	        
	        // Test case 1
	        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
	        System.out.println("Input: [1, 8, 6, 2, 5, 4, 8, 3, 7]");
	        System.out.println("Output: " + solution.maxArea(height1));
	        System.out.println();
	        
	        // Test case 2
	        int[] height2 = {1, 1};
	        System.out.println("Input: [1, 1]");
	        System.out.println("Output: " + solution.maxArea(height2));
	        System.out.println();
	        
	        // Test case 3
	        int[] height3 = {4, 3, 2, 1, 4};
	        System.out.println("Input: [4, 3, 2, 1, 4]");
	        System.out.println("Output: " + solution.maxArea(height3));
	        System.out.println();
	        
	        // Test case 4
	        int[] height4 = {1, 2, 1};
	        System.out.println("Input: [1, 2, 1]");
	        System.out.println("Output: " + solution.maxArea(height4));
	    }
}
