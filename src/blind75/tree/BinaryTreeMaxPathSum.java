package blind75.tree;
/*
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 * Time and Space Complexity
Time Complexity: O(n)

We visit each node in the tree exactly once
At each node, we do constant work (comparisons, additions)
If the tree has n nodes, we do O(n) work total

Space Complexity: O(h) where h is the height of the tree

This is the recursion stack space
In the worst case (skewed tree), h = n, so O(n)
In the best case (balanced tree), h = log(n), so O(log n)
We don't use any extra data structures, just the call stack
 */
public class BinaryTreeMaxPathSum {
	
	private int maxSum;
	
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		findMaxPath(root);
		return maxSum;
		
	}
	
	public int findMaxPath(TreeNode root) {
		if(root == null)
			return 0;
		
		int leftMax = Math.max(0, findMaxPath(root.left));
		int rightMax = Math.max(0, findMaxPath(root.right));
		int currentMax = leftMax + rightMax + root.val;
		maxSum = Math.max(currentMax, maxSum);
		return root.val + Math.max(leftMax, rightMax);
	}

	public static void main(String[] args) {
		BinaryTreeMaxPathSum solution = new BinaryTreeMaxPathSum();
        
        // Test Case 1: Simple tree [1,2,3]
        //       1
        //      / \
        //     2   3
        System.out.println("=== Test Case 1 ===");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Tree: [1,2,3]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root1));
        System.out.println("Expected: 6 (path: 2->1->3)\n");
        
        // Test Case 2: Tree with negative values [-10,9,20,null,null,15,7]
        //        -10
        //        / \
        //       9   20
        //          /  \
        //         15   7
        System.out.println("=== Test Case 2 ===");
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("Tree: [-10,9,20,null,null,15,7]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root2));
        System.out.println("Expected: 42 (path: 15->20->7)\n");
        
        // Test Case 3: Single node [5]
        System.out.println("=== Test Case 3 ===");
        TreeNode root3 = new TreeNode(5);
        System.out.println("Tree: [5]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root3));
        System.out.println("Expected: 5\n");
        
        // Test Case 4: All negative values [-3,-2,-1]
        //       -3
        //       / \
        //     -2  -1
        System.out.println("=== Test Case 4 ===");
        TreeNode root4 = new TreeNode(-3);
        root4.left = new TreeNode(-2);
        root4.right = new TreeNode(-1);
        System.out.println("Tree: [-3,-2,-1]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root4));
        System.out.println("Expected: -1 (single node path)\n");
        
        // Test Case 5: Complex tree [5,4,8,11,null,13,4,7,2,null,null,null,1]
        //           5
        //          / \
        //         4   8
        //        /   / \
        //       11  13  4
        //      / \       \
        //     7   2       1
        System.out.println("=== Test Case 5 ===");
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(4);
        root5.right = new TreeNode(8);
        root5.left.left = new TreeNode(11);
        root5.left.left.left = new TreeNode(7);
        root5.left.left.right = new TreeNode(2);
        root5.right.left = new TreeNode(13);
        root5.right.right = new TreeNode(4);
        root5.right.right.right = new TreeNode(1);
        System.out.println("Tree: [5,4,8,11,null,13,4,7,2,null,null,null,1]");
        System.out.println("Maximum Path Sum: " + solution.maxPathSum(root5));
        System.out.println("Expected: 48 (path: 7->11->4->5->8->13)\n");
    }
}
