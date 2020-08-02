package com.company;

import java.util.PriorityQueue;

import static com.company.Dji_Vertex.INF;

public class Djikstra {


	// initializing our graph
	public static Dji_Vertex[] initGraph() {
		Dji_Vertex v0 = new Dji_Vertex(0);
		Dji_Vertex v1 = new Dji_Vertex(1);
		Dji_Vertex v2 = new Dji_Vertex(2);
		Dji_Vertex v3 = new Dji_Vertex(3);
		Dji_Vertex v4 = new Dji_Vertex(4);

		v0.edge = new Dji_Edge[]{new Dji_Edge(1, 10), new Dji_Edge(4, 5)};
		v1.edge = new Dji_Edge[]{new Dji_Edge(2, 1), new Dji_Edge(4, 2)};
		v2.edge = new Dji_Edge[]{new Dji_Edge(3, 6)};
		v3.edge = new Dji_Edge[]{new Dji_Edge(2, 4), new Dji_Edge(0, 7)};
		v4.edge = new Dji_Edge[]{new Dji_Edge(3, 2), new Dji_Edge(2, 9), new Dji_Edge(1, 3)};

		Dji_Vertex vs[] = {v0, v1, v2, v3, v4};
		return vs;
	}

	// Djikstra algorithm - O((|V| + |E|)*Log}V|), ver is vertex array and num = number of vertexes.
	public static void Dijkstra(Dji_Vertex ver[], int num) {
		PriorityQueue<Dji_Vertex> q = new PriorityQueue<Dji_Vertex>(); // O(log|V|) // for priorityq on vertices.
		Dji_Vertex start = ver[num];
		q.add(start); // starting vertex enters the q
		start.dist = 0; // distance from himself is 0
		while (!q.isEmpty()) { // while our q is not empty.
			Dji_Vertex u = q.poll(); // taking out one neighbor out of the q and handles all its neighbors.
			System.out.println("and the poll is: " + u.name);
			PrintDij(ver);
			for (int i = 0; i < u.edge.length; i++) { // for each neighbor
				Dji_Edge e = u.edge[i];
				Dji_Vertex v = ver[e.vertex];
				if (!v.visit) { // if this neighbor had not been visited.
					if (v.dist > u.dist + e.weight) { // if distance to 0 is greater then the neighbor + weight, update the minimum.
						v.dist = u.dist + e.weight;
						v.prev = ver[u.name].name;
						q.remove(v);
						q.add(v);
					}
				}
			}
			u.visit = true;
		}
	}
	// printing helper function
	public static void PrintDij(Dji_Vertex v[]) {
		for (int i=0; i<v.length; i++) {
			System.out.println("ver "+i+", dist: "+v[i].dist+", prev: "+v[i].prev+", visit: "+v[i].visit);   }
		System.out.println("*********************************\n");  }


		// v = vertex array after dji, start = start vertex and end is the vertex we wonna go to.
	// finding the cheapest track between given start and end.
	public static String getTrack(Dji_Vertex v[], int start, int end) {
		String ans = "";
		if (v[end].dist == INF) return "there is no track!";
		while (v[end].dist != 0) {
			ans = end + " --> " + ans;
			end = v[end].prev;  }
		ans = start + " --> " + ans;
		return ans; }


	// main func
	// WE CAN FIND THE LENGTH OF THE TRACK TO SPECIFIC VERTEX, IT IS GIVEN IN double sum = v[end].dist;
	public static void main(String[] args) {
		Djikstra dji = new Djikstra();
		dji.Dijkstra(initGraph(), 4);
		Dji_Vertex[] v = initGraph();
		dji.Dijkstra(v, 4);
		System.out.println(dji.getTrack(v, 0, 2)); // doesnt get track, cant link to actual dijk
	}

	}
