package com.company;


// 305600579
public class BestCycle {
	double maxCircle; // sum of the largest sub-array
	double sumAll; // sum of all array elements
	double[] plusArr; // subarray
	double[] minusArr; // not-subarray
	double beginCircle;
	double endCircle;
	double sizeOfBest; // size of our 'Best' sub-array


	// constructor
	public BestCycle(double data[]) {
		int size = data.length;
		maxCircle = data[0];
		sumAll = 0;
		plusArr = new double[size];
		minusArr = new double[size];
		beginCircle = 0;
		endCircle = 0;
		sizeOfBest = 0;

		for (int i = 0; i < size; i++) {
			plusArr[i] = data[i];
			minusArr[i] = (-1)*data[i];
			sumAll = sumAll + data[i]; // saving all elements.
		}
	}

	// algo for finding largest sub-array in a circular array.
	public double getMaxSum() {
		Best positive = new Best(plusArr);
		double maxPositive = positive.getBest();
		if (maxPositive<0) { // if all elemets are negative, we stop here.
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;    sizeOfBest = 1;
			return maxCircle;   }

		Best negative = new Best(minusArr);
		double maxNegative = negative.getBest();
		if (maxPositive >= sumAll + maxNegative) {
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = endCircle - beginCircle + 1;
		}
		else {
			maxCircle = sumAll + maxNegative;
			beginCircle = negative.end+1;
			endCircle = negative.beginMax-1;
			sizeOfBest = plusArr.length - beginCircle + endCircle + 1;   }
		return maxCircle;  }

	// algo for finding largest sub-array in a circular array.
	public double getMinLength() {
		int arraySum = 1; // int to store our sum of shortest array with maximum sum, starting from 1
		Best positive = new Best(plusArr);
		double maxPositive = positive.getBest();
		if (maxPositive<0) { // if all elemets are negative, we stop here.
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;    sizeOfBest = 1;
			return maxCircle;   }

		Best negative = new Best(minusArr);
		double maxNegative = negative.getBest();
		if (maxPositive >= sumAll + maxNegative) {
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = endCircle - beginCircle + 1;
			arraySum++;

		}
		else {
			maxCircle = sumAll + maxNegative;
			beginCircle = negative.end+1;
			endCircle = negative.beginMax-1;
			sizeOfBest = plusArr.length - beginCircle + endCircle + 1;
			arraySum++; }
		return arraySum;  }

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

	}

	public static void main(String[] args) {
		BestCycle example = new BestCycle(new double[]{1, 2, -15, 7, 8, 0});
		BestCycle example2 = new BestCycle(new double[]{5, 1, -26, 6, 8, -18, 8});
		System.out.println(example.getMaxSum());
		System.out.println(example2.getMinLength());
	}

}
