package neetcode250.stack;

import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
Example 1:

Input: ["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]

Output: [null, null, null, 2, 2, false]
Explanation:
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.

 * Time & Space Complexity
OperationTimeWhypush(x)O(n)Every existing element is moved once from q1 → q2pop()O(1)Front of q1 is always the toptop()O(1)Peek at front of q1empty()O(1)Single isEmpty() check
Space complexity: O(n) — at most n elements exist across both queues at any time. q2 is fully emptied after every push, so total storage is always exactly n elements.
 */
public class StackUsingQueue {
	Queue<Integer> q1;
	Queue<Integer> q2;

	 public StackUsingQueue() {
	        q1 = new LinkedList<>();
	        q2 = new LinkedList<>();
	    }
	    
	    public void push(int x) {
	        q2.add(x);
	        while(!q1.isEmpty()) {
	        	q2.add(q1.poll());
	        	
	        }
	        Queue<Integer> temp = q1;
	        q1 = q2;
	        q2  = temp;
	        
	    }
	    
	    public int pop() {
	       return q1.poll();
	    }
	    
	    public int top() {
	        return q1.peek();
	    }
	    
	    public boolean empty() {
	        return q1.isEmpty();
	    }
	    
	    public static void main(String[] args) {

	        System.out.println("===== Test 1: Basic push/pop/peek =====");
	        StackUsingQueue stack1 = new StackUsingQueue();

	        stack1.push(1);
	        stack1.push(2);
	        stack1.push(3);
	        System.out.println("Pushed 1, 2, 3");
	        System.out.println("Top (peek): " + stack1.top());    // Expected: 3
	        System.out.println("Pop: "        + stack1.pop());    // Expected: 3
	        System.out.println("Pop: "        + stack1.pop());    // Expected: 2
	        System.out.println("Top (peek): " + stack1.top());    // Expected: 1
	        System.out.println("Empty? "      + stack1.empty());  // Expected: false
	        System.out.println("Pop: "        + stack1.pop());    // Expected: 1
	        System.out.println("Empty? "      + stack1.empty());  // Expected: true

	        System.out.println("\n===== Test 2: Interleaved push and pop =====");
	        StackUsingQueue stack2 = new StackUsingQueue();

	        stack2.push(10);
	        stack2.push(20);
	        System.out.println("Pop: " + stack2.pop());           // Expected: 20
	        stack2.push(30);
	        stack2.push(40);
	        System.out.println("Top: " + stack2.top());           // Expected: 40
	        System.out.println("Pop: " + stack2.pop());           // Expected: 40
	        System.out.println("Pop: " + stack2.pop());           // Expected: 30
	        System.out.println("Pop: " + stack2.pop());           // Expected: 10
	        System.out.println("Empty? " + stack2.empty());       // Expected: true

	        System.out.println("\n===== Test 3: Single element =====");
	        StackUsingQueue stack3 = new StackUsingQueue();
	        stack3.push(99);
	        System.out.println("Top: "   + stack3.top());         // Expected: 99
	        System.out.println("Pop: "   + stack3.pop());         // Expected: 99
	        System.out.println("Empty? " + stack3.empty());       // Expected: true

	        System.out.println("\n===== Test 4: Large sequence =====");
	        StackUsingQueue stack4 = new StackUsingQueue();
	        for (int i = 1; i <= 5; i++) {
	            stack4.push(i * 100);
	            System.out.println("Pushed: " + (i * 100));
	        }
	        System.out.println("Popping all:");
	        while (!stack4.empty()) {
	            System.out.print(stack4.pop() + " ");             // Expected: 500 400 300 200 100
	        }
	        System.out.println();
	    }
}
