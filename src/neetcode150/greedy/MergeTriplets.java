package neetcode150.greedy;
/*
 * 
 * You are given a 2D array of integers triplets, where triplets[i] = [ai, bi, ci] represents the ith triplet. You are also given an array of integers target = [x, y, z] which is the triplet we want to obtain.

To obtain target, you may apply the following operation on triplets zero or more times:

Choose two different triplets triplets[i] and triplets[j] and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
* E.g. if triplets[i] = [1, 3, 1] and triplets[j] = [2, 1, 2], triplets[j] will be updated to [max(1, 2), max(3, 1), max(1, 2)] = [2, 3, 2].

Return true if it is possible to obtain target as an element of triplets, or false otherwise.

Example 1:

Input: triplets = [[1,2,3],[7,1,1]], target = [7,2,3]

Output: true
Explanation:
Choose the first and second triplets, update the second triplet to be [max(1, 7), max(2, 1), max(3, 1)] = [7, 2, 3].

Example 2:

Input: triplets = [[2,5,6],[1,4,4],[5,7,5]], target = [5,4,6]

Output: false
Constraints:

1 <= triplets.length <= 1000
1 <= ai, bi, ci, x, y, z <= 100

We loop through triplets once.

If there are n triplets:

O(n)


Each check is constant time.

📦 Space Complexity

We only use 3 boolean variables:

O(1)


Constant space.

if  in any of the triplets, if any of the elements is greater the target element we should ignore that triplet.
since we can merge triplets multiple times, just check any of the first second thirs elemnts are euals 
in any of the remaining valid triplets then you know we can get the target.

 */
public class MergeTriplets {
	
	public static boolean mergeTriplets(int[][] triplets, int[] target) {
		boolean first = false;
		boolean second = false;
		boolean third = false;
		for(int[] triplet : triplets) {
			if(triplet[0] > target[0] ||
					triplet[1] > target[1] ||
					triplet[2] > target[2])
					continue;
			if(triplet[0] == target[0])
				first = true;
			if(triplet[1] == target[1])
				second = true;
			if(triplet[2] == target[2])
				third = true;
		}
		return first && second && third;
	}
	
	 public static void main(String[] args) {

	        // Example 1
	        int[][] triplets1 = {{2,5,3},{1,8,4},{1,7,5}};
	        int[] target1 = {2,7,5};
	        System.out.println("Example 1 Output: " + mergeTriplets(triplets1, target1));

	        // Example 2
	        int[][] triplets2 = {{3,4,5},{4,5,6}};
	        int[] target2 = {3,2,5};
	        System.out.println("Example 2 Output: " + mergeTriplets(triplets2, target2));

	        // Example 3
	        int[][] triplets3 = {{2,5,3},{2,3,4},{1,2,5},{5,2,3}};
	        int[] target3 = {5,5,5};
	        System.out.println("Example 3 Output: " + mergeTriplets(triplets3, target3));
	    }

}
