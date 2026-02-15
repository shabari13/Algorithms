package neetcode150.trees;
/*
 * 
 * Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.

The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q as descendants. The ancestor is allowed to be a descendant of itself.

Example 1:



Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 8

Output: 5
Example 2:



Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 4

Output: 3
Explanation: The LCA of nodes 3 and 4 is 3, since a node can be a descendant of itself.

Constraints:

2 <= The number of nodes in the tree <= 100.
-100 <= Node.val <= 100
p != q
p and q will both exist in the BST.

Time Complexity: O(h) where h is the height of the tree

In the worst case, we traverse from the root to a leaf node
For a balanced BST: O(log n) where n is the number of nodes
For a skewed BST (like a linked list): O(n)
We visit each node at most once along a single path

Space Complexity: O(1)

We only use a constant amount of extra space (the current pointer)
No recursion means no call stack space
We don't create any additional data structures
This is an iterative solution, making it very space-efficient!

*/
public class LowestCommonAncestor {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode current = root;
		while(current != null) {
			if(p.val < current.val && q.val < current.val)
				current  = current.left;
			else if(p.val > current.val && q.val > current.val)
				current = current.right;
			else 
				return current;
					
		}
		return null;
	}
}
