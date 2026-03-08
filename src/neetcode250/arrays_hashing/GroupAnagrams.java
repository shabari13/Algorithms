package neetcode250.arrays_hashing;

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

⏱ Time Complexity

Let:

N = number of words

K = average length of each word

For each word:

Sorting takes O(K log K)

So total time:

O(N × K log K)
📦 Space Complexity

We store:

HashMap with up to N keys

All words stored again in lists

So space complexity:

O(N × K)
 */
public class GroupAnagrams {

	public static List<List<String>> groupAnagrams(String[] strs) {
		if(strs == null || strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> map = new HashMap<>();
		for(String str : strs) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String key  = new String(charArray);
			if(!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(str);
		}
		return new ArrayList<>(map.values());
		
	}
	
	 public static void main(String[] args) {

	        // Sample Input 1
	        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
	        System.out.println("Input 1: " + Arrays.toString(input1));
	        System.out.println("Output 1: " + groupAnagrams(input1));
	        System.out.println();

	        // Sample Input 2
	        String[] input2 = {"listen", "silent", "enlist", "hello", "below", "elbow"};
	        System.out.println("Input 2: " + Arrays.toString(input2));
	        System.out.println("Output 2: " + groupAnagrams(input2));
	        System.out.println();

	        // Sample Input 3
	        String[] input3 = {"abc", "bca", "cab", "xyz", "zyx", "zxy", "a"};
	        System.out.println("Input 3: " + Arrays.toString(input3));
	        System.out.println("Output 3: " + groupAnagrams(input3));
	    }
}
