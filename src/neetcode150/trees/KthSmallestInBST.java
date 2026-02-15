package neetcode150.trees;
/*
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) in the tree.

A binary search tree satisfies the following constraints:

The left subtree of every node contains only nodes with keys less than the node's key.
The right subtree of every node contains only nodes with keys greater than the node's key.
Both the left and right subtrees are also binary search trees.
Example 1:



Input: root = [2,1,3], k = 1

Output: 1
Example 2:



Input: root = [4,3,5,2,null], k = 4

Output: 5
Constraints:

1 <= k <= The number of nodes in the tree <= 1000.
0 <= Node.val <= 1000

Time Complexity: O(H + k)

H is the height of the tree
In the worst case, we need to traverse down to the leftmost node (takes O(H) time)
Then we visit k nodes in the inorder traversal
Best case: O(k) when k is small and near the left side
Worst case: O(n) when k = n (we visit all nodes)
Average case for balanced BST: O(log n + k)

Space Complexity: O(H)

The space is used by the recursion call stack
The maximum depth of recursion is the height of the tree
Best case (balanced tree): O(log n)
Worst case (skewed tree): O(n) - when the tree looks like a linked list
We use only two integer variables (count and result), which is O(1) additional space

in inordertraversal, basically it will have elements in an ascending order. so you keep a count and
 when it becomes k you basically extract that value and assign it to result.


 */
public class KthSmallestInBST {

	private int count ;
	private int result;
	public int kthSmallest(TreeNode root, int k) {
		count = 0;
		result = 0;
		inrderTraversal(root, k);
		return result;
		
	}
	
	public void inrderTraversal(TreeNode node, int k) {
		if(node == null)
			return;
		inrderTraversal(node.left, k);
		count++;
		if(count == k) {
			result = node.val;
		}
		inrderTraversal(node.right, k);
		
	}
	  public static void main(String[] args) {
		  KthSmallestInBST solution = new KthSmallestInBST();
	        
	        // Test Case 1: BST = [3,1,4,null,2], k = 1
	        //       3
	        //      / \
	        //     1   4
	        //      \
	        //       2
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(1);
	        root1.right = new TreeNode(4);
	        root1.left.right = new TreeNode(2);
	        
	        System.out.println("Test Case 1:");
	        System.out.println("BST: [3,1,4,null,2], k = 1");
	        System.out.println("Output: " + solution.kthSmallest(root1, 1));
	        System.out.println("Expected: 1\n");
	        
	        // Test Case 2: Same tree, k = 2
	        System.out.println("Test Case 2:");
	        System.out.println("BST: [3,1,4,null,2], k = 2");
	        System.out.println("Output: " + solution.kthSmallest(root1, 2));
	        System.out.println("Expected: 2\n");
	        
	        // Test Case 3: BST = [5,3,6,2,4,null,null,1], k = 3
	        //          5
	        //         / \
	        //        3   6
	        //       / \
	        //      2   4
	        //     /
	        //    1
	        TreeNode root2 = new TreeNode(5);
	        root2.left = new TreeNode(3);
	        root2.right = new TreeNode(6);
	        root2.left.left = new TreeNode(2);
	        root2.left.right = new TreeNode(4);
	        root2.left.left.left = new TreeNode(1);
	        
	        System.out.println("Test Case 3:");
	        System.out.println("BST: [5,3,6,2,4,null,null,1], k = 3");
	        System.out.println("Output: " + solution.kthSmallest(root2, 3));
	        System.out.println("Expected: 3\n");
	        
	        // Test Case 4: Same tree, k = 5
	        System.out.println("Test Case 4:");
	        System.out.println("BST: [5,3,6,2,4,null,null,1], k = 5");
	        System.out.println("Output: " + solution.kthSmallest(root2, 5));
	        System.out.println("Expected: 5\n");
	        
	        // Test Case 5: Single node, k = 1
	        TreeNode root3 = new TreeNode(1);
	        System.out.println("Test Case 5:");
	        System.out.println("BST: [1], k = 1");
	        System.out.println("Output: " + solution.kthSmallest(root3, 1));
	        System.out.println("Expected: 1\n");
	    }
}
