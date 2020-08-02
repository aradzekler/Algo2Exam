package com.company;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Huffman algorithm - using two queue - assumption: the given freq array is sorted
 * Complexity: O(n)
 */



// a Node class for our binary tree.
class Node {
	char c; // the character
	int freq; // frequency of appereances.
	Node left;
	Node right;

	public Node(char c, int freq) {
		this.c = c;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}
}


public class Huffman {

	public static ArrayBlockingQueue<Node> q1,q2; // our 2 ques.


	// the actual encoding, recieves a node.
	public static Node Huffman(Node[] c) {
		int n = c.length; // word length
		Node temp = null;
		q1 = new ArrayBlockingQueue<Node>(n); // initializing of our ques.
		q2 = new ArrayBlockingQueue<Node>(n);

		for (int i = 0; i < c.length; i++) { // for each character in the word
			q1.add(c[i]); // add character to que.
		}
		boolean flag = true;
		while(flag) {
			Node min_1 = getMin(); // pulling out the 2 most minimal characters (with the lesser frequencies)
			Node min_2 = getMin();
			temp = new Node((char)(n+'a'),min_1.freq+min_2.freq); // sum the frequencies
			temp.left = min_1; // continueing to go down the tree
			temp.right = min_2;
			q2.add(temp); // inserting temp to the second que.
			if ((min_2.freq + min_1.freq) == 100) { // if we reached 100% prob, we reached root.
				flag = false;
			}
		}
		return temp;

	}

	// we need 2 ques for this function - to get the minimum.
	public static Node getMin() {
		if(q1.isEmpty() && q2.isEmpty()) return null;
		if(q1.isEmpty()) return q2.poll(); // poll - see and remove the first element
		if(q2.isEmpty()) return q1.poll();
		if(q1.peek().freq > q2.peek().freq) return q2.poll(); // peek - see the first element
		return q1.poll();
	}

	public static void printTree(Node c) {
		if(c == null) {
			return;
		}
		printTree(c.left);
		System.out.print(c.c + " - > ");
		printTree(c.right);
	}

	// recursive code for building our binary tree (that means that we are building it from the bottom up)
	public static void buildCode(String code, Node root,Node c) {
		if(root == null) { // if no root, no tree to build.
			return;
		}
		if(root.c == c.c)
			System.out.println("for "+c.c+" code = "+code);
		buildCode(code + "0",root.left, c);
		buildCode(code + "1",root.right, c);


	}

	public static void main(String[] args) {

		Node a = new Node('a',4);
		Node b = new Node('b',7);
		Node c = new Node('c',10);
		Node d = new Node('d',18);
		Node e = new Node('e',25);
		Node f = new Node('f',36);
		Node[] narr = {a,b,c,d,e,f};
		Node t = Huffman(narr);
		printTree(t);
		System.out.println();
		for (int i = 0; i < narr.length; i++) {
			buildCode("",t,narr[i]);

		}

	}


}
