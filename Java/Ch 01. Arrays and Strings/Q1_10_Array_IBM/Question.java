package Q1_10_Array_IBM;

/**
 * 
 * A professor in the Computer Science department of HackerLand College wants to
 * generate an array. Given an array of integers of length n, arr, and two
 * integer l and r, find another array, brr, such that:
 * 
 * a) l <= brr[i]<= r
 * 
 * b) brr[i]-arr[i]< brr[i+1]-arr[i+1], for every i less than n-1
 * 
 * c) brr[i]<= brr[i+1] for every i less than n-1
 * 
 * Among all such arrays, return the lexicographically(按词典顺序) smallest one. if
 * there in not an array that satisfies the conditions, the return an array with
 * the single element -1.
 * 
 * Example: arr=[1,2,1,2], i=1, r=10; the array [1,3,3,5] satisfies conditions
 */

public class Question {

	public static int[] findLexiSmallestArray(int arr[], int l, int r) {
		int size = arr.length;
		int[] brr = new int[size];
		boolean invalid = false;

		brr[0] = (arr[0] >= l && arr[0] <= r) ? l : 1; // set the first element based on condition a)
		for (int i = 1; i < size; i++) {
			int value = (brr[i - 1] - arr[i - 1]) + arr[i]; // based on condition b)

			// based on condition a), c), add 1 for the lexicographically smallest one
			if (value >= l && value <= r) {
				brr[i] = value + 1;
			} else {
				invalid = true;
				break;
			}
		}

		if (invalid) {
			for (int j = 0; j < size; j++) {
				brr[j] = -1;
			}
		}

		return brr;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 1, 2 };
		int l = 1, r = 10;
		int[] brr = findLexiSmallestArray(arr, l, r);
		System.out.print("=Results:\n");
		for (int j : brr) {
			System.out.print(j + "\n");
		}
	}
}
