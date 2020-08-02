package com.company;

// Java program to check whether input degree
// sequence is graph or tree

/**
 * The degree of a vertex is given by the number of edges incident or leaving from it.
 * This can simply be done using the properties of trees like –
 *
 * Tree is connected and has no cycles while graphs can have cycles.
 * Tree has exactly n-1 edges while there is no such constraint for graph.
 * It is given that the input graph is connected. We need at least n-1 edges to connect n nodes.
 * O(n)
 */
class IdentifyingTreeFromDeg
{

	// Function returns true for tree
	// false for graph
	static boolean check(int degree[], int n)
	{
		// Find sum of all degrees
		int deg_sum = 0;
		for (int i = 0; i < n; i++)
		{
			deg_sum += degree[i];
		}

		// Graph is tree if sum is equal to 2(n-1)
		return (2 * (n - 1) == deg_sum);
	}

	// Driver code
	public static void main(String[] args)
	{
		int n = 5;
		int degree[] = {2, 3, 1, 1, 1};

		if (check(degree, n))
		{
			System.out.println("Tree");
		}
		else
		{
			System.out.println("Graph");
		}
	}
}
