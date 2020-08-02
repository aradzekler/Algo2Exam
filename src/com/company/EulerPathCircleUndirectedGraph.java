package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class EulerPathCircleUndirectedGraph {

	private int numV;   // No. of vertices

	// Array  of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];

	// Constructor
	EulerPathCircleUndirectedGraph(int numV)
	{
		this.numV = numV;
		adj = new LinkedList[numV];
		for (int i = 0; i< numV; ++i)
			adj[i] = new LinkedList(); // initialization, for each vertice make a linkedlist.
	}

	//Function to add an edge into the graph
	void addEdge(int v, int w)
	{
		adj[v].add(w);// Add w to v's list.
		adj[w].add(v); //The graph is undirected so add a 2 way
	}

	// A function used by DFS, v = vertice
	void DFSUtil(int v,boolean visited[])
	{
		// Mark the current node as visited
		visited[v] = true;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext())
		{
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited); // like DFS, pass recursively.
		}
	}

	// Method to check if all non-zero degree vertices are
	// connected. It mainly does DFS traversal starting from
	// O(|V|)
	boolean isConnected()
	{
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[numV];
		int i;
		for (i = 0; i < numV; i++)
			visited[i] = false;

		// Find a vertex with non-zero degree
		for (i = 0; i < numV; i++)
			if (adj[i].size() != 0)
				break;

		// If there are no edges in the graph, then the graph is connected. return true
		if (i == numV)
			return true;

		// Start DFS traversal from a vertex with non-zero degree
		DFSUtil(i, visited);

		// Check if all non-zero degree vertices are visited
		for (i = 0; i < numV; i++)
			if (visited[i] == false && adj[i].size() > 0)
				return false;

		return true;
	}

	/* The function returns one of the following values
	   0 --> If grpah is not Eulerian
	   1 --> If graph has an Euler path (Semi-Eulerian)
	   2 --> If graph has an Euler Circuit (Eulerian)  */
	int isEulerian()
	{
		// Check if all non-zero degree vertices are not connected, if not, no eulerian
		if (isConnected() == false)
			return 0;

		// Count vertices with odd degree, if there is an odd degree it means there is an edge
		int odd = 0;
		for (int i = 0; i < numV; i++)
			if (adj[i].size()%2!=0)
				odd++;

		// If count is more than 2, then graph is not Eulerian
		if (odd > 2)
			return 0;

		// If odd count is 2, then semi-eulerian.
		// If odd count is 0, then eulerian
		// Note that odd count can never be 1 for undirected graph
		return (odd==2)? 1 : 2;
	}

	// Function to run test cases
	void test()
	{
		int res = isEulerian();
		if (res == 0)
			System.out.println("graph is not Eulerian");
		else if (res == 1)
			System.out.println("graph has a Euler path");
		else
			System.out.println("graph has a Euler cycle");
	}

	// Driver method
	public static void main(String args[])
	{
		// Let us create and test graphs shown in above figures
		EulerPathCircleUndirectedGraph g1 = new EulerPathCircleUndirectedGraph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		g1.test();

		EulerPathCircleUndirectedGraph g2 = new EulerPathCircleUndirectedGraph(5);
		g2.addEdge(1, 0);
		g2.addEdge(0, 2);
		g2.addEdge(2, 1);
		g2.addEdge(0, 3);
		g2.addEdge(3, 4);
		g2.addEdge(4, 0);
		g2.test();

		EulerPathCircleUndirectedGraph g3 = new EulerPathCircleUndirectedGraph(5);
		g3.addEdge(1, 0);
		g3.addEdge(0, 2);
		g3.addEdge(2, 1);
		g3.addEdge(0, 3);
		g3.addEdge(3, 4);
		g3.addEdge(1, 3);
		g3.test();

		// Let us create a graph with 3 vertices
		// connected in the form of cycle
		EulerPathCircleUndirectedGraph g4 = new EulerPathCircleUndirectedGraph(3);
		g4.addEdge(0, 1);
		g4.addEdge(1, 2);
		g4.addEdge(2, 0);
		g4.test();

		// Let us create a graph with all veritces
		// with zero degree
		EulerPathCircleUndirectedGraph g5 = new EulerPathCircleUndirectedGraph(3);
		g5.test();
	}
}
