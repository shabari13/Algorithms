package neetcode150.trie;
/*
 * Design a data structure that supports adding new words and searching for existing words.

Implement the WordDictionary class:

void addWord(word) Adds word to the data structure.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
Example 1:

Input:
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["day"],["bay"],["may"],["say"],["day"],[".ay"],["b.."]]

Output:
[null, null, null, null, false, true, true, true]

Explanation:
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("day");
wordDictionary.addWord("bay");
wordDictionary.addWord("may");
wordDictionary.search("say"); // return false
wordDictionary.search("day"); // return true
wordDictionary.search(".ay"); // return true
wordDictionary.search("b.."); // return true
Constraints:

1 <= word.length <= 20
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 10,000 calls will be made to addWord and search.

OperationTimeSpaceaddWord(w)O(L) where L = word lengthO(L × 26) worst case new nodessearch(w) 
no wildcardsO(L)O(L) recursion stacksearch(w) all wildcards "..."O(26^L) 
worst caseO(L) recursion stackOverall space (N words, avg len L)—O(N × L × 26) for the trie

We use a Trie (prefix tree) where each node has up to 26 children (one per letter). When we add a word, we walk through each character and create nodes along the path, marking the last node as "end of word." When we search, normal characters just walk the trie normally. The magic is with . (wildcard) — when we hit a dot, we try all 26 possible children recursively. If any path leads to a valid word ending, we return true.
Explain Like I'm 5
Imagine a big tree. Every branch is a letter. If you want to save the word "cat", you go: make a branch c, then from c make a branch a, then from a make a branch t, and put a flag 🚩 on t saying "a word ends here!"
Now when you search for "cat", you just follow branches c → a → t and check if there's a flag 🚩.
When you search for ".at", the dot means "I don't care what letter this is" — so you try every single branch from the start (a→t, b→t, c→t... all 26!), and if any of them lead to a flag 🚩 at the end, you say "Found it!"

basically when you have . in the search word, you loop through 26 and check if there are any children node with that
then we go for the recursive call with next index
then check if there are matching children with next index.
else get the indx normally like ch - 'a';
if there is not childredn with idx return false r elase recursivelay call with index+1 and node.children[indx]
 */
public class WordDictionary {
	
	class TrieNode{
		TrieNode[] children;
		boolean isEndOfWord;
		public TrieNode() {
			this.children = new TrieNode[26];
			this.isEndOfWord = false;
		}
	}
	TrieNode root;
	public WordDictionary() {
		this.root = new TrieNode();
	}
	
	public void addWord(String word) {
		
		TrieNode current  = root;
		for(char ch : word.toCharArray()) {
			
			int index = ch - 'a';
			if(current.children[index] == null) {
				current.children[index] = new TrieNode();
			}
			current = current.children[index];
		}
		current.isEndOfWord = true;
	}
	
	public boolean search(String word) {
		return searchHelper(word, 0, root);
	}
	
	public boolean searchHelper(String word, int index, TrieNode node) {
		if(index == word.length())
			return node.isEndOfWord;
		char ch = word.charAt(index);
		if(ch == '.') {
			for(int i = 0; i < 26; i++) {
				if(node.children[i] != null) {
					if(searchHelper(word, index + 1, node.children[i])) {
						return true;
					}
				}
			}
		return false;	
		} else {
			int indx = ch - 'a';
			if(node.children[indx] == null)
				return false;
			return searchHelper(word, index + 1, node.children[indx]);
		}
		
	}
	
	 public static void main(String[] args) {

	        System.out.println("========================================");
	        System.out.println("     WordDictionary - Trie Demo");
	        System.out.println("========================================\n");

	        WordDictionary wd = new WordDictionary();

	        // ── Adding Words ──────────────────────────────────────────
	        String[] wordsToAdd = {"bad", "dad", "mad", "cat", "bat", "rat", "pad"};
	        for (String w : wordsToAdd) {
	            wd.addWord(w);
	            System.out.println("addWord(\"" + w + "\") → added successfully");
	        }

	        System.out.println();

	        // ── Test Cases ────────────────────────────────────────────
	        // Format: {pattern, expected}
	        String[][] tests = {
	            // Exact matches
	            {"bad",  "true"},
	            {"dad",  "true"},
	            {"mad",  "true"},
	            {"sad",  "false"},   // never added
	            {"cat",  "true"},

	            // Wildcard: dot in different positions
	            {".ad",  "true"},    // b.ad→bad, d.ad→dad, m.ad→mad, p.ad→pad
	            {"b.d",  "true"},    // b?d → bad or bat? no... bad=b-a-d ✓
	            {"b.t",  "true"},    // bat → b-a-t ✓
	            {"..t",  "true"},    // bat or rat ✓
	            {"..d",  "true"},    // bad, dad, mad, pad ✓
	            {"...",  "true"},    // any 3-letter word ✓
	            {"....", "false"},   // no 4-letter words added
	            {".at",  "true"},    // cat, bat, rat ✓
	            {"r..",  "true"},    // rat ✓
	            {"z..",  "false"},   // nothing starts with z

	            // Edge cases
	            {"ba",   "false"},   // prefix only, not a full word
	            {"ba.",  "true"},    // bad or bat ✓
	        };

	        System.out.println("Pattern      | Expected | Got    | Pass?");
	        System.out.println("-------------|----------|--------|------");

	        for (String[] test : tests) {
	            String pattern  = test[0];
	            String expected = test[1];
	            boolean result  = wd.search(pattern);
	            String got      = String.valueOf(result);
	            String pass     = got.equals(expected) ? "✅" : "❌";

	            System.out.printf("%-12s | %-8s | %-6s | %s%n",
	                              "\"" + pattern + "\"", expected, got, pass);
	        }

	        System.out.println("\n========================================");
	        System.out.println("   Step-by-step trace for \".ad\"");
	        System.out.println("========================================");
	        System.out.println("Trie contains: bad, dad, mad, pad");
	        System.out.println("Pattern: \".ad\"");
	        System.out.println();
	        System.out.println("index=0, char='.' → try all 26 children of root");
	        System.out.println("  → child['b'-'a'=1] exists (from 'bad','bat')");
	        System.out.println("     index=1, char='a' → follow children[0]");
	        System.out.println("       index=2, char='d' → follow children[3]");
	        System.out.println("         index=3 == length → isEnd=true ✅ FOUND!");
	        System.out.println("Result: true");

	        System.out.println("\n========================================");
	        System.out.println("   Step-by-step trace for \"...\"");
	        System.out.println("========================================");
	        System.out.println("Pattern: \"...\"");
	        System.out.println("index=0, char='.' → try all 26 children of root");
	        System.out.println("  → 'b' exists: try 'b'");
	        System.out.println("     index=1, char='.' → try all 26 children of 'b'");
	        System.out.println("       → 'a' exists (bad, bat)");
	        System.out.println("          index=2, char='.' → try all 26 children of 'a'");
	        System.out.println("            → 'd' exists → index=3==length, isEnd=true ✅ FOUND!");
	        System.out.println("Result: true");
	    }

}
