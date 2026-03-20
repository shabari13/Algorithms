package neetcode250.linked_list;
/*
 * You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted linked list and return the head of the new sorted linked list.

The new list should be made up of nodes from list1 and list2.

Example 1:



Input: list1 = [1,2,4], list2 = [1,3,5]

Output: [1,1,2,3,4,5]
Example 2:

Input: list1 = [], list2 = [1,2]

Output: [1,2]
Example 3:

Input: list1 = [], list2 = []

Output: []
Constraints:

0 <= The length of the each list <= 100.
-100 <= Node.val <= 100

⏱ Complexity Analysis
Time Complexity: O(m + n)

m = length of list1, n = length of list2
In the worst case, every single node from both lists is visited exactly once — one comparison per node picked. Even the tail-append at the end costs O(1) since it's just a pointer assignment, not a loop. Total work is directly proportional to the combined size of both lists.

Space Complexity: O(1)

No new nodes are ever created. The algorithm purely rewires the existing next pointers of nodes that already exist in memory. The only extra variables used are dummy (one node, constant) and current (one pointer). No arrays, no recursion stack, nothing that grows with input size. This is as space-efficient as it gets for a merge operation.
 */
public class MergeSortedLinkedList {

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		
		if(l1 == null && l2 == null) {
			return null;
		}
		   // Step 1: Create a "dummy" head node.
        // This is a fake starting node we never actually use in the result.
        // Its only job is to give us a stable place to attach the first real node,
        // so we don't need messy special-casing for "what is the head of the result?".
     
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;

		while(l1 != null && l2 != null) {
			if(l1.val <= l2.val) {
				current.next = l1;
				l1 = l1.next;
			} else {
				current.next = l2;
				l2 = l2.next;
			}
			current = current.next;
		}
		if(l1 != null) {
			current.next = l1;
		}
		if(l2 != null) {
			current.next = l2;
		}
		return dummy.next;
	}
	
	  // ─── Helper: build a linked list from an array ──────────────────────────────
    static ListNode build(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode cur  = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    // ─── Helper: print a linked list ────────────────────────────────────────────
    static String stringify(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.length() == 0 ? "null" : sb.toString();
    }

    // ─── Main: run several test cases ───────────────────────────────────────────
    public static void main(String[] args) {

        // Test 1: Classic interleaving case
        ListNode l1 = build(new int[]{1, 3, 5});
        ListNode l2 = build(new int[]{2, 4, 6});
        System.out.println("Test 1:");
        System.out.println("  List1  : " + stringify(l1));
        System.out.println("  List2  : " + stringify(l2));
        System.out.println("  Merged : " + stringify(mergeTwoLists(l1, l2)));
        // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> 6

        System.out.println();

        // Test 2: One list's remaining tail gets appended at the end
        ListNode l3 = build(new int[]{1, 2, 4});
        ListNode l4 = build(new int[]{1, 3, 4});
        System.out.println("Test 2:");
        System.out.println("  List1  : " + stringify(l3));
        System.out.println("  List2  : " + stringify(l4));
        System.out.println("  Merged : " + stringify(mergeTwoLists(l3, l4)));
        // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4

        System.out.println();

        // Test 3: Both lists empty
        ListNode l5 = build(new int[]{});
        ListNode l6 = build(new int[]{});
        System.out.println("Test 3 (both empty):");
        System.out.println("  List1  : " + stringify(l5));
        System.out.println("  List2  : " + stringify(l6));
        System.out.println("  Merged : " + stringify(mergeTwoLists(l5, l6)));
        // Expected: null

        System.out.println();

        // Test 4: One list is empty
        ListNode l7 = build(new int[]{});
        ListNode l8 = build(new int[]{0});
        System.out.println("Test 4 (one empty):");
        System.out.println("  List1  : " + stringify(l7));
        System.out.println("  List2  : " + stringify(l8));
        System.out.println("  Merged : " + stringify(mergeTwoLists(l7, l8)));
        // Expected: 0

        System.out.println();

        // Test 5: Lists of unequal length — longer tail gets appended
        ListNode l9  = build(new int[]{1, 5, 7, 9, 11});
        ListNode l10 = build(new int[]{2, 3});
        System.out.println("Test 5 (unequal lengths):");
        System.out.println("  List1  : " + stringify(l9));
        System.out.println("  List2  : " + stringify(l10));
        System.out.println("  Merged : " + stringify(mergeTwoLists(l9, l10)));
        // Expected: 1 -> 2 -> 3 -> 5 -> 7 -> 9 -> 11
    }
}
