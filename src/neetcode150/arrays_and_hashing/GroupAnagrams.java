package neetcode150.arrays_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: strs = ["act","pots","tops","cat","stop","hat"]

Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
Example 2:

Input: strs = ["x"]

Output: [["x"]]
Example 3:

Input: strs = [""]

Output: [[""]]
Constraints:

1 <= strs.length <= 1000.
0 <= strs[i].length <= 100
strs[i] is made up of lowercase English letters.

Time Complexity: O(n × k log k)
Breaking it down:

n = number of words in the input array
k = maximum length of a word

Why?

We loop through each word once: O(n)
For each word, we sort its characters: O(k log k)
HashMap operations (put, get, containsKey) are: O(1) on average

Total: O(n) × O(k log k) = O(n × k log k)
Space Complexity: O(n × k)
Why?

We store all n words in the HashMap
Each word has length up to k
We also create character arrays for sorting (temporary space)

Total: O(n × k) for storing all the strings

The b

 */
public class GroupAnagrams {
	
	public List<List<String>> groupAnagrams(String[] strs) {
		
		Map<String, List<String>> map = new HashMap<>();
		
		for(String str : strs) {
			
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String key = new String(charArray);
			if(!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(str);
		}
		return new ArrayList<>(map.values());
	}
	
	 public static void main(String[] args) {
		 GroupAnagrams solution = new GroupAnagrams();
	        
	        // Test Case 1
	        System.out.println("Test Case 1:");
	        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
	        List<List<String>> result1 = solution.groupAnagrams(input1);
	        System.out.println("Input: " + Arrays.toString(input1));
	        System.out.println("Output: " + result1);
	        System.out.println();
	        
	        // Test Case 2
	        System.out.println("Test Case 2:");
	        String[] input2 = {""};
	        List<List<String>> result2 = solution.groupAnagrams(input2);
	        System.out.println("Input: " + Arrays.toString(input2));
	        System.out.println("Output: " + result2);
	        System.out.println();
	        
	        // Test Case 3
	        System.out.println("Test Case 3:");
	        String[] input3 = {"a"};
	        List<List<String>> result3 = solution.groupAnagrams(input3);
	        System.out.println("Input: " + Arrays.toString(input3));
	        System.out.println("Output: " + result3);
	        System.out.println();
	        
	        // Test Case 4
	        System.out.println("Test Case 4:");
	        String[] input4 = {"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"};
	        List<List<String>> result4 = solution.groupAnagrams(input4);
	        System.out.println("Input: " + Arrays.toString(input4));
	        System.out.println("Output: " + result4);
	    }

}
