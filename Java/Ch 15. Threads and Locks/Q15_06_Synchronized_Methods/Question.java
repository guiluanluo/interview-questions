package Q15_06_Synchronized_Methods;

/**
 * Synchronized methods: you are given a class with synchronized method A, and a normal method B. if you have two
 * threads in one instance of a program, can they both execute A at the same time? can they execute A and B at the same
 * time?
 *
 * Solution: by applying the word synchronized to a method, we ensure that two threads cannot execute synchronized
 * method on "the same object instance" at the same time.
 *
 * 1) so the answer to the first part really depends. if the two threads have the same instance of the object, then NO,
 * they cannot simultaneously execute method A. however, if they have different instances of the object, then they can!
 *
 * conceptually, you can see this by considering locks. a synchronized method applies a "lock" on all synchronized
 * methods in that instance of the object.this blocks other threads from executing synchronized within that instance.
 *
 * 2) in the second part, we are asked if thread1 can execute synchronized method A while thread2 is executing
 * non-synchronized method B. since B is not synchronized, there is nothing to block thread1 from executing A while
 * thread2 is executing B. this is tre regardless of whether thread1 and thread2 have the same instance of the object.
 */
public class Question {

  public static void main(String[] args) {

    /* Part 1 Demo -- same instance */
    System.out.println("Part 1 Demo with same instance.");
    Foo fooA = new Foo("ObjectOne");
    MyThread thread1a = new MyThread(fooA, "Dog", "A");
    MyThread thread2a = new MyThread(fooA, "Cat", "A");
    thread1a.start();
    thread2a.start();
    while (thread1a.isAlive() || thread2a.isAlive()) {
    }
    System.out.println("\n\n");

		/* Part 1 Demo -- difference instances */
    System.out.println("Part 1 Demo with different instances.");
    Foo fooB1 = new Foo("ObjectOne");
    Foo fooB2 = new Foo("ObjectTwo");
    MyThread thread1b = new MyThread(fooB1, "Dog", "A");
    MyThread thread2b = new MyThread(fooB2, "Cat", "A");
    thread1b.start();
    thread2b.start();
    while (thread1b.isAlive() || thread2b.isAlive()) {
    }

    System.out.println("\n\n");

		/* Part 2 Demo */
    System.out.println("Part 2 Demo.");
    Foo fooC = new Foo("ObjectOne");
    MyThread thread1c = new MyThread(fooC, "Dog", "A");
    MyThread thread2c = new MyThread(fooC, "Cat", "B");
    thread1c.start();
    thread2c.start();
  }

}
