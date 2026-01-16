package blind75.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * 
 * Problem: Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
Constraints:

The string may contain any possible characters out of 256 valid ASCII characters
Your encode and decode methods should be stateless (no instance variables)
The encoded string should be able to be decoded back to the original list of strings

Input: ["hello","world"]
encode("hello","world") -> "5#hello5#world"
decode("5#hello5#world") -> ["hello","world"]

Encoding:
Time Complexity: O(n)

n = total number of characters in all strings combined
We iterate through each string once: O(k) where k = number of strings
For each string, we append it to StringBuilder: O(length of string)
Total: We touch each character exactly once = O(n)

Space Complexity: O(n)

We create a StringBuilder that stores the encoded string
In the worst case, if all strings are length 1, encoded format is "1#a1#b1#c..."
Each string of length L becomes approximately (digits + 1 + L) characters
Overall, it's proportional to the input size = O(n)
Decoding:
Time Complexity: O(n)

n = length of encoded string
We scan through the encoded string once
For each encoded segment, we:

Find '#': O(d) where d = digits in length (usually small, max 3 for length ≤ 200)
Parse integer: O(d)
Extract substring: O(length of string)


Total: We process each character once = O(n)

Space Complexity: O(n)

We create a new list and strings to store the decoded result
The output size equals the original input size
Additional space: O(n) for the result list
 */
public class EncodeDecodeStrings {
	
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for(String str : strs) {
			sb.append(str.length());
			sb.append('#');
			sb.append(str);
			
		}
		return sb.toString();
		
	}
	
	public List<String> decode(String str) {
		int i = 0;
		List<String> decodedStrs = new ArrayList<>();
		while(i < str.length()) {
			int delimiterPos = str.indexOf('#', i);
			System.out.println("delimiterPos "+ delimiterPos);
			int length = Integer.parseInt(str.substring(i, delimiterPos));
			int startPos = delimiterPos + 1;
			String subStr = str.substring(startPos, startPos+length);
			decodedStrs.add(subStr);
			i = startPos+length;
			
		}
		return decodedStrs;
		
	}
	
	 public static void main(String[] args) {
	        EncodeDecodeStrings codec = new EncodeDecodeStrings();
	        
	        // Test case 1: Basic strings
	        System.out.println("=== Test Case 1 ===");
	        List<String> test1 = Arrays.asList("hello", "world");
	        System.out.println("Original: " + test1);
	        String encoded1 = codec.encode(test1);
	        System.out.println("Encoded: " + encoded1);
	        List<String> decoded1 = codec.decode(encoded1);
	        System.out.println("Decoded: " + decoded1);
	        System.out.println("Match: " + test1.equals(decoded1));
	        System.out.println();
	        
	        // Test case 2: Empty string
	        System.out.println("=== Test Case 2 ===");
	        List<String> test2 = Arrays.asList("");
	        System.out.println("Original: " + test2);
	        String encoded2 = codec.encode(test2);
	        System.out.println("Encoded: " + encoded2);
	        List<String> decoded2 = codec.decode(encoded2);
	        System.out.println("Decoded: " + decoded2);
	        System.out.println("Match: " + test2.equals(decoded2));
	        System.out.println();
	        
	        // Test case 3: Single characters
	        System.out.println("=== Test Case 3 ===");
	        List<String> test3 = Arrays.asList("a", "b", "c");
	        System.out.println("Original: " + test3);
	        String encoded3 = codec.encode(test3);
	        System.out.println("Encoded: " + encoded3);
	        List<String> decoded3 = codec.decode(encoded3);
	        System.out.println("Decoded: " + decoded3);
	        System.out.println("Match: " + test3.equals(decoded3));
	        System.out.println();
	        
	        // Test case 4: Strings with special characters and delimiters
	        System.out.println("=== Test Case 4 ===");
	        List<String> test4 = Arrays.asList("hello#world", "test:123", "special@chars!");
	        System.out.println("Original: " + test4);
	        String encoded4 = codec.encode(test4);
	        System.out.println("Encoded: " + encoded4);
	        List<String> decoded4 = codec.decode(encoded4);
	        System.out.println("Decoded: " + decoded4);
	        System.out.println("Match: " + test4.equals(decoded4));
	        System.out.println();
	        
	        // Test case 5: Mix of empty and non-empty strings
	        System.out.println("=== Test Case 5 ===");
	        List<String> test5 = Arrays.asList("", "abc", "", "def");
	        System.out.println("Original: " + test5);
	        String encoded5 = codec.encode(test5);
	        System.out.println("Encoded: " + encoded5);
	        List<String> decoded5 = codec.decode(encoded5);
	        System.out.println("Decoded: " + decoded5);
	        System.out.println("Match: " + test5.equals(decoded5));
	        System.out.println();
	        
	        // Test case 6: Strings with numbers
	        System.out.println("=== Test Case 6 ===");
	        List<String> test6 = Arrays.asList("123", "456#789", "0");
	        System.out.println("Original: " + test6);
	        String encoded6 = codec.encode(test6);
	        System.out.println("Encoded: " + encoded6);
	        List<String> decoded6 = codec.decode(encoded6);
	        System.out.println("Decoded: " + decoded6);
	        System.out.println("Match: " + test6.equals(decoded6));
	    }
	

}
