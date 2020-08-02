package com.company;
import java.util.*;

/** BFS for traversing a tree and finding the diameter of it
 * Step 1: Run bfs to find the farthest node from rooted tree let say A
 * Step 2: Then run bfs from A to find farthest node from A let B
 * Step 3: Distance between node A and B is the diameter of given tree
 * O(|V|+|E|)
 */
class BFSDiameter
{

	// Here 10000 is maximum number of nodes in
// given tree.
	static int diameter[] = new int[10001];

	// The Function to do bfs traversal.
// It uses iterative approach to do bfs
// bfsUtil()
	static int bfs(int init, Vector<Vector<Integer>>arr, int n)
	{
		// Initializing queue
		Queue<Integer> q = new LinkedList<>();
		q.add(init);

		int visited[] = new int[n + 1];
		for (int i = 0; i <= n; i++) // initalization: nobody is visited and diameter of all is 0
		{
			visited[i] = 0;
			diameter[i] = 0;
		}

		// Pushing each node in queue
		q.add(init);

		// Mark the traversed node visited
		visited[init] = 1;
		while (q.size() > 0)
		{
			int u = q.peek();
			q.remove();
			for (int i = 0;
			     i < arr.get(u).size(); i++)
			{
				if (visited[arr.get(u).get(i)] == 0) // if u neighbor i has not been visited yet
				{
					visited[arr.get(u).get(i)] = 1; // mark visited

					// Considering weight of edges equal to 1
					diameter[arr.get(u).get(i)] += diameter[u] + 1; // add to diameter
					q.add(arr.get(u).get(i)); // add the neighbor to q
				}
			}
		}
		int in = 0;
		for(int i = 0; i <= n; i++)
		{
			if(diameter[i] > diameter[in]) // return largest value in diameter (which will be.. the diameter)
				in = i;
		}

		// return index of max value in diameter
		return in;
	}

	static int findDiameter(Vector<Vector<Integer>> arr, int n)
	{
		int init = bfs(1, arr, n); // returning the index of max value in diameter of the shortest path between start to finish. x-->y
		int val = bfs(init, arr, n); // returning the index of max value in diameter of shortest path between the diameter and the end. y-->z
		return diameter[val]; // return its diameter -aka the distance between y-->z
	}

	// Driver Code
	public static void main(String args[])
	{
		// Input number of nodes
		int n = 6;

		Vector<Vector<Integer>> arr = new
				Vector<Vector<Integer>>();

		for(int i = 0; i < n + 1; i++)
		{
			arr.add(new Vector<Integer>());
		}

		// Input nodes in adjacency list
		arr.get(1).add(2);
		arr.get(1).add(3);
		arr.get(1).add(6);
		arr.get(2).add(4);
		arr.get(2).add(1);
		arr.get(2).add(5);
		arr.get(3).add(1);
		arr.get(4).add(2);
		arr.get(5).add(2);
		arr.get(6).add(1);

		System.out.printf("Diameter of n-ary tree is %d\n",
				findDiameter(arr, n));
	}
}

