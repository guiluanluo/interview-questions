package Q9_02_Social_Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Social network: how would you design the data structures for a very large social network link facebook or linkedin?
 * describe how you would design an algorithm to show the shortest path between tow people( me->bob->susan->jason->you)
 *
 * Solution: a good way to approach this problem is to remove some of the constraints and solve.
 *
 * Step 1: simplify the problems - forget about the millions of user. If i want to find the path between two people, I
 * could start with one person and do a simple breadth-first-search (BFS is the best way to find the shortest path
 * between two nodes in the graph)
 *
 * ==== System Design & Scalability: step-by-step ====
 * 1) scope the problem: design the data structure for an algorithm to find the shortest path between two people at a
 *    very large social network
 *
 * 2) make reasonable assumptions: to simplify the problem, we make assumption that users data is stored at one machine.
 *
 * 3) draw major components (please use whiteboard): for graph, depth-first search could find a path between two person,
 *    but it may not be the shortest path. bidirectional-breath-first search is the best algorithm for this problem
 *
 *   core components: when consider users data store at multiple, then draw system design with core components
 *   core objects: Person(Id, Name, lis of friends), BFSData(isVisited hashTable, toVisitQueue), PathNode(person, previousNode)
 *
 *    === Object-Oriented Design(OOD) Approach ===
 *    a) handle ambiguity: should inquire 1) who is going to use it and how they are going to use.
 *       depend on questions, you may even want to go through the "six Ws": who, what, where, when, how, why
 *    b) define the core objects
 *    c) analyze relationships between objects: which objects are members of which other object? do any object inherit
 *      from any others? are relationship many-to-many or one-to-many?
 *    d) investigate actions: consider the key actions that the objects will take and how they relate to each other
 *
 * 3) identity key issues: when deal with a service with large size of LinkedIn or Facebook, we can not keep all of user
 *    data in one machine! our "Person" object data structure doesn't quite work - since our friends may not live on
 *    the same machine at we do.
 *
 * 4) redesign for key issues: one server could have multiple machines, and one machine contains multiple Persons
 *    need two new objects: Server(HashMap<Integer, Machine> machines, HashMap<Integer, Integer> personToMachineMap),
 *                        Machine (Id, HashMap<Integer, Person> persons)
 *
 * 5) optimization:
 *    a) reduce machine jump: if 5 of my friends live on one machine, then I should look them up all at one time
 *    b) smart division of people and machine: divide people by country, city, state, and so on. this will reduce the number of jump
 */
public class QuestionA {

  public static LinkedList<Person> findPathBFS(HashMap<Integer, Person> people, int source, int destination) {

    Queue<PathNode> toVisit = new LinkedList<PathNode>();
    HashSet<Integer> visited = new HashSet<Integer>();

    toVisit.add(new PathNode(people.get(source), null));
    visited.add(source);

    while (!toVisit.isEmpty()) {
      PathNode node = toVisit.poll();
      Person person = node.getPerson();
      if (person.getID() == destination) {
        return node.collapse(false);
      }

			/* Search friends. */
      ArrayList<Integer> friends = person.getFriends();
      for (int friendId : friends) {
        if (!visited.contains(friendId)) {
          visited.add(friendId);
          Person friend = people.get(friendId);
          toVisit.add(new PathNode(friend, node));
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int nPeople = 11;
    HashMap<Integer, Person> people = new HashMap<Integer, Person>();
    for (int i = 0; i < nPeople; i++) {
      Person p = new Person(i);
      people.put(i, p);
    }

    int[][] edges = {{1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}, {3, 7}};

    for (int[] edge : edges) {
      Person source = people.get(edge[0]);
      source.addFriend(edge[1]);

      Person destination = people.get(edge[1]);
      destination.addFriend(edge[0]);
    }

    int i = 1;
    int j = 10;
    LinkedList<Person> path1 = findPathBFS(people, i, j);
    Tester.printPeople(path1);
  }

}
