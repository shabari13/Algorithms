package neetcode250.linked_list;

import java.util.HashMap;
import java.util.Map;

/*
 * You are given the head of a linked list of length n. Unlike a singly linked list, each node contains an additional pointer random, which may point to any node in the list, or null.

Create a deep copy of the list.

The deep copy should consist of exactly n new nodes, each including:

The original value val of the copied node
A next pointer to the new node corresponding to the next pointer of the original node
A random pointer to the new node corresponding to the random pointer of the original node
Note: None of the pointers in the new list should point to nodes in the original list.

Return the head of the copied linked list.

In the examples, the linked list is represented as a list of n nodes. Each node is represented as a pair of [val, random_index] where random_index is the index of the node (0-indexed) that the random pointer points to, or null if it does not point to any node.

Example 1:



Input: head = [[3,null],[7,3],[4,0],[5,1]]

Output: [[3,null],[7,3],[4,0],[5,1]]
Example 2:



Input: head = [[1,null],[2,2],[3,2]]

Output: [[1,null],[2,2],[3,2]]
Constraints:

0 <= n <= 100
-100 <= Node.val <= 100
random is null or is pointing to some node in the linked list.

The core idea: We solve this problem in two passes using a HashMap. In the first pass, we create a new copy of every node and 
store the mapping (original node → copied node) in the map — but we don't set any next or random pointers yet. 
In the second pass, we go through every original node again and use the map to wire up both the next and random pointers of the copied nodes. 
This works because by the time we set pointers, all copied nodes already exist in the map.

 * IDEA (Two-Pass HashMap):
 *   Pass 1 → Walk the original list. For every original node, create a brand-new
 *             copy node (same val) and store the mapping original→copy in a HashMap.
 *             We do NOT set next/random yet — we just birth every copy node first.
 *
 *   Pass 2 → Walk the original list again. For every original node:
 *             copy.next   = map.get(original.next)
 *             copy.random = map.get(original.random)
 *             This works because ALL copy nodes already exist in the map.
 *
 * TIME  COMPLEXITY: O(n) — we visit every node exactly twice.
 * SPACE COMPLEXITY: O(n) — the HashMap stores n entries (one per node).
 */
public class CopyRandomList {
	public static RandomNode copyRandomList(RandomNode head) {
		if(head == null)
			return null;
			
		Map<RandomNode, RandomNode> map = new HashMap<>();
		RandomNode curr = head;
		
		while(curr != null) {
			RandomNode copyNode = new RandomNode(curr.val);
			map.put(curr, copyNode);
			curr = curr.next;
		}
		curr = head;
		while(curr != null) {
			RandomNode copyNode = map.get(curr);
			copyNode.next = map.get(curr.next);
			copyNode.next = map.get(curr.random);
			curr = curr.next;
			
		}
		 return map.get(head);
	}
	
	  static RandomNode buildList(int[][] data) {
	        if (data == null || data.length == 0) return null;
	 
	        RandomNode[] nodes = new RandomNode[data.length];
	 
	        // Create all nodes first
	        for (int i = 0; i < data.length; i++) {
	            nodes[i] = new RandomNode(data[i][0]);
	        }
	        // Wire next and random
	        for (int i = 0; i < data.length; i++) {
	            if (i + 1 < data.length) nodes[i].next = nodes[i + 1];
	            int ri = data[i][1];
	            nodes[i].random = (ri == -1) ? null : nodes[ri];
	        }
	        return nodes[0];
	    }
	 
	    // ─────────────────────────────────────────────
	    // Helper: pretty-print the list
	    // ─────────────────────────────────────────────
	    static String listToString(RandomNode head) {
	        if (head == null) return "[]";
	 
	        // First, build an index map so we can print
	        // the random pointer's index number.
	        Map<RandomNode, Integer> indexMap = new HashMap<>();
	        RandomNode cur = head;
	        int idx = 0;
	        while (cur != null) {
	            indexMap.put(cur, idx++);
	            cur = cur.next;
	        }
	 
	        StringBuilder sb = new StringBuilder("[");
	        cur = head;
	        while (cur != null) {
	            int rIdx = cur.random == null ? -1 : indexMap.get(cur.random);
	            sb.append("[").append(cur.val).append(",").append(rIdx).append("]");
	            if (cur.next != null) sb.append(", ");
	            cur = cur.next;
	        }
	        sb.append("]");
	        return sb.toString();
	    }
	 
	    // ─────────────────────────────────────────────
	    // Main – multiple test cases with trace output
	    // ─────────────────────────────────────────────
	    public static void main(String[] args) {
	 
	        System.out.println("=".repeat(60));
	        System.out.println("  Copy Linked List With Random Pointer – Test Cases");
	        System.out.println("=".repeat(60));
	 
	        // ── TEST 1 ─────────────────────────────────
	        // List: 7 → 13 → 11 → 10 → 1
	        // Random: 7→null, 13→7, 11→1, 10→3, 1→0
	        //         (using 0-based indices)
	        System.out.println("\n─── Test 1 ───────────────────────────────────────");
	        System.out.println("Input: [[7,-1],[13,0],[11,4],[10,2],[1,0]]");
	        System.out.println("Expected: [[7,-1],[13,0],[11,4],[10,2],[1,0]]");
	 
	        int[][] t1 = {{7,-1},{13,0},{11,4},{10,2},{1,0}};
	        RandomNode head1 = buildList(t1);
	        RandomNode copy1 = copyRandomList(head1);
	 
	        System.out.println("Original : " + listToString(head1));
	        System.out.println("Deep Copy: " + listToString(copy1));
	 
	        // ── Detailed iteration trace for Test 1 ────
	        System.out.println("\n  [Trace – Pass 1: creating copy nodes]");
	        RandomNode trace = head1;
	        int i = 0;
	        Map<RandomNode, RandomNode> demoMap = new HashMap<>();
	        while (trace != null) {
	        	RandomNode copyNode = new RandomNode(trace.val);
	            demoMap.put(trace, copyNode);
	            System.out.printf("    Step %d: Original node(val=%d) → new copy node(val=%d) created%n",
	                    i, trace.val, copyNode.val);
	            i++;
	            trace = trace.next;
	        }
	 
	        System.out.println("\n  [Trace – Pass 2: wiring next and random]");
	        trace = head1;
	        i = 0;
	        while (trace != null) {
	        	RandomNode copyNode = demoMap.get(trace);
	            copyNode.next   = demoMap.get(trace.next);
	            copyNode.random = demoMap.get(trace.random);
	 
	            String nextVal   = (copyNode.next   == null) ? "null" : String.valueOf(copyNode.next.val);
	            String randomVal = (copyNode.random == null) ? "null" : String.valueOf(copyNode.random.val);
	            System.out.printf("    Step %d: copy(val=%d) → next=%s, random=%s%n",
	                    i, copyNode.val, nextVal, randomVal);
	            i++;
	            trace = trace.next;
	        }
	 
	        // ── TEST 2 ─────────────────────────────────
	        System.out.println("\n─── Test 2 ───────────────────────────────────────");
	        System.out.println("Input: [[1,1],[2,1]]");
	        System.out.println("Expected: [[1,1],[2,1]]");
	 
	        int[][] t2 = {{1,1},{2,1}};
	        RandomNode head2 = buildList(t2);
	        RandomNode copy2 = copyRandomList(head2);
	        System.out.println("Original : " + listToString(head2));
	        System.out.println("Deep Copy: " + listToString(copy2));
	 
	        // ── TEST 3 ─────────────────────────────────
	        System.out.println("\n─── Test 3 ───────────────────────────────────────");
	        System.out.println("Input: [[3,-1],[3,0],[3,-1]]");
	        System.out.println("Expected: [[3,-1],[3,0],[3,-1]]");
	 
	        int[][] t3 = {{3,-1},{3,0},{3,-1}};
	        RandomNode head3 = buildList(t3);
	        RandomNode copy3 = copyRandomList(head3);
	        System.out.println("Original : " + listToString(head3));
	        System.out.println("Deep Copy: " + listToString(copy3));
	 
	        // ── TEST 4 – edge case: single node ────────
	        System.out.println("\n─── Test 4 (single node, random→itself) ─────────");
	        System.out.println("Input: [[42,0]]");
	        System.out.println("Expected: [[42,0]]");
	 
	        int[][] t4 = {{42,0}};
	        RandomNode head4 = buildList(t4);
	        RandomNode copy4 = copyRandomList(head4);
	        System.out.println("Original : " + listToString(head4));
	        System.out.println("Deep Copy: " + listToString(copy4));
	 
	        // ── TEST 5 – edge case: null input ─────────
	        System.out.println("\n─── Test 5 (null / empty list) ──────────────────");
	        System.out.println("Input: []");
	        System.out.println("Expected: []");
	 
	        RandomNode copy5 = copyRandomList(null);
	        System.out.println("Original : []");
	        System.out.println("Deep Copy: " + listToString(copy5));
	 
	        // ── Deep copy independence check ────────────
	        System.out.println("\n─── Independence check (Test 1) ──────────────────");
	        System.out.println("Mutating original head val to 999...");
	        head1.val = 999;
	        System.out.println("Original head val : " + head1.val);
	        System.out.println("Copy head val     : " + copy1.val + "  (unchanged ✔)");
	 
	        System.out.println("\n" + "=".repeat(60));
	        System.out.println("  Complexity Summary");
	        System.out.println("  Time  : O(n) – two single passes over n nodes");
	        System.out.println("  Space : O(n) – HashMap stores one entry per node");
	        System.out.println("=".repeat(60));
	    }
}
