package com.company;

public class Best {

	double max;
	double beginMax;
	double end;
	double[] arr;
	int maxSum; // for matrix best.

	public Best(double[] Array) {
		arr = Array;
		max = arr[0];
		beginMax = 0;
		end = 0;
	}


	// regular best algorithm, runs at O(n)
	public double getBest() {
		int i = 0;
		int tempSum, tempBegin;
		while (arr[i] <= 0) { // if we begin with negative numbers
			i++; // advance till next positive
			if (i == arr.length) return max; // if only negatives, return what there is
			if (arr[i] > max) { // if we found a larger negative, we replace him.
				max = arr[i];
				beginMax = i;
				end = i;
			}
		}

		tempSum = 0;
		tempBegin = (int) beginMax;
		while (i < arr.length) { // if we are starting with positives..
			tempSum = (int) (tempSum + arr[i]);
			if (tempSum < 0) { // once sum is below zero
				tempSum = 0; // zero the sum
				tempBegin = i + 1; // advance our starting point
			} else if (tempSum >= max) { // if we found a bigger series..
				max = tempSum;
				beginMax = tempBegin;
				end = i;
			}
			i++;
		}
		return max;
	}

	public static void main(String[] args) {
		Best example = new Best(new double[]{1, 2, 3, 5, 6});
		System.out.println(example.getBest());
	}
}
