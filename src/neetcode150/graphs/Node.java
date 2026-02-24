package neetcode150.graphs;

import java.util.ArrayList;
import java.util.List;



public class Node{
	int val;
	List<Node> neighbors;
	public Node() {
		this.val = 0;
		neighbors = new ArrayList<>();
	}
	public Node(int val) {
		this.val = val;
		this.neighbors = new ArrayList<>();
	}
	public Node(int val, List<Node> neighbors) {
		this.val = val;
		this.neighbors = new ArrayList<>();
	}
	
}
