package neetcode250.stack;

import java.util.Stack;
/*
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
Example 1:

Input: ["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]

Output: [null, null, null, 1, 1, false]
Explanation:
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.

Time Complexity:
OperationComplexityWhypush(x)O(1)Single stack pushpop()Amortized O(1)Each element is moved at most once (inbox → outbox), so across N operations, total cost is O(N) → O(1) per oppeek()Amortized O(1)Same reasoning as popempty()O(1)Just checking .isEmpty() on both stacks
Space Complexity: O(N) — where N is the number of elements in the queue. Every element lives in exactly one of the two stacks at any point in time.
The key insight is lazy transfer — we only pour from inbox to outbox when outbox runs dry, and once an element is in outbox, it stays there until popped. This avoids redundant work.
 */
public class QueueUsingStack {
	Stack<Integer> stack1;
	Stack<Integer> stack2;
	public QueueUsingStack() {
		stack1 = new Stack<>();
		stack2 = new Stack<>();
	}
	
	public void push(int x) {
		stack1.push(x);
	}
	
	public void transfer() {
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
	}
	
	public int pop() {
		transfer();
		return stack2.pop();
	}
	
	public int peek() {
		transfer();
		return stack2.peek();
	}
	
	public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
	
	  public static void main(String[] args) {

	        System.out.println("===== Test 1: Basic enqueue/dequeue =====");
	        QueueUsingStack q1 = new QueueUsingStack();

	        q1.push(1);   // queue: [1]
	        q1.push(2);   // queue: [1, 2]
	        q1.push(3);   // queue: [1, 2, 3]

	        System.out.println("peek()  → " + q1.peek());   // expects 1
	        System.out.println("pop()   → " + q1.pop());    // expects 1
	        System.out.println("pop()   → " + q1.pop());    // expects 2
	        System.out.println("empty() → " + q1.empty());  // expects false
	        System.out.println("pop()   → " + q1.pop());    // expects 3
	        System.out.println("empty() → " + q1.empty());  // expects true

	        System.out.println("\n===== Test 2: Interleaved push and pop =====");
	        QueueUsingStack q2 = new QueueUsingStack();

	        q2.push(10);
	        q2.push(20);
	        System.out.println("pop()  → " + q2.pop());    // expects 10
	        q2.push(30);
	        System.out.println("peek() → " + q2.peek());   // expects 20
	        System.out.println("pop()  → " + q2.pop());    // expects 20
	        System.out.println("pop()  → " + q2.pop());    // expects 30

	        System.out.println("\n===== Test 3: Single element =====");
	        QueueUsingStack q3 = new QueueUsingStack();
	        q3.push(42);
	        System.out.println("peek() → " + q3.peek());   // expects 42
	        System.out.println("pop()  → " + q3.pop());    // expects 42
	        System.out.println("empty() → " + q3.empty()); // expects true

	        System.out.println("\n===== Test 4: Large sequence =====");
	        QueueUsingStack q4 = new QueueUsingStack();
	        for (int i = 1; i <= 5; i++) q4.push(i * 100);
	        while (!q4.empty()) {
	            System.out.print("pop() → " + q4.pop() + "  ");
	        }
	        System.out.println();
	    }

}
