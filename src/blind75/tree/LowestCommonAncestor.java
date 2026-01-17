package blind75.tree;
/*
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 

Example 1:


Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:


Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [2,1], p = 2, q = 1
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the BST.
 * O(h) where h = height of the tree

In the BEST case (balanced tree): O(log n)

Tree with 7 nodes has height 3
We visit at most 3 nodes


In the WORST case (skewed tree): O(n)

Tree like: 1→2→3→4→5 (just a line)
We might visit all 5 nodes



Why? We only go down ONE path, never backtrack, never visit siblings!

Space Complexity 💾
O(1) - Constant space!
Why? We only use a single variable current to keep track of where we are. We don't use recursion (no call stack), don't create any arrays or lists. Just one pointer moving down the tree!
If we used recursion, it would be O(h) for the call stack.

Think of it like a game of "Hot and Cold":

Both smaller? → Go LEFT (both nodes are in the left subtree)
Both bigger? → Go RIGHT (both nodes are in the right subtree)
One smaller, one bigger? → FOUND IT! (this is the split point)
One equals current? → FOUND IT! (one node is ancestor of the other)
 */
public class LowestCommonAncestor {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode current = root;
		
		while(current != null) {
			
			if(p.val < current.val && q.val < current.val) {
				current =  current.left;
			}
			
			else if(p.val  > current.val && q.val > current.val) {
				current =  current.right;
			}
			else 
				return current;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		LowestCommonAncestor solution = new LowestCommonAncestor();
        
        // Test Case 1: BST tree structure
        //       6
        //      / \
        //     2   8
        //    / \ / \
        //   0  4 7  9
        //     / \
        //    3   5
        
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        
        TreeNode p1 = root1.left; // Node 2
        TreeNode q1 = root1.right; // Node 8
        TreeNode result1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Test 1: LCA of 2 and 8 = " + result1.val);
        
        // Test Case 2: Same tree, different nodes
        TreeNode p2 = root1.left; // Node 2
        TreeNode q2 = root1.left.right; // Node 4
        TreeNode result2 = solution.lowestCommonAncestor(root1, p2, q2);
        System.out.println("Test 2: LCA of 2 and 4 = " + result2.val);
        
        // Test Case 3: Nodes on same side
        TreeNode p3 = root1.left.right.left; // Node 3
        TreeNode q3 = root1.left.right.right; // Node 5
        TreeNode result3 = solution.lowestCommonAncestor(root1, p3, q3);
        System.out.println("Test 3: LCA of 3 and 5 = " + result3.val);
        
        // Test Case 4: Simple tree
        //     2
        //    / \
        //   1   3
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        
        TreeNode p4 = root2.left; // Node 1
        TreeNode q4 = root2.right; // Node 3
        TreeNode result4 = solution.lowestCommonAncestor(root2, p4, q4);
        System.out.println("Test 4: LCA of 1 and 3 = " + result4.val);
        
        // Test Case 5: One node is ancestor of other
        TreeNode p5 = root1.right; // Node 8
        TreeNode q5 = root1.right.right; // Node 9
        TreeNode result5 = solution.lowestCommonAncestor(root1, p5, q5);
        System.out.println("Test 5: LCA of 8 and 9 = " + result5.val);
    }

}
