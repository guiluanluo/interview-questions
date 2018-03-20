package Q16_10_Living_People;

import java.util.Arrays;
import java.util.Random;

/**
 * To optimize Solution C, we need to get rid of the sorting step. we're back to dealing with unsorted values: birth:
 * 12,20,10,01,23,13,90,83,75 death: 15,90,98,72,98,82,98,99,94 we can create an array of the years, where the value at
 * array[year] indicates how the population changed in that year. to create this array, we walk through the list of
 * people and increment when they're born and decrement when they die.
 *
 * once we have this array, we can walk through each of the years, tracking the current population as we go (adding the
 * value at array[year] each time). this logic is reasonable good, but we should think about it more. does it really
 * work?
 *
 * one edge case we should consider is when a person dies at the same year that they're born. the increment and
 * decrement operations will cancel out to give 0 population change. according to the wording of the problem, this
 * person should be counted as living in that year. people who die in 1908 shouldn't be removed from the population
 * count until 1909. there is a simple fix: instead of decrementing array[deathYear], we should decrement
 * array[deathyear +1].
 */
public class QuestionD {

  public static int maxAliveYear(Person[] people, int min, int max) {
    /* Build population delta array. */
    int[] populationDeltas = getPopulationDeltas(people, min, max);
    System.out
        .println("populationDeltas: length=" + populationDeltas.length + ", " + Arrays.toString(populationDeltas));

    int maxAliveYear = getMaxAliveYear(populationDeltas);
    return maxAliveYear + min;
  }

  /* Add birth and death years to deltas array. */
  public static int[] getPopulationDeltas(Person[] people, int min, int max) {
    int[] populationDeltas = new int[max - min + 2];
    for (Person person : people) {

      int birth = person.birth - min;
      populationDeltas[birth]++;

      int death = person.death - min;
      populationDeltas[death + 1]--;

      System.out.println("people birth/death: " + person.birth + "/" + person.death
          + ", birthIndex/deathIndex:" + birth + "/" + death
          + ", value[" + birth + "," + (death + 1) + "]: " + populationDeltas[birth] + "/" + populationDeltas[death
          + 1]);
    }
    return populationDeltas;
  }

  /* Compute running sums and return index with max. */
  public static int getMaxAliveYear(int[] deltas) {
    int maxAliveYear = 0;
    int maxAlive = 0;
    int currentlyAlive = 0;
    for (int year = 0; year < deltas.length; year++) {
      currentlyAlive += deltas[year];
      if (currentlyAlive > maxAlive) {
        maxAliveYear = year;
        maxAlive = currentlyAlive;
      }
    }

    return maxAliveYear;
  }

  public static void main(String[] args) {
    int n = 3;
    int first = 1900;
    int last = 2000;
    Random random = new Random();
    Person[] people = new Person[n];
    for (int i = 0; i < n; i++) {
      int birth = first + random.nextInt(last - first);
      int death = birth + random.nextInt(last - birth);
      people[i] = new Person(birth, death);
      System.out.println(birth + ", " + death);
    }
    int year = maxAliveYear(people, first, last);
    System.out.println(year);
  }

}
