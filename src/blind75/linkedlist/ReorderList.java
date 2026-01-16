package blind75.linkedlist;
/*
 * 
 * You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:

⏱️ Time Complexity
Step	Time
Find middle	O(n)
Reverse	O(n)
Merge	O(n)
Total	✅ O(n)
💾 Space Complexity

No extra data structures

Only pointers

✅ O(1) (Constant Space)

🛠️ Strategy (Big Picture)

We solve this in 3 simple steps:

Step 1️⃣: Find the middle of the list
Step 2️⃣: Reverse the second half
Step 3️⃣: Merge the two halves like zipper teeth
 */
public class ReorderList {
	
	public static void reorderList(ListNode head) {
		if(head == null || head.next == null) {
			return;
		}
		
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
		}
		
		ListNode first = head;
		ListNode second = reverseList(slow.next);
		slow.next = null;
		
		while(second != null) {
			ListNode temp1 = first.next;
			ListNode temp2 = second.next;
			first.next = second;
			second.next = temp1;
			first = temp1;
			second = temp2;
			
		}
		
	}

	public static ListNode reverseList(ListNode head) {
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
	
	 // Helper method to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // Helper method to create list from array
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        int[][] testCases = {
            {1, 2, 3, 4},
            {1, 2, 3, 4, 5},
            {1}, {1, 2},
            {10, 20, 30, 40, 50, 60}
        };

        for (int[] test : testCases) {
            ListNode head = createList(test);
            System.out.print("Original: ");
            printList(head);

            reorderList(head);

            System.out.print("Reordered: ");
            printList(head);
            System.out.println("------------");
        }
    }
}
