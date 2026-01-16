package blind75.linkedlist;
/*
 * 
 * Given the head of a singly linked list, reverse the list, and return the reversed list.

 

Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []
 

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000
 

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
	
	public ListNode reverseList(ListNode root) {
		
		ListNode current = root;
		
		ListNode prev = null;
		
		while(current != null) {
			
			ListNode temp = current.next;
			
			current.next = prev;
			
			prev = current;
			
			current = temp;
		}
		
		return prev;
	}
	
	
	 // Helper method to create a linked list from an array
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print the linked list
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        
        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
    	ReverseLinkedList solution = new ReverseLinkedList();
        
        // Test Case 1: Normal list [1, 2, 3, 4, 5]
        System.out.println("Test Case 1:");
        System.out.print("Original: ");
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        printList(head1);
        ListNode reversed1 = solution.reverseList(head1);
        System.out.print("Reversed: ");
        printList(reversed1);
        System.out.println();
        
        // Test Case 2: Two nodes [1, 2]
        System.out.println("Test Case 2:");
        System.out.print("Original: ");
        ListNode head2 = createList(new int[]{1, 2});
        printList(head2);
        ListNode reversed2 = solution.reverseList(head2);
        System.out.print("Reversed: ");
        printList(reversed2);
        System.out.println();
        
        // Test Case 3: Empty list []
        System.out.println("Test Case 3:");
        System.out.print("Original: ");
        ListNode head3 = createList(new int[]{});
        printList(head3);
        ListNode reversed3 = solution.reverseList(head3);
        System.out.print("Reversed: ");
        printList(reversed3);
        System.out.println();
        
        // Test Case 4: Single node [42]
        System.out.println("Test Case 4:");
        System.out.print("Original: ");
        ListNode head4 = createList(new int[]{42});
        printList(head4);
        ListNode reversed4 = solution.reverseList(head4);
        System.out.print("Reversed: ");
        printList(reversed4);
        System.out.println();
        
        // Test Case 5: Longer list [10, 20, 30, 40, 50, 60]
        System.out.println("Test Case 5:");
        System.out.print("Original: ");
        ListNode head5 = createList(new int[]{10, 20, 30, 40, 50, 60});
        printList(head5);
        ListNode reversed5 = solution.reverseList(head5);
        System.out.print("Reversed: ");
        printList(reversed5);
    }

}
