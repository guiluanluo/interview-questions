package Q7_09_Circular_Array;

/**
 * Circular Array: implement a CircularArray class that supports an array-like data structure which can be efficiently
 * rotated. if possible, the class should use a generic type(also called a template), and should support iteration via
 * the standard for (Obj o: circularArray) notation.
 *
 * Solution: this problem really has two parts to it. first, we need to implement the CircularArray class. second, we
 * need to support iteration. we will address these parts separately.
 *
 * one way to implement the CircularArray class is to actually is to actually shift the elements each time we call
 * rotate(int shiftRight). doing this is, of course, not very efficient. instead, we ca just create a member variable
 * head which points to what should be conceptually viewed as the start of circular array. rather than shift around the
 * elements in the array, we just increment head by shiftRight.
 *
 * Note: when you get a problem like this one in an interview, there is a goo chance you don't rememver exactly what the
 * various methods and interfaces are called. in this case, work through the problem as well as you can. if you can
 * reason out what sort of methods on might need, that alone will show a good degree of competency.
 */
public class Question {


  public static void main(String[] args) {
    int size = 10;
    CircularArray<String> array = new CircularArray<String>(size);
    for (int i = 0; i < size; i++) {
      array.set(i, String.valueOf(i));
    }
    array.print();

    System.out.println("after rotate 3...");
    array.rotate(3);
    array.print();

    System.out.println("after rotate 2...");
    array.rotate(2);
    array.print();
    System.out.println("after set 0-11, 2-12...");
    array.set(0, "11");
    array.set(2, "12");
    array.print();
  }

}
