package neetcode150.trees;
/*
 * Within a binary tree, a node x is considered good if the path from the root of the tree to the node x contains no nodes with a value greater than the value of node x

Given the root of a binary tree root, return the number of good nodes within the tree.

Example 1:



Input: root = [2,1,1,3,null,1,5]

Output: 3


Example 2:

Input: root = [1,2,-1,3,4]

Output: 4
Constraints:

1 <= number of nodes in the tree <= 100
-100 <= Node.val <= 100
Time and Space Complexity
Time Complexity: O(N)

N is the number of nodes in the tree
We visit each node exactly once
At each node, we perform constant-time operations (comparison, addition, max calculation)
Therefore, total time = O(N)

Space Complexity: O(H)

H is the height of the tree
Space is used by the recursion call stack
In the worst case (skewed tree), H = N, so O(N)
In the best case (balanced tree), H = log(N), so O(log N)
Average case for a balanced binary tree: O(log N)

The solution uses a depth-first search (DFS) approach to traverse the binary tree while keeping track of the maximum value
 encountered along the path from the root to the current node. A node is considered "good" if
 its value is greater than or equal to all values on the path from the root to that node. By passing the maximum value seen so
  far down to each recursive call, we can efficiently determine if each node qualifies as a "good" node. 
We count 1 for each good node we find and recursively sum up the counts from the left and right subtrees.

Keep track of maxSofar along the path, keep count , check the count for left and right node from root. Return count;
 */
public class CountGoodNodes {
	public int goodNodes(TreeNode root) {
		return countGoodNodes(root, Integer.MIN_VALUE);
	}
	public int countGoodNodes(TreeNode node, int maxSoFar) {
		if(node == null)
			return 0;
		int count = 0;
		if(node.val >= maxSoFar) {
			count = 1;
		}
		maxSoFar = Math.max(maxSoFar, node.val);
		count+= countGoodNodes(node.left, maxSoFar);
		count+= countGoodNodes(node.right, maxSoFar);
		return count;
	}

	// Helper method to print tree (in-order traversal)
    public void printTree(TreeNode node, String prefix) {
        if (node == null) return;
        System.out.println(prefix + node.val);
        if (node.left != null || node.right != null) {
            printTree(node.left, prefix + "├─L: ");
            printTree(node.right, prefix + "├─R: ");
        }
    }
    
    public static void main(String[] args) {
    	CountGoodNodes solution = new CountGoodNodes();
        
        // Test Case 1: [3,1,4,3,null,1,5]
        System.out.println("=== Test Case 1 ===");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);
        
        System.out.println("Tree structure:");
        solution.printTree(root1, "");
        int result1 = solution.goodNodes(root1);
        System.out.println("Number of good nodes: " + result1);
        System.out.println();
        
        // Test Case 2: [3,3,null,4,2]
        System.out.println("=== Test Case 2 ===");
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        
        System.out.println("Tree structure:");
        solution.printTree(root2, "");
        int result2 = solution.goodNodes(root2);
        System.out.println("Number of good nodes: " + result2);
        System.out.println();
        
        // Test Case 3: Single node [1]
        System.out.println("=== Test Case 3 ===");
        TreeNode root3 = new TreeNode(1);
        
        System.out.println("Tree structure:");
        solution.printTree(root3, "");
        int result3 = solution.goodNodes(root3);
        System.out.println("Number of good nodes: " + result3);
        System.out.println();
        
        // Test Case 4: Increasing values [1,2,3,4,5]
        System.out.println("=== Test Case 4 ===");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        
        System.out.println("Tree structure:");
        solution.printTree(root4, "");
        int result4 = solution.goodNodes(root4);
        System.out.println("Number of good nodes: " + result4);
        System.out.println();
        
        // Test Case 5: All same values [5,5,5,5,5]
        System.out.println("=== Test Case 5 ===");
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(5);
        root5.right = new TreeNode(5);
        root5.left.left = new TreeNode(5);
        root5.left.right = new TreeNode(5);
        
        System.out.println("Tree structure:");
        solution.printTree(root5, "");
        int result5 = solution.goodNodes(root5);
        System.out.println("Number of good nodes: " + result5);
    }
	

}
