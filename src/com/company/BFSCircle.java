package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Java program to detect cycle in
//  an undirected graph using BFS.

/**
 * We do a BFS traversal of the given graph. For every visited vertex ‘v’,
 * if there is an adjacent ‘u’ such that u is already visited and u is not parent of v,
 * then there is a cycle in graph. If we don’t find such an adjacent for any vertex, we say that there is no cycle.
 * The assumption of this approach is that there are no parallel edges between any two vertices.
 */
class BFSCircle
{

	public static void main(String arg[])
	{

		int V = 4;
		ArrayList <Integer> adj[] = new ArrayList[V];
		for(int i = 0; i < 4; i++)
			adj[i] = new ArrayList<Integer>();

		addEdge(adj, 0, 1);
		addEdge(adj, 1, 2);
		addEdge(adj, 2, 0);
		addEdge(adj, 2, 3);

		if (isCyclicDisconnected(adj, V))
			System.out.println("Yes");
		else
			System.out.println("No");


	}

	static void addEdge(ArrayList<Integer> adj[], int u, int v)
	{
		adj[u].add(v);
		adj[v].add(u);
	}

	static boolean isCyclicConnected(ArrayList<Integer> adj[], int s,
	                                 int V, boolean visited[])
	{

		// Set parent vertex for every vertex as -1.
		int parent[] = new int[V];
		Arrays.fill(parent, -1);

		// Create a queue for BFS
		Queue<Integer> q = new LinkedList<>();

		// Mark the current node as
		// visited and enqueue it
		visited[s] = true;
		q.add(s);

		while (!q.isEmpty())
		{

			// Dequeue a vertex from
			// queue and print it
			int u = q.poll();


			// Get all adjacent vertices
			// of the dequeued vertex u.
			// If a adjacent has not been
			// visited, then mark it visited
			// and enqueue it. We also mark parent
			// so that parent is not considered
			// for cycle.
			for (int i=0; i<adj[u].size();i++)
			{
				int v = adj[u].get(i);
				if (!visited[v]) // if v adjacent of u is not yet visited..
				{
					visited[v] = true; // mark visited
					q.add(v); // add him to the q
					parent[v] = u; // mark the parent of v as u
				}
				else if (parent[u] != v) // if u is not the parent of v, and v is already visited,
					return true;
			}
		}
		return false;
	}


	static boolean isCyclicDisconnected(ArrayList<Integer> adj[], int V)
	{

		// Mark all the vertices as not visited
		boolean visited[] = new boolean[V];
		Arrays.fill(visited,false);

		for (int i = 0; i < V; i++)
			if (!visited[i] &&
					isCyclicConnected(adj, i, V, visited))
				return true;
		return false;
	}
}