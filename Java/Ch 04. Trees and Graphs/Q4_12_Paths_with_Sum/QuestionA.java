package Q4_12_Paths_with_Sum;

import CtCILibrary.TreeNode;

/**
 * Path with sum: you are given a binary tree in which each node contains an integer value(which might be positive or
 * negative). design an algorithm to count the number of paths that sum to a given value. the path does not need to
 * start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * hint 6: try simplifying te problem. what if the path had to start at the root.
 *
 * hint 14: don't forget that paths could overlap. for example, if you are looking for the sam 6, the path 1->3->2 and
 * 1->3->2->4->6->2 are both valid.
 *
 * hint 52: if each path ahd to start at the root, we could traverse all possible paths starting from the root. we can
 * track the sum as we go, incrementing totalPaths each time we find a path with out target sum. now, how do we extend
 * this to paths that can start anywhere? remember: just get a brute-force algorithm done, you could optimize later.
 *
 * hint 68: to extend this to paths that start anywhere, we can just repeat this process for all nodes.
 *
 * hint 77: if you've designed the algorithm as described thus far, you'll have an O(N long N) algorithm in a balance
 * tree. this is because there are N nodes, each of which is at depth O(log N) at worst. a node is touched once for each
 * node above it. therefore, the N nodes will be touched O(long N) tie. there is an optimization that will give us an
 * O(N) algorithm.
 *
 * hint 87: what work is duplicate in the current brute-force algorithm?
 *
 * hint 94: consider each path that starts from the root (there are N such paths) as an array. what our brute-force
 * algorithm is really doing is taking each array and finding all contiguous subsequences that have a particular sum. we
 * are doing this by computing all subarrays and their sums. it might be useful to just focus on this little subproblem.
 * given an array, how woul you find all contiguous subsequences with a particular sum? again, think about the
 * duplicated work in the brute-force algorith.
 *
 * hint 103: we are looking for subarrays with sum targetSum. observe that we can track in contonst time tht value of
 * runingSum, where this is the sume from element 0 through element i. for a subarray of element i through element j to
 * have sum targetSUme, runningSUm(i-1) + targetSum must equal runningSum(j)( try drawing a picture of an array or a
 * number line). given that we can track the urnnignSum as we go, how can we quickly lookup the number of indices i
 * where the previous equation is true?
 *
 * hint 108: try using a hash table that maps from a runningSum value to the number of elements with this runningSum
 *
 * hint 115: once you've solidified the algoritm to find all contiguous subarays in an array with a given sum, try to
 * apply this to a tree. remember that as you're traversing and modifying the hash table, you may need to "reverse the
 * damage" to the hash table as you traverse back up.
 *
 * Solution A: brute force - just look at all possible paths. to do this, we traverse to each node. at each node, we
 * recursively try all paths downwards, tracking the sam as we go. as soon as we hit out targetSum, we increate the
 * total.
 *
 * this algorithm talk O((N log N) runti in a balance tree. in an unbalance tree, the runtime could be much worse
 * O(N*N)
 */
public class QuestionA {

  public static int countPathsWithSum(TreeNode root, int targetSum) {
    if (root == null) {
      return 0;
    }

		/* Count paths with sum starting from the root. */
    int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

		/* Try the nodes on the left and right. */
    int pathsOnLeft = countPathsWithSum(root.left, targetSum);
    int pathsOnRight = countPathsWithSum(root.right, targetSum);

    return pathsFromRoot + pathsOnLeft + pathsOnRight;
  }

  /* Returns the number of paths with this sum starting from this node. */
  public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
    if (node == null) {
      return 0;
    }

    currentSum += node.data;

    int totalPaths = 0;
    if (currentSum == targetSum) { // Found a path from the root
      totalPaths++;
    }

    totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum); // Go left
    totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum); // Go right

    return totalPaths;
  }

  public static void main(String[] args) {
    /*
    TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);		
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(8);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(6);	
		System.out.println(countPathsWithSum(root, 0));*/

		/*TreeNode root = new TreeNode(-7);
    root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(countPathsWithSum(root, -14));*/

    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(0);
    root.right = new TreeNode(0);
    root.right.left = new TreeNode(0);
    root.right.left.right = new TreeNode(0);
    root.right.right = new TreeNode(0);
    System.out.println(countPathsWithSum(root, 0));
    System.out.println(countPathsWithSum(root, 4));
  }
}

