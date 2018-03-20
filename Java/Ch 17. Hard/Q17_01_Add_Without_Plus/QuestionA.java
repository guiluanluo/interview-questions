package Q17_01_Add_Without_Plus;

/*
 * Add without plus: write function that adds two numbers. you should not use + or any arithmetic operators.
 *
 * hint 466: walk through binary addition by hand(slowly) and try to really understand what is happening.
 *
 * hint 543: you can think about binary addition as iterating the number, bit by bit, adding tow bits, and then carrying
 * over the one if necessary. you could also think about it as grouping the operations. what if you first added each of
 * the bits (without carrying any overflow)? after that, you can handle the overflow
 *
 * hint 600: focus on just one of the steps above. if you "forgot" to carry the ones, what would the add operation look like?
 * hint 627: the adding step alone would convert 1+1>0,1+0->1,0+1->1,0+0->0. how do you do this without the + sign?
 * hint 641: you can do the add step with an XOR
 * hint 663: now focus on the carrying. in what cases will values carry? how do you apply the carry to the number?
 * hint 691: the carry will happen when you ar doing 1+1. how do you apply the carry to the number?
 * hint 711: you can use an AND operation to compute the carry. what do you do with it?
 * hint 723: you might need to do the add/carry operation more than once. adding carry to sum might cause new values to carry.
 *
 * Solution: our first instinct though that we are going to have to work with bits, the next though should be to deeply
 * understand how addition works. we can walk through an addition problem to see if we can understand something
 * new--some pattern--- and then see fi we can replicate that with code.
 *
 * so let's do just that -- let's walk through an addition problem. we will work in base 10 so that it's easier to see.
 * to add 759+674, i would usually add digit[0] from each number, carry the one, add digit[1] from each number, carry
 * the one and so on. you could take the same approach in binary: add each digit, and carry the one as necessary.
 *
 * can we make this a little easier? yes! imagine I decided to split apart the "addition" and "carry" steps. that is, I
 * do the following:
 *
 * 1) add 759+674, but "forget" to carry, I then get 323.
 * 2) add 759+674 but only do the carrying, rather than the addition of each digit. I then get 1110
 * 3) add the result of the first two operations (recursive, using the same process described in step 1 and 2): 1110+323
 * now, hwo would we do this in binary?
 * 1) if I add two binary numbers together, but forget to carry, the ith bit in the sum will be 0 only if a and b have
 *    the same ith bit(both 0 or 1). this is essentially an XOR.
 * 2) if I add two numbers together but only carry, I will have a 1 in the ith bit of the sum only if bits i
 *   - 1 of a and b are both 1s. this is an AND, shifted.
 * 3) now, recurse until there's nothing to carry.
 */
public class QuestionA {

  public static int add(int a, int b) {
    if (b == 0) {
      return a;
    }

    int sum = a ^ b; // add without carrying
    int carry = (a & b) << 1; // carry, but don't add
    System.out.println("** sum:" + Integer.toBinaryString(sum) + ", carry:" + Integer.toBinaryString(carry));

    return add(sum, carry); // recurse
  }

  public static void main(String[] args) {
    int number1 = 15;
    int number2 = 31;
    System.out.println("number1:" + Integer.toBinaryString(number1) + ", number2:" + Integer.toBinaryString(number2));
    int sum0 = add(number1, number2);
    int addSum = number1 + number2;
    System.out.println("==sum:0" + sum0 + ", addSum:" + addSum + "\n");

    int a = Integer.MAX_VALUE - 50;
    int b = 92;
    int sum = add(a, b);
    int intendedSum = a + b;
    if (sum != intendedSum) {
      System.out.println("ERROR");
    } else {
      System.out.println("SUCCESS");
    }
    System.out.println(a + " + " + b + " = " + sum + " vs " + intendedSum);
  }

}
