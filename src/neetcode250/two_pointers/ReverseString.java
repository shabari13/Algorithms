package neetcode250.two_pointers;
/*
 * You are given an array of characters which represents a string s. Write a function which reverses a string.

You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Input: s = ["n","e","e","t"]

Output: ["t","e","e","n"]
Example 2:

Input: s = ["r","a","c","e","c","a","r"]

Output: ["r","a","c","e","c","a","r"]
Constraints:

1 <= s.length < 100,000
s[i] is a printable ascii character.
TimeO(n)We perform exactly n/2 swaps — every character is touched once🧠 SpaceO(1)Only one temp variable is used regardless of input size — no extra array!
 */
public class ReverseString {
	public static void reverseString(char[] s) {
		int left = 0;
		int right = s.length - 1;
		while(left < right) {
			char temp = s[left];
			s[left] = s[right];
			s[right] = temp;
			left++;
			right--;
		}
		
		
	}
	 // 🖨️ Helper to print char array nicely
    public static void printArray(char[] s) {
        System.out.print("[");
        for (int i = 0; i < s.length; i++) {
            System.out.print("\"" + s[i] + "\"");
            if (i < s.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("     REVERSE STRING IN-PLACE RESULTS        ");
        System.out.println("============================================");

        // Example 1: Even length
        char[] s1 = {'n', 'e', 'e', 't'};
        System.out.print("Input:  "); printArray(s1);
        reverseString(s1);
        System.out.print("Output: "); printArray(s1);

        System.out.println("--------------------------------------------");

        // Example 2: Palindrome (odd length)
        char[] s2 = {'r', 'a', 'c', 'e', 'c', 'a', 'r'};
        System.out.print("Input:  "); printArray(s2);
        reverseString(s2);
        System.out.print("Output: "); printArray(s2);

        System.out.println("--------------------------------------------");

        // Example 3: Single character
        char[] s3 = {'x'};
        System.out.print("Input:  "); printArray(s3);
        reverseString(s3);
        System.out.print("Output: "); printArray(s3);

        System.out.println("--------------------------------------------");

        // Example 4: Two characters
        char[] s4 = {'h', 'i'};
        System.out.print("Input:  "); printArray(s4);
        reverseString(s4);
        System.out.print("Output: "); printArray(s4);

        System.out.println("--------------------------------------------");

        // Example 5: Full word
        char[] s5 = {'h', 'e', 'l', 'l', 'o'};
        System.out.print("Input:  "); printArray(s5);
        reverseString(s5);
        System.out.print("Output: "); printArray(s5);

        System.out.println("============================================");
    }
}
