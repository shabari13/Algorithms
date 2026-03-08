package neetcode150.mathandgeometry;

public class ReverseNumber {
	
	public static  int reverse(int n) {
		int result = 0;
		
		int sign = (n < 0) ? -1:1;
		
		while(n != 0) {
			int lastDigit = n %10;
			n = n / 10;
			result = result * 10 + lastDigit;
		}
		
		return result ;
		
	}
	public static void main(String[] args) {
		//System.out.print(reverse(-34346346));
		System.out.println(reverse(001));
		
	}

}
