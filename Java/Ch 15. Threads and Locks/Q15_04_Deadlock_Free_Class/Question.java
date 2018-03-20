package Q15_04_Deadlock_Free_Class;

/**
 * Deadlock-free class: design a class which provides a look on if there are not possible deadlocks.
 *
 * Solution: there are several common ways to prevent deadlock. one of the popular ways is to require a process to
 * declare upfront what looks it will need. we can then verify if a deadlock would be created by issuing these locks,
 * and we can fail if so.
 *
 * with these constraints in mind, let's investigate how we can detect deadlocks. suppose this was the orders of locks
 * requested. we can think about this as a graph, a deadlock is represented by a cycle!!
 */
public class Question {

  public static void main(String[] args) {
    int[] res1 = {1, 2, 3, 4};
    int[] res2 = {1, 5, 4, 1};
    int[] res3 = {1, 4, 5};

    LockFactory.initialize(10);

    LockFactory lf = LockFactory.getInstance();

    /**
     * To prevent deadlocks, force the processes to declare upfront what order they will need the locks in. Verify that
     * this order does not create a deadlock (a cycle in a directed graph).
     * if we created a cycle, destroy this resource list and return false; No cycles detected and return true.
     * Save the order that was declared, so that we can verify that the process is really calling the locks in the order
     * it said it would.
     */
    System.out.println("declare ownerId 1, res1: " + lf.declare(1, res1));
    System.out.println("declare ownerId 2, res2: " + lf.declare(2, res2));
    System.out.println("declare ownerId 3, res3: " + lf.declare(3, res3));

    System.out.println("get lock by owner 1, resourceId 1: " + lf.getLock(1, 1));
    System.out.println("get lock by owner 1, resourceId 2: " + lf.getLock(1, 2));

    System.out.println("get lock by owner 2, resourceId 1: " + lf.getLock(2, 1));
    System.out.println("get lock by owner 2, resourceId 5: " + lf.getLock(2, 5));
    System.out.println("get lock by owner 2, resourceId 4: " + lf.getLock(2, 4));

    System.out.println("get lock by owner 3, resourceId 1: " + lf.getLock(3, 1));
    System.out.println("get lock by owner 3, resourceId 4: " + lf.getLock(3, 4));
  }

}
