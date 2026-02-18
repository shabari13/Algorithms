package neetcode150.greedy;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given a string s consisting of lowercase english letters.

We want to split the string into as many substrings as possible, while ensuring that each letter appears in at most one substring.

Return a list of integers representing the size of these substrings in the order they appear in the string.

Example 1:

Input: s = "xyxxyzbzbbisl"

Output: [5, 5, 1, 1, 1]
Explanation: The string can be split into ["xyxxy", "zbzbb", "i", "s", "l"].

Example 2:

Input: s = "abcabc"

Output: [6]
Constraints:

1 <= s.length <= 100

TC on[n)
SC O(1)

⏳ Time Complexity

First loop to store last positions → O(n)

Second loop to partition → O(n)

Total:

O(n)

📦 Space Complexity

Array of size 26 → O(1) (constant space)

Result list → O(k) partitions

Overall:

O(1) auxiliary space

Basically you keep track of last occurrence of a character in each array . Then you get start and end. Find for each index what the max end. while looping
through each character in an array find the max end. If index is equal to the end then we can substring it.Add the end-start+1 to result array
And start position will be the i+1 for the next substring

 */
public class PartitionLabel {
	
	public static List<Integer> partitionLabels(String s){
		List<Integer> result = new ArrayList<>();
		int[] lastOccurence = new int[26];
		for(int i = 0; i < s.length(); i++) {
			lastOccurence[s.charAt(i) - 'a'] = i;
		}
		int start = 0;
		int end = 0;
		for(int i = 0; i < s.length(); i++) {
			end = Math.max(end, lastOccurence[s.charAt(i) - 'a']);
			if(i == end) {
				result.add(end - start + 1);
				start = i + 1;
			}
		}
		return result;
	}
	
public static void main(String[] args) {
        
        String s1 = "ababcbacadefegdehijhklij";
        String s2 = "eccbbbbdec";
        String s3 = "abc";
        String s4 = "aaaaa";

        System.out.println("Input: " + s1);
        System.out.println("Output: " + partitionLabels(s1));
        System.out.println();

        System.out.println("Input: " + s2);
        System.out.println("Output: " + partitionLabels(s2));
        System.out.println();

        System.out.println("Input: " + s3);
        System.out.println("Output: " + partitionLabels(s3));
        System.out.println();

        System.out.println("Input: " + s4);
        System.out.println("Output: " + partitionLabels(s4));
    }

}
