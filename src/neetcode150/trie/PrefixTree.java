package neetcode150.trie;
/*
 * A prefix tree (also known as a trie) is a tree data structure used to efficiently store and retrieve keys in a set of strings. Some applications of this data structure include auto-complete and spell checker systems.

Implement the PrefixTree class:

PrefixTree() Initializes the prefix tree object.
void insert(String word) Inserts the string word into the prefix tree.
boolean search(String word) Returns true if the string word is in the prefix tree (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
Example 1:

Input:
["Trie", "insert", "dog", "search", "dog", "search", "do", "startsWith", "do", "insert", "do", "search", "do"]

Output:
[null, null, true, false, true, null, true]

Explanation:
PrefixTree prefixTree = new PrefixTree();
prefixTree.insert("dog");
prefixTree.search("dog");    // return true
prefixTree.search("do");     // return false
prefixTree.startsWith("do"); // return true
prefixTree.insert("do");
prefixTree.search("do");     // return true
Constraints:

1 <= word.length, prefix.length <= 1000
word and prefix are made up of lowercase English letters.

Time & Space Complexity
OperationTimeExplanationinsertO(m)m = length of word; visit each char oncesearchO(m)m = length of wordstartsWithO(m)m = length of prefix
SpaceTotal spaceO(N × m × 26)

 */
public class PrefixTree {
	
	static class Trie {
		Trie[] children;
		boolean isEndOfWord = false;
		
		Trie() {
			children = new Trie[26];
			isEndOfWord = false;
		}
	}
	
	Trie root;
	
	public PrefixTree() {
		root = new Trie();
	}

	void insert(String word) {
		Trie current = root;
		for(char ch : word.toCharArray()) {
			int index = ch - 'a';
			if(current.children[index] == null) {
				current.children[index] = new Trie();
			}
			current = current.children[index];
		}
		current.isEndOfWord = true;
		
	}
	
	public boolean search(String word) {
		Trie current = root;
		for(char ch : word.toCharArray()) {
			int index = ch - 'a';
			if(current.children[index] == null) {
				return false;
			}
			current = current.children[index];
		}
		return current.isEndOfWord;
	}
	

	public boolean startsWith(String word) {
		Trie current = root;
		for(char ch:word.toCharArray()) {
			int index = ch - 'a';
			if(current.children[index] == null) {
				return false;
			}
			current = current.children[index];
		}
		return true;
	}
	
	private static void run(PrefixTree trie, String op, String word, Boolean expected) {
        boolean result;
        if (op.equals("search"))     result = trie.search(word);
        else                          result = trie.startsWith(word);
        String status = (expected == null || result == expected) ? "✓" : "✗";
        System.out.printf("  %-12s(\"%-10s\") => %-5b  %s%n", op, word, result, status);
    }

    // -----------------------------------------------------------
    // MAIN – sample + extra test cases
    // -----------------------------------------------------------
    public static void main(String[] args) {

        // ─── Test-case 1 (LeetCode example) ───────────────────
        System.out.println("=== Test 1 – LeetCode canonical example ===");
        PrefixTree t1 = new PrefixTree();
        t1.insert("apple");
        System.out.println("  insert(\"apple\")");

        run(t1, "search",     "apple",  true);   // true  – exact word
        run(t1, "search",     "app",    false);  // false – only a prefix
        run(t1, "startsWith", "app",    true);   // true  – prefix exists
        t1.insert("app");
        System.out.println("  insert(\"app\")");
        run(t1, "search",     "app",    true);   // true  – now a full word

        // ─── Test-case 2 (multiple words, shared prefix) ──────
        System.out.println("\n=== Test 2 – shared prefix 'car' ===");
        PrefixTree t2 = new PrefixTree();
        t2.insert("car");
        t2.insert("card");
        t2.insert("care");
        t2.insert("careful");
        t2.insert("cars");
        System.out.println("  inserted: car, card, care, careful, cars");

        run(t2, "search",     "car",      true);
        run(t2, "search",     "care",     true);
        run(t2, "search",     "cares",    false);  // not inserted
        run(t2, "startsWith", "care",     true);
        run(t2, "startsWith", "carx",     false);
        run(t2, "startsWith", "c",        true);
        run(t2, "startsWith", "careful",  true);
        run(t2, "search",     "careful",  true);

        // ─── Test-case 3 (single character words) ─────────────
        System.out.println("\n=== Test 3 – single-char words ===");
        PrefixTree t3 = new PrefixTree();
        t3.insert("a");
        t3.insert("b");
        System.out.println("  inserted: a, b");
        run(t3, "search",     "a",   true);
        run(t3, "search",     "b",   true);
        run(t3, "search",     "c",   false);
        run(t3, "startsWith", "a",   true);
        run(t3, "startsWith", "ab",  false);

        // ─── Test-case 4 (completely disjoint words) ──────────
        System.out.println("\n=== Test 4 – no shared prefix ===");
        PrefixTree t4 = new PrefixTree();
        t4.insert("hello");
        t4.insert("world");
        System.out.println("  inserted: hello, world");
        run(t4, "search",     "hello",  true);
        run(t4, "search",     "world",  true);
        run(t4, "search",     "hell",   false);
        run(t4, "startsWith", "wor",    true);
        run(t4, "startsWith", "wos",    false);
        run(t4, "search",     "word",   false);
    }
}
