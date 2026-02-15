package neetcode150.trees;
/*
 * Given the root of a binary tree, return its depth.

The depth of a binary tree is defined as the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:



Input: root = [1,2,3,null,null,4]

Output: 3
Example 2:

Input: root = []

Output: 0
⏱️ Time Complexity: O(n)

n = number of nodes in the tree
We visit every single node exactly once
At each node, we do a constant amount of work (comparing two numbers and adding 1)

💾 Space Complexity: O(h)

h = height of the tree
This is the space used by the recursion call stack
In the worst case (skewed tree like a linked list): O(n)
In the best case (balanced tree): O(log n)
Find the maxDepth of left Tree and right tree. Then find the max between left and right tree and add 1 to it to count root
 */
public class DepthOfBinaryTree {
	
	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	 public static void main(String[] args) {
		 DepthOfBinaryTree solution = new DepthOfBinaryTree();
	        
	        // Example 1: Tree [3,9,20,null,null,15,7]
	        //       3
	        //      / \
	        //     9  20
	        //       /  \
	        //      15   7
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(9);
	        root1.right = new TreeNode(20);
	        root1.right.left = new TreeNode(15);
	        root1.right.right = new TreeNode(7);
	        
	        System.out.println("Example 1: Tree [3,9,20,null,null,15,7]");
	        System.out.println("Maximum Depth: " + solution.maxDepth(root1));
	        System.out.println();
	        
	        // Example 2: Tree [1,null,2]
	        //     1
	        //      \
	        //       2
	        TreeNode root2 = new TreeNode(1);
	        root2.right = new TreeNode(2);
	        
	        System.out.println("Example 2: Tree [1,null,2]");
	        System.out.println("Maximum Depth: " + solution.maxDepth(root2));
	        System.out.println();
	        
	        // Example 3: Empty tree
	        TreeNode root3 = null;
	        
	        System.out.println("Example 3: Empty tree");
	        System.out.println("Maximum Depth: " + solution.maxDepth(root3));
	        System.out.println();
	        
	        // Example 4: Single node tree
	        //     5
	        TreeNode root4 = new TreeNode(5);
	        
	        System.out.println("Example 4: Single node [5]");
	        System.out.println("Maximum Depth: " + solution.maxDepth(root4));
	        System.out.println();
	        
	        // Example 5: Deeper tree
	        //       1
	        //      / \
	        //     2   3
	        //    /
	        //   4
	        //  /
	        // 5
	        TreeNode root5 = new TreeNode(1);
	        root5.left = new TreeNode(2);
	        root5.right = new TreeNode(3);
	        root5.left.left = new TreeNode(4);
	        root5.left.left.left = new TreeNode(5);
	        
	        System.out.println("Example 5: Deeper tree [1,2,3,4,null,null,null,5]");
	        System.out.println("Maximum Depth: " + solution.maxDepth(root5));
	    }

}
