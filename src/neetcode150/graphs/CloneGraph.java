package neetcode150.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Given a node in a connected undirected graph, return a deep copy of the graph.

Each node in the graph contains an integer value and a list of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
The graph is shown in the test cases as an adjacency list. An adjacency list is a mapping of nodes to lists, used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph. The index of each node within the adjacency list is the same as the node's value (1-indexed).

The input node will always be the first node in the graph and have 1 as the value.

Example 1:



Input: adjList = [[2],[1,3],[2]]

Output: [[2],[1,3],[2]]
Explanation: There are 3 nodes in the graph.
Node 1: val = 1 and neighbors = [2].
Node 2: val = 2 and neighbors = [1, 3].
Node 3: val = 3 and neighbors = [2].

Example 2:



Input: adjList = [[]]

Output: [[]]
Explanation: The graph has one node with no neighbors.

Example 3:

Input: adjList = []

Output: []
Explanation: The graph is empty.

Constraints:

0 <= The number of nodes in the graph <= 100.
1 <= Node.val <= 100
There are no duplicate edges and no self-loops in the graph.

ComplexityReasonTimeO(V + E)We visit every node (V) once and process every edge (E) 
once when iterating neighboursSpaceO(V)The HashMap stores one entry per node; 
the BFS queue holds at most O(V) nodes at once
Idea Behind the Solution (Normal Explanation)

A graph is made of nodes, and each node can have multiple neighbors. To clone a graph, we must create a completely new copy of every node and reconnect them in the same way as the original graph.

The tricky part is:

The graph can have cycles (like 1 → 2 → 1).

If we don’t track visited nodes, we may end up in infinite loops.

So we use:

A HashMap to remember already cloned nodes.

DFS (Depth First Search) to traverse the graph and clone each node recursively.

The HashMap helps us:

Avoid cloning the same node multiple times.

Prevent infinite recursion.

🧒 Explain Like You’re 5 Years Old

Imagine you have a group of friends holding hands in a circle.

You want to make a new group that looks exactly the same.

But here’s the rule:
You can’t reuse any old friend. You must make new copies.

So what do you do?

You see Friend 1.

You create a new Friend 1.

Then you look at who Friend 1 is holding hands with.

You make copies of them too.

But if you already made a copy of someone, you don’t make them again — you just reuse the copy.

So:

We keep a notebook (HashMap).

Whenever we copy someone, we write it down.

If we see them again, we check the notebook instead of copying again.

That way we don’t get stuck in a never-ending circle.
basically we clone the node, add to hashmap which tracks the cloned node.
then we go to neightboras, call clone() recursivey and the the clone neightbor to map.

 */
public class CloneGraph {
	
	Map<Node, Node> visited =  new HashMap<>();
	
	
	
	public Node cloneGraph(Node node) {
		if(node == null)
			return null;
		Node clone = new Node(node.val);
		if(visited.containsKey(node)) {
			return visited.get(node);
		}
		visited.put(node, clone);
		for(Node neighbor : node.neighbors) {
			Node clonedNeighbor = cloneGraph(neighbor);
			clone.neighbors.add(clonedNeighbor);
		}
		return clone;
		
	}
	
	  // Helper method to print graph using BFS
    public static void printGraph(Node node) {
        if (node == null) {
            System.out.println("Graph is empty.");
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print("Node " + current.val + " -> ");

            for (Node neighbor : current.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // -------- Test Case 1: Simple 4 node cycle --------
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph solution = new CloneGraph();

        System.out.println("Original Graph:");
        printGraph(node1);

        Node clonedGraph = solution.cloneGraph(node1);

        System.out.println("\nCloned Graph:");
        printGraph(clonedGraph);

        // -------- Test Case 2: Single Node --------
        Node singleNode = new Node(10);

        CloneGraph solution2 = new CloneGraph();
        Node clonedSingle = solution2.cloneGraph(singleNode);

        System.out.println("\nSingle Node Graph:");
        printGraph(clonedSingle);

        // -------- Test Case 3: Null Input --------
        CloneGraph solution3 = new CloneGraph();
        Node nullGraph = solution3.cloneGraph(null);

        System.out.println("\nNull Graph:");
        printGraph(nullGraph);
    }

}
