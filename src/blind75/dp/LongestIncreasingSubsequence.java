package blind75.dp;

/*
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

Time Complexity: O(n²)

We have two nested loops: outer loop runs n times, inner loop runs up to n times
For each element, we check all previous elements
n × n = n²

Space Complexity: O(n)

We use one extra array dp of size n to store the length of LIS ending at each position
No other significant space is used

# Explanation Like You're 5 Years Old 🎈

Imagine you have a line of toy blocks with numbers on them: **10, 9, 2, 5, 3, 7, 101, 18**

You want to pick some blocks (keeping them in order) so that each block you pick has a **bigger number** than the last one you picked. What's the most blocks you can pick?

### The Magic Helper Array (dp)

We create a special helper array called `dp`. Think of it as a notebook where we write down "How many blocks can I pick up to this point?"

Initially, every block can be picked by itself, so we write **1** under each block.
```
Blocks:  10   9   2   5   3   7  101  18
dp:      1    1   1   1   1   1   1    1
```

### Let's Walk Through Step by Step!

**Position 0 (number 10):**
- This is the first block, so we can only pick 1 block.
- dp[0] = 1

**Position 1 (number 9):**
- Look at all blocks before: just 10
- Is 9 > 10? No! So we can't add 9 after 10
- dp[1] = 1 (just pick 9 by itself)
```
Blocks:  10   9
dp:      1    1
```

**Position 2 (number 2):**
- Look at blocks before: 10, 9
- Is 2 > 10? No!
- Is 2 > 9? No!
- dp[2] = 1 (just pick 2 by itself)
```
Blocks:  10   9   2
dp:      1    1   1
```

**Position 3 (number 5):**
- Look at blocks before: 10, 9, 2
- Is 5 > 10? No!
- Is 5 > 9? No!
- Is 5 > 2? **Yes!** So we can pick 2, then 5. That's 2 blocks! (dp[2] + 1 = 1 + 1 = 2)
- dp[3] = 2
```
Blocks:  10   9   2   5
dp:      1    1   1   2
```

**Position 4 (number 3):**
- Look at blocks before: 10, 9, 2, 5
- Is 3 > 10? No!
- Is 3 > 9? No!
- Is 3 > 2? **Yes!** We can pick 2, then 3. That's 2 blocks! (dp[2] + 1 = 2)
- Is 3 > 5? No!
- dp[4] = 2
```
Blocks:  10   9   2   5   3
dp:      1    1   1   2   2
```

**Position 5 (number 7):**
- Look at blocks before: 10, 9, 2, 5, 3
- Is 7 > 10? No!
- Is 7 > 9? No!
- Is 7 > 2? **Yes!** We can extend: dp[2] + 1 = 1 + 1 = 2
- Is 7 > 5? **Yes!** We can extend: dp[3] + 1 = 2 + 1 = 3 ✨ (Best so far!)
- Is 7 > 3? **Yes!** We can extend: dp[4] + 1 = 2 + 1 = 3
- dp[5] = 3 (One possible sequence: 2, 5, 7)
```
Blocks:  10   9   2   5   3   7
dp:      1    1   1   2   2   3
```

**Position 6 (number 101):**
- Look at all previous blocks
- Is 101 > 10? **Yes!** dp[0] + 1 = 2
- Is 101 > 9? **Yes!** dp[1] + 1 = 2
- Is 101 > 2? **Yes!** dp[2] + 1 = 2
- Is 101 > 5? **Yes!** dp[3] + 1 = 3
- Is 101 > 3? **Yes!** dp[4] + 1 = 3
- Is 101 > 7? **Yes!** dp[5] + 1 = 3 + 1 = **4** 🎉
- dp[6] = 4 (Sequence: 2, 5, 7, 101)
```
Blocks:  10   9   2   5   3   7  101
dp:      1    1   1   2   2   3   4
```

**Position 7 (number 18):**
- Is 18 > 10? **Yes!** dp[0] + 1 = 2
- Is 18 > 9? **Yes!** dp[1] + 1 = 2
- Is 18 > 2? **Yes!** dp[2] + 1 = 2
- Is 18 > 5? **Yes!** dp[3] + 1 = 3
- Is 18 > 3? **Yes!** dp[4] + 1 = 3
- Is 18 > 7? **Yes!** dp[5] + 1 = 4 ⭐
- Is 18 > 101? No!
- dp[7] = 4 (Sequence: 2, 5, 7, 18)
```
Blocks:  10   9   2   5   3   7  101  18
dp:      1    1   1   2   2   3   4    4
Final Answer: 4
The longest increasing subsequence has length 4!
One example: 2 → 5 → 7 → 101 or 2 → 5 → 7 → 18
Time and Space Complexity
Time Complexity: O(n²)

We have two nested loops: outer loop runs n times, inner loop runs up to n times
For each element, we check all previous elements
n × n = n²

Space Complexity: O(n)

We use one extra array dp of size n to store the length of LIS ending at each position
No other significant space is used
Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?12:37 PMUntitledText public class LongestIncreasingSubsequence {
    // O(n^2) Dynamic Programming Solution
    public int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
 O(n log n) Solution - Explained Like You're 5! 🚀
Instead of checking every previous block, we'll use a smart trick with a helper array called tails.
The Big Idea 🎯
Imagine you're building towers of blocks. The tails array keeps track of the smallest last block for each tower height.

tails[0] = smallest ending block for a tower of height 1
tails[1] = smallest ending block for a tower of height 2
tails[2] = smallest ending block for a tower of height 3
And so on...

Why keep the smallest? Because a smaller ending block gives us more chances to add more blocks later!
Walking Through with Our Example: [10, 9, 2, 5, 3, 7, 101, 18]
Step 1: Process 10

tails is empty, so add 10
tails = [10]
Size = 1

Tower of height 1: ends with 10
Step 2: Process 9

Where does 9 fit? Binary search in tails
9 < 10, so we can replace 10 with 9 (better to end with 9!)
tails = [9]
Size = 1

Tower of height 1: ends with 9 (better than 10!)
Step 3: Process 2

Where does 2 fit? Binary search finds position 0
2 < 9, so replace 9 with 2
tails = [2]
Size = 1

Tower of height 1: ends with 2 (even better!)
Step 4: Process 5

Where does 5 fit? Binary search finds position 1
5 > 2, so we can extend! Add 5 at position 1
tails = [2, 5]
Size = 2

Tower of height 1: ends with 2
Tower of height 2: ends with 5 (we built: 2 → 5)
Step 5: Process 3

Where does 3 fit? Binary search finds position 1
3 > 2 but we already have 5 at position 1
Replace 5 with 3 (smaller is better!)
tails = [2, 3]
Size = 2

Tower of height 1: ends with 2
Tower of height 2: ends with 3 (better than 5!)
Step 6: Process 7

Where does 7 fit? Binary search finds position 2
7 > 3, so we can extend to height 3!
tails = [2, 3, 7]
Size = 3

Tower of height 1: ends with 2
Tower of height 2: ends with 3
Tower of height 3: ends with 7 (we built: 2 → 3 → 7)
Step 7: Process 101

Where does 101 fit? Binary search finds position 3
101 > 7, so extend to height 4!
tails = [2, 3, 7, 101]
Size = 4

Tower of height 1: ends with 2
Tower of height 2: ends with 3
Tower of height 3: ends with 7
Tower of height 4: ends with 101 (we built: 2 → 3 → 7 → 101)
Step 8: Process 18

Where does 18 fit? Binary search finds position 3
18 > 7 but < 101, replace 101 with 18
tails = [2, 3, 7, 18]
Size = 4

Tower of height 1: ends with 2
Tower of height 2: ends with 3
Tower of height 3: ends with 7
Tower of height 4: ends with 18 (better than 101!)
Final Answer: 4 🎉
The longest increasing subsequence has length 4!
Why Binary Search? 🔍
Instead of checking every element (which takes O(n) time), binary search finds where to place the new number in O(log n) time. It's like playing the guessing game where you guess the middle and eliminate half each time!
Time and Space Complexity
Time Complexity: O(n log n)

We loop through n elements: O(n)
For each element, we do binary search on the tails array: O(log n)
Total: O(n × log n) = O(n log n) 🚀

Space Complexity: O(n)

We use the tails array which can be at most size n
Much better than the O(n²) time of the previous solution!
Claude is AI and can make mistakes. Please double-check responses. Sonnet 4.5
 */
public class LongestIncreasingSubsequence {
	
	public int longestIncresingSubsequence(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 1;
		for(int i =1; i<nums.length; i++) {
			dp[i] = 1;
		}
		int maxLength = 1;
		
		for(int i = 1; i < nums.length; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			maxLength = Math.max(maxLength, dp[i]);
		}
		return maxLength;
		
	}
	
	 public static void main(String[] args) {
	        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
	        
	        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
	        
	        int result = solution.longestIncresingSubsequence(nums);
	        System.out.println("Input array: ");
	        for (int num : nums) {
	            System.out.print(num + " ");
	        }
	        System.out.println("\nLength of Longest Increasing Subsequence: " + result);
	    }

}
