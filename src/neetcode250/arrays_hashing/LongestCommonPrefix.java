package neetcode250.arrays_hashing;
/*
 * You are given an array of strings strs. Return the longest common prefix of all the strings.

If there is no longest common prefix, return an empty string "".

Example 1:

Input: strs = ["bat","bag","bank","band"]

Output: "ba"
Example 2:

Input: strs = ["dance","dag","danger","damage"]

Output: "da"
Example 3:

Input: strs = ["neet","feet"]

Output: ""
Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] is made up of lowercase English letters if it is non-empty.

Time  Complexity : O(n × m)                 ║
║   - Outer loop runs at most m times          ║
║   - Inner loop runs n-1 times per char       ║
║   - Total comparisons ≤ n × m                ║
║                                               ║
║  Space Complexity : O(1)  (+ O(m) for output)║
║   - We use only a few integer variables       ║
║   - No extra data structures are created     ║
║   - substring() call is O(m) but that is     ║
║     the output itself, not auxiliary space   ║
╚════════════════════════════════════════════hh

 */
public class LongestCommonPrefix {
	
	public static String longestCommonPrefix(String[] strs) {
		if(strs.length == 0 || strs == null)
			return "";
		String reference = strs[0];
		for(int i = 0; i < reference.length(); i++) {
			char c = reference.charAt(i);
			for(int j = 1; j < strs.length; j++) {
				if(i >= strs[j].length() || strs[j].charAt(i) != c) {
					return reference.substring(0, i);
				}
			}
		}
		return reference;
	}
	 public static void main(String[] args) {

	        System.out.println("╔══════════════════════════════════════════════╗");
	        System.out.println("║      LONGEST COMMON PREFIX — Test Cases      ║");
	        System.out.println("╚══════════════════════════════════════════════╝\n");

	        // ── TEST 1: Classic example ──────────────────────────────────
	        String[] test1 = {"flower", "flow", "flight"};
	        System.out.println("Test 1 Input : [\"flower\", \"flow\", \"flight\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test1) + "\"");
	        System.out.println("Explanation  : 'f' matches all ✓ | 'l' matches all ✓ | 'o' vs 'o' vs 'i' ✗ → stop at index 2");
	        System.out.println("Expected     : \"fl\"\n");

	        // ── TEST 2: No common prefix ─────────────────────────────────
	        String[] test2 = {"dog", "racecar", "car"};
	        System.out.println("Test 2 Input : [\"dog\", \"racecar\", \"car\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test2) + "\"");
	        System.out.println("Explanation  : 'd' vs 'r' ✗ → stop at index 0 immediately");
	        System.out.println("Expected     : \"\"\n");

	        // ── TEST 3: All words identical ──────────────────────────────
	        String[] test3 = {"apple", "apple", "apple"};
	        System.out.println("Test 3 Input : [\"apple\", \"apple\", \"apple\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test3) + "\"");
	        System.out.println("Explanation  : Every character matches → reference word is the prefix");
	        System.out.println("Expected     : \"apple\"\n");

	        // ── TEST 4: One word only ────────────────────────────────────
	        String[] test4 = {"alone"};
	        System.out.println("Test 4 Input : [\"alone\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test4) + "\"");
	        System.out.println("Explanation  : Only one word → it is its own prefix");
	        System.out.println("Expected     : \"alone\"\n");

	        // ── TEST 5: Empty string in array ────────────────────────────
	        String[] test5 = {"prefix", "pre", ""};
	        System.out.println("Test 5 Input : [\"prefix\", \"pre\", \"\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test5) + "\"");
	        System.out.println("Explanation  : Empty string has length 0, so i >= length at i=0 → stop immediately");
	        System.out.println("Expected     : \"\"\n");

	        // ── TEST 6: Long shared prefix ──────────────────────────────
	        String[] test6 = {"interview", "internal", "internet", "interlude"};
	        System.out.println("Test 6 Input : [\"interview\", \"internal\", \"internet\", \"interlude\"]");
	        System.out.println("Output       : \"" + longestCommonPrefix(test6) + "\"");
	        System.out.println("Explanation  : i-n-t-e-r all match → 'v' vs 'n' ✗ at index 5");
	        System.out.println("Expected     : \"inter\"\n");

	        // ── TEST 7: Null / empty array ───────────────────────────────
	        String[] test7 = {};
	        System.out.println("Test 7 Input : []");
	        System.out.println("Output       : \"" + longestCommonPrefix(test7) + "\"");
	        System.out.println("Explanation  : Empty array → guard clause returns \"\"");
	        System.out.println("Expected     : \"\"\n");

	        // ── COMPLEXITY SUMMARY ───────────────────────────────────────
	        System.out.println("╔══════════════════════════════════════════════╗");
	        System.out.println("║           COMPLEXITY ANALYSIS                ║");
	        System.out.println("╠══════════════════════════════════════════════╣");
	        System.out.println("║  n = number of words in the array            ║");
	        System.out.println("║  m = length of the shortest word             ║");
	        System.out.println("║                                               ║");
	        System.out.println("║  Time  Complexity : O(n × m)                 ║");
	        System.out.println("║   - Outer loop runs at most m times          ║");
	        System.out.println("║   - Inner loop runs n-1 times per char       ║");
	        System.out.println("║   - Total comparisons ≤ n × m                ║");
	        System.out.println("║                                               ║");
	        System.out.println("║  Space Complexity : O(1)  (+ O(m) for output)║");
	        System.out.println("║   - We use only a few integer variables       ║");
	        System.out.println("║   - No extra data structures are created     ║");
	        System.out.println("║   - substring() call is O(m) but that is     ║");
	        System.out.println("║     the output itself, not auxiliary space   ║");
	        System.out.println("╚══════════════════════════════════════════════╝");
	    }
}
