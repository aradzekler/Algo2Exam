package com.company;

import java.util.Arrays;

public class BottleProblemAndFW {
	static int INF = 9999;

	// FW with negative numbers
	public static void FWNeg(int [][] mat) {
		int n = mat.length;
		// matrix building
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (mat[i][k] != INF && mat[k][j] != INF) {
						mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
						System.out.println("k="+k+", i="+i+", j="+j+", mat["+i+","+j+"]="+mat[i][j]);
					}
				}
			}
		}
	}



		// bottle problem with bottle n and bottle m
	public static int[][] initRibs(int n, int m) {
		System.out.println("The maximum value of the first bottle(n) = "+n);
		System.out.println("The maximum value of the second bottle(m) = "+m);
		System.out.println("********************["+n+","+m+"]********************");
		System.out.println("*********************************************\n");

		int dim = (n+1)*(m+1); // n+1 and m+1 because of line and amuda of zeroes
		int mat[][] = new int[dim][dim];
		for (int i=0; i<dim; i++) {
			for (int j=0; j<dim; j++) {
				mat[i][j] = INF;    } // for each cell, mark INF (we dont know if there is a connection.
		}

		int ind1, ind2;
		for (int i=0; i<=n; i++) {
			for (int j=0; j<=m; j++) { // O(m*n)
				ind1 = index(i, j, m);
				mat[ind1][index(0, j, m)] = 1; // Rib 1
				mat[ind1][index(n, j, m)] = 1; // Rib 2
				mat[ind1][index(i, 0, m)] = 1; // Rib 3
				mat[ind1][index(i, m, m)] = 1; // Rib 4
				ind2 = index(Math.min(i + j, n), i + j - Math.min(i + j, n), m);
				mat[ind1][ind2] = 1; // Rib 5
				ind2 = index(i + j - Math.min(i + j, m), Math.min(i + j, m), m);
				mat[ind1][ind2] = 1; // Rib 6
			}}
				for (int t=0; t<dim; t++) // mark alachson in 0 (cause distance from vertice to itself = 0)
					mat[t][t] = 0;
				return mat;  }


	// helper function for FW for printing
	static void printSolution(int dist[][], int n)
	{
		System.out.println("The following matrix shows the shortest "+
				"distances between every pair of vertices");
		for (int i=0; i<n; ++i)
		{
			for (int j=0; j<n; ++j)
			{
				if (dist[i][j]== INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j]+"   ");
			}
			System.out.println();
		}
	}

	static int[][] floydWarshall(int[][] graph, int n) {
		int[][] dist = new int[n][n];
		int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				dist[i][j] = graph[i][j];

        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
		for (k = 0; k < n; k++)
		{
			// Pick all vertices as source one by one
			for (i = 0; i < n; i++)
			{
				// Pick all vertices as destination for the
				// above picked source
				for (j = 0; j < n; j++)
				{
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		// Print the shortest distance matrix
		return dist;
	}

	// helper function for converting int to bool
	public static boolean[][] convertIntFWToBool(int[][] graph, int n) {
		boolean[][] mat = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = false; // initialization
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = graph[i][j] != INF;
			}
		}
		return mat;
	}

	// the matrix bool initialization
	public static boolean[][] initBoolMatrBottle(int m, int n){
		int dim = (m+1)*(n+1); // matrix dimension
		boolean [][]mat = new boolean[dim][dim];
		for (int i=0; i<dim; i++){
			for (int j=0; j<dim; j++){
				mat[i][j]= false; // initialization: filling our matrix with false values.
			}
		}

		for (int i=0; i<=m; i++){
			for (int j=0; j<=n; j++){
				int ind = index(i,j,n);
				mat[ind][index(0,j,n)]=true;
				mat[ind][index(i,0,n)]=true;
				mat[ind][index(i,n,n)]=true;
				mat[ind][index(m,j,n)]=true;
				int i1=index(Math.max(0,i+j-n),Math.min(n,i+j) ,n);
				mat[ind][i1]=true;
				i1 = index(Math.min(m,i+j),Math.max(0,j+i-m) ,n);
				mat[ind][i1]=true;
			}
		}
		return mat;
	}

	// the matrix int initialization
	public static int[][] initIntMatrBottle(int m, int n){
		int dim = (m+1)*(n+1); // matrix dimension
		int [][]mat = new int[dim][dim];
		for (int i=0; i<dim; i++){
			for (int j=0; j<dim; j++){
				mat[i][j]= 0; // filling our matrix with false values.
			}
		}

		for (int i=0; i<=m; i++){
			for (int j=0; j<=n; j++){
				int ind = index(i,j,n);
				mat[ind][index(0,j,n)]=1;
				mat[ind][index(i,0,n)]=1;
				mat[ind][index(i,n,n)]=1;
				mat[ind][index(m,j,n)]=1;
				int i1=index(Math.max(0,i+j-n),Math.min(n,i+j) ,n);
				mat[ind][i1]=1;
				i1 = index(Math.min(m,i+j),Math.max(0,j+i-m) ,n);
				mat[ind][i1]=1;
			}
		}
		return mat;
	}


	//the index calculation
	private static int index(int i, int j, int n){
		return (n+1)*i + j;
	}


	//print function
	public static void printBoolMatrix(boolean [][] b){
		for (boolean[] booleans : b) {
			for (int j = 0; j < b[0].length; j++) {
				System.out.print(booleans[j] + "\t");
			}
			System.out.println();
		}
	}

	// printing function for int, can be modified to bool
	public static void printMat(int mat[][], int n, int m) {
		int stop = 0;
		String[] arr = new String[2*n*m + 2];
		int go = 0;   while (stop<=n) {
			for (int j=0; j<=m; j++) {
				arr[go] = "["+stop+","+j+"]\t";     go++;
				System.out.print("\t["+stop+","+j+"]");    }
			stop++;
			if (stop>n)
				break;
			for (int j=0; j<=m; j++) {
				System.out.print("\t["+stop+","+j+"]");
				arr[go] = "["+stop+","+j+"]\t";
				go++;    }
			stop++;   }

		System.out.println("\n");
		for (int i=0; i<mat.length; i++) {
			System.out.print(arr[i]);
			for (int j=0; j<mat[0].length; j++) {
				System.out.print("  "+mat[i][j]+"\t");    }
			System.out.println("\n");   }
	}


	// m is the size of the bottle, finding all indirect connections
	public static String[][] ConnectPossibleVertex(boolean[][] mat, int m) {
		int dim = mat.length;
		int a0, b0, a1, b1=0;
		String[][] path = new String[dim][dim];
		for (int i=0; i<dim; i++) {
			for (int j=0; j<dim; j++) {
				a0 = i / (m+1);
				b0 = i % (m+1);
				a1 = j / (m+1);
				b1 = j % (m+1);
				if (mat[i][j])
					path[i][j] = "["+a0+","+b0+"]-D->["+a1+","+b1+"]";
				else
					path[i][j] = "";    }
		}   for (int k=0; k<dim; k++) {
				for (int i=0; i<dim; i++) {
					for (int j=0; j<dim; j++) {
						if (!mat[i][j]) {
							mat[i][j] = mat[i][k] && mat[k][j];
							if (mat[i][j]) {
								path[i][j] = path[i][k] + " >-i-> " + path[k][j];
							}
						}
					}
				}
		}
		return path;  }

		// checks if graph is directed by checking upper triangle and lower triangle
	public static boolean isDirectedGraph(boolean[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<m; j++) {
				if (mat[i][j]!=mat[j][i])
					return false;
			}
		}
		return true;
	}

	// checks if the graph is tiable (kashir)
	public static boolean isTiableGraph(boolean[][] mat) {
		 int dim = mat.length;
		 for (int k=0; k<dim; k++) {
		 	for (int i=0; i<dim; i++) {
		 		for (int j=0; j<dim; j++) {
		 			if (!mat[i][j]) {
		 				mat[i][j] = mat[i][k] && mat[k][j];  // checks connectivity with FW
		 			}
		 		}
		 	}
		 }
		for (boolean[] booleans : mat) {
			for (int j = 0; j < dim; j++) {
				if (!booleans[j]) return false; // if one kodkod is not kashir (false) then graph is not tiable.
			}
		}
		 return true;
	}

	// checks how many components after we done FW
	public static int HowManyComponents(boolean[][] matAfterFW) {
		int count = 0;
		int dim = matAfterFW.length;
		int[] comps = new int[dim];
		for (int i=0; i<dim; i++) {
			if (comps[i]==0) count++;
			for (int j=i; j<dim; j++) {
				if (comps[j]==0 && matAfterFW[i][j])
					comps[j] = count;
			}
		}
		String[] cs = new String[count];
		for (int i=0; i<count; i++) {
			cs[i] = "";
		}
		for (int i=0; i<dim; i++)
			cs[comps[i]-1] = cs[comps[i]-1]+ i +"\t";
		for (int i=0; i<count; i++)
			System.out.println(cs[i].toString());
		return count;  }



	// main func
	public static void main(String[] args) {

		boolean[][] m = initBoolMatrBottle(2,2);
		int[][] g = initIntMatrBottle(2, 2);
		printBoolMatrix(m);
		printMat(g, 2, 2);
		System.out.println(Arrays.deepToString(ConnectPossibleVertex(m, 1)));
		System.out.println(isDirectedGraph(m));
		System.out.println(isTiableGraph(m));

		int graph[][] = { {0,   5,  INF, 10},
				{INF, 0,   3, INF},
				{INF, INF, 0,   1},
				{INF, INF, INF, 0}
		};
		int afterFW[][] = floydWarshall(graph, 4);
		boolean afterFWAndBool[][] = convertIntFWToBool(afterFW, 4);
		printSolution(floydWarshall(graph, 4), 4);
		System.out.println(HowManyComponents(afterFWAndBool));
		System.out.println(Arrays.deepToString(initRibs(4,5)));
		FWNeg(graph);
	}


}
