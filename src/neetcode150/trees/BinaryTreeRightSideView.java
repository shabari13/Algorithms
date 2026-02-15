package neetcode150.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * You are given the root of a binary tree. Return only the values of the nodes that are visible from the right side of the tree, ordered from top to bottom.

Example 1:

Input: root = [1,2,3,null,4,null,5]

Output: [1,3,5]
Explanation:



Example 2:

Input: root = [1,2,3,4,null,null,null,5]

Output: [1,3,4,5]
Explanation:



Example 3:

Input: root = [1,null,2]

Output: [1,2]
Example 4:

Input: root = []

Output: []
Time Complexity: O(n)

Where n is the number of nodes in the tree
We visit each node exactly once during the level order traversal
For each node, we do constant time operations (poll from queue, add to result, offer children to queue)
Total: O(n)

Space Complexity: O(w)

Where w is the maximum width of the tree (maximum number of nodes at any level)
The queue stores at most one complete level of the tree at a time
In the worst case (a complete binary tree), the last level has roughly n/2 nodes
Therefore, space complexity is O(w) which can be O(n) in the worst case
Additionally, the result list stores at most h values (where h is height), which is at most O(n)
Overall space complexity: O(n)
basically have a queue with ndoeds addded from each level. When queue is not empty check the size of the queue and loop through the size.
 When i becomes leadd than leavelsize by 1 that means it is a right most node and add that to result list.
 */
public class BinaryTreeRightSideView {
	
	public List<Integer> rightSideView(TreeNode root) {
		if(root == null)
			return new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			for(int i = 0 ; i<levelSize; i++) {
				TreeNode currentNode = queue.poll();
				if(i == levelSize - 1)
					result.add(currentNode.val);
				if(currentNode.left != null)
					queue.offer(currentNode.left);
				if(currentNode.right != null)
					queue.offer(currentNode.right);
			}
		}
		return result;
	}
	
	 public static void main(String[] args) {
		 BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
	        
	        // Test Case 1: [1,2,3,null,5,null,4]
	        //       1
	        //      / \
	        //     2   3
	        //      \   \
	        //       5   4
	        System.out.println("Test Case 1:");
	        TreeNode root1 = new TreeNode(1);
	        root1.left = new TreeNode(2);
	        root1.right = new TreeNode(3);
	        root1.left.right = new TreeNode(5);
	        root1.right.right = new TreeNode(4);
	        System.out.println("Input: [1,2,3,null,5,null,4]");
	        System.out.println("Output: " + solution.rightSideView(root1));
	        System.out.println("Expected: [1, 3, 4]\n");
	        
	        // Test Case 2: [1,null,3]
	        //     1
	        //      \
	        //       3
	        System.out.println("Test Case 2:");
	        TreeNode root2 = new TreeNode(1);
	        root2.right = new TreeNode(3);
	        System.out.println("Input: [1,null,3]");
	        System.out.println("Output: " + solution.rightSideView(root2));
	        System.out.println("Expected: [1, 3]\n");
	        
	        // Test Case 3: Empty tree
	        System.out.println("Test Case 3:");
	        System.out.println("Input: []");
	        System.out.println("Output: " + solution.rightSideView(null));
	        System.out.println("Expected: []\n");
	        
	        // Test Case 4: Single node
	        System.out.println("Test Case 4:");
	        TreeNode root4 = new TreeNode(1);
	        System.out.println("Input: [1]");
	        System.out.println("Output: " + solution.rightSideView(root4));
	        System.out.println("Expected: [1]\n");
	        
	        // Test Case 5: Left-skewed tree
	        //     1
	        //    /
	        //   2
	        //  /
	        // 3
	        System.out.println("Test Case 5:");
	        TreeNode root5 = new TreeNode(1);
	        root5.left = new TreeNode(2);
	        root5.left.left = new TreeNode(3);
	        System.out.println("Input: [1,2,null,3]");
	        System.out.println("Output: " + solution.rightSideView(root5));
	        System.out.println("Expected: [1, 2, 3]\n");
	    }

}
