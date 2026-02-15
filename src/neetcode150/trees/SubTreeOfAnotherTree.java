package neetcode150.trees;
/*
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:



Input: root = [1,2,3,4,5], subRoot = [2,4,5]

Output: true
Example 2:



Input: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]

Output: false
Constraints:

1 <= The number of nodes in both trees <= 100.
-100 <= root.val, subRoot.val <= 100
 */
public class SubTreeOfAnotherTree {
	
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if(root == null)
			return false;
		if(isSameTree(root, subRoot))
			return true;
		
		return (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
	}
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null)
			return true;
		if(p == null || q == null)
			return false;
		if(p.val != q.val)
			return false;
		return (isSameTree(p.left, q.left) && isSameTree(p.right , q.right));
	}
	
	   public static void main(String[] args) {
		   SubTreeOfAnotherTree solution = new SubTreeOfAnotherTree();
	        
	        // Example 1: subRoot exists in root
	        System.out.println("=== Example 1 ===");
	        // Root tree:      3
	        //               /   \
	        //              4     5
	        //             / \
	        //            1   2
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(4);
	        root1.right = new TreeNode(5);
	        root1.left.left = new TreeNode(1);
	        root1.left.right = new TreeNode(2);
	        
	        // SubRoot tree:  4
	        //               / \
	        //              1   2
	        TreeNode subRoot1 = new TreeNode(4);
	        subRoot1.left = new TreeNode(1);
	        subRoot1.right = new TreeNode(2);
	        
	        boolean result1 = solution.isSubtree(root1, subRoot1);
	        System.out.println("Is subRoot1 a subtree of root1? " + result1);
	        System.out.println("Expected: true\n");
	        
	        // Example 2: subRoot does NOT exist in root
	        System.out.println("=== Example 2 ===");
	        // Root tree:      3
	        //               /   \
	        //              4     5
	        //             / \
	        //            1   2
	        //               /
	        //              0
	        TreeNode root2 = new TreeNode(3);
	        root2.left = new TreeNode(4);
	        root2.right = new TreeNode(5);
	        root2.left.left = new TreeNode(1);
	        root2.left.right = new TreeNode(2);
	        root2.left.right.left = new TreeNode(0);
	        
	        // SubRoot tree:  4
	        //               / \
	        //              1   2
	        TreeNode subRoot2 = new TreeNode(4);
	        subRoot2.left = new TreeNode(1);
	        subRoot2.right = new TreeNode(2);
	        
	        boolean result2 = solution.isSubtree(root2, subRoot2);
	        System.out.println("Is subRoot2 a subtree of root2? " + result2);
	        System.out.println("Expected: false\n");
	        
	        // Example 3: Single node matching
	        System.out.println("=== Example 3 ===");
	        TreeNode root3 = new TreeNode(1);
	        TreeNode subRoot3 = new TreeNode(1);
	        
	        boolean result3 = solution.isSubtree(root3, subRoot3);
	        System.out.println("Is subRoot3 a subtree of root3? " + result3);
	        System.out.println("Expected: true\n");
	        
	        // Example 4: Null subRoot
	        System.out.println("=== Example 4 ===");
	        TreeNode root4 = new TreeNode(1);
	        root4.left = new TreeNode(2);
	        TreeNode subRoot4 = null;
	        
	        boolean result4 = solution.isSubtree(root4, subRoot4);
	        System.out.println("Is null a subtree? " + result4);
	        System.out.println("Expected: false (edge case)\n");
	    }

}
