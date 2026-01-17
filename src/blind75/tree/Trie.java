package blind75.tree;
/*
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
⏱️ Time Complexity
Insert: O(m) where m = length of word

We visit each letter once
Each operation (checking/creating a box) takes O(1) constant time

Search: O(m) where m = length of word

We visit each letter once
Each check takes O(1)

StartsWith: O(m) where m = length of prefix

Same as search - check each letter once


💾 Space Complexity
O(N × M × 26) where:

N = number of words
M = average length of words
26 = alphabet size (each node has 26 pointers)

In the worst case, if all words are completely
 *
 */

public class Trie {
    TrieNode root;
	class TrieNode {
		TrieNode[] children;
		boolean isEndOfWord;
		TrieNode() {
			children = new TrieNode[26];
			isEndOfWord = false;
			
		}
		
	}
	
	Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		
		TrieNode current =  root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if(current.children[index] == null) {
				current.children[index] = new TrieNode();
			}
			current = current.children[index];
			
		}
		current.isEndOfWord = true;
	}
	
	public boolean search(String word) {
		TrieNode current = root;
		
		for(int i = 0 ; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if(current.children[index] == null)
				return false;
			current = current.children[index];
			
		}
		return current.isEndOfWord;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode current = root;
		
		for(int i = 0; i < prefix.length(); i++) {
			char c =  prefix.charAt(i);
			int index = c - 'a';
			if(current.children[index] == null)
				return false;
			current = current.children[index];
		}
		return true;
	}
	 public static void main(String[] args) {
	        System.out.println("=== Creating a new Trie ===\n");
	        Trie trie = new Trie();
	        
	        // Test Case 1: Insert "apple"
	        System.out.println("Operation: insert(\"apple\")");
	        trie.insert("apple");
	        System.out.println("✓ Word 'apple' inserted\n");
	        
	        // Test Case 2: Search for "apple"
	        System.out.println("Operation: search(\"apple\")");
	        boolean result1 = trie.search("apple");
	        System.out.println("Result: " + result1 + " (Expected: true)\n");
	        
	        // Test Case 3: Search for "app"
	        System.out.println("Operation: search(\"app\")");
	        boolean result2 = trie.search("app");
	        System.out.println("Result: " + result2 + " (Expected: false - 'app' was never marked as complete word)\n");
	        
	        // Test Case 4: Check prefix "app"
	        System.out.println("Operation: startsWith(\"app\")");
	        boolean result3 = trie.startsWith("app");
	        System.out.println("Result: " + result3 + " (Expected: true - 'app' is prefix of 'apple')\n");
	        
	        // Test Case 5: Insert "app"
	        System.out.println("Operation: insert(\"app\")");
	        trie.insert("app");
	        System.out.println("✓ Word 'app' inserted\n");
	        
	        // Test Case 6: Search for "app" again
	        System.out.println("Operation: search(\"app\")");
	        boolean result4 = trie.search("app");
	        System.out.println("Result: " + result4 + " (Expected: true - now 'app' is a complete word)\n");
	        
	        // Additional test cases
	        System.out.println("=== Additional Tests ===\n");
	        
	        trie.insert("application");
	        System.out.println("Inserted: 'application'");
	        
	        System.out.println("search(\"application\"): " + trie.search("application"));
	        System.out.println("search(\"appl\"): " + trie.search("appl"));
	        System.out.println("startsWith(\"appl\"): " + trie.startsWith("appl"));
	        System.out.println("startsWith(\"ban\"): " + trie.startsWith("ban"));
	    }
}
