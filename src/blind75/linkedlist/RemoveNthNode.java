package blind75.linkedlist;

/*Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?

Time Complexity: O(L) where L is the length of the linked list

We traverse the list only ONCE
Fast pointer moves L steps total
Slow pointer moves (L - n) steps
Total: One pass through the list

Space Complexity: O(1)

We only use two pointers (fast and slow) and one dummy node
No extra arrays or data structures
Memory usage doesn't grow with input size

Imagine you have a line of kids holding hands. You want to remove one specific kid from the line, but here's the tricky part - you can only count from the END of the line, not the beginning!
The Magic Trick: Two Friends Walking
Instead of counting all the kids twice, we use a clever trick with two friends (we call them "fast" and "slow"):

Fast friend starts walking first and gets a head start
Slow friend waits at the beginning
When fast friend has walked enough steps ahead, slow friend starts walking too
When fast friend reaches the end, slow friend is standing RIGHT BEFORE the kid we need to remove!
*/
public class RemoveNthNode {
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode fast = dummy;
		ListNode slow = dummy;
		
		for(int i = 0; i <= n; i++) {
			fast = fast.next;
		}
		while(fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		
		slow.next = slow.next.next;
		
		return dummy.next;
		
	}

	 public ListNode createList(int[] arr) {
	        if (arr == null || arr.length == 0) return null;
	        
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
	    	RemoveNthNode solution = new RemoveNthNode();
	        
	        // Test Case 1: [1,2,3,4,5], n = 2
	        System.out.println("Test Case 1:");
	        int[] arr1 = {1, 2, 3, 4, 5};
	        ListNode head1 = solution.createList(arr1);
	        System.out.print("Original List: ");
	        solution.printList(head1);
	        System.out.println("Remove 2nd node from end");
	        ListNode result1 = solution.removeNthFromEnd(head1, 2);
	        System.out.print("Result: ");
	        solution.printList(result1);
	        System.out.println();
	        
	        // Test Case 2: [1], n = 1
	        System.out.println("Test Case 2:");
	        int[] arr2 = {1};
	        ListNode head2 = solution.createList(arr2);
	        System.out.print("Original List: ");
	        solution.printList(head2);
	        System.out.println("Remove 1st node from end");
	        ListNode result2 = solution.removeNthFromEnd(head2, 1);
	        System.out.print("Result: ");
	        solution.printList(result2);
	        System.out.println();
	        
	        // Test Case 3: [1,2], n = 1
	        System.out.println("Test Case 3:");
	        int[] arr3 = {1, 2};
	        ListNode head3 = solution.createList(arr3);
	        System.out.print("Original List: ");
	        solution.printList(head3);
	        System.out.println("Remove 1st node from end");
	        ListNode result3 = solution.removeNthFromEnd(head3, 1);
	        System.out.print("Result: ");
	        solution.printList(result3);
	        System.out.println();
	        
	        // Test Case 4: [1,2], n = 2
	        System.out.println("Test Case 4:");
	        int[] arr4 = {1, 2};
	        ListNode head4 = solution.createList(arr4);
	        System.out.print("Original List: ");
	        solution.printList(head4);
	        System.out.println("Remove 2nd node from end (head)");
	        ListNode result4 = solution.removeNthFromEnd(head4, 2);
	        System.out.print("Result: ");
	        solution.printList(result4);
	        System.out.println();
	        
	        // Test Case 5: [1,2,3,4,5,6,7], n = 4
	        System.out.println("Test Case 5:");
	        int[] arr5 = {1, 2, 3, 4, 5, 6, 7};
	        ListNode head5 = solution.createList(arr5);
	        System.out.print("Original List: ");
	        solution.printList(head5);
	        System.out.println("Remove 4th node from end");
	        ListNode result5 = solution.removeNthFromEnd(head5, 4);
	        System.out.print("Result: ");
	        solution.printList(result5);
	    }
}
