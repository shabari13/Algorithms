package neetcode150.trees;
/*
 * Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.

A valid binary search tree satisfies the following constraints:

The left subtree of every node contains only nodes with keys less than the node's key.
The right subtree of every node contains only nodes with keys greater than the node's key.
Both the left and right subtrees are also binary search trees.
Example 1:



Input: root = [2,1,3]

Output: true
Example 2:



Input: root = [1,2,3]

Output: false
Explanation: The root node's value is 1 but its left child's value is 2 which is greater than 1.

Constraints:

1 <= The number of nodes in the tree <= 1000.
-1000 <= Node.val <= 1000	
Time and Space Complexity
Time Complexity: O(n)

We visit each node exactly once
At each node, we perform constant-time operations (comparisons)
n = number of nodes in the tree
Best case: O(1) if we find an invalid node immediately
Average/Worst case: O(n) when we need to check all nodes

Space Complexity: O(h)

h = height of the tree
Space is used by the recursive call stack
Best case (balanced tree): O(log n) where h = log n
Worst case (skewed tree): O(n) where h = n
The Integer objects (min, max) use constant space per call
 */
public class ValidBinaryTree {
	
	public boolean isValidBST(TreeNode root) {
		return validTree(root, null, null);
	}
	public boolean validTree(TreeNode node, Integer min, Integer max) {
		if(node == null)
			return true;
		if(min != null && node.val <= min)
			return false;
		if(max != null && node.val >= max)
			return false;
		return (validTree(node.left, min, node.val)
				&& validTree(node.right, node.val, max));
	}
	  public static void main(String[] args) {
		  ValidBinaryTree solution = new ValidBinaryTree();
	        
	        // Test Case 1: Valid BST
	        //       2
	        //      / \
	        //     1   3
	        TreeNode test1 = new TreeNode(2);
	        test1.left = new TreeNode(1);
	        test1.right = new TreeNode(3);
	        System.out.println("Test 1 - Valid BST [2,1,3]: " + solution.isValidBST(test1));
	        
	        // Test Case 2: Invalid BST
	        //       5
	        //      / \
	        //     1   4
	        //        / \
	        //       3   6
	        TreeNode test2 = new TreeNode(5);
	        test2.left = new TreeNode(1);
	        test2.right = new TreeNode(4);
	        test2.right.left = new TreeNode(3);
	        test2.right.right = new TreeNode(6);
	        System.out.println("Test 2 - Invalid BST [5,1,4,null,null,3,6]: " + solution.isValidBST(test2));
	        
	        // Test Case 3: Single node
	        TreeNode test3 = new TreeNode(1);
	        System.out.println("Test 3 - Single node [1]: " + solution.isValidBST(test3));
	        
	        // Test Case 4: Valid larger BST
	        //       10
	        //      /  \
	        //     5    15
	        //    / \     \
	        //   2   7    20
	        TreeNode test4 = new TreeNode(10);
	        test4.left = new TreeNode(5);
	        test4.right = new TreeNode(15);
	        test4.left.left = new TreeNode(2);
	        test4.left.right = new TreeNode(7);
	        test4.right.right = new TreeNode(20);
	        System.out.println("Test 4 - Valid larger BST: " + solution.isValidBST(test4));
	        
	        // Test Case 5: Edge case - duplicate values (invalid)
	        //       2
	        //      / \
	        //     2   2
	        TreeNode test5 = new TreeNode(2);
	        test5.left = new TreeNode(2);
	        test5.right = new TreeNode(2);
	        System.out.println("Test 5 - Duplicate values [2,2,2]: " + solution.isValidBST(test5));
	        
	        // Test Case 6: Tricky invalid case
	        //       10
	        //      /  \
	        //     5    15
	        //         /  \
	        //        6    20
	        TreeNode test6 = new TreeNode(10);
	        test6.left = new TreeNode(5);
	        test6.right = new TreeNode(15);
	        test6.right.left = new TreeNode(6);
	        test6.right.right = new TreeNode(20);
	        System.out.println("Test 6 - Tricky invalid [10,5,15,null,null,6,20]: " + solution.isValidBST(test6));
	    }

}
