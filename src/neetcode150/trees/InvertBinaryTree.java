package neetcode150.trees;
/*
 * You are given the root of a binary tree root. Invert the binary tree and return its root.

Example 1:



Input: root = [1,2,3,4,5,6,7]

Output: [1,3,2,7,6,5,4]
Example 2:



Input: root = [3,2,1]

Output: [3,1,2]
Example 3:

Input: root = []

Output: []
Constraints:

0 <= The number of nodes in the tree <= 100.
-100 <= Node.val <= 100

⏱️ Time Complexity: O(n)

We visit each node in the tree exactly once
For a tree with n nodes, we perform a constant amount of work (swapping) at each node
So total time = O(n)

💾 Space Complexity: O(h) or O(n) in worst case

The space is used by the recursion call stack
h is the height of the tree
In the best case (balanced tree): O(log n)
In the worst case (skewed tree): O(n)
For example, if the tree looks like a linked list (all nodes only have left or right children), the height equals n, so space is O(n)

basically swap left and right node then call recursive call on both left and right nodes.
 */
public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		if(root == null)
			return null;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
	 public static void main(String[] args) {
		 InvertBinaryTree solution = new InvertBinaryTree();
	        
	        // Test Case 1: [4,2,7,1,3,6,9]
	        System.out.println("Test Case 1:");
	        TreeNode root1 = new TreeNode(4);
	        root1.left = new TreeNode(2);
	        root1.right = new TreeNode(7);
	        root1.left.left = new TreeNode(1);
	        root1.left.right = new TreeNode(3);
	        root1.right.left = new TreeNode(6);
	        root1.right.right = new TreeNode(9);
	        
	        System.out.println("Before inversion:");
	        printTree(root1);
	        TreeNode inverted1 = solution.invertTree(root1);
	        System.out.println("\nAfter inversion:");
	        printTree(inverted1);
	        System.out.println("\n");
	        
	        // Test Case 2: [2,1,3]
	        System.out.println("Test Case 2:");
	        TreeNode root2 = new TreeNode(2);
	        root2.left = new TreeNode(1);
	        root2.right = new TreeNode(3);
	        
	        System.out.println("Before inversion:");
	        printTree(root2);
	        TreeNode inverted2 = solution.invertTree(root2);
	        System.out.println("\nAfter inversion:");
	        printTree(inverted2);
	        System.out.println("\n");
	        
	        // Test Case 3: Empty tree
	        System.out.println("Test Case 3:");
	        TreeNode root3 = null;
	        System.out.println("Before inversion: null");
	        TreeNode inverted3 = solution.invertTree(root3);
	        System.out.println("After inversion: " + inverted3);
	        System.out.println();
	        
	        // Test Case 4: Single node
	        System.out.println("Test Case 4:");
	        TreeNode root4 = new TreeNode(1);
	        System.out.println("Before inversion:");
	        printTree(root4);
	        TreeNode inverted4 = solution.invertTree(root4);
	        System.out.println("\nAfter inversion:");
	        printTree(inverted4);
	    }
	    
	    // Helper method to print tree (level order)
	    public static void printTree(TreeNode root) {
	        if (root == null) {
	            System.out.print("null");
	            return;
	        }
	        
	        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
	        queue.offer(root);
	        
	        while (!queue.isEmpty()) {
	            TreeNode node = queue.poll();
	            if (node != null) {
	                System.out.print(node.val + " ");
	                queue.offer(node.left);
	                queue.offer(node.right);
	            } else {
	                System.out.print("null ");
	            }
	        }
	    }
}
