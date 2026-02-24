package neetcode150.mathandgeometry;
/*
 * You are given two strings num1 and num2 that represent non-negative integers.

Return the product of num1 and num2 in the form of a string.

Assume that neither num1 nor num2 contain any leading zero, unless they are the number 0 itself.

Note: You can not use any built-in library to convert the inputs directly into integers.

Example 1:

Input: num1 = "3", num2 = "4"

Output: "12"
Example 2:

Input: num1 = "111", num2 = "222"

Output: "24642"
ComplexityReason
TimeO(m × n)Every digit of num1 multiplied with every digit of num2
SpaceO(m + n)Result array of exactly m+n size

when you multiply 2 digits , maximum no of elemnts will be m+n
so you create a result array with m+n size.
 */
public class MultiplyStrings {
	
	public static String multiply(String num1, String num2) {
		
		int m = num1.length();
		int n = num2.length();
		
		int[] pos = new int[m+n];
		for(int i = m - 1; i >= 0; i--) {
			int digit1 = num1.charAt(i) - '0';
			for(int j = n-1; j>=0; j--) {
				int digit2 = num2.charAt(j) - '0';
				int product = digit1 * digit2;
				int p1 = i + j;
				int p2 = i+j+1;
				
				int sum = product + pos[p2];
				pos[p2] = sum % 10;
				pos[p1] += sum / 10; 
				
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int digit : pos) {
			if(sb.length() == 0 && digit == 0)
				continue;
			sb.append(digit);
			
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
	   // ──────────────────────────────────────────────────────────
    public static void traceMultiply(String num1, String num2) {
        System.out.println("─────────────────────────────────────────────────");
        System.out.println("  TRACE: \"" + num1 + "\" × \"" + num2 + "\"");
        System.out.println("─────────────────────────────────────────────────");

        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        System.out.println("  Result array size = " + m + " + " + n + " = " + (m + n));
        System.out.println("  Initial pos[] = " + arrayToString(pos));
        System.out.println();

        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int digit2  = num2.charAt(j) - '0';
                int product = digit1 * digit2;
                int p1      = i + j;
                int p2      = i + j + 1;
                int sum     = product + pos[p2];

                System.out.printf("  i=%d(digit=%d)  j=%d(digit=%d)  "
                        + "product=%d×%d=%d  p1=%d  p2=%d  "
                        + "sum=%d+%d=%d  → pos[%d]=%d  pos[%d]+=%d%n",
                        i, digit1, j, digit2,
                        digit1, digit2, product,
                        p1, p2,
                        product, pos[p2], sum,
                        p2, sum % 10,
                        p1, sum / 10);

                pos[p2]  = sum % 10;
                pos[p1] += sum / 10;

                System.out.println("  pos[] after this step = " + arrayToString(pos));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int d : pos) {
            if (sb.length() == 0 && d == 0) continue;
            sb.append(d);
        }
        String result = sb.length() == 0 ? "0" : sb.toString();
        System.out.println();
        System.out.println("  Final pos[] = " + arrayToString(pos));
        System.out.println("  Result      = \"" + result + "\"");
        System.out.println("─────────────────────────────────────────────────");
        System.out.println();
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ──────────────────────────────────────────────────────────
    //  MAIN METHOD
    // ──────────────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("         MULTIPLY STRINGS — TEST CASES           ");
        System.out.println("=================================================\n");

        // ── Test 1: Basic single digit ────────────────────────
        System.out.println("▶ Test 1: \"3\" × \"4\"");
        System.out.println("  Expected : \"12\"");
        System.out.println("  Got      : \"" + multiply("3", "4") + "\"");
        System.out.println();

        // ── Test 2: From LeetCode example ─────────────────────
        System.out.println("▶ Test 2: \"111\" × \"222\"");
        System.out.println("  Expected : \"24642\"");
        System.out.println("  Got      : \"" + multiply("111", "222") + "\"");
        System.out.println();

        // ── Test 3: Multiply by zero ───────────────────────────
        System.out.println("▶ Test 3: \"0\" × \"999\"");
        System.out.println("  Expected : \"0\"");
        System.out.println("  Got      : \"" + multiply("0", "999") + "\"");
        System.out.println();

        // ── Test 4: Large numbers ──────────────────────────────
        System.out.println("▶ Test 4: \"123\" × \"456\"");
        System.out.println("  Expected : \"56088\"");
        System.out.println("  Got      : \"" + multiply("123", "456") + "\"");
        System.out.println();

        // ── Test 5: Same large repeated digits ─────────────────
        System.out.println("▶ Test 5: \"999\" × \"999\"");
        System.out.println("  Expected : \"998001\"");
        System.out.println("  Got      : \"" + multiply("999", "999") + "\"");
        System.out.println();

        // ── Test 6: Very large numbers ─────────────────────────
        System.out.println("▶ Test 6: \"9133\" × \"0\"");
        System.out.println("  Expected : \"0\"");
        System.out.println("  Got      : \"" + multiply("9133", "0") + "\"");
        System.out.println();

        // ── Test 7: Asymmetric lengths ─────────────────────────
        System.out.println("▶ Test 7: \"99\" × \"999\"");
        System.out.println("  Expected : \"98901\"");
        System.out.println("  Got      : \"" + multiply("99", "999") + "\"");
        System.out.println();

        System.out.println("=================================================");
        System.out.println("   DETAILED ITERATION TRACE FOR \"23\" × \"45\"   ");
        System.out.println("=================================================\n");
        traceMultiply("23", "45");

        System.out.println("=================================================");
        System.out.println("  DETAILED ITERATION TRACE FOR \"3\" × \"4\"      ");
        System.out.println("=================================================\n");
        traceMultiply("3", "4");

        System.out.println("=================================================");
        System.out.println("  COMPLEXITY SUMMARY                             ");
        System.out.println("=================================================");
        System.out.println("  Time  : O(m × n)");
        System.out.println("          m = length of num1");
        System.out.println("          n = length of num2");
        System.out.println("          Every digit of num1 is multiplied with");
        System.out.println("          every digit of num2 — exactly m×n ops.");
        System.out.println();
        System.out.println("  Space : O(m + n)");
        System.out.println("          The result array has size m+n.");
        System.out.println("          No other significant extra space used.");
        System.out.println("=================================================");
    }
}
