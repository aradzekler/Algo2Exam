package com.company;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class TreeBurning {
	ArrayList<Integer> tree[];
	int n; // number of vertices in the graph
	int degree[]; // degrees of each vertice
	int radius;
	int diameter;

	// constructor.
	public TreeBurning(ArrayList<Integer>[] AL) {
		this.tree = AL;
		this.n = AL.length;
		this.degree = new int[n];
		this.radius = 0;
		this.diameter = 0;
	}

	// tree burning algo for finding the diameter
	public int findDiameter() {
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			degree[i] = tree[i].size();
			if (degree[i] == 1)
				leaves.add(i); // filling our arraylist with leaves.
		}
		int leaf = 0;
		int vertex = 0;

		while (n > 2) { // if there are more then 2 vertices.
			ArrayList<Integer> futureLeaves = new ArrayList<Integer>();
			for (int i = 0; i < leaves.size(); i++) {
				leaf = leaves.get(i);
				degree[leaf] = 0;
				for (int j = 0; j < tree[leaf].size(); j++) {
					vertex = tree[leaf].get(j);
					if (degree[vertex] > 0) {
						degree[vertex]--;
						if (degree[vertex] == 1)
							futureLeaves.add(vertex); // adding leaves to our futureleaves
					}
				}
				n--; // every iteration we burn down 1 leaf.
			}
			radius++;  // for every burning we make the radius larger.
			leaves = futureLeaves; // advancing farward in the tree (future leaves are now the current leaves)
		}
		if (leaves.size() == 2) { // by theorem
			radius++;
			diameter = 2 * radius - 1;
		} else diameter = 2 * radius;
		System.out.println("the radius is: " + radius + ", the diameter is: " + diameter + ", and center is: " + leaves);
		return diameter;
	}

	// find if something is ON the diameter, using BFS.
	public static boolean isOnDiameter(ArrayList<Integer>[] tree, int x) {
		int count = 0, radius = 0;
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		int n = tree.length;
		int[] deg = new int[n];
		for (int i = 0; i < n; i++) {
			deg[i] = tree[i].size();
			if(deg[i] == 1) leaves.add(i); // initializing degrees, if the degree of a node is 1 = leaf.
		}
		boolean xFound = false; // found a spot on diameter flag
		while(n > 2) {
			ArrayList<Integer> newLeaves = new ArrayList<Integer>(); // arratlist of new leaves to burn
			radius++;
			for (int v : leaves) { // for every leaf
				if(v == x) xFound = true; // if we found what we are looking for, flag= true
				n--;
				deg[v] = 0;
				for(int u : tree[v]) { // leaf burning
					deg[u]--;
					if(deg[u] == 1) newLeaves.add(u);
				}
			}
			leaves = newLeaves;
			if(!xFound) count++; // if we didnt found, counting up
		}
		int dist = Integer.MAX_VALUE; // activating BFS to find diameter
		for(int c : leaves) {
			int d = bfs(tree, c, x);
			if(d < dist) dist = d;
		}
		return dist + count == radius;
	}

	// BFS for finding a spot of diameter
	private static int bfs(ArrayList<Integer>[] tree, int s, int t) {
		int n = tree.length;
		double[] dist = new double[n];
		boolean[] visit = new boolean[n];
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			visit[i] = false;
		}
		q.add(s);
		dist[s] = 0;
		visit[s] = true;
		while(!q.isEmpty()) {
			int u = q.poll();
			if(u == t) return (int)dist[u];
			for(int v : tree[u]) {
				if(!visit[v]) {
					dist[v] = dist[u] + 1;
					visit[v] = true;
					q.add(v);
				}
			}

		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		ArrayList<Integer>[] g = new ArrayList[11];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<Integer>();
		}
		g[0].add(1);
		g[1].add(0);g[1].add(2);g[1].add(5);
		g[2].add(1);g[2].add(3);
		g[3].add(2);
		g[4].add(5);
		g[5].add(1);g[5].add(4);g[5].add(6);g[5].add(8);
		g[6].add(5);g[6].add(7);
		g[7].add(6);
		g[8].add(5);g[8].add(9);
		g[9].add(8);g[9].add(10);
		g[10].add(9);

		
		TreeBurning example = new TreeBurning(g);
		example.findDiameter();
		System.out.println(example.isOnDiameter(g, 5));


	}

}


