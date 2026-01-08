package blind75.dp;
/*
 * 
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 Time and Space Complexity
Time Complexity: O(m × n)

m = length of text1
n = length of text2
We visit each cell in the grid exactly once
For "abcde" and "ace": 5 × 3 = 15 operations

Space Complexity: O(m × n)

We create a 2D array of size (m+1) × (n+1)
For "abcde" and "ace": 6 × 4 = 24 cells
Imagine you have two chains of colorful beads:
- Chain 1: **A-B-C-D-E**
- Chain 2: **A-C-E**

You want to find how many beads appear in **the same order** in both chains (but they don't have to be next to each other).

### The Magic Grid Approach 📊

We create a special grid (like a treasure map) to remember our answers. Think of it as a game board where we compare each bead from Chain 1 with each bead from Chain 2.

### Step-by-Step Detailed Walkthrough

Let's use **text1 = "abcde"** and **text2 = "ace"**

**Step 1: Create the Grid**
We make a grid with extra rows and columns. The first row and column are filled with zeros (like empty boxes).
```
      ""  a  c  e
  ""   0  0  0  0
  a    0  ?  ?  ?
  b    0  ?  ?  ?
  c    0  ?  ?  ?
  d    0  ?  ?  ?
  e    0  ?  ?  ?
```

**Step 2: Fill the Grid - The Magic Rules**

We have two simple rules:
1. **If letters match**: Add 1 to the number diagonally above-left
2. **If letters don't match**: Take the bigger number from either left or above

### Detailed Iteration Process

**Iteration 1: i=1, j=1** (comparing 'a' from text1 with 'a' from text2)
- text1[0] = 'a', text2[0] = 'a'
- They MATCH! 🎉
- Look diagonally: dp[0][0] = 0
- Add 1: dp[1][1] = 0 + 1 = **1**
```
      ""  a  c  e
  ""   0  0  0  0
  a    0  1  ?  ?
```

**Iteration 2: i=1, j=2** (comparing 'a' from text1 with 'c' from text2)
- text1[0] = 'a', text2[1] = 'c'
- They DON'T match ❌
- Look left: dp[1][1] = 1
- Look above: dp[0][2] = 0
- Take max: dp[1][2] = **1**
```
      ""  a  c  e
  ""   0  0  0  0
  a    0  1  1  ?
```

**Iteration 3: i=1, j=3** (comparing 'a' from text1 with 'e' from text2)
- text1[0] = 'a', text2[2] = 'e'
- They DON'T match ❌
- Look left: dp[1][2] = 1
- Look above: dp[0][3] = 0
- Take max: dp[1][3] = **1**
```
      ""  a  c  e
  ""   0  0  0  0
  a    0  1  1  1
```

**Iteration 4: i=2, j=1** (comparing 'b' from text1 with 'a' from text2)
- text1[1] = 'b', text2[0] = 'a'
- They DON'T match ❌
- Look left: dp[2][0] = 0
- Look above: dp[1][1] = 1
- Take max: dp[2][1] = **1**

**Iteration 5: i=2, j=2** (comparing 'b' from text1 with 'c' from text2)
- text1[1] = 'b', text2[1] = 'c'
- They DON'T match ❌
- Look left: dp[2][1] = 1
- Look above: dp[1][2] = 1
- Take max: dp[2][2] = **1**

**Iteration 6: i=2, j=3** (comparing 'b' from text1 with 'e' from text2)
- text1[1] = 'b', text2[2] = 'e'
- They DON'T match ❌
- Look left: dp[2][2] = 1
- Look above: dp[1][3] = 1
- Take max: dp[2][3] = **1**
```
      ""  a  c  e
  ""   0  0  0  0
  a    0  1  1  1
  b    0  1  1  1
The answer is in the bottom-right corner: 3 (because "ace" appears in "abcde" in order)
Key Signals That Scream "2D DP Grid":
1. Two Sequences/Strings Being Compared
When you see problems involving:

"Two strings"
"Two arrays"
"Compare sequences"

Your brain should light up: "Maybe I need a 2D grid!"
2. Keywords That Hint at DP:

"Longest"
"Shortest"
"Maximum"
"Minimum"
"Count all ways"
"Optimal"
 */
public class LongestCommonSubsequnce {
	
	public int longestCommonSubsequence(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] dp = new int[m+1][n+1];
		
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n ; j++) {
				if(s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				
			}
		}
		
		return dp[m][n];
		
	}
	public static void main(String[] args) {
		LongestCommonSubsequnce solution = new LongestCommonSubsequnce();
        
        // Test Case 1
        String text1_1 = "abcde";
        String text2_1 = "ace";
        int result1 = solution.longestCommonSubsequence(text1_1, text2_1);
        System.out.println("Input: text1 = \"" + text1_1 + "\", text2 = \"" + text2_1 + "\"");
        System.out.println("Output: " + result1);
        System.out.println();
        
        // Test Case 2
        String text1_2 = "abc";
        String text2_2 = "abc";
        int result2 = solution.longestCommonSubsequence(text1_2, text2_2);
        System.out.println("Input: text1 = \"" + text1_2 + "\", text2 = \"" + text2_2 + "\"");
        System.out.println("Output: " + result2);
        System.out.println();
        
        // Test Case 3
        String text1_3 = "abc";
        String text2_3 = "def";
        int result3 = solution.longestCommonSubsequence(text1_3, text2_3);
        System.out.println("Input: text1 = \"" + text1_3 + "\", text2 = \"" + text2_3 + "\"");
        System.out.println("Output: " + result3);
        System.out.println();
        
        // Test Case 4
        String text1_4 = "horse";
        String text2_4 = "ros";
        int result4 = solution.longestCommonSubsequence(text1_4, text2_4);
        System.out.println("Input: text1 = \"" + text1_4 + "\", text2 = \"" + text2_4 + "\"");
        System.out.println("Output: " + result4);
    }
	

}
