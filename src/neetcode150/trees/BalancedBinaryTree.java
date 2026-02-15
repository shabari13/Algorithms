package neetcode150.trees;
/*
 * Given a binary tree, return true if it is height-balanced and false otherwise.

A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:



Input: root = [1,2,3,null,null,4]

Output: true
Example 2:



Input: root = [1,2,3,null,null,4,null,5]

Output: false
Example 3:

Input: root = []

Output: true
Constraints:

The number of nodes in the tree is in the range [0, 1000].
-1000 <= Node.val <= 1000
Time Complexity: O(n)

We visit each node in the tree exactly once
At each node, we perform constant-time operations (comparisons, arithmetic)
n is the total number of nodes in the tree
Therefore, the total time is proportional to the number of nodes

Space Complexity: O(h)

The space is used by the recursion call stack
In the worst case (completely skewed tree), h = n, so O(n)
In the best case (completely balanced tree), h = log(n), so O(log n)
h represents the height of the tree
We don't use any additional data structures, only the implicit call stack from recursion
 */
public class BalancedBinaryTree {
	
	public boolean isBalanced(TreeNode root) {
		return (getHeight(root) != -1);
	}
	public int getHeight(TreeNode root) {
		if(root == null)
			return 0;
		int leftHeight = getHeight(root.left);
		if(leftHeight == -1)
			return -1;
		int rightHeight = getHeight(root.right);
		if(rightHeight == -1) {
			return -1;
		}
		if(Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static void main(String[] args) {
		BalancedBinaryTree solution = new BalancedBinaryTree();
        
        // Test Case 1: Balanced tree [3,9,20,null,null,15,7]
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
        System.out.println("Test Case 1: " + solution.isBalanced(root1)); // Expected: true
        
        // Test Case 2: Unbalanced tree [1,2,2,3,3,null,null,4,4]
        //          1
        //         / \
        //        2   2
        //       / \
        //      3   3
        //     / \
        //    4   4
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);
        System.out.println("Test Case 2: " + solution.isBalanced(root2)); // Expected: false
        
        // Test Case 3: Empty tree
        TreeNode root3 = null;
        System.out.println("Test Case 3: " + solution.isBalanced(root3)); // Expected: true
        
        // Test Case 4: Single node
        TreeNode root4 = new TreeNode(1);
        System.out.println("Test Case 4: " + solution.isBalanced(root4)); // Expected: true
        
        // Test Case 5: Balanced tree with only left child
        //     1
        //    /
        //   2
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        System.out.println("Test Case 5: " + solution.isBalanced(root5)); // Expected: true
    }

}
