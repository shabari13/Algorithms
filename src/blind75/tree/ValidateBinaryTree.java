package blind75.tree;
/*
 * 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 

Constraints:

The number of nodes in the tree is in the range [1, 104].

Imagine you have a **magical tree of numbers**! In a Binary Search Tree (BST), there are special rules:

1. **Every number on the left side must be SMALLER** than the parent
2. **Every number on the right side must be BIGGER** than the parent
3. This rule applies to **EVERYONE in the tree**, not just the immediate children!

Think of it like organizing toys by size - everything to the left is smaller, everything to the right is bigger!

## How Does Our Solution Work? 🔍

We use **helper friends** called `min` and `max` to remember the allowed range for each number.

### The Magic Rules:
- When we go **LEFT**, we tell the child: "You must be smaller than your parent!"
- When we go **RIGHT**, we tell the child: "You must be bigger than your parent!"

## Step-by-Step Example Walkthrough 🚶
Time Complexity ⏱️
O(n) - where n is the number of nodes
We visit each node exactly once. Think of it like visiting every room in a house - you go to each room once and check if it's clean.
Space Complexity 💾
O(h) - where h is the height of the tree
This is the space used by the recursion call stack. In the worst case (a stick-like tree), h = n, so O(n). In a balanced tree, h = log(n), so O(log n).
Think of it like stacking blocks - the taller your tree, the more blocks you need to stack to remember where you came from!

 */
public class ValidateBinaryTree {
	
	public boolean isValidBST(TreeNode root) {
		
		return validate(root, null, null);
	}
	
	public boolean validate(TreeNode node, Integer min, Integer max) {
		if(node == null)
			return true;
		
		if(min != null && node.val <= min) 
			return false;
		if(max != null && node.val >= max)
			return false;
		
		return validate(node.left,min, node.val )
				&& validate(node.right, node.val, max);
		
	}
	
	public static void main(String[] args) {
		ValidateBinaryTree solution = new ValidateBinaryTree();
        
        // Test Case 1: Valid BST
        //     2
        //    / \
        //   1   3
        TreeNode test1 = new TreeNode(2);
        test1.left = new TreeNode(1);
        test1.right = new TreeNode(3);
        System.out.println("Test 1 - Valid BST [2,1,3]: " + solution.isValidBST(test1));
        
        // Test Case 2: Invalid BST
        //     5
        //    / \
        //   1   4
        //      / \
        //     3   6
        TreeNode test2 = new TreeNode(5);
        test2.left = new TreeNode(1);
        test2.right = new TreeNode(4);
        test2.right.left = new TreeNode(3);
        test2.right.right = new TreeNode(6);
        System.out.println("Test 2 - Invalid BST [5,1,4,null,null,3,6]: " + solution.isValidBST(test2));
        
        // Test Case 3: Single node (always valid)
        TreeNode test3 = new TreeNode(1);
        System.out.println("Test 3 - Single node [1]: " + solution.isValidBST(test3));
        
        // Test Case 4: Invalid - duplicate values
        //     2
        //    / \
        //   2   3
        TreeNode test4 = new TreeNode(2);
        test4.left = new TreeNode(2);
        test4.right = new TreeNode(3);
        System.out.println("Test 4 - Duplicate values [2,2,3]: " + solution.isValidBST(test4));
        
        // Test Case 5: Larger valid BST
        //       10
        //      /  \
        //     5    15
        //    / \     \
        //   3   7    20
        TreeNode test5 = new TreeNode(10);
        test5.left = new TreeNode(5);
        test5.right = new TreeNode(15);
        test5.left.left = new TreeNode(3);
        test5.left.right = new TreeNode(7);
        test5.right.right = new TreeNode(20);
        System.out.println("Test 5 - Valid larger BST: " + solution.isValidBST(test5));
    }

}
