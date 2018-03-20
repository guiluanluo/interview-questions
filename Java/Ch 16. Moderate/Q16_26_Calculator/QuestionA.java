package Q16_26_Calculator;

import java.util.ArrayList;

/*
 * Calculator: given an arithmetic equation consisting of positive integers, +,-,*,and /(no parenttheses), comput the
 * result
 *
 * example: input 2*3+5/6*3+15		output: 23.5
 *
 * hint 520: can we just process the expression from left to right? why might this fail?
 *
 * hint 623: multiplication and division are higher priority operations. in an expression like 3*4 +5*9/2 +3, the
 * multiplication and division parts need to be grouped together.
 *
 * hint 664: consider thinking about it as, when you get to a multiplication or division sign, jumping to a separate
 * "process" to compute the result of this chunk.
 *
 * hint 697: you can also maintain two stacks, one for the operators and one for the numbers. you push a number onto the
 * stack every time you see it. what about the operators? when do you pop operators from the stack and apply them to the
 * numbers?
 *
 * Solution 2: we can solve this problem using two stacks: one for numbers and one for operators.
 *
 * 2-6-7*8/2+5: the processing works as following:
 * 1) each time we see a number, it gets pushed onto numberStack.
 * 2) operators get pushed onto operatorStack -- as long as the operator has higher priority than the current top of
 *    the stack. if prirority(currentOperator) <= priority(operatorStack.top()), then we "collaps" the top of the stacks:
 *    a) collapsing: pop two elements off numberStack, pop an operator from operatorStack, apply the operator, and push
 *      the result onto numberStack.
 *    b) priority: +,- has equal priority, which is lower than the priority of *,/(also equal priority)
 */
public class QuestionA {

  public static Term collapseTerm(Term primary, Term secondary) {
    if (primary == null) {
      return secondary;
    }
    if (secondary == null) {
      return primary;
    }

    double value = applyOp(primary.getNumber(), secondary.getOperator(), secondary.getNumber());
    primary.setNumber(value);
    return primary;
  }

  public static double applyOp(double left, Operator op, double right) {
    if (op == Operator.ADD) {
      return left + right;
    } else if (op == Operator.SUBTRACT) {
      return left - right;
    } else if (op == Operator.MULTIPLY) {
      return left * right;
    } else if (op == Operator.DIVIDE) {
      return left / right;
    } else {
      return right;
    }
  }

  /* Compute the result of the arithmetic sequence. This
     works by reading left to right and applying each term to
     a result. When we see a multiplication or division, we
     instead apply this sequence to a temporary variable. */
  public static double compute(String sequence) {
    ArrayList<Term> terms = Term.parseTermSequence(sequence);
    if (terms == null) {
      return Integer.MIN_VALUE;
    }

    double result = 0;
    Term processing = null;
    for (int i = 0; i < terms.size(); i++) {
      Term current = terms.get(i);
      Term next = i + 1 < terms.size() ? terms.get(i + 1) : null;

			/* Apply the current term to “processing”. */
      processing = collapseTerm(processing, current);

			/* If next term is + or -, then this cluster is done 
       * and we should apply “processing” to “result”. */
      if (next == null || next.getOperator() == Operator.ADD || next.getOperator() == Operator.SUBTRACT) {
        result = applyOp(result, processing.getOperator(), processing.getNumber());
        processing = null;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    String expression = "6/5*3+4*5/2-12/6*3/3+3+3";
    double result = compute(expression);
    System.out.println(result);
  }

}
