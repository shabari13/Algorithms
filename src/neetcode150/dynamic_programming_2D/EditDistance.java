package neetcode150.dynamic_programming_2D;
/*
 * You are given two strings word1 and word2, each consisting of lowercase English letters.

You are allowed to perform three operations on word1 an unlimited number of times:

Insert a character at any position
Delete a character at any position
Replace a character at any position
Return the minimum number of operations to make word1 equal word2.

Example 1:

Input: word1 = "monkeys", word2 = "money"

Output: 2
Explanation:
monkeys -> monkey (remove s)
monkey -> money (remove k)

Example 2:

Input: word1 = "neatcdee", word2 = "neetcode"

Output: 3
Explanation:
neatcdee -> neetcdee (replace a with e)
neetcdee -> neetcde (remove last e)
neetcde -> neetcode (insert o)

Constraints:

0 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
## 💡 Idea Behind the Solution

💡 Simple Idea (Normal Explanation)

We use Dynamic Programming.

We create a 2D table dp[i][j] where:

i = first i characters of word1

j = first j characters of word2

dp[i][j] = minimum operations needed to convert first i characters of word1 into first j characters of word2

We fill this table step by step using previously solved smaller problems.

If characters match → no new cost.
If they don’t match → take minimum of:

Insert

Delete

Replace

🧸 Explain Like You're 5 Years Old

Imagine you have two words made of toy letters.

You want to change word A into word B.

You are allowed to:

Add a toy letter

Remove a toy letter

Change one toy letter into another

We make a big box (table) where:

Rows = letters of first word
Columns = letters of second word

Each small box tells us:
“How many tiny changes do we need?”

We fill the boxes one by one using answers from nearby boxes.

At the end, the bottom-right box gives the final answer 🎉

🧠 Step-by-Step Detailed Example

Let’s take:

word1 = "horse"
word2 = "ros"

Lengths:

m = 5
n = 3

We create a table of size (m+1) x (n+1) → 6 x 4

🔹 Step 1: Initialize Base Cases

If one word is empty:

dp[i][0] = i   (delete all characters)
dp[0][j] = j   (insert all characters)

So initial table:

	""	r	o	s
""	0	1	2	3
h	1			
o	2			
r	3			
s	4			
e	5			
🔹 Step 2: Fill Table
i = 1 (h), j = 1 (r)

Characters: h ≠ r

Options:

Insert → dp[1][0] = 1

Delete → dp[0][1] = 1

Replace → dp[0][0] = 0

Take minimum = 0
Add 1 → 1

So dp[1][1] = 1

i = 1 (h), j = 2 (o)

h ≠ o

Insert → dp[1][1] = 1
Delete → dp[0][2] = 2
Replace → dp[0][1] = 1

Minimum = 1
Add 1 → 2

dp[1][2] = 2

Continue this process…

Final table becomes:

	""	r	o	s
""	0	1	2	3
h	1	1	2	3
o	2	2	1	2
r	3	2	2	2
s	4	3	3	2
e	5	4	4	3

Final Answer:

dp[5][3] = 3

So minimum operations = 3

The Edit Distance (Levenshtein Distance) problem asks: **what is the minimum number of single-character operations (insert, delete, replace) needed to transform `word1` into `word2`?** We solve it using **Dynamic Programming**. We build a 2D table `dp[i][j]` that stores the answer to the sub-problem: *"how many operations does it take to convert the first `i` characters of `word1` into the first `j` characters of `word2`?"* By filling this table bottom-up using previously computed results, we avoid redundant computation and arrive at the final answer in `dp[m][n]` efficiently.

---

## 🧒 Explain Like I'm 5

Imagine you have the word **"CAT"** and you want to turn it into **"DOG"**. You can do three things: **add** a letter, **remove** a letter, or **swap** a letter for another. We want to do this in as **few steps as possible**.

To figure out the best way, we draw a **grid** — like a game board. Each box in the grid asks: *"If I only look at the first few letters of each word, what's the cheapest way to match them?"* We fill the grid box by box. If the current letters match — great, no work needed, copy the answer from the diagonal! If they don't match, we look at our three choices (add/remove/swap), pick the one that was **cheapest so far**, and add 1 for the current operation. The bottom-right corner of the grid is our final answer!

---

## 🔍 Step-by-Step Walkthrough: `"horse"` → `"ros"`

**The DP table (rows = horse+"", cols = ros+""):**
```
       ""  r  o  s
  ""  [ 0  1  2  3 ]
   h  [ 1  1  2  3 ]
   o  [ 2  2  1  2 ]
   r  [ 3  2  2  2 ]
   s  [ 4  3  3  2 ]
   e  [ 5  4  4  3 ]
Base cases:

dp[i][0] = i → to convert first i chars of "horse" to "", delete i times.
dp[0][j] = j → to convert "" to first j chars of "ros", insert j times.

Filling the table cell by cell:
i,jword1[i-1]word2[j-1]Match?Calculationdp[i][j]1,1hr❌1 + min(dp[1][0]=1, dp[0][1]=1, dp[0][0]=0) = 1+011,2ho❌1 + min(dp[1][1]=1, dp[0][2]=2, dp[0][1]=1) = 1+121,3hs❌1 + min(dp[1][2]=2, dp[0][3]=3, dp[0][2]=2) = 1+232,1or❌1 + min(dp[2][0]=2, dp[1][1]=1, dp[1][0]=1) = 1+122,2oo✅dp[1][1] = 1 (no cost!)12,3os❌1 + min(dp[2][2]=1, dp[1][3]=3, dp[1][2]=2) = 1+123,1rr✅dp[2][0] = 223,2ro❌1 + min(dp[3][1]=2, dp[2][2]=1, dp[2][1]=2) = 1+123,3rs❌1 + min(dp[3][2]=2, dp[2][3]=2, dp[2][2]=1) = 1+124,3ss✅dp[3][2] = 225,3es❌1 + min(dp[5][2]=4, dp[4][3]=2, dp[4][2]=3) = 1+23
Answer: dp[5][3] = 3 → The 3 operations are: replace h→r, delete r, delete e.

⏱️ Time & Space Complexity
ComplexityReasonTimeO(m × n)We fill every cell of the m+1 by n+1 table exactly onceSpaceO(m × n)We store the full DP tableOptimized SpaceO(min(m,n))We only ever need the current and previous row, so we can use two 1D arrays
Where m = len(word1) and n = len(word2).
 */
public class EditDistance {
	
	public int editDistance(String word1, String word2) {
		
		int m = word1.length();
		int n = word2.length();
		
		int[][] dp = new int[m+1][n+1];
		for(int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}
		for(int j = 0; j <= n; j++) {
			dp[0][j] = j;
		}
		
		for(int i=1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(word1.charAt(i -1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
				}
			}
			
		}
		
		return dp[m][n];
		
	}
	
	  public static void printDP(String word1, String word2, int[][] dp) {
	        int m = word1.length(), n = word2.length();
	        System.out.print("     \"\"");
	        for (char c : word2.toCharArray()) System.out.printf("  %c", c);
	        System.out.println();
	        for (int i = 0; i <= m; i++) {
	            System.out.printf("  %c  ", i == 0 ? '"' : word1.charAt(i - 1));
	            for (int j = 0; j <= n; j++) System.out.printf("%2d ", dp[i][j]);
	            System.out.println();
	        }
	    }

	    public static void solve(String word1, String word2) {
	        int m = word1.length(), n = word2.length();
	        int[][] dp = new int[m + 1][n + 1];
	        for (int i = 0; i <= m; i++) dp[i][0] = i;
	        for (int j = 0; j <= n; j++) dp[0][j] = j;
	        for (int i = 1; i <= m; i++)
	            for (int j = 1; j <= n; j++)
	                if (word1.charAt(i - 1) == word2.charAt(j - 1))
	                    dp[i][j] = dp[i - 1][j - 1];
	                else
	                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));

	        System.out.println("==================================================");
	        System.out.printf("word1 = \"%s\"  →  word2 = \"%s\"%n", word1, word2);
	        System.out.println("DP Table:");
	        printDP(word1, word2, dp);
	        System.out.printf("✅ Edit Distance = %d%n%n", dp[m][n]);
	    }

	    public static void main(String[] args) {
	        // Sample inputs
	        solve("horse",   "ros");       // classic example → 3
	        solve("intention", "execution"); // → 5
	        solve("",        "abc");       // insert 3 → 3
	        solve("abc",     "");          // delete 3 → 3
	        solve("abc",     "abc");       // identical → 0
	        solve("kitten",  "sitting");   // → 3
	        solve("sunday",  "saturday");  // → 3
	    }

}
