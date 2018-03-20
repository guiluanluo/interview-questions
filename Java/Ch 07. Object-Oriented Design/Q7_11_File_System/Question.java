package Q7_11_File_System;

/**
 * File system: explain the data structures and algorithms that you would use to design an in-memory file sysem.
 * Illustrate with an example in code where possible.
 *
 * hint 141: this is not as complicated as it sounds. start by making a list of key object in the system, then think
 * about how they interact.
 *
 * hint 216: what is the relationship between files and directories?
 *
 * Solution: a file system, in its most simplistic version, consists of Files and Directories. each Directory contains a
 * set of Files and Directories.since Files and Directories share so many characteristics, we have implemented them such
 * that they inherit from the same class, Entry.
 */
public class Question {

  public static void main(String[] args) {
    Directory root = new Directory("Food", null);
    File taco = new File("Taco", root, 4);
    File hamburger = new File("Hamburger", root, 9);
    root.addEntry(taco);
    root.addEntry(hamburger);

    Directory healthy = new Directory("Healthy", root);

    Directory fruits = new Directory("Fruits", healthy);
    File apple = new File("Apple", fruits, 5);
    File banana = new File("Banana", fruits, 6);
    fruits.addEntry(apple);
    fruits.addEntry(banana);

    healthy.addEntry(fruits);

    Directory veggies = new Directory("Veggies", healthy);
    File carrot = new File("Carrot", veggies, 6);
    File lettuce = new File("Lettuce", veggies, 7);
    File peas = new File("Peas", veggies, 4);
    veggies.addEntry(carrot);
    veggies.addEntry(lettuce);
    veggies.addEntry(peas);

    healthy.addEntry(veggies);

    root.addEntry(healthy);

    System.out.println(root.numberOfFiles());
    System.out.println(veggies.getFullPath());
  }

}
