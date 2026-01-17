package blind75.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000

## Time and Space Complexity

### **Time Complexity: O(n)**
- **n** = number of nodes in the tree
- We visit each apple (node) exactly **once**
- We add it to our result once
- So if there are 100 apples, we do 100 operations

### **Space Complexity: O(w)**
- **w** = maximum width of the tree (the level with the most nodes)
- The queue holds at most all the nodes at one level
- For a complete binary tree, the last level can have up to **n/2** nodes
- So worst case is **O(n)** space
- The result also stores all n nodes, so overall **O(n)** space


### The Magic Waiting Line (Queue)

Think of a **queue** like a line at an ice cream shop. The first person who gets in line gets ice cream first. That's called "First In, First Out" (FIFO).

We use this waiting line to remember which apples (tree nodes) we need to look at next!

---

 */
public class BinaryTreeLevelOrderTraversal {
	
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		
		if(root == null)
			return null;
		
		List<List<Integer>> result = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {	
			int levelSize = queue.size();
			List<Integer> currentLevel = new ArrayList<>();
			for (int i = 0; i < levelSize; i++)  {
				
				TreeNode node = queue.poll();
				currentLevel.add(node.val);
				if(node.left != null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
			result.add(currentLevel);
		}
		return result;
	}
	
	 public static void main(String[] args) {
		 BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();
	        
	        // Test Case 1: Regular tree
	        //       3
	        //      / \
	        //     9   20
	        //        /  \
	        //       15   7
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(9);
	        root1.right = new TreeNode(20);
	        root1.right.left = new TreeNode(15);
	        root1.right.right = new TreeNode(7);
	        
	        System.out.println("Test Case 1:");
	        System.out.println("Tree structure:");
	        System.out.println("       3");
	        System.out.println("      / \\");
	        System.out.println("     9   20");
	        System.out.println("        /  \\");
	        System.out.println("       15   7");
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
	        
	        // Test Case 5: Complete binary tree
	        //       1
	        //      / \
	        //     2   3
	        //    / \ / \
	        //   4  5 6  7
	        TreeNode root5 = new TreeNode(1);
	        root5.left = new TreeNode(2);
	        root5.right = new TreeNode(3);
	        root5.left.left = new TreeNode(4);
	        root5.left.right = new TreeNode(5);
	        root5.right.left = new TreeNode(6);
	        root5.right.right = new TreeNode(7);
	        
	        System.out.println("Test Case 5:");
	        System.out.println("Tree structure:");
	        System.out.println("       1");
	        System.out.println("      / \\");
	        System.out.println("     2   3");
	        System.out.println("    / \\ / \\");
	        System.out.println("   4  5 6  7");
	        System.out.println("Level Order: " + solution.levelOrder(root5));
	    }

}
