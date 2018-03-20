package Q15_05_Call_In_Order;

/*
 * Call in order: suppose we have the following code:
 * public class Foo {
 *    public Foo() {...}
 *    public void first() {....}
 *    public void second() {....}
 *    public void third() {....}
 * }
 * the same instance of Foo will be passed to three different threads. ThreadA will call first(), ThreadB will call second(),
 * and ThreadC will call third(). design a mechanism to ensure that first() is called before second() and second() is called
 * before third().
 *
 * Solution:
 * using lock to do it. however this code down't actually quite work due to the concept of "lock ownership". one thread
 * is actually performing the lock( in the FooBad constructor). but different threads attempt to unlock the locks. this
 * is not allowed, and you code will raise exception. "a lock in java is owned by the same thread which locked it"
 *
 * instead, we can replicate this behavior with semaphores. the logic is identical: Foo.java
 *
 * Lucy Notes: Lock, mutex, Semaphore are  related to concurrent programming, what's the difference?
 * 1) A lock allows only one thread to enter the part that's locked, and the lock is not shared with any other processes.
 * 2) A mutex is the same as a lock but it can be system wide (shared by multiple processes).
 * 3) A semaphore does the same as a mutex but allows x number of threads to enter, this can be used for example to limit
 * the number of cpu, io or ram intensive tasks running at the same time.
 *
 * You also have read/write locks that allows either unlimited number of readers or 1 writer at any given time.
 *
 *  JAVA 8 API Doc:
 * 1) A reentrant mutual exclusion Lock with the same basic behavior and semantics as the implicit monitor lock
 * accessed using "synchronized" methods and statements, but with extended capabilities. A ReentrantLock is owned by
 * the thread last successfully locking, but not yet unlocking it. A thread invoking lock will return, successfully
 * acquiring the lock, when the lock is not owned by another thread. The method will return immediately if the current
 * thread already owns the lock. This can be checked using methods isHeldByCurrentThread(), and getHoldCount().
 *
 * 2) Semaphore: a semaphore maintains a set of permits. Each acquire() blocks if necessary until a permit is available,
 * and then takes it. Each release() adds a permit, potentially releasing a blocking acquirer. However, no actual
 * permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly.
 *
 * Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource.
 *
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html
 */
public class Question {

  public static void main(String[] args) {
    FooBad foo = new FooBad();

    MyThread thread1 = new MyThread(foo, "first");
    MyThread thread2 = new MyThread(foo, "second");
    MyThread thread3 = new MyThread(foo, "third");

    thread3.start();
    thread2.start();
    thread1.start();
  }
}
