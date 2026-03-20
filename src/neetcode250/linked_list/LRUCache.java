package neetcode250.linked_list;

import java.util.HashMap;
import java.util.Map;

/*
 * LRU Cache
Medium
Topics
Company Tags
Hints
Implement the Least Recently Used (LRU) cache class LRUCache. The class should support the following operations

LRUCache(int capacity) Initialize the LRU cache of size capacity.
int get(int key) Return the value corresponding to the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the introduction of the new pair causes the cache to exceed its capacity, remove the least recently used key.
A key is considered used if a get or a put operation is called on it.

Ensure that get and put each run in 
O
(
1
)
O(1) average time complexity.

Example 1:

Input:
["LRUCache", [2], "put", [1, 10],  "get", [1], "put", [2, 20], "put", [3, 30], "get", [2], "get", [1]]

Output:
[null, null, 10, null, null, 20, -1]

Explanation:
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 10);  // cache: {1=10}
lRUCache.get(1);      // return 10
lRUCache.put(2, 20);  // cache: {1=10, 2=20}
lRUCache.put(3, 30);  // cache: {2=20, 3=30}, key=1 was evicted
lRUCache.get(2);      // returns 20 
lRUCache.get(1);      // return -1 (not found)
Constraints:

1 <= capacity <= 100
0 <= key <= 1000
0 <= value <= 1000
The Idea Behind the Solution
An LRU (Least Recently Used) cache is a data structure that holds a limited number of items and automatically evicts the least recently used one when it's full. To do this efficiently in O(1) time for both get and put, we combine two data structures: a HashMap (for instant key lookups) and a Doubly Linked List (to track usage order — the most recently used item stays at the front, and the least recently used item stays at the tail, ready to be kicked out).

Like You're 5 Years Old 🧒
Imagine you have a small toy shelf that can only hold 3 toys. Every time you play with a toy, you put it at the front of the shelf (most loved). When the shelf is full and you get a new toy, you throw away the toy at the very back (the one you haven't touched in the longest time) to make room. The HashMap is like a label on each toy so you can find any toy instantly without searching the whole shelf.
Time & Space Complexity
get(key) — O(1). HashMap lookup is O(1); removeNode and insertAfterHead each touch exactly 4 pointers, which is O(1).
put(key, value) — O(1). HashMap insert/delete is amortized O(1); all DLL pointer rewiring is constant work regardless of cache size.
Space — O(capacity). The HashMap and the DLL each hold at most capacity real nodes. The two dummy sentinel nodes are constant overhead.
 *
 */

public class LRUCache {
	class Node {
		int key; int val;
		Node next; Node prev;
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
	int capacity;
	Map<Integer, Node> map;
	Node head; Node tail;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		this.head = new Node(0,0);
		this.tail = new Node(0,0);
		head.next = tail;
		tail.prev = head;
	}
	
	public int get(int key) {
		if(!map.containsKey(key))
			return -1;
		Node node = map.get(key);
		removeNode(node);
		moveToFront(node);
		return node.val;
	}
	
	public void put(int key, int val) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			node.val = val;
			moveToFront(node);
			removeNode(node);
		} else {
			Node newNode = new Node(key, val);
			map.put(key, newNode);
			if(map.size() > capacity) {
				Node lru = tail.prev;
				removeNode(lru);
				map.remove(lru.key);
			}
			moveToFront(newNode);
		}
	}
	
	public void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	public void moveToFront(Node node) {
		node.next = head.next;
		head.next.prev = node;
		node.prev = head;
		head.next = node;
	}
	

	  public static void main(String[] args) {
	        System.out.println("=== Test Case 1: Basic Operations ===");
	        LRUCache cache1 = new LRUCache(2);
	        
	        System.out.println("put(1, 1)");
	        cache1.put(1, 1);
	        System.out.println("Cache state: {1=1}");
	        
	        System.out.println("\nput(2, 2)");
	        cache1.put(2, 2);
	        System.out.println("Cache state: {1=1, 2=2}");
	        
	        System.out.println("\nget(1)");
	        System.out.println("Output: " + cache1.get(1));
	        System.out.println("Cache state: {2=2, 1=1} (1 is now most recent)");
	        
	        System.out.println("\nput(3, 3)");
	        cache1.put(3, 3);
	        System.out.println("Output: evicts key 2");
	        System.out.println("Cache state: {1=1, 3=3}");
	        
	        System.out.println("\nget(2)");
	        System.out.println("Output: " + cache1.get(2));
	        System.out.println("Explanation: Returns -1 (not found)");
	        
	        System.out.println("\nput(4, 4)");
	        cache1.put(4, 4);
	        System.out.println("Output: evicts key 1");
	        System.out.println("Cache state: {3=3, 4=4}");
	        
	        System.out.println("\nget(1)");
	        System.out.println("Output: " + cache1.get(1));
	        
	        System.out.println("\nget(3)");
	        System.out.println("Output: " + cache1.get(3));
	        
	        System.out.println("\nget(4)");
	        System.out.println("Output: " + cache1.get(4));
	        
	        System.out.println("\n\n=== Test Case 2: Update Existing Key ===");
	        LRUCache cache2 = new LRUCache(2);
	        
	        System.out.println("put(1, 10)");
	        cache2.put(1, 10);
	        
	        System.out.println("put(2, 20)");
	        cache2.put(2, 20);
	        
	        System.out.println("put(1, 15) - updating key 1");
	        cache2.put(1, 15);
	        System.out.println("Cache state: {2=20, 1=15}");
	        
	        System.out.println("\nget(1)");
	        System.out.println("Output: " + cache2.get(1));
	        
	        System.out.println("\n\n=== Test Case 3: Single Capacity ===");
	        LRUCache cache3 = new LRUCache(1);
	        
	        System.out.println("put(5, 50)");
	        cache3.put(5, 50);
	        
	        System.out.println("get(5)");
	        System.out.println("Output: " + cache3.get(5));
	        
	        System.out.println("\nput(6, 60) - evicts key 5");
	        cache3.put(6, 60);
	        
	        System.out.println("get(5)");
	        System.out.println("Output: " + cache3.get(5));
	        
	        System.out.println("get(6)");
	        System.out.println("Output: " + cache3.get(6));
	    }
}
