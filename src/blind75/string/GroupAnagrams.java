package blind75.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]

Time Complexity: O(n × k log k)

n = number of words
k = maximum length of a word
We go through each word once: O(n)
For each word, we sort it: O(k log k)
Total: O(n × k log k)

Space Complexity: O(n × k)

We store all n words in the HashMap
Each word takes up to k characters
The sorted keys also take space: O(n × k)
Total: O(n × k)

Time Complexity: O(n × k log k)

n = number of words
k = maximum length of a word
We go through each word once: O(n)
For each word, we sort it: O(k log k)
Total: O(n × k log k)

Space Complexity: O(n × k)

We store all n words in the HashMap
Each word takes up to k characters
The sorted keys also take space: O(n × k)
Total: O(n × k)

 */

public class GroupAnagrams {

	public List<List<String>> groupAnagrams(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		
		for (String word : words) {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);
			if(!map.containsKey(sortedWord)) {
				map.put(sortedWord, new ArrayList<>());
				
			}
			map.get(sortedWord).add(word);
				
		} 
		
		 return new ArrayList<>(map.values());
	}
	
	
}
