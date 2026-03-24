package neetcode250.dynamic_programming;
/*
 * The Big Idea (Plain English): Imagine you're a kid standing in the middle of a word and you stretch your arms out in both directions. As long as the letter on your left and right are the same, you keep stretching. When they don't match, you stop. You do this for every single position in the string, and you keep track of the longest stretch you ever managed. That's it! For even-length palindromes, imagine standing between two characters instead of on one.
Step-by-step explanation:

expandAroundCenter(s, left, right) — this is our "stretching arms" helper. We keep going outward as long as characters match. When we're done, right - left - 1 gives the length of the palindrome we found.
In longestPalindrome, we visit each index i and try two expansions: one centered on i (for odd-length like "racecar"), and one centered between i and i+1 (for even-length like "abba").
We always track the best start and end positions seen so far, and at the end we return the substring from start to end.
 Complexity
ValueWhyTimeO(n²)We visit each of the n centers, and each expansion can go at most n/2 steps outwardSpaceO(1)We only store two integers (start, end) plus a few loop variables — no extra arrays
 *
 */
public class LongestPalindromicSubstring {
	public  static String longestPalindrome(String s) {
		if(s.length() == 0) {
			return "";
		}
		int len = s.length();
		int start = 0;
		int end = 0;
		int maxLen = 0;
		for(int i = 0 ; i <len ; i++) {
			int oddLength = expandFromCenter(s, i, i);
			int evenLength = expandFromCenter(s, i, i+1);
			maxLen  = Math.max(oddLength, evenLength);
			if(maxLen > end - start + 1) {
				start = i - (maxLen - 1)/2;
				end = i+maxLen/2;
			}
		}
		return s.substring(start, end + 1);
	}
	 // Helper: expand outward from (left, right) while chars match
    // Returns length of the palindrome found
	public static int expandFromCenter(String s, int left, int right) {
		 // Keep moving arms outward while:
        //  • we haven't fallen off either end of the string
        //  • the characters at both arms are the same
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right) ) {
            left--;   // move left arm one step further left
            right++;  // move right arm one step further right
		}
		// When the loop exits, s[left] != s[right] (mismatch or boundary).
        // The valid palindrome is from (left+1) to (right-1) inclusive.
        // Length = (right-1) - (left+1) + 1 = right - left - 1
		return right - left - 1;
	}
	
	public static void traceIterations(String s) {
        System.out.println("\n─────────────────────────────────────────");
        System.out.println("Tracing: \"" + s + "\"");
        System.out.println("─────────────────────────────────────────");

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            System.out.printf("%nCenter index i=%d  char='%c'%n", i, s.charAt(i));

            // --- Odd expansion ---
            int l = i, r = i;
            System.out.printf("  [ODD]  Start expanding from (%d,%d) = ('%c','%c')%n",
                              l, r, s.charAt(l), s.charAt(r));
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                System.out.printf("         Match! '%c'==%c → window [%d..%d] \"%s\"%n",
                                  s.charAt(l), s.charAt(r), l, r, s.substring(l, r + 1));
                l--; r++;
            }
            int oddLen  = r - l - 1;
            System.out.printf("         Stopped. Odd palindrome length = %d%n", oddLen);

            // --- Even expansion ---
            l = i; r = i + 1;
            if (r < s.length()) {
                System.out.printf("  [EVEN] Start expanding from (%d,%d) = ('%c','%c')%n",
                                  l, r, s.charAt(l), s.charAt(r));
                while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                    System.out.printf("         Match! '%c'=='%c' → window [%d..%d] \"%s\"%n",
                                      s.charAt(l), s.charAt(r), l, r, s.substring(l, r + 1));
                    l--; r++;
                }
            }
            int evenLen = r - l - 1;
            System.out.printf("         Stopped. Even palindrome length = %d%n", evenLen);

            int maxLen = Math.max(oddLen, evenLen);
            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2;
                end   = i + maxLen / 2;
                System.out.printf("  ★ New best! \"%s\" (len=%d, start=%d, end=%d)%n",
                                  s.substring(start, end + 1), maxLen, start, end);
            } else {
                System.out.printf("  (no improvement, best stays \"%s\")%n",
                                  s.substring(start, end + 1));
            }
        }

        System.out.println("\n→ Result: \"" + s.substring(start, end + 1) + "\"");
    }

    // ─────────────────────────────────────────────────────────
    // Main: run multiple test cases
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  Longest Palindromic Substring Results   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Test cases: { input, expected }
        String[][] tests = {
            { "babad",     "bab or aba"      },
            { "cbbd",      "bb"              },
            { "a",         "a"               },
            { "racecar",   "racecar"         },
            { "abacaba",   "abacaba"         },
            { "abba",      "abba"            },
            { "aacabdkacaa","aca"            },
            { "aaaa",      "aaaa"            },
            { "",          "(empty)"         }
        };

        System.out.printf("%-20s %-20s %-20s%n", "Input", "Our Output", "Expected");
        System.out.println("─".repeat(62));

        for (String[] t : tests) {
            String input    = t[0];
            String expected = t[1];
            String output   = longestPalindrome(input);
            String status   = output.isEmpty() ? "(empty)" : output;
            System.out.printf("%-20s %-20s %-20s%n", "\"" + input + "\"", "\"" + status + "\"", expected);
        }

        // Show full step-by-step trace for two interesting inputs
        traceIterations("babad");
        traceIterations("abba");
    }
}
