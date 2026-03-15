package neetcode250.stack;

import java.util.Stack;
/*
 * Design a stack class that supports the push, pop, top, and getMin operations.

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
Each function should run in 
O
(
1
)
O(1) time.

Example 1:

Input: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]

Output: [null,null,null,null,0,null,2,1]

Explanation:
MinStack minStack = new MinStack();
minStack.push(1);
minStack.push(2);
minStack.push(0);
minStack.getMin(); // return 0
minStack.pop();
minStack.top();    // return 2
minStack.getMin(); // return 1
Constraints:

-2^31 <= val <= 2^31 - 1.
pop, top and getMin will always be called on non-empty stacks.

 * ════════════════════════════════════════════════
 COMPLEXITY ANALYSIS
════════════════════════════════════════════════
 Time  — push : O(1)  | pop : O(1)
         top  : O(1)  | getMin : O(1)
 Space — O(n) — two stacks, each at most n entries
         n = number of elements currently pushed
 */
public class MinStack {
	Stack<Integer> mainStack;
	Stack<Integer> minStack;
	public MinStack() {
		mainStack = new Stack<>();
		minStack = new Stack<>();
	}
	public void push(int val) {
		mainStack.push(val);
		if(!minStack.isEmpty()) {
			if(val < minStack.peek()) {
				minStack.push(val);
			}
		} else {
			minStack.push(val);
		}
    }
	
	public void pop() {
		int top = mainStack.pop();
		if(!minStack.isEmpty()) {
			if(top == minStack.peek()) {
				minStack.pop();
			}
		}
    }
	
	public int top() {
        return mainStack.peek();
    }
	
	public int getMin() {
		if(!minStack.isEmpty()) {
			return minStack.peek();
		}
		return -1;
    }
	
	public static void main(String[] args) {
		 
        // ═══════════════════════════════════════════════════════════════════
        // TEST CASE 1 — LeetCode classic example
        //   Operations : push(-3), push(0), push(-2), push(-2), push(5)
        //                getMin, pop, top, getMin
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("═══════════════════════════════════════");
        System.out.println(" TEST CASE 1  (LeetCode classic)");
        System.out.println("═══════════════════════════════════════");
 
        MinStack ms1 = new MinStack();
 
        // ── push(-3) ────────────────────────────────────────────────────────
        // mainStack: [-3]         minStack is empty → currentMin = -3
        // minStack : [-3]
        ms1.push(-3);
        System.out.println("push(-3)  | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── push(0) ─────────────────────────────────────────────────────────
        // currentMin = min(0, -3) = -3  →  minStack gets -3 again
        // mainStack: [-3, 0]      minStack: [-3, -3]
        ms1.push(0);
        System.out.println("push(0)   | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── push(-2) ────────────────────────────────────────────────────────
        // currentMin = min(-2, -3) = -3
        // mainStack: [-3, 0, -2]  minStack: [-3, -3, -3]
        ms1.push(-2);
        System.out.println("push(-2)  | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── push(-2) again ──────────────────────────────────────────────────
        // currentMin = min(-2, -3) = -3
        // mainStack: [-3, 0, -2, -2]  minStack: [-3, -3, -3, -3]
        ms1.push(-2);
        System.out.println("push(-2)  | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── push(5) ─────────────────────────────────────────────────────────
        // currentMin = min(5, -3) = -3
        // mainStack: [-3, 0, -2, -2, 5]  minStack: [-3, -3, -3, -3, -3]
        ms1.push(5);
        System.out.println("push(5)   | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── getMin() ─────────────────────────────────────────────────────────
        // minStack.peek() = -3  → answer: -3
        System.out.println("getMin()  → " + ms1.getMin() + "   (expected -3)");
 
        // ── pop() ────────────────────────────────────────────────────────────
        // Removes 5 from mainStack AND -3 entry from minStack (in sync)
        // mainStack: [-3, 0, -2, -2]  minStack: [-3, -3, -3, -3]
        ms1.pop();
        System.out.println("pop()     | mainStack: " + ms1.mainStack
                         + " | minStack: " + ms1.minStack);
 
        // ── top() ────────────────────────────────────────────────────────────
        // mainStack.peek() = -2  → answer: -2
        System.out.println("top()     → " + ms1.top() + "     (expected -2)");
 
        // ── getMin() ─────────────────────────────────────────────────────────
        // minStack.peek() = -3  → answer still -3 (the -3 at the bottom is still there)
        System.out.println("getMin()  → " + ms1.getMin() + "   (expected -3)");
 
 
        // ═══════════════════════════════════════════════════════════════════
        // TEST CASE 2 — Minimum changes as we pop
        //   Demonstrates that getMin correctly "restores" the previous min
        //   after the current minimum is itself popped off.
        //   Operations: push(5), push(3), push(7), push(1),
        //               getMin, pop, getMin, pop, getMin
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════");
        System.out.println(" TEST CASE 2  (min updates on pop)");
        System.out.println("═══════════════════════════════════════");
 
        MinStack ms2 = new MinStack();
 
        // push(5):  mainStack=[5]       minStack=[5]   currentMin=5
        ms2.push(5);
        System.out.println("push(5)   | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // push(3):  mainStack=[5,3]     minStack=[5,3]  currentMin=min(3,5)=3
        ms2.push(3);
        System.out.println("push(3)   | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // push(7):  mainStack=[5,3,7]   minStack=[5,3,3]  currentMin=min(7,3)=3
        ms2.push(7);
        System.out.println("push(7)   | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // push(1):  mainStack=[5,3,7,1] minStack=[5,3,3,1] currentMin=min(1,3)=1
        ms2.push(1);
        System.out.println("push(1)   | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // getMin(): minStack.peek()=1  → 1
        System.out.println("getMin()  → " + ms2.getMin() + "     (expected 1)");
 
        // pop():    removes 1 from both stacks
        //           mainStack=[5,3,7]  minStack=[5,3,3]
        //           Now minStack.peek()=3 (restored automatically!)
        ms2.pop();
        System.out.println("pop()     | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // getMin(): minStack.peek()=3  → 3
        System.out.println("getMin()  → " + ms2.getMin() + "     (expected 3)");
 
        // pop():    removes 7 from mainStack, 3 from minStack
        //           mainStack=[5,3]  minStack=[5,3]
        ms2.pop();
        System.out.println("pop()     | mainStack: " + ms2.mainStack
                         + " | minStack: " + ms2.minStack);
 
        // getMin(): minStack.peek()=3  → still 3 (3 is still on the main stack)
        System.out.println("getMin()  → " + ms2.getMin() + "     (expected 3)");
 
 
        // ═══════════════════════════════════════════════════════════════════
        // TEST CASE 3 — Duplicate minimums
        //   Ensures duplicates are handled correctly.
        //   Operations: push(2), push(2), push(2),
        //               getMin, pop, getMin, pop, getMin
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════");
        System.out.println(" TEST CASE 3  (duplicate minimums)");
        System.out.println("═══════════════════════════════════════");
 
        MinStack ms3 = new MinStack();
 
        // push(2): mainStack=[2] minStack=[2]
        ms3.push(2);
        System.out.println("push(2)   | mainStack: " + ms3.mainStack
                         + " | minStack: " + ms3.minStack);
 
        // push(2): mainStack=[2,2] minStack=[2,2]  currentMin=min(2,2)=2
        ms3.push(2);
        System.out.println("push(2)   | mainStack: " + ms3.mainStack
                         + " | minStack: " + ms3.minStack);
 
        // push(2): mainStack=[2,2,2] minStack=[2,2,2]
        ms3.push(2);
        System.out.println("push(2)   | mainStack: " + ms3.mainStack
                         + " | minStack: " + ms3.minStack);
 
        System.out.println("getMin()  → " + ms3.getMin() + "     (expected 2)");
 
        ms3.pop();
        System.out.println("pop()     | mainStack: " + ms3.mainStack
                         + " | minStack: " + ms3.minStack);
        System.out.println("getMin()  → " + ms3.getMin() + "     (expected 2)");
 
        ms3.pop();
        System.out.println("pop()     | mainStack: " + ms3.mainStack
                         + " | minStack: " + ms3.minStack);
        System.out.println("getMin()  → " + ms3.getMin() + "     (expected 2)");
 
 
        // ═══════════════════════════════════════════════════════════════════
        // COMPLEXITY SUMMARY
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println(" COMPLEXITY ANALYSIS");
        System.out.println("════════════════════════════════════════════════");
        System.out.println(" Time  — push : O(1)  | pop : O(1)");
        System.out.println("         top  : O(1)  | getMin : O(1)");
        System.out.println(" Space — O(n) — two stacks, each at most n entries");
        System.out.println("         n = number of elements currently pushed");
        System.out.println("════════════════════════════════════════════════");
    }
}
