package neetcode150.trees;
/*
 * 
 * You are given two integer arrays preorder and inorder.

preorder is the preorder traversal of a binary tree
inorder is the inorder traversal of the same tree
Both arrays are of the same size and consist of unique values.
Rebuild the binary tree from the preorder and inorder traversals and return its root.

Example 1:



Input: preorder = [1,2,3,4], inorder = [2,1,3,4]

Output: [1,2,3,null,null,null,4]
Example 2:

Input: preorder = [1], inorder = [1]

Output: [1]
Constraints:

1 <= inorder.length <= 1000.
inorder.length == preorder.length
-1000 <= preorder[i], inorder[i] <= 1000

Time Complexity: O(n)

We visit each node exactly once
HashMap lookup is O(1) on average
Building the HashMap takes O(n)
Total: O(n) where n is the number of nodes

Space Complexity: O(n)

HashMap stores n entries: O(n)
Recursion stack depth in worst case (skewed tree): O(n)
Best case (balanced tree) recursion depth: O(log n)
Total: O(n) due to HashMap and worst-case recursion

## **The Big Idea Behind the Solution**

The solution leverages the fundamental properties of tree traversals: in **preorder traversal** (Root-Left-Right), the first element is always the root of the tree, while in **inorder traversal** (Left-Root-Right), elements to the left of the root belong to the left subtree and elements to the right belong to the right subtree. By using the preorder array to identify roots sequentially and the inorder array to partition left and right subtrees, we recursively reconstruct the entire tree. A HashMap is used to quickly locate the root's position in the inorder array, enabling efficient partitioning without repeated linear searches.

---

## **Explain Like I'm 5**

Imagine you have a family tree, but it's all mixed up! You have two lists:

1. **Preorder list**: This tells you the order you meet people when you visit the dad first, then his left kids, then his right kids.
2. **Inorder list**: This tells you the order when you visit all left kids first, then the dad, then all right kids.

Now, here's the magic trick:
- The **first person** in the preorder list is always the **dad** (root)!
- In the inorder list, find that dad. Everyone **before** him is his left family, and everyone **after** him is his right family.
- Now repeat: take the next person from preorder, they're the dad of their own smaller family!

We keep doing this smaller and smaller until we've built the whole family tree back together!

---
 */

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeFromPreOrderInorder {
	
	Map<Integer, Integer> map = new HashMap<>();
	int preorderIndex;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		preorderIndex = 0;
		for(int i = 0; i<inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildTreeHelper(preorder, 0, inorder.length-1);
	}
	public TreeNode buildTreeHelper(int[] preorder, int leftIndex, int rightIndex) {
		if(leftIndex > rightIndex) {
			return null;
		}
		int rootNodeVal  = preorder[preorderIndex++];

		TreeNode root = new TreeNode(rootNodeVal);
		int inOrderIndex = map.get(rootNodeVal);
		root.left = buildTreeHelper(preorder, leftIndex, inOrderIndex-1);
		root.right = buildTreeHelper(preorder, inOrderIndex+1, rightIndex);
		return root;
	}
	   
    // Helper method to print tree in preorder (for verification)
    public void printPreorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
    
    // Helper method to print tree in inorder (for verification)
    public void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
    
    // Helper method to print tree structure
    public void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) return;
        
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);
        
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            } else {
                System.out.println(prefix + (isLeft ? "│   " : "    ") + "├── null");
            }
            
            if (node.right != null) {
                printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            } else {
                System.out.println(prefix + (isLeft ? "│   " : "    ") + "└── null");
            }
        }
    }
    
    public static void main(String[] args) {
    	BinaryTreeFromPreOrderInorder solution = new BinaryTreeFromPreOrderInorder();
        
        // Test Case 1: Standard tree
        System.out.println("========== TEST CASE 1 ==========");
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        System.out.println("Preorder: " + java.util.Arrays.toString(preorder1));
        System.out.println("Inorder:  " + java.util.Arrays.toString(inorder1));
        System.out.println("\nConstructed Tree:");
        TreeNode root1 = solution.buildTree(preorder1, inorder1);
        solution.printTree(root1, "", false);
        System.out.print("\nVerification - Preorder: ");
        solution.printPreorder(root1);
        System.out.print("\nVerification - Inorder:  ");
        solution.printInorder(root1);
        System.out.println("\n");
        
        // Test Case 2: Single node
        System.out.println("\n========== TEST CASE 2 ==========");
        int[] preorder2 = {1};
        int[] inorder2 = {1};
        System.out.println("Preorder: " + java.util.Arrays.toString(preorder2));
        System.out.println("Inorder:  " + java.util.Arrays.toString(inorder2));
        System.out.println("\nConstructed Tree:");
        TreeNode root2 = solution.buildTree(preorder2, inorder2);
        solution.printTree(root2, "", false);
        System.out.print("\nVerification - Preorder: ");
        solution.printPreorder(root2);
        System.out.print("\nVerification - Inorder:  ");
        solution.printInorder(root2);
        System.out.println("\n");
        
        // Test Case 3: Left-skewed tree
        System.out.println("\n========== TEST CASE 3 ==========");
        int[] preorder3 = {1, 2, 3, 4};
        int[] inorder3 = {4, 3, 2, 1};
        System.out.println("Preorder: " + java.util.Arrays.toString(preorder3));
        System.out.println("Inorder:  " + java.util.Arrays.toString(inorder3));
        System.out.println("\nConstructed Tree:");
        TreeNode root3 = solution.buildTree(preorder3, inorder3);
        solution.printTree(root3, "", false);
        System.out.print("\nVerification - Preorder: ");
        solution.printPreorder(root3);
        System.out.print("\nVerification - Inorder:  ");
        solution.printInorder(root3);
        System.out.println("\n");
        
        // Test Case 4: Right-skewed tree
        System.out.println("\n========== TEST CASE 4 ==========");
        int[] preorder4 = {1, 2, 3, 4};
        int[] inorder4 = {1, 2, 3, 4};
        System.out.println("Preorder: " + java.util.Arrays.toString(preorder4));
        System.out.println("Inorder:  " + java.util.Arrays.toString(inorder4));
        System.out.println("\nConstructed Tree:");
        TreeNode root4 = solution.buildTree(preorder4, inorder4);
        solution.printTree(root4, "", false);
        System.out.print("\nVerification - Preorder: ");
        solution.printPreorder(root4);
        System.out.print("\nVerification - Inorder:  ");
        solution.printInorder(root4);
        System.out.println("\n");
        
        // Test Case 5: Balanced tree
        System.out.println("\n========== TEST CASE 5 ==========");
        int[] preorder5 = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder5 = {4, 2, 5, 1, 6, 3, 7};
        System.out.println("Preorder: " + java.util.Arrays.toString(preorder5));
        System.out.println("Inorder:  " + java.util.Arrays.toString(inorder5));
        System.out.println("\nConstructed Tree:");
        TreeNode root5 = solution.buildTree(preorder5, inorder5);
        solution.printTree(root5, "", false);
        System.out.print("\nVerification - Preorder: ");
        solution.printPreorder(root5);
        System.out.print("\nVerification - Inorder:  ");
        solution.printInorder(root5);
        System.out.println("\n");
    }

}
