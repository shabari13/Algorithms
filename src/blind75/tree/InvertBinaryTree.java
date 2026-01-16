package blind75.tree;
/*
 * 
 * Given the root of a binary tree, invert the tree, and return its root.

 

Example 1:


Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
Example 2:


Input: root = [2,1,3]
Output: [2,3,1]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

## Explanation Like You're 5 Years Old 🎈

Imagine you have a **family tree** made of toy blocks!

### What is "Inverting"?
Inverting means **flipping the tree like a mirror**. Everything on the left goes to the right, and everything on the right goes to the left!

### The Magic Trick:
Think of it like this - you're standing in front of a mirror:
- Your left hand appears on the right side in the mirror
- Your right hand appears on the left side in the mirror

We do the same thing with our tree!

---

## Step-by-Step Walkthrough 🚶

Let's use **Test Case 1** and see exactly what happens:

### Initial Tree:
```
       4
      / \
     2   7
    / \ / \
   1  3 6  9
```

### Step-by-Step Process:

**STEP 1:** Start at the root (node with value 4)
- Current node: **4**
- Left child: **2**, Right child: **7**
- Action: **SWAP!** Now left becomes 7, right becomes 2

After swap at node 4:
```
       4
      / \
     7   2
    / \ / \
   6  9 1  3
```
Wait! The children (6,9,1,3) haven't moved yet - we'll fix them next!

**STEP 2:** Move to the LEFT child (which is now 7)
- Current node: **7**
- Left child: **6**, Right child: **9**
- Action: **SWAP!** Now left becomes 9, right becomes 6

After swap at node 7:
```
       4
      / \
     7   2
    / \ / \
   9  6 1  3
```

**STEP 3:** Move to node 9 (left child of 7)
- Current node: **9**
- Left child: **null**, Right child: **null**
- Action: Nothing to swap! This is a leaf node.

**STEP 4:** Move to node 6 (right child of 7)
- Current node: **6**
- Left child: **null**, Right child: **null**
- Action: Nothing to swap! This is a leaf node.

**STEP 5:** We're done with node 7's subtree! Go back to node 4 and process the RIGHT child (which is now 2)
- Current node: **2**
- Left child: **1**, Right child: **3**
- Action: **SWAP!** Now left becomes 3, right becomes 1

After swap at node 2:
```
       4
      / \
     7   2
    / \ / \
   9  6 3  1
```

**STEP 6:** Move to node 3 (left child of 2)
- Current node: **3**
- Left child: **null**, Right child: **null**
- Action: Nothing to swap! This is a leaf node.

**STEP 7:** Move to node 1 (right child of 2)
- Current node: **1**
- Left child: **null**, Right child: **null**
- Action: Nothing to swap! This is a leaf node.

### Final Inverted Tree:
```
       4
      / \
     7   2
    / \ / \
   9  6 3  1

How the Code Works 🔧
javapublic TreeNode invertTree(TreeNode root) {
    // 1. If there's no tree or we reached the end, stop!
    if (root == null) {
        return null;
    }
    
    // 2. Swap left and right children (like switching hands in mirror)
    TreeNode temp = root.left;    // Hold left child in temporary box
    root.left = root.right;        // Put right child on left side
    root.right = temp;             // Put what was in left (from temp box) on right
    
    // 3. Now do the same magic trick for left subtree
    invertTree(root.left);
    
    // 4. And do the same magic trick for right subtree
    invertTree(root.right);
    
    // 5. Return the flipped tree!
    return root;
}

Time and Space Complexity ⏱️
Time Complexity: O(n)

n = number of nodes in the tree
Why? We visit every single node exactly once to swap its children
Even if tree has 100 nodes, we visit all 100 nodes once

Space Complexity: O(h)

h = height of the tree
Why? We use the call stack for recursion
In worst case (skewed tree): h = n, so O(n)
In best case (balanced tree): h = log(n), so O(log n)
Average case for balanced tree: O(log n)

Example:

A tree with 7 nodes and height 3 uses at most 3 recursive calls on the stack at once
A tree with 7 nodes in a straight line (height 7) uses 7 recursive calls on the stack
Claude is AI and can make mistakes. Please double-check responses.
 */
public class InvertBinaryTree {

	public TreeNode invertTree(TreeNode root) {
		
		if(root == null) {
			
			return null;
			
		}
		
		TreeNode temp = root.left;
		
		root.left = root.right;
		
		root.right = temp;
		
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
	}
	 public static void main(String[] args) {
		 InvertBinaryTree solution = new InvertBinaryTree();
	        
	        // Test Case 1: [4,2,7,1,3,6,9]
	        System.out.println("Test Case 1:");
	        TreeNode root1 = new TreeNode(4);
	        root1.left = new TreeNode(2);
	        root1.right = new TreeNode(7);
	        root1.left.left = new TreeNode(1);
	        root1.left.right = new TreeNode(3);
	        root1.right.left = new TreeNode(6);
	        root1.right.right = new TreeNode(9);
	        
	        System.out.print("Before inversion: ");
	        printTree(root1);
	        System.out.println();
	        
	        TreeNode inverted1 = solution.invertTree(root1);
	        System.out.print("After inversion: ");
	        printTree(inverted1);
	        System.out.println("\n");
	        
	        // Test Case 2: [2,1,3]
	        System.out.println("Test Case 2:");
	        TreeNode root2 = new TreeNode(2);
	        root2.left = new TreeNode(1);
	        root2.right = new TreeNode(3);
	        
	        System.out.print("Before inversion: ");
	        printTree(root2);
	        System.out.println();
	        
	        TreeNode inverted2 = solution.invertTree(root2);
	        System.out.print("After inversion: ");
	        printTree(inverted2);
	        System.out.println("\n");
	        
	        // Test Case 3: Empty tree
	        System.out.println("Test Case 3:");
	        TreeNode root3 = null;
	        
	        System.out.print("Before inversion: ");
	        printTree(root3);
	        System.out.println();
	        
	        TreeNode inverted3 = solution.invertTree(root3);
	        System.out.print("After inversion: ");
	        printTree(inverted3);
	        System.out.println("\n");
	        
	        // Test Case 4: Single node [1]
	        System.out.println("Test Case 4:");
	        TreeNode root4 = new TreeNode(1);
	        
	        System.out.print("Before inversion: ");
	        printTree(root4);
	        System.out.println();
	        
	        TreeNode inverted4 = solution.invertTree(root4);
	        System.out.print("After inversion: ");
	        printTree(inverted4);
	        System.out.println();
	    }
	    
	    // Helper method to print tree in level-order
	    public static void printTree(TreeNode root) {
	        if (root == null) {
	            System.out.print("[]");
	            return;
	        }
	        
	        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
	        queue.offer(root);
	        System.out.print("[");
	        
	        boolean first = true;
	        while (!queue.isEmpty()) {
	            TreeNode node = queue.poll();
	            
	            if (!first) {
	                System.out.print(",");
	            }
	            first = false;
	            
	            if (node != null) {
	                System.out.print(node.val);
	                queue.offer(node.left);
	                queue.offer(node.right);
	            } else {
	                System.out.print("null");
	            }
	        }
	        System.out.print("]");
	    }
}
