package neetcode150.trees;
/*
 * The diameter of a binary tree is defined as the length of the longest path between any two nodes within the tree. The path does not necessarily have to pass through the root.

The length of a path between two nodes in a binary tree is the number of edges between the nodes. Note that the path can not include the same node twice.

Given the root of a binary tree root, return the diameter of the tree.

Example 1:



Input: root = [1,null,2,3,4,5]

Output: 3
Explanation: 3 is the length of the path [1,2,3,5] or [5,3,2,4].

Example 2:

Input: root = [1,2,3]

Output: 2
Constraints:

1 <= number of nodes in the tree <= 100
-100 <= Node.val <= 100

**The Trick:**
For every branch (node), we imagine: "What if the longest path goes through ME?"
- How far down can I go on my LEFT side?
- How far down can I go on my RIGHT side?
- If I add LEFT + RIGHT, that's the longest path passing through me!'

**At each node, we check:**
1. What's the longest path going DOWN the left side?
2. What's the longest path going DOWN the right side?
3. If I connect left and right through THIS node, what's the total path length?
4. Is this the longest path we've seen so far?

## Time Complexity: **O(n)** ⏰

**Why?**
- We visit **every node** exactly once
- At each node, we do constant work (addition, comparison)
- If tree has `n` nodes → we visit `n` nodes → **O(n)**

**Example:** Tree with 100 nodes = 100 visits = O(100) = O(n)

---

## Space Complexity: **O(h)** where h = height 📦

**Why?**
- We use recursion (call stack)
- The deepest the stack gets = height of the tree
- **Best case:** O(log n) for balanced tree (pyramid shape)
- **Worst case:** O(n) for skewed tree (straight line)


We do this for EVERY branch and remember the biggest number we find!

 */
public class DiameterOfBinaryTree {
	int diameter = 0;
	
	public int diameterOfBinaryTree(TreeNode root) {
		diameter = 0;
		calculateHeight(root);
		return diameter;
	}
	
	public int calculateHeight(TreeNode root) {
		if(root == null)
			return 0;
		int leftHeight = calculateHeight(root.left);
		int rightHeight = calculateHeight(root.right);
		int currentDiameter = leftHeight + rightHeight;
		diameter = Math.max(diameter, currentDiameter);
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	 public static void main(String[] args) {
		 DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
	        
	        // Test Case 1: [1,2,3,4,5]
	        //       1
	        //      / \
	        //     2   3
	        //    / \
	        //   4   5
	        TreeNode root1 = new TreeNode(1);
	        root1.left = new TreeNode(2);
	        root1.right = new TreeNode(3);
	        root1.left.left = new TreeNode(4);
	        root1.left.right = new TreeNode(5);
	        
	        System.out.println("Test Case 1:");
	        System.out.println("Tree: [1,2,3,4,5]");
	        System.out.println("       1");
	        System.out.println("      / \\");
	        System.out.println("     2   3");
	        System.out.println("    / \\");
	        System.out.println("   4   5");
	        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root1));
	        System.out.println("(Path: 4 -> 2 -> 5 or 4 -> 2 -> 1 -> 3)");
	        System.out.println();
	        
	        // Test Case 2: [1,2]
	        //     1
	        //    /
	        //   2
	        TreeNode root2 = new TreeNode(1);
	        root2.left = new TreeNode(2);
	        
	        System.out.println("Test Case 2:");
	        System.out.println("Tree: [1,2]");
	        System.out.println("     1");
	        System.out.println("    /");
	        System.out.println("   2");
	        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root2));
	        System.out.println("(Path: 2 -> 1)");
	        System.out.println();
	        
	        // Test Case 3: Single node
	        TreeNode root3 = new TreeNode(1);
	        
	        System.out.println("Test Case 3:");
	        System.out.println("Tree: [1]");
	        System.out.println("     1");
	        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root3));
	        System.out.println("(No path, just one node)");
	        System.out.println();
	        
	        // Test Case 4: Skewed tree [1,2,null,3,null,4]
	        //     1
	        //    /
	        //   2
	        //  /
	        // 3
	        ///
	        //4
	        TreeNode root4 = new TreeNode(1);
	        root4.left = new TreeNode(2);
	        root4.left.left = new TreeNode(3);
	        root4.left.left.left = new TreeNode(4);
	        
	        System.out.println("Test Case 4:");
	        System.out.println("Tree: [1,2,null,3,null,4]");
	        System.out.println("     1");
	        System.out.println("    /");
	        System.out.println("   2");
	        System.out.println("  /");
	        System.out.println(" 3");
	        System.out.println("/");
	        System.out.println("4");
	        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root4));
	        System.out.println("(Path: 4 -> 3 -> 2 -> 1)");
	        System.out.println();
	        
	        // Test Case 5: Balanced tree
	        //       1
	        //      / \
	        //     2   3
	        //    / \   \
	        //   4   5   6
	        //  /
	        // 7
	        TreeNode root5 = new TreeNode(1);
	        root5.left = new TreeNode(2);
	        root5.right = new TreeNode(3);
	        root5.left.left = new TreeNode(4);
	        root5.left.right = new TreeNode(5);
	        root5.right.right = new TreeNode(6);
	        root5.left.left.left = new TreeNode(7);
	        
	        System.out.println("Test Case 5:");
	        System.out.println("Tree: [1,2,3,4,5,null,6,7]");
	        System.out.println("       1");
	        System.out.println("      / \\");
	        System.out.println("     2   3");
	        System.out.println("    / \\   \\");
	        System.out.println("   4   5   6");
	        System.out.println("  /");
	        System.out.println(" 7");
	        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root5));
	        System.out.println("(Path: 7 -> 4 -> 2 -> 5 or 7 -> 4 -> 2 -> 1 -> 3 -> 6)");
	        System.out.println();
	    }

}
