package Q15_05_Call_In_Order;

import java.util.concurrent.locks.ReentrantLock;


/**
 * Lucy Notes: Lock, mutex, Semaphore are  related to concurrent programming, what's the difference?
 *
 * A lock allows only one thread to enter the part that's locked and the lock is not shared with any other processes.
 *
 * A mutex is the same as a lock but it can be system wide (shared by multiple processes).
 *
 * A semaphore does the same as a mutex but allows x number of threads to enter, this can be used for example to limit
 * the number of cpu, io or ram intensive tasks running at the same time.
 *
 * You also have read/write locks that allows either unlimited number of readers or 1 writer at any given time.
 */
public class FooBad {

  public int pauseTime = 1000;
  public ReentrantLock lock1;
  public ReentrantLock lock2;

  public FooBad() {
    try {
      lock1 = new ReentrantLock();
      lock2 = new ReentrantLock();

      lock1.lock();
      lock2.lock();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void first() {
    try {
      System.out.println("Started Executing 1");
      Thread.sleep(pauseTime);
      System.out.println("Finished Executing 1");
      lock1.unlock();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void second() {
    try {
      lock1.lock();
      lock1.unlock();
      System.out.println("Started Executing 2");
      Thread.sleep(pauseTime);
      System.out.println("Finished Executing 2");
      lock2.unlock();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void third() {
    try {
      lock2.lock();
      lock2.unlock();
      System.out.println("Started Executing 3");
      Thread.sleep(pauseTime);
      System.out.println("Finished Executing 3");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
