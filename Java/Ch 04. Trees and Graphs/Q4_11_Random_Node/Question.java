package Q4_11_Random_Node;

/**
 * Random node: you are implementing a binary tree class from scratch which, in addition to insert, find, and delete,
 * has a method getRandomNode() which returns a random node from the tree. all nodes should be equally likely to be
 * chosen. design and implement an algorithm for getRandomNode, and explain how you would implement the rest of the
 * methods.
 *
 * hint 42: be very careful in this problem to ensure that each node is equally likely and that your soluton doesn't
 * dlow doen the speed of standard binary search tree algorithms (link insert, find, delete). also, remember that even
 * if you assume that it's a balanced binary search tree, this doesn't mean that the tree is full/complete/perfect.
 *
 * hint 62: as a naive "brute force" algorithm, can you use a tree traversal algorithm to implement this algorithm? what
 * is the runtime of this?
 *
 * hint 75: alternatively, you could pick a random depth to traverse to and then randomly traverse, stopping when you
 * get to that depth. think this through, though. does this works?
 *
 * hint 89: picking a random depth worn't help us much. first, there's more nodes  at lower depths than higher depth.
 * second, even if we re-balanced these probabilities, we could hit a "dead end" where we meant to pick a node a depth 5
 * but hit a leaf at depth 3. re-balanced the probabilities is an interesting, though.
 *
 * hint 99: a naive approach that many people come up with is to pick a random number between 1 and 3. if it's 1, then
 * return the current node. if its's 2, branch left,. if it's 3, branch right. this solution doesn't work, why not? is
 * there a wya you can adjust it to make it work?
 *
 * hint 112: the reason that the earlier solution(picking a random number between 1 and 3) doesn't work is that the
 * probabilities for the nodes won't be equal. for example, the root will be return with probability 1/3, even fi there
 * are 50+ nodes int he tree. clearly, not all the nodes have probabilities 1/3, so these nodes won't have equal
 * probabilities. we can resolve this one issue by picking a random number between 1 and size_of_tree instead. this only
 * resolves the issue for the root, though. what about the reset of the nodes?
 *
 * hint 119: the issue with earlier solution is that there could be more nodes on one side of a node that the other. so,
 * we need ot weight the probability of going left and right based on the number of nodes on each side. how does this
 * work, exactly? how can we know the number of nodes?
 */
public class Question {

  public static void main(String[] args) {
    int[] counts = new int[10];
    for (int i = 0; i < 1000000; i++) {
      Tree tree = new Tree();
      int[] array = {1, 0, 6, 2, 3, 9, 4, 5, 8, 7};
      for (int x : array) {
        tree.insertInOrder(x);
      }
      int d = tree.getRandomNode().data;
      counts[d]++;
    }

    for (int i = 0; i < counts.length; i++) {
      System.out.println(i + ": " + counts[i]);
    }
  }

}
