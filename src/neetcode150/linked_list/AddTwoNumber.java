package neetcode150.linked_list;
/*
 * You are given two non-empty linked lists, l1 and l2, where each represents a non-negative integer.

The digits are stored in reverse order, e.g. the number 321 is represented as 1 -> 2 -> 3 -> in the linked list.

Each of the nodes contains a single digit. You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Return the sum of the two numbers as a linked list.

Example 1:



Input: l1 = [1,2,3], l2 = [4,5,6]

Output: [5,7,9]

Explanation: 321 + 654 = 975.
Example 2:

Input: l1 = [9], l2 = [9]

Output: [8,1]

Time and Space Complexity
Time Complexity: O(max(m, n))

m is the length of list 1
n is the length of list 2
We visit each node exactly once, so we go through the longer list completely

Space Complexity: O(max(m, n))

We create a new linked list to store the result
The result has at most max(m, n) + 1 nodes (the +1 is for a possible carry at the end)
So the space we use grows with the size of the longer input list
 */
public class AddTwoNumber {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		while(l1 != null || l2!= null || carry != 0) {
			int l1Val = (l1 != null)? l1.val : 0;
			int l2Val = (l2 != null)? l2.val : 0;
			int sum = l1Val + l2Val+ carry;
			int digit = sum % 10;
			carry = sum / 10;
			ListNode newNode = new ListNode(digit);
			current.next = newNode;
			current = newNode;
			if(l1 != null)
				l1 = l1.next;
			if(l2 != null) 
				l2 = l2.next;
		}
		return dummy.next;
		
	}
	
	public static void main(String[] args) {
		AddTwoNumber solution = new AddTwoNumber();
        
        // Test Case 1: 342 + 465 = 807 (represented as 2->4->3 + 5->6->4 = 7->0->8)
        ListNode l1 = createLinkedList(new int[]{2, 4, 3});
        ListNode l2 = createLinkedList(new int[]{5, 6, 4});
        System.out.println("Test 1:");
        System.out.print("Input: ");
        printList(l1);
        System.out.print(" + ");
        printList(l2);
        System.out.print("\nOutput: ");
        printList(solution.addTwoNumbers(l1, l2));
        System.out.println("\n");
        
        // Test Case 2: 0 + 0 = 0
        l1 = createLinkedList(new int[]{0});
        l2 = createLinkedList(new int[]{0});
        System.out.println("Test 2:");
        System.out.print("Input: ");
        printList(l1);
        System.out.print(" + ");
        printList(l2);
        System.out.print("\nOutput: ");
        printList(solution.addTwoNumbers(l1, l2));
        System.out.println("\n");
        
        // Test Case 3: 9999999 + 9999 = 10009998 
        l1 = createLinkedList(new int[]{9, 9, 9, 9, 9, 9, 9});
        l2 = createLinkedList(new int[]{9, 9, 9, 9});
        System.out.println("Test 3:");
        System.out.print("Input: ");
        printList(l1);
        System.out.print(" + ");
        printList(l2);
        System.out.print("\nOutput: ");
        printList(solution.addTwoNumbers(l1, l2));
        System.out.println();
    }
    
    private static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }
    
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
    }

}
