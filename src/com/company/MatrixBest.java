package com.company;

import java.util.Arrays;

// finding the largest-sum submatrix.
public class MatrixBest {

	int mat[][];
	int helpMat[][];
	int n, m;

	public MatrixBest(int firstMat[][]) {
		mat = firstMat;
		n = mat.length;
		m = mat[0].length;
		helpMat = new int[n][m + 1]; // important! make amudot m+1! (for amudat afasim)
	}

	// filling our help matrix
	public void fillHelpMatrix() {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < m + 1; j++) {
				helpMat[i][j] = mat[i][j - 1] + helpMat[i][j - 1];
			}
		}
	}

	// function for calculating the largest sum of a submatrix.
	public int getMaxMatrixOn3() {
		double maxSum = 0; // maximum sum reached
		double startI = 0; // the left-up most corner of our matrix
		double startJ = 0;
		double endI = 0; // the right-down most corner of our matrix.
		double endJ = 0;
		double v[] = new double[n];
		Best SuperBest; // we gonna use Best soon
		for (int jb = 0; jb < m + 1; jb++) {
			for (int je = jb + 1; je < m + 1; je++) {
				for (int i = 0; i < n; i++) {
					v[i] = helpMat[i][je] - helpMat[i][jb]; // creating the sum of all sub-matrixes.
				}
				SuperBest = new Best(v); // activating best on row
				SuperBest.getBest();
				if (SuperBest.max > maxSum) {
					maxSum = SuperBest.max;
					startI = SuperBest.beginMax;
					endI = SuperBest.end;
					startJ = jb;
					endJ = je - 1;
				}
			}
		}
		System.out.println("the max sum is " + maxSum);
		System.out.println("[i1,j1] -> [" + startI + "," + startJ + "]");
		System.out.println("[i2,j2] -> [" + endI + "," + endJ + "]");
		return (int) maxSum;
	}

	public static void main(String[] args) {
		int[][] mat = {{1,2,-3}, {3,4,-5}, {1,6,-7}};
		MatrixBest best = new MatrixBest(mat);
		System.out.println(Arrays.toString(best.mat[0]));
		best.fillHelpMatrix();
		System.out.println(Arrays.toString(best.helpMat[0]));
		best.getMaxMatrixOn3();

	}
}
