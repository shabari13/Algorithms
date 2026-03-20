package neetcode250.linked_list;
/*
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
The problem asks us to remove a node counting from the end of the list, but we can only traverse forward. The naive way would be to walk the list once to count its length, then walk again to the right position — but we can do it in a single pass using the Two-Pointer (Fast & Slow) technique. We place two pointers on the list and push one of them (fast) exactly n+1 steps ahead. Then we march both pointers forward together. Since the gap between them is always n+1, the instant fast falls off the end (becomes null), slow will be sitting exactly one node before the node we want to delete. We then do a simple link skip to remove the target.

🧒 Explain It Like I'm 5
Imagine you have a train with 5 wagons: 1 → 2 → 3 → 4 → 5. You want to unhook wagon number 4 (the 2nd from the end).
You have two toy ants, a fast ant and a slow ant. Both start from a pretend wagon 0 (a dummy wagon we add at the front).

You first let the fast ant walk 3 steps (n+1 = 2+1 = 3). Fast ant lands on wagon 3. Slow ant stays at wagon 0.
Now both ants walk together one step at a time:

Step 1: Fast → wagon 4, Slow → wagon 1
Step 2: Fast → wagon 5, Slow → wagon 2
Step 3: Fast → falls off (null), Slow → wagon 3


The slow ant stopped right at wagon 3, which is just before wagon 4 (the one you want to remove). So you just disconnect wagon 4 and connect wagon 3 directly to wagon 5. Done!
ComplexityReasonTimeO(N)We traverse the list at most once (fast pointer walks from start to end)
SpaceO(1)Only two extra pointers are used, no extra data structures

 *
 **/

public class RemoveNthFromEnd {
	public static  ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;
		for(int i = 0; i <= n; i++) {
			fast = fast.next;
		}
		while(fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}
	   // ─── Helper: build a linked list from an int array ──────────────────────────
    static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }
 
    // ─── Helper: print a linked list ────────────────────────────────────────────
    static String listToString(ListNode head) {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.append("]").toString();
    }
 
    // ─── Main: multiple test cases ──────────────────────────────────────────────
    public static void main(String[] args) {
 
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     Remove N-th Node From End of Linked List         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
 
        // ── Test Case 1: Classic example ──────────────────────────────────────
        // List: 1 -> 2 -> 3 -> 4 -> 5,  n = 2
        // Remove 2nd from end (node with value 4)
        // Expected: 1 -> 2 -> 3 -> 5
        runTest("Test 1 — Remove 2nd from end",
                new int[]{1, 2, 3, 4, 5}, 2,
                "1 -> 2 -> 3 -> 5");
 
        // ── Test Case 2: Remove the head (1st from end in a 1-element list) ───
        // List: [1],  n = 1
        // Remove only element → empty list
        // Expected: []
        runTest("Test 2 — Single element list, remove head",
                new int[]{1}, 1,
                "");
 
        // ── Test Case 3: Remove the head of a multi-element list ──────────────
        // List: 1 -> 2,  n = 2
        // Remove 2nd from end = 1st from front = head
        // Expected: [2]
        runTest("Test 3 — Remove head of two-element list",
                new int[]{1, 2}, 2,
                "2");
 
        // ── Test Case 4: Remove the tail ──────────────────────────────────────
        // List: 1 -> 2 -> 3,  n = 1
        // Remove last node (value 3)
        // Expected: 1 -> 2
        runTest("Test 4 — Remove tail (last node)",
                new int[]{1, 2, 3}, 1,
                "1 -> 2");
 
        // ── Test Case 5: Longer list, remove middle ───────────────────────────
        // List: 10 -> 20 -> 30 -> 40 -> 50 -> 60,  n = 3
        // 3rd from end = node with value 40
        // Expected: 10 -> 20 -> 30 -> 50 -> 60
        runTest("Test 5 — Longer list, remove 3rd from end",
                new int[]{10, 20, 30, 40, 50, 60}, 3,
                "10 -> 20 -> 30 -> 50 -> 60");
    }
 
    static void runTest(String label, int[] vals, int n, String expected) {
        ListNode head   = build(vals);
        String   before = listToString(head);
        ListNode result = removeNthFromEnd(head, n);
        String   after  = listToString(result);
 
        System.out.println("─── " + label + " ───");
        System.out.println("  Input  : " + before + "  |  n = " + n);
        System.out.println("  Output : " + after);
        System.out.println("  Expect : [" + expected + "]");
        System.out.println("  Status : " + (after.equals("[" + expected + "]") ? "✅ PASS" : "❌ FAIL"));
        System.out.println();
    }
}
