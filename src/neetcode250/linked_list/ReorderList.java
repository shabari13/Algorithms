package neetcode250.linked_list;
/*
 * You are given the head of a singly linked-list.

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
The key insight is that the reordering pattern — first, last, second, second-to-last, ... — can be achieved without any extra array or stack if we break it into three clean in-place sub-problems: find the middle, reverse the second half, then merge the two halves. This avoids O(n) extra space and runs in a single logical pass per sub-step.
Imagine you have a row of toy blocks: 1 2 3 4 5.
You want to rearrange them so the first and the last alternate: 1 5 2 4 3.
Here's what you do:

Find the middle — walk to the middle of the row. That splits it into 1 2 3 and 4 5.
Flip the second half — turn 4 5 around so it becomes 5 4.
Zip them together — take one from the first pile, one from the flipped pile, one from the first, one from the flipped... and you get 1 5 2 4 3. Done! 🎉
 ComplexityReason
 TimeO(n)Each of the 3 steps visits every node exactly once
 SpaceO(1)Only a handful of pointer variables — no extra list, array, or stack
 *
 */
public class ReorderList {
	public static void reorderList(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode secondHalf = reverse(slow.next);
		slow.next = null;
		ListNode firstHalf = head;
		 merge(firstHalf, secondHalf);
		
	}
	
	public static ListNode reverse(ListNode head) {
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
	
	public  static void merge(ListNode l1, ListNode l2) {
		while(l2 != null) {
			ListNode nextFirst = l1.next;
			ListNode nextSecond = l2.next;
			l1.next = l2;
			l2.next = nextFirst;
			l1 = nextFirst;
			l2 = nextSecond;
			
		}

	}
	
	 static ListNode buildList(int[] values) {
	        if (values.length == 0) return null;
	        ListNode dummy = new ListNode(0);
	        ListNode curr  = dummy;
	        for (int v : values) {
	            curr.next = new ListNode(v);
	            curr = curr.next;
	        }
	        return dummy.next;
	    }
	 
	    // Print the linked list as a readable string
	    static String listToString(ListNode head) {
	        StringBuilder sb = new StringBuilder("[");
	        ListNode curr = head;
	        while (curr != null) {
	            sb.append(curr.val);
	            if (curr.next != null) sb.append(" → ");
	            curr = curr.next;
	        }
	        sb.append("]");
	        return sb.toString();
	    }
	 
	    // ─────────────────────────────────────────────────────────────────────────
	    // MAIN METHOD – sample calls with different inputs
	    // ─────────────────────────────────────────────────────────────────────────
	    public static void main(String[] args) {
	 
	        System.out.println("╔══════════════════════════════════════════════════╗");
	        System.out.println("║          REORDER LINKED LIST – DEMO              ║");
	        System.out.println("╚══════════════════════════════════════════════════╝\n");
	 
	        // ── Test 1: ODD length list ──────────────────────────────────────────
	        // Input : 1 → 2 → 3 → 4 → 5
	        // Output: 1 → 5 → 2 → 4 → 3
	        int[] arr1  = {1, 2, 3, 4, 5};
	        ListNode l1 = buildList(arr1);
	        System.out.println("Test 1 (Odd length)");
	        System.out.println("  Input : " + listToString(l1));
	        reorderList(l1);
	        System.out.println("  Output: " + listToString(l1));
	        System.out.println("  Expected: [1 → 5 → 2 → 4 → 3]\n");
	 
	        // ── Test 2: EVEN length list ─────────────────────────────────────────
	        // Input : 1 → 2 → 3 → 4
	        // Output: 1 → 4 → 2 → 3
	        int[] arr2  = {1, 2, 3, 4};
	        ListNode l2 = buildList(arr2);
	        System.out.println("Test 2 (Even length)");
	        System.out.println("  Input : " + listToString(l2));
	        reorderList(l2);
	        System.out.println("  Output: " + listToString(l2));
	        System.out.println("  Expected: [1 → 4 → 2 → 3]\n");
	 
	        // ── Test 3: Single element ───────────────────────────────────────────
	        int[] arr3  = {42};
	        ListNode l3 = buildList(arr3);
	        System.out.println("Test 3 (Single element)");
	        System.out.println("  Input : " + listToString(l3));
	        reorderList(l3);
	        System.out.println("  Output: " + listToString(l3));
	        System.out.println("  Expected: [42]\n");
	 
	        // ── Test 4: Two elements ─────────────────────────────────────────────
	        int[] arr4  = {1, 2};
	        ListNode l4 = buildList(arr4);
	        System.out.println("Test 4 (Two elements)");
	        System.out.println("  Input : " + listToString(l4));
	        reorderList(l4);
	        System.out.println("  Output: " + listToString(l4));
	        System.out.println("  Expected: [1 → 2]\n");
	 
	        // ── Test 5: Six elements ─────────────────────────────────────────────
	        // Input : 1 → 2 → 3 → 4 → 5 → 6
	        // Output: 1 → 6 → 2 → 5 → 3 → 4
	        int[] arr5  = {1, 2, 3, 4, 5, 6};
	        ListNode l5 = buildList(arr5);
	        System.out.println("Test 5 (Six elements)");
	        System.out.println("  Input : " + listToString(l5));
	        reorderList(l5);
	        System.out.println("  Output: " + listToString(l5));
	        System.out.println("  Expected: [1 → 6 → 2 → 5 → 3 → 4]\n");
	 
	        System.out.println("╔══════════════════════════════════════════════════╗");
	        System.out.println("║  Time Complexity  : O(n)                         ║");
	        System.out.println("║  Space Complexity : O(1)                         ║");
	        System.out.println("╚══════════════════════════════════════════════════╝");
	    }
}
