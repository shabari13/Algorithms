package blind75.string;

public class LongestPalindromicSubstring {
	
	
	public String longestPalindrome(String s) {
		int start = 0;
		int end = 0;
		int len = 0;
		for(int i = 0; i < s.length(); i++) {
			int len1 =  expandFromCenter(s, i , i);
			
			int len2 =  expandFromCenter(s, i , i+1);
			
			len = Math.max(len1, len2);
			
			if( len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
			
		}
		return s.substring(start, end+1);
	}
		
		public int expandFromCenter(String s, int left, int right) {
			while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
				
				left--;
				right++;
				
			}
			return right - left -1;
		}
		
		
		public static void main(String[] args) {
	        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
	        
	        // Test case 1
	        String input1 = "babad";
	        String result1 = solution.longestPalindrome(input1);
	        System.out.println("Input: \"" + input1 + "\"");
	        System.out.println("Output: \"" + result1 + "\"");
	        System.out.println();
	        
	        // Test case 2
	        String input2 = "cbbd";
	        String result2 = solution.longestPalindrome(input2);
	        System.out.println("Input: \"" + input2 + "\"");
	        System.out.println("Output: \"" + result2 + "\"");
	        System.out.println();
	        
	        // Test case 3
	        String input3 = "a";
	        String result3 = solution.longestPalindrome(input3);
	        System.out.println("Input: \"" + input3 + "\"");
	        System.out.println("Output: \"" + result3 + "\"");
	        System.out.println();
	        
	        // Test case 4
	        String input4 = "racecar";
	        String result4 = solution.longestPalindrome(input4);
	        System.out.println("Input: \"" + input4 + "\"");
	        System.out.println("Output: \"" + result4 + "\"");
	        System.out.println();
	        
	        // Test case 5
	        String input5 = "noon";
	        String result5 = solution.longestPalindrome(input5);
	        System.out.println("Input: \"" + input5 + "\"");
	        System.out.println("Output: \"" + result5 + "\"");
	        System.out.println();
	    }

}
