package blind75.binary;

public class SumOfTwoIntegers {
	
	public static int getSum(int a, int b) {
		while(b != 0) {
			int carry = a & b;
			
			a = a ^ b;
			b = carry << 1;
		}
		
		return a;
	}

	public static void main(String[] args) {
        // Sample inputs
        int num1 = 5;
        int num2 = 3;
        
        System.out.println("Input: a = " + num1 + ", b = " + num2);
        int result = getSum(num1, num2);
        System.out.println("Output: " + result);
        
        System.out.println("\n--- More Test Cases ---");
        
        // Test case 2
        num1 = 7;
        num2 = 8;
        System.out.println("Input: a = " + num1 + ", b = " + num2);
        System.out.println("Output: " + getSum(num1, num2));
        
        // Test case 3 (with negative number)
        num1 = -1;
        num2 = 1;
        System.out.println("Input: a = " + num1 + ", b = " + num2);
        System.out.println("Output: " + getSum(num1, num2));
    }
}
