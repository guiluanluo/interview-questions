package Q7_12_Hash_Table.using_binary_search_tree;


import java.util.Scanner;

public class Question {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Hash Table Test\n\n");
    System.out.println("Enter size");

    /* Make object of HashTableChainingBinaryTree  */
    HashTableChainingBinaryTree htcbt = new HashTableChainingBinaryTree(scan.nextInt());

    char ch;
        /*  Perform HashTableChainingBinaryTree operations  */
    do {
      System.out.println("\nHash Table Operations\n");
      System.out.println("1. insert ");
      System.out.println("2. remove");
      System.out.println("3. clear");
      System.out.println("4. size");
      System.out.println("5. check empty");

      int choice = scan.nextInt();
      switch (choice) {
        case 1:
          System.out.println("Enter integer element to insert");
          htcbt.insert(scan.nextInt());
          break;
        case 2:
          System.out.println("Enter integer element to delete");
          htcbt.remove(scan.nextInt());
          break;
        case 3:
          htcbt.makeEmpty();
          System.out.println("Hash Table Cleared\n");
          break;
        case 4:
          System.out.println("Size = " + htcbt.getSize());
          break;
        case 5:
          System.out.println("Empty status = " + htcbt.isEmpty());
          break;
        default:
          System.out.println("Wrong Entry \n ");
          break;
      }
            /* Display hash table */
      htcbt.printHashTable();

      System.out.println("\nDo you want to continue (Type y or n) \n");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');
  }

}
