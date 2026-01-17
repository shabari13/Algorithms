package blind75.tree;
/*
 * 
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

 

Example 1:

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
Example 2:


Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
 

Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104


## Explaining Like You're 5 Years Old! 🌳

### The Big Picture
Imagine you have a **big LEGO tower** (main tree) and a **small LEGO piece** (subtree). You want to know: "Can I find this exact small piece somewhere inside my big tower?"

---

### How We Solve It

**Step 1: We use TWO helpers**
1. **Helper #1 (isSubtree)**: Walks around the big tower looking for places where the small piece might fit
2. **Helper #2 (isSameTree)**: Checks if two LEGO pieces are EXACTLY the same
Time Complexity: O(m × n)
What does this mean?

m = number of nodes in the main tree
n = number of nodes in the subtree

Why?

In the worst case, we might check EVERY node in the main tree (m nodes)
For EACH node, we might compare the entire subtree (n comparisons)
So: m × n total operations

Example: If main tree has 100 nodes and subtree has 10 nodes:

Worst case: 100 × 10 = 1,000 comparisons

Space Complexity: O(h)
What does this mean?

h = height of the main tree

Why?

We use recursion (function calls that stack up)
The deepest our recursion goes is the height of the tree
Each recursive call takes up space in the call stack

Example:

Balanced tree with 100 nodes: height ≈ 7, so O(7) extra space
Skewed tree (like a linked list): height = 100, so O(100) extra space


 */
public class SubtreeOfAnotherTree {
	
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if(root == null)
			return false;
		
		if(isSameTree(root, subRoot))
			return true;
		
		return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
		
	}
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null)
			return true;
		if(p == null || q == null)
			return false;
		return p.val == q.val && isSameTree(p.left, q.left)
				&& isSameTree(p.right, q.right);
	}

	 public static void main(String[] args) {
		 SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();
	        
	        // Test Case 1: subRoot is a subtree
	        // Main tree:      3
	        //               /   \
	        //              4     5
	        //             / \
	        //            1   2
	        //
	        // SubRoot:    4
	        //            / \
	        //           1   2
	        
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(4);
	        root1.right = new TreeNode(5);
	        root1.left.left = new TreeNode(1);
	        root1.left.right = new TreeNode(2);
	        
	        TreeNode subRoot1 = new TreeNode(4);
	        subRoot1.left = new TreeNode(1);
	        subRoot1.right = new TreeNode(2);
	        
	        System.out.println("Test Case 1:");
	        System.out.println("Main tree: [3,4,5,1,2]");
	        System.out.println("SubRoot: [4,1,2]");
	        System.out.println("Is subtree? " + solution.isSubtree(root1, subRoot1));
	        System.out.println();
	        
	        // Test Case 2: subRoot is NOT a subtree (extra node)
	        // Main tree:      3
	        //               /   \
	        //              4     5
	        //             / \
	        //            1   2
	        //               /
	        //              0
	        //
	        // SubRoot:    4
	        //            / \
	        //           1   2
	        
	        TreeNode root2 = new TreeNode(3);
	        root2.left = new TreeNode(4);
	        root2.right = new TreeNode(5);
	        root2.left.left = new TreeNode(1);
	        root2.left.right = new TreeNode(2);
	        root2.left.right.left = new TreeNode(0);
	        
	        TreeNode subRoot2 = new TreeNode(4);
	        subRoot2.left = new TreeNode(1);
	        subRoot2.right = new TreeNode(2);
	        
	        System.out.println("Test Case 2:");
	        System.out.println("Main tree: [3,4,5,1,2,null,null,0]");
	        System.out.println("SubRoot: [4,1,2]");
	        System.out.println("Is subtree? " + solution.isSubtree(root2, subRoot2));
	        System.out.println();
	        
	        // Test Case 3: Single node matching
	        // Main tree: 1
	        // SubRoot: 1
	        
	        TreeNode root3 = new TreeNode(1);
	        TreeNode subRoot3 = new TreeNode(1);
	        
	        System.out.println("Test Case 3:");
	        System.out.println("Main tree: [1]");
	        System.out.println("SubRoot: [1]");
	        System.out.println("Is subtree? " + solution.isSubtree(root3, subRoot3));
	        System.out.println();
	        
	        // Test Case 4: SubRoot is the entire tree
	        // Main tree:   1
	        //             / \
	        //            2   3
	        //
	        // SubRoot:   1
	        //           / \
	        //          2   3
	        
	        TreeNode root4 = new TreeNode(1);
	        root4.left = new TreeNode(2);
	        root4.right = new TreeNode(3);
	        
	        TreeNode subRoot4 = new TreeNode(1);
	        subRoot4.left = new TreeNode(2);
	        subRoot4.right = new TreeNode(3);
	        
	        System.out.println("Test Case 4:");
	        System.out.println("Main tree: [1,2,3]");
	        System.out.println("SubRoot: [1,2,3]");
	        System.out.println("Is subtree? " + solution.isSubtree(root4, subRoot4));
	    }
}
