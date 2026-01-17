package blind75.tree;
/*
 * 
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.\\

⏱️ Time Complexity
addWord(word):

We visit each letter once
O(L) where L = length of word

search(word):

Best case (no dots): O(L)
Worst case (all dots "..."): O(26^L)

Each dot tries all 26 letters!




💾 Space Complexity
Overall: O(N × L)

N = number of words
L = average length of words
We store each letter as a node in the tree


 */
public class WordDictionary {
	
	class TrieNode {
		
		TrieNode[] children;
		boolean isEndOfWord;
		TrieNode() {
			children = new TrieNode[26];
			isEndOfWord = false;
			
		}
	}
	
	TrieNode root;
	WordDictionary() {
		root = new TrieNode();
	}
	
	public void addWord(String word) {
		
		TrieNode current = root;
		
		for(int i = 0; i<word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if(current.children[index] ==  null) {
				
				current.children[index] = new TrieNode();
			}
			current = current.children[index];	
			
		}
		current.isEndOfWord = true;
	}
	
	public  boolean search(String word) {
		return searchHelper(word, 0, root);
	}
	
	public boolean searchHelper(String word, int index, TrieNode node ) {
		if(word.length() == index)
			return node.isEndOfWord;
	
			char c = word.charAt(index);
			if(c == '.') {
				for(int i = 0; i < 26; i++) {
					if(node.children[i] != null) {
						if(searchHelper(word, index+1, node.children[i]))
							return true;
					}
					
				}
				return false;
			} else {
				int idx = c - 'a';
				
				if(node.children[idx] == null) {
					return false;
				}
				return searchHelper(word, index+1, node.children[idx]);
			}
		
	}
	public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        
        System.out.println("=== Adding words ===");
        wd.addWord("bad");
        System.out.println("Added: bad");
        
        wd.addWord("dad");
        System.out.println("Added: dad");
        
        wd.addWord("mad");
        System.out.println("Added: mad");
        
        System.out.println("\n=== Searching words ===");
        
        System.out.println("search(\"pad\"): " + wd.search("pad"));
        System.out.println("search(\"bad\"): " + wd.search("bad"));
        System.out.println("search(\".ad\"): " + wd.search(".ad"));
        System.out.println("search(\"b..\"): " + wd.search("b.."));
        System.out.println("search(\"...\"): " + wd.search("..."));
        System.out.println("search(\".a.\"): " + wd.search(".a."));
        System.out.println("search(\"..d\"): " + wd.search("..d"));
        
        System.out.println("\n=== More examples ===");
        WordDictionary wd2 = new WordDictionary();
        wd2.addWord("at");
        wd2.addWord("and");
        wd2.addWord("an");
        wd2.addWord("add");
        
        System.out.println("Added: at, and, an, add");
        System.out.println("search(\"a\"): " + wd2.search("a") + " (Expected: false - 'a' alone was never added)");
        System.out.println("search(\".at\"): " + wd2.search(".at") + " (Expected: false - no 3-letter word ending in 'at')");
        System.out.println("search(\"an.\"): " + wd2.search("an.") + " (Expected: true - matches 'and')");
        System.out.println("search(\"a.d\"): " + wd2.search("a.d") + " (Expected: true - matches 'and' or 'add')");
        System.out.println("search(\"a.d.\"): " + wd2.search("a.d.") + " (Expected: false - no 4-letter words)");
  
    }

}
