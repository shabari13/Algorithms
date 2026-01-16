package blind75.linkedlist;
/*
 * 
 * You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

 

Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
 

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.

Time Complexity: O(n + m)

We visit each node in both lists exactly once
If list1 has n nodes and list2 has m nodes, we do n + m operations total
It's like counting all the kids in both lines once

Space Complexity: O(1)

We only create one dummy node
We're just rearranging pointers, not creating new nodes for the merged list
We reuse the existing nodes from the input lists
Think of it like rearranging kids in a line without making copies of them!

Our Strategy:

We create a "dummy" starting point (like a pretend train car at the front)
We compare the first numbers from both lists
We pick the smaller one and add it to our result
We keep doing this until we've used all numbers from both lists
 */
public class MergeTwoSortedLists {
	
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(-1);
		ListNode current = dummy;
		
		while(list1 != null && list2 != null) {
			if(list1.val < list2.val) {
				current.next = list1;
				list1 = list1.next;
			} else {
				current.next = list2;
				list2 = list2.next;
			}
			current = current.next;
		}
		
		if(list1 != null) {
			current.next = list1;
		}
		
		if(list2 != null) {
			current.next = list2;
		}
		
		return dummy.next;
		
	}
	
	  // Helper method to create a linked list from array
    public static ListNode createList(int[] arr) {
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
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();
        
        // Test Case 1
        System.out.println("Test Case 1:");
        ListNode list1 = createList(new int[]{1, 2, 4});
        ListNode list2 = createList(new int[]{1, 3, 4});
        System.out.print("List 1: ");
        printList(list1);
        System.out.print("List 2: ");
        printList(list2);
        ListNode result1 = solution.mergeTwoLists(list1, list2);
        System.out.print("Merged: ");
        printList(result1);
        System.out.println();
        
        // Test Case 2
        System.out.println("Test Case 2:");
        ListNode list3 = createList(new int[]{});
        ListNode list4 = createList(new int[]{});
        System.out.print("List 1: ");
        printList(list3);
        System.out.print("List 2: ");
        printList(list4);
        ListNode result2 = solution.mergeTwoLists(list3, list4);
        System.out.print("Merged: ");
        printList(result2);
        System.out.println();
        
        // Test Case 3
        System.out.println("Test Case 3:");
        ListNode list5 = createList(new int[]{});
        ListNode list6 = createList(new int[]{0});
        System.out.print("List 1: ");
        printList(list5);
        System.out.print("List 2: ");
        printList(list6);
        ListNode result3 = solution.mergeTwoLists(list5, list6);
        System.out.print("Merged: ");
        printList(result3);
        System.out.println();
        
        // Test Case 4
        System.out.println("Test Case 4:");
        ListNode list7 = createList(new int[]{5, 10, 15});
        ListNode list8 = createList(new int[]{2, 3, 20});
        System.out.print("List 1: ");
        printList(list7);
        System.out.print("List 2: ");
        printList(list8);
        ListNode result4 = solution.mergeTwoLists(list7, list8);
        System.out.print("Merged: ");
        printList(result4);
    }

}
