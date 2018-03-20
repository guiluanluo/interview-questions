package Q15_03_Dining_Philosophers.QuestionA;

/**
 * Dining Philosophers: in the famous dining philosopher problem, a bunch of philosophers are sitting around a circular
 * table with one chopstick between each of them. a philosopher needs both chopsticks to eat, and always picks up the
 * left chopstick before the right one. a deadlock could potential occur if all the philosophers reached for the left
 * chopstick at the same time. using threads and locks, implement a simulation of the dining philosophers problem that
 * prevents deadlocks.
 *
 * In Solution A, we need to be sure to release the left chopstick if we can't pick up the right one - and to not call
 * putDown() on the chopsticks if we never had them in the first place
 *
 * One issue with this is that if all the philosophers were perfectly synchronized, the could simultaneously pick up
 * their left chopstick, be unable to pick up the right one, and then put back down the left one - only to have the
 * process repeated again.
 *
 * Solution B: prioritize: alternatively, we can label the chopsticks with a number from 0 to N-1. each philosopher
 * attempts to pick up the lower number chopstick first. this essentially means that each philosopher goes for the left
 * chopstick before right one ( assuming that's the way you labeled it), except for the last philosopher who does this
 * in reverse. the will break the cycle.
 */
public class Question {

  public static int size = 3;

  public static int leftOf(int i) {
    return i;
  }

  public static int rightOf(int i) {
    return (i + 1) % size;
  }

  public static void main(String[] args) {

    Chopstick[] chopsticks = new Chopstick[size + 1];
    for (int i = 0; i < size + 1; i++) {
      chopsticks[i] = new Chopstick();
    }

    Philosopher[] philosophers = new Philosopher[size];
    for (int i = 0; i < size; i++) {
      Chopstick left = chopsticks[leftOf(i)];
      Chopstick right = chopsticks[rightOf(i)];
      philosophers[i] = new Philosopher(i, left, right);
    }

    for (int i = 0; i < size; i++) {
      philosophers[i].start();
    }
  }
}
