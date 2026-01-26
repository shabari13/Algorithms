package neetcode150.arrays_and_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * 
 * Encode and Decode Strings
Medium
Company Tags
Hints
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
    // ... your code
    return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
    //... your code
    return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Example 1:

Input: dummy_input = ["Hello","World"]

Output: ["Hello","World"]

Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

Machine 2:
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
Example 2:

Input: dummy_input = [""]

Output: [""]

Constraints:

0 <= strs.length < 100
0 <= strs[i].length < 200
strs[i] contains any possible characters out of 256 valid ASCII characters.

Encode TC : O(n) and SC : O(n)

Encoding: O(n)

n = total number of characters in all strings
We visit each character exactly once to build the encoded string

Decoding: O(n)

n = length of the encoded string
We visit each character exactly once to extract the original strings

Overall: O(n) for both operations

💾 Space Complexity
Encoding: O(n)

We create a new StringBuilder that stores all characters plus the length numbers and # symbols
In the worst case, this is proportional to the input size

Decoding: O(n)

We create a new list to store the decoded strings
The total space used is proportional to the original input
 */

public class EncodeDecodeString {

	
	public String encode(List<String> strs) {
		
		StringBuilder sb  = new StringBuilder();
		
		for(String str : strs) {
			sb.append(str.length()).append('#').append(str);
		
		}
		return sb.toString();
	}
	
	public List<String> decode(String str) {
		List<String> result = new ArrayList<>();
		int i = 0 ;
		
		while(i < str.length()) {
			int delimiterPos = str.indexOf('#', i);
			int length = Integer.parseInt(str.substring(i, delimiterPos));
			int start = delimiterPos + 1;
			String word  = str.substring(start, start + length);
			result.add(word);
			i = start + length;
			
		}
				
		return result;
		
	}
	
	public static void main(String[] args) {
		EncodeDecodeString codec = new EncodeDecodeString();
        
        // Test Case 1: Regular strings
        System.out.println("=== Test Case 1 ===");
        List<String> input1 = Arrays.asList("hello", "world");
        String encoded1 = codec.encode(input1);
        System.out.println("Original: " + input1);
        System.out.println("Encoded: " + encoded1);
        List<String> decoded1 = codec.decode(encoded1);
        System.out.println("Decoded: " + decoded1);
        System.out.println();
        
        // Test Case 2: Strings with special characters
        System.out.println("=== Test Case 2 ===");
        List<String> input2 = Arrays.asList("a#b", "c:d", "e,f");
        String encoded2 = codec.encode(input2);
        System.out.println("Original: " + input2);
        System.out.println("Encoded: " + encoded2);
        List<String> decoded2 = codec.decode(encoded2);
        System.out.println("Decoded: " + decoded2);
        System.out.println();
        
        // Test Case 3: Empty strings
        System.out.println("=== Test Case 3 ===");
        List<String> input3 = Arrays.asList("", "a", "");
        String encoded3 = codec.encode(input3);
        System.out.println("Original: " + input3);
        System.out.println("Encoded: " + encoded3);
        List<String> decoded3 = codec.decode(encoded3);
        System.out.println("Decoded: " + decoded3);
        System.out.println();
        
        // Test Case 4: Single string
        System.out.println("=== Test Case 4 ===");
        List<String> input4 = Arrays.asList("leetcode");
        String encoded4 = codec.encode(input4);
        System.out.println("Original: " + input4);
        System.out.println("Encoded: " + encoded4);
        List<String> decoded4 = codec.decode(encoded4);
        System.out.println("Decoded: " + decoded4);
        System.out.println();
        
        // Test Case 5: Strings with numbers
        System.out.println("=== Test Case 5 ===");
        List<String> input5 = Arrays.asList("123", "45#67", "89");
        String encoded5 = codec.encode(input5);
        System.out.println("Original: " + input5);
        System.out.println("Encoded: " + encoded5);
        List<String> decoded5 = codec.decode(encoded5);
        System.out.println("Decoded: " + decoded5);
    }
			
}
