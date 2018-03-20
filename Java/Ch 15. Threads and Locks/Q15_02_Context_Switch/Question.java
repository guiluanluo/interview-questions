package Q15_02_Context_Switch;

/*
 * A Context Switch is the time spent switching between tow processes (such as bring a waiting process into execution
 * and sending an executing process into waiting/terminated state). this happens in multasking. the operating system
 * must bring the state information of waiting process into memory and save the state information of the currently
 * running process.
 *
 * Let's take easy example: assume there are only two processes P1, P2. an iteration of the game is played with the
 * following steps:
 *
 * 1) P2 blocks awaiting data from P1
 * 2) P1 marks the start time
 * 3) P1 sends token to P2
 * 4) P1 attempts to read a response token from P2. This induces a context switch
 * 5) P2 is scheduled and receives the token
 * 6) P2 sends a response token to P1
 * 7) P2 attempts to read a response token from P1. this induces a context switch
 * 8) P1 is scheduled and receives the token
 * 9) P1 marks the end time
 *
 * To measure how much time a context switch takes I would run something like the following
 */
public class Question {

  public static long startTime = 0L;
  public static long endTime = 0L;

  public static void main(String[] args) {

    Object theLock = new Object();

    synchronized (theLock) {
      Thread task = new TheTask(theLock);
      task.start();
      try {
        theLock.wait();
        endTime = System.nanoTime();
      } catch (InterruptedException e) {
        // do something if interrupted
      }
    }
    System.out.println(
        "Context Switch Time elapsed: endTime:" + endTime + ", startTime:" + startTime + ", " + (endTime - startTime));
  }

  public static class TheTask extends Thread {

    private Object theLock;

    public TheTask(Object theLock) {
      this.theLock = theLock;
    }

    public void run() {
      synchronized (theLock) {
        startTime = System.nanoTime();
        theLock.notify();
      }
    }
  }

}
