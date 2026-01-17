package blind75.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/*
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 


Time Complexity: O(n)

We visit each node exactly once
We build the hashmap in O(n) time
Looking up positions in the map is O(1)
Total: O(n) + O(n) = O(n)

Space Complexity: O(n)

HashMap stores n entries: O(n)
Recursion stack in worst case (skewed tree): O(n)
In best case (balanced tree): O(log n) for recursion
Total: O(n) due to the hashmap

The Big Idea
Think of it like making a sandwich tower:

Preorder tells you: "Pick the bread first, then the cheese, then the tomato"
Inorder tells you: "When you look from bottom to top, you see cheese, bread, tomato"

From this, you can figure out where each piece goes!
 */
public class ConstructBinaryTree {
	
	public int preIndex = 0;
	
	public Map<Integer, Integer> inorderMap = new HashMap<>();
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		for(int i =0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);
		}
		return buildTreeHelper(preorder, 0, inorder.length - 1);
		
	}

	public TreeNode buildTreeHelper(int[] preorder, int left, int right) {
		if(left > right)
			return null;
		
		int rootNodeVal = preorder[preIndex++];
		TreeNode root = new TreeNode(rootNodeVal);
		int inorderIndex = inorderMap.get(rootNodeVal);
		root.left = buildTreeHelper(preorder, left, inorderIndex-1);
		root.right = buildTreeHelper(preorder, inorderIndex+1, right);
		return root;
		
	}
	
	 public static void printTree(TreeNode root) {
	        if (root == null) {
	            System.out.println("[]");
	            return;
	        }
	        
	        Queue<TreeNode> queue = new LinkedList<>();
	        queue.offer(root);
	        List<String> result = new ArrayList<>();
	        
	        while (!queue.isEmpty()) {
	            TreeNode node = queue.poll();
	            if (node != null) {
	                result.add(String.valueOf(node.val));
	                queue.offer(node.left);
	                queue.offer(node.right);
	            } else {
	                result.add("null");
	            }
	        }
	        
	        // Remove trailing nulls
	        while (result.size() > 0 && result.get(result.size() - 1).equals("null")) {
	            result.remove(result.size() - 1);
	        }
	        
	        System.out.println(result);
	    }
	    
	    public static void main(String[] args) {
	    	ConstructBinaryTree solution = new ConstructBinaryTree();
	        
	        // Test Case 1
	        System.out.println("Test Case 1:");
	        int[] preorder1 = {3, 9, 20, 15, 7};
	        int[] inorder1 = {9, 3, 15, 20, 7};
	        System.out.println("Preorder: " + Arrays.toString(preorder1));
	        System.out.println("Inorder: " + Arrays.toString(inorder1));
	        TreeNode tree1 = solution.buildTree(preorder1, inorder1);
	        System.out.print("Output Tree (level order): ");
	        printTree(tree1);
	        System.out.println();
	        
	        // Reset for next test
	        solution = new ConstructBinaryTree();
	        
	        // Test Case 2
	        System.out.println("Test Case 2:");
	        int[] preorder2 = {-1};
	        int[] inorder2 = {-1};
	        System.out.println("Preorder: " + Arrays.toString(preorder2));
	        System.out.println("Inorder: " + Arrays.toString(inorder2));
	        TreeNode tree2 = solution.buildTree(preorder2, inorder2);
	        System.out.print("Output Tree (level order): ");
	        printTree(tree2);
	        System.out.println();
	        
	        // Reset for next test
	        solution = new ConstructBinaryTree();
	        
	        // Test Case 3
	        System.out.println("Test Case 3:");
	        int[] preorder3 = {1, 2, 3, 4, 5};
	        int[] inorder3 = {3, 2, 4, 1, 5};
	        System.out.println("Preorder: " + Arrays.toString(preorder3));
	        System.out.println("Inorder: " + Arrays.toString(inorder3));
	        TreeNode tree3 = solution.buildTree(preorder3, inorder3);
	        System.out.print("Output Tree (level order): ");
	        printTree(tree3);
	        System.out.println();
	        
	        // Reset for next test
	        solution = new ConstructBinaryTree();
	        
	        // Test Case 4
	        System.out.println("Test Case 4:");
	        int[] preorder4 = {1, 2, 4, 5, 3, 6, 7};
	        int[] inorder4 = {4, 2, 5, 1, 6, 3, 7};
	        System.out.println("Preorder: " + Arrays.toString(preorder4));
	        System.out.println("Inorder: " + Arrays.toString(inorder4));
	        TreeNode tree4 = solution.buildTree(preorder4, inorder4);
	        System.out.print("Output Tree (level order): ");
	        printTree(tree4);
	    }
}
