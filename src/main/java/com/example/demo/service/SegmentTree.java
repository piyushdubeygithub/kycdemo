package com.example.demo;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
import java.util.*;

class Pair {
	int d;
	int index;
}

// java program to show segment tree operations like construction, update and querry
class SegmentTree {
	// the array that stores the segment tree nodes
	int[] st;

	/*
	 * constructor to construct segment tree from given array. This constructor
	 * allocates memory for segment tree and calls constructSTUtil() to fill
	 * allocated memory
	 */
	public SegmentTree(int[] a, int n) {
		// Height of segment tree
		int x = (int) Math.ceil((Math.log(n) / Math.log(2)));
		// maximum size of segment tree
		int maxSize = 2 * (int) Math.pow(2, x) - 1;
		// memory allocation
		st = new int[maxSize];
		constructSTUtil(a, 0, n - 1, 0);
		for (int i = 0; i < maxSize; i++) {
			System.out.print(st[i] + " ");
		}
	}

	// a recursive function which creates segment tree for array[ss..se]
	// si is the index of current node in the segment tree st
	private int constructSTUtil(int[] a, int ss, int se, int si) {
		// if there is only one element in the array store it in the current node of the
		// segment tree and return
		if (ss == se) {
			st[si] = a[ss];
			return a[ss];
		}
		int mid = getMid(ss, se);
		/*
		 * if there are more than one elements, then recur for left and right subtrees
		 * and store the value in the current node
		 */
		st[si] = constructSTUtil(a, ss, mid, 2 * si + 1) + constructSTUtil(a, mid + 1, se, 2 * si + 2);
		return st[si];
	}

	/*
	 * a utility function to get middle index from corner indexes
	 */
	private int getMid(int ss, int se) {
		return ss + (se - ss) / 2;
	}

	/*
	 * returns sum of elements from qs(query start) to qs(query end). It mainly uses
	 * getSumUtil()
	 */
	public int getSum(int n, int qs, int qe) {
		// check for errorneous input
		if (qs < 0 || qe > n - 1 || qs > qe) {
			System.out.println("invalid input");
			return -1;
		}
		return getSumUtil(0, n - 1, qs, qe, 0);
	}

	/*
	 * A recursive function to get sum of values in a given range of a array. st-->
	 * pointer to segment tree si--> index of current node in the segment tree.
	 * Initially 0 is passed as root is always at index 0 ss & se--> starting and
	 * ending index of the segment represented by the current node i.e st[si] qs &
	 * qe--> starting and ending index of query range
	 */
	private int getSumUtil(int ss, int se, int qs, int qe, int si) {
		// if the segment of this node is part of the query range, then return sum of
		// the segment
		if (qs <= ss && qe >= se) {
			return st[si];
		}
		// if segment of this node is not part of the query range
		if (se < qs || ss > qe) {
			return 0;
		}
		int mid = getMid(ss, se);
		// if part of this segment overlaps with the given query range
		return getSumUtil(ss, mid, qs, qe, 2 * si + 1) + getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
	}

	/*
	 * this function to update value in array and segment tree it uses
	 * updateValueUtil to update value in segment tree
	 */
	public void updateValue(int[] a, int n, int i, int newVal) {
		// check for errorneous input
		if (i < 0 || i > n - 1) {
			System.out.println("invalid input");
			return;
		}
		// get the difference between new value and old value
		int diff = newVal - a[i];
		// update the value in array
		a[i] = newVal;
		// update the value of nodes in the segment tree
		updateValueUtil(0, n - 1, i, diff, 0);
	}

	/*
	 * a recursive function to update the nodes having index in their range i-->
	 * index of element to be updated in input array. diff--> value to be added to
	 * all nodes having i in their range
	 */
	private void updateValueUtil(int ss, int se, int i, int diff, int si) {
		// base case: if input index lies outside the range of this segment
		if (i < ss || i > se) {
			return;
		}
		st[si] = st[si] + diff;
		int mid = getMid(ss, se);
		updateValueUtil(ss, mid, i, diff, 2 * si + 1);
		updateValueUtil(ss, mid, i, diff, 2 * si + 2);
	}

}

class Test {
	public static void main(String args[]) throws Exception {

		int arr[] = { 1, 3, 5, 7, 9, 11 };
		int n = arr.length;
		SegmentTree tree = new SegmentTree(arr, n);

		// Build segment tree from given array

		// Print sum of values in array from index 1 to 3
		System.out.println("Sum of values in given range = " + tree.getSum(n, 1, 3));

		// Update: set arr[1] = 10 and update corresponding segment
		// tree nodes
		tree.updateValue(arr, n, 1, 10);

		// Find sum after the value is updated
		System.out.println("Updated sum of values in given range = " + tree.getSum(n, 1, 3));
	}
}
