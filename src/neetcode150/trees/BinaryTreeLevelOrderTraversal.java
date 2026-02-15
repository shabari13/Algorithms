package neetcode150.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * Given a binary tree root, return the level order traversal of it as a nested list, where each sublist contains the values of nodes at a particular level in the tree, from left to right.

Example 1:



Input: root = [1,2,3,4,5,6,7]

Output: [[1],[2,3],[4,5,6,7]]
Example 2:

Input: root = [1]

Output: [[1]]
Example 3:

Input: root = []

Output: []
Constraints:

0 <= The number of nodes in the tree <= 1000.
-1000 <= Node.val <= 1000
Time and Space Complexity
Time Complexity: O(n)

Where n is the number of nodes in the tree
We visit each node exactly once
For each node, we perform constant time operations (add to list, add children to queue)
Total: O(n)

Space Complexity: O(w)

Where w is the maximum width of the tree (maximum number of nodes at any level)
The queue stores at most all nodes at the widest level
For a complete binary tree, the maximum width is approximately n/2 at the last level
In the worst case (complete binary tree), this is O(n)
The result list also stores all n nodes, contributing O(n)
Overall: O(n) for both the queue and result storage
basically have a queue which will keep track of nodes in the current level. Als keep track of the size of the current level
then loop it through and get each node and add it to list.Then add this to result list.
 */
public class BinaryTreeLevelOrderTraversal{
	public List<List<Integer>> levelOrder(TreeNode root) {
		if(root == null) {
			return null;
		}
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> currentList = new ArrayList<>();
			for(int i=0; i<levelSize; i++) {
				TreeNode node = queue.poll();
				currentList.add(node.val);
				if(node.left != null)
					queue.add(node.left);
				if(node.right != null)
					queue.add(node.right);	
			}
			result.add(currentList);
		}
		return result;
	}
	 public static void main(String[] args) {
		 BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();
	        
	        // Test Case 1: Standard tree
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
	        
	        System.out.println("Test Case 1:");
	        System.out.println("Tree structure:");
	        System.out.println("       3");
	        System.out.println("      / \\");
	        System.out.println("     9  20");
	        System.out.println("       /  \\");
	        System.out.println("      15   7");
	        System.out.println("Level Order: " + solution.levelOrder(root1));
	        System.out.println();
	        
	        // Test Case 2: Single node
	        TreeNode root2 = new TreeNode(1);
	        System.out.println("Test Case 2:");
	        System.out.println("Tree structure: 1");
	        System.out.println("Level Order: " + solution.levelOrder(root2));
	        System.out.println();
	        
	        // Test Case 3: Empty tree
	        TreeNode root3 = null;
	        System.out.println("Test Case 3:");
	        System.out.println("Tree structure: (empty)");
	        System.out.println("Level Order: " + solution.levelOrder(root3));
	        System.out.println();
	        
	        // Test Case 4: Left-skewed tree
	        //     1
	        //    /
	        //   2
	        //  /
	        // 3
	        TreeNode root4 = new TreeNode(1);
	        root4.left = new TreeNode(2);
	        root4.left.left = new TreeNode(3);
	        
	        System.out.println("Test Case 4:");
	        System.out.println("Tree structure:");
	        System.out.println("     1");
	        System.out.println("    /");
	        System.out.println("   2");
	        System.out.println("  /");
	        System.out.println(" 3");
	        System.out.println("Level Order: " + solution.levelOrder(root4));
	        System.out.println();
	        
	        // Test Case 5: Larger balanced tree
	        //        1
	        //      /   \
	        //     2     3
	        //    / \   / \
	        //   4   5 6   7
	        TreeNode root5 = new TreeNode(1);
	        root5.left = new TreeNode(2);
	        root5.right = new TreeNode(3);
	        root5.left.left = new TreeNode(4);
	        root5.left.right = new TreeNode(5);
	        root5.right.left = new TreeNode(6);
	        root5.right.right = new TreeNode(7);
	        
	        System.out.println("Test Case 5:");
	        System.out.println("Tree structure:");
	        System.out.println("        1");
	        System.out.println("      /   \\");
	        System.out.println("     2     3");
	        System.out.println("    / \\   / \\");
	        System.out.println("   4   5 6   7");
	        System.out.println("Level Order: " + solution.levelOrder(root5));
	    }

}
