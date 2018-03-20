package Q16_10_Living_People;

import java.util.Random;

/**
 * Living people: given a list of people with either birth and death years, implement a method to compute the year with
 * the most number of people alive. you may assume that all people were born between 1900 and 20000 (inclusive). if a
 * person was alive during any portion of that year, they should be included in that year's count. for example,
 * person(birth=1908, death=1909) is included in the counts for both 1908 and 1909.
 *
 * hint 475: solution 1: can you count the number of people alive in each year?
 *
 * hint 489: solution 1: try using a hash table, or an array that mps from a birth year to how many people are alive in
 * that year
 *
 * hint 506: solution 2: what if you sorted the years? what would you sorted by?
 *
 * hint 513: solution 2: do you actually need to match the birth years and death years? does it matter when a specific
 * person died, or do you just need a list of the years of deaths?
 *
 * hint 522: solution 2: observe that people are "fungible". it doesn't matter who was born and when they died. all you
 * need is a list of birth years and death years. this might make the question of how your sort the list of people
 * easier.
 *
 * hint 531: solution 2: try creating a sorted list of births and a sorted list of deaths. can you iterate through both,
 * tracking the number of people alive at any one time?
 *
 * hint 540: solution 3: each birth adds one person and each death removes a person. try writing an example of a list of
 * people (with birth and death years) and then re-formatting this into a list of each year and a+1 for a birth and a-1
 * for death.
 *
 * hint 548: solution 3: what if you created an array of years and how the population changed in each year> could you
 * then find the year with the highest populations?
 *
 * hint 575: solution 3: be careful with the little details in the problem. does your algorithm/code handle a person who
 * dies in the same year that they are born? this person should be counted as one person on the population count
 */
public class QuestionA {

  public static int maxAliveYear(Person[] people, int min, int max) {
    int maxAlive = 0;
    int maxAliveYear = min;

    for (int year = min; year <= max; year++) {

      int alive = 0;
      for (Person person : people) {
        if (person.birth <= year && year <= person.death) {
          alive++;
        }
      }
      
      if (alive > maxAlive) {
        maxAlive = alive;
        maxAliveYear = year;
      }
    }

    return maxAliveYear;
  }

  public static void main(String[] args) {
    int n = 10000;
    int first = 0;
    int last = 200000;
    Random random = new Random();
    Person[] people = new Person[n];
    for (int i = 0; i < n; i++) {
      int birth = first + random.nextInt(last - first);
      int death = birth + random.nextInt(last - birth);
      people[i] = new Person(birth, death);
      //System.out.println(birth + ", " + death);
    }

    System.out.println(n);
    for (int i = 0; i < n; i++) {
      //int birth = first + random.nextInt(last - first);
      //int death = birth + random.nextInt(last - birth);
      //people[i] = new Person(birth, death);
      System.out.println(people[i].birth);
    }
    System.out.println(n);
    for (int i = 0; i < n; i++) {
      //int birth = first + random.nextInt(last - first);
      //int death = birth + random.nextInt(last - birth);
      //people[i] = new Person(birth, death);
      System.out.println(people[i].death);
    }

    int year = maxAliveYear(people, first, last);
    System.out.println(year);
  }

}
