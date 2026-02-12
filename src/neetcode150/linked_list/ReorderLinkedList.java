package neetcode150.linked_list;
/*
 * Reorder Linked List
Medium
Topics
Company Tags
Hints
You are given the head of a singly linked-list.

The positions of a linked list of length = 7 for example, can intially be represented as:

[0, 1, 2, 3, 4, 5, 6]

Reorder the nodes of the linked list to be in the following order:

[0, 6, 1, 5, 2, 4, 3]

Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:

[0, n-1, 1, n-2, 2, n-3, ...]

You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.

Example 1:

Input: head = [2,4,6,8]

Output: [2,8,4,6]
Example 2:

Input: head = [2,4,6,8,10]

Output: [2,10,4,8,6]
Constraints:

1 <= Length of the list <= 1000.
1 <= Node.val <= 1000

Time Complexity: O(n)

Finding the middle: O(n) - we traverse the list once
Reversing the second half: O(n/2) = O(n) - we reverse half the list
Merging: O(n/2) = O(n) - we merge half the list
Total: O(n) + O(n) + O(n) = O(n)

Space Complexity: O(1)

We only use a few pointers (slow, fast, prev, current, temp1, temp2)
We don't create any new lists or use extra data structures
We modify the existing list in-place
Total: O(1) constant space
 */
public class ReorderLinkedList {
	public void reorderList(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode secondHalf = reverseList(slow.next);
		slow.next  = null;
		ListNode firstHalf = head;
		mergeList(firstHalf, secondHalf);
		
	}
	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode current = head;
		while(current != null) {
			ListNode temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
		return prev;
	}
	public void mergeList(ListNode l1, ListNode l2) {
		while(l2 != null) {
			ListNode temp1 = l1.next;
			ListNode temp2 = l2.next;
			l1.next = l2;
			l2.next = temp1;
			l1 = temp1;
			l2 = temp2;
			
		}
	}
	
	 public static void main(String[] args) {
		 ReorderLinkedList solution = new ReorderLinkedList();
	        
	        // Test Case 1: [1,2,3,4]
	        System.out.println("Test Case 1: [1,2,3,4]");
	        ListNode head1 = createList(new int[]{1, 2, 3, 4});
	        printList(head1);
	        solution.reorderList(head1);
	        System.out.print("After reordering: ");
	        printList(head1);
	        System.out.println();
	        
	        // Test Case 2: [1,2,3,4,5]
	        System.out.println("Test Case 2: [1,2,3,4,5]");
	        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5});
	        printList(head2);
	        solution.reorderList(head2);
	        System.out.print("After reordering: ");
	        printList(head2);
	        System.out.println();
	        
	        // Test Case 3: [1,2]
	        System.out.println("Test Case 3: [1,2]");
	        ListNode head3 = createList(new int[]{1, 2});
	        printList(head3);
	        solution.reorderList(head3);
	        System.out.print("After reordering: ");
	        printList(head3);
	        System.out.println();
	        
	        // Test Case 4: [1]
	        System.out.println("Test Case 4: [1]");
	        ListNode head4 = createList(new int[]{1});
	        printList(head4);
	        solution.reorderList(head4);
	        System.out.print("After reordering: ");
	        printList(head4);
	        System.out.println();
	    }
	    
	    private static ListNode createList(int[] values) {
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
	        System.out.print("Original: ");
	        ListNode current = head;
	        while (current != null) {
	            System.out.print(current.val);
	            if (current.next != null) {
	                System.out.print(" -> ");
	            }
	            current = current.next;
	        }
	        System.out.println();
	    }
}
