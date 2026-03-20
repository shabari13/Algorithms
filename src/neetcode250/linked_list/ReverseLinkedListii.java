package neetcode250.linked_list;
/*
 * You are given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right (1-indexed), and return the reversed list.

Example 1:



Input: head = [1,2,3,4,5], left = 1, right = 3

Output: [3,2,1,4,5]
Example 2:

Input: head = [1,1], left = 1, right = 1

Output: [1,1]
Constraints:

The number of nodes in the list is n.
1 <= n <= 500.
-500 <= Node.val <= 500
1 <= left <= right <= n
Follow up: Could you do it in one pass?
💡 The Idea (for grown-ups)
We use a dummy node + four-pointer technique. We locate the node just before position left, then perform an in-place reversal of exactly right - left nodes by repeatedly plucking the next node and inserting it right after the prev pointer. This avoids a two-pass approach and handles all edge cases cleanly in a single traversal.

🧒 Explain Like I'm 5
Imagine you have a train 🚂 with carriages numbered 1, 2, 3, 4, 5. You want to flip carriages 2 through 4 so they go 4, 3, 2. You don't rebuild the whole train — you just unhook carriage 4 from behind and snap it in front of carriage 2, then do the same with 3. You keep doing this until the middle section is reversed. The engine (carriage 1) and the tail (carriage 5) never move!


⏱️ Complexity
Time — O(n): We walk to position left (up to n steps), then perform right - left pointer swaps (also at most n). Total is proportional to right, which in the worst case equals n.
Space — O(1): We only allocate 4 pointer variables (dummy, prev, curr, next) regardless of list size. No recursion stack, no extra arrays — pure in-place manipulation.

 */

// ----------------------------------------------------------
//  CORE SOLUTION
// ----------------------------------------------------------
/**
 * Reverses nodes from position 'left' to 'right' (1-indexed)
 * in a single pass using the "insert-at-front" technique.
 *
 * Pointers we maintain:
 *   dummy  → a fake node placed before the head so we never
 *            have to special-case changes to the real head.
 *   prev   → the node just BEFORE position 'left'.
 *            It never moves after we find it.
 *   curr   → starts at position 'left'; after each iteration
 *            it stays pointing at the "tail" of the
 *            already-reversed segment.
 *   next   → the node we are about to move (always curr.next).
 *
 * Each iteration we:
 *   1. Save next = curr.next
 *   2. Skip 'next' out of its current position (curr.next = next.next)
 *   3. Insert 'next' right after 'prev'   (next.next = prev.next;
 *                                           prev.next = next)
 * We repeat this (right - left) times.
 */
public class ReverseLinkedListii {
	public static ListNode reverseBetween(ListNode head, int left, int right) {
		 // STEP 1 ─────────────────────────────────────────────
        // Create a dummy node that sits before the real head.
        // This lets us treat every node (including head) the
        // same way — no special cases.
        //
        //   dummy → 1 → 2 → 3 → 4 → 5 → null
        //
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		// STEP 2 ─────────────────────────────────────────────
        // Walk 'prev' forward (left - 1) steps so it lands on
        // the node just BEFORE the reversal zone.
        //
        // Example: left = 2  →  move prev 1 step
        //   dummy → [1] → 2 → 3 → 4 → 5
        //            ^
        //           prev
        //
		for(int i = 0 ; i < left -1 ; i ++) {
			prev = prev.next;
		}
		 // STEP 3 ─────────────────────────────────────────────
        // 'curr' begins at position 'left'.
        // It will always be the tail of the reversed segment.
        //
        //   dummy → 1 → [2] → 3 → 4 → 5
        //           ^    ^
        //          prev curr
        //
		ListNode curr = prev.next;
		
		// STEP 4 ─────────────────────────────────────────────
        // Perform (right - left) node-pluck-and-insert moves.
        //
        // Each move takes the node AFTER curr and inserts it
        // directly AFTER prev.
        //
		for(int i = 0 ; i < right - left; i++) {
			ListNode next = curr.next;
			curr.next = next.next;
			next.next = prev.next;
			prev.next = next;
		}
		return dummy.next;
	}
	
	/** Build a linked list from an int array. */
    static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }
 
    /** Convert a linked list to a readable string like [1 → 2 → 3]. */
    static String listToString(ListNode head) {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) sb.append(" → ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
 
    /** Run one test case and print the result. */
    static void runTest(String label, int[] vals, int left, int right) {
        ListNode head   = build(vals);
        String   before = listToString(head);
        ListNode result = reverseBetween(head, left, right);
        System.out.printf("%-30s  left=%-2d right=%-2d  Before: %-30s  After: %s%n",
                label, left, right, before, listToString(result));
    }
 
    // ----------------------------------------------------------
    //  MAIN — Sample & Edge-Case Tests
    // ----------------------------------------------------------
    public static void main(String[] args) {
 
        System.out.println("=".repeat(110));
        System.out.println("  Reverse Linked List II — Test Results");
        System.out.println("=".repeat(110));
 
        // Test 1: Classic example from LeetCode
        runTest("Classic (L=2, R=4)",
                new int[]{1, 2, 3, 4, 5}, 2, 4);
        // Expected: [1 → 4 → 3 → 2 → 5]
 
        // Test 2: Reverse entire list
        runTest("Full reverse (L=1, R=5)",
                new int[]{1, 2, 3, 4, 5}, 1, 5);
        // Expected: [5 → 4 → 3 → 2 → 1]
 
        // Test 3: Single node — no change
        runTest("Single node list (L=1, R=1)",
                new int[]{42}, 1, 1);
        // Expected: [42]
 
        // Test 4: left == right — no change
        runTest("left == right (L=3, R=3)",
                new int[]{1, 2, 3, 4, 5}, 3, 3);
        // Expected: [1 → 2 → 3 → 4 → 5]
 
        // Test 5: Reverse starting from head
        runTest("Reverse from head (L=1, R=3)",
                new int[]{1, 2, 3, 4, 5}, 1, 3);
        // Expected: [3 → 2 → 1 → 4 → 5]
 
        // Test 6: Reverse ending at tail
        runTest("Reverse to tail (L=3, R=5)",
                new int[]{1, 2, 3, 4, 5}, 3, 5);
        // Expected: [1 → 2 → 5 → 4 → 3]
 
        // Test 7: Two-element list, reverse both
        runTest("Two elements (L=1, R=2)",
                new int[]{7, 9}, 1, 2);
        // Expected: [9 → 7]
 
        // Test 8: Longer list
        runTest("Longer list (L=2, R=7)",
                new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 7);
        // Expected: [1 → 7 → 6 → 5 → 4 → 3 → 2 → 8]
 
        System.out.println("=".repeat(110));
 
        // ── Detailed iteration trace for Test 1 ─────────────
        System.out.println("\n  DETAILED ITERATION TRACE  —  [1,2,3,4,5], left=2, right=4");
        System.out.println("-".repeat(70));
        System.out.println("  Initial list : [1 → 2 → 3 → 4 → 5]");
        System.out.println("  dummy → 1 → 2 → 3 → 4 → 5 → null");
        System.out.println();
        System.out.println("  STEP 2: Walk prev (left-1 = 1 step)");
        System.out.println("    prev = node(1)");
        System.out.println();
        System.out.println("  STEP 3: curr = prev.next = node(2)");
        System.out.println("    State: dummy → 1(prev) → 2(curr) → 3 → 4 → 5");
        System.out.println();
        System.out.println("  STEP 4: Loop runs (right-left = 2) times");
        System.out.println();
        System.out.println("  ─ Iteration i=0 ─");
        System.out.println("    next  = curr.next       → node(3)");
        System.out.println("    curr.next = next.next   → curr(2).next = node(4)");
        System.out.println("    next.next = prev.next   → node(3).next = node(2)  [prev.next was 2]");
        System.out.println("    prev.next = next        → node(1).next = node(3)");
        System.out.println("    State: dummy → 1(prev) → 3 → 2(curr) → 4 → 5");
        System.out.println();
        System.out.println("  ─ Iteration i=1 ─");
        System.out.println("    next  = curr.next       → node(4)");
        System.out.println("    curr.next = next.next   → curr(2).next = node(5)");
        System.out.println("    next.next = prev.next   → node(4).next = node(3)  [prev.next was 3]");
        System.out.println("    prev.next = next        → node(1).next = node(4)");
        System.out.println("    State: dummy → 1(prev) → 4 → 3 → 2(curr) → 5");
        System.out.println();
        System.out.println("  STEP 5: return dummy.next → Final: [1 → 4 → 3 → 2 → 5]  ✓");
        System.out.println("-".repeat(70));
 
        // ── Complexity Summary ───────────────────────────────
        System.out.println("\n  COMPLEXITY");
        System.out.println("-".repeat(40));
        System.out.println("  Time  : O(right)   — one pass to reach 'left', then");
        System.out.println("          (right-left) swaps; overall proportional to 'right'.");
        System.out.println("          In the worst case (right = n), this is O(n).");
        System.out.println("  Space : O(1)       — only a constant number of pointers");
        System.out.println("          (dummy, prev, curr, next); no extra data structures.");
        System.out.println("=".repeat(110));
    }
}
