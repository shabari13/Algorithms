package neetcode150.greedy;

import java.util.TreeMap;
/*
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.

Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
Example 2:

Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.

 

Constraints:

1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length

one need to use TreeMap. TreeMap will have sorted order of keys.
once ou use it in sequnce, reduce the count or remove the key.Also check the lenght and diving by k should yield 0 reminder.
Time: O(N log N) — building the map is O(N log N), and the greedy loop touches each card once with O(log N) TreeMap ops each.
Space: O(N) — the TreeMap holds at most N distinct entries.
Let:

N = number of cards

M = number of unique cards

Building map → O(N log M)

Each card removal → O(log M)

Total complexity:

O(N log M)


Worst case:

O(N log N)

📦 Space Complexity

We store counts in TreeMap.

Worst case:

O(N)


If all numbers are unique.
 */

public class HandOfStriaghts {
	
	public static boolean isNStraightHand(int[] nums, int k) {
		if(nums.length % k != 0){
			return false;
		}
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		while(!map.isEmpty()) {
			int first = map.firstKey();
			for(int i = 0; i < k; i++) {
				int currentCard = i + first;
				if(!map.containsKey(currentCard)) {
					return false;
				}
				int count = map.get(currentCard);
				if(count == 1)
					map.remove(currentCard);
				else 
					map.put(currentCard, count - 1);
			}
		}
		return true;
		
		
	}
	
	 public static void main(String[] args) {

	        // Test 1: Classic example — should be TRUE
	        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
	        int groupSize1 = 3;
	        System.out.println("Test 1: hand=[1,2,3,6,2,3,4,7,8], groupSize=3");
	        System.out.println("Result: " + isNStraightHand(hand1, groupSize1));
	        System.out.println();

	        // Test 2: Can't form groups — should be FALSE
	        int[] hand2 = {1, 2, 3, 4};
	        int groupSize2 = 3;
	        System.out.println("Test 2: hand=[1,2,3,4], groupSize=3");
	        System.out.println("Result: " + isNStraightHand(hand2, groupSize2));
	        System.out.println();

	        // Test 3: Single card groups — always TRUE
	        int[] hand3 = {5, 10, 3};
	        int groupSize3 = 1;
	        System.out.println("Test 3: hand=[5,10,3], groupSize=1");
	        System.out.println("Result: " + isNStraightHand(hand3, groupSize3));
	        System.out.println();

	        // Test 4: Perfect ascending sequence — TRUE
	        int[] hand4 = {1, 2, 3, 4, 5, 6};
	        int groupSize4 = 3;
	        System.out.println("Test 4: hand=[1,2,3,4,5,6], groupSize=3");
	        System.out.println("Result: " + isNStraightHand(hand4, groupSize4));
	        System.out.println();

	        // Test 5: Uneven total — FALSE (divisibility check)
	        int[] hand5 = {1, 2, 3, 4, 5};
	        int groupSize5 = 3;
	        System.out.println("Test 5: hand=[1,2,3,4,5], groupSize=3");
	        System.out.println("Result: " + isNStraightHand(hand5, groupSize5));
	        System.out.println();

	        // Test 6: Gap in sequence — FALSE
	        int[] hand6 = {1, 2, 4, 5, 6, 7};
	        int groupSize6 = 3;
	        System.out.println("Test 6: hand=[1,2,4,5,6,7], groupSize=3");
	        System.out.println("Result: " + isNStraightHand(hand6, groupSize6));
	    }

}
