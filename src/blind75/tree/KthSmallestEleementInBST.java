package blind75.tree;

public class KthSmallestEleementInBST {
	int result ;
	int count;

	public int kthSmallest(TreeNode root, int k) {
		 count = 0;
	     result = 0;
		inorderTraversal(root, k);
		return result;
	}
	
	public void inorderTraversal(TreeNode node, int k) {
		if (node == null)
			return;
		
		inorderTraversal(node.left, k);
		
		count++;
		
		if(count == k) {
			result = node.val;
			return;
		}
		
		inorderTraversal(node.right, k);
		
	}
	
	 public static void main(String[] args) {
		 KthSmallestEleementInBST solution = new KthSmallestEleementInBST();
	        
	        // Test Case 1: BST = [3,1,4,null,2], k = 1
	        System.out.println("Test Case 1:");
	        TreeNode root1 = new TreeNode(3);
	        root1.left = new TreeNode(1);
	        root1.right = new TreeNode(4);
	        root1.left.right = new TreeNode(2);
	        System.out.println("BST: [3,1,4,null,2]");
	        System.out.println("k = 1");
	        System.out.println("Output: " + solution.kthSmallest(root1, 1));
	        System.out.println("Expected: 1\n");
	        
	        // Test Case 2: BST = [5,3,6,2,4,null,null,1], k = 3
	        System.out.println("Test Case 2:");
	        TreeNode root2 = new TreeNode(5);
	        root2.left = new TreeNode(3);
	        root2.right = new TreeNode(6);
	        root2.left.left = new TreeNode(2);
	        root2.left.right = new TreeNode(4);
	        root2.left.left.left = new TreeNode(1);
	        System.out.println("BST: [5,3,6,2,4,null,null,1]");
	        System.out.println("k = 3");
	        System.out.println("Output: " + solution.kthSmallest(root2, 3));
	        System.out.println("Expected: 3\n");
	        
	        // Test Case 3: BST = [5,3,6,2,4,null,null,1], k = 5
	        System.out.println("Test Case 3:");
	        System.out.println("BST: [5,3,6,2,4,null,null,1]");
	        System.out.println("k = 5");
	        System.out.println("Output: " + solution.kthSmallest(root2, 5));
	        System.out.println("Expected: 5\n");
	        
	        // Test Case 4: Simple BST = [2,1,3], k = 2
	        System.out.println("Test Case 4:");
	        TreeNode root3 = new TreeNode(2);
	        root3.left = new TreeNode(1);
	        root3.right = new TreeNode(3);
	        System.out.println("BST: [2,1,3]");
	        System.out.println("k = 2");
	        System.out.println("Output: " + solution.kthSmallest(root3, 2));
	        System.out.println("Expected: 2\n");
	    }
}
