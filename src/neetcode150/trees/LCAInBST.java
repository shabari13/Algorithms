package neetcode150.trees;

public class LCAInBST {
    // Helper method to build a BST from array (level order)
    public static TreeNode buildBST(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            
            // Left child
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;
            
            // Right child
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
    
    // Helper method to find a node with given value
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        
        return findNode(root.right, val);
    }
    
    public static void main(String[] args) {
    	LowestCommonAncestor solution = new LowestCommonAncestor();
        
        // Test Case 1: Standard BST
        System.out.println("=== Test Case 1 ===");
        Integer[] tree1 = {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        TreeNode root1 = buildBST(tree1);
        TreeNode p1 = findNode(root1, 2);
        TreeNode q1 = findNode(root1, 8);
        TreeNode lca1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Tree: [6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println("p = 2, q = 8");
        System.out.println("LCA = " + lca1.val);
        System.out.println("Explanation: LCA of 2 and 8 is 6\n");
        
        // Test Case 2: Both nodes in left subtree
        System.out.println("=== Test Case 2 ===");
        TreeNode p2 = findNode(root1, 2);
        TreeNode q2 = findNode(root1, 4);
        TreeNode lca2 = solution.lowestCommonAncestor(root1, p2, q2);
        System.out.println("Tree: [6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println("p = 2, q = 4");
        System.out.println("LCA = " + lca2.val);
        System.out.println("Explanation: LCA of 2 and 4 is 2 (ancestor can be the node itself)\n");
        
        // Test Case 3: Simple BST
        System.out.println("=== Test Case 3 ===");
        Integer[] tree3 = {2, 1};
        TreeNode root3 = buildBST(tree3);
        TreeNode p3 = findNode(root3, 2);
        TreeNode q3 = findNode(root3, 1);
        TreeNode lca3 = solution.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Tree: [2,1]");
        System.out.println("p = 2, q = 1");
        System.out.println("LCA = " + lca3.val);
        System.out.println("Explanation: LCA of 2 and 1 is 2\n");
        
        // Test Case 4: Both nodes in right subtree
        System.out.println("=== Test Case 4 ===");
        TreeNode p4 = findNode(root1, 7);
        TreeNode q4 = findNode(root1, 9);
        TreeNode lca4 = solution.lowestCommonAncestor(root1, p4, q4);
        System.out.println("Tree: [6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println("p = 7, q = 9");
        System.out.println("LCA = " + lca4.val);
        System.out.println("Explanation: LCA of 7 and 9 is 8\n");
        
        // Test Case 5: Nodes at different levels
        System.out.println("=== Test Case 5 ===");
        TreeNode p5 = findNode(root1, 0);
        TreeNode q5 = findNode(root1, 5);
        TreeNode lca5 = solution.lowestCommonAncestor(root1, p5, q5);
        System.out.println("Tree: [6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println("p = 0, q = 5");
        System.out.println("LCA = " + lca5.val);
        System.out.println("Explanation: LCA of 0 and 5 is 2\n");
    }
}

