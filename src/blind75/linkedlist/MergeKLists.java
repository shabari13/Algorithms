package blind75.linkedlist;

import java.util.PriorityQueue;
/*
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.


Time: O(N log k)

N = total number of nodes across ALL lists
k = number of lists
Why?

We process each node exactly once: N operations
Each heap operation (insert/remove) takes log k time
Total: N × log k



Example: If you have 3 lists with 10 total nodes:

Processing all nodes: 10 operations
Each operation with heap of size 3: log(3) ≈ 1.5
Total: 10 × 1.5 = 15 operations


💾 Space Complexity
Space: O(k)

The heap stores at most k nodes at any time (one from each list)
We also create a new result list, but that doesn't count as extra space (it's the output)

Think of it like this:
Step 1: The Starting Line

You have 3 friends, each with their train line:

Friend 1: 1 → 4 → 5
Friend 2: 1 → 3 → 4
Friend 3: 2 → 6



Step 2: The Magic Box (Min-Heap)

You have a special box that always tells you which car has the SMALLEST number
First, you put the FIRST car from each friend's line into the magic box
Magic box has: 1 (Friend 1), 1 (Friend 2), 2 (Friend 3)

Step 3: Building Your New Line
Now you repeat this:

Take out the smallest car from the magic box
Put it at the end of your new line
If that car had another car behind it, put THAT car into the magic box	
 */

public class MergeKLists {
	
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
		
		ListNode dummy = new ListNode(-1);
		ListNode current = dummy;
		
		for(ListNode node : lists) {
			if(node != null) {
				minHeap.offer(node);
			}
		}
		
		while(!minHeap.isEmpty()) {
			ListNode smallest = minHeap.poll();
			current.next = smallest;
			current = current.next;
			if(smallest.next != null) {
				minHeap.offer(smallest.next);
			}
			
		}
		return dummy.next;
	}
	
	public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print a linked list
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
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
    	MergeKLists solution = new MergeKLists();
        
        // Test Case 1: Three sorted lists
        System.out.println("Test Case 1:");
        System.out.println("Input lists:");
        ListNode list1 = createList(new int[]{1, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode list3 = createList(new int[]{2, 6});
        System.out.print("  List 1: "); printList(list1);
        System.out.print("  List 2: "); printList(list2);
        System.out.print("  List 3: "); printList(list3);
        
        ListNode[] lists1 = {list1, list2, list3};
        ListNode result1 = solution.mergeKLists(lists1);
        System.out.print("Merged Result: "); printList(result1);
        System.out.println();
        
        // Test Case 2: Empty list
        System.out.println("Test Case 2:");
        System.out.println("Input: Empty array of lists");
        ListNode[] lists2 = {};
        ListNode result2 = solution.mergeKLists(lists2);
        System.out.print("Merged Result: "); printList(result2);
        System.out.println();
        
        // Test Case 3: Single empty list
        System.out.println("Test Case 3:");
        System.out.println("Input: Array with one empty list");
        ListNode[] lists3 = {null};
        ListNode result3 = solution.mergeKLists(lists3);
        System.out.print("Merged Result: "); printList(result3);
        System.out.println();
        
        // Test Case 4: Two lists with different sizes
        System.out.println("Test Case 4:");
        System.out.println("Input lists:");
        ListNode list4a = createList(new int[]{1, 2, 3, 7, 8});
        ListNode list4b = createList(new int[]{4, 5, 6});
        System.out.print("  List 1: "); printList(list4a);
        System.out.print("  List 2: "); printList(list4b);
        
        ListNode[] lists4 = {list4a, list4b};
        ListNode result4 = solution.mergeKLists(lists4);
        System.out.print("Merged Result: "); printList(result4);
        System.out.println();
        
        // Test Case 5: Single list
        System.out.println("Test Case 5:");
        System.out.println("Input lists:");
        ListNode list5 = createList(new int[]{1, 2, 3});
        System.out.print("  List 1: "); printList(list5);
        
        ListNode[] lists5 = {list5};
        ListNode result5 = solution.mergeKLists(lists5);
        System.out.print("Merged Result: "); printList(result5);
    }

}
