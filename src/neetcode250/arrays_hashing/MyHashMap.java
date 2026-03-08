package neetcode250.arrays_hashing;
/*
 * Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
Example 1:

Input: ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]

Output: [null, null, null, 1, -1, null, 1, null, -1]
Explanation:
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1); // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3); // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2); // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2); // return -1 (i.e., not found), The map is now [[1,1]]

Constraints:

0 <= key, value <= 1,000,000
At most 10,000 calls will be made to put, get, and remove.
OperationAverage CaseWorst Caseput()O(1)O(n) — all keys collide into 1 bucketget()O(1)O(n) — traversing a long chainremove()O(1)O(n) — traversing a long chain

Average O(1) because with 1000 buckets and well-distributed keys, each bucket holds very few nodes.

Space Complexity: O(n + k)

n = number of key-value pairs stored
k = number of buckets (1000 in our case)
Total = O(n + 1000) = O(n)
 */
public class MyHashMap {
	
	class Node {
		int key, val;
		Node next;
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
			this.next = null;
		}
		
	}
	
	private int SIZE = 1000;
	Node[] buckets;
	public MyHashMap() {
		buckets = new Node[SIZE];
		for(int i = 0; i < SIZE; i++) {
			buckets[i] = new Node(-1, -1);
		}
	}
	
	public int getIndex(int key) {
		return key % 1000;
	}
	
	public void put(int key, int val) {
		int idx = getIndex(key);
		Node head = buckets[idx];
		Node current = head;
		while(current.next != null ) {
			if(current.next.key == key) {
				current.next.val = val;
				return;
			}
			current = current.next;
		}
		current.next = new Node(key, val);
	}
	
	public int get(int key) {
		int index = getIndex(key);       // Find the bucket index
        Node curr = buckets[index].next; // Skip dummy head

        while (curr != null) {
            if (curr.key == key) {
                return curr.val;       // Key found! Return value
            }
            curr = curr.next;
        }

        return -1; // Key not found
		
	}
	
	public void remove(int key) {
		 int index = getIndex(key);  // Find the bucket index
	        Node curr = buckets[index]; // Start from dummy head (prev pointer)

	        while (curr.next != null) {
	            if (curr.next.key == key) {
	                curr.next = curr.next.next; // Skip over the node to delete it
	                return;
	            }
	            curr = curr.next;
	        }
	}
	
	public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       DESIGN HASHMAP - DEMO            ");
        System.out.println("========================================\n");

        // ---- Scenario 1: Basic Put and Get ----
        System.out.println("--- Scenario 1: Basic Put and Get ---");
        MyHashMap map1 = new MyHashMap();
        map1.put(1, 10);
        map1.put(2, 20);
        map1.put(3, 30);
        System.out.println("put(1, 10), put(2, 20), put(3, 30)");
        System.out.println("get(1) => " + map1.get(1)); // Expected: 10
        System.out.println("get(2) => " + map1.get(2)); // Expected: 20
        System.out.println("get(3) => " + map1.get(3)); // Expected: 30
        System.out.println("get(9) => " + map1.get(9)); // Expected: -1 (not found)

        // ---- Scenario 2: Update existing key ----
        System.out.println("\n--- Scenario 2: Update Existing Key ---");
        MyHashMap map2 = new MyHashMap();
        map2.put(5, 50);
        System.out.println("put(5, 50) => get(5): " + map2.get(5)); // Expected: 50
        map2.put(5, 99);  // Update key 5
        System.out.println("put(5, 99) => get(5): " + map2.get(5)); // Expected: 99

        // ---- Scenario 3: Remove a key ----
        System.out.println("\n--- Scenario 3: Remove a Key ---");
        MyHashMap map3 = new MyHashMap();
        map3.put(7, 70);
        map3.put(8, 80);
        System.out.println("put(7, 70), put(8, 80)");
        System.out.println("get(7) => " + map3.get(7)); // Expected: 70
        map3.remove(7);
        System.out.println("After remove(7):");
        System.out.println("get(7) => " + map3.get(7)); // Expected: -1
        System.out.println("get(8) => " + map3.get(8)); // Expected: 80

        // ---- Scenario 4: Collision — same bucket ----
        System.out.println("\n--- Scenario 4: Hash Collision (key 1 and key 1001 → same bucket) ---");
        MyHashMap map4 = new MyHashMap();
        // key 1 % 1000 = 1, key 1001 % 1000 = 1 → SAME BUCKET!
        map4.put(1, 100);
        map4.put(1001, 200);
        System.out.println("put(1, 100), put(1001, 200) — both go to bucket index 1");
        System.out.println("get(1)    => " + map4.get(1));    // Expected: 100
        System.out.println("get(1001) => " + map4.get(1001)); // Expected: 200

        // ---- Scenario 5: Edge Cases ----
        System.out.println("\n--- Scenario 5: Edge Cases ---");
        MyHashMap map5 = new MyHashMap();
        System.out.println("get(0) on empty map => " + map5.get(0));  // Expected: -1
        map5.put(0, 0);
        System.out.println("put(0, 0) => get(0): " + map5.get(0));   // Expected: 0
        map5.remove(0);
        System.out.println("remove(0) => get(0): " + map5.get(0));   // Expected: -1

        // ---- Scenario 6: Large key ----
        System.out.println("\n--- Scenario 6: Large Key ---");
        MyHashMap map6 = new MyHashMap();
        map6.put(999999, 42);
        System.out.println("put(999999, 42) => bucket index: " + (999999 % 1000));
        System.out.println("get(999999) => " + map6.get(999999)); // Expected: 42

        System.out.println("\n========================================");
        System.out.println("         ALL TESTS COMPLETE             ");
        System.out.println("========================================");
    }

}
