package neetcode250.stack;

import java.util.Stack;

/*
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.
Example 1:

Input: ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]

Output: [null, 1, 1, 1, 2, 1, 4, 6]
Explanation:
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80); // return 1
stockSpanner.next(60); // return 1
stockSpanner.next(70); // return 2
stockSpanner.next(60); // return 1
stockSpanner.next(75); // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85); // return 6

Constraints:

1 <= price <= 100,000
At most 10,000 calls will be made to next.

ComplexityExplanationTime per call (amortized)O(1)Each price is pushed exactly once and popped at most once over its entire lifetime. So N calls = O(N) total work → O(1) amortized per call.Time worst case per callO(N)A single call can pop all N previous elements (e.g., the last price is the global maximum).SpaceO(N)In the worst case (strictly descending prices), nothing ever gets popped and all N elements stay on the stack.

 */
public class StockSpanner {

	private Stack<int[]> stack;
	public StockSpanner() {
		stack =  new Stack<>();
		
	}
	
	public  int next(int price) {
		int span = 1;
		while(!stack.isEmpty() && stack.peek()[0] <= price) {
			span += stack.pop()[1];
		}
		stack.push(new int[]{price, span});
		return span;
	}
	
	   public static void main(String[] args) {

	        System.out.println("=== Sample 1: [100, 80, 60, 70, 60, 75, 85] ===");
	        int[] prices1 = {100, 80, 60, 70, 60, 75, 85};
	        StockSpanner spanner1 = new StockSpanner();

	        System.out.print("Prices : ");
	        for (int p : prices1) System.out.printf("%4d", p);
	        System.out.println();

	        System.out.print("Spans  : ");
	        for (int p : prices1) System.out.printf("%4d", spanner1.next(p));
	        System.out.println();
	        // Expected: [1, 1, 1, 2, 1, 4, 6]

	        System.out.println();
	        System.out.println("=== Sample 2: [31, 41, 48, 59, 79] (strictly ascending) ===");
	        int[] prices2 = {31, 41, 48, 59, 79};
	        StockSpanner spanner2 = new StockSpanner();

	        System.out.print("Prices : ");
	        for (int p : prices2) System.out.printf("%4d", p);
	        System.out.println();

	        System.out.print("Spans  : ");
	        for (int p : prices2) System.out.printf("%4d", spanner2.next(p));
	        System.out.println();
	        // Expected: [1, 2, 3, 4, 5]

	        System.out.println();
	        System.out.println("=== Sample 3: [10, 10, 10, 10] (all equal) ===");
	        int[] prices3 = {10, 10, 10, 10};
	        StockSpanner spanner3 = new StockSpanner();

	        System.out.print("Prices : ");
	        for (int p : prices3) System.out.printf("%4d", p);
	        System.out.println();

	        System.out.print("Spans  : ");
	        for (int p : prices3) System.out.printf("%4d", spanner3.next(p));
	        System.out.println();
	        // Expected: [1, 2, 3, 4]

	        System.out.println();
	        System.out.println("=== Sample 4: [50, 40, 30, 20, 10] (strictly descending) ===");
	        int[] prices4 = {50, 40, 30, 20, 10};
	        StockSpanner spanner4 = new StockSpanner();

	        System.out.print("Prices : ");
	        for (int p : prices4) System.out.printf("%4d", p);
	        System.out.println();

	        System.out.print("Spans  : ");
	        for (int p : prices4) System.out.printf("%4d", spanner4.next(p));
	        System.out.println();
	        // Expected: [1, 1, 1, 1, 1]
	    }
	
}
