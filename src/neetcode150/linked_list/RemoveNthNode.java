package neetcode150.linked_list;

/*
 * 
 * You are given the beginning of a linked list head, and an integer n.

Remove the nth node from the end of the list and return the beginning of the list.

Example 1:

Input: head = [1,2,3,4], n = 2

Output: [1,2,4]
Example 2:

Input: head = [5], n = 1

Output: []
Example 3:

Input: head = [1,2], n = 2

Output: [2]
Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
### Time Complexity: **O(L)** where L is the length of the linked list

**Why?**
- We move the fast pointer n+1 steps: O(n)
- We move both pointers until fast reaches the end: O(L-n)
- Total: O(n) + O(L-n) = O(L)
- We traverse the list only once!

**Real example with L=5, n=2:**
- Move fast 3 steps: 3 operations
- Move both 2 steps: 2 operations
- Total: 5 operations for a list of length 5

### Space Complexity: **O(1)**
 */
public class RemoveNthNode {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;
		for(int i = 0; i<= n ; i++) {
			fast = fast.next;
		}
		while(fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next  = slow.next.next;
		return dummy.next;
	}
	
	public ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    // Helper method to print linked list
    public void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
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
		   RemoveNthNode solution = new RemoveNthNode();
	        
	        // Test Case 1: Remove 2nd node from end
	        System.out.println("Test Case 1:");
	        int[] arr1 = {1, 2, 3, 4, 5};
	        ListNode head1 = solution.createList(arr1);
	        System.out.print("Original List: ");
	        solution.printList(head1);
	        ListNode result1 = solution.removeNthFromEnd(head1, 2);
	        System.out.print("After removing 2nd from end: ");
	        solution.printList(result1);
	        System.out.println();
	        
	        // Test Case 2: Remove 1st node from end (last node)
	        System.out.println("Test Case 2:");
	        int[] arr2 = {1, 2, 3, 4, 5};
	        ListNode head2 = solution.createList(arr2);
	        System.out.print("Original List: ");
	        solution.printList(head2);
	        ListNode result2 = solution.removeNthFromEnd(head2, 1);
	        System.out.print("After removing 1st from end: ");
	        solution.printList(result2);
	        System.out.println();
	        
	        // Test Case 3: Remove only node
	        System.out.println("Test Case 3:");
	        int[] arr3 = {1};
	        ListNode head3 = solution.createList(arr3);
	        System.out.print("Original List: ");
	        solution.printList(head3);
	        ListNode result3 = solution.removeNthFromEnd(head3, 1);
	        System.out.print("After removing 1st from end: ");
	        solution.printList(result3);
	        System.out.println();
	        
	        // Test Case 4: Remove first node (from a 2-node list)
	        System.out.println("Test Case 4:");
	        int[] arr4 = {1, 2};
	        ListNode head4 = solution.createList(arr4);
	        System.out.print("Original List: ");
	        solution.printList(head4);
	        ListNode result4 = solution.removeNthFromEnd(head4, 2);
	        System.out.print("After removing 2nd from end: ");
	        solution.printList(result4);
	        System.out.println();
	        
	        // Test Case 5: Remove 5th node from end
	        System.out.println("Test Case 5:");
	        int[] arr5 = {1, 2, 3, 4, 5};
	        ListNode head5 = solution.createList(arr5);
	        System.out.print("Original List: ");
	        solution.printList(head5);
	        ListNode result5 = solution.removeNthFromEnd(head5, 5);
	        System.out.print("After removing 5th from end: ");
	        solution.printList(result5);
	    }

}
