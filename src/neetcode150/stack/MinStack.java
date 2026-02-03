package neetcode150.stack;

import java.util.Stack;
/*
 * 
 * Time complexity: O(1) for all operations
Space complexity: O(n) in worst case

Design a stack class that supports the push, pop, top, and getMin operations.

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
 */
public class MinStack {
	
	private Stack<Integer> minStack;
	private Stack<Integer> stack;
	
	public MinStack() {
		stack = new Stack<>();
		minStack = new Stack<>();
	}
	
	public void push(int val) {
		stack.push(val);
		if(minStack.isEmpty() || val <= minStack.peek()) {
			minStack.push(val);
		}
	}
	
	public void pop() {
		if(stack.isEmpty()) {
			return;
		}
		int removed = stack.pop();
		if(removed == minStack.peek()) {
			minStack.pop();
		}
	}
	
	public int top(){
		return stack.peek();
	}
	public int getMin() {
		return minStack.peek();
	}
	
	public void printState() {
        System.out.println("Main Stack: " + stack);
        System.out.println("Min Stack:  " + minStack);
        if (!stack.isEmpty()) {
            System.out.println("Top element: " + top());
            System.out.println("Min element: " + getMin());
        }
        System.out.println("---");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Example 1: Basic Operations ===\n");
        
        MinStack minStack1 = new MinStack();
        
        System.out.println("Operation: push(-2)");
        minStack1.push(-2);
        minStack1.printState();
        
        System.out.println("Operation: push(0)");
        minStack1.push(0);
        minStack1.printState();
        
        System.out.println("Operation: push(-3)");
        minStack1.push(-3);
        minStack1.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack1.getMin());
        System.out.println();
        
        System.out.println("Operation: pop()");
        minStack1.pop();
        minStack1.printState();
        
        System.out.println("Operation: top()");
        System.out.println("Output: " + minStack1.top());
        System.out.println();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack1.getMin());
        System.out.println("\n");
        
        
        System.out.println("=== Example 2: Duplicate Minimums ===\n");
        
        MinStack minStack2 = new MinStack();
        
        System.out.println("Operation: push(5)");
        minStack2.push(5);
        minStack2.printState();
        
        System.out.println("Operation: push(1)");
        minStack2.push(1);
        minStack2.printState();
        
        System.out.println("Operation: push(1)");
        minStack2.push(1);
        minStack2.printState();
        
        System.out.println("Operation: push(3)");
        minStack2.push(3);
        minStack2.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack2.getMin());
        System.out.println();
        
        System.out.println("Operation: pop()");
        minStack2.pop();
        minStack2.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack2.getMin());
        System.out.println();
        
        System.out.println("Operation: pop()");
        minStack2.pop();
        minStack2.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack2.getMin());
        System.out.println("\n");
        
        
        System.out.println("=== Example 3: Descending Order ===\n");
        
        MinStack minStack3 = new MinStack();
        
        System.out.println("Operation: push(10)");
        minStack3.push(10);
        minStack3.printState();
        
        System.out.println("Operation: push(7)");
        minStack3.push(7);
        minStack3.printState();
        
        System.out.println("Operation: push(4)");
        minStack3.push(4);
        minStack3.printState();
        
        System.out.println("Operation: push(2)");
        minStack3.push(2);
        minStack3.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack3.getMin());
        System.out.println();
        
        System.out.println("Operation: pop()");
        minStack3.pop();
        minStack3.printState();
        
        System.out.println("Operation: getMin()");
        System.out.println("Output: " + minStack3.getMin());
        System.out.println();
    }

}
