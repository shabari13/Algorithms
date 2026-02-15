package neetcode150.trees;
/*
 * Given the roots of two binary trees p and q, return true if the trees are equivalent, otherwise return false.

Two binary trees are considered equivalent if they share the exact same structure and the nodes have the same values.

Example 1:



Input: p = [1,2,3], q = [1,2,3]

Output: true
Example 2:



Input: p = [4,7], q = [4,null,7]

Output: false
Example 3:



Input: p = [1,2,3], q = [1,3,2]

Output: false
Constraints:

0 <= The number of nodes in both trees <= 100.
-100 <= Node.val <= 100


The solution uses a recursive approach to check if two binary trees are identical. We compare the trees node by node,
 starting from the roots. At each step, we check if both nodes have the same value and then recursively verify that their left subtrees are 
 identical and their right subtrees are identical. If at any point we find a mismatch in values or structure, we return false. The base case handles
  when both nodes are null (which means we've successfully matched that branch) or when only one is null (which means the trees differ in structure).
  
  Time Complexity: O(min(N, M))

N = number of nodes in tree p
M = number of nodes in tree q
We visit each node once, but we stop early if we find a mismatch
In the worst case (when trees are identical), we visit all nodes in the smaller tree
Example: If tree p has 100 nodes and tree q has 50 nodes, we visit at most 50 nodes

Space Complexity: O(min(H₁, H₂))

H₁ = height of tree p
H₂ = height of tree q
The space is used by the recursion call stack
In the worst case (skewed tree), height = number of nodes, so O(N)
In the best case (balanced tree), height = log(N), so O(log N)
We only go as deep as the smaller tree's height because if one tree is shorter, we'll detect the difference and return early

 */
public class SameBinaryTree {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null) {
			return true;
		}
		if(p == null || q == null) {
			return false;
		}
		if(p.val != q.val) {
			return false;
		}
		
		return (isSameTree(p.left , q.left) && isSameTree(p.right, q.right));
	}
	
	 public static void main(String[] args) {
		 SameBinaryTree solution = new SameBinaryTree();
	        
	        // Test Case 1: Both trees are identical
	        // Tree 1:     1
	        //           /   \
	        //          2     3
	        TreeNode tree1 = new TreeNode(1);
	        tree1.left = new TreeNode(2);
	        tree1.right = new TreeNode(3);
	        
	        TreeNode tree2 = new TreeNode(1);
	        tree2.left = new TreeNode(2);
	        tree2.right = new TreeNode(3);
	        
	        System.out.println("Test Case 1 - Identical trees [1,2,3] and [1,2,3]:");
	        System.out.println("Result: " + solution.isSameTree(tree1, tree2));
	        System.out.println();
	        
	        // Test Case 2: Different structure
	        // Tree 3:     1          Tree 4:     1
	        //           /                          \
	        //          2                            2
	        TreeNode tree3 = new TreeNode(1);
	        tree3.left = new TreeNode(2);
	        
	        TreeNode tree4 = new TreeNode(1);
	        tree4.right = new TreeNode(2);
	        
	        System.out.println("Test Case 2 - Different structure [1,2] and [1,null,2]:");
	        System.out.println("Result: " + solution.isSameTree(tree3, tree4));
	        System.out.println();
	        
	        // Test Case 3: Different values
	        // Tree 5:     1          Tree 6:     1
	        //           /   \                  /   \
	        //          2     1                1     2
	        TreeNode tree5 = new TreeNode(1);
	        tree5.left = new TreeNode(2);
	        tree5.right = new TreeNode(1);
	        
	        TreeNode tree6 = new TreeNode(1);
	        tree6.left = new TreeNode(1);
	        tree6.right = new TreeNode(2);
	        
	        System.out.println("Test Case 3 - Different values [1,2,1] and [1,1,2]:");
	        System.out.println("Result: " + solution.isSameTree(tree5, tree6));
	        System.out.println();
	        
	        // Test Case 4: Both trees are null
	        System.out.println("Test Case 4 - Both trees are null:");
	        System.out.println("Result: " + solution.isSameTree(null, null));
	        System.out.println();
	        
	        // Test Case 5: One tree is null
	        TreeNode tree7 = new TreeNode(1);
	        System.out.println("Test Case 5 - One tree null, other has [1]:");
	        System.out.println("Result: " + solution.isSameTree(tree7, null));
	        System.out.println();
	        
	        // Test Case 6: Larger identical trees
	        // Tree 8 & 9:      1
	        //                /   \
	        //               2     3
	        //              / \
	        //             4   5
	        TreeNode tree8 = new TreeNode(1);
	        tree8.left = new TreeNode(2);
	        tree8.right = new TreeNode(3);
	        tree8.left.left = new TreeNode(4);
	        tree8.left.right = new TreeNode(5);
	        
	        TreeNode tree9 = new TreeNode(1);
	        tree9.left = new TreeNode(2);
	        tree9.right = new TreeNode(3);
	        tree9.left.left = new TreeNode(4);
	        tree9.left.right = new TreeNode(5);
	        
	        System.out.println("Test Case 6 - Larger identical trees:");
	        System.out.println("Result: " + solution.isSameTree(tree8, tree9));
	    }
}
