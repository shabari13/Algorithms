package neetcode250.binary_search;
/*
 * ou are given an integer array piles where piles[i] is the number of bananas in the ith pile. You are also given an integer h, which represents the number of hours you have to eat all the bananas.

You may decide your bananas-per-hour eating rate of k. Each hour, you may choose a pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour.

Return the minimum integer k such that you can eat all the bananas within h hours.

Example 1:

Input: piles = [1,4,3,2], h = 9

Output: 2
Explanation: With an eating rate of 2, you can eat the bananas in 6 hours. With an eating rate of 1, you would need 10 hours to eat all the bananas (which exceeds h=9), thus the minimum eating rate is 2.

Example 2:

Input: piles = [25,10,23,4], h = 4

Output: 25
Constraints:

1 <= piles.length <= 1,000
piles.length <= h <= 1,000,000
1 <= piles[i] <= 1,000,000,000

Time Complexity

Binary search runs:

log(maxPile)

Each step scans the array

O(n)

So total complexity:

O(n log m)

Where

n = number of piles
m = max bananas in a pile
6. Space Complexity
O(1)

Only a few variables are used.
 */
public class KokoEatingBananas {
	
	public static int minEatingSpeed(int[] piles, int h) {
		int left = 0 ;
		int right = getMax(piles);
		int result = right;
		while(left <= right) {
			int mid = left + (right - left)/2;
			int totalHours = calculateTime(piles, mid);
			if(totalHours <= h) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid+1;
			}
		}
		return result;
	}
	public static int getMax(int[] piles) {
		int max = piles[0];
		for(int  i = 1; i < piles.length; i++) {
			max = Math.max(max,  piles[i]);
		}
		return max;
	}
	
	public static int calculateTime(int[] piles, int speed) {
		int total = 0;
		
		for(int i = 0; i < piles.length; i++) {
			total += Math.ceil((double) piles[i] / speed);
		}
		return total;	
	}
	
	 public static void main(String[] args) {

	        int[] piles1 = {3,6,7,11};
	        int h1 = 8;

	        int[] piles2 = {30,11,23,4,20};
	        int h2 = 5;

	        int[] piles3 = {30,11,23,4,20};
	        int h3 = 6;

	        System.out.println("Example 1:");
	        System.out.println("Minimum speed: " + minEatingSpeed(piles1, h1));

	        System.out.println("\nExample 2:");
	        System.out.println("Minimum speed: " + minEatingSpeed(piles2, h2));

	        System.out.println("\nExample 3:");
	        System.out.println("Minimum speed: " + minEatingSpeed(piles3, h3));
	    }

}
