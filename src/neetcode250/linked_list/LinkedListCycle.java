package neetcode250.linked_list;
/**
 * Returns true if the linked list has a cycle.
 *
 * How it works:
 *  - slow moves 1 step at a time.
 *  - fast moves 2 steps at a time.
 *  - If they ever point to the same node → cycle exists.
 *  - If fast reaches null → no cycle.
 */
public class LinkedListCycle {

	public static boolean hasCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast !=null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) {
				return true;
			}
		}
		return false;
	}
	 // ─── Helper: build a list with a cycle ─────────────────────────
    static ListNode buildCyclicList(int[] values, int cycleStart) {
        if (values.length == 0) return null;
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i < values.length - 1; i++) nodes[i].next = nodes[i + 1];
        if (cycleStart >= 0) nodes[values.length - 1].next = nodes[cycleStart]; // Attach tail → cycle
        return nodes[0];
    }

    // ─── Helper: build a plain list (no cycle) ─────────────────────
    static ListNode buildList(int[] values) {
        return buildCyclicList(values, -1); // -1 means no cycle
    }

    // ─── Main: run different test cases ────────────────────────────
    public static void main(String[] args) {

        // ── Test 1: Cycle at index 1 → 1→2→3→4→5→(back to 2)
        ListNode list1 = buildCyclicList(new int[]{1, 2, 3, 4, 5}, 1);
        System.out.println("Test 1 (cycle at index 1): " + hasCycle(list1)); // true

        // ── Test 2: No cycle → 1→2→3→4→null
        ListNode list2 = buildList(new int[]{1, 2, 3, 4});
        System.out.println("Test 2 (no cycle):         " + hasCycle(list2)); // false

        // ── Test 3: Single node, self-loop → 1→(back to 1)
        ListNode list3 = new ListNode(1);
        list3.next = list3;
        System.out.println("Test 3 (self-loop):        " + hasCycle(list3)); // true

        // ── Test 4: Single node, no loop → 1→null
        ListNode list4 = new ListNode(1);
        System.out.println("Test 4 (single, no loop):  " + hasCycle(list4)); // false

        // ── Test 5: Cycle at the very tail → 1→2→3→(back to 3)
        ListNode list5 = buildCyclicList(new int[]{1, 2, 3}, 2);
        System.out.println("Test 5 (tail cycle):       " + hasCycle(list5)); // true

        // ── Test 6: Two nodes with cycle → 1→2→(back to 1)
        ListNode list6 = buildCyclicList(new int[]{1, 2}, 0);
        System.out.println("Test 6 (two-node cycle):   " + hasCycle(list6)); // true

        // ── Test 7: Null/empty list
        System.out.println("Test 7 (null list):        " + hasCycle(null)); // false
    }
}

