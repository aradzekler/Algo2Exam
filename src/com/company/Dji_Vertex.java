package com.company;

public class Dji_Vertex implements Comparable<Dji_Vertex> {
	int name;  // vertex
	int prev; // father vertex
	Dji_Edge edge[]; // edges of vertex
	double dist; // distance from S (start) vertex
	boolean visit; //

	public static final Double INF = Double.POSITIVE_INFINITY;
	public static final int NIL = -1;

	// constructor
	public Dji_Vertex(int name) {
		this.name = name;
		dist = INF;
		visit = false;
		prev = NIL;
	}


	@Override
	public int compareTo(Dji_Vertex dji_vertex) {
		return 0;
	}
}
