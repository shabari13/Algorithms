package neetcode150.linked_list;

import java.util.HashMap;
import java.util.Map;
/*
 * Implement the Least Recently Used (LRU) cache class LRUCache. The class should support the following operations

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
 * Time Complexity

get(key): O(1) - Constant time

HashMap lookup: O(1)
Remove and add to head: O(1)


put(key, value): O(1) - Constant time

HashMap lookup: O(1)
Remove node: O(1)
Add to head: O(1)
Remove LRU: O(1)
 */
public class LRUCache {
	class Node {
		int key;
		int value;
		Node next;Node prev;
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	private Map<Integer, Node> cache;
	private int capacity;
	private Node head;
	private Node tail;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache  = new HashMap<>();
		this.head = new Node(0, 0);
		this.tail = new Node(0,0);
		head.next = tail;
		tail.prev = head;
	}
	public int get(int key) {
		if(!cache.containsKey(key))
			return -1;
		Node node = cache.get(key);
		removeNode(node);
		moveToFront(node);
		return node.value;
	}
	public void put(int key, int value) {
		if(cache.containsKey(key)) {
			Node node = cache.get(key);
			removeNode(node);
			moveToFront(node);
			node.value = value;
		} else {
			Node newNode = new Node(key, value);
			moveToFront(newNode);
			cache.put(key, newNode);
			if(cache.size() > capacity) {
				Node lru = tail.prev;
				removeNode(lru);
				cache.remove(lru.key);
			} 
			moveToFront(newNode);
			cache.put(key, newNode);
		}
	}
	public void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	public void moveToFront(Node node) {
		node.next = head.next; 
		node.prev = head;
		head.next.prev = node;
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
