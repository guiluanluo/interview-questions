package Q3_06_Animal_Shelter;

/**
 * Animal shelter: animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis.
 * people must adopt either the "oldest" (based on arrival time) or all animals at the shelter, or they can select
 * whether they would prefer a dog or a cat(and will receive the oldest animal of that type). they cannot select which
 * specific animal they would like. create the data structures to maintain this system and implement operations such as
 * enquence(), dequeueAny(), dequeueDog(), and dequeueCat(). you may use the build-in LinkedList data structure.
 *
 * hint 22: we could consider keeping a single linked list for dogs and cats, and then iterating through it to find the
 * first dog(or cat). what is the impact of doing this?
 *
 * hint 56: let's suppose we kept seperate lists for dogs and cats. how would we find the oldest animal of any type? be
 * creative!
 *
 * hint 63: think about how you'd do in real life. you have a list of dogs in chronological order and a list of cats in
 * chronological order. what data would you need to find the oldest animal? how would you maintain this data?
 */
public class Question {


  public static void main(String[] args) {
    AnimalQueue animals = new AnimalQueue();
    animals.enqueue(new Cat("Callie"));
    animals.enqueue(new Cat("Kiki"));
    animals.enqueue(new Dog("Fido"));
    animals.enqueue(new Dog("Dora"));
    animals.enqueue(new Cat("Kari"));
    animals.enqueue(new Dog("Dexter"));
    animals.enqueue(new Dog("Dobo"));
    animals.enqueue(new Cat("Copa"));
    System.out.println(animals.toString());

    System.out.println(animals.dequeueAny().name());
    System.out.println(animals.dequeueAny().name());
    System.out.println(animals.dequeueAny().name());
    System.out.println("After call dequeneAny() 3 times: " + animals.toString());

    animals.enqueue(new Dog("Dapa"));
    animals.enqueue(new Cat("Kilo"));
    System.out.println("After call enqueue() 2 times: " + animals.toString());

//    while (animals.size() != 0) {
//      System.out.println(animals.dequeueAny().name());
//    }
  }

}
