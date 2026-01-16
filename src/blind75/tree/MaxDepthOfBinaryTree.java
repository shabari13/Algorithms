package blind75.tree;
/*
 * Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100

The Big Idea
Think of a tree where each branch can split into two more branches (left and right). We want to count how many levels of branches there are from the ground to the tallest branch.

Time and Space Complexity
Time Complexity: O(n)

We visit every single node in the tree exactly once
n is the total number of nodes
Even if the tree has 1000 nodes, we visit each one just once

Space Complexity: O(h) where h is the height of the tree

This is the space used by the recursion call stack
In the worst case (skewed tree like Test Case 4), h = n, so O(n)
In the best case (balanced tree), h = log(n), so O(log n)
Think of it like a stack of plates - we can only stack as high as the tree is tall
 * 
 */
public class MaxDepthOfBinaryTree {

	public int maxDepth(TreeNode root) {
		
		if(root  == null)
			return 0;
		
		int leftDepth  = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		return 1 + Math.max(leftDepth, rightDepth);
		
	}
	
    public static void main(String[] args) {
    	MaxDepthOfBinaryTree solution = new MaxDepthOfBinaryTree();
        
        // Test Case 1: Tree with depth 3
        // Tree structure:
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        TreeNode tree1 = new TreeNode(3);
        tree1.left = new TreeNode(9);
        tree1.right = new TreeNode(20);
        tree1.right.left = new TreeNode(15);
        tree1.right.right = new TreeNode(7);
        
        System.out.println("Test Case 1:");
        System.out.println("Tree: [3,9,20,null,null,15,7]");
        System.out.println("Maximum Depth: " + solution.maxDepth(tree1));
        System.out.println();
        
        // Test Case 2: Single node tree
        // Tree structure:
        //       1
        TreeNode tree2 = new TreeNode(1);
        
        System.out.println("Test Case 2:");
        System.out.println("Tree: [1]");
        System.out.println("Maximum Depth: " + solution.maxDepth(tree2));
        System.out.println();
        
        // Test Case 3: Empty tree
        TreeNode tree3 = null;
        
        System.out.println("Test Case 3:");
        System.out.println("Tree: []");
        System.out.println("Maximum Depth: " + solution.maxDepth(tree3));
        System.out.println();
        
        // Test Case 4: Skewed tree (only left children)
        // Tree structure:
        //       1
        //      /
        //     2
        //    /
        //   3
        //  /
        // 4
        TreeNode tree4 = new TreeNode(1);
        tree4.left = new TreeNode(2);
        tree4.left.left = new TreeNode(3);
        tree4.left.left.left = new TreeNode(4);
        
        System.out.println("Test Case 4:");
        System.out.println("Tree: [1,2,null,3,null,4]");
        System.out.println("Maximum Depth: " + solution.maxDepth(tree4));
        System.out.println();
        
        // Test Case 5: Balanced tree with depth 4
        // Tree structure:
        //         1
        //       /   \
        //      2     3
        //     / \   /
        //    4   5 6
        //   /
        //  7
        TreeNode tree5 = new TreeNode(1);
        tree5.left = new TreeNode(2);
        tree5.right = new TreeNode(3);
        tree5.left.left = new TreeNode(4);
        tree5.left.right = new TreeNode(5);
        tree5.right.left = new TreeNode(6);
        tree5.left.left.left = new TreeNode(7);
        
        System.out.println("Test Case 5:");
        System.out.println("Tree: [1,2,3,4,5,6,null,7]");
        System.out.println("Maximum Depth: " + solution.maxDepth(tree5));
    }
}
