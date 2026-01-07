package blind75.array;

/*
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

 

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1
 

Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
Time Complexity: O(n)

We visit each element at most once
Left pointer moves right, right pointer moves left
They meet in the middle after checking n elements
No nested loops!

Space Complexity: O(1)

We only use a few variables (left, right, maxArea, etc.)
No extra arrays or data structures needed
Memory usage doesn't grow with input size

The Strategy:

Start by placing your fingers on the first stick (left) and last stick (right)
Calculate how much water these two can hold
Then move ONE finger inward - always move the finger that's on the shorter stick
Why? Because the shorter stick is limiting us. Maybe if we move it, we'll find a taller stick!


Always move inware the pointer depending on the shorter height of the pointer
 */
public class ContainerWithMostWater {
	
	public static int maxWater(int[] nums) {
		int maxWater = 0;
		int left = 0;
		int right = nums.length - 1;
		while(left < right) {
			int width = right - left;
			int height = Math.min(nums[left], nums[right]);
			int currentArea = width * height;
			maxWater = Math.max(maxWater, currentArea);
			if(nums[left] < nums[right])
				left++;
			else 
				right--;
		}
		return maxWater;
	}
	
	public static void main(String[] args) {
        // Sample input
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        System.out.println("Heights: ");
        for (int i = 0; i < height.length; i++) {
            System.out.print(height[i] + " ");
        }
        System.out.println("\n");
        
        // Call the method
        int result = maxWater(height);
        
        // Print the output
        System.out.println("Maximum area of water that can be contained: " + result);
    }

}
