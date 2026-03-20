package neetcode250.binary_search;
/*
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API int guess(int num), which returns three possible results:

0: your guess is equal to the number I picked (i.e. num == pick).
-1: Your guess is higher than the number I picked (i.e. num > pick).
1: Your guess is lower than the number I picked (i.e. num < pick).
Return the number that I picked.

Example 1:

Input: n = 5, pick = 3

Output: 3
Example 2:

Input: n = 15, pick = 10

Output: 10
Example 3:

Input: n = 1, pick = 1

Output: 1
Constraints:

1 <= pick <= n <= ((2^31)-1)

 */
public class GuessGame extends GuessGameChild{
	public int guessNumber(int n) {
		int low = 0;
		int high = n;
		while(low <= low) {
			
			 int mid = low + (high - low) / 2;  
			int result = guess(mid);
			if(result == 0) {
				return mid;
			} else if (result == -1) {                // Step 7: Guess was too HIGH
                high = mid - 1;                       //         Move to lower half
            } else {
            	low = mid + 1;
            }
		}
		return -1;
	}
	
	public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     Guess Number Higher or Lower          ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── Test Case 1: n=10, secret=6 ──
        runTest(10, 6);

        // ── Test Case 2: n=1, secret=1 (Edge: only one number) ──
        runTest(1, 1);

        // ── Test Case 3: n=100, secret=1 (Edge: very first number) ──
        runTest(100, 1);

        // ── Test Case 4: n=100, secret=100 (Edge: very last number) ──
        runTest(100, 100);

        // ── Test Case 5: n=2126753390, secret=1702766719 (Large input) ──
        runTest(2126753390, 1702766719);
    }


    // ─────────────────────────────────────────────
    // Helper to run and print each test case
    // ─────────────────────────────────────────────
    static void runTest(int n, int secret) {
    	GuessGame solver = new GuessGame();
        GuessGame.secretNumber = secret;

        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf ("│  Range: [1 ... %d]%n", n);
        System.out.printf ("│  Secret Number: %d%n", secret);
        System.out.println("└─────────────────────────────────────────┘");

        int answer = solver.guessNumber(n);
        System.out.printf("   ✅ Answer: %d%n%n", answer);
    }
	
}

