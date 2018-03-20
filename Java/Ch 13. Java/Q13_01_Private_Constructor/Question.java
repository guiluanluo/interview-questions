package Q13_01_Private_Constructor;

/**
 * Private Constructor: in terms of inheritance, what is the effect of keeping a constructor private?
 */
public class Question {

  private Question() {
    System.out.println("Q");
  }

  static class A {

    private A() {
      System.out.println("private constructor A");
    }
  }

  static class B extends A {

    public B() {
      System.out.println("public constructor B");
    }
  }

  public static void main(String[] args) {
    new Question.A();

    new Question.B();
//    new B();
  }

}
