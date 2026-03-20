package neetcode250.linked_list;
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
Constraints:

1 <= l1.length, l2.length <= 100.
0 <= Node.val <= 9
The Idea
The Add Two Numbers problem gives you two non-empty linked lists where each node stores a single digit of a number in reverse order (so 2 → 4 → 3 means 342). You add them digit-by-digit, just like how you do long addition on paper — starting from the ones place, moving left, carrying a 1 whenever the sum of two digits exceeds 9. The result is also stored as a reversed linked list.

Like You're 5 Years Old
Imagine you have two trains of single-digit cars driving backwards. You line them up side by side and add the cars one by one. If the cars add up to, say, 17, you write down 7 and "carry" a tiny 1 to give to the next pair of cars. You keep doing this until both trains run out of cars (and don't forget to make a new car if you still have a leftover carry at the end!).

Time and Space Complexity
Time complexity — O(max(m, n)) where m and n are the lengths of the two lists. The loop visits each node at most once, 
plus at most one extra iteration if a final carry exists.
Space complexity — O(max(m, n)) for the new result list. The output has at most max(m, n) + 1 nodes (the +1 accounts for a possible final carry digit).
 No extra data structures are used beyond the output itself.
 *
 */
public class AddTwoNumber {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		int carry = 0;
		while(l1 != null || l2 != null || carry != 0) {
			int num1 = (l1 != null) ? l1.val : 0;
			int num2 = (l2 != null) ? l2.val : 0;
			int sum = num1 + num2 + carry;
			int digit = sum % 10;
			carry = sum / 10;
			ListNode newNode = new ListNode(digit);
			current.next = newNode; 
			 if (l1 != null) l1 = l1.next;
			 if (l2 != null) l2 = l2.next;
			current = current.next;
			
		}
		return dummy.next;
	}
	
	  // Build a linked list from an array: {2,4,3} → 2→4→3
    static ListNode buildList(int[] digits) {
        ListNode dummy = new ListNode(0);
        ListNode cur   = dummy;
        for (int d : digits) {
            cur.next = new ListNode(d);
            cur      = cur.next;
        }
        return dummy.next;
    }

    // Print a linked list as  val1 -> val2 -> ... -> null
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        sb.append(" -> null");
        System.out.println(sb);
    }

    // ── Main: multiple test cases ─────────────────────────────────────
    public static void main(String[] args) {

        // ── Test 1: 342 + 465 = 807 ──
        System.out.println("Test 1: 342 + 465 = 807");
        ListNode l1 = buildList(new int[]{2, 4, 3});   // represents 342
        ListNode l2 = buildList(new int[]{5, 6, 4});   // represents 465
        System.out.print("  L1:     "); printList(l1);
        System.out.print("  L2:     "); printList(l2);
        System.out.print("  Result: "); printList(addTwoNumbers(l1, l2));

        // ── Test 2: 0 + 0 = 0 ──
        System.out.println("\nTest 2: 0 + 0 = 0");
        l1 = buildList(new int[]{0});
        l2 = buildList(new int[]{0});
        System.out.print("  L1:     "); printList(l1);
        System.out.print("  L2:     "); printList(l2);
        System.out.print("  Result: "); printList(addTwoNumbers(l1, l2));

        // ── Test 3: 9999999 + 9999 = 10009998 (carry chain) ──
        System.out.println("\nTest 3: 9,999,999 + 9,999 = 10,009,998");
        l1 = buildList(new int[]{9,9,9,9,9,9,9});
        l2 = buildList(new int[]{9,9,9,9});
        System.out.print("  L1:     "); printList(l1);
        System.out.print("  L2:     "); printList(l2);
        System.out.print("  Result: "); printList(addTwoNumbers(l1, l2));

        // ── Test 4: unequal lengths — 99 + 1 = 100 ──
        System.out.println("\nTest 4: 99 + 1 = 100");
        l1 = buildList(new int[]{9, 9});   // represents 99
        l2 = buildList(new int[]{1});      // represents 1
        System.out.print("  L1:     "); printList(l1);
        System.out.print("  L2:     "); printList(l2);
        System.out.print("  Result: "); printList(addTwoNumbers(l1, l2));
    }
}
