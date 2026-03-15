package neetcode250.two_pointers;

import java.util.Arrays;

/*
 * You are given an integer array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

Example 1:

Input: people = [5,1,4,2], limit = 6

Output: 2
Explanation:
First boat [5,1].
Second boat [4,2].

Example 2:

Input: people = [1,3,2,3,2], limit = 3

Output: 4
Explanation:
First boat [3].
Second boat [3].
Third boat [1,2].
Fourth boat [2].

Constraints:

1 <= people.length <= 50,000
1 <= people[i] <= limit <= 30,000

ComplexityReasonTimeO(n log n)Sorting dominates; two-pointer scan is O(n)
SpaceO(1)Only a few integer variables used; sorting is in-place
 */
public class BoatsToSavePeople {
	  public static int numRescueBoats(int[] people, int limit) {
		  Arrays.sort(people);
		  int left = 0;
		  int right = people.length - 1;
		  int boat = 0;
		  
		  while(left <= right) {
			  if(people[left] + people[right] <= limit) left++;
			  right--;
			  boat++;
		  }
	        return boat;
	    }
	  public static void main(String[] args) {

	        // Test Case 1
	        int[] people1 = {1, 2};
	        int limit1 = 3;
	        System.out.println("Input: " + Arrays.toString(people1) + ", Limit: " + limit1);
	        System.out.println("Minimum Boats: " + numRescueBoats(people1, limit1));
	        // Expected: 1 (both fit in one boat: 1+2=3)

	        System.out.println();

	        // Test Case 2
	        int[] people2 = {3, 2, 2, 1};
	        int limit2 = 3;
	        System.out.println("Input: " + Arrays.toString(people2) + ", Limit: " + limit2);
	        System.out.println("Minimum Boats: " + numRescueBoats(people2, limit2));
	        // Expected: 3

	        System.out.println();

	        // Test Case 3
	        int[] people3 = {3, 5, 3, 4};
	        int limit3 = 5;
	        System.out.println("Input: " + Arrays.toString(people3) + ", Limit: " + limit3);
	        System.out.println("Minimum Boats: " + numRescueBoats(people3, limit3));
	        // Expected: 4

	        System.out.println();

	        // Test Case 4: All same weight
	        int[] people4 = {2, 2, 2, 2};
	        int limit4 = 4;
	        System.out.println("Input: " + Arrays.toString(people4) + ", Limit: " + limit4);
	        System.out.println("Minimum Boats: " + numRescueBoats(people4, limit4));
	        // Expected: 2

	        System.out.println();

	        // Test Case 5: Everyone too heavy to pair
	        int[] people5 = {5, 5, 5};
	        int limit5 = 6;
	        System.out.println("Input: " + Arrays.toString(people5) + ", Limit: " + limit5);
	        System.out.println("Minimum Boats: " + numRescueBoats(people5, limit5));
	        // Expected: 3 (each person needs their own boat)
	    }
}
