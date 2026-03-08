package neetcode250.arrays_hashing;
/*
 * Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
Example 1:

Input: ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]

Output: [null, null, null, true, false, null, true, null, false]
Explanation:
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1); // set = [1]
myHashSet.add(2); // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2); // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2); // set = [1]
myHashSet.contains(2); // return False, (already removed)

Constraints:

0 <= key <= 1,000,000
At most 10,000 calls will be made to add, remove, and contains.
A HashSet stores unique values and supports three operations: add, remove, and contains.
 Instead of using Java's built-in HashSet, we implement it from scratch using array-based chaining (open hashing). We create a fixed-size array of "buckets"
  (linked lists). When a value comes in, we compute value % bucketCount to find which bucket it belongs to. All values that map to the same bucket are stored in a linked list 
  inside that bucket. This handles collisions
 (when two values map to the same index). Add checks for duplicates before inserting, remove walks the list to unlink the node, and contains just scans the list.
 Average CaseWorst CaseaddO(1)O(n)removeO(1)O(n)containsO(1)O(n)SpaceO(B + n) = O(n)—

Average O(1): with 1009 buckets and uniform distribution, each list has ~1 node
Worst O(n): if all keys collide into one bucket (e.g., all multiples of 1009)
Space: B = 1009 fixed bucket array + n Node objects total = O(n)
 */
public class MyHashSet {
	
	private final int  SIZE = 1009;
	Node[] buckets;
	MyHashSet() {
		buckets = new Node[SIZE];
	}
	public int hash(int key) {
		return key % SIZE;
	}
	public void add(int key) {
		int idx = hash(key);
		if(buckets[idx] == null) {
			buckets[idx] = new Node(key);
			return;
		}
		Node current = buckets[idx];
		while(current != null) {
			if(current.val == key) return;
			if(current.next == null) break;
			current = current.next;
		}
		current.next = new Node(key);
	}
	public void remove(int key) {
	    int idx = hash(key);
	    if (buckets[idx] == null) return;

	    // 🔴 YOU MISSED THIS: what if the HEAD node is the one to remove?
	    if (buckets[idx].val == key) {
	        buckets[idx] = buckets[idx].next;  // move head forward
	        return;
	    }

	    // Now handle all OTHER nodes (non-head)
	    Node prev = buckets[idx];
	    Node current = prev.next;              // start current at 2nd node

	    while (current != null) {
	        if (current.val == key) {
	            prev.next = current.next;
	            return;
	        }
	        prev = current;
	        current = current.next;
	    }
	}
	public boolean contains(int key) {
		int idx = hash(key);
		if(buckets[idx] == null) return false;
		Node current = buckets[idx];
		while(current != null) {
			if(current.val == key) {
				return true;
			}
			current  = current.next;
		}
		
		return false;
	}
	
	 public static void main(String[] args) {

	        System.out.println("========================================");
	        System.out.println("   DESIGN HASHSET — Demo & Test Cases  ");
	        System.out.println("========================================\n");

	        // ── TEST CASE 1: LeetCode's own example ─────────────────
	        System.out.println("--- Test Case 1: LeetCode Example ---");
	        MyHashSet hs1 = new MyHashSet();

	        hs1.add(1);
	        System.out.println("add(1)");

	        hs1.add(2);
	        System.out.println("add(2)");

	        System.out.println("contains(1) → " + hs1.contains(1));  // true
	        System.out.println("contains(3) → " + hs1.contains(3));  // false

	        hs1.add(2);                                               // duplicate: ignored
	        System.out.println("add(2) again (duplicate, should be ignored)");
	        System.out.println("contains(2) → " + hs1.contains(2));  // true

	        hs1.remove(2);
	        System.out.println("remove(2)");
	        System.out.println("contains(2) → " + hs1.contains(2));  // false

	        // ── TEST CASE 2: Remove head node ───────────────────────
	        System.out.println("\n--- Test Case 2: Remove the head node ---");
	        MyHashSet hs2 = new MyHashSet();
	        hs2.add(10);
	        hs2.add(20);
	        hs2.add(30);
	        System.out.println("add(10), add(20), add(30)");
	        System.out.println("contains(10) → " + hs2.contains(10)); // true
	        hs2.remove(10);
	        System.out.println("remove(10)");
	        System.out.println("contains(10) → " + hs2.contains(10)); // false
	        System.out.println("contains(20) → " + hs2.contains(20)); // true
	        System.out.println("contains(30) → " + hs2.contains(30)); // true

	        // ── TEST CASE 3: Collision handling ─────────────────────
	        // Keys 0 and 1009 both hash to bucket index 0 (0%1009=0, 1009%1009=0)
	        System.out.println("\n--- Test Case 3: Collision (0 and 1009 share bucket 0) ---");
	        MyHashSet hs3 = new MyHashSet();
	        hs3.add(0);
	        hs3.add(1009);   // same bucket as 0
	        System.out.println("add(0), add(1009)");
	        System.out.println("contains(0)    → " + hs3.contains(0));    // true
	        System.out.println("contains(1009) → " + hs3.contains(1009)); // true
	        hs3.remove(0);
	        System.out.println("remove(0)");
	        System.out.println("contains(0)    → " + hs3.contains(0));    // false
	        System.out.println("contains(1009) → " + hs3.contains(1009)); // true

	        // ── TEST CASE 4: Remove non-existent key ────────────────
	        System.out.println("\n--- Test Case 4: Remove a key that was never added ---");
	        MyHashSet hs4 = new MyHashSet();
	        hs4.add(5);
	        System.out.println("add(5)");
	        hs4.remove(99);   // should do nothing
	        System.out.println("remove(99) — key doesn't exist, no error");
	        System.out.println("contains(5)  → " + hs4.contains(5));  // true
	        System.out.println("contains(99) → " + hs4.contains(99)); // false

	        // ── TEST CASE 5: Edge — key = 0 ─────────────────────────
	        System.out.println("\n--- Test Case 5: Edge case — key = 0 ---");
	        MyHashSet hs5 = new MyHashSet();
	        System.out.println("contains(0) before add → " + hs5.contains(0)); // false
	        hs5.add(0);
	        System.out.println("add(0)");
	        System.out.println("contains(0) after add  → " + hs5.contains(0)); // true
	        hs5.remove(0);
	        System.out.println("remove(0)");
	        System.out.println("contains(0) after remove → " + hs5.contains(0)); // false

	        // ── TEST CASE 6: Large key near max constraint ───────────
	        System.out.println("\n--- Test Case 6: Large key (10^6) ---");
	        MyHashSet hs6 = new MyHashSet();
	        hs6.add(1000000);
	        System.out.println("add(1000000)");
	        System.out.println("contains(1000000) → " + hs6.contains(1000000)); // true
	        hs6.remove(1000000);
	        System.out.println("remove(1000000)");
	        System.out.println("contains(1000000) → " + hs6.contains(1000000)); // false

	        System.out.println("\n========================================");
	        System.out.println("           All Tests Complete!          ");
	        System.out.println("========================================");
	    }

}
