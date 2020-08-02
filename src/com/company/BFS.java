package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {
	public static final int white = 0; // not been visited yet.
	public static final int gray = 1; // visited but not done
	public static final int black = 2; // visited and done
	public static final int NIL = -1;

	int color[]; // colors of all vertices
	int dist[]; // distances from source of all vertices
	int pred[]; // fathers of all vertices.
	LinkedList<Integer>[] list;
	int size;
	int start;

	// constructor
	public BFS(int numOfV, LinkedList <Integer>list[]) {
		size = numOfV;
		color = new int [size];
		this.start = 0;
		this.list = list;
		dist = new int[size];
		pred = new int[size];
	}

	// BFS algorithm, O(m+n) - m= vertices, n = e, st = start vertex
	public void B_F_S(int st) {
		for (int i=0; i<size; i++) {  // initialization
			dist[i] = NIL;
			pred[i] = NIL;
		}
		start = st;
		int v, u; // variables for neighbors
		dist[start] = 0; // distance of starting vertice from itself = 0
		color[start] = gray; // we start with him so paint him grey
		Queue<Integer> Que = new ArrayBlockingQueue<Integer>(100);
		Que.add(start); // defining a que for the vertices and putting start(S) into it
		while (!Que.isEmpty()) {
			v = Que.poll(); // taking the vertice in first place in the que and moving on its neighbors
			while (!list[v].isEmpty()) { // if there are neighbors left
				u = list[v].removeFirst(); // define u as the first neighbor
				if (color[u]==white) { // if u is still white, paint him gray
					color[u] = gray;
					dist[u] = dist[v] + 1; // increase distance by 1
					pred[u] = v; // mark v as u father
					Que.add(u); // add u to the q
				}
			}
			color[v] = black; // after we finished all v neighbors, we mark v black
			System.out.println("the Que now is: "+Que);
			printArrays(dist,pred,color);
		}
	}

	//for easy handling of the input for BFS
	public static LinkedList<Integer>[] Input() {
		int size = 8;
		LinkedList <Integer>list[] = new LinkedList[size];
		for (int i=0; i<size; i++) {
			list[i] = new LinkedList<Integer>();   }
		list[0].add(1);
		list[0].add(4);
		list[1].add(0);
		list[1].add(5);
		list[2].add(3);
		list[2].add(5);
		list[2].add(6);
		list[3].add(2);
		list[3].add(6);
		list[3].add(7);
		list[4].add(0);
		list[5].add(1);
		list[5].add(2);
		list[5].add(6);
		list[6].add(5);
		list[6].add(2);
		list[6].add(3);
		list[6].add(7);
		list[7].add(3);
		list[7].add(6);
		return list;
	}

	// checks how many tied elements are in the graph
	public void compoenents() {
		boolean flag = false;
		String comp = new String();
		int t[] = new int[size];
		for (int i=0; i<size; i++) {
			t[i] = NIL; // filling helper array with -1's
		}
		int count = 1;
		int index;
		while (!flag) {
			PrintArrayLL();
			comp = new String();
			for (int i=0; i<size; i++) {
				if (dist[i]!=NIL) { // if there are no NIL's, there is a track and the components are linked
					comp = comp + i + "->";
					t[i] = dist[i];
				}
			}
			System.out.println("\nThe count is: " + count);
			System.out.println(comp);
			flag = true;
			int nextIndex = 0;
			for (index = 0; flag && index<size; index++) {
				if (t[index]==NIL) {
					flag = false;
					count++;
					nextIndex = index;
				}
			}
			B_F_S(nextIndex);
		}
	}

	// checks if there is a track from S to end
	public void PrintTrack(int end) {
		String ans = "" + end;
		int temp = pred[end];
		while (temp != NIL) {
			ans = temp +"->"+ ans;
			temp = pred[temp];    }
		if (dist[end] == NIL) // if end is of the same component as the track
			System.out.println("there is no Track");
		else System.out.println("the track from "+start+" to "+end+" is: " + ans); }


	// helper function for easy printing
	public static void printArrays(int dist[], int pred[], int color[]) {
		for (int i=0; i<dist.length; i++) {
			String temp = "";
			if (color[i]==0) temp = "White";
			if (color[i]==1) temp = "Gray";
			if (color[i]==2) temp = "Black";
			System.out.println("Vertex "+i+"\tDist="+dist[i]+"\t\tPred="+pred[i]+"\t\tThe color: "+temp);
		}   System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

	// helper function for easy printing array data.
	public void PrintArrayLL() {
		System.out.println("************ the Array LinkedList of Graph is: ************");
		for (int i=0; i<size; i++) {
			System.out.println("vertex "+i+" -> "+list[i].toString());
		}
		System.out.println("******************************************************\n");
	}



	// main func
	public static void main(String[] args) {
		BFS algo = new BFS(8, Input());
		algo.B_F_S(0);
		algo.PrintTrack(6);
		algo.compoenents(); // components resets the track, so it should be put after PrintTrack
	}

}
