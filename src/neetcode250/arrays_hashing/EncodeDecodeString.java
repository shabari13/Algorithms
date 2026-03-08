package neetcode250.arrays_hashing;

import java.util.ArrayList;
import java.util.List;

/*
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

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
 * OperationTimeSpaceEncodeO(N) — iterate through all characters of all strings onceO(N) — for the output encoded stringDecodeO(N) — two-pointer scan through the encoded string onceO(N) — for the output list of strings
Where N = total number of characters across all strings combined.
 */

public class EncodeDecodeString {
		public static String encode(List<String> strs) {
			StringBuilder sb = new StringBuilder();
			for(String str :strs) {
				sb.append(str.length()).append('#').append(str);
			}
			
			return sb.toString();
		}
		public static List<String> decode(String str){
			int i = 0;
			int n = str.length();
			List<String> result = new ArrayList<>();
			while(i < n) {
				int charIndex =str.indexOf('#', i);
				int length = Integer.parseInt(str.substring(i,charIndex));
				result.add(str.substring(charIndex+1, charIndex+1+length));
				i = charIndex+1+length;
			}
			return result;
			
		}
		public static void main(String[] args) {
			List<String> list = new ArrayList<>();
			list.add("hello");
			list.add("world");
			String encodedStr = encode(list);
			System.out.println(encodedStr);
			System.out.println(decode(encodedStr));
			
		}
}
