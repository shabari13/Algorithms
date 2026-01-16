package blind75.tree;
/*
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

 

Example 1:


Input: p = [1,2,3], q = [1,2,3]
Output: true
Example 2:


Input: p = [1,2], q = [1,null,2]
Output: false
Example 3:


Input: p = [1,2,1], q = [1,1,2]
Output: false

Time Complexity: O(min(N, M))

N is the number of nodes in tree p
M is the number of nodes in tree q
We visit each node at most once
We stop early if we find a difference
In the worst case (identical trees), we visit all nodes

Space Complexity: O(min(H1, H2))

H1 is the height of tree p
H2 is the height of tree q
This is the space used by the recursion call stack
In the worst case (skewed tree), this is O(N)
In the best case (balanced tree), this is O(log N)
 * 
 */
public class SameTree {
	
	 public boolean isSameTree(TreeNode p, TreeNode q) {
		 if(p == null && q == null)
			 return true;
		 if(p == null || q == null)
			 return false;
		 
		 if(p.val != q.val)
			 return false;
		 
		 return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
	 }
	 
	 public static void main(String[] args) {
		 SameTree solution = new SameTree();
	        
	        // Test Case 1: Both trees are the same
	        // Tree 1:     1
	        //            / \
	        //           2   3
	        TreeNode p1 = new TreeNode(1);
	        p1.left = new TreeNode(2);
	        p1.right = new TreeNode(3);
	        
	        TreeNode q1 = new TreeNode(1);
	        q1.left = new TreeNode(2);
	        q1.right = new TreeNode(3);
	        
	        System.out.println("Test Case 1 - Same trees [1,2,3] and [1,2,3]:");
	        System.out.println("Result: " + solution.isSameTree(p1, q1));
	        System.out.println();
	        
	        // Test Case 2: Different structure
	        // Tree p:  1          Tree q:  1
	        //         /                     \
	        //        2                       2
	        TreeNode p2 = new TreeNode(1);
	        p2.left = new TreeNode(2);
	        
	        TreeNode q2 = new TreeNode(1);
	        q2.right = new TreeNode(2);
	        
	        System.out.println("Test Case 2 - Different structure [1,2] and [1,null,2]:");
	        System.out.println("Result: " + solution.isSameTree(p2, q2));
	        System.out.println();
	        
	        // Test Case 3: Different values
	        // Tree p:  1          Tree q:  1
	        //         / \                 / \
	        //        2   1               1   2
	        TreeNode p3 = new TreeNode(1);
	        p3.left = new TreeNode(2);
	        p3.right = new TreeNode(1);
	        
	        TreeNode q3 = new TreeNode(1);
	        q3.left = new TreeNode(1);
	        q3.right = new TreeNode(2);
	        
	        System.out.println("Test Case 3 - Different values [1,2,1] and [1,1,2]:");
	        System.out.println("Result: " + solution.isSameTree(p3, q3));
	        System.out.println();
	        
	        // Test Case 4: Both trees are empty
	        System.out.println("Test Case 4 - Both empty trees:");
	        System.out.println("Result: " + solution.isSameTree(null, null));
	        System.out.println();
	        
	        // Test Case 5: One tree is empty
	        TreeNode p5 = new TreeNode(1);
	        System.out.println("Test Case 5 - One empty tree [1] and []:");
	        System.out.println("Result: " + solution.isSameTree(p5, null));
	    }

}
