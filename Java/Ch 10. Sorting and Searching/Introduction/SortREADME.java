package Introduction;

/**
 * 1:Merge sort | runtime: O(n log n) average and worst case. memory: depends
 * merge sort divides the array in half, sorts each of those halves, and then merges them back together. each of those
 * halves has the same sorting algorithm applied to it. eventually, you are merging just two single-element arrays. it
 * is the "merge" part that does all the heavy lifting.
 *
 * 2:Quick Sort | runtime: O(n log n) average, O(n*n) worst case. memory: O(log(n))
 * in quick sort, we pick a random element and partition the array, such that all numbers that are less than the
 * partitioning element come before all elements that are greater than it. the partitioning can be performed efficiently
 * through a series of swaps.
 *
 * 3:Bucket sort is mainly useful when input is uniformly distributed over a range. For example, consider the following
 * problem: "Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed
 * across the range." How do we sort the numbers efficiently?
 *
 * Selection Sort | Runtime: O(n*n) average and worst case. memory: O(1)
 * Selection sort is the child's algorithm:
 * simple, but inefficient. find the smallest element using a linear scan and move it to the front
 * (swapping it with the front element). then, find the second smallest and move it, again doing a
 * linear scan. continue doing this until all the elements are in place.
 */
public class SortREADME {

}
