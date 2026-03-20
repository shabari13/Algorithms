package neetcode250.linked_list;
/*
 * Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.

Example 1:

Input: head = [0,1,2,3]

Output: [3,2,1,0]
Example 2:

Input: head = []

Output: []
Constraints:

0 <= The length of the list <= 1000.
-1000 <= Node.val <= 1000

The Big Idea
Imagine you have a chain of paper clips connected together, each one pointing to the next. To reverse the chain, you walk through it one clip at a time — before you move forward, you "flip" the current clip to point backward instead. You use three "fingers" to keep track: one holds the previous clip, one holds the current clip, and one peeks at the next clip so you don't lose the rest of the chain. By the time you reach the end, every clip is pointing the other way.

Time & Space Complexity
Time complexity: O(n) — you visit every node exactly once. Whether the list has 5 nodes or 5 million, you touch each one a constant number of times (save next, flip pointer, advance). There's no inner loop and no backtracking.
Space complexity: O(1) — you only ever use three extra pointer variables (prev, curr, nextTemp). No matter how big the list grows, the amount of extra memory you use stays flat. You're rearranging the existing nodes in place rather than creating a new list.


 */
public class ReverseLinkedList {

	public static Node reverse(Node head) {
		Node prev = null;
		Node current = head;
		while(current != null) {
			Node temp = current.next;
			current.next =  prev;
			prev = current;
			current = temp;
			
		}
		return prev;	
	}
	
	 static Node buildList(int[] values) {
	        if (values.length == 0) return null;
	        Node head = new Node(values[0]);
	        Node curr = head;
	        for (int i = 1; i < values.length; i++) {
	            curr.next = new Node(values[i]);
	            curr = curr.next;
	        }
	        return head;
	    }

	    // -------------------------------------------------------
	    // HELPER: Print a linked list as  1 -> 2 -> 3 -> null
	    // -------------------------------------------------------
	    static String listToString(Node head) {
	        StringBuilder sb = new StringBuilder();
	        Node curr = head;
	        while (curr != null) {
	            sb.append(curr.val).append(" -> ");
	            curr = curr.next;
	        }
	        sb.append("null");
	        return sb.toString();
	    }

	    // -------------------------------------------------------
	    // MAIN: Test with several different inputs
	    // -------------------------------------------------------
	    public static void main(String[] args) {

	        // --- Test 1: Normal list ---
	        int[] input1 = {1, 2, 3, 4, 5};
	        Node list1 = buildList(input1);
	        System.out.println("Original : " + listToString(list1));
	        list1 = reverse(list1);
	        System.out.println("Reversed : " + listToString(list1));

	        System.out.println();

	        // --- Test 2: Two elements ---
	        int[] input2 = {10, 20};
	        Node list2 = buildList(input2);
	        System.out.println("Original : " + listToString(list2));
	        list2 = reverse(list2);
	        System.out.println("Reversed : " + listToString(list2));

	        System.out.println();

	        // --- Test 3: Single element (nothing changes) ---
	        int[] input3 = {42};
	        Node list3 = buildList(input3);
	        System.out.println("Original : " + listToString(list3));
	        list3 = reverse(list3);
	        System.out.println("Reversed : " + listToString(list3));

	        System.out.println();

	        // --- Test 4: Empty list (null) ---
	        Node list4 = null;
	        System.out.println("Original : " + listToString(list4));
	        list4 = reverse(list4);
	        System.out.println("Reversed : " + listToString(list4));
	    }
}
